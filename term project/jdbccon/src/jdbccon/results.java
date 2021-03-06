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
 * @author SHESHANT
 */
public class results extends javax.swing.JFrame {

    /**
     * Creates new form results
     */
    Connection con = null;
    String url = "jdbc:mysql://10.5.18.66:3306/";
    String dbName = "12CS10046";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "12CS10046";
        String kk = "btech12";
    String user,pass,sql,my_id;
    
    PreparedStatement pst = null;
    boolean a,b,c;
    public results() {
        initComponents();
        try {
          Class.forName(driver).newInstance();
          con = DriverManager.getConnection(url+dbName,userName,kk);
//          st=con.createStatement();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        patient_id = new javax.swing.JTextField();
        conform = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        print = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        amount_text = new javax.swing.JTextField();
        pay_bill = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SEE YOUR RESULTS");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setText("Your patient id");

        patient_id.setEditable(false);
        patient_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        patient_id.setForeground(new java.awt.Color(0, 102, 51));
        patient_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patient_idActionPerformed(evt);
            }
        });

        conform.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        conform.setForeground(new java.awt.Color(0, 102, 102));
        conform.setText("Conform");
        conform.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        conform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conformActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jLabel3.setText("Result");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tests", "checkup date", "Result", "Bill", "Bill status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        print.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        print.setForeground(new java.awt.Color(0, 102, 102));
        print.setText("Print");
        print.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Total amount");

        amount_text.setEditable(false);
        amount_text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amount_text.setForeground(new java.awt.Color(0, 102, 51));
        amount_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amount_textActionPerformed(evt);
            }
        });

        pay_bill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pay_bill.setForeground(new java.awt.Color(0, 102, 102));
        pay_bill.setText("Pay Bill");
        pay_bill.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pay_bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_billActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(117, 117, 117)
                                    .addComponent(patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(59, 59, 59)
                                    .addComponent(conform, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(280, 280, 280)
                        .addComponent(amount_text, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(pay_bill, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(635, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(105, 105, 105)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conform, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 100, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amount_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pay_bill, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(269, 269, 269))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(285, 285, 285)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(337, Short.MAX_VALUE)))
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

    private void patient_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patient_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patient_idActionPerformed

    private void conformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conformActionPerformed
        my_id = patient_id.getText();
        Statement st = null;
    //
        ResultSet rs = null;
        ResultSet rs1 = null;
        
        try {
            sql = "select T.test_id as test_id,T.name as test_name,TP.result as result ,(select AMOUNT from BILL natural join BILL_PATIENT where PATIENT_ID = '"+my_id+"' and TEST_ID = T.test_id) as amount ,(select PAID_STATUS from BILL natural join BILL_PATIENT where PATIENT_ID = '"+my_id+"' and TEST_ID = T.test_id) as status from TEST as T natural join TEST_PATIENT as TP  where TP.PATIENT_ID = '"+my_id+"';";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setAutoCreateRowSorter(true);
            ColumnsAutoSizer.sizeColumnsToFit(table);
            rs1 = st.executeQuery(sql);
            int amount = 0;
            while(rs1.next())
            {
                String str = rs1.getString("status");
                if(str != null && !str.isEmpty())
                {
                    if(str.equals("not paid")) 
                    {
                        amount += rs1.getInt("amount");
                    }
                }
            }
            amount_text.setText(String.valueOf(amount));
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(rs != null)
            {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs1 != null)
            {
                try {
                    rs1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(st != null)
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_conformActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        MessageFormat footer = new MessageFormat("page,{0,number ,integer}");
        MessageFormat header = new MessageFormat(my_id+" results");
        try
        {
            boolean print1 = table.print(JTable.PrintMode.NORMAL, header, footer);
        }
        catch(java.awt.print.PrinterException pr)
        {
            JOptionPane.showMessageDialog(null, "printer not found", "INVALID",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_printActionPerformed

    private void amount_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amount_textActionPerformed
        
    }//GEN-LAST:event_amount_textActionPerformed

    private void pay_billActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_billActionPerformed
        Statement st = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            st = con.createStatement();
            sql = "update BILL set PAID_STATUS = 'paid' where PAID_STATUS = 'not paid' and BILL_ID in (select BILL_ID from BILL_PATIENT where PATIENT_ID = '"+my_id+"' );";
            int rowsAffected2 = st.executeUpdate(sql);
            if(rowsAffected2 == 0)
            {
                JOptionPane.showMessageDialog(null, "Not successfully updated table BILL_PATIENT","ERROR",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Successfully updated table BILL_PATIENT","ERROR",JOptionPane.WARNING_MESSAGE);
                sql = "select T.test_id as test_id,T.name as test_name,TP.result as result ,(select AMOUNT from BILL natural join BILL_PATIENT where PATIENT_ID = '"+my_id+"' and TEST_ID = T.test_id) as amount ,(select PAID_STATUS from BILL natural join BILL_PATIENT where PATIENT_ID = '"+my_id+"' and TEST_ID = T.test_id) as status from TEST as T natural join TEST_PATIENT as TP where TP.PATIENT_ID = '"+my_id+"';";
                st = con.createStatement();
                rs1 = st.executeQuery(sql);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setModel(DbUtils.resultSetToTableModel(rs1));
                table.setAutoCreateRowSorter(true);
                ColumnsAutoSizer.sizeColumnsToFit(table);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(results.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(rs != null)
            {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs1 != null)
            {
                try {
                    rs1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(st != null)
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_pay_billActionPerformed

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
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new results().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount_text;
    private javax.swing.JButton conform;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField patient_id;
    private javax.swing.JButton pay_bill;
    private javax.swing.JButton print;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    void set_id(String my_id) {
        patient_id.setText(my_id);
    }
}
