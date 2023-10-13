import bagel.*;

import java.util.ArrayList;
import bagel.util.Vector2;

/**
 * Class for projectiles
 */
public class Projectile {

    private final Image PROJECTILE_IMAGE = new Image ("res/arrow.png");
    private Vector2 vector = new Vector2(Guardian.X_COORDINATE,Guardian.Y_COORDINATE);
    private int speed = 6;
    /**
     * The maximum distance for collision detection.
     */
    public static final int COLLISION_DISTANCE = 104;
    private boolean active = true;

    private double rotation = 0;
    DrawOptions drawOptions = new DrawOptions();

    /**
     * Default constructor
     */
    public Projectile(){
        return;
    }

    private void draw() {
        PROJECTILE_IMAGE.draw(vector.x, vector.y, drawOptions);
    }

    /**
     * Sets the activity state of the projectile.
     *
     * @param active A boolean value indicating if the projectile is active.
     */

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Sets the rotation angle of the projectile.
     *
     * @param rotation The new rotation angle in radians.
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }


    /**
     * Updates the position of the projectile and deactivates
     * the projectile and enemy when necessary.
     *
     * @param enemies An ArrayList of `Enemy` objects that are to be deactivated if hit.
     */
    public void update(ArrayList<Enemy> enemies){
        if (active && vector.y < Window.getHeight() && vector.y > 0
                && vector.x < Window.getWidth() && vector.x > 0) {
            double newX = vector.x + speed * Math.cos(rotation);
            double newY = vector.y + speed * Math.sin(rotation);
            vector = new Vector2(newX,newY);
            draw();
            calculateCollision(enemies);
        }
    }
    /* Calculate whether a collision has occurred with any enemy
       and deactivate the enemy if it has occurred
     */
    private void calculateCollision(ArrayList<Enemy> enemies) {
        for (Enemy enemy: enemies) {
            if (enemy.isActive() && Math.abs(vector.y - enemy.getyCoordinate()) <= COLLISION_DISTANCE
                    && Math.abs(vector.x - enemy.getxCoordinate()) <= COLLISION_DISTANCE) {
                enemy.setActive(false);
            }
        }
    }

}
