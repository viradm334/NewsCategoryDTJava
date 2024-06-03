import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataSplitter {

    public static void main(String[] args) {
        // Add code here to split the dataset into training and test sets
    }

    public static List<List<String>>[] splitDataset(String filePath, double testPercentage) {
        List<List<String>> trainData = new ArrayList<>();
        List<List<String>> testData = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] lines;
            boolean isFirstRow = true;

            while ((lines = csvReader.readNext()) != null) {
                // skip header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                // Process each row
                List<String> row = new ArrayList<>();
                for (String line : lines) {
                    row.add(line);
                }

                // Randomly assign to training or test set
                if (new Random().nextDouble() < testPercentage / 100.0) {
                    testData.add(row);
                } else {
                    trainData.add(row);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        // Return the split data
        List<List<String>>[] splitData = new List[2];
        splitData[0] = trainData;
        splitData[1] = testData;
        return splitData;
    }
}
