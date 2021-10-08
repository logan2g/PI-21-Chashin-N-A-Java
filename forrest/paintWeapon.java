package forrest;

import javax.swing.*;
import java.awt.*;

public class paintWeapon {
    private Weapons w = Weapons.n2;

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

    public void DrawWeapon(Graphics2D g2d, Color c, int startX, int startY, int offset_y){
        g2d.setColor(c);
        g2d.drawLine(startX + 100, startY + 10 + offset_y, startX + 165, startY - 3 + offset_y);
        g2d.drawLine(startX + 50, startY + 10 + offset_y, startX - 15, startY - 3 + offset_y);
        if(w == Weapons.n2) return;
        g2d.drawLine(startX + 100, startY + 10 + offset_y,startX + 165,startY + 10 + offset_y);
        g2d.drawLine(startX + 50, startY + 10 + offset_y, startX - 15, startY + 10 + offset_y);
        if(w == Weapons.n4) return;
        g2d.drawLine(startX + 100, startY + 10 + offset_y, startX + 165, startY - 15 + offset_y);
        g2d.drawLine(startX + 50, startY + 10 + offset_y, startX - 15, startY - 15 + offset_y);
    }
}
