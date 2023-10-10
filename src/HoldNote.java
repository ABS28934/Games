import bagel.*;
public class HoldNote extends Note{
    private boolean released;

    private static final int HEIGHT_OFFSET = 82;



    public HoldNote(String noteType, int frameNumber) {
        super(noteType,frameNumber);
        setNoteImage(new Image("res/holdNote" + noteType + ".png"));
        setyCoordinate(24);
        this.released = false;
    }
    public void startHold() {setPressed(true);
    }

    public int scoreNote(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
        if (isActive() && !isPressed()) {
            int score = accuracy.evaluateScore(bottomHeight(), targetHeight, input.wasPressed(relevantKey));

            if (score == Accuracy.MISS_SCORE) {
                deactivate();
                return score;
            } else if (score != Accuracy.NOT_SCORED) {
                startHold();
                return score;
            }
        } else if (isActive() && isPressed()) {

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

    private int topHeight (){
        return getyCoordinate() - HEIGHT_OFFSET;
    }
    private int bottomHeight (){
        return getyCoordinate() + HEIGHT_OFFSET;
    }





}
