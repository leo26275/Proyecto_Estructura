package CapturarPantalla;

import Meet.Alertas;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Capture {
    private Alertas  aler = new Alertas();
    private BufferedImage image;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen", "jpg");
    private JFileChooser filechooser = new JFileChooser();

    public void captureScreen(Point p, Dimension screenSize) {
        try {
            Rectangle screenRectangle = new Rectangle(p, screenSize);
            Robot robot = new Robot();
            image = robot.createScreenCapture(screenRectangle);
            Guardar_Foto();
        } catch (Exception ex) {

        }
    }
    private void Guardar_Foto(){
        String file=null;
        filechooser = new JFileChooser();
        filechooser.setFileFilter(filter);
        int result = filechooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            file = filechooser.getSelectedFile().toString();
            guardar_imagen(file + ".jpg");
        }
    }
    private void guardar_imagen(String f){
        try{
            ImageIO.write(image, "jpg", new File(f));
            aler.info("Captura", "La captura se guardo con exito");
        }catch (IOException ex) {
           aler.error("Error", "Error: No se pudo guaradar la imagen" + ex);
        }
    }
}
