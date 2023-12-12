package day_12;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Test {
    @Test
    public void sumEquals21() {
        Day12 d12 = new Day12("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/simpleInput.txt");
        assertEquals(21, d12.checkSprings());
    }

    @Test
    public void sumEquals7718() {
        Day12 d12 = new Day12("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/input.txt");
        assertEquals(7718, d12.checkSprings());
    }
}
