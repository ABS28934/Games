import bagel.*;

/**
 * Class for Level2
 */
public class Level2 extends Levels{

    /**
     * Constructor for level2
     *
     * @param levelScore The current level score.
     * @param csvFile    The CSV file containing level data.
     */
    public Level2(int levelScore, String csvFile){
        super(levelScore, csvFile);
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

    /**
     * Updates the game state for this level, including guardian, enemies, lanes, and accuracy.
     *
     * @param input The user input for the game.
     */
    public void update(Input input){
        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);

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



}
