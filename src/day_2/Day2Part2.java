package day_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2Part2 {

    private String path;
    private ArrayList<String> games = new ArrayList<>();

    private int red;
    private int green;
    private int blue;

    public Day2Part2(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            File inputFile = new File(path);
            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = buffer.readLine()) != null) {
                games.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

    public void splitSets(ArrayList<String> setList, String game) {
        int startPosition = game.indexOf(':');
        String sets = game.substring(startPosition + 2);
        for (String set : sets.split(";")) {
            setList.add(set.trim());
        }
    }

    public void countMinimunColors(String set) {
        for (int i = 0; i < set.length(); i++) {
            StringBuilder sb = new StringBuilder();
            if (Character.compare(set.charAt(i), ' ') == 0) i++;
            while (Character.isDigit(set.charAt(i))) {
                sb.append(set.charAt(i));
                i++;
            }
            i++;
            int actualCount = Integer.parseInt(sb.toString());
            if (set.indexOf("red") == i) {
                if (actualCount > red) red = actualCount;
                i += 3;
            }
            else if (set.indexOf("blue") == i) {
                if (actualCount > blue) blue = actualCount;
                i += 4;
            }
            else if (set.indexOf("green") == i) {
                if (actualCount > green) green = actualCount;
                i += 5;
            }
        }
    }

    public int getMinimunSetAndPower(String game) {
        ArrayList<String> setList = new ArrayList<>();
        splitSets(setList, game);

        red = 0;
        green = 0;
        blue = 0;
        for (String set : setList) {
            countMinimunColors(set);
        }
        ArrayList<Integer> minSet = new ArrayList<>();
        if (red > 0) minSet.add(red);
        if (blue > 0) minSet.add(blue);
        if (green > 0) minSet.add(green);
        return minSet.stream().reduce(1, (a, b) -> a * b);
    }

    public int getSumOfPowerOfMinimunSet() {
        openFile();
        int sum = 0;
        for (int i = 0; i < games.size(); i++) {
            sum += getMinimunSetAndPower(games.get(i));
        }
        return sum;
    }

    public static void main(String[] args) {
        Day2Part2 d2 = new Day2Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_2/input.txt");
        System.out.println(d2.getSumOfPowerOfMinimunSet());
    }
}
