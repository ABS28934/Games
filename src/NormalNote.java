import bagel.*;

public class NormalNote extends Note{

    public NormalNote(String noteType, int frameNumber) {
        super(noteType,frameNumber);
        setNoteImage(new Image("res/note" + noteType + ".png"));
        setyCoordinate(100);
    }


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
