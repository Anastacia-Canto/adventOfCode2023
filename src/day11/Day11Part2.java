package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11Part2 {

    private String path;
    private List<String> galaxiesMap;
    private long lengths;
    private Map<Integer, List<Long>> galaxiesPositions = new HashMap<>();
    private Map<String, List<Long>> emptyLinesPositions = new HashMap<>();
    private long emptyRows;
    private long emptyColumns;
    private long factor;

    public Day11Part2(String path) {
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

    public void getEmptyLinesPositions() {
        emptyLinesPositions.put("rows", new ArrayList<>());
        emptyLinesPositions.put("columns", new ArrayList<>());
        //storing rows
        for (int i = 0; i < galaxiesMap.size(); i++) {
            if (!galaxiesMap.get(i).contains("#")) {
                emptyLinesPositions.get("rows").add((long)i);
            }
        }
        //storing columns
        for (int i = 0; i < galaxiesMap.get(0).length(); i++) {
            int j = 0;
            for (; j < galaxiesMap.size(); j++) {
                if (Character.compare('#', galaxiesMap.get(j).charAt(i)) == 0)
                    break;
            }
            if (j == galaxiesMap.size()){
                emptyLinesPositions.get("columns").add((long)i);
            }
        }
    }

    public void getGalaxiesPositions() {
        int count = 1;
        for (int i = 0; i < galaxiesMap.size(); i++) {
            String line = galaxiesMap.get(i);
            for (int j = 0; j < line.length(); j++){
                if (Character.compare('#', line.charAt(j)) == 0) {
                    galaxiesPositions.put(count, new ArrayList<>(Arrays.asList((long)i, (long)j)));
                    count++;
                }
            }
        }
    }

    public void countEmpties(long row1, long column1, long row2, long column2) {
        emptyRows = 0;
        emptyColumns = 0;

        long rowMin = Math.min(row1, row2);
        long rowMax = Math.max(row1, row2);
        for (long i = rowMin + 1; i < rowMax; i++) {
            if (emptyLinesPositions.get("rows").contains(i)) emptyRows++;
        }

        long columnMin = Math.min(column1, column2);
        long columnMax = Math.max(column1, column2);
        for (long i = columnMin + 1; i < columnMax; i++) {
            if (emptyLinesPositions.get("columns").contains(i)) emptyColumns++;
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
                countEmpties(row1, column1, row2, column2);
                lengths += length + (emptyColumns + emptyRows) * (factor - 1);
            }
        }
    }

    public long getSumOfLengths(long multiple) {
        openFile();
        this.factor = multiple;
        getEmptyLinesPositions();
        getGalaxiesPositions();
        calculateLengths();
        return lengths;
    }

    public static void main(String[] args) {
        Day11Part2 d11 = new Day11Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day11/input.txt");
        System.out.println(d11.getSumOfLengths(1000000L));
    }
}
