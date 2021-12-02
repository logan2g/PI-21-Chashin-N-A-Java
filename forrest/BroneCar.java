package forrest;

import java.awt.*;

public class BroneCar extends abstrBron{
    private final int carWidth = 160;
    private final int carHeight = 107;
    protected final char separator = ';';
    protected boolean delta;

    public BroneCar(int maxSpeed, float weight, Color mainColor, boolean needDelta)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        delta = needDelta;
    }

    public BroneCar(String info){
        String strs[] = info.split(String.valueOf(separator));
        if (strs.length == 4)
        {
            MaxSpeed = Integer.valueOf(strs[0]);
            Weight = Float.valueOf(strs[1]);
            MainColor = Color.decode(strs[2]);
            delta = Boolean.valueOf(strs[3]);
        }
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

    public void DrawTransport(Graphics2D g) {
        g.setColor(MainColor);
        int offset_y = 0;
        if (delta) offset_y = 20;
        g.drawRect((int) _startPosX + 5, (int) _startPosY + 50 + offset_y, 150, 40);
        //Большие колеса
        g.drawOval((int) _startPosX + 6, (int) _startPosY + 53 + offset_y, 35, 35);
        g.drawOval((int) _startPosX + 120, (int) _startPosY + 53 + offset_y, 35, 35);
        //Маленькие колесики
        g.drawOval((int) _startPosX + 45, (int) _startPosY + 70 + offset_y, 20, 20);
        g.drawOval((int) _startPosX + 70, (int) _startPosY + 70 + offset_y, 20, 20);
        g.drawOval((int) _startPosX + 95, (int) _startPosY + 70 + offset_y, 20, 20);
        //Броня
        g.drawRect((int)_startPosX + 45, (int)_startPosY + offset_y, 60, 25);
        g.drawRect((int)_startPosX, (int)_startPosY + 25 + offset_y, 160, 35);
    }

    @Override
    public String toString()
    {
        return MaxSpeed + String.valueOf(separator) + Weight + separator + MainColor.getRGB() + separator + delta;
    }
}
