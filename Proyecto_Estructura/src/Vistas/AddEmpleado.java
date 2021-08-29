/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Empleado;
import Clases.Cargo;
import Dao.Dao_Cargo;
import Dao.Dao_Empleados;
import Estructuras.ArbolBBEmpleado;
import Estructuras.ListaDobleEmpleado;
import Meet.Alertas;
import Modelos.Modelo_Empleado;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import Validaciones.ValidaeNit;
import Validaciones.ValidarDui;
import Validaciones.ValidarTele;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author leo_g
 */
public class AddEmpleado extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDobleEmpleado lista;
    Empleado empleado;
    Modelo_Empleado ml;
    Alertas aler;
    Dao_Empleados emple;
    ValidarDui vdui;
    ValidarTele vtele;
    ValidaeNit vnit;
    ArrayList<String> combo;
    ArbolBBEmpleado albolbusquedad;
    String tipo;

    /**
     * Creates new form AddEmpleado
     */
    public AddEmpleado(java.awt.Frame parent, boolean modal, String tipo1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        lista = new ListaDobleEmpleado();
        empleado = new Empleado();
        aler = new Alertas();
        emple = new Dao_Empleados();
        vdui = new ValidarDui();
        vtele = new ValidarTele();
        vnit = new ValidaeNit();
        combo = new ArrayList();
        tipo = tipo1;
        albolbusquedad = new ArbolBBEmpleado();
        if (tipo.equals("Empleado")) {
            codigo.setEnabled(false);
            nombre.setEnabled(false);
            apellido.setEnabled(false);
            direccion.setEnabled(false);
            telefono.setEnabled(false);
            nit.setEnabled(false);
            nit.setEnabled(false);
            correo.setEnabled(false);
            salario.setEnabled(false);
            comboCargo.setEnabled(false);
            jButton1.setEnabled(false);
            Editar.setEnabled(false);
            eliminar.setEnabled(false);
            saveE.setEnabled(false);
        }
        llenarTabla();
        llenarcombo();
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
        lista = new ListaDobleEmpleado();
        for (Empleado w : emple.listaEmpleado()) {
            empleado = new Empleado(w.getIdempleado(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDui(), w.getNit(), w.getTelefono(), w.getCorreo(), w.getDireccion(), w.getSalario(), new Cargo(w.getId_cargo().getNombre()));
            lista.insertar(empleado);
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBBEmpleado();
        lista = new ListaDobleEmpleado();
        for (Empleado w : emple.listaEmpleado()) {
            empleado = new Empleado(w.getIdempleado(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDui(), w.getNit(), w.getTelefono(), w.getCorreo(), w.getDireccion(), w.getSalario(), new Cargo(w.getId_cargo().getNombre()));
            albolbusquedad.insertar(empleado);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_Empleado ml = new Modelo_Empleado(lista.toArrayAsc());
        tabla.setModel(ml);
    }

    public void eliminar() {
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        if (filaseleccionada >= 0) {
            if (emple.eliminarEmpleado(dato1)) {
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
        int nom = comboCargo.getSelectedIndex() - 1;
        String car = (String) combo.get(nom);
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        empleado = new Empleado(dato1, nombre.getText(), apellido.getText(), dui.getText(), nit.getText(), telefono.getText(), correo.getText(), direccion.getText(), Double.parseDouble(salario.getText()), new Cargo(car));
        if (filaseleccionada >= 0) {
            if (emple.modificarEmpleado(empleado)) {
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
        int nom = comboCargo.getSelectedIndex() - 1;
        String car = (String) combo.get(nom);
        empleado = new Empleado(codigo.getText(), nombre.getText(), apellido.getText(), dui.getText(), nit.getText(), telefono.getText(), correo.getText(), direccion.getText(), Double.parseDouble(salario.getText()), new Cargo(car));
        if (emple.insetarEmpleado(empleado)) {
            aler.info("Registro", "Exito");
            codigo.setText("");
            nombre.setText("");
            apellido.setText("");
            direccion.setText("");
            telefono.setText("");
            dui.setText("");
            nit.setText("");
            correo.setText("");
            salario.setText("");
            comboCargo.setSelectedIndex(0);
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
            a.setVisible(true);
        }
        llenarTabla();
    }

    public void llenarcombo() {
        Dao_Cargo car = new Dao_Cargo();
        comboCargo.addItem("-Seleccione Cargo-");
        for (Cargo e : car.listaCargo()) {
            comboCargo.addItem(e.getNombre());
            combo.add(String.valueOf(e.getIdcargo()));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboCargo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        salario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        encabezado = new javax.swing.JLabel();
        barraBaja = new javax.swing.JPanel();
        guardar = new javax.swing.JPanel();
        saveE = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        buscarn = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        validui = new javax.swing.JLabel();
        valinit = new javax.swing.JLabel();
        valitelefono = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        valicorreo = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        nit = new javax.swing.JFormattedTextField();
        telefono = new javax.swing.JFormattedTextField();
        dui = new javax.swing.JFormattedTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Editar);

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
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 100, 277, 24));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 102, 150, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Apellido");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 144, 150, -1));

        apellido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        apellido.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });
        getContentPane().add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 142, 277, 24));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("DUI");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 222, 150, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("NIT");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 257, 150, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Direccion");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 289, 150, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Correo");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 324, 150, -1));

        direccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        direccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 287, 277, 24));

        correo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        correo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                correoKeyTyped(evt);
            }
        });
        getContentPane().add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 322, 277, 24));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Telefono");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 150, -1));

        comboCargo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboCargo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(comboCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 277, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Cargo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 150, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Salario");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 424, 150, -1));

        salario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        salario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        salario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                salarioKeyTyped(evt);
            }
        });
        getContentPane().add(salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 419, 67, 24));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 740, 10));

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nuevo Empleado");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 760, -1));

        codigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        codigo.setEnabled(false);
        getContentPane().add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 184, 276, 25));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Codigo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 192, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 459, 740, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5", "Título 6", "Título 7", "Título 8", "Título 9", "Título 10"
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
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 760, 150));

        validui.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        validui.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(validui, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 198, 24));

        valinit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valinit.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(valinit, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 185, 26));

        valitelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valitelefono.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(valitelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 352, 183, 24));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nombre.png"))); // NOI18N
        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, -1, -1));

        valicorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valicorreo.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(valicorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 322, 198, 24));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente.png"))); // NOI18N
        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 385, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 150, 10));

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

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 30));

        nit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        try {
            nit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-######-###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nitKeyTyped(evt);
            }
        });
        getContentPane().add(nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 280, 30));

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
        getContentPane().add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 280, 30));

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
        getContentPane().add(dui, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 280, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correoKeyTyped
//        char c = evt.getKeyChar();
//        evt.setKeyChar(Character.toLowerCase(c));
    }//GEN-LAST:event_correoKeyTyped

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_encabezadoMousePressed

    private void saveEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveEMouseClicked
        if (codigo.getText().isEmpty() || nombre.getText().isEmpty() || apellido.getText().isEmpty() || direccion.getText().isEmpty() || telefono.getText().isEmpty() || dui.getText().isEmpty() || nit.getText().isEmpty() || correo.getText().isEmpty() || salario.getText().isEmpty() || Integer.parseInt(salario.getText())<10|| comboCargo.getSelectedIndex() == 0) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
            a.setVisible(true);
        } else {
            vdui.setDui(dui.getText());
            vtele.setTelefono(telefono.getText());
            vnit.setNit(nit.getText());
            if (!vdui.isCorrecto()) {
                validui.setText("Dato incorrecto");
                JOptionPaneInfo gg = new JOptionPaneInfo(null, true, "Formato", "00000000-0");
                gg.setVisible(true);
                dui.requestFocus();
            } else {
                if (!vtele.isCorrecto()) {
                    valitelefono.setText("Dato incorrecto");
                    JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Formato", "0000-0000");
                    dd.setVisible(true);
                    telefono.requestFocus();
                } else {
                    if (!vnit.isCorrecto()) {
                        valinit.setText("Dato incorrecto");
                        JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Formato", "0000-000000-000-0");
                        dd.setVisible(true);
                        nit.requestFocus();
                    } else {
                        if (saveE.getText().equals("Editar")) {
                            Modificar();
                            codigo.setText("");
                            nombre.setText("");
                            apellido.setText("");
                            direccion.setText("");
                            telefono.setText("");
                            nit.setText("");
                            dui.setText("");
                            correo.setText("");
                            salario.setText("");
                            comboCargo.setSelectedIndex(0);
                        } else {
                            if (emple.validardui(dui.getText())) {
                                validui.setText("Dato incorrecto");
                                JOptionPaneInfo gg = new JOptionPaneInfo(null, true, "Duplicado", "El 'DUI' ya Existe...");
                                gg.setVisible(true);
                                dui.requestFocus();
                            } else {
                                if (emple.validarnit(nit.getText())) {
                                    valinit.setText("Dato incorrecto");
                                    JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Duplicado", "El 'NIT' ya Existe...");
                                    dd.setVisible(true);
                                    nit.requestFocus();
                                } else {
                                    if (emple.validarcorreo(correo.getText())) {
                                        valicorreo.setText("Dato incorrecto");
                                        JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Duplicado", "El 'CORREO' ya Existe...");
                                        dd.setVisible(true);
                                        nit.requestFocus();
                                    } else {
                                        Ingresar();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_saveEMouseClicked

    private void volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volverMouseClicked

        this.dispose();
    }//GEN-LAST:event_volverMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (nombre.getText().isEmpty() || apellido.getText().isEmpty()) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos Nombre y Apellido");
            a.setVisible(true);
        } else {
            codigo.setText(codigo(nombre.getText() + " " + apellido.getText(), 0).toUpperCase());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        filaseleccionada = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
        saveE.setText("Editar");
        codigo.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 0)));
        nombre.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        apellido.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 2)));
        nit.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 3)));
        nit.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 4)));
        telefono.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 5)));
        correo.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 6)));
        direccion.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 7)));
        salario.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 8)));
        Dao_Cargo car = new Dao_Cargo();
        int posicion = 0;
        int contador = -1;
        for (Cargo e : car.listaCargo()) {
            contador++;
            if (e.getNombre().equals(String.valueOf(tabla.getValueAt(filaseleccionada, 9)))) {
                posicion = contador;
            }
        }
        comboCargo.setSelectedIndex(posicion + 1);
    }//GEN-LAST:event_EditarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (buscarn.getText().isEmpty()) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
                buscarn.requestFocus();
            } else {
                llenarArbol();
                String nom = ((Empleado) albolbusquedad.buscar(buscarn.getText()).getDato()).getNombre();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDobleEmpleado();
                    for (Empleado w : emple.listaEmpleado()) {
                        if (w.getNombre().equals(nom)) {
                            empleado = new Empleado(w.getIdempleado(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDui(), w.getNit(), w.getTelefono(), w.getCorreo(), w.getDireccion(), w.getSalario(), new Cargo(w.getId_cargo().getNombre()));
                            lista.insertar(empleado);
                        }
                    }
                    Modelo_Empleado ml = new Modelo_Empleado(lista.toArrayAsc());
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
        ArrayList<Empleado> nuevali = new ArrayList<Empleado>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDobleEmpleado();
        for (Empleado w : nuevali) {
            empleado = new Empleado(w.getIdempleado(), w.getCodigo(), w.getNombre(), w.getApellido(), w.getDui(), w.getNit(), w.getTelefono(), w.getCorreo(), w.getDireccion(), w.getSalario(), new Cargo(w.getId_cargo().getNombre()));
            lista.insertar(empleado);
        }
        Modelo_Empleado ml = new Modelo_Empleado(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AddCargo c = new AddCargo(null, true, tipo);
        c.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

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

    private void nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }
    }//GEN-LAST:event_nitKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void duiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_duiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_duiKeyTyped

    private void salarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salarioKeyTyped
       char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            JOptionPaneError a = new JOptionPaneError(null, true, "Error", "Digite solo Numeros");
            a.setVisible(true);
        }
    }//GEN-LAST:event_salarioKeyTyped
    public ArrayList<Empleado> buscar(String nom, ArrayList<Empleado> lis) {
        ArrayList<Empleado> nuevalis = new ArrayList<Empleado>();
        for (Empleado pd : lis) {
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
    private javax.swing.JMenuItem Editar;
    private javax.swing.JTextField apellido;
    private javax.swing.JPanel barraBaja;
    private javax.swing.JTextField buscarn;
    private javax.swing.JTextField codigo;
    private javax.swing.JComboBox<String> comboCargo;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField direccion;
    private javax.swing.JFormattedTextField dui;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel encabezado;
    private javax.swing.JPanel guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField nit;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField salario;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE;
    private javax.swing.JTable tabla;
    private javax.swing.JFormattedTextField telefono;
    private javax.swing.JLabel valicorreo;
    private javax.swing.JLabel validui;
    private javax.swing.JLabel valinit;
    private javax.swing.JLabel valitelefono;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
