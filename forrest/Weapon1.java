package forrest;

import java.awt.*;

public class Weapon1 extends abstrWeapon {

    public Weapon1(){
        var = 1;
    }

    @Override
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
