package forrest;

import javax.swing.*;
import java.awt.*;

public class Weapon3 implements IWeapon {
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
        g2d.fillPolygon(new int[]{startX + 100, startX + 165, startX + 165, startX + 100},
                new int[]{startY + 28, startY + 17, startY + 21, startY + 30}, 4);
        g2d.fillPolygon(new int[]{startX + 50, startX - 15, startX - 15, startX + 50},
                new int[]{startY + 28, startY + 17, startY + 21, startY + 30}, 4);
        if(w == Weapons.n2) return;
        g2d.fillPolygon(new int[]{startX + 95, startX + 165, startX + 165, startX + 95},
                new int[]{startY + 35, startY + 30, startY + 35, startY + 35}, 4);
        g2d.fillPolygon(new int[]{startX + 55, startX - 15, startX - 15, startX + 55},
                new int[]{startY + 35, startY + 30, startY + 35, startY + 35}, 4);
        if(w == Weapons.n4) return;
        g2d.fillPolygon(new int[]{startX + 95, startX + 165, startX + 165, startX + 95},
                new int[]{startY + 30, startY + 5, startY, startY + 28}, 4);
        g2d.fillPolygon(new int[]{startX + 50, startX - 15, startX - 15, startX + 50},
                new int[]{startY + 30, startY + 5, startY, startY + 28}, 4);
    }
}
