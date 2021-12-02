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
        setVarWeapon(varWeapon);
        setn_w(n_weapon);
    }

    private void setVarWeapon(int var){
        switch(var){
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
    }

    public BroneZenit(String info){
        super(info);
        String strs[] = info.split(String.valueOf(separator));
        if (strs.length == 9)
        {
            MaxSpeed = Integer.valueOf(strs[0]);
            Weight = Float.valueOf(strs[1]);
            MainColor = Color.decode(strs[2]);
            delta = Boolean.valueOf(strs[3]);
            DopColor = Color.decode(strs[4]);
            Weapon = Boolean.valueOf(strs[5]);
            Head = Boolean.valueOf(strs[6]);
            setVarWeapon(Integer.valueOf(strs[7]));
            setn_w(Integer.valueOf(strs[8]));
        }
    }

    public void setDopColor(Color color){
        DopColor = color;
    }

    public void setWeapon(IWeapon weapon){
        pn = weapon;
    }

    public void setn_w(int n){
        pn.setn_w(n);
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
        if(Weapon) {
            g.setStroke(new BasicStroke(5));
            pn.DrawWeapon(g, (int) _startPosX, (int) _startPosY);
            g.setStroke(new BasicStroke(1));
        }
        if(Head) g.fillOval((int) _startPosX + 60, (int) _startPosY, 20, 20);
    }

    @Override
    public String toString() {
        return super.toString() + separator + DopColor.getRGB() + separator + Weapon + separator + Head + separator + pn.toString();
    }
}
