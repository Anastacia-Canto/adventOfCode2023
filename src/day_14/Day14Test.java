package day_14;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void sumEqualsTo136() {
        Day14 d14 = new Day14("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_14/simpleInput.txt");
        assertEquals(136, d14.sumLoad());
    }

    @Test
    public void sumEqualsTo109424() {
        Day14 d14 = new Day14("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_14/input.txt");
        assertEquals(109424, d14.sumLoad());
    }
}
