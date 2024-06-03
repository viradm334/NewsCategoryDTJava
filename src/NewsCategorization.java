import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;

public class NewsCategorization {
    public static void main(String[] args) throws Exception {
        ArrayList<String[]> data = new ArrayList<>(); // ArrayList to store the data
        DataPreprocessor preprocessor = new DataPreprocessor();
        
        try (CSVReader csvReader = new CSVReader(new FileReader("resources/BBC News Train.csv"))) {
            String[] lines;
            boolean isFirstRow = true;
            
            while ((lines = csvReader.readNext()) != null) {
                // skip header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                // Process each field in the row
                if (lines.length > 1) {
                    lines[1] = preprocessor.removeSpecialCharacters(lines[1]);
                    lines[1] = preprocessor.lowerCase(lines[1]);
                }
                data.add(lines); // Add each processed row to the ArrayList
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the 'Text' column of the second row for verification
        if (data.size() > 1) {
            String[] secondRow = data.get(1);
            System.out.println(secondRow[1]);
        }
    }
}
