package day_1;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    @Test
    public void valueEqual12() {
        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/oneLine.txt");
        assertEquals(12, d1.getCalibrationValuesSum());
    }

    @Test
    public void valueEqual142() {
        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/fourLines.txt");
        assertEquals(142, d1.getCalibrationValuesSum());
    }

    @Test
    public void valueEqual249() {
        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/test1.txt");
        assertEquals(249, d1.getCalibrationValuesSum());
    }

    @Test
    public void valueEqual29() {
        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/simpleSpelled.txt");
        assertEquals(29, d1.getCalibrationValuesSum());
    }

    @Test
    public void valueEqual281() {
        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/test1Spelled.txt");
        assertEquals(281, d1.getCalibrationValuesSum());
    }

    @Test
    public void valueEqual83() {
        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/test2Spelled.txt");
        assertEquals(83, d1.getCalibrationValuesSum());
    }

}
