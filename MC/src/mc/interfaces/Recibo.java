package mc.interfaces;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mc.datos.*;
import mc.db.*;
import mc.impresoras.StatusDeCuenta;
import mc.impresoras.Visor;

/**
 ** @author luna
 *
 */
public class Recibo extends javax.swing.JFrame {

    /**
     ** Iniciamos con las variables basicas para este programa.
     *
     ** @param df controla los decimales de las cantidades flotantes.
     ** @param d maneja informacion de una tabla que se llama datos.
     ** @param m maneja informacion de una tabla que se llama monto.
     ** @param p maneja informacion de una tabla que se llama pedido.
     ** @param a maneja informacion de una tabla que se llama abonadora.
     ** @param calendario maneja el calendario.
*
     */
    private final DecimalFormat df = new DecimalFormat("#.00");
    private Datos d;
    private Monto m;
    private Pedido p;
    private Abonadora a;
    private GregorianCalendar calendario;

    public Recibo() {
        /**
         **Inicializamos las variables antes de carcar los componentes graficos
         *
         */
        this.d = new Datos();
        this.m = new Monto();
        this.p = new Pedido();
        this.a = new Abonadora();
        /**
         ** Inicializacoms componentes graficos
         *
         */
        initComponents();
        /**
         ** manejamos el calendario para obtener la fecha del día * maneja
         * variables enteras para year, month, day, * se pasa para variable
         * String fecha y se evalua la condicion de * los meses y los días del 1
         * al 9 y si es mayor que 10.
         *
         */
        this.calendario = new GregorianCalendar();
        int year = calendario.getTime().getYear() + 1900;
        int month = calendario.getTime().getMonth() + 1;
        int day = calendario.getTime().getDate();
        String fecha = year + "-" + month + "-" + day;
        if (month < 10 && day < 10) {
            fecha = year + "-0" + month + "-0" + day;
            this.jLabel22.setText(fecha);
        } else if (month < 10 && day > 10) {
            fecha = year + "-0" + month + "-" + day;
            this.jLabel22.setText(fecha);
        } else if (month > 10 && day < 10) {
            fecha = year + "-" + month + "-0" + day;
            this.jLabel22.setText(fecha);
        }
        //le indicamos que no sera maximizada o escalable
        this.setResizable(false);
        /**
         ** Preparamos los componentes graficos para ponerles detalles * tales
         * como background y la opacidad.
        *
         */
        this.jTextField1.setOpaque(true);
        this.jTextField2.setOpaque(true);
        this.jTextField3.setOpaque(true);
        this.jTextField4.setOpaque(true);
        this.jTextField5.setOpaque(true);
        this.jTextField6.setOpaque(true);
        this.jTextField7.setOpaque(true);
        this.jTextField8.setOpaque(true);
        this.jLabel1.setBackground(Color.white);
        this.jLabel1.setOpaque(true);
        this.jLabel2.setBackground(Color.white);
        this.jLabel2.setOpaque(true);
        this.jLabel3.setBackground(Color.white);
        this.jLabel3.setOpaque(true);
        this.jLabel4.setBackground(Color.white);
        this.jLabel4.setOpaque(true);
        this.jLabel5.setBackground(Color.white);
        this.jLabel5.setOpaque(true);
        this.jLabel6.setBackground(Color.white);
        this.jLabel6.setOpaque(true);
        this.jLabel7.setBackground(Color.white);
        this.jLabel7.setOpaque(true);
        this.jLabel8.setBackground(Color.white);
        this.jLabel8.setOpaque(true);
        this.jLabel9.setBackground(Color.white);
        this.jLabel9.setOpaque(true);
        this.jLabel10.setBackground(Color.white);
        this.jLabel10.setOpaque(true);
        this.jLabel12.setBackground(Color.white);
        this.jLabel12.setOpaque(true);
        this.jLabel13.setBackground(Color.white);
        this.jLabel13.setOpaque(true);
        this.jLabel14.setBackground(Color.white);
        this.jLabel14.setOpaque(true);
        this.jLabel15.setBackground(Color.white);
        this.jLabel15.setOpaque(true);
        this.jLabel16.setBackground(Color.white);
        this.jLabel16.setOpaque(true);
        this.jLabel17.setBackground(Color.white);
        this.jLabel17.setOpaque(true);
        this.jLabel18.setBackground(Color.white);
        this.jLabel18.setOpaque(true);
        this.jLabel19.setBackground(Color.white);
        this.jLabel19.setOpaque(true);
        this.jLabel23.setBackground(Color.white);
        this.jLabel23.setOpaque(true);
        this.jLabel24.setBackground(Color.white);
        this.jLabel24.setOpaque(true);
        this.jLabel25.setBackground(Color.white);
        this.jLabel25.setOpaque(true);
        this.jLabel26.setBackground(Color.white);
        this.jLabel26.setOpaque(true);
        JOptionPane.showMessageDialog(this, "Ingresa por favor el iva para el producto");
        jTextField9.setText(JOptionPane.showInputDialog("ingresa el iva por favor"));
        /*Espacio para los hilos*/
        Thread h1 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        multiplicadora();
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            /**
             ** metodo que se encarga de multiplicar la cantidad por el monto
             * unitario. * r=a*b
        *
             */
            private void multiplicadora() {
                if (!jTextField5.getText().isEmpty() && !jTextField7.getText().isEmpty()) {
                    int a = Integer.parseInt(jTextField5.getText());
                    double b = Float.parseFloat(jTextField7.getText());
                    jTextField8.setText(df.format((a * b)) + "");
                }
            }
        };
        h1.start();
        Thread h2 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        calculaSubtotal();
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            private void calculaSubtotal() {
                /**
                 ** En este apartado se maneja un pase de datos entre
                 * variables. * es la sumatoria de el valor contenido en n de i
                 * donde a=a+n(que hacer el metodo formal)
                *
                 */
                double temp = 0;
                double temp2 = 0;
                double iva = 0;
                int tempC = 0;
                if (tempC != jTable1.getRowCount()) {
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        temp = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                        temp2 = temp2 + temp;
                        jLabel13.setText(df.format(temp2) + "");
                    }
                    tempC = (int) jTable1.getRowCount();
                    if (!jTextField9.getText().isEmpty()) {
                        String stringTemp = "0." + jTextField9.getText();
                        iva = Double.parseDouble(stringTemp);
                        jLabel16.setText(df.format(temp2 * iva) + "");
                        jLabel10.setText(df.format(temp2 + (temp2 * iva)) + "");
                    }
                }

            }

        };
        h2.start();
        Thread h3 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        cero();
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, e);
                }

            }

            public void cero() {
                /**
                 ** Evalua la tabla si esta en ceros, y coloca los campos en
                 * 0.0
                *
                 */
                int a = jTable1.getRowCount();
                if (a == 0) {
                    jLabel13.setText("0.0");
                    jLabel16.setText("0.0");
                    jLabel10.setText("0.0");
                }
            }
        };
        h3.start();
        Thread h4 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {

                        evaluadora();

                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            /**
             ** calula el restante en caso de dejar antisipo.
            *
             */
            public void evaluadora() {
                double a = Double.parseDouble(jLabel10.getText());
                double b = 0;
                if (jTextField10.getText().isEmpty()) {
                    b = 0;
                } else {
                    b = Double.parseDouble(jTextField10.getText());
                }
                double temp = a - b;
                jLabel26.setText(df.format(temp) + "");
            }
        };
        h4.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        jMenuItem1.setText("Eliminar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setText("Seleccione iva: ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, 20));
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 50, 40));

        jButton2.setBackground(new java.awt.Color(114, 198, 59));
        jButton2.setText("Generar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 160, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("TOTAL");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 280, 130, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("0.0");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 300, 100, 20));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(887, 77, 131, -1));

        jButton1.setBackground(new java.awt.Color(114, 198, 59));
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, -1, -1));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Apellido materno");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(789, 80, -1, -1));
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 87, -1));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("subtotal");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 119, -1, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 77, 131, -1));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Apellido paterno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 80, -1, -1));

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("monto unitario");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 119, -1, -1));
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 116, 78, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Producto", "Monto unitario", "Subtotal", "Fecha"
            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 178, 857, 327));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 77, 168, -1));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 80, -1, -1));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("producto");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 118, -1, -1));
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 115, 192, -1));
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 115, 57, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 77, 169, -1));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("No. identificacion");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 80, -1, -1));

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("cantidad");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 118, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Subtotal");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 130, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("0.0");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 220, 100, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("$");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 220, 30, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("$");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 300, 30, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("0.0");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 100, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("$");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 260, 30, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("IVA");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 240, 130, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("FOLIO:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("2018-05-11");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 120, 30));

        jButton3.setBackground(new java.awt.Color(114, 198, 59));
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, 70, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("ANTICIPO");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, 100, -1));
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 340, 100, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Restante");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 90, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("$");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 400, 20, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("0.0");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 400, 80, 30));

        jButton4.setBackground(new java.awt.Color(114, 198, 59));
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 470, -1, -1));
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 440, 120, -1));

        jButton5.setBackground(new java.awt.Color(114, 198, 59));
        jButton5.setText("Cuenta");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 470, -1, -1));

        jButton6.setBackground(new java.awt.Color(114, 198, 59));
        jButton6.setText("Cerrar sesion");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 20, 130, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 1050, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("img/logo.png"));

        return retValue;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarProductoATabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int a = jTable1.getSelectedRow();
        if (a < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto a eliminar... ^_^");
        } else {
            model.removeRow(a);
            jTable1.setModel(model);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTextField9.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa por favor el iva para el producto");
            jTextField9.setText(JOptionPane.showInputDialog("ingresa el iva por favor"));
        } else {
            if (!jTextField1.getText().isEmpty() || !jTextField2.getText().isEmpty()
                    && !jTextField3.getText().isEmpty() && !jTextField4.getText().isEmpty()) {
                if (jTable1.getRowCount() > 0) {
                    // <editor-fold defaultstate="collapsed" desc="Generated Code">
                    this.d = new Datos(0, jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText());
                    if (!Transaccion.insertDatos(d)) {
                        int id_datos = Transaccion.selectSearchData();
                        this.jLabel20.setText(id_datos + "");
                        this.m = new Monto(0, id_datos, Double.parseDouble(jLabel13.getText()), Double.parseDouble(jLabel10.getText()));
                        if (!Transaccion.insertMonto(m)) {
                            int id_monto = Transaccion.selectSearchMonto();
                            for (int i = 0; i < jTable1.getRowCount(); i++) {
                                this.p = new Pedido(0, id_datos, Integer.parseInt(jTable1.getValueAt(i, 0).toString()),
                                        jTable1.getValueAt(i, 1).toString(), Double.parseDouble(jTable1.getValueAt(i, 2).toString()),
                                        Double.parseDouble(jTable1.getValueAt(i, 3).toString()), id_monto, jLabel22.getText());
                                Transaccion.insertPedido(p);
                                if (!jTextField10.getText().isEmpty()) {
                                    a = new Abonadora(0, id_datos, Double.parseDouble(jTextField10.getText()), jLabel22.getText());
                                    Transaccion.insertAbonadora(a);
                                }
                            }//end for
                            new Visor(jLabel20.getText(), jLabel22.getText(), jTextField1.getText(),
                                    jTextField2.getText(), jTextField3.getText(), jTextField4.getText(), (DefaultTableModel) jTable1.getModel(),
                                    jLabel13.getText(), jLabel16.getText(), jLabel10.getText(), jTextField10.getText(),
                                    jLabel26.getText()).setVisible(true);
                        }//end if 2
                    }//end if 1 */
                    // </editor-fold>
                } else {
                    JOptionPane.showMessageDialog(this, "No tiene productos en lista para generar venta");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Requiere de informacion para poder generar la venta");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cls();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int opc = Integer.parseInt(JOptionPane.showInputDialog("Ingresa por favor la siguiente opcion\n1.-No. Identificacion      2.-Folio\n3.-nombre"));
        //System.out.println("El dato fue: " + opc);
        this.d = Transaccion.searchDatos(jTextField11.getText(), opc);
        this.jLabel20.setText("" + d.getIdDatos());
        this.jTextField1.setText(d.getMatricula());
        this.jTextField2.setText(d.getNombre());
        this.jTextField3.setText(d.getApPat());
        this.jTextField4.setText(d.getApMat());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = jTable1.getRowCount(); i > 0; i--) {
            model.removeRow(jTable1.getRowCount() - 1);
        }
        jTable1.setModel(model);
        ArrayList<Pedido> temp = Transaccion.searchPedido(d.getIdDatos());
        String[] ped = new String[5];
        int i = 0;
        for (int j = 0; j < temp.size(); j++) {
            ped[0] = temp.get(i).getCantidad() + "";
            ped[1] = temp.get(i).getProducto();
            ped[2] = temp.get(i).getMontoUnit() + "";
            ped[3] = temp.get(i).getSubtotal() + "";
            ped[4] = temp.get(i).getFecha();
            i++;
            model.addRow(ped);
        }
        jTable1.setModel(model);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new StatusDeCuenta(jLabel20.getText(), jLabel13.getText(),
                jLabel16.getText(), jLabel10.getText(),
                (DefaultTableModel) jTable1.getModel(), jLabel22.getText()).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buscador() {
        jTextField11.getText();
    }

    private void cls() {
        jLabel20.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = jTable1.getRowCount(); i > 0; i--) {
            model.removeRow(jTable1.getRowCount() - 1);
        }
        jTable1.setModel(model);
    }

    private void agregarProductoATabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        Object[] fila = new Object[5];
        fila[0] = jTextField5.getText();
        fila[1] = jTextField6.getText();
        fila[2] = jTextField7.getText();
        fila[3] = df.format(Double.parseDouble(jTextField8.getText()));
        fila[4] = jLabel22.getText();
        modelo.addRow(fila);
        jTable1.setModel(modelo);
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
