package mc.impresoras;

import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mc.datos.Abonadora;
import mc.db.Transaccion;

/**
 ** @author luna
 **/
public class StatusDeCuenta extends javax.swing.JFrame {

    private DecimalFormat df = new DecimalFormat("#.00");
    private String fecha;

    public StatusDeCuenta(String folio, String subtotal, String iva,
            String total, DefaultTableModel model, String fecha) {
        initComponents();
        this.setResizable(false);
        jLabel2.setText(folio);
        jLabel4.setText(subtotal);
        jLabel5.setText(iva);
        jLabel8.setText(total);
        this.jTable2.setModel(model);
        this.fecha = fecha;
        cargarHistorial();
        Thread h1;
        h1 = new Thread() {
            @Override
            public void run() {
                int temp = jTable1.getRowCount();
                int temp2 = 0;
                try {
                    while (true) {
                        if (temp != temp2) {
                            sumaSubtotalAbono();
                            temp2 = temp;
                        }
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    java.util.logging.Logger.getLogger(StatusDeCuenta.class.getName()).log(Level.SEVERE, null, e);
                }
            }            
        };
        h1.start();
        
        Thread h2 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (Double.parseDouble(jLabel10.getText()) > 0) {
                            balanceadora();
                        }
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    Logger.getLogger(StatusDeCuenta.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            public void balanceadora() {
                double a = Double.parseDouble(jLabel8.getText());
                double b = Double.parseDouble(jLabel10.getText());
                jLabel11.setText(df.format(a - b) + "");
            }
        };
        h2.start();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("img/logo.png"));

        return retValue;
    }
    
    private void cargarHistorial() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<Abonadora> temp = Transaccion.searchAbonadora(Integer.parseInt(jLabel2.getText()));
        String[] fila = new String[3];

        for (int j = 0; j < temp.size(); j++) {
            fila[0] = temp.get(j).getIdDatos() + "";
            fila[1] = temp.get(j).getAbono() + "";
            fila[2] = temp.get(j).getFecha() + "";

            model.addRow(fila);
        }
        jTable1.setModel(model);
    }

    private void sumaSubtotalAbono() {
                double temp = 0;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    temp = temp + Double.parseDouble(jTable1.getValueAt(i, 1).toString());
                }
                jLabel10.setText(temp + "");
            }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("0.0");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 94, 112, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Total: $");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 94, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("IVA: $");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 61, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("0.0");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 61, 110, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("0.0");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 35, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Subtotal: $");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 35, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Folio:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 14, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 14, 112, 15));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Abono: $");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 127, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("0.0");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 127, 112, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("0.0");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 160, 90, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("A cuenta: $");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 160, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo1.png"))); // NOI18N
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, -6, 200, 430));

        jTable1.setBackground(new java.awt.Color(77, 164, 215));
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FOLIO", "ABONO", "FECHA"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setBackground(new java.awt.Color(77, 164, 215));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Producto", "Monto unitario", "Subtotal", "Fecha"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(114, 198, 59));
        jButton1.setText("Abonar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 14, 129, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 2, 1090, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = jTable1.getRowCount(); i > 0; i--) {
            model.removeRow(jTable1.getRowCount() - 1);
        }
        jTable1.setModel(model);
        Abonadora a = new Abonadora(0, Integer.parseInt(jLabel2.getText()), Double.parseDouble(jTextField1.getText()), fecha);
        if (!Transaccion.insertAbonadora(a)) {
            cargarHistorial();
            sumaSubtotalAbono();
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
