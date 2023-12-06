package day_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5Part2Reverse {

    private String path;

    private Long lowestLocation;
    private String initialSeeds;
    private Long initialLocation;
    private ArrayList<String> seedToSoil = new ArrayList<>();
    private ArrayList<String> soilToFertilizer = new ArrayList<>();
    private ArrayList<String> fertilizerToWater = new ArrayList<>();
    private ArrayList<String> waterToLight = new ArrayList<>();
    private ArrayList<String> lightToTemperature = new ArrayList<>();
    private ArrayList<String> temperatureToHumidity = new ArrayList<>();
    private ArrayList<String> humidityToLocation = new ArrayList<>();


    public Day5Part2Reverse(Long initialLocation, String path) {
        this.initialLocation = initialLocation;
        this.path = path;
    }

    public void checkSeed() {
        long location =  initialLocation;
        List<String> stringNumbers = Arrays.asList((initialSeeds.substring(initialSeeds.indexOf(':') + 2, initialSeeds.length()).split(" ")));

        while (true) {
            long seed = getSeed(location);
            for (int i = 0; i < stringNumbers.size(); i++) {
                long range = Long.parseLong(stringNumbers.get(i + 1));
                long start = Long.parseLong(stringNumbers.get(i));
                long end = start + range;
                if (seed >= start && seed <= end) {
                    lowestLocation = location;
                    return;
                }
                i++;
            }
            location++;
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

    public long getSource(long destination, ArrayList<String> mapToSource) {
        for (String info : mapToSource) {
            List<Long> infoList = Arrays.asList(info.split(" ")).stream().map(Long::valueOf).collect(Collectors.toList());
            if (infoList.get(0) <= destination && (destination < (infoList.get(0) + infoList.get(2)))) {
                return destination - infoList.get(0) + infoList.get(1);
            }
        }
        return destination;
    }

    public long getSeed(long location) {
        long humidity = getSource(location, humidityToLocation);
        long temperature = getSource(humidity, temperatureToHumidity);
        long light = getSource(temperature, lightToTemperature);
        long water = getSource(light, waterToLight);
        long fertilizer = getSource(water, fertilizerToWater);
        long soil = getSource(fertilizer, soilToFertilizer);
        return getSource(soil, seedToSoil);
    }

    public long getLowestLocation() {
        openFile();
        checkSeed();
        return lowestLocation;
    }

    public static void main(String[] args) {
        Day5Part2Reverse d5 = new Day5Part2Reverse((long)1000000, "/home/anastacia/IdeaProjects/AdventOfCode2023/src/day_5/input.txt");
        System.out.println("lowest location: " + d5.getLowestLocation());
    }
}
