/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbccon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author sheshant
 */
public class admin_table extends javax.swing.JFrame {

    /**
     * Creates new form admin_table
     */
    Connection con = null;
    String url = "jdbc:mysql://10.5.18.66:3306/";
    String dbName = "12CS10046";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "12CS10046";
        String password = "btech12";
    String user,pass,sql;
    
    PreparedStatement pst = null;
    boolean a,b,c;
    String my_id = null,tab = "";
    public admin_table() {
        initComponents();
        try {
          Class.forName(driver).newInstance();
          con = DriverManager.getConnection(url+dbName,userName,password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        }
        report_pending();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        close = new javax.swing.JButton();
        admin_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        print = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADMIN DATA");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        close.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        close.setForeground(new java.awt.Color(0, 102, 102));
        close.setText("close");
        close.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        admin_label.setFont(new java.awt.Font("Tekton Pro Ext", 1, 18)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setEnabled(false);
        jScrollPane1.setViewportView(table);

        print.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        print.setForeground(new java.awt.Color(0, 102, 102));
        print.setText("print");
        print.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(admin_label, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(444, 444, 444)
                                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(162, 162, 162)
                                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 427, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(admin_label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        if(con != null)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        MessageFormat footer = new MessageFormat("page,{0,number ,integer}");
        MessageFormat header = new MessageFormat("list of "+tab);
        try
        {
            boolean print1 = table.print(JTable.PrintMode.NORMAL, header, footer);
        }
        catch(java.awt.print.PrinterException pr)
        {
            JOptionPane.showMessageDialog(null, "printer not found", "INVALID",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_printActionPerformed

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
            java.util.logging.Logger.getLogger(admin_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_table().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel admin_label;
    private javax.swing.JButton close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton print;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    void set_id(String p) {
        
        if(p.equals("d"))
        {
            tab = "doctors";
            Statement st = null;
            //
            ResultSet rs = null;
            try {
                st = con.createStatement();
                admin_label.setText("DOCTORS DETAILS");
                sql = "select * from DOCTOR;";
                rs = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
        try{
            if(rs != null)
            {
                rs.close();
            }
           
           if(pst != null)
            {
                pst.close();
            }
           if(st != null)
            {
                st.close();
            }
            
    }       catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        }
        else if(p.equals("p"))
        {
            tab = "patients";
            Statement st = null;
            //st = con.createStatement();
            ResultSet rs = null;
            try {
                st = con.createStatement();
                admin_label.setText("PATIENTS DETAILS");
                sql = "select * from PATIENT;";
                rs = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
        try{
            if(rs != null)
            {
                rs.close();
            }
           
           if(pst != null)
            {
                pst.close();
            }
           if(st != null)
            {
                st.close();
            }
            
    }       catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        }
        
    }
    void show_paid_status()
    {
        
        Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                admin_label.setText("LIST OF PATIENTS WITH DUES PENDING");
                sql = "select * from PATIENT as P natural join BILL_PATIENT AS BP join BILL as B where BP.BILL_ID = B.BILL_ID and B.PAID_STATUS = 'not paid';";
                rs = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
        try{
            if(rs != null)
            {
                rs.close();
            }
           
           if(pst != null)
            {
                pst.close();
            }
           if(st != null)
            {
                st.close();
            }
            
    }       catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    void test_and_doctor()
    {
        
        Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                admin_label.setText("TESTS AND CORRESPONDING DOCTORS");
                sql = "select T.test_id as test_id,T.name as test_name,T.subtype,T.category,D.DOCTOR_ID as DOCTOR_ID ,D.name as name from TEST as T natural join TEST_DOCTOR as TD join DOCTOR as D where TD.DOCTOR_ID = D.DOCTOR_ID ;";
                rs = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
        try{
            if(rs != null)
            {
                rs.close();
            }
           
           if(pst != null)
            {
                pst.close();
            }
           if(st != null)
            {
                st.close();
            }
            
    }       catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    void doctor_and_patient()
    {
        
        Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                admin_label.setText("DOCTORS WITH THEIR CORRESPONDING PATIENTS");
                sql = "select * from PATIENT AS P JOIN DOCTOR AS D where exists (select * from TEST_DOCTOR as TD join TEST_PATIENT as TP where TP.test_id = TD.test_id and TD.DOCTOR_ID = D.DOCTOR_ID and TP.PATIENT_ID = P.PATIENT_ID);";
                rs = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
        try{
            if(rs != null)
            {
                rs.close();
            }
           
           if(pst != null)
            {
                pst.close();
            }
           if(st != null)
            {
                st.close();
            }
            
    }       catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    void report_pending()
    {
        
        Statement st = null;
            ResultSet rs = null;
            try {
                st = con.createStatement();
                admin_label.setText("PATIENTS WITH PENDING REPORTS");
                sql = "select TP.test_id,(select name as test_name from TEST where test_id = TP.test_id) as test_name,(select category as test_category from TEST where test_id = TP.test_id) as category,TP.PATIENT_ID,(select name as patient_name from PATIENT where PATIENT_ID = TP.PATIENT_ID) as patient_name,TP.RESULT,TP.DATE_OF_TEST ,(select DOCTOR_ID as doctor_id from TEST_DOCTOR as TD where TD.test_id = TP.test_id) as doctor_id,(select name from DOCTOR where DOCTOR_ID = (select DOCTOR_ID as doctor_id from TEST_DOCTOR as TD where TD.test_id = TP.test_id)) as doctor_name from TEST_PATIENT as TP where TP.RESULT = 'NA';";
                rs = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
            } catch (SQLException ex) {
                Logger.getLogger(admin_table.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
        try{
            if(rs != null)
            {
                rs.close();
            }
           
           if(pst != null)
            {
                pst.close();
            }
           if(st != null)
            {
                st.close();
            }
            
    }       catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
