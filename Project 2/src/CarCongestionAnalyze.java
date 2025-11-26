import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CarCongestionAnalyze {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Car Counter (c_s) - Sheet1.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}