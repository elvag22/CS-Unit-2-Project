import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;


public class CarCongestionAnalyze {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the car congestion analysis program, would you like to learn abiut car congestion before we start? (yes/no)\nUser:");
        String LearnChoice = sc.nextLine().trim().toLowerCase();
         if (LearnChoice.equals("yes")){
             System.out.println("Traffic congestion occurs when too many cars use the same road at the same time. This causes delays, sfaety hazards, and decreased productivity. \nUsually,traffic managers would use IR sensors to sense how many cars are in the same road");
         }
         else if (LearnChoice.equals("no")){
             System.out.println("Okay, let's continue to the next step");
         }
         else{
             System.out.println("Invalid input. Try again");
         }
        System.out.println("Would you like to start analyzing current traffic? (yes/no) \nUser:");
         String analyzeChoice = sc.nextLine().trim().toLowerCase();
         if (analyzeChoice.equals("yes")){
             System.out.println("Below is the current analysis at North,South,West,and Easr bounds");
         }
         else if (analyzeChoice.equals("no")){
             System.out.println("Thank you for using this program, safe driving!");
             return;
         }
         else {
             System.out.println("Invalid input. Try again");
             return;
         }
        ArrayList<Integer> northbound = new ArrayList<>();
        ArrayList<Integer> southbound = new ArrayList<>();
        ArrayList<Integer> eastbound = new ArrayList<>();
        ArrayList<Integer> westbound = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Car Counter (c_s) - Sheet1.csv"))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first == true) {
                    first = false;
                } else {
                    int firstcomma = line.indexOf(',');
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

        createGraph("Northbound cars", northbound);
        createGraph("Southbound cars", southbound);
        createGraph("Eastbound cars", eastbound);
        createGraph("Westbound cars", westbound);
    }

    public static void read(String name, ArrayList<Integer> list) {
        System.out.println("-{" + name + "}-");
        double average = average(list);
        System.out.println("Average of cars is: " + average);
        if (average > 30)
            System.out.println("Traffic Level is high right now");
        else
            System.out.println("Traffic Level is low right now");
        double slope = slope(list);
        System.out.println("The trend of cars right now is " + slope);

        System.out.println("Predicted next 5 minutes:");
        predicton(list, slope);
        lightsuggestion(average, slope);


    }

    public static double average(ArrayList<Integer> list) {
        double sum = 0;
        for (double value : list) sum += value;
        return (double) sum / list.size();
    }

    public static double slope(ArrayList<Integer> list) {
        int lastnum = list.get(list.size() - 1);
        int secondlastnum = list.get(list.size() - 2);
        return (secondlastnum - lastnum);

    }

    public static void predicton(ArrayList<Integer> list, double slope) {
        int lastNum = list.get(list.size() - 1);
        for (int i = 1; i <= 5; i++) {
            double predicted = lastNum + (slope * i);
            System.out.println("Predicted cars in " + i + " minutes: " + predicted);

        }
    }

    public static void lightsuggestion(double average, double slope) {
        int greenLight;
        if (average >= 35) greenLight = 50;
        else if (average >= 25) greenLight = 40;
        else greenLight = 25;
        System.out.println("Recommended green light is: " + greenLight + " seconds");
    }

    public static void createGraph(String name, ArrayList<Integer> list) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < list.size(); i++) {
            dataset.addValue(list.get(i), name, "T" + (i + 1));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                name + " Traffic per minute",
                "Time",
                "Number of Cars",
                dataset
        );

        javax.swing.JFrame frame = new javax.swing.JFrame(name + " Chart");
        frame.setContentPane(new ChartPanel(chart));
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}


