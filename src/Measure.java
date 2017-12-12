/*
 * Created by Dave on 12/9/17.
 */

import java.util.ArrayList;

public class Measure {

    private ArrayList<Beat> beats;
    private int measureNumber;

    public Measure(ArrayList<Beat> beats, int measureNumber) {
        this.beats = beats;
        this.measureNumber = measureNumber;
    }

    public ArrayList<Beat> getBeats() {
        return beats;
    }

    public void setBeats(ArrayList<Beat> beats) {
        this.beats = beats;
    }

    public int getMeasureNumber() {
        return measureNumber;
    }

    public void setMeasureNumber(int measureNumber) {
        this.measureNumber = measureNumber;
    }

    public void adjustStartTimes(int number) {
        // fix so it loops over each beat and adjusts the time accordingly
        if (!beats.isEmpty()) {
            double initialStart = beats.get(0).getStart();
            for (Beat b: beats) {
                double previousStart = b.getStart();
                double updatedStart = previousStart - (initialStart - (number * 3));
                b.setStart(updatedStart);
            }
        }
    }

    public int getSize() {
        return beats.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Beat b: beats) {
            sb.append(b.toString()).append("\n");
        }
        return sb.toString();
    }
}

