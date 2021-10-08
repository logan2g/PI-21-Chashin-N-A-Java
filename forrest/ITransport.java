package forrest;

import java.awt.*;

public interface ITransport {
    void SetPosition(int x, int y, int width, int height);

    void MoveTransport(Direction direction);

    void DrawTransport(Graphics2D g);
}
