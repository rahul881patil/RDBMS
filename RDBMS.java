/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygui;
import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;
/**
 *
 * @author Rahul
 */
public class RDBMS extends javax.swing.JFrame {

    /**
     * Creates new form RDBMS
     */
    public static OracleDataSource ds = null;
    public static Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rset = null;
    public static CallableStatement cs = null;
    public static ResultSetMetaData rsetMData = null;
    public static int TableColumn = 0;
    public static int statusConn = 0;
    public static int addPurchase = 0;

    
    public RDBMS() {
        initComponents();
    }
    
    public void populateDropDown(){
        try{
                stmt = conn.createStatement();
               
                //execute the stored procedure and sql stmt
                String sql_query_1 = "select eid from employees";
                String sql_query_2 = "select pid from products";
                String sql_query_3 = "select cid from customers";
                
                //SQL Query to see QOH of a product
                rset = stmt.executeQuery(sql_query_1);
                int i = 0;
                while(rset.next()){
                    jComboBox2.addItem(rset.getString(1));
                    i++;                      
                }
                
                //combo box 2 and 1
                rset = stmt.executeQuery(sql_query_2);
                i = 0;
                while(rset.next()){
                     jComboBox3.addItem(rset.getString(1));
                     jComboBox5.addItem(rset.getString(1));
                    i++;                      
                }
            
                //combo box 3
                rset = stmt.executeQuery(sql_query_3);
                i = 0;
                while(rset.next()){
                    jComboBox4.addItem(rset.getString(1));
                    i++;                      
                }
                
        }catch(Exception e){
             System.out.println(e.getMessage());
            
        }
        
    }
    
    public void cleanTables(){
        // Clean the tables 1, 2, 3
        for(int i=0; i< jTable1.getRowCount(); i++){
            for(int j=0; j< jTable1.getColumnCount(); j++){
                jTable1.getModel().setValueAt(" ", i, j);           
            }
        }    
        for(int i=0; i< jTable2.getRowCount(); i++){
            for(int j=0; j< jTable2.getColumnCount(); j++){  
                jTable2.getModel().setValueAt(" ", i, j);  
            }
        }    
        for(int i=0; i< jTable3.getRowCount(); i++){
            for(int j=0; j< jTable3.getColumnCount(); j++){
                jTable3.getModel().setValueAt(" ", i, j);
            }
        }    
    }
    
    public void updateTable2_3(){
        try{
                //Show table 2 with products
                cs = conn.prepareCall("{call show.show_products( ? )}");
                cs.registerOutParameter (1, OracleTypes.CURSOR);
                cs.executeQuery();
                rset = (ResultSet) cs.getObject(1);
                rsetMData = rset.getMetaData();
                TableColumn = rsetMData.getColumnCount();
              
                // Display data into table
                jTable2.setAutoCreateRowSorter(true);
                jTable2.setModel(new DefaultTableModel(100, TableColumn));
                int i = 0, j=0;
                while(rset.next()){
                   for(j=0; j< TableColumn; j++){
                       jTable2.getModel().setValueAt(rset.getString(j+1), i, j);
                   } 
                   i++;
                }

                //Show table 3 with logs
                cs = conn.prepareCall("{call show.show_logs( ? )}");
                cs.registerOutParameter (1, OracleTypes.CURSOR);
                cs.executeQuery();
                rset = (ResultSet) cs.getObject(1);
                rsetMData = rset.getMetaData();
                TableColumn = rsetMData.getColumnCount();
              
                // Display data into table
                jTable3.setAutoCreateRowSorter(true);
                jTable3.setModel(new DefaultTableModel(500, TableColumn));
                i = 0; j=0;
                while(rset.next()){
                   for(j=0; j< TableColumn; j++){
                       jTable3.getModel().setValueAt(rset.getString(j+1), i, j);
                   } 
                   i++;
                } 
        }catch(Exception e){
            jLabel3.setText(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Status\n");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoscrolls(false);
        jTable1.setEditingColumn(10);
        jTable1.setEditingRow(10);
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Clear");
        jButton2.setSize(new java.awt.Dimension(96, 27));
        jButton2.setVerifyInputWhenFocusTarget(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Connect");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RDMS Systems");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employees", "Customers", "Products", "Purchases", "Supply", "Suppliers", "Logs" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton4.setText("Add Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jTextField1.setText("QTY");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton5.setText("Disconnect");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Products");

        jLabel5.setText("Logs");

        jLabel6.setText("Table");

        jButton1.setText("Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("Add Product");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField3.setText("PID");

        jTextField4.setText("PNAME");

        jTextField5.setText("QOH");

        jTextField6.setText("Qoh_threshold");

        jTextField7.setText("Price");

        jTextField8.setText("Discount");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jLabel4)
                        .addGap(342, 342, 342)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(344, 344, 344))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(297, 297, 297))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jTextField2))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Code to connect to data base
        try{
                ds = new oracle.jdbc.pool.OracleDataSource();
                ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
                conn = ds.getConnection("pchaudh2", "Careeratus2");
                stmt = conn.createStatement();   
                jLabel2.setText("Status : Connecte to \"castor.cc.binghamton.edu\" ! ");
                jLabel2.setOpaque(true);
                jLabel2.setBackground(Color.green);
                statusConn = 1;
                
                //Show Table 2 and 3
                updateTable2_3();
                
                //Show drop Down
                populateDropDown();
         
        }catch(Exception e){
            jLabel3.setText("Cant Connect to Data Base Server!");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        // Disconnect from server
        try{
            if ( statusConn == 1) {
                    conn.close();
                    cleanTables();
                    statusConn = 0;
                    jLabel2.setText("Status : Disconnected");
                    jLabel2.setOpaque(true);
                    jLabel2.setBackground(Color.red);
                    jLabel3.setOpaque(false);
                    jLabel3.setText("");
                     //clear text field
        jTextField2.setText(" ");
            }
        }catch(Exception e){
            jLabel3.setText(e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        // Display Table by Choice
        if (statusConn == 1){
            String tableName = jComboBox1.getSelectedItem().toString();
            String sql_string = "{call show.show_"+tableName.toLowerCase()+"( ? )}";
        
            try{
            
                jLabel6.setText(tableName);
                cs = conn.prepareCall(sql_string);
                cs.registerOutParameter (1, OracleTypes.CURSOR);
                cs.executeQuery();
                rset = (ResultSet) cs.getObject(1);
                rsetMData = rset.getMetaData();
                TableColumn = rsetMData.getColumnCount();
              
                // Display data into table
                jTable1.setAutoCreateRowSorter(true);
                jTable1.setModel(new DefaultTableModel(100, TableColumn));
                int i = 0, j=0;
                for(int z = 0; z< TableColumn; z++){
                    jTable1.getModel().setValueAt(rsetMData.getColumnName(z+1), 0, z);
                }
                i=1;
                while(rset.next()){
                   for(j=0; j< TableColumn; j++){
                       jTable1.getModel().setValueAt(rset.getString(j+1), i, j);
                   } 
                   i++;
                }
            }catch(Exception e){
                jLabel3.setText(e.getMessage());
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // Clean table and any Status labels

        // clean the table1
        for(int i=0; i< jTable1.getRowCount(); i++){
            for(int j=0; j< jTable1.getColumnCount(); j++){
                jTable1.getModel().setValueAt(" ", i, j);
            }
        }
        
        //Clean the labels
        jLabel3.setOpaque(false);
        jLabel3.setText(" ");
        
        //clear text field
        jTextField2.setText(" ");
          
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // Update Purchase
        try{
               
            if(statusConn == 1){
                    
                //Clean the labels
                jLabel3.setOpaque(false);
                jLabel3.setText(" ");
        
                //clear text field
                jTextField2.setText(" ");
                
                //Update function
                cs = conn.prepareCall("{call add_purchase(?, ?, ?, ?)}");
                stmt = conn.createStatement();
                
                String e_id = jComboBox2.getSelectedItem().toString();
                String pp_id =  jComboBox3.getSelectedItem().toString();
                String c_id = jComboBox4.getSelectedItem().toString();
                String q_ty = jTextField1.getText();
                
                cs.setString(1, e_id);
                cs.setString(2, pp_id);
                cs.setString(3, c_id);
                cs.setString(4, q_ty);
		     
                //execute the stored procedure and sql stmt
                String p_id = "'"+jComboBox3.getSelectedItem().toString()+"'";
                String sql_query = "select qoh, qoh_threshold from products where pid ="+p_id;
                
                //SQL Query to see QOH of a product
                rset = stmt.executeQuery(sql_query);
                rset.next();

                int q_oh = Integer.parseInt(rset.getString(1));
                int q_th = Integer.parseInt(rset.getString(2));
                int c_qt = Integer.parseInt(jTextField1.getText());
                
                 // Check QOH of a produt then only update
		if(c_qt <= q_oh){
                    
                    // add purchase in to purchase table
                    cs.executeUpdate();
                    
                    // check current qoh is less than qoh_threshold
                    int check = q_oh - c_qt;
                    if(check < q_th){
                        String message = "Current Quantity On Hand is "+check+" below threshold for PID :" + jComboBox3.getSelectedItem().toString() +
                                "\n" + "New Supply is required! "+
                                "\n" + " Ordered new supply with quantity of " + ( (10 + (q_th - (q_oh - c_qt))) + 1 + (q_oh - c_qt)) ; 
                        jTextField2.setText(message);
                    }else{
                            jLabel3.setText("Purchase added Succesfully!");
                    }
                    //Show Table 2 and 3
                    updateTable2_3();
                }else{
                    jLabel3.setOpaque(true);
                    jLabel3.setBackground(Color.red);
                    jLabel3.setText("Can't Purchase! Insufficient Quantity On Stock"); 
                }
            }    
        }catch(Exception e){
            System.out.println(e.getMessage());
            jLabel3.setText("Please Enter Valid EID, PID, CID - char, Quantity - Number 5");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Show report 
        try{
            if(statusConn == 1){
                cs = conn.prepareCall("{call show_report( ?, ? )}");
                cs.setString (1, jComboBox5.getSelectedItem().toString());
                cs.registerOutParameter (2, OracleTypes.CURSOR);
                cs.executeQuery();
                rset = (ResultSet) cs.getObject(2);
                rsetMData = rset.getMetaData();
                TableColumn = rsetMData.getColumnCount();
               
                //Display data into table
                jTable1.setAutoCreateRowSorter(true);
                jTable1.setModel(new DefaultTableModel(100,TableColumn));
                int i = 1, j=0;
                for(int z = 0; z< TableColumn; z++){
                    jTable1.getModel().setValueAt(rsetMData.getColumnName(z+1), 0, z);
                }
                while(rset.next()){
                   for(j=0; j< TableColumn; j++){
                       jTable1.getModel().setValueAt(rset.getString(j+1), i, j);
                   } 
                   i++;
                }
                       
            }else{
                 jLabel3.setText("Please connect to DB !");
            }
        }catch(Exception e){
            jLabel3.setText("PID Does not Exist"+ e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        // Add Product to the products table 
        try{
               
            if(statusConn == 1){
                    
               //Clean the labels
               jLabel3.setOpaque(false);
               jLabel3.setText(" ");
        
               //clear text field
               jTextField2.setText(" ");
                
                //Update function
               cs = conn.prepareCall("{call add_product(?, ?, ?, ?, ?, ?)}");
               stmt = conn.createStatement();
                
               String p_id = jTextField3.getText();
               String p_name = jTextField4.getText();
               String q_oh = jTextField5.getText();
               String q_th = jTextField6.getText();
               String o_price = jTextField7.getText();
               String d_rate = jTextField8.getText();
                
               // check valid pid for duplicate 
               String sql_query = "select pid from products";
               rset = stmt.executeQuery(sql_query);
               boolean valid_pid = true;
               int i = 0;
               while(rset.next()){
                if(rset.getString(1).equals(p_id)){
                    valid_pid = false;
                }
               } 
               
               if(valid_pid == true){
                   // can add the product
                   if(Integer.parseInt(q_oh) == (int)Integer.parseInt(q_oh)){
                      // can add product 
                       if(Integer.parseInt(q_th) == (int)Integer.parseInt(q_th)){
                        // can add product   
                            if(Float.parseFloat(o_price) == (float)Float.parseFloat(o_price)){
                             // can add product    
                                if(Float.parseFloat(d_rate) <= 0.8 && Float.parseFloat(d_rate) >= 0.0){
                                    // add product for sure
                                    cs.setString(1, p_id);
                                    cs.setString(2, p_name);
                                    cs.setString(3, q_oh);
                                    cs.setString(4, q_th);
                                    cs.setString(5, o_price);
                                    cs.setString(6, d_rate);
                                    cs.executeUpdate();
                                    jLabel3.setText("Product added !");
                                
                                    //Show Table 2 and 3
                                    updateTable2_3();
                                
                                }else{
                                    jLabel3.setText("Discount rate should be in between 0 - 0.8");
                                }
                            }else{
                                    jLabel3.setText("Price should be number(6, 2) format");
                            }
                        }else{
                                jLabel3.setText("Quantity/ Q_threshold should be integer value");
                        }   
                    }else{
                            jLabel3.setText("Quantity/ Q_threshold should be integer value");
                    } 
               }else{
                   jLabel3.setText("Duplicate PID found");
               }
            }    
        }catch(Exception e){
            System.out.println(e.getMessage());
            jLabel3.setText("Quantity/ Q_threshold should be integer and Price can be Float value");
        }
   
    }//GEN-LAST:event_jButton6ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RDBMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RDBMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RDBMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RDBMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RDBMS().setVisible(true);
 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
