package pack;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Mouse extends GameObject{

    private Random r= new Random();
    Handler handler;
    HUD hud;

    public Mouse(int x, int y, int init, int power, ID id, int repPower, Handler handler) {
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

        if( y <= 0 || y >= Game.HEIGHT-64) valY *= -1;
        if( x <= 0 || x >= Game.WIDTH-32) valX *= -1;


        repPower+=r.nextInt(7);

        collision();
        power--;
        x = Game.clamp(x,0,Game.WIDTH-32);
        y = Game.clamp(y,0,Game.HEIGHT-64);

    }

    //@Override
    public void render(Graphics g) {
        // Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.lightGray);
        g.fillRect(x,y,8,8);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x,y,8,8);
    }

    public void collision()
    {
        for(int i=0; i<handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Grass)
            {
                if(getBounds().intersects(tempObject.getBounds())) {
                  //  if (r.nextInt(2) == 1)
                  //  {
                        setPower(tempObject.getPower() + getPower());
                    tempObject.setPower(0);
                    repPower += 2;
                }
                }
            }
        }


    public void move()
    {
        int velobj1[]= closestobj(ID.Grass);
        int velobj2[]= closestobj(ID.Cat);
        double x1,x2,y1,y2,temp;
        int temp1,temp2;


        if((velobj2[2]==0)&&(velobj2[3]==0)) {
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
        else {
     /*     x1 = (double) getX();
          x2 = (double) velobj1[2];
          y1 = (double) getX();
          y2 = (double) velobj1[3];*/



            x1 = (double) getX();
            x2 = (double) velobj2[2];
            y1 = (double) getX();
            y2 = (double) velobj2[3];
            temp = abs(Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1))));
            if (temp < 30) {
                setValX(-velobj2[0]);
                setValY(-velobj2[1]);
            }

            x += 3 * valX;
            y += 3 * valY;
        }
    }







    public int[] closestobj(ID z)
    {
        //int temp3,tempy,tempx;
        double temp1, temp2;
        double x1,x2,y1,y2;

        int[] velobj = {0,0,0,0};
        //Arrays.fill(velobj,-1);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject o = handler.object.get(i);
            if(o.getId()==z) {
                if ((velobj[0] == 0) && (velobj[1] == 0)) {
                    velobj[0] = o.getValX();
                    velobj[1] = o.getValY();
                    velobj[2] = o.getX();
                    velobj[3] = o.getY();
                } else {
                    x1=(double)getX();
                    x2=(double)o.getX();
                    y1=(double)getY();
                    y2=(double)o.getY();

                    temp1 = abs(Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1))));

                    x2=(double)velobj[0];
                    y2=(double)velobj[1];

                    temp2 = abs(Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1))));

                    if(temp1<temp2)
                    {
                        velobj[0] = o.getValX();
                        velobj[1] = o.getValY();
                        velobj[2] = o.getX();
                        velobj[3] = o.getY();

                    }


                }
            }
        }

        return velobj;
    }

}

