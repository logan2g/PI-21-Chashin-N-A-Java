package forrest;

import java.awt.*;

public class Parking <T extends ITransport, V extends IWeapon>{
    private final T[] _places;
    private final int pictureWidth;
    private final int pictureHeight;
    private final int _placeSizeWidth = 210;
    private final int _placeSizeHeight = 140;

    public Parking(int picWidth, int picHeight)
    {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _places = (T[]) new ITransport[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    private boolean CheckFreePlace(int index)
    {
        return _places[index] == null;
    }

    public int add(T car)
    {
        for (int i = 0; i < _places.length; i++)
        {
            if (CheckFreePlace(i))
            {
                _places[i] = car;
                _places[i].SetPosition(20 + i % 4 * _placeSizeWidth, i / 4 * _placeSizeHeight + 15, pictureWidth, pictureHeight);
                return i;
            }
        }
        return -1;
    }

    public T takeBrone (int index)
    {
        if (index < 0 || index > _places.length)
        {
            return null;
        }
        if (!CheckFreePlace(index))
        {
            T car = _places[index];
            _places[index] = null;
            return car;
        }
        return null;
    }

    public boolean isLower(BroneCar car){
        long min_h = Integer.MAX_VALUE;
        for(int i = 0; i < _places.length; i++){
            if(_places[i] != null)
            {
                if(_places[i].hashCode() < min_h) min_h = _places[i].hashCode();
            }
        }
        if(car.hashCode() < min_h) return true;
        return false;
    }

    public boolean isBigger(BroneCar car){
        long max_h = Integer.MIN_VALUE;
        for(int i = 0; i < _places.length; i++){
            if(_places[i] != null)
            {
                if(_places[i].hashCode() > max_h) max_h = _places[i].hashCode();
            }
        }
        if(car.hashCode() > max_h) return true;
        return false;
    }

    public void Draw(Graphics2D g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.length; i++)
        {
            if(_places[i] != null) _places[i].DrawTransport(g);
        }
    }

    private void DrawMarking(Graphics2D g)
    {
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++)
        {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j)
            {//линия рамзетки места
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i *_placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
        g.setStroke(new BasicStroke(1));
    }
}
