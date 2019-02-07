package pack;

import java.awt.*;
import java.util.Random;


public class Grass extends GameObject{

    private Random r= new Random();
    Handler handler;

    public Grass(int x, int y, int init, int power, ID id, int repPower, Handler handler) {
        super(x, y, init, power, id, repPower);

        this.handler = handler;
    //    this.valX =
     //   this.valY =
    }



    //@Override
    public void tick() {

        repPower+=r.nextInt(6);

       power--;
        x = Game.clamp(x,0,Game.WIDTH-32);
        y = Game.clamp(y,0,Game.HEIGHT-52);

    }

    //@Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x,y,10,10);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,10,10);
    }


}
