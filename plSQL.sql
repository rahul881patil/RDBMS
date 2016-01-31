set serveroutput on 

-- question 1 - sequences for pur#, supply#, log#

create sequence purchases_seq
MINVALUE 100015
START WITH 100015
INCREMENT BY 1
CACHE 20;

create sequence supply_seq
MINVALUE 1010
START WITH 1010
INCREMENT BY 2
CACHE 20;  

create sequence logs_seq
MINVALUE 23451
START WITH 23451
INCREMENT BY 5
CACHE 20;  

-- Question 2 - show all tables

create or replace package show as 
	procedure show_employees ( c_ursor OUT SYS_REFCURSOR );
	procedure show_customers ( c_ursor OUT SYS_REFCURSOR );
	procedure show_products  ( c_ursor OUT SYS_REFCURSOR );
	procedure show_purchases ( c_ursor OUT SYS_REFCURSOR );
	procedure show_suppliers ( c_ursor OUT SYS_REFCURSOR );
	procedure show_supply    ( c_ursor OUT SYS_REFCURSOR );
	procedure show_logs      ( c_ursor OUT SYS_REFCURSOR );
end;	
/

create or replace package body show as 
	procedure show_employees( c_ursor OUT SYS_REFCURSOR ) is 
	begin 
	open c_ursor for
		select * from employees;
	end;

	procedure show_customers ( c_ursor OUT SYS_REFCURSOR ) is
       	begin
       	open c_ursor for
		select * from customers;
       	end;

	procedure show_products ( c_ursor OUT SYS_REFCURSOR ) is
	begin
        open c_ursor for
                select * from products;
        end;

	procedure show_purchases ( c_ursor OUT SYS_REFCURSOR ) is 
	begin
        open c_ursor for
                select * from purchases;
        end;

	procedure show_suppliers ( c_ursor OUT SYS_REFCURSOR ) is 
	begin
        open c_ursor for
                select * from suppliers;
        end;

	procedure show_supply ( c_ursor OUT SYS_REFCURSOR ) is 
	begin
        open c_ursor for
                select * from supply;
        end;

        procedure show_logs ( c_ursor OUT SYS_REFCURSOR ) is
        begin
        open c_ursor for
                select * from logs;
        end;

end;
/


-- Question 3 - display report as per pid, valid pis id must as input parameter


create or replace procedure show_report(
			p_id in purchases.pid % type,
			c_cursor OUT SYS_REFCURSOR  ) is
begin
	open c_cursor for 
		select products.pname as PNAME,
		to_char(purchases.ptime,'MON') as PMON,
		to_char(purchases.ptime,'YYYY') as PYEAR,
		sum(purchases.qty) as TOTAL,
		( sum(purchases.total_price) / sum(purchases.qty)) as AVG_MONTH from purchases, products
		WHERE purchases.pid = products.pid
		AND purchases.pid = p_id
		GROUP BY to_char(purchases.ptime,'MON'), to_char(purchases.ptime,'YYYY'), products.pname;
end;
/


-- Question 4 - will add products, customer, employees detail to purchase table

create or replace procedure add_purchase(
		e_id in purchases.eid % type, 
		p_id in purchases.pid % type, 
		c_id in purchases.cid % type,
		pur_qty in purchases.qty % type) is
		t_price purchases.total_price % type;  
begin

	SELECT ( pur_qty * ( original_price * ( 1 - discnt_rate) ) ) INTO t_price FROM products WHERE pid = p_id; 
	insert into purchases values (
					purchases_seq.NEXTVAL,
					e_id, p_id, c_id,
					pur_qty,
					sysdate,
					t_price );
exception
        when others then
                dbms_output.put_line(' Insufficient Quantity on Stock');

end;
/


-- Add product to product table 

create or replace procedure add_product(
				p_id    products.pid % type,
				p_name  products.pname % type,
				q_oh    products.qoh % type,
				q_th    products.qoh_threshold % type,
				o_price products.original_price  % type,
				d_rate  products.discnt_rate % type) is 
begin

	insert into products values ( p_id, p_name, q_oh, q_th, o_price, d_rate);

end;
/


-- Question 5 - every trigger will be fired if any changes happen to logg in to log table

create or replace trigger purchases_tri
after insert on purchases
for each row
declare
	user_n logs.who % type;
begin 
	select user into user_n from dual;
	insert into logs values( logs_seq.NEXTVAL, user_n, SYSDATE, 'purchases', 'insert', purchases_seq.currval);
end;
/


create or replace trigger products_tri
after update of qoh on products
for each row
declare
        user_n logs.who % type;
begin
        select user into user_n from dual;
        insert into logs values
        ( logs_seq.NEXTVAL, user_n, SYSDATE, 'products', 'update', :old.pid);
end;
/

create or replace trigger customers_tri
after update of visits_made on customers
for each row
declare 
	user_n logs.who % type;
begin
	select user into user_n from dual;
	insert into logs values
	( logs_seq.NEXTVAL, user_n, SYSDATE, 'customers', 'update', :old.cid);
end;
/

create or replace trigger supply_tri
after insert on supply
declare
	user_n logs.who % type;
begin
	select user into user_n from dual;
        insert into logs values( logs_seq.NEXTVAL, user_n, SYSDATE, 'supply', 'insert', supply_seq.currval);
end;
/


-- Question 6  - check requested quantity should be less than qoh else reject the request

create or replace trigger purchases_qoh_tri
before insert on purchases
for each row
declare 
	q_oh products.qoh % type;
	insuff_qoh exception;
begin
	select qoh into q_oh from products where pid = :new.pid;
	if ( :new.qty > q_oh ) then raise insuff_qoh;
	end if;
exception 
	when insuff_qoh then
		 raise_application_error(-20001, 'Insufficient Quantity in Stock! ');
end;
/

-- Question 7

-- reduce the qoh if new purchase is made

create or replace trigger update_prod_chk_tri
after insert on purchases
for each row
declare
	q_oh products.qoh % type;
	u_qoh products.qoh % type;
begin
	
	select qoh into q_oh from products where pid = :new.pid;
	u_qoh := q_oh - :new.qty;

	update products
	set qoh = u_qoh
	where pid = :new.pid;
	
end;
/

-- update quantity of a product if new purchase added and warn if qoh < qoh_threshold
-- order new supply with a sepecific quantity such that qoh > qoh_threshold

create or replace trigger chk_product_tri
after insert on purchases
for each row
declare
	s_id supply.sid % type;
	q_oh products.qoh % type;
	q_th products.qoh_threshold % type;
begin
	select qoh, qoh_threshold into q_oh, q_th from products where pid = :new.pid;

	if ( q_oh < q_th ) then 
		dbms_output.put_line( 'For Product '||:new.pid||' Current Quantity ON HAND '||q_oh||' is below threshold');
		dbms_output.put_line( 'New supply is required for '||:new.pid);

		select min(sid) into s_id from supply where pid = :new.pid;
		dbms_output.put_line( 'New '||:new.pid||' '||(10 + (q_th - q_oh) + 1 + q_oh)||' '||s_id);

		insert into supply
		values(supply_seq.NEXTVAL, :new.pid, s_id, SYSDATE, ((10 + q_th - q_oh) + 1 + q_oh));

		dbms_output.put_line( 'New supply is ordered !');

	end if;	
end;
/

-- increase quantity of a product if new supply id ordered

create or replace trigger update_prod_qoh_tri
after insert on supply
for each row
declare
        q_oh products.qoh % type;
        u_qoh products.qoh % type;
begin
        select qoh into q_oh from products where pid = :new.pid;
        u_qoh := q_oh + :new.quantity;

        update products
        set qoh = u_qoh
        where pid = :new.pid;

        dbms_output.put_line(' For pid = '||:new.pid||' QOH updated to '||u_qoh);
end;
/

-- update visits and last visit date if new date purchase found

create or replace trigger update_cust_visit
after insert on purchases
for each row
declare
        visit customers.visits_made % type;
        u_visit customers.visits_made % type;
        l_date customers.last_visit_date % type;
begin
        select last_visit_date into l_date from customers where cid = :new.cid;

        if ( to_date(:new.ptime, 'dd/mm/yyyy') != to_date(l_date, 'dd/mm/yyyy')) then
                
		select visits_made into visit from customers where cid = :new.cid;
                u_visit := visit + 1;

                update customers
                set visits_made = u_visit,
                last_visit_date = :new.ptime
                where cid = :new.cid;
        
	end if;
end;
/

