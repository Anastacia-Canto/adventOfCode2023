package day_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day3Part2 {

    private String path;
    private ArrayList<String> schema = new ArrayList<>();
    private ArrayList<Integer> partNumbers;
    private ArrayList<Integer> gearRatios = new ArrayList<>();

    private int checkingLine;
    private int checkingPos;

    public Day3Part2(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            File inputFile = new File(path);
            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));

            String line;
            while ((line = buffer.readLine()) != null) {
                schema.add(line);
            }
        } catch(IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

    public boolean isDigitCharacter(int line, int pos) {
        return Character.isDigit(schema.get(line).charAt(pos));
    }

    public int setInitialPosition(int line, int pos) {
        int initial = pos;
        while (Character.isDigit(schema.get(line).charAt(initial))) {
            initial--;
            if (initial < 0) break;
        }
        initial++;
        return initial;
    }

    public void check(Map<Integer, Set<Integer>> partNumberPositions) {
        if (isDigitCharacter(checkingLine, checkingPos)) {
            partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
        }
    }

    public void checkUp(int line, int pos, Map<Integer, Set<Integer>> partNumberPositions) {
        checkingLine = line - 1;
        if (pos > 0) {
            checkingPos = pos - 1;
            check(partNumberPositions);
        }
        checkingPos = pos;
        check(partNumberPositions);
        if (pos < schema.get(line).length() - 1) {
            checkingPos = pos + 1;
            check(partNumberPositions);
        }
    }

    public void checkDown(int line, int pos, Map<Integer, Set<Integer>> partNumberPositions) {
        checkingLine = line + 1;
        if (pos > 0) {
            checkingPos = pos - 1;
            check(partNumberPositions);
        }
        checkingPos = pos;
        check(partNumberPositions);
        if (pos < schema.get(line).length() - 1) {
            checkingPos = pos + 1;
            check(partNumberPositions);
        }
    }

    public void checkOnTheSame(int line, int pos, Map<Integer, Set<Integer>> partNumberPositions) {
        checkingLine = line;
        if (pos > 0) {
            checkingPos = pos - 1;
            check(partNumberPositions);
        }
        if (pos < schema.get(line).length() - 1) {
            checkingPos = pos + 1;
            check(partNumberPositions);
        }
    }

    public void isNearAPartNumber(int line, int pos) {
        partNumbers = new ArrayList<>();
        Map<Integer, Set<Integer>> partNumberPositions = new HashMap<>();
        partNumberPositions.put(line - 1, new HashSet<>());
        partNumberPositions.put(line, new HashSet<>());
        partNumberPositions.put(line + 1, new HashSet<>());
        if (line == 0) {
            checkOnTheSame(line, pos, partNumberPositions);
            checkDown(line, pos, partNumberPositions);
        } else if (line > 0 && line < schema.size() - 1) {
            checkUp(line, pos, partNumberPositions);
            checkOnTheSame(line, pos, partNumberPositions);
            checkDown(line, pos, partNumberPositions);
        } else if (line == schema.size() - 1) {
            checkUp(line, pos, partNumberPositions);
            checkOnTheSame(line, pos, partNumberPositions);
        }
        getPartNumber(partNumberPositions);
        partNumberPositions.clear();
        if (partNumbers.size() == 2) {
            gearRatios.add(partNumbers.stream().reduce(1, (a, b) -> a * b));
        }
        partNumbers.clear();
    }

    public void getPartNumber(Map<Integer, Set<Integer>> partNumberPositions) {
        for (int line : partNumberPositions.keySet()) {
            for (int pos : partNumberPositions.get(line)) {
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(schema.get(line).charAt(pos))) {
                    sb.append(schema.get(line).charAt(pos));
                    pos++;
                    if (pos == schema.get(line).length()) break;
                }
                partNumbers.add(Integer.parseInt(sb.toString()));
            }
        }
    }

    public int sum() {
        return gearRatios.stream().reduce(0, (a, b) -> a + b);
    }

    public int getSumOfGearRatios() {
        openFile();

        for (String line : schema) {
            for (int i = 0; i < line.length(); i++) {
                if (Character.compare(line.charAt(i), '*') == 0) {
                    isNearAPartNumber(schema.indexOf(line), i);
                }
            }
        }
        return sum();
    }

    public static void main(String[] args) {
        Day3Part2 d3 = new Day3Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/input.txt");
        System.out.println(d3.getSumOfGearRatios());
    }
}
