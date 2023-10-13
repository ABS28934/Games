import bagel.*;


import java.util.ArrayList;

public class Guardian extends Character{
    public static final int X_COORDINATE = 800;
    public static final int Y_COORDINATE = 600;

    private final ArrayList<Projectile> projectiles = new ArrayList<>();


    public Guardian(String characterType) {
        super(characterType);
        setyCoordinate(Y_COORDINATE);
        setxCoordinate(X_COORDINATE);
    }

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

    public double calculateRotation(Enemy enemy){
        int x = enemy.getxCoordinate() - X_COORDINATE;
        int y = enemy.getyCoordinate() - Y_COORDINATE;
        return Math.atan2(y,x);
    }
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
