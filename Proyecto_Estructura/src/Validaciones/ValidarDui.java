package Validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarDui {
    private String Dui;
    private boolean correcto;

    public ValidarDui() {
    }

    public ValidarDui(String Dui) {
        this.Dui = Dui;
    }
    public boolean isCorrecto(){
        return validarDui();
    }

    public void setDui(String Dui) {
        this.Dui = Dui;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }
    
    public boolean validarDui(){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9]{8}-[0-9]{1})$");
        mat = pat.matcher(Dui);
        if(mat.find()){
            return true;
        }else {
            return false;
        }
    }
    
}
