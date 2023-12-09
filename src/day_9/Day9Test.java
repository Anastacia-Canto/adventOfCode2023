package day_9;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day9Test {

    @Test
    public void sumEqualsTo114(){
        Day9 d9 = new Day9("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_9/simpleInput.txt");
        assertEquals(114L, d9.getSumOfExtrapolatedValues(1));
    }

    @Test
    public void sumEqualsTo1980437560(){
        Day9 d9 = new Day9("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_9/input.txt");
        assertEquals(1980437560L, d9.getSumOfExtrapolatedValues(1));
    }

    @Test
    public void sumEqualsTo2(){
        Day9 d9 = new Day9("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_9/simpleInput.txt");
        assertEquals(2L, d9.getSumOfExtrapolatedValues(2));
    }

    @Test
    public void sumEqualsTo977(){
        Day9 d9 = new Day9("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_9/input.txt");
        assertEquals(977L, d9.getSumOfExtrapolatedValues(2));
    }
}
