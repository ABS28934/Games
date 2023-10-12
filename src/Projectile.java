import bagel.*;

import java.util.ArrayList;


public class Projectile {

    private final Image PROJECTILE_IMAGE = new Image ("res/arrow.png");
    private int xCoordinate = 800;
    private int yCoordinate = 600;

    private int speed = 6;
    private boolean collision = false;
    public static final int COLLISION_DISTANCE = 104;
    private boolean active = true;
    DrawOptions drawOptions = new DrawOptions();

    public Projectile(){
        return;
    }

    private void draw() {
        PROJECTILE_IMAGE.draw(xCoordinate, yCoordinate, drawOptions);
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public void update(ArrayList<Enemy> enemies){
        if (active && yCoordinate < Window.getHeight() && yCoordinate > 0 && xCoordinate < Window.getWidth() && xCoordinate > 0) {
            draw();
            calculateCollision(enemies);
        }
    }



    private void calculateCollision(ArrayList<Enemy> enemies) {
        for (Enemy enemy: enemies) {
            if (enemy.isActive() && Math.abs(yCoordinate - enemy.getyCoordinate()) <= COLLISION_DISTANCE && Math.abs(xCoordinate - enemy.getxCoordinate()) <= COLLISION_DISTANCE) {
                enemy.setActive(false);
            }
        }
    }

}
