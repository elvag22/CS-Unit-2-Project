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
            while ((line = br.readLine()) != null) {

            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}