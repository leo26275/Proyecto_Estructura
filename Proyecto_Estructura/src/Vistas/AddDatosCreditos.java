/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cliente;
import Clases.Datos_credito;
import Clases.Detalle_venta;
import Clases.Producto;
import Clases.Venta;
import Dao.Dao_Datos_credito;
import Estructuras.ArbolBBDatosCreditos;
import Estructuras.ListaDobleDatosCredito;
import Meet.Alertas;
import Modelos.Modelo_DatosCredito;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author leo_g
 */
public class AddDatosCreditos extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDobleDatosCredito lista;
    Datos_credito datos;
    Modelo_DatosCredito ml;
    Alertas aler;
    Dao_Datos_credito da;
    ArbolBBDatosCreditos albolbusquedad;
    ArrayList<Venta> combo;
    ArrayList<String> clie;
    ArrayList<Datos_credito> refere;
    ArrayList<Datos_credito> refere2;
    ArrayList<String> combovv;
    String credi;
    String producto;
    String cliente;
    String iddetalle;
    String tipo;

    /**
     * Creates new form AddDatosCreditos
     */
    public AddDatosCreditos(java.awt.Frame parent, boolean modal, ArrayList<Venta> combo1, ArrayList<Datos_credito> referen, String credi1, String producto1, String cliente1, String iddetalle1, String tipo1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        aler = new Alertas();
        lista = new ListaDobleDatosCredito();
        datos = new Datos_credito();
        da = new Dao_Datos_credito();
        combovv = new ArrayList();
        refere = new ArrayList();
        albolbusquedad = new ArbolBBDatosCreditos();
        refere2 = referen;
        combo = combo1;
        credi = credi1;
        producto = producto1;
        cliente = cliente1;
        iddetalle = iddetalle1;
        tipo = tipo1;
        if (refere2 != null) {
            for (Datos_credito a : refere2) {
                ventadd.setText(a.getId_venta().getNumerofactura());
                fecha.setDate(Date.valueOf(a.getFecha_cance()));
                tiempo.setValue(Integer.parseInt(String.valueOf(a.getTiempo())));
                totales.setValue(Integer.parseInt(String.valueOf(a.getCuotas_totales())));
                paga.setText(String.valueOf(a.getCuotas_pagadas()));
            }
            saveE.setText("Editar");
            refere2.clear();
        }
        if (combo != null) {
            for (Venta e : combo) {
                ventadd.setText(e.getNumerofactura());
            }
        }
        if (!credi.equals("")) {
            ventadd.setText(credi);
        }
        llenarTabla();
        try {
            if (!String.valueOf(tabla.getValueAt(0, 0)).isEmpty()) {
                fecha.setEnabled(false);
                tiempo.setEnabled(false);
                totales.setEnabled(false);
            }
        } catch (Exception e) {
            fecha.setEnabled(true);
            tiempo.setEnabled(true);
            totales.setEnabled(true);
        }
        mes.setSelected(true);
        totales.setEnabled(false);
    }

    public void llenarLista() {
        lista = new ListaDobleDatosCredito();
        for (Datos_credito w : da.listaDatos_credito()) {
            if (ver.isSelected()) {
                datos = new Datos_credito(w.getId_datos(), w.getFecha_cance(), w.getTiempo(), w.getCuotas_totales(), w.getCuotas_pagadas(), new Producto(w.getProducto().getCodigo(), w.getProducto().getNombre()), new Detalle_venta(w.getDetalleventa().getCantidad(), w.getDetalleventa().getTipo(), w.getDetalleventa().getId_detalle()));
                lista.insertar(datos);
            } else {
                if (/*w.getId_venta().getNumerofactura().equals(ventadd.getText()) && (w.getCliente().getNombre()).equals(cliente) &&*/(w.getProducto().getNombre()).equals(producto) && /*w.getDetalleventa().getTipo().equals("Credito") && */ w.getDetalleventa().getId_detalle().equals(iddetalle)) {
                    datos = new Datos_credito(w.getId_datos(), w.getFecha_cance(), w.getTiempo(), w.getCuotas_totales(), w.getCuotas_pagadas(), new Producto(w.getProducto().getCodigo(), w.getProducto().getNombre()), new Detalle_venta(w.getDetalleventa().getCantidad(), w.getDetalleventa().getTipo(), w.getDetalleventa().getId_detalle()));
                    lista.insertar(datos);
                }
            }
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBBDatosCreditos();
        for (Datos_credito w : da.listaDatos_credito()) {
            datos = new Datos_credito(w.getId_datos(), w.getFecha_cance(), w.getTiempo(), w.getCuotas_totales(), w.getCuotas_pagadas(), new Producto(w.getProducto().getCodigo(), w.getProducto().getNombre()), new Detalle_venta(w.getDetalleventa().getCantidad(), w.getDetalleventa().getTipo(), w.getDetalleventa().getId_detalle()));
            albolbusquedad.insertar(datos);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_DatosCredito ml = new Modelo_DatosCredito(lista.toArrayAsc());
        tabla.setModel(ml);
    }

    public void eliminar() {
        if (buscarn.getText().isEmpty()) {
            llenarcombo();
        }
        String dato1 = combovv.get(filaseleccionada);
        if (filaseleccionada >= 0) {
            if (da.eliminarDatos_credito(dato1)) {
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
        if (buscarn.getText().isEmpty()) {
            llenarcombo();
        }
        String dato1 = combovv.get(filaseleccionada);
        datos = new Datos_credito(dato1, ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), Integer.parseInt(String.valueOf(tiempo.getValue())), new Venta(iddetalle), Integer.parseInt(String.valueOf(totales.getValue())), Integer.parseInt("0"));
        if (filaseleccionada >= 0) {
            if (da.modificarDatos_credito(datos)) {
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
        datos = new Datos_credito(((JTextField) fecha.getDateEditor().getUiComponent()).getText(), Integer.parseInt(String.valueOf(tiempo.getValue())), new Venta(iddetalle), Integer.parseInt(String.valueOf(totales.getValue())), Integer.parseInt("0"));
        if (da.insetarDatos_credito(datos)) {
            aler.info("Registro", "Exito");
            fecha.setDate(null);
//            ventadd.setText("");
            tiempo.setValue(0);
            totales.setValue(0);
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
            a.setVisible(true);
        }
        llenarTabla();
    }

    public void llenarcombo() {
        combovv.clear();
        for (Object e : lista.toArrayAsc()) {
            combovv.add(String.valueOf(((Datos_credito) e).getId_datos()));
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
        Eliminar = new javax.swing.JMenuItem();
        pagascuota = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        encabezado = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ventadd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        tiempo = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        totales = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        paga = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barraBaja = new javax.swing.JPanel();
        guardar = new javax.swing.JPanel();
        saveE = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        buscarn = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ver = new javax.swing.JRadioButton();
        ano = new javax.swing.JCheckBox();
        mes = new javax.swing.JCheckBox();
        ale = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editar);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        pagascuota.setText("Paga de Cuota");
        pagascuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagascuotaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(pagascuota);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nuevo Venta a Credito");
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
        getContentPane().add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 730, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Venta");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 150, -1));

        ventadd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ventadd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        ventadd.setEnabled(false);
        getContentPane().add(ventadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 351, 24));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Fecha");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 150, -1));

        fecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        fecha.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 351, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Cuotas totales");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 150, -1));

        tiempo.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));
        tiempo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        tiempo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tiempoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                tiempoAncestorMoved(evt);
            }
        });
        tiempo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tiempoStateChanged(evt);
            }
        });
        tiempo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tiempoMouseMoved(evt);
            }
        });
        tiempo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tiempoMouseClicked(evt);
            }
        });
        tiempo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tiempoKeyReleased(evt);
            }
        });
        getContentPane().add(tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 140, 20));

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Tiempo");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 150, -1));

        totales.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10000, 1));
        totales.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(totales, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 140, 20));

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("Cuotas Pagadas");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 150, -1));

        paga.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        paga.setText("0");
        paga.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        paga.setEnabled(false);
        getContentPane().add(paga, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 351, 24));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5", "Título 6"
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 750, -1));

        barraBaja.setBackground(new java.awt.Color(236, 138, 32));

        guardar.setBackground(new java.awt.Color(238, 238, 238));
        guardar.setForeground(new java.awt.Color(238, 238, 238));

        saveE.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        saveE.setForeground(new java.awt.Color(236, 138, 32));
        saveE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveE.setText("Guardar");
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

        buscarn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarnKeyReleased(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.setBorderPainted(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bolsa-de-la-compra.png"))); // NOI18N
        jButton1.setText("Ir a Venta");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barraBajaLayout.setVerticalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)
                        .addComponent(jButton1))
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 750, -1));

        ver.setText("Ver todas las ventas");
        ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verActionPerformed(evt);
            }
        });
        getContentPane().add(ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        buttonGroup1.add(ano);
        ano.setText("Años");
        ano.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        ano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anoActionPerformed(evt);
            }
        });
        getContentPane().add(ano, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        buttonGroup1.add(mes);
        mes.setText("Meses");
        mes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesActionPerformed(evt);
            }
        });
        getContentPane().add(mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, -1));

        buttonGroup1.add(ale);
        ale.setText("Aleatorio");
        ale.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        ale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aleActionPerformed(evt);
            }
        });
        getContentPane().add(ale, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20 (1).png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 30, 30));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setOpaque(true);
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 180, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
//          if (saveE.getText().equals("Editar")) {
//            refere.add(new Datos_credito(((JTextField) fecha.getDateEditor().getUiComponent()).getText(), Integer.parseInt(String.valueOf(tiempo.getValue())), new Venta(ventadd.getText()), Integer.parseInt(String.valueOf(totales.getValue())), Integer.parseInt(paga.getText())));
//            viewVenta1 cli = new viewVenta1(null, true, refere);
//            this.dispose();
//            cli.setVisible(true);
//        } else {
//            //            if (producto.getText().isEmpty()) {
//            //                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campo Cliente..");
//            //                a.setVisible(true);
//            //            } else {
//            viewVenta1 cli = new viewVenta1(null, true, null);
//            this.dispose();
//            cli.setVisible(true);
//            //            }
//        }
    }//GEN-LAST:event_encabezadoMousePressed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        try {
            if (String.valueOf(totales.getValue()).equals("0") || ventadd.getText().isEmpty() || String.valueOf(tiempo.getValue()).equals("0") || fecha.getDate() == null) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos  11");
                a.setVisible(true);
            } else {
                if (saveE.getText().equals("Editar")) {
                    Modificar();
                    fecha.setDate(null);
//            ventadd.setText("");
                    tiempo.setValue(0);
                    totales.setValue(0);
                    fecha.setEnabled(false);
                    tiempo.setEnabled(false);
                    totales.setEnabled(false);
                } else {
                    if (!ver.isSelected()) {
                        Ingresar();
                        fecha.setEnabled(false);
                        tiempo.setEnabled(false);
                        totales.setEnabled(false);
                    } else {
                        JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "No pude registrar");
                        a.setVisible(true);
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_saveEMouseClicked

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked
        AddDetalleVenta a = new AddDetalleVenta(null, true, null, null, null, "", ventadd.getText(), tipo,0);
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_volverMouseClicked

    private void buscarnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnKeyReleased
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarTabla();
        }
        ArrayList<Datos_credito> nuevali = new ArrayList<Datos_credito>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDobleDatosCredito();
        for (Datos_credito w : nuevali) {
            datos = new Datos_credito(w.getId_datos(), w.getFecha_cance(), w.getTiempo(), w.getCuotas_totales(), w.getCuotas_pagadas(), new Producto(w.getProducto().getCodigo(), w.getProducto().getNombre()), new Detalle_venta(w.getDetalleventa().getCantidad(), w.getDetalleventa().getTipo(), w.getDetalleventa().getId_detalle()));
            lista.insertar(datos);
        }
        Modelo_DatosCredito ml = new Modelo_DatosCredito(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            if (buscarn.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
                buscarn.requestFocus();
            } else {
                llenarArbol();
                String nom = ((Datos_credito) albolbusquedad.buscar(buscarn.getText()).getDato()).getProducto().getNombre();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDobleDatosCredito();
                    for (Datos_credito w : da.listaDatos_credito()) {
                        if (w.getProducto().getNombre().equals(nom)) {
                            datos = new Datos_credito(w.getId_datos(), w.getFecha_cance(), w.getTiempo(), w.getCuotas_totales(), w.getCuotas_pagadas(), new Producto(w.getProducto().getCodigo(), w.getProducto().getNombre()), new Detalle_venta(w.getDetalleventa().getCantidad(), w.getDetalleventa().getTipo(), w.getDetalleventa().getId_detalle()));
                            lista.insertar(datos);
                            combovv.clear();
                            combovv.add(String.valueOf(w.getId_datos()));
                        }
                    }
                    Modelo_DatosCredito ml = new Modelo_DatosCredito(lista.toArrayAsc());
                    tabla.setModel(ml);
                    aler.exito("Busqueda", "El Dato se Encomtro con Exito..");
                }
            }
        } catch (Exception e) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        saveE.setText("Editar");
        fecha.setDate(Date.valueOf(String.valueOf(tabla.getValueAt(filaseleccionada, 0))));
        tiempo.setValue(Integer.parseInt(String.valueOf(tabla.getValueAt(filaseleccionada, 1))));
        totales.setValue(Integer.parseInt(String.valueOf(tabla.getValueAt(filaseleccionada, 2))));
        paga.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 3)));
        // ventadd.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 2)));
        fecha.setEnabled(true);
        tiempo.setEnabled(true);
        totales.setEnabled(true);
    }//GEN-LAST:event_editarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void pagascuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagascuotaActionPerformed
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarcombo();
        }
        String dato = combovv.get(filaseleccionada);
        AddPagoCuotas cli = new AddPagoCuotas(null, true, "", dato, ventadd.getText(), producto, cliente, iddetalle, tipo);
        this.dispose();
        cli.setVisible(true);
    }//GEN-LAST:event_pagascuotaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddVenta v = new AddVenta(null, true, null, null, null, "", tipo);
        this.dispose();
        v.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActionPerformed
        // TODO add your handling code here:
        llenarTabla();
        fecha.setEnabled(false);
        tiempo.setEnabled(false);
        totales.setEnabled(false);
        if (ver.isSelected()) {
            pagascuota.setEnabled(false);
        } else {
            pagascuota.setEnabled(true);
        }
    }//GEN-LAST:event_verActionPerformed

    private void tiempoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiempoMouseClicked
        // TODO add your handling code here:
        if (mes.isSelected() == true) {
            totales.setValue(1);
        }
        if (ano.isSelected()) {
            int a = Integer.parseInt(String.valueOf(tiempo.getValue()));
            int r = a * 12;
            totales.setValue(r);
        }
        if (ale.isSelected()) {
            totales.setEnabled(true);
        }
    }//GEN-LAST:event_tiempoMouseClicked

    private void tiempoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tiempoKeyReleased
        // TODO add your handling code here:
        if (mes.isSelected() == true) {
            totales.setValue(1);
        }
        if (ano.isSelected()) {
            int a = Integer.parseInt(String.valueOf(tiempo.getValue()));
            int r = a * 12;
            totales.setValue(r);
        }
        if (ale.isSelected()) {
            totales.setEnabled(true);
        }
    }//GEN-LAST:event_tiempoKeyReleased

    private void tiempoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tiempoAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoAncestorAdded

    private void tiempoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tiempoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoMouseMoved

    private void mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesActionPerformed
        // TODO add your handling code here:
        if(mes.isSelected()){
            totales.setEnabled(false);
            totales.setValue(tiempo.getValue());
        }
    }//GEN-LAST:event_mesActionPerformed

    private void tiempoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tiempoAncestorMoved
        // TODO add your handling code
    }//GEN-LAST:event_tiempoAncestorMoved

    private void aleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aleActionPerformed
        // TODO add your handling code here:
        if(ale.isSelected()){
            totales.setEnabled(true);
        }
    }//GEN-LAST:event_aleActionPerformed

    private void anoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anoActionPerformed
        // TODO add your handling code here:
         if (ano.isSelected()) {
            int a = Integer.parseInt(String.valueOf(tiempo.getValue()));
            int r = a * 12;
            totales.setValue(r);
            totales.setEnabled(false);
        }
    }//GEN-LAST:event_anoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tiempoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tiempoStateChanged
        // TODO add your handling code here:
         if (mes.isSelected() == true) {
            totales.setValue(tiempo.getValue());
        }
        if (ano.isSelected()) {
            int a = Integer.parseInt(String.valueOf(tiempo.getValue()));
            int r = a * 12;
            totales.setValue(r);
        }
        if (ale.isSelected()) {
            totales.setEnabled(true);
        }
    }//GEN-LAST:event_tiempoStateChanged
    public ArrayList<Datos_credito> buscar(String nom, ArrayList<Datos_credito> lis) {
        ArrayList<Datos_credito> nuevalis = new ArrayList<Datos_credito>();
        for (Datos_credito pd : lis) {
            int resultado = pd.getProducto().getNombre().indexOf(nom);
            if (resultado != -1) {
                nuevalis.add(pd);
            }
        }
        return nuevalis;
    }

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JCheckBox ale;
    private javax.swing.JCheckBox ano;
    private javax.swing.JPanel barraBaja;
    private javax.swing.JTextField buscarn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem editar;
    private javax.swing.JLabel encabezado;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JPanel guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox mes;
    private javax.swing.JTextField paga;
    private javax.swing.JMenuItem pagascuota;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JSpinner tiempo;
    private javax.swing.JSpinner totales;
    private javax.swing.JTextField ventadd;
    private javax.swing.JRadioButton ver;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
