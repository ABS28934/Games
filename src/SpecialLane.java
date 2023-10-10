//import bagel.*;
//
//import java.util.ArrayList;
//public class SpecialLane extends Lane{
//    private int numSpecials = 0;
//    private int indexSpecials = 0;
//    private boolean specialFinished = false;
//
//    private static final ArrayList<SpecialNote> specialNotes = new ArrayList<>();
//
//    public SpecialLane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage){
//        super(laneType, xCoordinate,relevantKey ,laneImage);
//    }
//
//    public SpecialLane (){
//        return;
//    }
//
//    public void drawLane() {
//        super.drawLane();
//        for (int i = indexSpecials; i < numSpecials; i++) {
//            specialNotes.get(i).draw();
//        }
//
//    }
//    public void addSpecial(SpecialNote n) {
//        specialNotes.add(n);
//        numSpecials++;
//    }
//
//    public int update(Input input, Accuracy accuracy) {
//        drawLane();
//
//        for (int i = indexSpecials; i < numSpecials; i++) {
//            specialNotes.get(i).update();
//        }
//
//
//        if (indexSpecials < numSpecials) {
//            int score = specialNotes.get(indexSpecials).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
//            if (specialNotes.get(indexSpecials).isCompleted()) {
//                indexSpecials++;
//                return score;
//            }
//        }
//
//
//
//        return Accuracy.NOT_SCORED;
//    }
//    public void setCompleted() {
//        for (int i = 0; i < numSpecials; i++) {
//            specialNotes.get(i).setCompleted(false);
//        }
//
//    }
//
//    public boolean isNormalFinished() {
//        for (int i = 0; i < numSpecials; i++) {
//            if (!specialNotes.get(i).isCompleted()) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//
//}