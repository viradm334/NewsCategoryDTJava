import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataSplitter {

    public static void main(String[] args) {
        List<List<String>> texts = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        
        // Load and preprocess the dataset
        try (BufferedReader br = new BufferedReader(new FileReader("resources/BBC News Train.csv"))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                // Skip header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String text = parts[1]; // 'Text' column
                    String category = parts[2]; // 'Category' column

                    // Text preprocessing
                    text = DataPreprocessor.removeTags(text);
                    text = DataPreprocessor.lowerCase(text);
                    text = DataPreprocessor.removeSpecialCharacters(text);
                    text = DataPreprocessor.removeStopwords(text);

                    // Tokenization: split the text into words
                    List<String> tokens = Arrays.asList(text.split("\\s+"));

                    texts.add(tokens);
                    categories.add(category);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert texts to bag of words representation
        // Here, you would typically use a CountVectorizer or a similar technique to convert texts to numerical features

        // Now, let's split the dataset into training and test sets
        double testSize = 0.3;
        Random random = new Random();
        List<List<String>> xTrain = new ArrayList<>();
        List<List<String>> xTest = new ArrayList<>();
        List<String> yTrain = new ArrayList<>();
        List<String> yTest = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            if (random.nextDouble() < testSize) {
                xTest.add(texts.get(i));
                yTest.add(categories.get(i));
            } else {
                xTrain.add(texts.get(i));
                yTrain.add(categories.get(i));
            }
        }

        // Print lengths of training and test sets
        System.out.println("Train: " + xTrain.size());
        System.out.println("test: " + xTest.size());
    }
}
