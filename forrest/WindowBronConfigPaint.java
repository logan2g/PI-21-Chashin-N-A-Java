package forrest;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WindowBronConfigPaint extends JComponent {
    private static final int windowHeight = 140;
    private static final int windowWidth = 200;
    private Image holst = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = (Graphics2D) holst.getGraphics();
    private Random r;

    public WindowBronConfigPaint(){
        super();
        r = new Random();
        prepare_holst();
    }

    private void drawCar(){
        prepare_holst();
        if(WindowBronConfig.car != null){
            WindowBronConfig.car.SetPosition(25, 15, windowWidth, windowHeight);
            WindowBronConfig.car.DrawTransport(g2d);
        }
    }

    private void prepare_holst(){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, holst.getWidth(this), holst.getHeight(this));
        g2d.setColor(Color.BLACK);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawCar();
        if(holst != null){
            g.drawImage(holst, 0,0,this);
        }
        super.repaint();
    }
}
