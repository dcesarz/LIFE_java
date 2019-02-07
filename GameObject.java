package pack;

import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected int init;
    protected ID id;
    protected int valX;
    protected int valY;
    protected int power;



    protected int repPower;

    public GameObject(int x, int y, int init, int power, ID id, int repPower)
    {
        this.x = x;
        this.y = y;
        this.init = init;
        this.power = power;
        this.id = id;
        this.repPower = repPower;
    }

    public GameObject(GameObject newobj)
    {
        this.x =newobj.x;
        this.y = newobj.y;
        this.init = newobj.init;
        this.power = power;
        this.id = id;
        this.repPower = repPower;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
   // public abstract void collision();

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


    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRepPower() {
        return repPower;
    }

    public void setRepPower(int repPower) {
        this.repPower = repPower;
    }
}
