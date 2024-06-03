import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class Tokenization {

    public static void main(String[] args) throws Exception {
        List<List<String>> texts = new ArrayList<>();
        List<String> articleIds = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        List<String> categoryIds = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader("resources/BBC News Train.csv"))) {
            String[] lines;
            boolean isFirstRow = true;

            while ((lines = csvReader.readNext())!= null) {
                // skip header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                if (lines.length > 1) {
                    String articleId = lines[0]; // 'ArticleId' column
                    String text = lines[1]; // 'Text' column
                    String category = lines[2]; // 'Category' column
                    String categoryId = getCategoryID(category);

                    // Text preprocessing
                    text = DataPreprocessor.removeTags(text);
                    text = DataPreprocessor.lowerCase(text);
                    text = DataPreprocessor.removeSpecialCharacters(text);
                    text = DataPreprocessor.removeStopwords(text);

                    // Tokenization: split the text into words
                    List<String> tokens = Arrays.asList(text.split("\\s+"));

                    // Add each entry to respective lists
                    articleIds.add(articleId);
                    texts.add(tokens);
                    categories.add(category);
                    categoryIds.add(categoryId);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print dataset in the required format
        for (int i = 0; i < articleIds.size(); i++) {
            System.out.println(articleIds.get(i) + "\t" + getTextString(texts.get(i)) + "\t" + categories.get(i) + "\t" + categoryIds.get(i));
        }
    }

    // Helper method to convert list of tokens to a single string
    private static String getTextString(List<String> tokens) {
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            sb.append(token).append(" ");
        }
        return sb.toString().trim();
    }

    // Helper method to map category to CategoryId
    private static String getCategoryID(String category) {
        switch (category) {
            case "business":
                return "0";
            case "tech":
                return "1";
            case "entertainment":
                return "2";
            default:
                return "-1"; // Unknown category
        }
    }
}
