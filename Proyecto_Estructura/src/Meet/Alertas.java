package Meet;

import rojerusan.RSNotifyAnimated;

public class Alertas {

    public Alertas() {
    }
    
    public void exito (String titulo, String tipo){
        new rojerusan.RSNotifyAnimated(titulo, tipo, 
                3, RSNotifyAnimated.PositionNotify.BottomRight, RSNotifyAnimated.AnimationNotify.BottomUp,
                RSNotifyAnimated.TypeNotify.SUCCESS).setVisible(true);
    }
    
    public void error (String titulo, String tipo){
        new rojerusan.RSNotifyAnimated(titulo, tipo, 
                3, RSNotifyAnimated.PositionNotify.BottomRight, RSNotifyAnimated.AnimationNotify.BottomUp,
                RSNotifyAnimated.TypeNotify.ERROR).setVisible(true);
    }
    
    public void warning (String titulo, String tipo){
        new rojerusan.RSNotifyAnimated(titulo, tipo, 
                3, RSNotifyAnimated.PositionNotify.BottomRight, RSNotifyAnimated.AnimationNotify.BottomUp,
                RSNotifyAnimated.TypeNotify.WARNING).setVisible(true);
    }
    
    public void info (String titulo, String tipo){
        new rojerusan.RSNotifyAnimated(titulo, tipo, 
                3, RSNotifyAnimated.PositionNotify.BottomRight, RSNotifyAnimated.AnimationNotify.BottomUp,
                RSNotifyAnimated.TypeNotify.INFORMATION).setVisible(true);
    }
    
}
