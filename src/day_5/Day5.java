package day_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day5 {

    private String path;

    private ArrayList<Integer> listOfLocations = new ArrayList<>();
    private ArrayList<Integer> seeds = new ArrayList<>();
    private ArrayList<String> seedToSoil = new ArrayList<>();
    private ArrayList<String> soilToFertilizer = new ArrayList<>();
    private ArrayList<String> fertilizerToWater = new ArrayList<>();
    private ArrayList<String> waterToLight = new ArrayList<>();
    private ArrayList<String> lightToTemperature = new ArrayList<>();
    private ArrayList<String> temperatureToHumidity = new ArrayList<>();
    private ArrayList<String> humidityToLocation = new ArrayList<>();


    public Day5(String path) {
        this.path = path;
    }

    public void getSeeds(String line) {
        List<String> stringNumbers = Arrays.asList((line.substring(line.indexOf(':') + 1, line.length()).split(" ")));
        for (String num : stringNumbers) {
            seeds.add(Integer.parseInt(num));
        }
    }

    public void getInfo(BufferedReader buffer, ArrayList<String> list) {
        try {
            String line;
            while (((line = buffer.readLine()) != null) && Character.isDigit(line.charAt(0))) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }

    }
    public void openFile() {
        try {
            File inputFile = new File(path);
            BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = buffer.readLine()) != null) {
                if (line.isEmpty()) continue;
                else if (line.contains("seeds:")) {
                    getSeeds(line);
                } else if (line.contains("seed-to-soil")) {
                    getInfo(buffer, seedToSoil);
                } else if (line.contains("soil-to-fertilizer")) {
                    getInfo(buffer, soilToFertilizer);
                } else if (line.contains("fertilizer-to-water")) {
                    getInfo(buffer, fertilizerToWater);
                } else if (line.contains("water-to-light")) {
                    getInfo(waterToLight);
                } else if (line.contains("light-to-temperature")) {
                    getInfo(lightToTemperature);
                } else if (line.contains("temperature-to-humidity")) {
                    getInfo(temperatureToHumidity);
                } else if (line.contains("humidity-to-location")) {
                    getInfo();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }


    }

    public int getLocation() {

        return 0;
    }

    public int getLowestLocation() {
        Collections.sort(listOfLocations);
        return listOfLocations.get(0);
    }

    public static void main(String[] args) {
        Day5 d5 = new Day5("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/simpleInput.txt");
        System.out.println(d5.getLowestLocation());
    }
}
