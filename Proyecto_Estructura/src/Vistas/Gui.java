/*
 * Autor: Juan Carlos Arcila Diaz
 * Localidad: Chiclayo-Peru
 * Email:carlos_ad_6@hotmail.com
 * Para Comunidad IncanatoHack.com
 */
package Vistas;

import MyOption.JOptionPaneError;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import objetos.*;
import operaciones.Disjktra;
import operaciones.Gestion;
import CapturarPantalla.frmPrincipal;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

//import Grafos.GrafoV;
/**
 *
 * @author leo_g
 */
public class Gui extends javax.swing.JFrame implements Printable {

    Grafo grafo = new Grafo();
    Nodo nodoInicio = null;
    Nodo nodoFin = null;
    int x = 0, y = 0;
    String nombre;
    String telefono;
    String tipo;
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    byte[] bytesImg;
    Gestion gestion = new Gestion();
//    GrafoV grafito;

    /**
     * Creamos el form
     */
    public Gui(String nombre1, String telefono1, String tipo1) {
        initComponents();
        this.setLocationRelativeTo(null);
        nombre = nombre1;
        tipo = tipo1;
        telefono = telefono1;
        if (!nombre.equals("")) {
            nombre2.setText(nombre);
        }
        if (!telefono.equals("")) {
            telefono2.setText(telefono);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        imgf = new javax.swing.JLabel();
        encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombre2 = new javax.swing.JLabel();
        telefono2 = new javax.swing.JLabel();
        barraBaja2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgf, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );

        encabezado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        encabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezado.setText("Ver Ruta mas corta de Referencia del Cliente");
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Telefono");

        nombre2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nombre2.setForeground(new java.awt.Color(102, 102, 102));

        telefono2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        telefono2.setForeground(new java.awt.Color(102, 102, 102));

        barraBaja2.setBackground(new java.awt.Color(236, 138, 32));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pantalla.png"))); // NOI18N
        jButton4.setText("Capturar Ruta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/flecha-a-la-izquierda-volver.png"))); // NOI18N
        jButton2.setText("Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/impresora-herramienta.png"))); // NOI18N
        jButton1.setText("Guardar e Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/google.png"))); // NOI18N
        jButton3.setText("Seleccionar Imagen");
        jButton3.setAutoscrolls(true);
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraBaja2Layout = new javax.swing.GroupLayout(barraBaja2);
        barraBaja2.setLayout(barraBaja2Layout);
        barraBaja2Layout.setHorizontalGroup(
            barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBaja2Layout.createSequentialGroup()
                .addContainerGap(469, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        barraBaja2Layout.setVerticalGroup(
            barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraBaja2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(barraBaja2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(encabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(barraBaja2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(telefono2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraBaja2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void dibujarGrafo() {
        jPanel1.paint(jPanel1.getGraphics());
        dibujarArista();
        dibujarNodos();
    }

    public void dibujarNodos() {
        ArrayList<Nodo> listaNodo = grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            nodo.getCirculo().dibujarCirculo(jPanel1.getGraphics());
        }
    }

    public void dibujarArista() {
        ArrayList<Nodo> listaNodo = grafo.getListaNodos();
        for (Nodo nodo : listaNodo) {
            ArrayList<Enlace> listaEnlace = nodo.getListaNodoAdyacente();
            if (listaEnlace != null) {
                for (Enlace enlace : listaEnlace) {
                    enlace.getArista().getLineaQuebrada().dibujarLineaQuebrada(jPanel1.getGraphics());
                }
            }
        }
    }

    private int ingresarPeso() {
        String peso = JOptionPane.showInputDialog("Digite la distancia entre puntos en METROS");
        int intPeso = 0;
        try {
            intPeso = Integer.parseInt(peso);
        } catch (Exception ex) {
            intPeso = ingresarPeso();
        }
        return intPeso;
    }

    private void eliminarNodo(int x, int y) {
        if (grafo.buscarNodo(x, y) != null) {
            Nodo temp = grafo.buscarNodo(x, y);
            grafo.eliminarAristasEntrante(temp);
            if (grafo.eliminarNodo(temp)) {
                JOptionPane.showMessageDialog(null, "Eliminado");
                dibujarGrafo();
            }

        }
    }

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        int x = evt.getX();
        int y = evt.getY();
        if (evt.isMetaDown()) {
            if (grafo.buscarNodo(x, y) != null) {
                if (nodoInicio == null) {
                    grafo.reiniciarGrafoParaDisjktra();
                    grafo.reiniciarColores();
                    nodoInicio = grafo.buscarNodo(x, y);
                    nodoInicio.getCirculo().setColor(Color.red);
                } else {
                    nodoFin = grafo.buscarNodo(x, y);
                    Disjktra disjktra = new Disjktra(grafo);
                    disjktra.ejecutar(nodoInicio);
                    disjktra.marcarRutaCorta(nodoFin, Color.red);
                    nodoInicio = null;
                    nodoFin = null;
                }
            }

        } else {
            if (grafo.buscarNodo(x, y) != null) {
                if (nodoInicio == null) {
                    nodoInicio = grafo.buscarNodo(x, y);
                    nodoInicio.getCirculo().setColor(Color.red);
                } else {
                    nodoFin = grafo.buscarNodo(x, y);
                    crearArista();

                    nodoInicio.getCirculo().setColor(Color.yellow);

                    nodoInicio = null;
                    nodoFin = null;
                }
            } else {
                crearNodo(x, y);
            }
        }
        dibujarGrafo();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void encabezadoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_encabezadoMouseDragged

    private void encabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_encabezadoMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_encabezadoMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AddReferencia r = new AddReferencia(null, true, null, null, tipo);
        this.dispose();
        r.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            PrinterJob gap = PrinterJob.getPrinterJob();
            gap.setPrintable(this);
            boolean top = gap.printDialog();
            if (top) {
                gap.print();
            }
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Error" + e, "mansaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (seleccionado.showDialog(null, "ABRIR ARCHIVO") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png") || archivo.getName().endsWith("gif")) {
                bytesImg = gestion.AbrirImagen(archivo);
                imgf.setIcon(new ImageIcon(bytesImg));
                System.out.println(archivo.toString());
            } else {
                JOptionPaneError a = new JOptionPaneError(null, true, "Archivo", "Por favor seleccione un archivo de tipo Imagen.");
                a.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            frmPrincipal f = new frmPrincipal();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
            int x = (int) rect.getMaxX() - f.getWidth()-5;
            int y = (int) rect.getMaxY() - f.getHeight()-30;
            f.setLocation(x, y);
            f.setVisible(true);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void crearArista() {
        int intPeso = ingresarPeso();
        Arista arista = new Arista();
        arista.setPeso(intPeso);
        Coordenadas c = new Coordenadas(100000, 100000);
        c.addCoordenada(nodoInicio.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro() / 2), nodoInicio.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro() / 2));
        c.addCoordenada(nodoFin.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro() / 2), nodoFin.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro() / 2));
        LineaQuebrada lq = new LineaQuebrada(c);
        arista.setLineaQuebrada(lq);
        grafo.crearEnlacesNoDirigido(nodoInicio, nodoFin, arista);

    }

    private void crearNodo(int x, int y) {
        Coordenadas c = new Coordenadas(100000, 100000, x, y);
        String dato = JOptionPane.showInputDialog("Digite un dato o Nombre de la coordenada");
        if (dato != null) {
            dato = dato.toUpperCase();
            Nodo nodo = new Nodo(dato, c);
            nodo.getCirculo().setDiametro(12);
            nodo.getCirculo().setEtiqueta(nodo.getDato() + "");
            if (grafo.adjuntarNodo(nodo)) {
                nodo.getCirculo().dibujarCirculo(jPanel1.getGraphics());
            } else {
            }
            nodoInicio = null;
            nodoFin = null;
        }
    }

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraBaja2;
    private javax.swing.JLabel encabezado;
    private javax.swing.JLabel imgf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nombre2;
    private javax.swing.JLabel telefono2;
    // End of variables declaration//GEN-END:variables
    @Override
    public int print(Graphics graf, PageFormat pagfor, int index) throws PrinterException {
        if (index > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D hub = (Graphics2D) graf;
        hub.translate(pagfor.getImageableX() + 40, pagfor.getImageableY() + 40);
        hub.scale(1.0, 1.0);
        jPanel1.paintAll(graf);
        return PAGE_EXISTS;
    }
}
