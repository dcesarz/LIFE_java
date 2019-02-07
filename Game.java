package pack;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public final static int WIDTH = 1280, HEIGHT = 720;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random r;
    private HUD hud;


    public Game(){

        handler = new Handler();
        new Window(WIDTH, HEIGHT, "LIFE (alpha)",this);
        hud = new HUD(handler);
        r = new Random();

        for(int i=0;i<10;i++) {
            handler.addObject(new Wolf(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-64),8 ,120,ID.Wolf,0,handler));
        }
        for(int i=0;i<6;i++) {
            handler.addObject(new Cat(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-64),1 ,85,ID.Cat,0,handler));
        }

        for(int i=0;i<36;i++) {
            handler.addObject(new Sheep(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-64), 4,100, ID.Sheep,0,handler));
        }

        for(int i=0;i<15;i++) {
            handler.addObject(new Mouse(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-64), 4,61, ID.Mouse,0,handler));
        }

        for(int i=0;i<30;i++) {
            handler.addObject(new Grass(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-64),1 ,80,ID.Grass,0,handler));
        }


    }


    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }


    public synchronized void stop()
    {
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void run()
    {
        Random r= new Random();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 500){
                timer += 500;
                System.out.println("FPS: " + frames);
                frames = 0;
                for (int i = 0; i < handler.object.size(); i++) {
                    GameObject o = handler.object.get(i);
                    if (o.getPower() <= 0) {
                        handler.object.remove(o);
                    }

                }
                for (int i = 0; i < handler.object.size(); i++) {
                    GameObject o = handler.object.get(i);
                    if (o.getRepPower() >= 100) {

                        if (o.getId() == ID.Wolf) {
                            o.setRepPower(0);
                            handler.object.add(new Wolf(o.getX() + r.nextInt(4) - 2, o.getY() + r.nextInt(4) - 2, o.getInit(), 120, ID.Wolf, 0, handler));
                        }
                        if (o.getId() == ID.Sheep) {
                            o.setRepPower(0);
                            handler.object.add(new Sheep(o.getX() + r.nextInt(50) - 25, o.getY() + r.nextInt(50) - 25, o.getInit(), 100, ID.Sheep, 0, handler));
                        }
                        if (o.getId() == ID.Cat) {
                            o.setRepPower(0);
                            handler.object.add(new Cat(o.getX() + r.nextInt(50) - 25, o.getY() + r.nextInt(50) - 25, o.getInit(), 85, ID.Cat, 0, handler));
                        }
                        if (o.getId() == ID.Mouse) {
                            o.setRepPower(0);
                            handler.object.add(new Mouse(o.getX() + r.nextInt(50) - 25, o.getY() + r.nextInt(50) - 25, o.getInit(), 61, ID.Mouse, 0, handler));
                        }
                        if (o.getId() == ID.Grass) {
                            o.setRepPower(0);
                            handler.object.add(new Grass(o.getX() + r.nextInt(160) - 80, o.getY() + r.nextInt(160) - 80, o.getInit(), 80, ID.Grass, 0, handler));
                        }
                    }
                }
                handler.addObject(new Grass(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-64),1 ,80,ID.Grass,0,handler));
            }
        }
        stop();

    }

    private void tick()
    {
    handler.tick();
    hud.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,WIDTH,HEIGHT);


        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static int clamp(int var, int min, int max)
    {
        if(var>=max)
        {
            return var = max;
        }
        else if(var<=min)
        {
            return var = min;
        }
        else
            return var;
    }



    public static void main(String[] args) {
       new Game();


    }
}
