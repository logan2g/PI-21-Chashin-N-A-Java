package forrest;

import javax.swing.*;
import java.awt.*;

public abstract class abstrWeapon implements IWeapon{
    protected Weapons w = Weapons.n2;
    protected final char separator = ';';
    protected int var;

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

    public void DrawWeapon(Graphics2D g2d, int startX, int startY){};

    @Override
    public String toString(){
        return var + String.valueOf(separator) + w.toString();
    }
}
