import bagel.*;
public class NormalLane extends Lane{
    private int numHolds = 0;
    private int numNormals = 0;
    private int indexHolds = 0;
    private int indexNormals = 0;
    private boolean normalFinished = false;

    public NormalLane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage){
        super(laneType, xCoordinate,relevantKey ,laneImage);
    }


    public boolean isNormalFinished() {
        return normalFinished;
    }

    public void setNormalFinished(boolean normalFinished) {
        this.normalFinished = normalFinished;
    }


    @Override
    public void drawLane() {
        super.drawLane();
        for (int i = indexNormals; i < numNormals; i++) {
            normalNotes[i].draw(Lane.getxCoordinate());
        }

        for (int j = indexHolds; j < numHolds; j++) {
            holdNotes[j].draw(Lane.getxCoordinate());
        }
    }
}
