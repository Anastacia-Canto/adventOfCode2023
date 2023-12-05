package day_5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day5Test {

    @Test
    public void lowestEquals35() {
        Day5 d5 = new Day5("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/simpleInput.txt");
        assertEquals(35, d5.getLowestLocation());
    }

    @Test
    public void lowestEquals261668924() {
        Day5 d5 = new Day5("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/input.txt");
        assertEquals(261668924, d5.getLowestLocation());
    }

    @Test
    public void lowestEquals46() {
        Day5Part2 d5 = new Day5Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/simpleInput.txt");
        assertEquals(46, d5.getLowestLocation());
    }
}
