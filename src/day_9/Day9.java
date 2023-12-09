package day_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {
    private String path;
    private List<List<Long>> histories = new ArrayList<>();
    private List<Long> referenceValues = new ArrayList<>();
    private List<Long> extrapolatedValues = new ArrayList<>();

    public Day9(String path) {
        this.path = path;
    }

    public void openFile(){
        try {
            List<String> input = Files.readAllLines(Path.of(path));
            for (String line : input) {
                histories.add(
                        Arrays.asList(line.split(" "))
                                .stream()
                                .map(Long::valueOf)
                                .collect(Collectors.toList()));
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public List<Long> findReferenceValue(List<Long> list){
        List<Long> diffs = new ArrayList<>();
        for (int i = 1; i < list.size(); i++){
            diffs.add(list.get(i) - list.get(i - 1));
        }
        referenceValues.add(diffs.get(diffs.size() - 1));
        return diffs;
    }

    public void calculateExtrapolatedValue(List<Long> history) {
        long lastValue = history.get(history.size() - 1);
        long diff = referenceValues.stream().reduce(0L, (a, b) -> a + b);
        extrapolatedValues.add(lastValue + diff);
        referenceValues.clear();
    }
    public void extrapolateValue(int part) {
        for (List<Long> history : histories) {
            if (part == 2) Collections.reverse(history);
            List<Long> diffs = history;
            while (diffs.stream().reduce(0L, (a, b) -> Math.abs(a) + Math.abs(b)) != 0) {
                diffs = findReferenceValue(diffs);
            }
            calculateExtrapolatedValue(history);
        }
    }

    public long getSumOfExtrapolatedValues(int part) {
        openFile();
        extrapolateValue(part);
        return extrapolatedValues.stream().reduce(0L, (a, b) -> a + b);
    }
    public static void main(String[] args) {
        Day9 d9 = new Day9("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_9/input.txt");
        System.out.println(d9.getSumOfExtrapolatedValues(1));
    }
}
