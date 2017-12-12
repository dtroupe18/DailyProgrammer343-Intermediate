/*
 * Created by Dave on 12/9/17.
 */

public class Beat {
    private String note;
    private double start;
    private double end;

    public Beat(String note, double start, double end) {
        this.note = note;
        this.start = start;
        this.end = end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(note).append(" ").append(start).append(" ").append(end).toString();
    }
}