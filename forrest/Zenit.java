package forrest;

import java.awt.*;

public class Zenit {
    private float _startPosX;
    private float _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private final int carWidth = 160;
    private final int carHeight = 107;
    private boolean Armor;
    private boolean Weapon;
    private boolean Head;
    private paintWeapon pn;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;

    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean armor, boolean weapon, boolean head)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        Armor = armor;
        Weapon = weapon;
        Head = head;
        pn = new paintWeapon();
        pn.setn_w(4);
    }

    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (_startPosX + step < _pictureWidth - carWidth)
                {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;
            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - carHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }

    public void DrawTransport(Graphics2D g)
    {
        g.setColor(MainColor);
        int offset_y = 0;
        if(Head) offset_y = 20;
        g.drawRect((int)_startPosX + 5, (int)_startPosY + 50 + offset_y, 150, 40);
        //Большие колеса
        g.drawOval((int)_startPosX + 6, (int)_startPosY + 53 + offset_y, 35, 35);
        g.drawOval((int)_startPosX + 120, (int)_startPosY + 53 + offset_y, 35, 35);
        //Маленькие колесики
        g.drawOval((int)_startPosX + 45, (int)_startPosY + 70 + offset_y, 20, 20);
        g.drawOval((int)_startPosX + 70, (int)_startPosY + 70 + offset_y, 20, 20);
        g.drawOval((int)_startPosX + 95, (int)_startPosY + 70 + offset_y, 20, 20);
        g.setStroke(new BasicStroke(5));
        g.drawLine((int)_startPosX + 7, (int)_startPosY + offset_y, (int)_startPosX + 7, (int)_startPosY + 30 + offset_y);
        g.setStroke(new BasicStroke(1));
        if (Armor) {
            g.setColor(DopColor);
            g.fillRect((int) _startPosX + 45, (int) _startPosY + offset_y, 60, 25);
            g.fillRect((int)_startPosX, (int) _startPosY + 25 + offset_y, 160, 35);
        }
        else
        {
            g.drawRect((int)_startPosX + 45, (int)_startPosY + offset_y, 60, 25);
            g.drawRect((int)_startPosX, (int)_startPosY + 25 + offset_y, 160, 35);
        }
        if (Weapon)
        {
            g.setStroke(new BasicStroke(5));

            pn.DrawWeapon(g, DopColor, (int)_startPosX, (int)_startPosY, offset_y);
            g.setStroke(new BasicStroke(1));
        }
        if (Head)
        {
            g.setColor(DopColor);
            g.fillOval((int)_startPosX + 60, (int)_startPosY, 20, 20);
        }
    }
}
