package day_14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static file_manager.OpenFile.open;

public class Day14Part2 {
    Map<Integer, String> tiltedInput = new HashMap<>();

    public Day14Part2(String path) {
        List<String> input = open(path);
        for (int i = 0; i < input.size(); i++) {
            tiltedInput.put(i, input.get(i));
        }
    }

    public void changeBetweenLines(StringBuilder actualLine, int actualIndex, int newIndex, int actualPosition){
        StringBuilder newLine = new StringBuilder(tiltedInput.get(newIndex));
        newLine.replace(actualPosition, actualPosition + 1, "O");
        actualLine.replace(actualPosition, actualPosition + 1, ".");
        tiltedInput.put(actualIndex, actualLine.toString());
        tiltedInput.put(newIndex, newLine.toString());
    }

    public int loopToNorthOrSouth(char direction, int actualLineIndex, int positionOnString, StringBuilder actualLine) {
        int newLineIndex = actualLineIndex;
        if (Character.compare(direction, 'N') == 0) {
            for (int j = actualLineIndex - 1; j >= 0; j--) {
                if (Character.compare(tiltedInput.get(j).charAt(positionOnString), '.') == 0) {
                    newLineIndex = j;
                } else if (Character.compare(tiltedInput.get(j).charAt(positionOnString), '#') == 0) break;
            }
        } else if (Character.compare(direction, 'S') == 0){
            for (int j = actualLineIndex + 1; j < actualLine.length(); j++) {
                if (Character.compare(tiltedInput.get(j).charAt(positionOnString), '.') == 0) {
                    newLineIndex = j;
                } else if (Character.compare(tiltedInput.get(j).charAt(positionOnString), '#') == 0) break;
            }
        }
        return newLineIndex;
    }

    public void tiltNorthOrSouth(char direction) {
        for (int actualLineIndex = 0; actualLineIndex < tiltedInput.size(); actualLineIndex++) {
            if (Character.compare(direction, 'N') == 0 && actualLineIndex == 0) actualLineIndex = 1;
            if (tiltedInput.get(actualLineIndex).contains("O")) {
                StringBuilder actualLine = new StringBuilder(tiltedInput.get(actualLineIndex));
                int positionOnString = tiltedInput.get(actualLineIndex).indexOf("O");
                while (positionOnString >= 0) {
                    int newLineIndex = loopToNorthOrSouth(direction, actualLineIndex, positionOnString, actualLine);
                    if (newLineIndex != actualLineIndex) {
                        changeBetweenLines(actualLine, actualLineIndex, newLineIndex, positionOnString);
                    }
                    positionOnString = tiltedInput.get(actualLineIndex).indexOf("O", positionOnString + 1);
                }
            }
        }
    }

    public void changeOnString(StringBuilder actualLine, int key, int actualPosition, int newPosition){
        actualLine.replace(newPosition, newPosition + 1, "O");
        actualLine.replace(actualPosition, actualPosition + 1, ".");
        tiltedInput.put(key, actualLine.toString());
    }

    public int loopToWestOrEast(char direction, int position, StringBuilder actualLine) {
        int newPosition = position;
        if (Character.compare(direction, 'W') == 0) {
            for (int i = position; i > 0; i--) {
                if (Character.compare(actualLine.charAt(newPosition - 1), '.') == 0) newPosition--;
                else if (Character.compare(actualLine.charAt(newPosition - 1), '#') == 0) break;
            }
        } else if (Character.compare(direction, 'E') == 0) {
            for (int i = newPosition + 1; i < actualLine.length(); i++) {
                if (Character.compare(actualLine.charAt(i), '.') == 0) newPosition = i;
                else if (Character.compare(actualLine.charAt(i), '#') == 0) break;
            }
        }
        return newPosition;
    }
    public void tiltWestOrEast(char direction){
        for (int key : tiltedInput.keySet()) {
            StringBuilder actualLine = new StringBuilder(tiltedInput.get(key));
            int position = actualLine.indexOf("O");
            if (position == 0 && Character.compare(direction, 'W') == 0) position = actualLine.indexOf("O", position + 1);
            else if (position == actualLine.length() - 1 && Character.compare(direction, 'E') == 0) continue;
            while (position >= 0) {
                int newPosition = loopToWestOrEast(direction, position, actualLine);
                if (newPosition != position) {
                    changeOnString(actualLine, key, position, newPosition);
                }
                position = actualLine.indexOf("O", position + 1);
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

    public void makeACycle(){
        tiltNorthOrSouth('N');
        tiltWestOrEast('W');
        tiltNorthOrSouth('S');
        tiltWestOrEast('E');
    }

    public int sumLoad(long cycles) {
        for (long i = 0; i < cycles; i++) {
            makeACycle();
        }
        int load = 1;
        int totalLoad = 0;
        for (int i = tiltedInput.size() - 1; i >= 0; i--){
            totalLoad += countRocks(i) * load;
            load++;
        }
        return totalLoad;
    }


    public static void main(String[] args) {
        Day14Part2 d14 = new Day14Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_14/simpleInput.txt");
        System.out.println("Total: " + d14.sumLoad(3L));
    }
}
