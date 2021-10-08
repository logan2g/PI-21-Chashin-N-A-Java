package forrest;

import java.awt.*;

public class BroneZenit extends BroneCar{
    public Color DopColor;
    private boolean Weapon;
    private boolean Head;
    private IWeapon pn;

    public BroneZenit(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean weapon, boolean head, int varWeapon, int n_weapon){
        super(maxSpeed, weight, mainColor, true);
        DopColor = dopColor;
        Weapon = weapon;
        Head = head;
        switch(varWeapon){
            case 1:
                pn = new Weapon1();
                break;
            case 2:
                pn = new Weapon2();
                break;
            case 3:
                pn = new Weapon3();
                break;
            default:
                pn = new Weapon1();
                break;
        }
        pn.setn_w(n_weapon);
    }

    @Override
    public void DrawTransport(Graphics2D g) {
        super.DrawTransport(g);
        g.setStroke(new BasicStroke(5));
        g.drawLine((int)_startPosX + 7, (int)_startPosY + 20, (int)_startPosX + 7, (int)_startPosY + 50);
        g.setStroke(new BasicStroke(1));
        g.setColor(DopColor);
        g.fillRect((int) _startPosX + 45, (int) _startPosY + 20, 60, 25);
        g.fillRect((int)_startPosX, (int) _startPosY + 45, 160, 35);
        g.setStroke(new BasicStroke(5));
        pn.DrawWeapon(g, (int) _startPosX, (int) _startPosY);
        g.setStroke(new BasicStroke(1));
        g.fillOval((int) _startPosX + 60, (int) _startPosY, 20, 20);
    }
}
