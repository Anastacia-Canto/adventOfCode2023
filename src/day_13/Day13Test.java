package day_13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {

    @Test
    public void equalsTo405() {
        Day13 d13 = new Day13("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_13/simpleInput.txt");
        assertEquals(405, d13.findReflection());
    }

    @Test
    public void equalsTo41859() {
        Day13 d13 = new Day13("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_13/input.txt");
        assertEquals(41859, d13.findReflection());
    }
}
