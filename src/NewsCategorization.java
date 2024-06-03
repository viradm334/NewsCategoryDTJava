import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;

public class NewsCategorization {
    public static void main(String[] args) throws Exception {
        ArrayList<String[]> data = new ArrayList<>(); // ArrayList to store the data
        
        try (CSVReader csvReader = new CSVReader(new FileReader("resources/BBC News Train.csv"))) {
            String[] lines;
            while ((lines = csvReader.readNext()) != null) {
                data.add(lines); // Add each row to the ArrayList
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Print the first three records from the ArrayList
        int count = 0;
        for (String[] row : data) {
            if (count < 3) {
                for (String value : row) {
                    System.out.print(value + "**");
                }
                System.out.println();
                count++;
            } else {
                break; // Stop iterating after printing three records
            }
        }
    }
}
