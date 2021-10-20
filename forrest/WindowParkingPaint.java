package forrest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WindowParkingPaint extends JComponent implements ActionListener {
    private static final int windowHeight = 600;
    private static final int windowWidth = 900;
    private Image holst = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = (Graphics2D) holst.getGraphics();
    private Random r;
    private Parking<ITransport, IWeapon> parking;

    public WindowParkingPaint(){
        super();
        r = new Random();
        parking = new Parking<>(windowWidth, windowHeight);
        Draw();
    }

    private void Draw()
    {
        prepare_holst();
        parking.Draw(g2d);
    }

    private void prepare_holst(){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, holst.getWidth(this), holst.getHeight(this));
        g2d.setColor(Color.BLACK);
    }

    public void actionPerformed(ActionEvent e){
        prepare_holst();
        switch(e.getActionCommand()){
            case "setBron":
                Color mainColor = JColorChooser.showDialog(null, "Choose a main color", Color.RED);;
                if (mainColor != null){
                    ITransport car = new BroneCar(100,1000,mainColor, false);
                    if(parking.add(car) > -1){
                        Draw();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Парковка переполнена");
                    }
                }
                break;
            case "setZenit":
                mainColor = JColorChooser.showDialog(null, "Choose a main color", Color.RED);
                if (mainColor != null){
                    Color dopColor = JColorChooser.showDialog(null, "Choose an additional color", Color.RED);
                    if(dopColor != null) {
                        ITransport car = new BroneZenit(100, 1000, mainColor, dopColor,
                                true, true, 2, 4);
                        if (parking.add(car) > -1) {
                            Draw();
                        } else {
                            JOptionPane.showMessageDialog(null, "Парковка переполнена");
                        }
                    }
                }
                break;
            case "take":
                if(!WindowParking.tFPos.getText().equals("")){
                    String tmp =  WindowParking.tFPos.getText();
                    tmp = tmp.trim();
                    ITransport car = parking.takeBrone(Integer.parseInt(tmp));
                    if(car != null)
                    {
                        WindowZenit wind = new WindowZenit(car);
                    }
                    Draw();
                }
                break;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(holst != null){
            g.drawImage(holst, 0,0,this);
        }
        super.repaint();
    }
}
