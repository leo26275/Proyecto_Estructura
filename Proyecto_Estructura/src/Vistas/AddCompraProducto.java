/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.CompraProducto;
import Clases.Producto;
import Clases.Proveedor;
import Dao.Dao_CompraProducto;
import Dao.Dao_Producto;
import Dao.Dao_Proveedor;
import Estructuras.ArbolBBComproProducto;
import Estructuras.ListaDobleCompraProducto;
import Meet.Alertas;
import Modelos.Modelo_CompraProducto;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author leo_g
 */
public class AddCompraProducto extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDobleCompraProducto lista;
    CompraProducto compraproducto;
    Modelo_CompraProducto ml;
    Alertas aler;
    Dao_CompraProducto com;
    ArrayList<Producto> combo;
    ArrayList<String> pro;
    ArrayList<CompraProducto> refere;
    ArrayList<CompraProducto> refere2;
    ArrayList<String> prove;
    ArbolBBComproProducto albolbusquedad;
    Dao_Producto procc;
    String tipo;

    /**
     * Creates new form AddCompraProducto
     */
    public AddCompraProducto(java.awt.Frame parent, boolean modal, ArrayList<Producto> combo1, ArrayList<CompraProducto> referen, String tipo1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        aler = new Alertas();
        lista = new ListaDobleCompraProducto();
        compraproducto = new CompraProducto();
        pro = new ArrayList();
        procc = new Dao_Producto();
        prove = new ArrayList();
        refere = new ArrayList();
        com = new Dao_CompraProducto();
        albolbusquedad = new ArbolBBComproProducto();
        refere2 = referen;
        combo = combo1;
        tipo = tipo1;
        if (refere2 != null) {
            for (CompraProducto a : refere2) {
                cantidad.setValue(Integer.parseInt(String.valueOf(a.getCantidad())));
                fecha.setDate(Date.valueOf(a.getFechaadquisiscion()));
                porcentaje.setText(String.valueOf(a.getPorcentajeganancia()));
                precio.setText(String.valueOf(a.getPrecio()));
                Dao_Proveedor car = new Dao_Proveedor();
                int posicion = 0;
                int contador = -1;
                for (Proveedor e : car.listaProveedor()) {
                    contador++;
                    if (e.getNombre().equals(String.valueOf(a.getProveedor().getNombre()))) {
                        posicion = contador;
                    }
                }
                llenarcombopro();
                proveedor.setSelectedIndex(posicion + 1);
            }
            saveE.setText("Editar");
            refere2.clear();
        }
        if (combo != null) {
            for (Producto e : combo) {
                producto.setText(e.getNombre());
            }
        }
//         if(tipo.equals("Empleado")){
//              cantidad.setEnabled(false);
//                precio.setEnabled(false);
//                porcentaje.setEnabled(false);
//                fecha.setEnabled(false);
//                producto.setEnabled(false);
//                proveedor.setEnabled(false);
//                jButton1.setEnabled(false);
//                Editar.setEnabled(false);
//                Eliminar.setEnabled(false);
//                saveE.setEnabled(false);
//         }
        llenarTabla();
        llenarcombo();
        llenarcombopro();
    }

    public void llenarLista() {
        lista = new ListaDobleCompraProducto();
        for (CompraProducto w : com.listaCompraProducto()) {
            compraproducto = new CompraProducto(w.getIdcompra(), w.getCantidad(), w.getPrecio(), w.getFechaadquisiscion(), w.getPorcentajeganancia(), new Proveedor(w.getProveedor().getNombre()), new Producto(w.getProducto().getNombre()));
            lista.insertar(compraproducto);
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBBComproProducto();
        for (CompraProducto w : com.listaCompraProducto()) {
            compraproducto = new CompraProducto(w.getIdcompra(), w.getCantidad(), w.getPrecio(), w.getFechaadquisiscion(), w.getPorcentajeganancia(), new Proveedor(w.getProveedor().getNombre()), new Producto(w.getProducto().getNombre()));
            albolbusquedad.insertar(compraproducto);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_CompraProducto ml = new Modelo_CompraProducto(lista.toArrayAsc());
        tabla.setModel(ml);
    }

    public void eliminar() {
        llenarcombo();
        String dato1 = pro.get(filaseleccionada);
        if (filaseleccionada >= 0) {
            if (com.eliminarCompraProducto(dato1)) {
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
        double pre = 0;
        double preciomedio = 0;
        for (Producto e : clien.listaProducto()) {
            if (producto.getText().equals(e.getNombre())) {
                cli = String.valueOf(e.getIdproducto());
            }
        }
        if (buscarn.getText().isEmpty()) {
            llenarcombo();
        }
        pre = com.listarPrecio(cli);
        preciomedio = (pre + Double.parseDouble(precio.getText())) / 2;
        int nom = proveedor.getSelectedIndex() - 1;
        String car = (String) prove.get(nom);
        String dato1 = pro.get(filaseleccionada);
        int num1 = Integer.parseInt(com.actenerFisico(dato1));
        int cant = num1 + Integer.parseInt(String.valueOf(cantidad.getValue()));
        compraproducto = new CompraProducto(dato1, /*Integer.parseInt(String.valueOf(cantidad.getValue()))*/ cant, /*Double.parseDouble(precio.getText())*/ preciomedio, ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), Double.parseDouble(porcentaje.getText()), new Proveedor(car), new Producto(cli));
        if (filaseleccionada >= 0) {
            if (com.modificarCompraProducto(compraproducto)) {
                aler.info("Editar", "Dato Editado");
                llenarTabla();
                saveE.setText("Guardar");
                jButton1.setEnabled(true);
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);
                proveedor.setEnabled(true);
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
        String cli = "";
        boolean vv = false;
        if (combo != null) {
            for (Producto e : combo) {
                cli = String.valueOf(e.getIdproducto());
            }
        }
        try {
            for (CompraProducto w : com.listarID()) {
                if (w.getIdcompra().equals(cli)) {
                    JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "El producto ya esta comprado..");
                    a.setVisible(true);
                    vv = true;
                }
            }
        } catch (Exception e) {
        }
        int nom = proveedor.getSelectedIndex() - 1;
        String car = (String) prove.get(nom);
        if (vv == false) {
            compraproducto = new CompraProducto(Integer.parseInt(String.valueOf(cantidad.getValue())), Double.parseDouble(precio.getText()), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), Double.parseDouble(porcentaje.getText()), new Proveedor(car), new Producto(cli));
            if (com.insetarCompraProducto(compraproducto)) {
                aler.info("Registro", "Exito");
                cantidad.setValue(0);
                precio.setText("");
                porcentaje.setText("");
                fecha.setDate(null);
                producto.setText("");
                proveedor.setSelectedIndex(0);
            } else {
                JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
                a.setVisible(true);
            }
        }
        llenarTabla();
    }

    public void llenarcombo() {
        pro.clear();
        for (Object e : lista.toArrayAsc()) {
            pro.add(String.valueOf(((CompraProducto) e).getIdcompra()));
        }
    }

    public void llenarcombopro() {
        Dao_Proveedor car = new Dao_Proveedor();
        proveedor.addItem("-Seleccione Proveedor-");
        for (Proveedor e : car.listaProveedor()) {
            proveedor.addItem(e.getNombre());
            prove.add(String.valueOf(e.getIdproveedor()));
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
        Editar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        fecha = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        encabezado = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        producto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        porcentaje = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        proveedor = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barraBaja = new javax.swing.JPanel();
        guardar = new javax.swing.JPanel();
        saveE = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        buscarn = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        cantidad = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        Editar.setText("Recomprar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Editar);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        fecha.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 277, 25));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 180, 10));

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nuevo Producto");
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
        getContentPane().add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 747, 30));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Producto");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 150, -1));

        producto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        producto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        producto.setEnabled(false);
        getContentPane().add(producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 280, 24));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Cantidad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 150, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Porcentaje de Ganancia");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 177, -1));

        porcentaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        porcentaje.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        porcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeKeyTyped(evt);
            }
        });
        getContentPane().add(porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 277, 24));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Precio");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 150, 15));

        precio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        precio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioKeyTyped(evt);
            }
        });
        getContentPane().add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 280, 24));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Fecha Adquisición");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 150, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Proveedor");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 150, -1));

        proveedor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        proveedor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });
        getContentPane().add(proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 277, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 267, 747, -1));

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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBajaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
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
                        .addComponent(jButton2))
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 750, -1));

        cantidad.setModel(new javax.swing.SpinnerNumberModel(1, 0, 10000, 1));
        cantidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 127, 26));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-a-la-cesta-de-la-compra.png"))); // NOI18N
        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-usuario.png"))); // NOI18N
        jButton4.setText("Nuevo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, -1, 30));

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

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 30));

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
        if (saveE.getText().equals("Recomprar")) {
            refere.add(new CompraProducto(Integer.parseInt(String.valueOf(cantidad.getValue())), Double.parseDouble(precio.getText()), ((JTextField) fecha.getDateEditor().getUiComponent()).getText(), Double.parseDouble(porcentaje.getText()), new Proveedor(String.valueOf(proveedor.getSelectedItem())), new Producto(producto.getText())));
            viewProducto cli = new viewProducto(null, true, refere, tipo);
            this.dispose();
            cli.setVisible(true);
        } else {
            viewProducto cli = new viewProducto(null, true, null, tipo);
            this.dispose();
            cli.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        try {
            if ((Integer.parseInt(porcentaje.getText()) < 1 && Integer.parseInt(porcentaje.getText()) > 100) || producto.getText().isEmpty() || precio.getText().isEmpty() ||Integer.parseInt(precio.getText())<1||String.valueOf(cantidad.getValue()).equals("0") || fecha.getDate() == null || porcentaje.getText().isEmpty() || proveedor.getSelectedIndex() == 0) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
            } else {
                if (saveE.getText().equals("Recomprar")) {
                    Modificar();
                    cantidad.setValue(0);
                    precio.setText("");
                    porcentaje.setText("");
                    fecha.setDate(null);
                    producto.setText("");
                    proveedor.setSelectedIndex(0);
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (buscarn.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
                buscarn.requestFocus();
            } else {
                llenarArbol();
                String nom = ((CompraProducto) albolbusquedad.buscar(buscarn.getText()).getDato()).getFechaadquisiscion();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDobleCompraProducto();
                    for (CompraProducto w : com.listaCompraProducto()) {
                        if (w.getFechaadquisiscion().equals(nom)) {
                            compraproducto = new CompraProducto(w.getIdcompra(), w.getCantidad(), w.getPrecio(), w.getFechaadquisiscion(), w.getPorcentajeganancia(), new Proveedor(w.getProveedor().getNombre()), new Producto(w.getProducto().getNombre()));
                            lista.insertar(compraproducto);
                            pro.clear();
                            pro.add(String.valueOf(w.getIdcompra()));
                        }
                    }
                    Modelo_CompraProducto ml = new Modelo_CompraProducto(lista.toArrayAsc());
                    tabla.setModel(ml);
                    aler.exito("Busqueda", "El Dato se Encomtro con Exito..");
                }
            }
        } catch (Exception e) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
        saveE.setText("Recomprar");
        cantidad.setValue(0);
//        cantidad.setValue(Integer.parseInt(String.valueOf(tabla.getValueAt(filaseleccionada, 0))));
//        precio.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        Calendar calendar = Calendar.getInstance();
        String sfecha = String.valueOf(calendar.get(Calendar.YEAR));
        sfecha += "-";
        sfecha += String.valueOf(calendar.get(Calendar.MONTH) + 1);
        sfecha += "-";
        sfecha += String.valueOf(calendar.get(Calendar.DATE));
        fecha.setDate(Date.valueOf(sfecha));
//        fecha.setDate(Date.valueOf(String.valueOf(tabla.getValueAt(filaseleccionada, 2))));
//        porcentaje.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 3)));
        porcentaje.setText("0");
        Dao_Proveedor car = new Dao_Proveedor();
        int posicion = 0;
        int contador = -1;
        for (Proveedor e : car.listaProveedor()) {
            contador++;
            if (e.getNombre().equals(String.valueOf(tabla.getValueAt(filaseleccionada, 4)))) {
                posicion = contador;
            }
        }
        proveedor.setSelectedIndex(posicion + 1);
        producto.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 5)));
        jButton1.setEnabled(false);
        jButton3.setEnabled(false);
//        jButton4.setEnabled(false);
//        proveedor.setEnabled(false);
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void buscarnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnKeyReleased
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarTabla();
        }
        ArrayList<CompraProducto> nuevali = new ArrayList<CompraProducto>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDobleCompraProducto();
        for (CompraProducto w : nuevali) {
            compraproducto = new CompraProducto(w.getIdcompra(), w.getCantidad(), w.getPrecio(), w.getFechaadquisiscion(), w.getPorcentajeganancia(), new Proveedor(w.getProveedor().getNombre()), new Producto(w.getProducto().getNombre()));
            lista.insertar(compraproducto);
        }
        Modelo_CompraProducto ml = new Modelo_CompraProducto(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AddProducto p = new AddProducto(null, true, tipo);
        p.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AddProveedor p = new AddProveedor(null, true, tipo);
        p.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorActionPerformed

    private void porcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_porcentajeKeyTyped

    private void precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_precioKeyTyped
    public ArrayList<CompraProducto> buscar(String nom, ArrayList<CompraProducto> lis) {
        ArrayList<CompraProducto> nuevalis = new ArrayList<CompraProducto>();
        for (CompraProducto pd : lis) {
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
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JPanel barraBaja;
    private javax.swing.JTextField buscarn;
    private javax.swing.JSpinner cantidad;
    private javax.swing.JLabel encabezado;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JPanel guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField porcentaje;
    private javax.swing.JTextField precio;
    private javax.swing.JTextField producto;
    private javax.swing.JComboBox<String> proveedor;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
