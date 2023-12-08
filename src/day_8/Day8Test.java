package day_8;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day8Test {

    @Test
    public void stepsEquals6(){
        Day8 d8 = new Day8("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/simpleInput.txt");
        assertEquals(6, d8.getSteps());
    }

    @Test
    public void stepsEquals2(){
        Day8 d8 = new Day8("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/test1.txt");
        assertEquals(2, d8.getSteps());
    }

    @Test
    public void stepsEquals14257(){
        Day8 d8 = new Day8("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/input.txt");
        assertEquals(14257, d8.getSteps());
    }

    @Test
    public void stepsEquals16187743689077(){
        Day8Part2LCM d8 = new Day8Part2LCM("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/input.txt");
        assertEquals(16187743689077L, d8.getSteps());
    }
}
