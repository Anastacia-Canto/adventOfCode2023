package day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day7 {

    private String path;
    private List<Character> pattern = new ArrayList<>(
            Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'));;
    private List<String> hands = new ArrayList<>();
    private List<String> fiveOfAKind = new ArrayList<>();
    private List<String> fourOfAKind = new ArrayList<>();
    private List<String> fullHouse = new ArrayList<>();
    private List<String> threeOfAKind = new ArrayList<>();
    private List<String> twoPair = new ArrayList<>();
    private List<String> onePair = new ArrayList<>();
    private List<String> highCard = new ArrayList<>();

    public Day7(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            hands = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public void orderHands() {
        for (String hand : hands) {
            int[] positions = new int[13];
            Arrays.fill(positions, 0);
            for (char c : hand.split(" ")[0].toCharArray()){
               positions[pattern.indexOf(c)]++;
            }
            Arrays.sort(positions);
            switch (positions[12]) {
                case 5: fiveOfAKind.add(hand);
                        break;
                case 4: fourOfAKind.add(hand);
                        break;
                case 3: if (positions[11] == 2) fullHouse.add(hand);
                        else threeOfAKind.add(hand);
                        break;
                case 2: if (positions[11] == 2) twoPair.add(hand);
                        else onePair.add(hand);
                        break;
                case 1: highCard.add(hand);
                        break;
            }
        }
    }

    public void orderTypes(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int result = 0;
                for (int i = 0; i < 5; i++) {
                    result = compareChar(a.charAt(i), b.charAt(i));
                    if (result != 0) return result;
                }
                return result;
            }
        });
    }

    public int compareChar(char a, char b) {
        return pattern.indexOf(a) - pattern.indexOf(b);
    }

    public long calcWinnings() {
       long winnings = 0L;

        long position = hands.size();
        winnings += rankHands(fiveOfAKind, position);
        position -=  fiveOfAKind.size();
        winnings += rankHands(fourOfAKind, position);
        position -= fourOfAKind.size();
        winnings += rankHands(fullHouse, position);
        position -= fullHouse.size();
        winnings += rankHands(threeOfAKind, position);
        position -= threeOfAKind.size();
        winnings += rankHands(twoPair, position);
        position -= twoPair.size();
        winnings += rankHands(onePair, position);
        position -= onePair.size();
        winnings += rankHands(highCard, position);
        return winnings;
    }

    public long rankHands(List<String> list, long position) {
        int winnings = 0;
        for (int i = 0; i < list.size(); i++) {
            long value = Long.parseLong(list.get(i).split(" ")[1]);
            winnings += value * position;
            position--;
        }
        return winnings;
    }

    public long getTotalWinnings() {
        openFile();
        orderHands();
        orderTypes(fiveOfAKind);
        orderTypes(fourOfAKind);
        orderTypes(fullHouse);
        orderTypes(threeOfAKind);
        orderTypes(twoPair);
        orderTypes(onePair);
        orderTypes(highCard);
        return calcWinnings();
    }

    public static void main(String[] args) {
        Day7 d7 = new Day7("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_7/input.txt");
        System.out.println(d7.getTotalWinnings());
    }
}
