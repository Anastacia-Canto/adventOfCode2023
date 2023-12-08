package day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day8Part2 {

    private String path;
    private List<String> nodes = new ArrayList<>();
    private String instruction;
    private long steps;
    private List<Integer> startPoints = new ArrayList<>();

    public Day8Part2(String path) {
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

    public boolean checkDestinations() {
        int goal = startPoints.size();
        int count = 0;
        for (int position : startPoints) {
            if (nodes.get(position).substring(0, 3).endsWith("Z")) {
                count++;
            }
        }
        return count == goal;
    }
    public boolean readInstructions() {
        String destination = "";
        for (int i = 0; i < instruction.length(); i++) {
            steps++;
            List<String> actualNodes = startPoints.stream().map(position -> nodes.get(position)).collect(Collectors.toList());
            startPoints.clear();
            for (String node : actualNodes) {
                String[] nodeParts = node.substring(node.indexOf("(") + 1, node.indexOf(")")).split(",");
                System.out.println("node0: " + nodeParts[0] + " node1: " + nodeParts[1]);
                destination = Character.compare(instruction.charAt(i), 'R') == 0
                        ? destination = nodeParts[1].trim() : nodeParts[0].trim();
                startPoints.add(searchForPosition(destination));
            }
            if (checkDestinations()) return true;
        }
        return false;
    }

    public void findStartPoints(){
        startPoints = nodes.stream()
                .filter(node -> node.substring(0, 3).endsWith("A"))
                .map(node -> nodes.indexOf(node))
                .collect(Collectors.toList());
    }

    public long getSteps(){
        openFile();
        findStartPoints();
        while (!readInstructions()) {}
        return steps;
    }

    public static void main(String[] args) {
        Day8Part2 d8 = new Day8Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/input.txt");
        System.out.println(d8.getSteps());
    }
}
