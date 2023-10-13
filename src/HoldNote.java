import bagel.*;

/**
 * Class for hold notes
 */
public class HoldNote extends Note{
    // height offset to find the top and bottom of note
    private static final int HEIGHT_OFFSET = 82;

    private static final int YCOORDINATE = 24;

    /**
     * Constructor for Hold Note class
     *
     * @param noteType      The type of the note, used to set the note's image.
     * @param frameNumber   The frame number at which the note should appear.
     */
    public HoldNote(String noteType, int frameNumber) {
        super(noteType,frameNumber);
        setNoteImage(new Image("res/holdNote" + noteType + ".png"));
        setyCoordinate(YCOORDINATE);
    }

    /**
     * Determines the start of the hold
     * by setting pressed to true.
     */
    public void startHold() {setPressed(true);
    }

    /**
     * Calculate the score for a hold note.
     *
     * @param input         The input from the user.
     * @param accuracy      The accuracy of the user's input.
     * @param targetHeight  The target height for hitting the note.
     * @param relevantKey   The key associated with the note's lane.
     * @return int Returns the score for pressing the note and releasing the note.
     */
    public int scoreNote(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive() && !isPressed()) {
            // Finds the score of pressing the note
            int score = accuracy.evaluateScore(bottomHeight(), targetHeight, input.wasPressed(relevantKey));

            if (score == Accuracy.MISS_SCORE) {
                deactivate();
                return score;
            } else if (score != Accuracy.NOT_SCORED) {
                startHold();
                return score;
            }
        } else if (isActive() && isPressed()) {
            // Finds the score of releasing the note

            int score = accuracy.evaluateScore(topHeight(), targetHeight, input.wasReleased(relevantKey));

            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            } else if (input.wasReleased(relevantKey)) {
                deactivate();
                accuracy.setAccuracy(Accuracy.MISS);
                return Accuracy.MISS_SCORE;
            }
        }

        return 0;
    }

    // Get the top height of the note
    private int topHeight (){
        return getyCoordinate() - HEIGHT_OFFSET;
    }
    // Get the bottom height of the note
    private int bottomHeight (){
        return getyCoordinate() + HEIGHT_OFFSET;
    }





}
