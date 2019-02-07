package pack;

import java.awt.*;

public class HUD {


    int s,w,gr,c,m = 0;
    Handler handler;

    public HUD(Handler handler)
    {
        this.handler = handler;
    }

    public void tick()
    {
        s=0;
        w=0;
        gr=0;
        c=0;
        m=0;

     for (int i = 0; i < handler.object.size(); i++) {
            GameObject o = handler.object.get(i);
                if (o.getId() == ID.Wolf) {
                    w++;
                }
                if (o.getId() == ID.Sheep) {
                    s++;
                }
                if (o.getId() == ID.Grass) {
                    gr++;
                }
                if(o.getId() == ID.Cat)
                {
                    c++;
                }
                if(o.getId() == ID.Mouse)
                {
                 m++;
                 }
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(10,9,140,65);
        g.setColor(Color.white);
        g.drawString("SHEEP:", 25, 25);
        g.drawString(String.valueOf(s), 100, 25);
        g.drawString("WOLVES:", 25, 35);
        g.drawString(String.valueOf(w), 100, 35);
        g.drawString("GRASS:", 25, 45);
        g.drawString(String.valueOf(gr), 100, 45);
        g.drawString("MICE:", 25, 55);
        g.drawString(String.valueOf(m), 100, 55);
        g.drawString("CATS:", 25, 65);
        g.drawString(String.valueOf(c), 100, 65);
    }

}
