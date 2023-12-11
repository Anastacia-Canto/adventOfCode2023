package day11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void sumEqual374() {
        Day11 d11 = new Day11("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day11/simpleInput.txt");
        assertEquals(374L, d11.getSumOfLengths());
    }

    @Test
    public void sumEqual9563821() {
        Day11 d11 = new Day11("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day11/input.txt");
        assertEquals(9563821L, d11.getSumOfLengths());
    }

    @Test
    public void sumEqual374withPart2() {
        Day11Part2 d11 = new Day11Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day11/simpleInput.txt");
        assertEquals(374L, d11.getSumOfLengths(2L));
    }

    @Test
    public void sumEqual827009909817() {
        Day11Part2 d11 = new Day11Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day11/input.txt");
        assertEquals(827009909817L, d11.getSumOfLengths(1000000L));
    }
}
