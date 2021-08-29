/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Empleado;
import Clases.Usuario;
import Dao.Dao_Empleados;
import Dao.Dao_Usuario;
import Estructuras.ArbolBBUsuario;
import Estructuras.ListaDobleUsuario;
import Meet.Alertas;
import Modelos.Modelo_Usuario;
import MyOption.Confirmar;
import MyOption.JOptionPaneError;
import MyOption.JOptionPaneInfo;
import MyOption.JOptionPaneWornin;
import java.util.ArrayList;

/**
 *
 * @author leo_g
 */
public class AddUsuario extends javax.swing.JDialog {

    int x = 0, y = 0, filaseleccionada = 0;
    ListaDobleUsuario lista;
    Usuario usuario;
    Modelo_Usuario ml;
    Alertas aler;
    Dao_Usuario usu;
    ArrayList<String> combo;
    ArrayList<String> combo1;
    ArbolBBUsuario albolbusquedad;
     String tipo1;

    /**
     * Creates new form AddUsuario
     */
    public AddUsuario(java.awt.Frame parent, boolean modal, String tipo2) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        lista = new ListaDobleUsuario();
        usuario = new Usuario();
        aler = new Alertas();
        usu = new Dao_Usuario();
        combo = new ArrayList();
        combo1 = new ArrayList();
        albolbusquedad = new ArbolBBUsuario();
        contra.setEchoChar('\u2022');
        contra1.setEchoChar('\u2022');
        tipo1 = tipo2;
         if(tipo1.equals("Empleado")){
            nombre.setEnabled(false);
            contra.setEnabled(false);
            contra1.setEnabled(false);
            empleado.setEnabled(false);
            tipo.setEnabled(false);
            saveE2.setEnabled(false);
            editra.setEnabled(false);
            eliminra.setEnabled(false);
         }
        llenarTabla();
        llenarcombo();
    }

    public void llenarLista() {
        lista = new ListaDobleUsuario();
        for (Usuario w : usu.listaUsuario()) {
            usuario = new Usuario(w.getId_usuario(), w.getNombre(), w.getContraseña(), w.getTipo(), new Empleado(w.getId_empleado().getNombre()));
            lista.insertar(usuario);
        }
    }

    public void llenarArbol() {
        albolbusquedad = new ArbolBBUsuario();
        for (Usuario w : usu.listaUsuario()) {
            usuario = new Usuario(w.getId_usuario(), w.getNombre(), w.getContraseña(), w.getTipo(), new Empleado(w.getId_empleado().getNombre()));
            albolbusquedad.insertar(usuario);
        }
    }

    public void llenarTabla() {
        llenarLista();
        Modelo_Usuario ml = new Modelo_Usuario(lista.toArrayAsc());
        tabla.setModel(ml);
    }

    public void eliminar() {
        String dato1 = String.valueOf(tabla.getValueAt(filaseleccionada, 0));
        if (filaseleccionada >= 0) {
            if (usu.eliminarUsuario(dato1)) {
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
            llenarcombo1();
        }
        String dato1 = combo1.get(filaseleccionada);
        int nom = empleado.getSelectedIndex() - 1;
        String car = (String) combo.get(nom);
        usuario = new Usuario(Integer.parseInt(dato1), nombre.getText(), contra.getText(), String.valueOf(tipo.getSelectedItem()), new Empleado(car));
        if (filaseleccionada >= 0) {
            if (usu.modificarUsuario(usuario)) {
                aler.info("Editar", "Dato Editado");
                llenarTabla();
                saveE2.setText("Guardar");
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
        int nom = empleado.getSelectedIndex() - 1;
        String car = (String) combo.get(nom);
        usuario = new Usuario(nombre.getText(), contra.getText(), String.valueOf(tipo.getSelectedItem()), new Empleado(car));
        if (usu.insetarUsuario(usuario)) {
            aler.info("Registro", "Exito");
            nombre.setText("");
            contra.setText("");
            contra1.setText("");
            empleado.setSelectedIndex(0);
            tipo.setSelectedIndex(0);
        } else {
            JOptionPaneError a = new JOptionPaneError(null, true, "Registro", "Nose pudo registrar");
            a.setVisible(true);
        }
        llenarTabla();
    }

    public void llenarcombo() {
        Dao_Empleados car = new Dao_Empleados();
        empleado.addItem("-Seleccione Cargo-");
        for (Empleado e : car.listaEmpleado()) {
            empleado.addItem(e.getNombre());
            combo.add(String.valueOf(e.getIdempleado()));
//            if(empleado.getSelectedIndex() != 0){
//                nombre.setText(e.getCorreo());
//            }
        }

    }

    public void llenarcombo1() {
        combo1.clear();
        for (Object e : lista.toArrayAsc()) {
            combo1.add(String.valueOf(((Usuario) e).getId_usuario()));
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
        editra = new javax.swing.JMenuItem();
        eliminra = new javax.swing.JMenuItem();
        encabezado = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        empleado = new javax.swing.JComboBox<>();
        tipo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        barraBaja2 = new javax.swing.JPanel();
        guardar2 = new javax.swing.JPanel();
        saveE2 = new javax.swing.JLabel();
        salir = new javax.swing.JPanel();
        volver = new javax.swing.JLabel();
        buscarn = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        valicontra = new javax.swing.JLabel();
        verContra = new javax.swing.JToggleButton();
        contra = new javax.swing.JPasswordField();
        contra1 = new javax.swing.JPasswordField();
        verContravali = new javax.swing.JToggleButton();
        vali = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        editra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        editra.setText("Editar");
        editra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editraActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editra);

        eliminra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.png"))); // NOI18N
        eliminra.setText("Eliminar");
        eliminra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminraActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminra);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Nuevo Usuario");
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

        nombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        nombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        nombre.setEnabled(false);
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 270, 24));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Contraseña");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 117, 150, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Empleado");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 188, 150, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Tipo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 226, 150, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Ingrese de nuevo la Contraseña");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 155, 225, -1));

        empleado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        empleado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empleadoMouseClicked(evt);
            }
        });
        empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadoActionPerformed(evt);
            }
        });
        getContentPane().add(empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 185, 277, -1));

        tipo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccione-", "Empleado", "Administrador" }));
        tipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 223, 277, -1));

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 259, 760, -1));

        barraBaja2.setBackground(new java.awt.Color(236, 138, 32));

        guardar2.setBackground(new java.awt.Color(238, 238, 238));
        guardar2.setForeground(new java.awt.Color(238, 238, 238));

        saveE2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        saveE2.setForeground(new java.awt.Color(236, 138, 32));
        saveE2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveE2.setText("Guardar");
        saveE2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveE2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout guardar2Layout = new javax.swing.GroupLayout(guardar2);
        guardar2.setLayout(guardar2Layout);
        guardar2Layout.setHorizontalGroup(
            guardar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveE2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        guardar2Layout.setVerticalGroup(
            guardar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveE2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        salir.setBackground(new java.awt.Color(238, 238, 238));
        salir.setForeground(new java.awt.Color(238, 238, 238));

        volver.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        volver.setForeground(new java.awt.Color(236, 138, 32));
        volver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volver.setText("Cancelar");
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

        javax.swing.GroupLayout barraBaja2Layout = new javax.swing.GroupLayout(barraBaja2);
        barraBaja2.setLayout(barraBaja2Layout);
        barraBaja2Layout.setHorizontalGroup(
            barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBaja2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barraBaja2Layout.setVerticalGroup(
            barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBaja2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(barraBaja2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 528, 760, -1));

        valicontra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valicontra.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(valicontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 144, 150, 30));

        verContra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/verC.png"))); // NOI18N
        verContra.setBorder(null);
        verContra.setBorderPainted(false);
        verContra.setContentAreaFilled(false);
        verContra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verContra.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/verC2.png"))); // NOI18N
        verContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verContraActionPerformed(evt);
            }
        });
        getContentPane().add(verContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 114, -1, -1));

        contra.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 114, 275, 29));

        contra1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        getContentPane().add(contra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 149, 271, 25));

        verContravali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/verC.png"))); // NOI18N
        verContravali.setBorder(null);
        verContravali.setBorderPainted(false);
        verContravali.setContentAreaFilled(false);
        verContravali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verContravali.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/verC2.png"))); // NOI18N
        verContravali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verContravaliActionPerformed(evt);
            }
        });
        getContentPane().add(verContravali, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 144, -1, -1));

        vali.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vali.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(vali, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 79, 148, 24));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-usuario.png"))); // NOI18N
        jButton5.setText("Nuevo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, -1, -1));

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
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 62, 150, 10));

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

    private void saveE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveE2MouseClicked
        try {
            if (nombre.getText().isEmpty() || contra.getText().isEmpty() || contra1.getText().isEmpty() || empleado.getSelectedIndex() == 0 || tipo.getSelectedIndex() == 0) {
                JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Registro", "LLenar Campos");
                a.setVisible(true);
            } else {
                if (!contra1.getText().equals(contra.getText())) {
                    valicontra.setText("Dato incorrecto");
                    JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Formato", "No son iguales");
                    dd.setVisible(true);
                    contra1.requestFocus();
                } else {
                    if (saveE2.getText().equals("Editar")) {
                        Modificar();
                        nombre.setText("");
                        contra.setText("");
                        contra1.setText("");
                        empleado.setSelectedIndex(0);
                        tipo.setSelectedIndex(0);
                    } else {
                        if (usu.validarcorreo(nombre.getText())) {
                            vali.setText("Dato incorrecto");
                            JOptionPaneInfo dd = new JOptionPaneInfo(null, true, "Formato", "Empleado ya cuenta un un Usuario");
                            dd.setVisible(true);
                            nombre.requestFocus();
                        } else {
                            Ingresar();
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_saveE2MouseClicked

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
                String nom = ((Usuario) albolbusquedad.buscar(buscarn.getText()).getDato()).getNombre();
                if (nom.equals("")) {
                    JOptionPaneInfo a = new JOptionPaneInfo(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
                    a.setVisible(true);
                } else {
                    lista = new ListaDobleUsuario();
                    for (Usuario w : usu.listaUsuario()) {
                        if (w.getNombre().equals(nom)) {
                            usuario = new Usuario(w.getId_usuario(), w.getNombre(), w.getContraseña(), w.getTipo(), new Empleado(w.getId_empleado().getNombre()));
                            lista.insertar(usuario);
                            combo1.clear();
                            combo1.add(String.valueOf(w.getId_usuario()));
                        }
                    }
                    Modelo_Usuario ml = new Modelo_Usuario(lista.toArrayAsc());
                    tabla.setModel(ml);
                    aler.exito("Busqueda", "El Dato se Encomtro con Exito..");
                }
            }
        } catch (Exception e) {
            JOptionPaneWornin a = new JOptionPaneWornin(null, true, "Busqueda", "No se pudo encontarar el registro solo 'Similares'!!!...");
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void editraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editraActionPerformed
        // TODO add your handling code here:
        saveE2.setText("Editar");
        nombre.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 0)));
        contra.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        contra1.setText(String.valueOf(tabla.getValueAt(filaseleccionada, 1)));
        tipo.setSelectedItem(String.valueOf(tabla.getValueAt(filaseleccionada, 2)));
        Dao_Empleados car = new Dao_Empleados();
        int posicion = 0;
        int contador = -1;
        for (Empleado e : car.listaEmpleado()) {
            contador++;
            if (e.getNombre().equals(String.valueOf(tabla.getValueAt(filaseleccionada, 3)))) {
                posicion = contador;
            }
        }
        empleado.setSelectedIndex(posicion + 1);
    }//GEN-LAST:event_editraActionPerformed

    private void eliminraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminraActionPerformed
        // TODO add your handling code here:
        Confirmar cc = new Confirmar(null, true, "Estas seguro de Eliminar este reguistro");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            eliminar();
        }
    }//GEN-LAST:event_eliminraActionPerformed

    private void empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empleadoMouseClicked
        // TODO add your handling code here:
//        Dao_Empleados car = new Dao_Empleados();
//        for (Empleado e : car.listaEmpleado()) {
//            if(empleado.getSelectedIndex() != 0){
//                nombre.setText(e.getCorreo());
//            }
//        }    
    }//GEN-LAST:event_empleadoMouseClicked

    private void verContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verContraActionPerformed
        if (verContra.isSelected()) {
            contra.setEchoChar('\u0000');
        } else {
            contra.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_verContraActionPerformed

    private void verContravaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verContravaliActionPerformed
        // TODO add your handling code here:
        if (verContra.isSelected()) {
            contra1.setEchoChar('\u0000');
        } else {
            contra1.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_verContravaliActionPerformed

    private void empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadoActionPerformed
        // TODO add your handling code here:
        Dao_Empleados car = new Dao_Empleados();
        for (Empleado e : car.listaEmpleado()) {
            if (empleado.getSelectedIndex() != 0) {
                if (e.getNombre().equals(empleado.getSelectedItem())) {
                    nombre.setText(e.getCorreo());
                }
            }
        }
    }//GEN-LAST:event_empleadoActionPerformed

    private void buscarnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnKeyReleased
        // TODO add your handling code here:
        if (buscarn.getText().isEmpty()) {
            llenarTabla();
        }
        ArrayList<Usuario> nuevali = new ArrayList<Usuario>();
        nuevali = buscar(buscarn.getText(), lista.toArrayAsc());
        lista = new ListaDobleUsuario();
        for (Usuario w : nuevali) {
            usuario = new Usuario(w.getId_usuario(), w.getNombre(), w.getContraseña(), w.getTipo(), new Empleado(w.getId_empleado().getNombre()));
            lista.insertar(usuario);
        }
        Modelo_Usuario ml = new Modelo_Usuario(lista.toArrayAsc());
        tabla.setModel(ml);
    }//GEN-LAST:event_buscarnKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        AddEmpleado e = new AddEmpleado(null, true, tipo1);
        e.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed
    public ArrayList<Usuario> buscar(String nom, ArrayList<Usuario> lis) {
        ArrayList<Usuario> nuevalis = new ArrayList<Usuario>();
        for (Usuario pd : lis) {
            int resultado = pd.getNombre().indexOf(nom);
            if (resultado != -1) {
                nuevalis.add(pd);
            }
        }
        return nuevalis;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraBaja2;
    private javax.swing.JTextField buscarn;
    private javax.swing.JPasswordField contra;
    private javax.swing.JPasswordField contra1;
    private javax.swing.JMenuItem editra;
    private javax.swing.JMenuItem eliminra;
    private javax.swing.JComboBox<String> empleado;
    private javax.swing.JLabel encabezado;
    private javax.swing.JPanel guardar2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombre;
    private javax.swing.JPanel salir;
    private javax.swing.JLabel saveE2;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JLabel vali;
    private javax.swing.JLabel valicontra;
    private javax.swing.JToggleButton verContra;
    private javax.swing.JToggleButton verContravali;
    private javax.swing.JLabel volver;
    // End of variables declaration//GEN-END:variables
}
