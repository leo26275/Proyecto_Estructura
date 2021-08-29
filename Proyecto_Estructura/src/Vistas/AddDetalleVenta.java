/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cliente;
import Clases.Detalle_venta;
import Clases.Producto;
import Clases.Venta;
import Dao.Dao_Detalle_venta;
import Dao.Dao_Producto;
import Dao.Dao_Venta;
import Estructuras.ArbolBBDetalleVenta;
import Estructuras.ListaDobleDetalleVenta;
import Meet.Alertas;
import Modelos.Modelo_DetalleVenta;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import java.util.ArrayList;

/**
 *
 * @author leo_g
 */
public class AddDetalleVenta extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDobleDetalleVenta lista;
    Detalle_venta venta;
    Modelo_DetalleVenta ml;
    Alertas aler;
    Dao_Detalle_venta ven;
    ArrayList<Venta> combove;
    ArrayList<Producto> combo;
    ArrayList<String> clie;
    ArrayList<String> combovv;
    ArrayList<Detalle_venta> refere;
    ArrayList<Detalle_venta> refere2;
    ArbolBBDetalleVenta albolbusquedad;
    Dao_Producto pro;
    String clien;
    String clienven;
    String tipo1;
    double totalfac = 0;
    int cont = 0;

    /**
     * Creates new form AddDetalleVenta
     */
    public AddDetalleVenta(java.awt.Frame parent, boolean modal, ArrayList<Producto> combo1, ArrayList<Venta> combo2, ArrayList<Detalle_venta> referen, String clien1, String clienven1, String tipo2, int ccc1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        aler = new Alertas();
        lista = new ListaDobleDetalleVenta();
        venta = new Detalle_venta();
        ven = new Dao_Detalle_venta();
        clie = new ArrayList();
        refere = new ArrayList();
        combovv = new ArrayList();
        albolbusquedad = new ArbolBBDetalleVenta();
        pro = new Dao_Producto();
        refere2 = referen;
        combo = combo1;
        combove = combo2;
        clien = clien1;
        clienven = clienven1;
        tipo1 = tipo2;
        if (refere2 != null) {
            for (Detalle_venta a : refere2) {
                producto.setText(a.getId_producto().getNombre());
                ventadd.setText(a.getId_datos_venta().getNumerofactura());
                cantidad.setValue(Integer.parseInt(String.valueOf(a.getCantidad())));
            }
            saveE.setText("Editar");
            refere2.clear();
        }
        if (combo != null) {
            for (Producto e : combo) {
                producto.setText(e.getNombre());
            }
        }
        if (combove != null) {
            for (Venta e : combove) {
                ventadd.setText(e.getNumerofactura());
            }
        }
        if (!clien.equals("")) {
            producto.setText(clien);
        }
        if (!clienven.equals("")) {
            ventadd.setText(clienven);
        }
        if (ccc1 != 0) {
            cont = ccc1;
        }
        ref.setVisible(false);
        llenarTabla();
//         llenarcombo();
        jButton4.setVisible(false);
    }

    public void llenartotal() {
        lista = new ListaDobleDetalleVenta();
        int cant = 0;
        for (Detalle_venta w : ven.listarFactura()) {
            if (w.getId_datos_venta().getNumerofactura().equals(ventadd.getText())) {
                totalfac = totalfac + (w.getCompraproducto().getPrecio() + (w.getCompraproducto().getPrecio() * (w.getCompraproducto().getPorcentajeganancia() / 100)));
                cant = cant + w.getCompraproducto().getCantidad();
            }
        }
        total.setText(String.valueOf(totalfac * cant));
    }

    public void llenarLista() {
        lista = new ListaDobleDetalleVenta();
        for (Detalle_venta w : ven.listaDetalle_venta()) {
            if (ventadd.getText().isEmpty()) {
                venta = new Detalle_venta(w.getId_detalle(), new Producto(w.getId_producto().getNombre()), w.getCantidad(), new Venta(w.getId_datos_venta().getNumerofactura()), new Cliente(w.getCliente().getNombre()), w.getTipo());
                lista.insertar(venta);
            } else {
                if (w.getId_datos_venta().getNumerofactura().equals(ventadd.getText())) {
                    venta = new Detalle_venta(w.getId_detalle(), new Producto(w.getId_producto().getNombre()), w.getCantidad(), new Venta(w.getId_datos_venta().getNumerofactura()), new Cliente(w.getCliente().getNombre()), w.getTipo());
                    lista.insertar(venta);
                }
            }
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBBDetalleVenta();
        for (Detalle_venta w : ven.listaDetalle_venta()) {
            venta = new Detalle_venta(w.getId_detalle(), new Producto(w.getId_producto().getNombre()), w.getCantidad(), new Venta(w.getId_datos_venta().getNumerofactura()), new Cliente(w.getCliente().getNombre()), w.getTipo());
            albolbusquedad.insertar(venta);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_DetalleVenta ml = new Modelo_DetalleVenta(lista.toArrayAsc());
        tabla.setModel(ml);
        llenartotal();
    }

    public void eliminar() {
        if (buscarn.getText().isEmpty()) {
            llenarcombo();
        }
        String dato1 = combovv.get(filaseleccionada);
        if (filaseleccionada >= 0) {
            if (ven.eliminarDetalle_venta(dato1)) {
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
        Dao_Producto clien = new Dao_Producto();
        String cli = "";
        for (Producto e : clien.listaProducto()) {
            if (producto.getText().equals(e.getNombre())) {
                cli = String.valueOf(e.getIdproducto());
            }
        }
        if (buscarn.getText().isEmpty()) {
            llenarcombo();
        }
        String dato1 = combovv.get(filaseleccionada);
        venta = new Detalle_venta(dato1, new Producto(cli), Integer.parseInt(String.valueOf(cantidad.getValue())), new Venta(ventadd.getText()), String.valueOf(tipo.getSelectedItem()));
        if (filaseleccionada >= 0) {
            if (ven.modificarDetalle_venta(venta)) {
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
        Dao_Producto clien = new Dao_Producto();
        String cli = "";
        boolean vv = false;
        for (Producto e : clien.listaProducto()) {
            if (producto.getText().equals(e.getNombre())) {
                cli = String.valueOf(e.getIdproducto());
            }
        }
//        try {
//            for (Detalle_venta w : ven.listaID(ventadd.getText())) {
//                if (w.getTipo().equals(cli)) {
//                    JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "El producto ya asido comprado..");
//                    a.setVisible(true);
//                    producto.setText("");
//                    cantidad.setValue(0);
//                    tipo.setSelectedIndex(0);
//                    vv = true;
//                }
//            }
//        } catch (Exception e) {
//        }
        if (String.valueOf(tipo.getSelectedItem()).equals("Normal")) {
            String ho = pro.actenerIDcompra(cli);
            int num1 = Integer.parseInt(pro.actenerFisico(ho));
            if (num1 == 0) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "No hay productosde estos..");
                a.setVisible(true);
                jButton4.setVisible(true);
            } else {
                if (num1 < Integer.parseInt(String.valueOf(cantidad.getValue()))) {
                    JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "La Cantidad de Productos son insuficientes para la cantidad que desea octener..");
                    a.setVisible(true);
                    jButton4.setVisible(true);
                } else {
                    int cant = num1 - Integer.parseInt(String.valueOf(cantidad.getValue()));
                    if (pro.ActualizarInventario(String.valueOf(cant), ho)) {
                        aler.info("Registro", "Exito");
                    }
                    venta = new Detalle_venta(new Producto(cli), Integer.parseInt(String.valueOf(cantidad.getValue())), new Venta(ventadd.getText()), String.valueOf(tipo.getSelectedItem()));
                    if (ven.insetarDetalle_venta(venta)) {
                        aler.info("Registro", "Exito");
                        producto.setText("");
//            ventadd.setText("");
                        cantidad.setValue(0);
                        tipo.setSelectedIndex(0);
                        cont++;
                    } else {
                        JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
                        a.setVisible(true);
                    }
                }
            }
        } else {
            if (Integer.parseInt(String.valueOf(cantidad.getValue())) == 1) {
                String ho = pro.actenerIDcompra(cli);
                int num1 = Integer.parseInt(pro.actenerFisico(ho));
                if (num1 == 0) {
                    JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "No hay productosde estos..");
                    a.setVisible(true);
                    jButton4.setVisible(true);
                } else {
                    if (num1 < Integer.parseInt(String.valueOf(cantidad.getValue()))) {
                        JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "La Cantidad de Productos son insuficientes para la cantidad que desea octener..");
                        a.setVisible(true);
                        jButton4.setVisible(true);
                    } else {
                        int cant = num1 - Integer.parseInt(String.valueOf(cantidad.getValue()));
                        if (pro.ActualizarInventario(String.valueOf(cant), ho)) {
                            aler.info("Registro", "Exito");
                        }
                        venta = new Detalle_venta(new Producto(cli), Integer.parseInt(String.valueOf(cantidad.getValue())), new Venta(ventadd.getText()), String.valueOf(tipo.getSelectedItem()));
                        if (ven.insetarDetalle_venta(venta)) {
                            aler.info("Registro", "Exito");
                            producto.setText("");
//            ventadd.setText("");
                            cantidad.setValue(0);
                            tipo.setSelectedIndex(0);
                            cont++;
                        } else {
                            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
                            a.setVisible(true);
                        }
                    }
                }
            } else {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "Solo un producto puede comprarse a credito");
                a.setVisible(true);
            }
        }
        llenarTabla();
    }

    public void llenarcombo() {
        combovv.clear();
        llenarLista();
        for (Object e : lista.toArrayAsc()) {
            System.out.println(String.valueOf(((Detalle_venta) e).getId_detalle()));
            combovv.add(String.valueOf(((Detalle_venta) e).getId_detalle()));
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
        datocredito = new javax.swing.JMenuItem();
        jRadioButton1 = new javax.swing.JRadioButton();
        encabezado = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        producto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ventadd = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barraBaja = new javax.swing.JPanel();
        guardar = new javax.swing.JPanel();
        saveE = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        buscarn = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        cantidad = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        ref = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();

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

        datocredito.setText("Agregar Datos de  Credito");
        datocredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datocreditoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(datocredito);

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nueva Venta");
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

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Producto");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 150, -1));

        producto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        producto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        producto.setEnabled(false);
        getContentPane().add(producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 280, 24));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Venta");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 150, -1));

        ventadd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ventadd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        ventadd.setEnabled(false);
        getContentPane().add(ventadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 280, 24));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bolsa-de-la-compra.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Cantidad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 150, -1));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 740, -1));

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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/factura.png"))); // NOI18N
        jButton6.setText("Generar Factura");
        jButton6.setBorderPainted(false);
        jButton6.setRolloverEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jButton3)
                        .addComponent(jButton6))
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 770, -1));

        cantidad.setModel(new javax.swing.SpinnerNumberModel(1, 0, 10000, 1));
        cantidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 212, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Tipo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 150, -1));

        tipo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccione-", "Normal", "Credito" }));
        tipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });
        getContentPane().add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 277, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-a-la-cesta-de-la-compra.png"))); // NOI18N
        jButton5.setText("Nuevo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, -1, 30));

        ref.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/referir.png"))); // NOI18N
        ref.setText("Agregar Referencia");
        ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refActionPerformed(evt);
            }
        });
        getContentPane().add(ref, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 147, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png"))); // NOI18N
        jButton4.setText("Ir a Inventario Fisico");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 30, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 210, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Total");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, -1));

        total.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 50, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_encabezadoMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (saveE.getText().equals("Editar")) {
            refere.add(new Detalle_venta(new Producto(producto.getText()), Integer.parseInt(String.valueOf(cantidad.getValue())), new Venta(ventadd.getText())));
            viewProducto1 cli = new viewProducto1(null, true, refere, "", tipo1, cont);
            this.dispose();
            cli.setVisible(true);
        } else {
            viewProducto1 cli = new viewProducto1(null, true, null, ventadd.getText(), tipo1, cont);
            this.dispose();
            cli.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (saveE.getText().equals("Editar")) {
            refere.add(new Detalle_venta(new Producto(producto.getText()), Integer.parseInt(String.valueOf(cantidad.getValue())), new Venta(ventadd.getText())));
            viewVenta cli = new viewVenta(null, true, refere, "", tipo1);
            this.dispose();
            cli.setVisible(true);
        } else {
//            if (producto.getText().isEmpty()) {
//                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campo Cliente..");
//                a.setVisible(true);
//            } else {
            viewVenta cli = new viewVenta(null, true, null, producto.getText(), tipo1);
            this.dispose();
            cli.setVisible(true);
//            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
        if (filaseleccionada >= 0) {
            if (String.valueOf(tabla.getValueAt(filaseleccionada, 4)).equals("Normal")) {
                datocredito.setEnabled(false);
            } else {
                datocredito.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        try {
            if (producto.getText().isEmpty() || ventadd.getText().isEmpty() || String.valueOf(cantidad.getValue()).equals("0")) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos  11");
                a.setVisible(true);
            } else {
                if (saveE.getText().equals("Editar")) {
                    Modificar();
                    producto.setText("");
//                ventadd.setText("");
                    cantidad.setValue(0);
                    tipo.setSelectedIndex(0);
                    if (combo != null) {
                        combo.clear();
                    }
                    if (combove != null) {
                        combove.clear();
                    }
                } else {
                    if (tipo.getSelectedItem().equals("Credito")) {
                        String gg = ven.actenerIDClienteVenta(ventadd.getText());
                        if (ven.actenerIDClienteFerencia(gg).equals("")) {
                            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "El cliente tiene que tener una referencia para poder comprar al credito.");
                            a.setVisible(true);
                            ref.setVisible(true);
                        } else {
                            Ingresar();
                        }
                    } else {
                        if (tipo.getSelectedItem().equals("Normal")) {
                            Ingresar();
                        }
                    }
                    if (combo != null) {
                        combo.clear();
                    }
                    if (combove != null) {
                        combove.clear();
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_saveEMouseClicked

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked
        AddVenta a = new AddVenta(null, true, null, null, null, "", tipo1);
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_volverMouseClicked

    private void buscarnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnKeyReleased
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarTabla();
        }
        ArrayList<Detalle_venta> nuevali = new ArrayList<Detalle_venta>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDobleDetalleVenta();
        for (Detalle_venta w : nuevali) {
            venta = new Detalle_venta(w.getId_detalle(), new Producto(w.getId_producto().getNombre()), w.getCantidad(), new Venta(w.getId_datos_venta().getNumerofactura()), new Cliente(w.getCliente().getNombre()), w.getTipo());
            lista.insertar(venta);
        }
        Modelo_DetalleVenta ml = new Modelo_DetalleVenta(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased
    public ArrayList<Detalle_venta> buscar(String nom, ArrayList<Detalle_venta> lis) {
        ArrayList<Detalle_venta> nuevalis = new ArrayList<Detalle_venta>();
        for (Detalle_venta pd : lis) {
            int resultado = pd.getId_producto().getNombre().indexOf(nom);
            if (resultado != -1) {
                nuevalis.add(pd);
            }
        }
        return nuevalis;
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (buscarn.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
                buscarn.requestFocus();
            } else {
                llenarArbol();
                String nom = ((Detalle_venta) albolbusquedad.buscar(buscarn.getText()).getDato()).getId_producto().getNombre();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDobleDetalleVenta();
                    for (Detalle_venta w : ven.listaDetalle_venta()) {
                        if (w.getId_producto().getNombre().equals(nom)) {
                            venta = new Detalle_venta(w.getId_detalle(), new Producto(w.getId_producto().getNombre()), w.getCantidad(), new Venta(w.getId_datos_venta().getNumerofactura()), new Cliente(w.getCliente().getNombre()), w.getTipo());
                            lista.insertar(venta);
                            combovv.clear();
                            combovv.add(String.valueOf(w.getId_detalle()));
                        }
                    }
                    Modelo_DetalleVenta ml = new Modelo_DetalleVenta(lista.toArrayAsc());
                    tabla.setModel(ml);
                    aler.exito("Busqueda", "El Dato se Encomtro con Exito..");
                }
            }
        } catch (Exception e) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        saveE.setText("Editar");
        producto.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        ventadd.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 0)));
        cantidad.setValue(Integer.parseInt(String.valueOf(tabla.getValueAt(filaseleccionada, 2))));
        tipo.setSelectedItem(String.valueOf(tabla.getValueAt(filaseleccionada, 4)));
    }//GEN-LAST:event_editarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void datocreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datocreditoActionPerformed
        // TODO add your handling code here:
        llenarcombo();
        String dato1 = combovv.get(filaseleccionada);
        AddDatosCreditos a = new AddDatosCreditos(null, true, null, null, String.valueOf(tabla.getValueAt(filaseleccionada, 0)), String.valueOf(tabla.getValueAt(filaseleccionada, 1)), String.valueOf(tabla.getValueAt(filaseleccionada, 3)), dato1, tipo1);
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_datocreditoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        AddProducto p = new AddProducto(null, true, tipo1);
        p.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        FacturaNormal f = new FacturaNormal(null, true, ventadd.getText(), cont);
        f.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refActionPerformed
        // TODO add your handling code here:
        AddReferencia r = new AddReferencia(null, true, null, null, tipo1);
        r.setVisible(true);
    }//GEN-LAST:event_refActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        InventarioFisico f = new InventarioFisico(null, true, tipo1);
        f.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraBaja;
    private javax.swing.JTextField buscarn;
    private javax.swing.JSpinner cantidad;
    private javax.swing.JMenuItem datocredito;
    private javax.swing.JMenuItem editar;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel encabezado;
    private javax.swing.JPanel guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField producto;
    private javax.swing.JButton ref;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JLabel total;
    private javax.swing.JTextField ventadd;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
