//import bagel.*;
//public class SpecialNote extends Note{
//
//    public SpecialNote(String noteType, int frameNumber, int xCoordinate) {
//        super(noteType,frameNumber,xCoordinate);
//        setNoteImage(new Image("res/note" + noteType + ".png"));
//        setyCoordinate(100);
//    }
//
//    public int scoreNote(Input input, Accuracy accuracy, int targetHeight, Keys relevantKey) {
//        if (super.isActive()) {
//            // evaluate accuracy of the key press
//            int score = accuracy.evaluateScore(super.getyCoordinate(), targetHeight, input.wasPressed(relevantKey));
//
//            if (score != Accuracy.NOT_SCORED) {
//                deactivate();
//                return score;
//            }
//
//        }
//
//        return 0;
//    }
//
//
//
//
//
//}