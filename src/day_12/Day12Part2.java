package day_12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day12Part2 {

    private String path;
    private List<String> springs = new ArrayList<>();
    private List<List<Integer>> groups = new ArrayList<>();
    private List<String> possibilities = new ArrayList<>();
    private Map<String, Boolean> cache = new HashMap<>();
    private long sum;

    public Day12Part2(String path) {
        this.path = path;
    }


    public void openFile(){
        try {
            List<String> input = Files.readAllLines(Path.of(path));
            for (int i = 0; i < input.size(); i++) {
                groups.add(new ArrayList<>());
                String[] splited = input.get(i).split(" ");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 5; j++) {
                    sb.append(splited[0]);
                    if (j < 4) sb.append("?");
                    groups.get(i).addAll(Arrays.stream(splited[1].split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()));
                }
                springs.add(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
//        System.out.println("springs: " + springs);
//        System.out.println("groups: " + groups);
    }

    public boolean checkIfIsPossible(String spring, List<Integer> groupList) {
//        System.out.println("Checking: " + spring);
//        System.out.println("GroupList: " + groupList);
        if (cache.containsKey(spring)) return cache.get(spring);
        int i = 0;
        List<Integer> nums = new ArrayList<>();
        while (i < spring.length()) {
            if (Character.compare(spring.charAt(i), '#') == 0) {
                int j = 0;
                while (i < spring.length() && Character.compare(spring.charAt(i), '#') == 0) {
                    i++;
                    j++;
                }
                nums.add(j);
            }
            i++;
        }
//        System.out.println("size nums: " + nums.size() + " size groups: " + groupList.size());
        if (nums.size() > groupList.size()) return false;
        for (int k = 0; k < nums.size(); k++) {
            if (k == nums.size() - 1) {
                if (nums.get(k) > groupList.get(k)) {
                    cache.put(spring, false);
                    return false;
                } else if (nums.get(k) < groupList.get(k) && !spring.endsWith(".")) {
                    return true;
                }
            }
            if(Integer.compare(nums.get(k), groupList.get(k)) != 0) {
                cache.put(spring, false);
                return false;
            }
        }
        cache.put(spring, true);
        return true;
    }
    public void checkCharacter(String spring, List<Integer> groupList) {
//        System.out.println("spring: " + spring);
        if (spring.contains("?")) {
//            String partial = spring.substring(0, spring.indexOf("?"));
            boolean result = checkIfIsPossible(spring.substring(0, spring.indexOf("?")), groupList);
//            System.out.println("result: " + result);
            if (result) {
                checkCharacter(spring.replaceFirst("\\?", "."), groupList);
                checkCharacter(spring.replaceFirst("\\?", "#"), groupList);
            } else {
                return ;
            }
        }

        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < spring.length(); i++) {
            if (Character.compare('#', spring.charAt(i)) == 0) {
                int j = 0;
                while ((j + i < spring.length()) && Character.compare('#', spring.charAt(j + i)) == 0) {
                    j++;
                }
                set.add(j);
                i += j - 1;
            }
        }
        if (!spring.contains("?") && set.equals(groupList)) {
//            System.out.println("answer: " + spring);
//            possibilities.add(spring);
            sum++;
            System.out.println("sum: " + sum);
        }
    }

    public long checkSprings() {
        for (int i = 0; i < springs.size(); i++) {
            System.out.println("LINHA: " + i);
            cache.clear();
            System.gc();
//            System.out.println("Spring " + (i + 1) + "------------------------");
            checkCharacter(springs.get(i), groups.get(i));
//            sum += possibilities.size();
//            possibilities.clear();
        }
        System.out.println(sum);
        return sum;
    }


    public static void main(String[] args) {
        Day12Part2 d12 = new Day12Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/input.txt");
        d12.openFile();
        long partialSum1 = d12.checkSprings();
        System.out.println("RESULT: " + partialSum1);
//        System.gc();
//        Day12Part2 d122 = new Day12Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/input2.txt");
//        d122.openFile();
//        long partialSum2 = partialSum1 + d122.checkSprings();
//        System.gc();
//        Day12Part2 d123 = new Day12Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/input3.txt");
//        d123.openFile();
//        long totalSum = partialSum2 + d123.checkSprings();
//        System.out.println("TOTAL: " + totalSum);
    }
}
