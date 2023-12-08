package day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    private String path;
    private List<String> nodes = new ArrayList<>();
    private String instruction;
    private int position;
    private long steps;

    public Day8(String path) {
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
            String node = nodes.get(position);
            String[] nodeParts = node.substring(node.indexOf("(") + 1, node.indexOf(")")).split(",");
            destination = Character.compare(instruction.charAt(i), 'R') == 0
                    ? destination = nodeParts[1].trim() : nodeParts[0].trim();
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
        Day8 d8 = new Day8("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_8/input.txt");
        System.out.println(d8.getSteps());
    }
}
