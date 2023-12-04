package day_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day4Part2 {

    private String path;
    private ArrayList<String> input = new ArrayList<>();
    private Map<Integer, ArrayList<Integer>> winningNumbers = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> numbers = new HashMap<>();
    private Map<Integer, Integer> instancesPerCard = new HashMap<>();

    public Day4Part2(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            File inputFile = new File(path);
            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));

            String line;
            while ((line = buffer.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading input file.");
            e.printStackTrace();
        }
    }

    public int getFirstNumberPosition(int start, String line) {
        int firstDigitPos = 0;
        for (int i = start; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                firstDigitPos = i;
                break;
            }
        }
        return firstDigitPos;
    }

    public void collectNumbers(int start, int end, String line, ArrayList<Integer> list) {
        for (String num : line.substring(start, end).split(" ")) {
            if (!num.isEmpty()) list.add(Integer.parseInt(num));
        }
    }
    public void splitNumbers() {
        for (String line : input) {
            instancesPerCard.put(input.indexOf(line) + 1, 1);
            int firstDigitPos =getFirstNumberPosition(line.indexOf(':'), line);
            winningNumbers.put(input.indexOf(line) + 1, new ArrayList<>());
            collectNumbers(firstDigitPos, line.indexOf('|'), line, winningNumbers.get(input.indexOf(line) + 1));
            firstDigitPos = getFirstNumberPosition(line.indexOf('|'), line);
            numbers.put(input.indexOf(line) + 1, new ArrayList<>());
            collectNumbers(firstDigitPos, line.length(), line, numbers.get(input.indexOf(line) + 1));
        }
    }

    public void makeCopies() {
        for (int i = 1; i <= numbers.size(); i++) { // Card 1
            int points = 0;
            for (int num : numbers.get(i)) {
                if (winningNumbers.get(i).contains(num)) points++;
            }
            int numOfCards = instancesPerCard.get(i);
            for (int j = 0; j < points; j++) {
                if (i + 1 <= instancesPerCard.size()) instancesPerCard.put(i + j + 1, instancesPerCard.get(i + j + 1) + numOfCards);
            }
        }
    }

    public int countCards() {
        int count = 0;
        for (int i : instancesPerCard.values()) {
            count += i;
        }
        return count;
    }
    public int getTotalOfCards() {
        openFile();
        splitNumbers();
        makeCopies();

        return countCards();
    }

    public static void main(String[] args) {
        Day4Part2 d4 = new Day4Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_4/input.txt");
        System.out.println(d4.getTotalOfCards());
    }
}
