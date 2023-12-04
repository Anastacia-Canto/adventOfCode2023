package day_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day3 {

    private String path;
    private ArrayList<String> schema = new ArrayList<>();
    Map<Integer, Set<Integer>> partNumberPositions = new HashMap<>();
    private ArrayList<Integer> partNumbers = new ArrayList<>();

    private int checkingLine;
    private int checkingPos;

    public Day3(String path) {
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

    public boolean isSymbol(char c) {
        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('@', '#', '$', '%', '&', '*', '+', '=', '-', '/'));
        if (symbols.contains(c)) return true;
        return false;
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

    public void checkUp(int line, int pos) {
        checkingLine = line - 1;
        if (pos > 0) {
            checkingPos = pos - 1;
            if (isDigitCharacter(checkingLine, checkingPos)) {
                partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
            }
        }
        checkingPos = pos;
        if (isDigitCharacter(checkingLine, checkingPos)) {
            partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
        }
        if (pos < schema.get(line).length() - 1) {
            checkingPos = pos + 1;
            if (isDigitCharacter(checkingLine, checkingPos)) {
                partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
            }
        }
    }

    public void checkDown(int line, int pos) {
        checkingLine = line + 1;
        if (pos > 0) {
            checkingPos = pos - 1;
            if (isDigitCharacter(checkingLine, checkingPos)) {
                partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
            }
        }
        checkingPos = pos;
        if (isDigitCharacter(checkingLine, checkingPos)) {
            partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
        }
        if (pos < schema.get(line).length() - 1) {
            checkingPos = pos + 1;
            if (isDigitCharacter(checkingLine, checkingPos)) {
                partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
            }
        }
    }

    public void checkOnTheSame(int line, int pos) {
        checkingLine = line;
        if (pos > 0) {
            checkingPos = pos - 1;
            if (isDigitCharacter(checkingLine, checkingPos)) {
                partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
            }
        }
        if (pos < schema.get(line).length() - 1) {
            checkingPos = pos + 1;
            if (isDigitCharacter(checkingLine, checkingPos)) {
                partNumberPositions.get(checkingLine).add(setInitialPosition(checkingLine, checkingPos));
            }
        }
    }

    public void isNearAPartNumber(int line, int pos) {
        if (line == 0) {
            checkOnTheSame(line, pos);
            checkDown(line, pos);
        } else if (line > 0 && line < schema.size() - 1) {
            checkUp(line, pos);
            checkOnTheSame(line, pos);
            checkDown(line, pos);
        } else if (line == schema.size() - 1) {
            checkUp(line, pos);
            checkOnTheSame(line, pos);
        }
    }

    public void getPartNumber() {
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
        return partNumbers.stream().reduce(0, (a, b) -> a + b);
    }

    public int getSumOfPartNumbers() {
        openFile();

        for (String line : schema) {
            partNumberPositions.put(schema.indexOf(line), new HashSet<>());
        }

        for (String line : schema) {
            for (int i = 0; i < line.length(); i++) {
                if (isSymbol(line.charAt(i))) {
                    isNearAPartNumber(schema.indexOf(line), i);
                }
            }
        }
        getPartNumber();
        return sum();
    }

    public static void main(String[] args) {
        Day3 d3 = new Day3("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_3/input.txt");
        System.out.println(d3.getSumOfPartNumbers());
    }
}
