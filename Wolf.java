package pack;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;

public class Wolf extends GameObject{

    private Random r= new Random();
    Handler handler;

    public Wolf(int x, int y, int init, int power, ID id, int repPower, Handler handler) {
        super(x, y, init, power, id, repPower);

        this.handler = handler;
        this.init = 3;
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

        //Random rand = new Random();

        move();

        /*if(r.nextInt(2)==1) {
            x += 2*valX;
            //    y += valY;}
        }
        else
        {
            x -= 2*valX;
        }
        if(r.nextInt(2)==1) {
            y += 2*valY;
            //    y += valY;}
        }
        else
        {
            y -= 2*valY;
        }*/


        if( y <= 0 || y >= Game.HEIGHT-64) valY *= -1;
        if( x <= 0 || x >= Game.WIDTH-32) valX *= -1;
        repPower+=r.nextInt(2);

        collision();
     power--;
        x = Game.clamp(x,0,Game.WIDTH-32);
        y = Game.clamp(y,0,Game.HEIGHT-64);
    }

    //@Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x,y,25,25);
    }

    public Rectangle getBounds() {
            return new Rectangle(x,y,25,25);
    }

    public void collision()
    {
        for(int i=0; i<handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Sheep)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    setPower(tempObject.getPower() + getPower());
                    if(r.nextInt(2)==1) {
                        power+=tempObject.getPower()/2;
                        tempObject.setPower(0);
                        //  repPower += r.nextInt(3);
                    }
                    else {
                        tempObject.setPower(tempObject.getPower() / 2);

                       // power+=tempObject.getPower()/4;
                    }


                }
            }
            if(tempObject.getId() == ID.Mouse)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    setPower(tempObject.getPower() + getPower());
                    if(r.nextInt(5)==1) {
                        power+=tempObject.getPower()/2;
                        tempObject.setPower(0);
                        //  repPower += r.nextInt(3);
                    }
                    else {
                        tempObject.setPower(tempObject.getPower() / 2);

                        // power+=tempObject.getPower()/4;
                    }


                }
            }
        }
    }
    public void move()
    {
        int distance[]= closestobj();

        if((distance[0]==-1)&&(distance[1]==-1)) {
            if (r.nextInt(2) == 1) {
                x += 2 * valX;
                //    y += valY;}
            } else {
                x -= 2 * valX;
            }
            if (r.nextInt(2) == 1) {
                y += 2 * valY;
                //    y += valY;}
            } else {
                y -= 2 * valY;
            }
        }
        else
        {
            if(distance[0]>getX())
            {
                x+=2;
            }
            else
            {
                x+=-2;
            }
            if(distance[1]>getY())
            {
                y+=2;
            }
            else
            {
                y+=-2;
            }

        }
    }




    public int[] closestobj()
    {
        //int temp3,tempy,tempx;
        double temp1, temp2;
        double x1,x2,y1,y2;

        int[] distance = {0,0};
        Arrays.fill(distance,-1);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject o = handler.object.get(i);
            if(o.getId()==ID.Sheep) {
                if ((distance[0] == -1) && (distance[1] == -1)) {
                    distance[0] = o.getX();
                    distance[1] = o.getY();
                } else {
                    x1=(double)getX();
                    x2=(double)o.getX();
                    y1=(double)getY();
                    y2=(double)o.getY();

                    temp1 = abs(Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1))));

                    x2=(double)distance[0];
                    y2=(double)distance[1];

                    temp2 = abs(Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1))));

                    if(temp1<temp2)
                    {
                        distance[0] = o.getX();
                        distance[1] = o.getY();
                    }


                }
            }
        }

        return distance;
    }


}
