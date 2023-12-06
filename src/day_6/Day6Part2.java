package day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day6Part2 {
    private String path;
    private Long time;
    private Long distance;

    public Day6Part2(String path) {
        this.path = path;
    }

    public void getInfo(List<String> lines) {
        lines = lines.stream().map(s -> s.replaceAll("\s+", "")).collect(Collectors.toList());
        for (String line : lines) {
            long value = Long.parseLong(line.substring(line.indexOf(":") + 1, line.length()));
            if (line.startsWith("Time")) time = value;
            else if (line.startsWith("Distance")) distance = value;
        }
    }
    public void openFile() {
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            getInfo(lines);
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public int calcPossibleWays() {
        int ways = 0;

        long start = distance / time;
        for (long i = start; i < (time - start); i++) {
            if (i * (time - i) > distance) ways++;
        }
        return ways;
    }

    public int getAmountOfPossibilities() {
        openFile();
        return calcPossibleWays();
    }

    public static void main(String[] args) {
        Day6Part2 d6 = new Day6Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_6/input.txt");
        System.out.println(d6.getAmountOfPossibilities());
    }
}
