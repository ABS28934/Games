//import java.io.BufferedReader;
//import java.io.FileReader;
//import bagel.*;
//
//public abstract class Levels {
//    private int levelScore;
//    private int targetScore;
//    private String csvFile;
//    private int laneNum = 0;
//    private boolean finished;
//
//    private final NormalLane[] normalLanes = new NormalLane[4];
//
//    public void readCsv() {
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//            String textRead;
//            while ((textRead = br.readLine()) != null) {
//                String[] splitText = textRead.split(",");
//
//                if (splitText[0].equals("Lane") && !splitText[1].equals("Special")) {
//                    // reading lanes
//                    String laneType = splitText[1];
//                    int pos = Integer.parseInt(splitText[2]);
//                    Keys relevantKey = Lane.getRelevantKey(laneType);
//                    Image laneImage = Lane.getLaneImage(laneType);
//                    NormalLane normalLane = new NormalLane(laneType, pos, relevantKey,laneImage);
//                    normalLanes[laneNum++] = normalLane;
//                } else if (!splitText[0].equals("Lane") && !splitText[1].equals("Special")){
//                    // reading notes
//                    String dir = splitText[0];
//                    NormalLane normalLane = null;
//                    for (int i = 0; i < laneNum; i++) {
//                        if (normalLanes[i].getLaneType().equals(dir)) {
//                            normalLane = normalLanes[i];
//                        }
//                    }
//
//                    if (normalLane != null) {
//                        switch (splitText[1]) {
//                            case "Normal":
//                                NormalNote note = new Note(dir, Integer.parseInt(splitText[2]));
//                                normalLane.addNote(note);
//                                break;
//                            case "Hold":
//                                HoldNote holdNote = new HoldNote(dir, Integer.parseInt(splitText[2]));
//                                normallane.addHoldNote(holdNote);
//                                break;
//                        }
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//
//    }
//
//    public boolean checkFinished() {
//        for (int i = 0; i < laneNum; i++) {
//            if (normalLanes[i].isNormalFinished()==true){
//                finished = true;
//            }
//        }
//        return finished;
//    }
//
//
//}
