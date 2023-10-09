import bagel.*;

import java.util.ArrayList;

public class NormalLane extends Lane{
    private int numHolds = 0;
    private int numNormals = 0;
    private int indexHolds = 0;
    private int indexNormals = 0;
    private boolean normalFinished = false;

    private static final ArrayList<NormalNote> normalNotes = new ArrayList<>();
    private static final ArrayList<HoldNote> holdNotes = new ArrayList<>();

    public NormalLane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage){
        super(laneType, xCoordinate,relevantKey ,laneImage);
    }



    public void setNormalFinished(boolean normalFinished) {
        this.normalFinished = normalFinished;
    }

    public static ArrayList<NormalNote> getNormalNotes() {
        return normalNotes;
    }
    public static ArrayList<HoldNote> getHoldNotes() {
        return holdNotes;
    }




    public void drawLane() {
        super.drawLane();

        for (int i = indexNormals; i < numNormals; i++) {
            normalNotes.get(i).draw();
        }

        for (int j = indexHolds; j < numHolds; j++) {
            holdNotes.get(j).draw();
        }
    }

    /**
     * Finished when all the notes have been pressed or missed
     */
    public boolean isNormalFinished() {
        for (int i = 0; i < numNormals; i++) {
            if (!normalNotes.get(i).isCompleted()) {
                return false;
            }
        }

        for (int j = 0; j < numHolds; j++) {
            if (!holdNotes.get(j).isCompleted()) {
                return false;
            }
        }

        return true;
    }

    public int update(Input input, Accuracy accuracy) {
        drawLane();

        for (int i = indexNormals; i < numNormals; i++) {
            normalNotes.get(i).update();
        }

        for (int j = indexHolds; j < numHolds; j++) {
            holdNotes.get(j).update();
        }

        if (indexNormals < numNormals) {
            int score = normalNotes.get(indexNormals).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
            if (normalNotes.get(indexNormals).isCompleted()) {
                indexNormals++;
                return score;
            }
        }

        if (indexHolds < numHolds) {
            int score = holdNotes.get(indexHolds).scoreNote(input, accuracy, Lane.TARGET_HEIGHT, getRelevantKey());
            if (holdNotes.get(indexHolds).isCompleted()) {
                indexHolds++;
            }
            return score;
        }

        return Accuracy.NOT_SCORED;
    }

    public int getNumHolds() {
        return numHolds;
    }

    public void setNumHolds(int numHolds) {
        this.numHolds = numHolds;
    }

    public int getNumNormals() {
        return numNormals;
    }

    public void setNumNormals(int numNormals) {
        this.numNormals = numNormals;
    }

    public void addNormal(NormalNote n) {
        normalNotes.add(n);
        numNormals++;
    }
    public void addHold(HoldNote n) {
        holdNotes.add(n);
        numHolds++;
    }

}

