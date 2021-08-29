/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Datos_credito;
import Clases.Detalle_venta;
import Clases.Pago;
import Clases.Recibo;
import Dao.Dao_Pago;
import Estructuras.ArbolBBPago;
import Estructuras.ListaDoblePago;
import Meet.Alertas;
import Modelos.Modelo_Pago;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTextField;

/**
 *
 * @author leo_g
 */
public class AddPagoCuotas extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDoblePago lista;
    Pago pago;
    Modelo_Pago ml;
    Alertas aler;
    Dao_Pago pa;
    ArrayList<Pago> combo;
    ArrayList<Datos_credito> refere1;
    String veri;
    ArbolBBPago albolbusquedad;
    ArrayList<String> combovv;
    String dato;
    String credi;
    String producto;
    String cliente;
    String iddetalle;
    int pagoxx;
    int pagoyy;
    String tipo;

    /**
     * Creates new form AddPagoCuotas
     */
    public AddPagoCuotas(java.awt.Frame parent, boolean modal, String veri1, String dato1, String credi1, String producto1, String cliente1, String iddetalle1, String tipo1) {
        super(parent, modal);
        initComponents();
        combovv = new ArrayList();
        pa = new Dao_Pago();
        pago = new Pago();
        this.setLocationRelativeTo(null);
        combo = new ArrayList();
        veri = veri1;
        dato = dato1;
        aler = new Alertas();
        credi = credi1;
        producto = producto1;
        cliente = cliente1;
        iddetalle = iddetalle1;
         tipo = tipo1;
        Calendar calendar = Calendar.getInstance();
        String sfecha = String.valueOf(calendar.get(Calendar.YEAR));
        sfecha += "-";
        sfecha += String.valueOf(calendar.get(Calendar.MONTH) + 1);
        sfecha += "-";
        sfecha += String.valueOf(calendar.get(Calendar.DATE));
        fecha.setDate(Date.valueOf(sfecha));
        llenarTabla();
    }

    public void llenarLista() {
        lista = new ListaDoblePago();
        int conta = 1;
        for (Pago w : pa.listaPago()) {
//            if (ver.isSelected()) {
//                pago = new Pago(w.getIdpago(), w.getFechapago(), new Datos_credito(w.getDatoscredito().getId_datos(), w.getDatoscredito().getCuotas_totales(), w.getDatoscredito().getCuotas_pagadas()));
//                lista.insertar(pago);
//            } else {
            if (w.getDatoscredito().getId_datos().equals(dato)) {
                pago = new Pago(w.getIdpago(), w.getFechapago(), new Datos_credito(w.getDatoscredito().getId_datos(), w.getDatoscredito().getCuotas_totales(), conta));
                lista.insertar(pago);
                conta++;
                pagoxx = w.getDatoscredito().getCuotas_pagadas();
                pagoyy = w.getDatoscredito().getCuotas_totales();
            }
//            }
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBBPago();
        for (Pago w : pa.listaPago()) {
            pago = new Pago(w.getIdpago(), w.getFechapago(), new Datos_credito(w.getDatoscredito().getId_datos(), w.getDatoscredito().getCuotas_totales(), w.getDatoscredito().getCuotas_pagadas()));
            albolbusquedad.insertar(pago);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_Pago ml = new Modelo_Pago(lista.toArrayAsc());
        tabla.setModel(ml);
    }

    public void eliminar() {
        int num1 = Integer.parseInt(pa.actenerpago(dato));
        int cant = num1 - 1;
        llenarcombo();
        String dato1 = combovv.get(filaseleccionada);
        if (filaseleccionada >= 0) {
            if (pa.eliminarPago(dato1) && pa.modificarDatos_credito_Pago(String.valueOf(cant), dato)) {
                aler.info("Eliminar", "Dato Eliminado");
                llenarTabla();
            } else {
                JOptionPaneError a = new JOptionPaneError(null, true, "Eliminar", "Nose pudo Eliminar");
                a.setVisible(true);
            }
        } else {
            JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Eliminar", "Selecione un dato");
            a.setVisible(true);
        }
    }

    public void Modificar() {
        llenarcombo();
        String dato1 = combovv.get(filaseleccionada);
        pago = new Pago(dato1, ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), new Datos_credito(dato));
        if (filaseleccionada >= 0) {
            if (pa.modificarPago(pago)) {
                aler.info("Editar", "Dato Editado");
                llenarTabla();
                saveE.setText("Guardar");
            } else {
                JOptionPaneError a = new JOptionPaneError(null, true, "Editar", "Nose pudo Modificar");
                a.setVisible(true);
            }
        } else {
            JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Editar", "Selecione un dato");
            a.setVisible(true);
        }
    }

    public void Ingresar() {
        int num1 = Integer.parseInt(pa.actenerpago(dato));
        int cant = num1 + 1;
        pago = new Pago(((JTextField) fecha.getDateEditor().getUiComponent()).getText(), new Datos_credito(dato));
        if (pa.insetarPago(pago) && pa.modificarDatos_credito_Pago(String.valueOf(cant), dato)) {
            aler.info("Registro", "Exito al pagar");
            Calendar calendar = Calendar.getInstance();
            String sfecha = String.valueOf(calendar.get(Calendar.YEAR));
            sfecha += "-";
            sfecha += String.valueOf(calendar.get(Calendar.MONTH) + 1);
            sfecha += "-";
            sfecha += String.valueOf(calendar.get(Calendar.DATE));
            fecha.setDate(Date.valueOf(sfecha));
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo Pagar");
            a.setVisible(true);
        }
//        llenarTabla();
    }

    public void Pagocuo() {
        int num = Integer.parseInt(veri);
        int cant = num + 1;
        if (pa.modificarDatos_credito_Pago(String.valueOf(cant), dato)) {
            aler.info("Registro", "Exito al Pagar");
            Calendar calendar = Calendar.getInstance();
            String sfecha = String.valueOf(calendar.get(Calendar.DATE));
            sfecha += "-";
            sfecha += String.valueOf(calendar.get(Calendar.MONTH) + 1);
            sfecha += "-";
            sfecha += String.valueOf(calendar.get(Calendar.YEAR));
//            fecha.setDate(Date.valueOf(String.valueOf(sfecha)));
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo Pagar");
            a.setVisible(true);
        }
//        llenarTabla();
    }

    public void llenarcombo() {
        combovv.clear();
        for (Object e : lista.toArrayAsc()) {
            combovv.add(String.valueOf(((Pago) e).getIdpago()));
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        editar = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        recibo = new javax.swing.JMenuItem();
        encabezado = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barraBaja = new javax.swing.JPanel();
        guardar = new javax.swing.JPanel();
        saveE = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editar);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminar);

        recibo.setText("Generar Recibo");
        recibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reciboActionPerformed(evt);
            }
        });
        jPopupMenu1.add(recibo);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nuevo Pago de Cuota");
        encabezado.setToolTipText("");
        encabezado.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        encabezado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                encabezadoMouseDragged(evt);
            }
        });
        encabezado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                encabezadoMousePressed(evt);
            }
        });
        getContentPane().add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 760, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Fecha");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 150, -1));

        fecha.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 299, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setComponentPopupMenu(jPopupMenu1);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 116, 740, -1));

        barraBaja.setBackground(new java.awt.Color(236, 138, 32));

        guardar.setBackground(new java.awt.Color(238, 238, 238));
        guardar.setForeground(new java.awt.Color(238, 238, 238));

        saveE.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        saveE.setForeground(new java.awt.Color(236, 138, 32));
        saveE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveE.setText("Pagar");
        saveE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveEMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout guardarLayout = new javax.swing.GroupLayout(guardar);
        guardar.setLayout(guardarLayout);
        guardarLayout.setHorizontalGroup(
            guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        guardarLayout.setVerticalGroup(
            guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveE, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        salir.setBackground(new java.awt.Color(238, 238, 238));
        salir.setForeground(new java.awt.Color(238, 238, 238));

        volver.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        volver.setForeground(new java.awt.Color(236, 138, 32));
        volver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volver.setText("Volver");
        volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout salirLayout = new javax.swing.GroupLayout(salir);
        salir.setLayout(salirLayout);
        salirLayout.setHorizontalGroup(
            salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        salirLayout.setVerticalGroup(
            salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addContainerGap(532, Short.MAX_VALUE)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barraBajaLayout.setVerticalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 345, 760, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20 (1).png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton7.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 30, 30));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 160, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_encabezadoMousePressed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        try {
            if (fecha.getDate() == null) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campo Fecha");
                a.setVisible(true);
            } else {
                if (pagoxx == 0) {
                    Ingresar();
                    llenarTabla();
                } else {
                    if (pagoxx == pagoyy) {
                        JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Imformacion", "Los Pagos estan Completos...");
                        a.setVisible(true);
                        fecha.setEnabled(false);
                    } else {
                        if (saveE.getText().equals("Editar")) {
                            Modificar();
                            llenarTabla();
                            Calendar calendar = Calendar.getInstance();
                            String sfecha = String.valueOf(calendar.get(Calendar.YEAR));
                            sfecha += "-";
                            sfecha += String.valueOf(calendar.get(Calendar.MONTH) + 1);
                            sfecha += "-";
                            sfecha += String.valueOf(calendar.get(Calendar.DATE));
                            fecha.setDate(Date.valueOf(sfecha));
                        } else {
                            Ingresar();
//                    Pagocuo();
                            llenarTabla();
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_saveEMouseClicked

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked
        AddDatosCreditos a = new AddDatosCreditos(null, true, null, null, credi, producto, cliente, iddetalle,tipo);
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_volverMouseClicked
    public ArrayList<Pago> buscar(String nom, ArrayList<Pago> lis) {
        ArrayList<Pago> nuevalis = new ArrayList<Pago>();
        for (Pago pd : lis) {
            int resultado = pd.getFechapago().indexOf(nom);
            if (resultado != -1) {
                nuevalis.add(pd);
            }
        }
        return nuevalis;
    }
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        saveE.setText("Editar");
        fecha.setDate(Date.valueOf(String.valueOf(tabla.getValueAt(filaseleccionada, 0))));
    }//GEN-LAST:event_editarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void reciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reciboActionPerformed
        // TODO add your handling code here:
        ArrayList<Recibo> reci = new ArrayList<Recibo>();
        double dd = Integer.parseInt(pa.actenerTiempo(dato));
        double con = 0;
        double cc = dd * 12;
        if (dd > pagoyy) {
            con = dd / pagoyy;
        }
        if (dd < pagoyy) {
            con = pagoyy / dd;
        }
        if(dd == pagoyy){
            con = 1;
        }
        if(cc == pagoyy){
            con = 1;
        }
        int rr = pagoyy - Integer.parseInt(String.valueOf(tabla.getValueAt(filaseleccionada, 2)));
        Recibo re = new Recibo(cliente, credi, String.valueOf(tabla.getValueAt(filaseleccionada, 2)), producto, String.valueOf(tabla.getValueAt(filaseleccionada, 0)), String.valueOf(con), String.valueOf(rr), pagoyy);
        reci.add(re);
        ReciboCredito r = new ReciboCredito(null, true, reci);
        r.setVisible(true);
    }//GEN-LAST:event_reciboActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraBaja;
    private javax.swing.JMenuItem editar;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel encabezado;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JPanel guardar;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem recibo;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
