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
        int score = 0;
        int distance = Math.abs(super.getyCoordinate() - targetHeight);
        if (super.isActive()) {
            // evaluate accuracy of the key press
            if (getyCoordinate() >= Window.getHeight()) {
                deactivate();
            }
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
                    }
                    deactivate();
                }
            }
        }
        return score;
    }





}
