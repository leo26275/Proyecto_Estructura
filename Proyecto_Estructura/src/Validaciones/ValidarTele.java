
package Validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarTele {
     private String telefono;
    private boolean correcto;

    public ValidarTele() {
    }

    public ValidarTele(String telefono) {
        this.telefono = telefono;
    }
    public boolean isCorrecto(){
        return validarTelefono();
    }

    public void setTelefono(String Dui) {
        this.telefono = Dui;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }
    
    public boolean validarTelefono(){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9]{4}-[0-9]{4})$");
        mat = pat.matcher(telefono);
        if(mat.find()){
            return true;
        }else {
            return false;
        }
    }
}
