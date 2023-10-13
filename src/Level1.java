import bagel.*;

/**
 * Class for Level1
 */
public class Level1 extends Levels{

    /**
     * Constructor for level2
     *
     * @param levelScore The current level score.
     * @param csvFile    The CSV file containing level data.
     */
    public Level1(int levelScore, String csvFile){
        super(levelScore, csvFile);
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
        super.getAccuracy().update();
        super.setFinished(checkFinished());
        if (checkFinished()){
            for (int i=0; i< getLaneNum();i++){
                getNormalLanes().get(i).clearNotes();
            }
            getNormalLanes().clear();
            setLaneNum(0);

        }

    }

}
