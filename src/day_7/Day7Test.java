package day_7;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day7Test {

    @Test
    public void equalsTo6440() {
        Day7 d7 = new Day7("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_7/simpleInput.txt");
        assertEquals(6440, d7.getTotalWinnings());
    }

    @Test
    public void equalsTo248396258() {
        Day7 d7 = new Day7("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_7/input.txt");
        assertEquals(248396258, d7.getTotalWinnings());
    }

    @Test
    public void equalsTo5905() {
        Day7Part2 d7 = new Day7Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_7/simpleInput.txt");
        assertEquals(5905, d7.getTotalWinnings());
    }

    @Test
    public void equalsTo246436046() {
        Day7Part2 d7 = new Day7Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_7/input.txt");
        assertEquals(246436046, d7.getTotalWinnings());
    }
}
