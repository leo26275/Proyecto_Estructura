package Vistas;

import Dao.Dao_Usuario;
import Meet.Alertas;
import MyOption.*;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author leo_g
 */
public class Login extends javax.swing.JFrame {

    String tipo = "";
    int x = 0, y = 0;
    Alertas alerta;
    Dao_Usuario usu;

    public Login() {
        initComponents();
        alerta = new Alertas();
        pass.setEchoChar('\u2022');
        this.setLocationRelativeTo(null);
        user.requestFocus();
        usu = new Dao_Usuario();
        user.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encabezado = new javax.swing.JLabel();
        barraBaja = new javax.swing.JPanel();
        botonIniciar = new javax.swing.JPanel();
        iniciar = new javax.swing.JLabel();
        botonCancelar = new javax.swing.JPanel();
        salir = new javax.swing.JLabel();
        olvidado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        verContra = new javax.swing.JToggleButton();
        valiuser = new javax.swing.JLabel();
        valicontra = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Inicio de Sesi??n");
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
        getContentPane().add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 552, 30));

        barraBaja.setBackground(new java.awt.Color(236, 138, 32));

        botonIniciar.setBackground(new java.awt.Color(238, 238, 238));
        botonIniciar.setForeground(new java.awt.Color(238, 238, 238));

        iniciar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        iniciar.setForeground(new java.awt.Color(236, 138, 32));
        iniciar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iniciar.setText("Iniciar");
        iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout botonIniciarLayout = new javax.swing.GroupLayout(botonIniciar);
        botonIniciar.setLayout(botonIniciarLayout);
        botonIniciarLayout.setHorizontalGroup(
            botonIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        botonIniciarLayout.setVerticalGroup(
            botonIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        botonCancelar.setBackground(new java.awt.Color(238, 238, 238));
        botonCancelar.setForeground(new java.awt.Color(238, 238, 238));

        salir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(236, 138, 32));
        salir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salir.setText("Salir");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout botonCancelarLayout = new javax.swing.GroupLayout(botonCancelar);
        botonCancelar.setLayout(botonCancelarLayout);
        botonCancelarLayout.setHorizontalGroup(
            botonCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        botonCancelarLayout.setVerticalGroup(
            botonCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        olvidado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        olvidado.setForeground(new java.awt.Color(238, 238, 238));
        olvidado.setText("??Olvidaste tu Contrase??a?");
        olvidado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        olvidado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                olvidadoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout barraBajaLayout = new javax.swing.GroupLayout(barraBaja);
        barraBaja.setLayout(barraBajaLayout);
        barraBajaLayout.setHorizontalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(olvidado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barraBajaLayout.setVerticalGroup(
            barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonIniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBajaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(barraBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(olvidado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        getContentPane().add(barraBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 357, 552, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 552, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Usuario");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 228, 166, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Contrase??a");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 270, 166, -1));

        user.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        user.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        getContentPane().add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 226, 190, 24));

        pass.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        pass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 138, 32), 1, true));
        pass.setPreferredSize(new java.awt.Dimension(2, 18));
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        getContentPane().add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 268, 190, 24));

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
        getContentPane().add(verContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, -1, -1));

        valiuser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valiuser.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(valiuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 226, 143, 24));

        valicontra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valicontra.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(valicontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 268, 106, 25));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_encabezadoMousePressed

    private void verContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verContraActionPerformed
        if (verContra.isSelected()) {
            pass.setEchoChar('\u0000');
        } else {
            pass.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_verContraActionPerformed

    private void iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarMouseClicked
        if (!usu.validarcorreo(user.getText())) {
            valiuser.setText("Dato incorrecto");
            JOptionPaneError gg = new JOptionPaneError(null, true, "Error", "El 'USUARIO' es incorrecto..");
            gg.setVisible(true);
            user.requestFocus();
        } else {
            if (!usu.validarcontrasena(pass.getText())) {
                valicontra.setText("Dato incorrecto");
                valiuser.setText("Dato correcto");
                valiuser.setForeground(Color.black);

                JOptionPaneError gg = new JOptionPaneError(null, true, "Error", "El 'CONTRASE??A' es incorrecto..");
                gg.setVisible(true);
                pass.requestFocus();
            } else {
                Escritorio a = new Escritorio(usu.tipocuenta(user.getText()));
                this.dispose();
                a.setVisible(true);
            }
        }
    }//GEN-LAST:event_iniciarMouseClicked

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        Confirmar cc = new Confirmar(this, true, "Estas seguro que desea salir del sistema.");
        cc.setVisible(true);
        if (cc.dialog == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_salirMouseClicked

    private void olvidadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_olvidadoMouseClicked
        Recuperar re = new Recuperar(this, true);
        re.setVisible(true);
    }//GEN-LAST:event_olvidadoMouseClicked

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userActionPerformed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!usu.validarcorreo(user.getText())) {
                valiuser.setText("Dato incorrecto");
                JOptionPaneError gg = new JOptionPaneError(null, true, "Error", "El 'USUARIO' es incorrecto..");
                gg.setVisible(true);
                user.requestFocus();
            } else {
                if (!usu.validarcontrasena(pass.getText())) {
                    valicontra.setText("Dato incorrecto");
                    valiuser.setText("Dato correcto");
                    valiuser.setForeground(Color.black);

                    JOptionPaneError gg = new JOptionPaneError(null, true, "Error", "El 'CONTRASE??A' es incorrecto..");
                    gg.setVisible(true);
                    pass.requestFocus();
                } else {
                    Escritorio a = new Escritorio(usu.tipocuenta(user.getText()));
                    a.setVisible(true);
                    this.dispose();

                }
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_passKeyPressed

    /**
     * @param args the command line arguments
     */
    //--------------------------------------------------------------------------

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraBaja;
    private javax.swing.JPanel botonCancelar;
    private javax.swing.JPanel botonIniciar;
    private javax.swing.JLabel encabezado;
    private javax.swing.JLabel iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel olvidado;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel salir;
    private javax.swing.JTextField user;
    private javax.swing.JLabel valicontra;
    private javax.swing.JLabel valiuser;
    private javax.swing.JToggleButton verContra;
    // End of variables declaration//GEN-END:variables
}
