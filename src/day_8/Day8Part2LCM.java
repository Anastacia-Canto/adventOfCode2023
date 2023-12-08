package day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8Part2LCM {

    private String path;
    private List<String> nodes = new ArrayList<>();
    private String instruction;
    private Set<Long> minimumSteps = new HashSet<>();
    private List<Integer> startPoints = new ArrayList<>();

    public Day8Part2LCM(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            nodes = Files.readAllLines(Path.of(path));
            instruction = nodes.remove(0);
            nodes.remove(0);
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public int searchForPosition(String target) {
        String nextNode = nodes.stream()
                .filter(node -> node.startsWith(target))
                .findFirst().get();
        return nodes.indexOf(nextNode);
    }

    public void findMinimumSteps(int start) {
        String destination = "";
        long steps = 0L;
        for (int i = 0; i < instruction.length(); i++) {
            steps++;
            String node = nodes.get(start);
            String[] nodeParts = node.substring(node.indexOf("(") + 1, node.indexOf(")")).split(",");
            destination = Character.compare(instruction.charAt(i), 'R') == 0
                    ? destination = nodeParts[1].trim() : nodeParts[0].trim();
            start = searchForPosition(destination);
            if (destination.endsWith("Z")) {
                minimumSteps.add(steps);
                return;
            }
            if (i == instruction.length() - 1) i = -1;
        }
    }

    public void findStartPoints(){
        startPoints = nodes.stream()
                .filter(node -> node.substring(0, 3).endsWith("A"))
                .map(node -> nodes.indexOf(node))
                .collect(Collectors.toList());
    }

    public long findRealSteps() {
        List<Long> steps = new ArrayList<>();
        steps.addAll(minimumSteps);
        steps.sort(null);
        long highestAmount = steps.get(steps.size() - 1);
        long lcm = steps.get(steps.size() - 1);
        int remainder = 10;
        while (remainder != 0) {
            remainder = 0;
            for (long step : minimumSteps) {
                remainder += lcm % step;
            }
            if (remainder != 0) lcm += highestAmount;
        }
        return lcm;
    }

    public long getSteps(){
        openFile();
        findStartPoints();
        for (int position : startPoints) {
            findMinimumSteps(position);
        }
        return findRealSteps();
    }

    public static void main(String[] args) {
        Day8Part2LCM d8 = new Day8Part2LCM("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/input.txt");
        System.out.println(d8.getSteps());
    }
}
