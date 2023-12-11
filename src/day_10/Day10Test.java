package day_10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Test {

    @Test
    public void stepsEqual8() {
        Day10 d10 = new Day10("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_10/tes1t.txt");
        assertEquals(8, d10.getStepsToFarthestPoint());
    }

    @Test
    public void stepsEqual4() {
        Day10 d10 = new Day10("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_10/simpleInput.txt");
        assertEquals(4, d10.getStepsToFarthestPoint());
    }

    @Test
    public void stepsEqual6697() {
        Day10 d10 = new Day10("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_10/input.txt");
        assertEquals(6697, d10.getStepsToFarthestPoint());
    }
}
