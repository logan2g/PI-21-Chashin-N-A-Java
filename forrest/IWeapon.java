package forrest;

import java.awt.*;
import java.io.Serializable;

public interface IWeapon extends Serializable {
    void setn_w(int n_w);
    void DrawWeapon(Graphics2D g2d, int startX, int startY);
}
