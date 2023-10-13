import bagel.*;

/** Class for normal notes
 */
public class NormalNote extends Note{
    private static final int YCOORDINATE = 100;

    /**
     * Constructor for the NormalNote class.
     *
     * @param noteType    The type of the normal note, used to set the note's image.
     * @param frameNumber The frame number at which the note  should appear.
     */
    public NormalNote(String noteType, int frameNumber) {
        super(noteType,frameNumber);
        setNoteImage(new Image("res/note" + noteType + ".png"));
        setyCoordinate(YCOORDINATE);
    }


    /**
     * Calculates the score for a normal note.
     *
     * @param input         The input from the user.
     * @param accuracy      The accuracy of the user's input.
     * @param targetHeight  The target height for hitting the note.
     * @param relevantKey   The key associated with the note's lane.
     * @return int Returns the score for pressing the note.
     */
    public int scoreNote(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (super.isActive()) {
            // evaluate accuracy of the key press
            int score = accuracy.evaluateScore(super.getyCoordinate(), targetHeight, input.wasPressed(relevantKey));


            if (score != Accuracy.NOT_SCORED) {
                deactivate();
                return score;
            }

        }

        return 0;
    }





}
