import bagel.*;

import java.util.ArrayList;
import bagel.util.Vector2;


public class Projectile {

    private final Image PROJECTILE_IMAGE = new Image ("res/arrow.png");
    private Vector2 vector = new Vector2(800,600);
    private int speed = 6;
    private boolean collision = false;
    public static final int COLLISION_DISTANCE = 104;
    private boolean active = true;

    private int direction = 0;
    private double rotation = 0;
    DrawOptions drawOptions = new DrawOptions();

    public Projectile(){
        return;
    }

    private void draw() {
        PROJECTILE_IMAGE.draw(vector.x, vector.y, drawOptions);
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }


    public void update(ArrayList<Enemy> enemies){
        if (active && vector.y < Window.getHeight() && vector.y > 0 && vector.x < Window.getWidth() && vector.x > 0) {
            double newX = vector.x + speed * Math.cos(rotation);
            double newY = vector.y + speed * Math.sin(rotation);
            vector = new Vector2(newX,newY);
            draw();
            calculateCollision(enemies);
        }
    }



    private void calculateCollision(ArrayList<Enemy> enemies) {
        for (Enemy enemy: enemies) {
            if (enemy.isActive() && Math.abs(vector.y - enemy.getyCoordinate()) <= COLLISION_DISTANCE && Math.abs(vector.x - enemy.getxCoordinate()) <= COLLISION_DISTANCE) {
                enemy.setActive(false);
            }
        }
    }

}
