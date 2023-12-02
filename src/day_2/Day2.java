package day_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    private String path;
    private ArrayList<String> games = new ArrayList<>();

    public Day2(String path) {
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

    public boolean countColors(String set) {
        // 3 blue, 4 red
        int red = 0;
        int blue = 0;
        int green = 0;

        for (int i = 0; i < set.length(); i++) {
            StringBuilder sb = new StringBuilder();
            if (Character.compare(set.charAt(i), ' ') == 0) i++;
            while (Character.isDigit(set.charAt(i))) {
                sb.append(set.charAt(i));
                i++;
            }
            i++;
            if (set.indexOf("red") == i) {
                red = Integer.parseInt(sb.toString());
                i += 3;
            }
            else if (set.indexOf("blue") == i) {
                blue = Integer.parseInt(sb.toString());
                i += 4;
            }
            else if (set.indexOf("green") == i) {
                green = Integer.parseInt(sb.toString());
                i += 5;
            }
        }
        return (red <= 12) && (green <= 13) && (blue <= 14);
    }

    public boolean isGamePossible(String game) {
        ArrayList<String> setList = new ArrayList<>();
        splitSets(setList, game);
        for (String set : setList){
            if (!countColors(set)) return false;
        }
        return true;
    }

    public int getPossibleIDsSum() {
        openFile();
        int sum = 0;
        for (int i = 0; i < games.size(); i++) {
            if (isGamePossible(games.get(i))) sum += i + 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        Day2 d2 = new Day2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_2/input.txt");
        System.out.println(d2.getPossibleIDsSum());
    }
}
