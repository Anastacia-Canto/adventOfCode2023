package day_13;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static file_manager.OpenFile.open;

public class Day13 {

    List<String> input = new ArrayList<>();
    List<String> pattern = new ArrayList<>();
    private long columns;
    private long rows;
    public Day13(String path) {
        input = open(path);
    }

    public void splitPattern() {
        pattern.clear();
        ListIterator<String> iterator = input.listIterator();
        while (iterator.hasNext()){
            String line = iterator.next();
            if (line.isEmpty()) {
                iterator.remove();
                break;
            }
            pattern.add(line);
            iterator.remove();
        }
    }

    public int checkVerticalReflection(int left, int right) {
        int limit = Math.min((pattern.get(0).length() - right), right);
        for (String line : pattern) {
            for (int i = 0; i < limit; i++) {
                if(Character.compare(line.charAt(left - i), line.charAt(right + i)) != 0) {
                    return -1;
                }
            }
        }
        return right;
    }

    public int lookForVerticalReflection() {
        int columnsBefore = 0;
        String line = pattern.get(0);
        for (int i = 0; i < line.length() - 1; i++) {
            if (Character.compare(line.charAt(i), line.charAt(i + 1)) == 0) {
                columnsBefore = checkVerticalReflection(i, i + 1);
                if (columnsBefore != -1) break;
            }
        }
        return columnsBefore == -1 ? 0 : columnsBefore;
    }

    public int checkHorizontalReflection(int up, int down) {
        int limit = Math.min((pattern.size() - down), down);
        int i = 1;
        for (; i < limit; i++) {
            if (!pattern.get(up - i).equals(pattern.get(down + i))) return -1;
        }
        return down;
    }

    public int lookForHorizontalReflection() {
        List<Integer> rowsBefore =  new ArrayList<>();
        String before = pattern.get(0);
        int i = 1;
        for (; i < pattern.size(); i++) {
            if (pattern.get(i).equals(before)) {
                if (i == 1) {
                    rowsBefore.add(1);
                    break;
                }
                rowsBefore.add(checkHorizontalReflection(i - 1, i));
            }
            before = pattern.get(i);
        }

        if (rowsBefore.size() == 0) return 0;

        int result = -1;
        for (int row : rowsBefore) {
            if (row != -1) result = row;
        }
        return result > 0 ? result : 0;
    }

    public long findReflection() {
        while (!input.isEmpty()) {
            splitPattern();
            columns += lookForVerticalReflection();
            rows += lookForHorizontalReflection();
        }
        return columns + 100 * rows;
    }

    public static void main(String[] args) {
        Day13 d13 = new Day13("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_13/input.txt");
        d13.input.stream().forEach(System.out::println);
        System.out.println(d13.findReflection());
    }

}
