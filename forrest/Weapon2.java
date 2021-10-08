package forrest;

import javax.swing.*;
import java.awt.*;

public class Weapon2 implements IWeapon {
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
        Color bak = g2d.getColor();
        g2d.drawLine(startX + 100, startY + 30, startX + 165, startY + 17);
        g2d.drawLine(startX + 50, startY + 30, startX - 15, startY + 17);
        g2d.setColor(Color.RED);
        g2d.fillRect(startX + 160, startY + 12, 10, 10);
        g2d.fillRect(startX - 20, startY + 12, 10, 10);
        g2d.setColor(bak);
        if(w == Weapons.n2) return;
        g2d.drawLine(startX + 100, startY + 30,startX + 165,startY + 30);
        g2d.drawLine(startX + 50, startY + 30, startX - 15, startY + 30);
        g2d.setColor(Color.RED);
        g2d.fillRect(startX + 160, startY + 25, 10, 10);
        g2d.fillRect(startX - 20, startY + 25, 10, 10);
        g2d.setColor(bak);
        if(w == Weapons.n4) return;
        g2d.drawLine(startX + 100, startY + 30, startX + 165, startY + 5);
        g2d.drawLine(startX + 50, startY + 30, startX - 15, startY + 5);
        g2d.setColor(Color.RED);
        g2d.fillRect(startX + 160, startY, 10, 10);
        g2d.fillRect(startX - 20, startY, 10, 10);
        g2d.setColor(bak);
    }
}
