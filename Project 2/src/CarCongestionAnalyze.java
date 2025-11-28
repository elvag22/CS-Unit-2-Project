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

                    int north = Integer.parseInt(line.substring(0, firstcomma)

                }

            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}