package day_4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day4Test {
    @Test
    public void totalOf13() {
        Day4 d4 = new Day4("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_4/simpleInput.txt");
        assertEquals(13, d4.getTotal());
    }

    @Test
    public void totalOf30() {
        Day4Part2 d4 = new Day4Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_4/simpleInput.txt");
        assertEquals(30, d4.getTotalOfCards());
    }
}
