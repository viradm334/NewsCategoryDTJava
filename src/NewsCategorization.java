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
            boolean isFirstRow = true;
            
            while ((lines = csvReader.readNext()) != null) {
                // skip header row
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
    }
}