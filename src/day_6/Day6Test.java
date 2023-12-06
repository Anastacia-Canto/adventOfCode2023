package day_6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day6Test {

    @Test
    public void resultEqualsTo288() {
        Day6 d6 = new Day6("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_6/simpleInput.txt");
        assertEquals(288, d6.getMultipliedPossibilities());
    }
    @Test
    public void resultEqualsTo71503() {
        Day6Part2 d6 = new Day6Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_6/simpleInput.txt");
        assertEquals(71503, d6.getAmountOfPossibilities());
    }

    @Test
    public void resultEqualsTo27340847() {
        Day6Part2 d6 = new Day6Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_6/input.txt");
        assertEquals(27340847, d6.getAmountOfPossibilities());
    }
}
