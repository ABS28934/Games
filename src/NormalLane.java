import bagel.*;

import java.util.ArrayList;

/**
 * Class for normal lane
 */

public class NormalLane extends Lane{
    private int numHolds = 0;
    private int numNormals = 0;
    private int indexHolds = 0;
    private int indexNormals = 0;

    private int numBombs = 0;

    private int indexBombs = 0;

    private  final ArrayList<NormalNote> normalNotes = new ArrayList<>();
    private  final ArrayList<HoldNote> holdNotes = new ArrayList<>();
    private final ArrayList<SpecialNote> bombs = new ArrayList<>();

    /**
     * Constructor for normal lane
     *
     * @param laneType   The type of the lane.
     * @param xCoordinate The x-coordinate of the lane.
     * @param relevantKey The relevant key for the lane.
     * @param laneImage   The image corresponding to the lane.
     */
    public NormalLane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage){
        super(laneType, xCoordinate,relevantKey ,laneImage);
    }

    /**
     * Gets a list of normal notes in the lane.
     *
     * @return ArrayList<NormalNote> Returns the list of normal notes in the lane.
     */
    public  ArrayList<NormalNote> getNormalNotes() {
        return normalNotes;
    }


    /**
     * Checks if all normal notes in the lane are completed.
     *
     * @return boolean Returns whether normal notes are completed.
     */
    public boolean isNormalFinished() {
        for (int i = 0; i < numNormals; i++) {
            if (!normalNotes.get(i).isCompleted()) {
                return false;
            }
        }

        for (int j = 0; j < numHolds; j++) {
            if (!holdNotes.get(j).isCompleted()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Clears all notes from the normal lane.
     */
    public void clearNotes() {
        normalNotes.clear();
        holdNotes.clear();
    }

    /**
     * Updates the normal lane by drawing it, updating notes, and scoring notes.
     *
     * @param input     The user input for the game.
     * @param accuracy  The accuracy of the note pressed.
     * @return int Returns the score for the note if pressed.
     */
    public int update(Input input, Accuracy accuracy) {
        drawLane();


        for (int i = indexNormals; i < numNormals; i++) {
            normalNotes.get(i).update();
        }
        for (int i = indexBombs; i < numBombs; i++) {
            bombs.get(i).update();
        }
        for (int j = indexHolds; j < numHolds; j++) {
            holdNotes.get(j).update();
        }

        // score notes and implement the impact of the notes
        if (indexNormals < numNormals) {
            int score = normalNotes.get(indexNormals).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
            if (normalNotes.get(indexNormals).isCompleted()) {
                indexNormals++;
                return score;
            }
        }
        if (indexBombs < numBombs) {
            int score = bombs.get(indexBombs).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
            if (bombs.get(indexBombs).isBombed()){
                deactivateAllActiveNotes();
            }
            if (bombs.get(indexBombs).isCompleted()) {
                indexBombs++;
                return score;
            }
        }
        if (indexHolds < numHolds) {
            int score = holdNotes.get(indexHolds).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
            if (holdNotes.get(indexHolds).isCompleted()) {
                indexHolds++;
            }
            return score;
        }

        return Accuracy.NOT_SCORED;
    }

    /**
     * Adds a normal note to the normal notes list.
     *
     * @param n The normal note to add.
     */
    public void addNormal(NormalNote n) {
        normalNotes.add(n);
        numNormals++;
    }
    /**
     * Adds a hold note to the hold notes list.
     *
     * @param n The hold note to add.
     */
    public void addHold(HoldNote n) {
        holdNotes.add(n);
        numHolds++;
    }
    /**
     * Adds a bomb note to the bomb notes list.
     *
     * @param n The bomb note to add.
     */
    public void addBomb(SpecialNote n) {
        bombs.add(n);
        numBombs++;
    }

    /**
     * Draws the normal lane and the notes.
     */
    public void drawLane() {
        super.drawLane();
        for (int i = indexNormals; i < numNormals; i++) {
            normalNotes.get(i).draw(getXCoordinate());
        }

        for (int i = indexBombs; i < numBombs; i++) {
            bombs.get(i).draw(getXCoordinate());
        }

        for (int j = indexHolds; j < numHolds; j++) {
            holdNotes.get(j).draw(getXCoordinate());
        }
    }

    /**
     * Deactivates all active notes in the lane.
     */
    public void deactivateAllActiveNotes() {
        for (NormalNote note : normalNotes) {
            if (note.isActive()) {
                note.deactivate();
            }
        }

        for (HoldNote note : holdNotes) {
            if (note.isActive()) {
                note.deactivate();
            }
        }

        for (SpecialNote note : bombs) {
            if (note.isActive()) {
                note.deactivate();
            }
        }

    }
}

