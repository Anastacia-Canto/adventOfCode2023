package day_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5Part2 {

    private String path;

    private Long lowestLocation;
    private String initialSeeds;
    private ArrayList<String> seedToSoil = new ArrayList<>();
    private ArrayList<String> soilToFertilizer = new ArrayList<>();
    private ArrayList<String> fertilizerToWater = new ArrayList<>();
    private ArrayList<String> waterToLight = new ArrayList<>();
    private ArrayList<String> lightToTemperature = new ArrayList<>();
    private ArrayList<String> temperatureToHumidity = new ArrayList<>();
    private ArrayList<String> humidityToLocation = new ArrayList<>();


    public Day5Part2(String path) {
        this.path = path;
    }

    public void getSeeds() {
        List<String> stringNumbers = Arrays.asList((initialSeeds.substring(initialSeeds.indexOf(':') + 2, initialSeeds.length()).split(" ")));
        for (int i = 0; i < stringNumbers.size(); i++) {
            long range = Long.parseLong(stringNumbers.get(i + 1));
            for (int j = 0; j < range; j++) {
                long location = getLocation(Long.parseLong(stringNumbers.get(i)) + j);
                if (lowestLocation == null || location < lowestLocation) lowestLocation = location;
            }
            i++;
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
                    initialSeeds = line;
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
            if (infoList.get(1) <= source && (source < (infoList.get(1) + infoList.get(2)))) {
                return infoList.get(0) + (source - infoList.get(1));
            }
        }
        return source;
    }

    public long getLocation(long seed) {
        long soil = getDestination(seed, seedToSoil);
//            System.out.println("soil: " + soil);
        long fertilizer = getDestination(soil, soilToFertilizer);
//            System.out.println("fertilizer: " + fertilizer);
        long water = getDestination(fertilizer, fertilizerToWater);
//            System.out.println("water: " + water);
        long light = getDestination(water, waterToLight);
//            System.out.println("light: " + light);
        long temperature = getDestination(light, lightToTemperature);
//            System.out.println("temperature: " + temperature);
        long humidity = getDestination(temperature, temperatureToHumidity);
//            System.out.println("humidity: " + humidity);
        return getDestination(humidity, humidityToLocation);
    }

    public long getLowestLocation() {
        openFile();
        getSeeds();
        return lowestLocation;
    }

    public static void main(String[] args) {
        Day5Part2 d5 = new Day5Part2("/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/test1.txt");
        System.out.println(d5.getLowestLocation());
    }
}
