package operaciones;

import java.io.*;

public class Gestion {

    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;

    public Gestion() {
    }

    public byte[] AbrirImagen(File archivo) {
        byte[] bytesImg = new byte[1024 * 1024 * 100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImg);
        } catch (Exception e) {

        }
        return bytesImg;
    }
}
