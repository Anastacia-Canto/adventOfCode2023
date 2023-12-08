package day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8Part2 {

    private String path;
    private List<String> nodes = new ArrayList<>();
    private String instruction;
    private int position;
    private long steps;

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
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).startsWith(target)) {
                position = i;
                break;
            }
        }
        return position;
    }
    public String readInstructions() {
        String destination = "";
        for (int i = 0; i < instruction.length(); i++) {
            steps++;
            System.out.println("steps: " + steps);
            System.out.println("position: " + position);
            System.out.println("instruction: " + instruction.charAt(i));
            String node = nodes.get(position);
            System.out.println("node: " + node);
            String[] nodeParts = node.substring(node.indexOf("(") + 1, node.indexOf(")")).split(",");
            System.out.println("node0: " + nodeParts[0] + " node1: " + nodeParts[1]);
            destination = Character.compare(instruction.charAt(i), 'R') == 0
                    ? destination = nodeParts[1].trim() : nodeParts[0].trim();
            System.out.println("destination: " + destination);
            if (destination.startsWith("Z")) return destination;
            else position = searchForPosition(destination);
        }
        return destination;
    }

    public long getSteps(){
        openFile();
        position = searchForPosition("AAA");
        while (!readInstructions().startsWith("ZZZ")) {}
        return steps;
    }

    public static void main(String[] args) {
        Day8Part2 d8 = new Day8Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/input.txt");
        System.out.println(d8.getSteps());
    }
}
