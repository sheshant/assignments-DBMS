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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author SHESHANT
 */
public class welcome_doctor extends javax.swing.JFrame {

    /**
     * Creates new form welcome_doctor
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
    String doctor_id = null;
    DefaultTableModel cm;
    public welcome_doctor() {
        initComponents();
        try {
          Class.forName(driver).newInstance();
          con = DriverManager.getConnection(url+dbName,userName,password);
//          st=con.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
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
        jPanel2 = new javax.swing.JPanel();
        welcome_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        patient_id = new javax.swing.JTextField();
        test_id = new javax.swing.JTextField();
        submit_results = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        view_patients = new javax.swing.JMenuItem();
        view_patient_full_details = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        update_details = new javax.swing.JMenuItem();
        change_password = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("WELCOME DOCTOR");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        welcome_label.setFont(new java.awt.Font("Tekton Pro Ext", 1, 18)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "patient id", "patient name", "patient date of birth", "corresponding test", "patient gender", "test date", "nulltest price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Patient id");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("test id");

        patient_id.setEditable(false);
        patient_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        patient_id.setForeground(new java.awt.Color(0, 102, 51));
        patient_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patient_idActionPerformed(evt);
            }
        });

        test_id.setEditable(false);
        test_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        test_id.setForeground(new java.awt.Color(0, 102, 51));
        test_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                test_idActionPerformed(evt);
            }
        });

        submit_results.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        submit_results.setForeground(new java.awt.Color(0, 102, 102));
        submit_results.setText("submit results");
        submit_results.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submit_results.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_resultsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(welcome_label, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(test_id))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(submit_results, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addComponent(patient_id)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(welcome_label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(test_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(submit_results, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("View");

        view_patients.setText("view your patients");
        view_patients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_patientsActionPerformed(evt);
            }
        });
        jMenu1.add(view_patients);

        view_patient_full_details.setText("view patient full details");
        view_patient_full_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_patient_full_detailsActionPerformed(evt);
            }
        });
        jMenu1.add(view_patient_full_details);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        update_details.setText("update details");
        update_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_detailsActionPerformed(evt);
            }
        });
        jMenu2.add(update_details);

        change_password.setText("change password");
        change_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_passwordActionPerformed(evt);
            }
        });
        jMenu2.add(change_password);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void test_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_test_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_test_idActionPerformed

    private void view_patientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_patientsActionPerformed
        Statement st = null;
        ResultSet rs = null;
        try {
            sql = "select PATIENT_ID as patient_id,P.name as patient_name,DOB,P.gender as gender,phone_number,T.test_id as test_id,T.name as test_name,T.subtype,S.RESULT as result,S.DATE_OF_TEST as date_of_test from PATIENT as P natural join TEST_PATIENT as S join TEST as T where S.test_id = T.test_id ;";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setAutoCreateRowSorter(true);
            ColumnsAutoSizer.sizeColumnsToFit(table);
        } catch (SQLException ex) {
            Logger.getLogger(welcome_doctor.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            if(rs != null)
            {
                try {
                    rs.close();
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
    }//GEN-LAST:event_view_patientsActionPerformed

    private void update_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_detailsActionPerformed
        update_doctor ud = new update_doctor();
        ud.set_id(doctor_id);
        ud.show();
// TODO add your handling code here:
    }//GEN-LAST:event_update_detailsActionPerformed

    private void change_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change_passwordActionPerformed
        doctor_password dp = new doctor_password();
        dp.set_id(doctor_id);
        dp.show();
// TODO add your handling code here:
    }//GEN-LAST:event_change_passwordActionPerformed

    private void submit_resultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_resultsActionPerformed
        String ptid = patient_id.getText();
        String tid = test_id.getText();
        if(ptid.length() == 0||tid.length() == 0)
        {
            JOptionPane.showMessageDialog(null,"please first choose patient from table","ERROR" ,JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            results_entry re = new results_entry();
            re.set_id(doctor_id,patient_id.getText(),test_id.getText());
            re.show();
        }
    }//GEN-LAST:event_submit_resultsActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        try{
            int row = table.getSelectedRow();
            String ptid = (String) table.getModel().getValueAt(row, 0);
            String tid = (String) table.getModel().getValueAt(row, 5);
            patient_id.setText(ptid);
            test_id.setText(tid);
        }catch(Exception e)
        {
            System.out.println("bhak");
        }
        
    }//GEN-LAST:event_tableMouseClicked

    private void view_patient_full_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_patient_full_detailsActionPerformed
        patient_details pd = new patient_details();
        pd.show();
    }//GEN-LAST:event_view_patient_full_detailsActionPerformed

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
            java.util.logging.Logger.getLogger(welcome_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(welcome_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(welcome_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(welcome_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new welcome_doctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem change_password;
    private javax.swing.JButton conform;
    private javax.swing.JButton conform1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField patient_id;
    private javax.swing.JButton submit_results;
    private javax.swing.JTable table;
    private javax.swing.JTextField test_id;
    private javax.swing.JMenuItem update_details;
    private javax.swing.JMenuItem view_patient_full_details;
    private javax.swing.JMenuItem view_patients;
    private javax.swing.JLabel welcome_label;
    // End of variables declaration//GEN-END:variables

    void set_id(String id) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            sql = "select name from DOCTOR where DOCTOR_ID = '"+id+"';";
            rs = st.executeQuery(sql);
            rs.next();
            welcome_label.setText("WELCOME "+rs.getString("name"));
            doctor_id = id;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,null,String.valueOf(ex) ,JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(welcome_doctor.class.getName()).log(Level.SEVERE, null, ex);
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
            if(st != null)
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(results_entry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
