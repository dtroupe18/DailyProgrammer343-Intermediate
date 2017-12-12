/*
 * Created by Dave on 12/9/17.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MozartsMusicalDice {

    private ArrayList<Measure> measures;
    private int[] randomMeasures;

    private int[][] table = {
                    {96, 32, 69, 40, 148, 104, 152, 119, 98, 3, 54},
                    {22, 6, 95, 17, 74, 157, 60, 84, 142, 87, 130},
                    {141, 128, 158, 113, 163, 27, 171, 114, 42, 165, 10},
                    {41, 63, 13, 85, 45, 167, 53, 50, 156, 61, 103},
                    {105, 146, 153, 161, 80, 154, 99, 140, 75, 135, 28},
                    {122, 46, 55, 2, 97, 68, 133, 86, 129, 47, 37},
                    {11, 134, 110, 159, 36, 118, 21, 169, 62, 147, 106},
                    {30, 81, 24, 100, 107, 91, 127, 94, 123, 33, 5},
                    {70, 117, 66, 90, 25, 138, 16, 120, 65, 102, 35},
                    {121, 39, 136, 176, 143, 71, 155, 88, 77, 4, 20},
                    {26, 126, 15, 7, 64, 150, 57, 48, 19, 31, 108},
                    {9, 56, 132, 34, 125, 29, 175, 166, 82, 164, 92},
                    {112, 174, 73, 67, 76, 101, 43, 51, 137, 144, 12},
                    {49, 18, 58, 160, 136, 162, 168, 115, 38, 59, 124},
                    {109, 116, 145, 52, 1, 23, 89, 72, 149, 173, 44},
                    {14, 83, 79, 170, 93, 151, 172, 111, 8, 78, 131}};

    public static void main(String[] args) {
        MozartsMusicalDice test = new MozartsMusicalDice();
        test.readStartingComposition();
        test.generateSong();

    }

    private void readStartingComposition() {
        int measureEnd = 3;
        BufferedReader br = null;
        ArrayList<Beat> beats = new ArrayList<>();
        measures = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader("StartingComposition.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\s+");
                double start = Double.parseDouble(split[1]);
                double end = Double.parseDouble(split[2]);

                if (start + end <= measureEnd) {
                    Beat beat = new Beat(split[0], start, end);
                    beats.add(beat);
                }
                else {
                    Measure measure = new Measure(beats, (measureEnd / 3) - 1);
                    measures.add(measure);
                    // System.out.println("\nMeasure: " + measure.toString());
                    measureEnd += 3;
                    beats = new ArrayList<>();
                    Beat beat = new Beat(split[0], start, end);
                    beats.add(beat);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void generateSong() {
        randomMeasures = getRandomMeasureNumbers();
        getRandomMeasureNotes();
    }

    private int[] getRandomMeasureNumbers() {
        randomMeasures = new int[16];
        Dice dice = new Dice();
		for (int i = 0; i < 16; i++) {
			int roll = dice.roll() - 2; // 2 -> 12 becomes 0-10 for indexing
            int[] row = table[i];
            randomMeasures[i] = row[roll];
		}
        System.out.println("Measures selected: ");
        System.out.println(Arrays.toString(randomMeasures) + "\n");
        return randomMeasures;
    }

    private void getRandomMeasureNotes() {
        ArrayList<Measure> song = new ArrayList<>();
        int measureCount = 0;
        for (int i: randomMeasures) {
            int index = i - 1;
            Measure current = measures.get(index);
            current.adjustStartTimes(measureCount);
            measureCount++;
            song.add(current);
        }
         System.out.println("Song: ");
         printMeasures(song);
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public void printMeasures(ArrayList<Measure> m) {
        for (Measure current : m) {
            System.out.println(current.toString());
        }
    }
}
