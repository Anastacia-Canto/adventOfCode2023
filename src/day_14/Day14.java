package day_14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static file_manager.OpenFile.open;

public class Day14 {
    Map<Integer, String> tiltedInput = new HashMap<>();

    public Day14(String path) {
        List<String> input = open(path);
        for (int i = 0; i < input.size(); i++) {
            tiltedInput.put(i, input.get(i));
        }
    }

    public void tilt() {
        for (int i = 1; i < tiltedInput.size(); i++) {
            if (tiltedInput.get(i).contains("O")) {
                StringBuilder actualLine = new StringBuilder(tiltedInput.get(i));
                int pos = tiltedInput.get(i).indexOf("O");
                while (pos >= 0) {
                    int newLinePos = i;
                    for (int j = i - 1; j >= 0; j--) {
                        if (Character.compare(tiltedInput.get(j).charAt(pos), 'O') == 0) continue;
                        else if (Character.compare(tiltedInput.get(j).charAt(pos), '.') == 0) {
                            newLinePos = j;
                        } else break;
                    }
                    if (newLinePos != i) {
                        StringBuilder newLine = new StringBuilder(tiltedInput.get(newLinePos));
                        newLine.replace(pos, pos + 1, "O");
                        actualLine.replace(pos, pos + 1, ".");
                        tiltedInput.put(i, actualLine.toString());
                        tiltedInput.put(newLinePos, newLine.toString());
                    }
                    pos = tiltedInput.get(i).indexOf("O", pos + 1);
                }
            }
        }
    }

    public int countRocks(int line) {
        int amount = 0;
        for (int i = 0; i < tiltedInput.get(line).length(); i++) {
            if (Character.compare(tiltedInput.get(line).charAt(i), 'O') == 0) amount++;
        }
        return amount;
    }

    public int sumLoad() {
        tilt();
        int load = 1;
        int totalLoad = 0;
        for (int i = tiltedInput.size() - 1; i >= 0; i--){
            totalLoad += countRocks(i) * load;
            load++;
        }
        return totalLoad;
    }


    public static void main(String[] args) {
        Day14 d14 = new Day14("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_14/input.txt");
        System.out.println("Total: " + d14.sumLoad());
    }
}
