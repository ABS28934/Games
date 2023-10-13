import bagel.*;


import java.util.ArrayList;

/**
 * Class for guardian characters
 */
public class Guardian extends Character{
    /**
     * The x-coordinate of the guardian character.
     */
    public static final int X_COORDINATE = 800;
    /**
     * The y-coordinate of the guardian character.
     */
    public static final int Y_COORDINATE = 600;

    private final ArrayList<Projectile> projectiles = new ArrayList<>();

    /**
     * Constructor for a guardian
     *
     * @param characterType The character type (guardian in this case)
     */
    public Guardian(String characterType) {
        super(characterType);
        setyCoordinate(Y_COORDINATE);
        setxCoordinate(X_COORDINATE);
    }

    /**
     * Finds the nearest enemy from an array list of enemies.
     *
     * @param enemies The array list of enemy characters to consider.
     * @return Enemy Returns the nearest enemy character.
     */
    public Enemy nearestEnemy(ArrayList<Enemy> enemies) {
        Enemy nearest = null;
        double minDistance = Double.MAX_VALUE;


        for (Enemy enemy : enemies) {
            if (enemy.isActive()) {
                // Calculate the distance between Guardian and the current enemy
                double distance = Math.sqrt(
                        Math.pow(X_COORDINATE - enemy.getxCoordinate(), 2) +
                                Math.pow(Y_COORDINATE - enemy.getyCoordinate(), 2)
                );

                // Check if this enemy is closer than the current nearest enemy
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = enemy;
                }
            }
        }
        return nearest;
    }

    /**
     * Calculates the rotation angle between the
     * guardian character and the nearest enemy.
     *
     * @param enemy The enemy character to calculate the rotation angle to.
     * @return double Returns the rotation angle in radians.
     */
    public double calculateRotation(Enemy enemy){
        int x = enemy.getxCoordinate() - X_COORDINATE;
        int y = enemy.getyCoordinate() - Y_COORDINATE;
        return Math.atan2(y,x);
    }

    /**
     * Updates the state of the guardian by creating projectiles.
     *
     * @param input The user input for the game.
     * @param enemies The array list of enemy characters.
     * @param enemyActive A boolean indicating whether any enemies are currently active.
     */
    public void update(Input input, ArrayList<Enemy> enemies, boolean enemyActive) {
       drawCharacter();
       if (!enemies.isEmpty()) {
           if (input.wasPressed(Keys.LEFT_SHIFT)&& enemyActive) {
               Projectile projectile = new Projectile();
               projectile.setActive(true);
               projectile.drawOptions.setRotation(calculateRotation(nearestEnemy(enemies)));
               projectile.setRotation(calculateRotation(nearestEnemy(enemies)));
               projectiles.add(projectile);
           }

           for (Projectile projectile : projectiles) {
               projectile.update(enemies);
           }
       }

    }

}
