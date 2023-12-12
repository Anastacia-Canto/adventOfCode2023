package day_12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {

    private String path;
    private List<String> springs = new ArrayList<>();
    private List<List<Integer>> groups = new ArrayList<>();
    private List<String> possibilities = new ArrayList<>();

    public Day12(String path) {
        this.path = path;
    }

    public void openFile(){
        try {
            List<String> input = Files.readAllLines(Path.of(path));
            for (int i = 0; i < input.size(); i++) {
                String[] splited = input.get(i).split(" ");
                springs.add(splited[0]);
                groups.add(new ArrayList<>());
                groups.get(i).addAll(Arrays.stream(splited[1].split(","))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()));
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }

    public void checkCharacter(String spring, List<Integer> groupList) {

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

    }

    public int checkSprings() {
        openFile();
        for (int i = 0; i < springs.size(); i++) {
            checkCharacter(springs.get(i), groups.get(i));
        }
       return possibilities.size();
    }



    public static void main(String[] args) {
        Day12 d12 = new Day12("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_12/simpleInput.txt");
        System.out.println(d12.checkSprings());
    }
}
