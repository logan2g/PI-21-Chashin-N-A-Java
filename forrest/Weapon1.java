package forrest;

import javax.swing.*;
import java.awt.*;

public class Weapon1 implements IWeapon {
    protected Weapons w = Weapons.n2;

    public void setn_w(int n_w){
        switch(n_w){
            case 2:
                w = Weapons.n2;
                break;
            case 4:
                w = Weapons.n4;
                break;
            case 6:
                w = Weapons.n6;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Значение не соответствует значениям перечисления. Будет установлено количество равное 2ум");
                w = Weapons.n2;
                break;
        }
    }

    public void DrawWeapon(Graphics2D g2d, int startX, int startY){
        g2d.drawLine(startX + 100, startY + 30, startX + 165, startY + 17);
        g2d.drawLine(startX + 50, startY + 30, startX - 15, startY + 17);
        if(w == Weapons.n2) return;
        g2d.drawLine(startX + 100, startY + 30,startX + 165,startY + 30);
        g2d.drawLine(startX + 50, startY + 30, startX - 15, startY + 30);
        if(w == Weapons.n4) return;
        g2d.drawLine(startX + 100, startY + 30, startX + 165, startY + 5);
        g2d.drawLine(startX + 50, startY + 30, startX - 15, startY + 5);
    }
}
