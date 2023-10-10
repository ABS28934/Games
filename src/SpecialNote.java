import bagel.*;
public class SpecialNote extends Note{

    public SpecialNote(String noteType, int frameNumber) {
        super(noteType,frameNumber);
        if (noteType.equals("DoubleScore")) {
            setNoteImage(new Image("res/note2x.png"));
        } else {
            setNoteImage(new Image("res/note" + noteType + ".png"));
        }
        setyCoordinate(100);
    }

    public int scoreNote(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (super.isActive()) {
            // evaluate accuracy of the key press
            int score = 0;
            if (getNoteType().equals("SpeedUp")) {
                 score = accuracy.speedUp(super.getyCoordinate(), targetHeight, input.wasPressed(relevantKey));
            }
            else if (getNoteType().equals("SlowDown")) {
                score = accuracy.slowDown(super.getyCoordinate(), targetHeight, input.wasPressed(relevantKey));

            }
            else if (getNoteType().equals("DoubleScore")) {
                score = accuracy.doubled(super.getyCoordinate(), targetHeight, input.wasPressed(relevantKey));
            }

            if (score != Accuracy.NOT_SCORED ) {
                deactivate();
                if (score == Accuracy.ARBITRARY_SCORE){
                    score -= Accuracy.ARBITRARY_SCORE;
                }
                return score;
            }

        }

        return 0;
    }





}
