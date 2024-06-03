public class DataPreprocessor {
    public String removeSpecialCharacters(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", " ");
    }

    public String lowerCase(String text) {
        return text.toLowerCase();
    }
}
