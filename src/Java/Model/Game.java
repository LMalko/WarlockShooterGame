package Java.Model;

import Java.Enums.ID;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private ObjectHandler objectHandler;
    private Window window;

    private BufferedImage image = null;

    public void startApp(){
        window = new Window(1000, 563, "Player Shooter", this);
        start();
        objectHandler = new ObjectHandler();
        this.addKeyListener(new KeyInput(objectHandler));

        BufferedImageLoader loader = new BufferedImageLoader();
        this.image = loader.loadImage("res/dupa.bmp");


        objectHandler.addGameObject(new Player(100, 100, ID.Player, objectHandler));
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }

    public void tick(){
        /* Update everything in the game. */
        objectHandler.tick();
    }

    public void render(){
//        Render everything in the game.
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.red);
        graphics.fillRect(0, 0, 1000, 563);

        objectHandler.render(graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
