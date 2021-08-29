/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cliente;
import Dao.Dao_Clientes;
import Estructuras.ArbolBB;
import Estructuras.ListaDoubleCliente;
import Meet.Alertas;
import Modelos.Modelo_Cliente;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import Validaciones.ValidarDui;
import Validaciones.ValidarTele;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author leo_g
 */
public class AddCliente extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDoubleCliente lista;
    Cliente cliente;
    Modelo_Cliente ml;
    Alertas aler;
    Dao_Clientes cli;
    ValidarDui vdui;
    ValidarTele vtele;
    ArbolBB albolbusquedad;
    String tipo;

    /**
     * Creates new form AddEmpleado
     */
    public AddCliente(java.awt.Frame parent, boolean modal, String tipo1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        lista = new ListaDoubleCliente();
        cliente = new Cliente();
        aler = new Alertas();
        cli = new Dao_Clientes();
        vdui = new ValidarDui();
        vtele = new ValidarTele();
        albolbusquedad = new ArbolBB();
        tipo = tipo1;
//        if (tipo.equals("Empleado")) {
//            codigo.setEnabled(false);
//            nombre.setEnabled(false);
//            apellido.setEnabled(false);
//            direccion.setEnabled(false);
//            telefono.setEnabled(false);
//            dui.setEnabled(false);
//            jButton1.setEnabled(false);
//            modificar.setEnabled(false);
//            eliminar.setEnabled(false);
//            saveE.setEnabled(false);
//        }
        llenarTabla();
    }

    public static String codigo(String nom, int lon) {
        if (lon == nom.length() - 1) {
            return String.valueOf(nom.length()) + imprimir(generarAleatoriosNoRepetidos());
        } else if (lon == 0) {
            return nom.charAt(lon) + codigo(nom, lon + 1);
        } else {
            String letra = "";
            letra += nom.charAt(lon);
            if (letra.equals(" ")) {
                letra = "";
                return nom.charAt(lon + 1) + codigo(nom, lon + 1);
            }
            return codigo(nom, lon + 1);
        }
    }

    public static ArrayList<Integer> generarAleatoriosNoRepetidos() {
        ArrayList<Integer> respuesta = new ArrayList<>();
        respuesta.add(gen(respuesta));
        return respuesta;
    }

    public static int gen(ArrayList<Integer> a) {
        Random ra = new Random();
        int numero = ra.nextInt(9999);
        if (!a.contains(numero)) {
            return numero;
        } else {
            return gen(a);
        }
    }

    public static Integer imprimir(ArrayList<Integer> num) {
        for (int i = 0; i < num.size(); i++) {
            return num.get(i);
        }
        return 0;
    }

    public void llenarLista() {
        lista = new ListaDoubleCliente();
        for (Cliente w : cli.listaCliente()) {
            cliente = new Cliente(w.getIdcliente(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDireccion(), w.getTelefono(), w.getDui());
            lista.insertar(cliente);
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBB();
        for (Cliente w : cli.listaCliente()) {
            cliente = new Cliente(w.getIdcliente(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDireccion(), w.getTelefono(), w.getDui());
            albolbusquedad.insertar(cliente);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_Cliente ml = new Modelo_Cliente(lista.toArrayAsc());
        tabla.setModel(ml);
    }

    public void eliminar() {
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        if (filaseleccionada >= 0) {
            if (cli.eliminarCliente(dato1)) {
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
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        cliente = new Cliente(dato1, nombre.getText(), apellido.getText(), direccion.getText(), telefono.getText(), dui.getText());
        if (filaseleccionada >= 0) {
            if (cli.modificarCliente(cliente)) {
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
        cliente = new Cliente(codigo.getText(), nombre.getText(), apellido.getText(), direccion.getText(), telefono.getText(), dui.getText());
        if (cli.insetarCliente(cliente)) {
            aler.info("Registro", "Exito");
            codigo.setText("");
            nombre.setText("");
            apellido.setText("");
            direccion.setText("");
            telefono.setText("");
            dui.setText("");
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
            a.setVisible(true);
        }
        llenarTabla();
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
        modificar = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        encabezado = new javax.swing.JLabel();
        barraBaja = new javax.swing.JPanel();
        guardar = new javax.swing.JPanel();
        saveE = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        buscarn = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        validui = new javax.swing.JLabel();
        validui1 = new javax.swing.JLabel();
        validui2 = new javax.swing.JLabel();
        validui3 = new javax.swing.JLabel();
        valitelefono = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        telefono = new javax.swing.JFormattedTextField();
        dui = new javax.swing.JFormattedTextField();

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        modificar.setText("Editar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(modificar);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        nombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 277, 24));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 150, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Apellido");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 150, -1));

        apellido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        apellido.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });
        getContentPane().add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 277, 24));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("DUI");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 150, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Direccion");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 150, -1));

        direccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        direccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 277, 24));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Telefono");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 150, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 640, 10));

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nuevo Cliente");
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
        getContentPane().add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 690, 30));

        barraBaja.setBackground(new java.awt.Color(236, 138, 32));
        barraBaja.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));

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

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setText("Referencia");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(jButton2)
                        .addComponent(jButton8))
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 690, -1));

        codigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        codigo.setEnabled(false);
        getContentPane().add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 280, 23));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Codigo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo ", "Nombre ", "Apellido", "Direccion", "Telefono ", "DUI"
            }
        ));
        tabla.setComponentPopupMenu(jPopupMenu1);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 690, 170));

        validui.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        validui.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(validui, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 140, 30));

        validui1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        validui1.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(validui1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 140, 20));

        validui2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        validui2.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(validui2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 140, 20));

        validui3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        validui3.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(validui3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 140, 20));

        valitelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valitelefono.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(valitelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 140, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nombre.png"))); // NOI18N
        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20 (1).png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/icons8-close-window-20.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 30, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 30));

        telefono.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        try {
            telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });
        getContentPane().add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 280, 30));

        dui.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        try {
            dui.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                duiKeyTyped(evt);
            }
        });
        getContentPane().add(dui, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 280, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_encabezadoMousePressed

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        try {
            if (codigo.getText().isEmpty() || nombre.getText().isEmpty() || apellido.getText().isEmpty() || direccion.getText().isEmpty() || dui.getText().isEmpty() || telefono.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
            } else {
                vdui.setDui(dui.getText());
                vtele.setTelefono(telefono.getText());
                if (!vdui.isCorrecto()) {
                    validui.setText("Dato incorrecto");
                    JOptionPaneInfo gg = new JOptionPaneInfo(null, true, "Formato", "00000000-0");
                    gg.setVisible(true);
                    telefono.requestFocus();
                } else {
                    if (!vtele.isCorrecto()) {
                        valitelefono.setText("Dato incorrecto");
                        JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Formato", "0000-0000");
                        dd.setVisible(true);
                        telefono.requestFocus();
                    } else {
                        if (saveE.getText().equals("Editar")) {
                            Modificar();
                            codigo.setText("");
                            nombre.setText("");
                            apellido.setText("");
                            direccion.setText("");
                            dui.setText("");
                            telefono.setText("");
                        } else {
                            if (cli.validardui(telefono.getText())) {
                                validui.setText("Dato incorrecto");
                                JOptionPaneInfo gg = new JOptionPaneInfo(null, true, "Duplicado", "El 'DUI' ya Existe..");
                                gg.setVisible(true);
                                telefono.requestFocus();
                            } else {
                                Ingresar();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_saveEMouseClicked

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked

        this.dispose();
    }//GEN-LAST:event_volverMouseClicked

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
        saveE.setText("Editar");
        codigo.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 0)));
        nombre.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        apellido.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 2)));
        direccion.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 3)));
        telefono.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 4)));
        telefono.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 5)));
    }//GEN-LAST:event_modificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (nombre.getText().isEmpty() || apellido.getText().isEmpty()) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos Nombre y Apellido");
            a.setVisible(true);
        } else {
            codigo.setText(codigo(nombre.getText() + " " + apellido.getText(), 0).toUpperCase());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (buscarn.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
                buscarn.requestFocus();
            } else {
                llenarArbol();
                String nom = ((Cliente) albolbusquedad.buscar(buscarn.getText()).getDato()).getNombre();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDoubleCliente();
                    for (Cliente w : cli.listaCliente()) {
                        if (w.getNombre().equals(nom)) {
                            cliente = new Cliente(w.getIdcliente(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDireccion(), w.getTelefono(), w.getDui());
                            lista.insertar(cliente);
                        }
                    }
                    Modelo_Cliente ml = new Modelo_Cliente(lista.toArrayAsc());
                    tabla.setModel(ml);
                    aler.exito("Busqueda", "El Dato se Encomtro con Exito..");
                }
            }
        } catch (Exception e) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buscarnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnKeyReleased
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarTabla();
        }
        ArrayList<Cliente> nuevali = new ArrayList<Cliente>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDoubleCliente();
        for (Cliente w : nuevali) {
            cliente = new Cliente(w.getIdcliente(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDireccion(), w.getTelefono(), w.getDui());
            lista.insertar(cliente);
        }
        Modelo_Cliente ml = new Modelo_Cliente(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        AddReferencia r = new AddReferencia(null, true, null, null, tipo);
        r.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
       char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo letras");
            a.setVisible(true);
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo letras");
            a.setVisible(true);
        }
    }//GEN-LAST:event_apellidoKeyTyped

    private void duiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_duiKeyTyped
       char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }
    }//GEN-LAST:event_duiKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
         char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }
    }//GEN-LAST:event_telefonoKeyTyped
    public ArrayList<Cliente> buscar(String nom, ArrayList<Cliente> lis) {
        ArrayList<Cliente> nuevalis = new ArrayList<Cliente>();
        for (Cliente pd : lis) {
            int resultado = pd.getNombre().indexOf(nom);
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
    private javax.swing.JTextField apellido;
    private javax.swing.JPanel barraBaja;
    private javax.swing.JTextField buscarn;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField direccion;
    private javax.swing.JFormattedTextField dui;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel encabezado;
    private javax.swing.JPanel guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JFormattedTextField telefono;
    private javax.swing.JLabel validui;
    private javax.swing.JLabel validui1;
    private javax.swing.JLabel validui2;
    private javax.swing.JLabel validui3;
    private javax.swing.JLabel valitelefono;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
