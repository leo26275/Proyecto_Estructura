package Meet;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class Fondo implements Border {

    BufferedImage cargarImagen;

    public Fondo(String im) {
        try {
            URL ruta = new URL(getClass().getResource("/Imagenes/")+im);
            cargarImagen = ImageIO.read(ruta);
        } catch (Exception ex) {
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        //// centra la imagen respentando el tamaño de la misma
//        g.drawImage(cargarImagen, (x+(width - cargarImagen.getWidth())/2),
//                (y+(height -cargarImagen.getHeight())/2), null);
//        
        // adapta la imagen al tamaño de la pantalla
        g.drawImage(cargarImagen, 0, 0, width, height, null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

}
