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

    public void checkCharacter(String spring, List<Integer> groupList) {
//        System.out.println("spring: " + spring);
        if (spring.contains("?")) {
            checkCharacter(spring.replaceFirst("\\?", "."), groupList);
            checkCharacter(spring.replaceFirst("\\?", "#"), groupList);
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
        if (!spring.contains("?") && set.equals(groupList)) possibilities.add(spring);
        System.out.println("spring: " + spring);
    }

    public void checkSprings() {
        for (int i = 0; i < springs.size(); i++) {
//            System.out.println("Spring " + (i + 1) + "------------------------");
            checkCharacter(springs.get(i), groups.get(i));
        }
        System.out.println(possibilities.size());
    }


    public static void main(String[] args) {
        Day12Part2 d12 = new Day12Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/simpleInput.txt");
        d12.openFile();
        d12.checkSprings();
    }
}
