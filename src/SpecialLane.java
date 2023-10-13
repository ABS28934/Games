import bagel.*;

import java.util.ArrayList;

/**
 * Class for special lane
 */
public class SpecialLane extends Lane{
    private int numSpecials = 0;
    private int indexSpecials = 0;

    private  final ArrayList<SpecialNote> specialNotes = new ArrayList<>();

    /**
     * Constructor for special lane
     *
     * @param laneType   The type of the lane.
     * @param xCoordinate The x-coordinate of the lane.
     * @param relevantKey The relevant key for the lane.
     * @param laneImage   The image corresponding to the lane.
     */
    public SpecialLane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage){
        super(laneType, xCoordinate,relevantKey,laneImage);
    }

    /**
     * Default constructor.
     */
    public SpecialLane (){
        return;
    }

    /**
     * Draws the special lane and the special notes.
     */
    public void drawLane() {
        super.drawLane();
        for (int i = indexSpecials; i < numSpecials; i++) {
            specialNotes.get(i).draw(getXCoordinate());
        }

    }

    /**
     * Adds a special note to the special lane.
     *
     * @param n The special note to add.
     */
    public void addSpecial(SpecialNote n) {
        specialNotes.add(n);
        numSpecials++;
    }

    /**
     * Updates the special lane by drawing it, updating special notes, and scoring notes.
     *
     * @param input     The user input for the game.
     * @param accuracy  The accuracy of the note pressed.
     * @return int Returns the score for the note if pressed.
     */
    public int update(Input input, Accuracy accuracy) {
        drawLane();

        for (int i = indexSpecials; i < numSpecials; i++) {
            specialNotes.get(i).update();
        }

        // score special notes and implement the impact of the notes
        if (indexSpecials < numSpecials) {
            int score = specialNotes.get(indexSpecials).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
            if (specialNotes.get(indexSpecials).getNoteType().equals("Bomb") &&
                    specialNotes.get(indexSpecials).isBombed() ){
                deactivateAllActiveNotes();
            }
            if (specialNotes.get(indexSpecials).isCompleted()) {
                indexSpecials++;
                return score;
            }
        }



        return Accuracy.NOT_SCORED;
    }

    /**
     * Checks if all special notes in the lane are completed.
     *
     * @return boolean Returns whether special notes are completed.
     */
    public boolean isSpecialFinished() {
        for (int i = 0; i < numSpecials; i++) {
            if (!specialNotes.get(i).isCompleted()) {
                return false;
            }
        }
        Note.setSpeed(Lane.SPEED);
        return true;
    }

    /**
     * Clears all special notes from the special lane.
     */
    public void clearNotes() {
        specialNotes.clear();
    }

    /**
     * Deactivates all active special notes in the lane.
     */
    public void deactivateAllActiveNotes() {

        for (SpecialNote note : specialNotes) {
            if (note.isActive()) {
                note.deactivate();
            }
        }
    }


}
