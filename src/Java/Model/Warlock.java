package Java.Model;

import Java.Abstract.GameObject;
import Java.Enums.ID;

import java.awt.*;

public class Warlock extends GameObject{

        ObjectHandler handler;

        public Warlock(int x, int y, ID id, ObjectHandler handler) {
                super(x, y, id);
                this.handler = handler;
        }

        public void tick(){
                x += velocityX;
                y += velocityY;

                if(handler.isUp()){
                        velocityY -= 5;
                }else if(!handler.isDown()){
                        velocityY = 0;
                }

                if(handler.isDown()){
                        velocityY -= 5;
                }else if(!handler.isUp()){
                        velocityY = 0;
                }

                if(handler.isLeft()){
                        velocityY -= 5;
                }else if(!handler.isRight()){
                        velocityY = 0;
                }

                if(handler.isRight()){
                        velocityY -= 5;
                }else if(!handler.isLeft()){
                        velocityY = 0;
                }

        }

        @Override
        public void render(Graphics graphics) {
                graphics.setColor(Color.green);
                graphics.fillRect(this.x, this.y, 32, 48);
        }

        @Override
        public Rectangle getBounds() {
                return new Rectangle(this.x, this.y,32,48);
        }


}