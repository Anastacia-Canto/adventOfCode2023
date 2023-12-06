package day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {
    private String path;
    private List<Integer> possibleWays = new ArrayList<>();
    private List<Integer> times = new ArrayList<>();
    private List<Integer> distances = new ArrayList<>();

    public Day6(String path) {
        this.path = path;
    }

    public void getInfo(List<String> lines) {
        lines = lines.stream().map(s -> s.replaceAll("\s+", " ")).collect(Collectors.toList());
        for (String line : lines) {
            String[] subLine = line.substring(line.indexOf(":") + 2, line.length()).split(" ");
            if (line.startsWith("Time")) times = Arrays.asList(subLine).stream().map(Integer::valueOf).collect(Collectors.toList());
            else if (line.startsWith("Distance")) distances = Arrays.asList(subLine).stream().map(Integer::valueOf).collect(Collectors.toList());
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

    public void calcDistances() {
        int ways = 0;

        for (int i = 0; i < times.size(); i++) {
            ways = 0;
            for (int j = 2; j < times.get(i); j++) {
                if (j * (times.get(i) - j) > distances.get(i)) ways++;
            }
            possibleWays.add(ways);
        }
        System.out.println(possibleWays);
    }

    public int getMultipliedPossibilities() {
        openFile();
        calcDistances();
        return possibleWays.stream().reduce(1, (a, b) -> a * b);
    }

    public static void main(String[] args) {
        Day6 d6 = new Day6("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_6/input.txt");
        System.out.println(d6.getMultipliedPossibilities());
    }
}
