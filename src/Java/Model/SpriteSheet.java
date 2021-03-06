package Java.Model;

import java.awt.image.BufferedImage;

public class SpriteSheet {

        private BufferedImage image;

        SpriteSheet(BufferedImage image) {
                this.image = image;
        }

        public BufferedImage grabImage(int col, int row, int width, int height) {
                return image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        }
}
