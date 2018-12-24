package pack;

import java.awt.*;
import java.util.Random;

public class Organism extends GameObject{

    private Random r= new Random();


    public Organism(int x, int y, ID id) {
        super(x, y, id);

        valX = r.nextInt(4) -2 ;
        valY = r.nextInt(4) -2;
        {
            while ((valX == 0) && (valY == 0)) {
                valX = r.nextInt(4) -2;
                valY = r.nextInt(4) -2;
            }
        }

    }



    //@Override
    public void tick() {
        x += valX;
        y += valY;

        if( y <= 0 || y >= Game.HEIGHT-32) valY *= -1;
        if( x <= 0 || x >= Game.WIDTH-10) valX *= -1;


    }

    //@Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,2,2);
    }


}
