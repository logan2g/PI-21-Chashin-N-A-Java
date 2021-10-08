package forrest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class paintClass extends JComponent implements ActionListener {
    private static final int windowHeight = 600;
    private static final int windowWidth = 800;
    private Image holst = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = (Graphics2D) holst.getGraphics();
    private Random r;
    private ITransport zenitka;

    public paintClass(){
        super();
        r = new Random();
        prepare_holst();
    }

    private void Draw()
    {
        prepare_holst();
        zenitka.DrawTransport(g2d);
    }

    private void prepare_holst(){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, holst.getWidth(this), holst.getHeight(this));
        g2d.setColor(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        prepare_holst();
        switch(e.getActionCommand()){
            case "createBase":
                zenitka = new BroneCar(r.nextInt(200) + 100, r.nextInt(1000) + 1000,
                        Color.BLACK, false);
                zenitka.SetPosition(r.nextInt(100) + 10, r.nextInt(100) + 20, windowWidth, windowHeight - 60);
                break;
            case "createZenit":
                zenitka = new BroneZenit(r.nextInt(200) + 100, r.nextInt(1000) + 1000,
                        Color.BLACK, Color.GREEN, true, true, 2, 6);
                zenitka.SetPosition(r.nextInt(100) + 10, r.nextInt(100) + 20, windowWidth, windowHeight - 60);
                break;
            case "up":
                zenitka.MoveTransport(Direction.Up);
                break;
            case "down":
                zenitka.MoveTransport(Direction.Down);
                break;
            case "right":
                zenitka.MoveTransport(Direction.Right);
                break;
            case "left":
                zenitka.MoveTransport(Direction.Left);
                break;
        }
        Draw();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(holst != null){
            g.drawImage(holst, 0,0,this);
        }
        super.repaint();
    }
}
