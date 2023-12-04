package day_3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day3Test {

    @Test
    public void sumEquals4361() {
        Day3 d3 = new Day3("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/simpleInput.txt");
        assertEquals(4361, d3.getSumOfPartNumbers());
    }

    @Test
    public void sumEquals13052() {
        Day3 d3 = new Day3("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/test1.txt");
        assertEquals(13052, d3.getSumOfPartNumbers());
    }

    @Test
    public void sumEquals13033() {
        Day3 d3 = new Day3("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/test2.txt");
        assertEquals(13033, d3.getSumOfPartNumbers());
    }

    @Test
    public void sumEquals13421() {
        Day3 d3 = new Day3("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/test3.txt");
        assertEquals(13421, d3.getSumOfPartNumbers());
    }

    @Test
    public void sumEquals467835() {
        Day3Part2 d3 = new Day3Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/simpleInput.txt");
        assertEquals(467835, d3.getSumOfGearRatios());
    }

    @Test
    public void sumEquals1884563() {
        Day3Part2 d3 = new Day3Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/test1.txt");
        assertEquals(1884563, d3.getSumOfGearRatios());
    }

    @Test
    public void sumEquals2330965() {
        Day3Part2 d3 = new Day3Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/test2.txt");
        assertEquals(2330965, d3.getSumOfGearRatios());
    }

}
