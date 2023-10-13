import bagel.*;

import java.util.ArrayList;

/**
 * Class for Level3
 */
public class Level3 extends Levels{

    private Guardian guardian = new Guardian("guardian");

    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private static final int ENEMY_FRAME = 600;

    private int indexEnemies = 0;

    /**
     * Constructor for level3
     *
     * @param levelScore The current level score.
     * @param csvFile    The CSV file containing level data.
     */
    public Level3(int levelScore, String csvFile){
        super(levelScore, csvFile);
    }

    /**
     * Updates the game state for this level, including guardian, enemies, lanes, and accuracy.
     *
     * @param input The user input for the game.
     */
    public void update(Input input){
        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);

        guardian.update(input,enemies,enemyActive());
        // Create enemies
        if (ShadowDance.getFrameCount()%ENEMY_FRAME == 0){
            enemies.add(new Enemy("enemy"));
            enemies.get(indexEnemies).setActive(true);
            indexEnemies++;
        }
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < getLaneNum(); j++) {
                enemies.get(i).update(getNormalLanes().get(j).getNormalNotes(),getNormalLanes().get(j).getXCoordinate());
            }
        }

        for (int i = 0; i < getLaneNum(); i++) {
            super.setLevelScore(getLevelScore() + super.getNormalLanes().get(i).update(input, getAccuracy()));
        }
        super.setLevelScore(getLevelScore() + super.getSpecialLane().update(input, getAccuracy()));

        super.getAccuracy().update();
        super.setFinished(checkFinished());
        if (checkFinished()){
            for (int i=0; i< getLaneNum();i++){
                getNormalLanes().get(i).clearNotes();
            }
            getSpecialLane().clearNotes();
            getNormalLanes().clear();
            setLaneNum(0);

        }


    }

    /**
     * Checks if there are active enemies in the level.
     *
     * @return boolean Returns whether any enemy is active
     */
    public boolean enemyActive(){
        for (Enemy enemy: enemies){
            if (enemy.isActive()){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the level is finished.
     *
     * @return boolean Returns the completion status of the level
     */
    public boolean checkFinished() {
        for (int i = 0; i < getLaneNum(); i++) {
            if (!getNormalLanes().get(i).isNormalFinished()) {
                return false;
            }
        }
        if (!getSpecialLane().isSpecialFinished()) {
            return false;
        }
        return true;
    }




}
