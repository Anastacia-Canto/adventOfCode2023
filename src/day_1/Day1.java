package day_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day1 {

    private String path;
    private ArrayList<String> lines = new ArrayList<>();

    private Map<String, Integer> spelled = new HashMap<>() {
        {
            put("one", 1);
            put("two", 2);
            put("three", 3);
            put("four", 4);
            put("five", 5);
            put("six", 6);
            put("seven", 7);
            put("eight", 8);
            put("nine", 9);
        }};

    public Day1(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            File myFile = new File(path);
            BufferedReader buffer = new BufferedReader(new FileReader(myFile));
            String line;
            while ((line = buffer.readLine()) != null) {
                lines.add(line);
            }
        } catch(IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

    public Integer findCalibrationValue(String line) {
        Integer firstNum = null;
        Integer lastNum = null;

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                if (firstNum == null) firstNum = Character.getNumericValue(line.charAt(i));
                lastNum = Character.getNumericValue(line.charAt(i));
            } else {
                for (String spelledNum : spelled.keySet()) {
                    if (line.indexOf(spelledNum, i) == i) {
                        if (firstNum == null) {
                            firstNum = spelled.get(spelledNum);
                        }
                        lastNum = spelled.get(spelledNum);
                    }
                }
            }
        }
        return Integer.parseInt(firstNum.toString() + lastNum.toString());
    }

    public Integer sum(ArrayList<Integer> list) {
        return list.stream().reduce(0, (a, b) -> a + b);
    }

    public int getCalibrationValuesSum() {
        openFile();

        ArrayList<Integer> list = new ArrayList<>();

        for (String line : lines) {
            list.add(findCalibrationValue(line));
        }

        return sum(list);
    }

    public static void main(String[] args) {

        Day1 d1 = new Day1("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_1/input.txt");
        System.out.println(d1.getCalibrationValuesSum());
    }
}
