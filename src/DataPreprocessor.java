import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataPreprocessor {
    private static final List<String> STOPWORDS = Arrays.asList(
        "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", 
        "are", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", 
        "between", "both", "but", "by", "can't", "cannot", "could", "couldn't", "did", "didn't", 
        "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few", "for", 
        "from", "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", 
        "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", 
        "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", 
        "isn't", "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", 
        "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", 
        "ought", "our", "ours", "ourselves", "out", "over", "own", "same", "shan't", "she", 
        "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such", "than", "that", 
        "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", 
        "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", 
        "to", "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", 
        "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", 
        "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would",
        "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves"
    );

    public static String removeTags(String text) {
        return text.replaceAll("<[^>]*>", "");
    }

    public static String removeStopwords(String text) {
        List<String> words = new ArrayList<>(Arrays.asList(text.split("\\s+")));
        words.removeAll(STOPWORDS);
        return String.join(" ", words);
    }
}
