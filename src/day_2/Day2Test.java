package day_2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day2Test {

    @Test
    public void sumEquals8() {
        Day2 d2 = new Day2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_2/simpleTest.txt");
        assertEquals(8, d2.getPossibleIDsSum());
    }

    @Test
    public void sumEquals2286() {
        Day2Part2 d2p2 = new Day2Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_2/simpleTest.txt");
        assertEquals(2286, d2p2.getSumOfPowerOfMinimunSet());
    }
}
