package day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day10 {

    private String path;
    private List<String> maze = new ArrayList<>();
    private Map<String, Integer> startPosition = new HashMap<>();
    private Map<String, Integer> actualPosition = new HashMap<>();
    private Map<String, Integer> previousPosition = new HashMap<>();
    private Map<String, Integer> startOfPath = new HashMap<>();
    private int steps;


    public Day10(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            maze = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public void findStart() {
        for (String line : maze) {
            if (line.contains("S")) {
                startPosition.put("line", maze.indexOf(line));
                startPosition.put("column", line.indexOf('S'));
            }
        }
        actualPosition.put("line", startPosition.get("line"));
        actualPosition.put("column", startPosition.get("column"));
        System.out.println("Start: " + startPosition.get("line") + " " + startPosition.get("column"));
    }

    public char lookUp() {
        List<String> possibilities = new ArrayList<>(Arrays.asList("|", "F", "7"));
        if (actualPosition.get("line") > 0) {
            String line = maze.get(actualPosition.get("line") - 1);
            String actualChar = line.substring(actualPosition.get("column"), actualPosition.get("column") + 1);
            if (possibilities.contains(actualChar)) {
                return actualChar.charAt(0);
            }
        }
        return '0';
    }

    public char lookDown() {
        List<String> possibilities = new ArrayList<>(Arrays.asList("|", "L", "J"));
        if (actualPosition.get("line") < maze.size() - 1) {
            String line = maze.get(actualPosition.get("line") + 1);
            String actualChar = line.substring(actualPosition.get("column"), actualPosition.get("column") + 1);
            if (possibilities.contains(actualChar)) {
                return actualChar.charAt(0);
            }
        }
        return '0';
    }

    public char lookRight() {
        List<String> possibilities = new ArrayList<>(Arrays.asList("-", "J"));
        if (actualPosition.get("column") < maze.get(actualPosition.get("line")).length() - 1) {
            String actualChar = maze.get(actualPosition.get("line")).substring(actualPosition.get("column") + 1, actualPosition.get("column") + 2);
            if (possibilities.contains(actualChar)) {
                return actualChar.charAt(0);
            }
        }
        return '0';
    }

    public char lookLeft() {
        List<String> possibilities = new ArrayList<>(Arrays.asList("-", "L"));
        if (actualPosition.get("column") > 0) {
            String actualChar = maze.get(actualPosition.get("line")).substring(actualPosition.get("column") - 1, actualPosition.get("column"));
            if (possibilities.contains(actualChar)) {
                return actualChar.charAt(0);
            }
        }
        return '0';
    }

    public List<String> findPossibleInitialDirections() {
        List<String> paths = new ArrayList<>();
        if (Character.compare(lookUp(), '0') != 0) paths.add("up");
        if (Character.compare(lookDown(), '0') != 0) paths.add("down");
        if (Character.compare(lookRight(), '0') != 0) paths.add("right");
        if (Character.compare(lookLeft(), '0') != 0) paths.add("left");
        return paths;
    }

    public void definePaths(String path, Map<String, Integer> start) {
        switch (path) {
            case "up":
                start.put("line", actualPosition.get("line") - 1);
                start.put("column", actualPosition.get("column"));
                break;
            case "down":
                start.put("line", actualPosition.get("line") + 1);
                start.put("column", actualPosition.get("column"));
                break;
            case "right":
                start.put("line", actualPosition.get("line"));
                start.put("column", actualPosition.get("column") + 1);
                break;
            case "left":
                start.put("line", actualPosition.get("line"));
                start.put("column", actualPosition.get("column") - 1);
        }
    }

    public String defineDirection() {
        String direction = "";
        char position = maze.get(actualPosition.get("line")).charAt(actualPosition.get("column"));
//        System.out.println("actualChar: " + position);
        switch (position) {
            case '|':
                direction = previousPosition.get("line") < actualPosition.get("line") ? "down" : "up";
                break;
            case '-':
                direction = previousPosition.get("column") < actualPosition.get("column") ? "right" : "left";
                break;
            case 'F':
                direction = previousPosition.get("line") == actualPosition.get("line") ? "down" : "right";
                break;
            case 'L':
                direction = previousPosition.get("line") == actualPosition.get("line") ? "up" : "right";
                break;
            case 'J':
                direction = previousPosition.get("line") == actualPosition.get("line") ? "up" : "left";
                break;
            case '7':
                direction = previousPosition.get("line") == actualPosition.get("line") ? "down" : "left";
                break;
        }
        return direction;
    }

    public void move(String direction) {
        previousPosition.put("line", actualPosition.get("line"));
        previousPosition.put("column", actualPosition.get("column"));
        switch (direction) {
            case "up":
                actualPosition.put("line", previousPosition.get("line") - 1);
                break;
            case "down":
                actualPosition.put("line", previousPosition.get("line") + 1);
                break;
            case "left":
                actualPosition.put("column", previousPosition.get("column") - 1);
                break;
            case "right":
                actualPosition.put("column", previousPosition.get("column") + 1);
                break;
        }
        steps++;
    }

    public boolean checkEnd() {
        if (Character.compare('S', maze.get(actualPosition.get("line")).charAt(actualPosition.get("column"))) == 0) return true;
        return false;
    }

    public void walkThroughTheMaze() {
        previousPosition.put("line", actualPosition.get("line"));
        previousPosition.put("column", actualPosition.get("column"));
        actualPosition.put("line", startOfPath.get("line"));
        actualPosition.put("column", startOfPath.get("column"));
        steps++;
        while (!checkEnd()) {
            move(defineDirection());
        }

    }

    public int getStepsToFarthestPoint() {
        openFile();
        findStart();
        List<String> paths = findPossibleInitialDirections();
        System.out.println(paths);
        definePaths(paths.get(0), startOfPath);
        System.out.println("path1: " + startOfPath);
        walkThroughTheMaze();
        System.out.println("steps: " + (steps / 2));
        return steps / 2;
    }

    public static void main(String[] args) {
        Day10 d10 = new Day10("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_10/input.txt");
        System.out.println("steps: " + d10.getStepsToFarthestPoint());
    }
}
