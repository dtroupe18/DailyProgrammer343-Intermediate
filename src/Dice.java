/*
 * Created by Dave on 12/9/17.
 */

import java.security.SecureRandom;

public class Dice {

    private int valueOne;
    private int valueTwo;
    private SecureRandom random;
    private int min = 1;
    private int max = 6;

    public Dice() {
        valueOne = 0;
        valueTwo = 0;
        random = new SecureRandom();
    }

    public int roll() {
        valueOne = random.nextInt(max - min + 1) + min;
        valueTwo = random.nextInt(max - min + 1) + min;

        // System.out.println("ValueOne: " + valueOne);
        // System.out.println("ValueTwo: " + valueTwo);

        return valueOne + valueTwo;
    }
}

