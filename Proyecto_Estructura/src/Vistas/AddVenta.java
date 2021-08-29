/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cliente;
import Clases.Empleado;
import Clases.Venta;
import Dao.Dao_Clientes;
import Dao.Dao_Empleados;
import Dao.Dao_Venta;
import Estructuras.ArbolBBVenta;
import Estructuras.ListaDobleVenta;
import Meet.Alertas;
import Modelos.Modelo_Venta;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author leo_g
 */
public class AddVenta extends javax.swing.JDialog {
    
    int x = 0, y = 0, filaseleccionada = 0;
    ListaDobleVenta lista;
    Venta venta;
    Modelo_Venta ml;
    Alertas aler;
    Dao_Venta ven;
    ArrayList<Empleado> comboem;
    ArrayList<Cliente> combo;
    ArrayList<String> clie;
    ArrayList<Venta> refere;
    ArrayList<Venta> refere2;
    ArbolBBVenta albolbusquedad;
    String clien;
    String tipo1;

    /**
     * Creates new form AddVenta
     */
    public AddVenta(java.awt.Frame parent, boolean modal, ArrayList<Cliente> combo1, ArrayList<Empleado> combo2, ArrayList<Venta> referen, String clien1, String tipo2) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        aler = new Alertas();
        lista = new ListaDobleVenta();
        venta = new Venta();
        ven = new Dao_Venta();
        clie = new ArrayList();
        refere = new ArrayList();
        albolbusquedad = new ArbolBBVenta();
        refere2 = referen;
        combo = combo1;
        comboem = combo2;
        clien = clien1;
        tipo1 = tipo2;
        if (refere2 != null) {
            for (Venta a : refere2) {
                cliente.setText(a.getIdcliente().getNombre());
                empleado.setText(a.getIdempleado().getNombre());
                fecha.setDate(Date.valueOf(a.getFecha()));
                tipo.setSelectedItem(String.valueOf(a.getTipo()));
            }
            saveE.setText("Editar");
            refere2.clear();
        }
        if (combo != null) {
            for (Cliente e : combo) {
                cliente.setText(e.getNombre());
            }
        }
        if (comboem != null) {
            for (Empleado e : comboem) {
                empleado.setText(e.getNombre());
            }
        }
        if (!clien.equals("")) {
            cliente.setText(clien);
        }
        if (tipo1.equals("Empleado")) {
            jButton5.setEnabled(false);
        }
        llenarTabla();
        
    }
    
    public void llenarLista() {
        lista = new ListaDobleVenta();
//        try {
        for (Venta w : ven.listaVenta()) {
            venta = new Venta(w.getNumerofactura(), new Empleado(w.getIdempleado().getNombre()), new Cliente(w.getIdcliente().getNombre()), w.getFecha(), w.getTipo());
            lista.insertar(venta);
        }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
//        }
    }
    
    public void llenarArbol() {
        albolbusquedad = new ArbolBBVenta();
        for (Venta w : ven.listaVenta()) {
            venta = new Venta(w.getNumerofactura(), new Empleado(w.getIdempleado().getNombre()), new Cliente(w.getIdcliente().getNombre()), w.getFecha(), w.getTipo());
            albolbusquedad.insertar(venta);
        }
    }
    
    public void llenarTabla() {
        llenarLista();
        Modelo_Venta ml = new Modelo_Venta(lista.toArrayAsc());
        tabla.setModel(ml);
    }
    
    public void eliminar() {
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        if (filaseleccionada >= 0) {
            if (ven.eliminarVenta(dato1)) {
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
        Dao_Clientes clien = new Dao_Clientes();
        String cli = "";
        for (Cliente e : clien.listaCliente()) {
            if (cliente.getText().equals(e.getNombre())) {
                cli = String.valueOf(e.getIdcliente());
            }
        }
        Dao_Empleados emple = new Dao_Empleados();
        String em = "";
        for (Empleado a : emple.listaEmpleado()) {
            if (cliente.getText().equals(a.getNombre())) {
                em = String.valueOf(a.getIdempleado());
            }
        }
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        venta = new Venta(dato1, new Empleado(em), new Cliente(cli), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), String.valueOf(tipo.getSelectedItem()));
        if (filaseleccionada >= 0) {
            if (ven.modificarVenta(venta)) {
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
        Dao_Clientes clien = new Dao_Clientes();
        String cli = "";
        for (Cliente e : clien.listaCliente()) {
            if (cliente.getText().equals(e.getNombre())) {
                cli = String.valueOf(e.getIdcliente());
            }
        }
        Dao_Empleados emple = new Dao_Empleados();
        String em = "";
        for (Empleado a : emple.listaEmpleado()) {
            if (empleado.getText().equals(a.getNombre())) {
                em = String.valueOf(a.getIdempleado());
            }
        }
        venta = new Venta("dd", new Empleado(em), new Cliente(cli), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), String.valueOf(tipo.getSelectedItem()));
        if (ven.insetarVenta(venta)) {
            aler.info("Registro", "Exito");
            llenarTabla();
            tipo.setSelectedIndex(0);
            fecha.setDate(null);
            empleado.setText("");
            cliente.setText("");
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
            a.setVisible(true);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        editar = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        agregardetalles = new javax.swing.JMenuItem();
        encabezado = new javax.swing.JLabel();
        empleado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox<>();
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
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        detalle = new javax.swing.JLabel();

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
        eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        eliminar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        eliminar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminar);

        agregardetalles.setText("Agregar Detalles de venta");
        agregardetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregardetallesActionPerformed(evt);
            }
        });
        jPopupMenu1.add(agregardetalles);

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
        getContentPane().add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 750, 30));

        empleado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        empleado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        empleado.setEnabled(false);
        getContentPane().add(empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 124, 280, 24));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Empleado");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 126, 150, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 129, -1, 30));

        cliente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cliente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        cliente.setEnabled(false);
        getContentPane().add(cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 88, 280, 24));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Cliente");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 90, 150, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Fecha Compra");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 170, 150, -1));

        fecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        fecha.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 167, 277, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Tipo Pago");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 203, 150, -1));

        tipo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccione-", "Efectivo", "Targeta" }));
        tipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 200, 277, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5"
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 236, 748, -1));

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

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
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
                    .addGroup(barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 469, 750, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-usuario-nuevo.png"))); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dependiente-con-corbata.png"))); // NOI18N
        jButton5.setText("Nuevo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/referir.png"))); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, -1));

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
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 30, 30));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 230, 10));
        getContentPane().add(detalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 54, 110, 20));

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
        if (tipo1.equals("Empleado")) {
            if (saveE.getText().equals("Editar")) {
                refere.add(new Venta(String.valueOf(tabla.getValueAt(filaseleccionada, 0)), new Empleado(empleado.getText()), new Cliente(cliente.getText()), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), String.valueOf(tipo.getSelectedItem())));
                viewEmpleado1 cli = new viewEmpleado1(null, true, refere, "", tipo1);
                this.dispose();
                cli.setVisible(true);
            } else {
                if (cliente.getText().isEmpty()) {
                    JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campo Cliente..");
                    a.setVisible(true);
                } else {
                    viewEmpleado1 cli = new viewEmpleado1(null, true, null, cliente.getText(), tipo1);
                    this.dispose();
                    cli.setVisible(true);
                }
            }
        } else {
            if (saveE.getText().equals("Editar")) {
                refere.add(new Venta(String.valueOf(tabla.getValueAt(filaseleccionada, 0)), new Empleado(empleado.getText()), new Cliente(cliente.getText()), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), String.valueOf(tipo.getSelectedItem())));
                viewEmpleado cli = new viewEmpleado(null, true, refere, "", tipo1);
                this.dispose();
                cli.setVisible(true);
            } else {
                if (cliente.getText().isEmpty()) {
                    JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campo Cliente..");
                    a.setVisible(true);
                } else {
                    viewEmpleado cli = new viewEmpleado(null, true, null, cliente.getText(), tipo1);
                    this.dispose();
                    cli.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (saveE.getText().equals("Editar")) {
            refere.add(new Venta(String.valueOf(tabla.getValueAt(filaseleccionada, 0)), new Empleado(empleado.getText()), new Cliente(cliente.getText()), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), String.valueOf(tipo.getSelectedItem())));
            viewCliente1 cli = new viewCliente1(null, true, refere, tipo1);
            this.dispose();
            cli.setVisible(true);
        } else {
            viewCliente1 cli = new viewCliente1(null, true, null, tipo1);
            this.dispose();
            cli.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        try {
            if (empleado.getText().isEmpty() || cliente.getText().isEmpty() || tipo.getSelectedIndex() == 0 || fecha.getDate() == null) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
            } else {
                if (saveE.getText().equals("Editar")) {
                    Modificar();
                    tipo.setSelectedIndex(0);
                    fecha.setDate(null);
                    empleado.setText("");
                    cliente.setText("");
                } else {
                    Ingresar();
                }
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_saveEMouseClicked

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked
        
        this.dispose();
    }//GEN-LAST:event_volverMouseClicked

    private void buscarnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnKeyReleased
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarTabla();
        }
        ArrayList<Venta> nuevali = new ArrayList<Venta>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDobleVenta();
        for (Venta w : nuevali) {
            venta = new Venta(w.getNumerofactura(), new Empleado(w.getIdempleado().getNombre()), new Cliente(w.getIdcliente().getNombre()), w.getFecha(), w.getTipo());
            lista.insertar(venta);
        }
        Modelo_Venta ml = new Modelo_Venta(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (buscarn.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
                buscarn.requestFocus();
            } else {
                llenarArbol();
                String nom = ((Venta) albolbusquedad.buscar(buscarn.getText()).getDato()).getIdcliente().getNombre();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDobleVenta();
                    for (Venta w : ven.listaVenta()) {
                        if (w.getIdcliente().getNombre().equals(nom)) {
                            venta = new Venta(w.getNumerofactura(), new Empleado(w.getIdempleado().getNombre()), new Cliente(w.getIdcliente().getNombre()), w.getFecha(), w.getTipo());
                            lista.insertar(venta);
                        }
                    }
                    Modelo_Venta ml = new Modelo_Venta(lista.toArrayAsc());
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
        cliente.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 2)));
        empleado.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        fecha.setDate(Date.valueOf(String.valueOf(tabla.getValueAt(filaseleccionada, 3))));
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

    private void agregardetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregardetallesActionPerformed
        // TODO add your handling code here:
        AddDetalleVenta a = new AddDetalleVenta(null, true, null, null, null, "", String.valueOf(tabla.getValueAt(filaseleccionada, 0)), tipo1, 0);
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_agregardetallesActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AddCliente c = new AddCliente(null, true, tipo1);
        c.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        AddEmpleado e = new AddEmpleado(null, true, tipo1);
        e.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        AddReferencia r = new AddReferencia(null, true, null, null, tipo1);
        r.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        this.detalle.setText("Buscar Cliente");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        this.detalle.setText(""); 
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
       this.detalle.setText("Nuevo Cliente"); 
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
       this.detalle.setText(""); 
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
this.detalle.setText("");         // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
       this.detalle.setText("Nuevo Referencia"); 
    }//GEN-LAST:event_jButton6MouseEntered
    public ArrayList<Venta> buscar(String nom, ArrayList<Venta> lis) {
        ArrayList<Venta> nuevalis = new ArrayList<Venta>();
        for (Venta pd : lis) {
            int resultado = pd.getIdcliente().getNombre().indexOf(nom);
            if (resultado != -1) {
                nuevalis.add(pd);
            }
        }
        return nuevalis;
    }

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agregardetalles;
    private javax.swing.JPanel barraBaja;
    private javax.swing.JTextField buscarn;
    private javax.swing.JTextField cliente;
    private javax.swing.JLabel detalle;
    private javax.swing.JMenuItem editar;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JTextField empleado;
    private javax.swing.JLabel encabezado;
    private com.toedter.calendar.JDateChooser fecha;
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
