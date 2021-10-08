package forrest;

import java.awt.*;

public abstract class abstrBron implements ITransport {
    protected float _startPosX;

    protected float _startPosY;

    protected int _pictureWidth;

    protected int _pictureHeight;

    protected int MaxSpeed;

    protected float Weight;

    protected Color MainColor;

    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureHeight = height;
        _pictureWidth = width;
    }

    public abstract void DrawTransport(Graphics2D g);

    public abstract void MoveTransport(Direction direction);
}
