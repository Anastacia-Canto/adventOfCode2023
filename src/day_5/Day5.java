package day_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    private String path;

    private ArrayList<Long> listOfLocations = new ArrayList<>();
    private ArrayList<Long> seeds = new ArrayList<>();
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
        List<String> stringNumbers = Arrays.asList((line.substring(line.indexOf(':') + 2, line.length()).split(" ")));
        for (String num : stringNumbers) {
            seeds.add(Long.parseLong(num));
        }
    }

    public void getInfo(BufferedReader buffer, ArrayList<String> list) {
        try {
            String line = buffer.readLine();
            while (line != null && !line.isEmpty() && Character.isDigit(line.charAt(0))) {
                list.add(line);
                line = buffer.readLine();
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
                    getInfo(buffer, waterToLight);
                } else if (line.contains("light-to-temperature")) {
                    getInfo(buffer, lightToTemperature);
                } else if (line.contains("temperature-to-humidity")) {
                    getInfo(buffer, temperatureToHumidity);
                } else if (line.contains("humidity-to-location")) {
                    getInfo(buffer, humidityToLocation);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }


    }

    public long getDestination(long source, ArrayList<String> mapToDestination) {
        for (String info : mapToDestination) {
            List<Long> infoList = Arrays.asList(info.split(" ")).stream().map(Long::valueOf).collect(Collectors.toList());
            if (infoList.get(1) < source && (source < (infoList.get(1) + infoList.get(2)))) {
                return infoList.get(0) + (source - infoList.get(1));
            }
        }
        return source;
    }

    public void getLocation() {
        for (long seed : seeds) {
            long soil = getDestination(seed, seedToSoil);
            long fertilizer = getDestination(soil, soilToFertilizer);
            long water = getDestination(fertilizer, fertilizerToWater);
            long light = getDestination(water, waterToLight);
            long temperature = getDestination(light, lightToTemperature);
            long humidity = getDestination(temperature, temperatureToHumidity);
            listOfLocations.add(getDestination(humidity, humidityToLocation));
        }
    }

    public long getLowestLocation() {
        openFile();
        getLocation();
        Collections.sort(listOfLocations);
        return listOfLocations.get(0);
    }

    public static void main(String[] args) {
        Day5 d5 = new Day5("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/input.txt");
        System.out.println(d5.getLowestLocation());
    }
}
