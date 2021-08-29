package CapturarPantalla;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainPanel extends JPanel implements MouseMotionListener, MouseListener {

    private Point start_drag;
    private Point start_loc;
    private JFrame padre;
    private BufferedImage image;

    public PainPanel(JFrame padre) {
        this.padre = padre;
        initComponents();
    }

    private void initComponents() {
        this.setVisible(true);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Point pos = this.getLocationOnScreen();
        Point offset = new Point(-pos.x, -pos.y);
        if (this.image != null) {
            g.drawImage(image, offset.x, offset.y, null);
        }
        g2.setStroke(new BasicStroke(6f));
        g2.setColor(new Color(255, 0, 0));
        g2.draw(new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight()));
    }

    public void ActualizarImagen() {
        try {
            Robot robot = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension dim = tk.getScreenSize();
            Rectangle rect = new Rectangle(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
            this.padre.setExtendedState(1);
            image = robot.createScreenCapture(rect);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.padre.setExtendedState(0);
    }

    public Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint();
        Point target_location = this.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()), (int) (target_location.getY() + cursor.getY()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point current = this.getScreenLocation(e);
        Point offset = new Point((int) current.getX() - (int) start_drag.getX(), (int) current.getY() - (int) start_drag.getY());
        Point new_location = new Point((int) (this.start_drag.getX() + offset.getX()), (int) (this.start_drag.getY() + offset.getY()));
        this.padre.setLocation(new_location);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.start_drag = getScreenLocation(e);
        this.start_loc = this.padre.getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ActualizarImagen();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
