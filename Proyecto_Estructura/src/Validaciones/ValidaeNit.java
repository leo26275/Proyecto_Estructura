package Validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaeNit {
    private String nit;
    private boolean correcto;

    public ValidaeNit() {
    }

    public ValidaeNit(String nit) {
        this.nit = nit;
    }
    public boolean isCorrecto(){
        return validarNit();
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }
    
    public boolean validarNit(){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9]{4}-[0-9]{6}-[0-9]{3}-[0-9]{1})$");
        mat = pat.matcher(nit);
        if(mat.find()){
            return true;
        }else {
            return false;
        }
    }
    
}
