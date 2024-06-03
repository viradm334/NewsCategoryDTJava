import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import java.util.List;

public class NewsCategorization {

    public static void main(String[] args) throws Exception {
        String filePath = "resources/BBC News Train.csv"; // File path to the CSV
        double testPercentage = 30.0; // Percentage of data to be used for testing
        
        // Read and preprocess the data
        ArrayList<String[]> data = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] lines;
            boolean isFirstRow = true;
            
            while ((lines = csvReader.readNext()) != null) {
                // Skip header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                // Process each field in the row
                if (lines.length > 1) {
                    lines[1] = DataPreprocessor.removeTags(lines[1]);
                    lines[1] = DataPreprocessor.lowerCase(lines[1]);
                    lines[1] = DataPreprocessor.removeSpecialCharacters(lines[1]);
                    lines[1] = DataPreprocessor.removeStopwords(lines[1]);
                }
                data.add(lines); // Add each processed row to the ArrayList
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
                String articleId = row[0];
                String originalText = row[1];
                String category = row[2];

                String textWithoutTags = DataPreprocessor.removeTags(originalText);
                String cleanText = DataPreprocessor.removeStopwords(textWithoutTags);

                System.out.println("ArticleId: " + articleId);
                System.out.println("Category: " + category);
                System.out.println("Original Text: " + originalText);
                System.out.println("Text without HTML tags: " + textWithoutTags);
                System.out.println("Cleaned Text: " + cleanText);
                System.out.println();

                count++;
            } else {
                break; // Stop iterating after printing three records
            }
        }

        // Print the entire dataset
        System.out.println("Entire Dataset:");
        System.out.println("\tArticleId\tText\tCategory\tCategoryId");
        for (String[] row : data) {
            System.out.print("\t");
            for (String field : row) {
                System.out.print(field + "\t");
            }
            System.out.println();
        }

        // Split the dataset into training and test sets
        List<List<String>>[] splitData = DataSplitter.splitDataset(filePath, testPercentage);

        List<List<String>> trainData = splitData[0];
        List<List<String>> testData = splitData[1];

        // Print lengths of training and test sets
        System.out.println("Train : " + trainData.size());
        System.out.println("Test : " + testData.size());

        // Continue with your classification tasks using trainData and testData
    }
}
