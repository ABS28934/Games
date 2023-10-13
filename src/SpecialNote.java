import bagel.*;

/**
 * Class for special notes
 */
public class SpecialNote extends Note{

    private static final int YCOORDINATE = 100;

    /**
     * Constructor for the SpecialNote class.
     *
     * @param noteType    The type of the special note, used to set the note's image.
     * @param frameNumber The frame number at which the note  should appear.
     */
    public SpecialNote(String noteType, int frameNumber) {
        super(noteType,frameNumber);
        // Set the note's image
        if (noteType.equals("DoubleScore")) {
            setNoteImage(new Image("res/note2x.png"));
        } else {
            setNoteImage(new Image("res/note" + noteType + ".png"));
        }
        setyCoordinate(YCOORDINATE);
    }

    /**
     * Calculates the score for a special note.
     *
     * @param input         The input from the user.
     * @param accuracy      The accuracy of the user's input.
     * @param targetHeight  The target height for hitting the note.
     * @param relevantKey   The key associated with the note's lane.
     * @return int Returns the score for pressing the note.
     */
    public int scoreNote(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        int score = 0;
        int distance = Math.abs(super.getyCoordinate() - targetHeight);
        if (super.isActive()) {
            // deactivate missed notes
            if (getyCoordinate() >= Window.getHeight()) {
                deactivate();
            }
            // evaluate accuracy of the key press
            if (input.wasReleased(relevantKey)) {
                if (distance <= Accuracy.ACTIVATE_DISTANCE) {
                    if (getNoteType().equals("SpeedUp")) {
                        score = accuracy.speedUp();
                    } else if (getNoteType().equals("SlowDown")) {
                        score = accuracy.slowDown();
                    } else if (getNoteType().equals("DoubleScore")) {
                        accuracy.doubled();
                    } else if (getNoteType().equals("Bomb")) {
                        accuracy.bombed();
                        setBombed(true);
                    }
                    deactivate();
                }
            }
        }
        return score;
    }





}
