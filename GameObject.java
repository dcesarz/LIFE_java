package pack;

import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected ID id;
    protected int valX, valY;

    public GameObject(int x, int y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
   // public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getValX() {
        return valX;
    }

    public void setValX(int valX) {
        this.valX = valX;
    }

    public int getValY() {
        return valY;
    }

    public void setValY(int valY) {
        this.valY = valY;
    }
}
