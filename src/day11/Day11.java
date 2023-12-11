package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11 {

    private String path;
    private List<String> galaxiesMap;
    private long lengths;
    private Map<Integer, List<Integer>> galaxiesPositions = new HashMap<>();

    public Day11(String path) {
        this.path = path;
    }

    public void openFile() {
        try {
            galaxiesMap = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public void expandUniverse() {
        ListIterator<String> iterator = galaxiesMap.listIterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (!line.contains("#")){
                iterator.add(line);
            }
        }
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < galaxiesMap.get(0).length(); i++) {
            int count = 0;
            for (String line : galaxiesMap) {
                if (Character.compare('#', line.charAt(i)) == 0)
                    break;
                count++;
            }
            if (count == galaxiesMap.size()){
                columns.add(i);
            }
        }

        iterator = galaxiesMap.listIterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            StringBuilder sb = new StringBuilder(line);
            for (int column : columns) {
                sb.insert(column + columns.indexOf(column), ".");
            }
            iterator.set(sb.toString());
        }
    }

    public void getPositions() {
        int count = 1;
        for (int i = 0; i < galaxiesMap.size(); i++) {
            String line = galaxiesMap.get(i);
            for (int j = 0; j < line.length(); j++){
                if (Character.compare('#', line.charAt(j)) == 0) {
                    galaxiesPositions.put(count, new ArrayList<>(Arrays.asList(i, j)));
                    count++;
                }
            }
        }
    }

    public void calculateLengths() {
        for (int i = 1; i <= galaxiesPositions.size(); i++) {
            long row1 = galaxiesPositions.get(i).get(0);
            long column1 = galaxiesPositions.get(i).get(1);
            for (int j = i + 1; j <= galaxiesPositions.size(); j++) {
                long row2 = galaxiesPositions.get(j).get(0);
                long column2 = galaxiesPositions.get(j).get(1);
                long length = Math.abs(row2 - row1) + (Math.abs(column2 - column1));
                lengths += length;
            }
        }
    }

    public long getSumOfLengths() {
        openFile();
        expandUniverse();
        getPositions();
        calculateLengths();
        return lengths;
    }

    public static void main(String[] args) {
        Day11 d11 = new Day11("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day11/input.txt");
        System.out.println(d11.getSumOfLengths());
    }
}
