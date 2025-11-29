import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CarCongestionAnalyze {
    public static void main(String[] args) {
        ArrayList<Integer> northbound = new ArrayList<>();
        ArrayList<Integer> southbound = new ArrayList<>();
        ArrayList<Integer> eastbound  = new ArrayList<>();
        ArrayList<Integer> westbound  = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Car Counter (c_s) - Sheet1.csv"))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first == true) {
                    first = false;
                } else {
                    int firstcomma =  line.indexOf(',');
                    int secondcomma = line.indexOf(',', firstcomma + 1);
                    int thirdcomma = line.indexOf(',', secondcomma + 1);

                    int north = Integer.parseInt(line.substring(0, firstcomma).trim());
                    int south = Integer.parseInt(line.substring(firstcomma + 1, secondcomma).trim());
                    int east = Integer.parseInt(line.substring(secondcomma + 1, thirdcomma).trim());
                    int west = Integer.parseInt(line.substring(thirdcomma + 1).trim());

                    northbound.add(north);
                    southbound.add(south);
                    eastbound.add(east);
                    westbound.add(west);
                }
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        read("Northbound", northbound);
        read("Southbound", southbound);
        read("Eastbound", eastbound);
        read("Westbound", westbound);
    }
    public static void read(String name, ArrayList<Integer> list) {
        System.out.println("-{" + name + "}-");
    }
    public static double average(ArrayList<Integer> list) {
        double sum = 0;
        for (double value : list) sum += value;
        return (double) sum / list.size();
    }
    


    }