package dictionary;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Word {
    private String word = "";
    private String pronunciation = "";
    private String part_of_speech = "";
    private final ArrayList<String> pastTenseList = new ArrayList<>();
    private final HashMap<String, String> hashMap = new HashMap<>();

    public Word(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            word = resultSet.getString(1);
            pronunciation = resultSet.getString(2);
            part_of_speech = resultSet.getString(3);
            part_of_speech = decodePOS();
            String definition = resultSet.getString(4);
            definition = definition.replaceAll("[=]", "• ");
            definition = definition.replaceAll("[+]", ":");
            definition = definition.replaceAll("[\n][!]", "\n▶ ");
            if (!definition.isEmpty()) {
                hashMap.put(part_of_speech, definition);
            }
        }
    }

    public String getWord() {
        return word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getPart_of_speech() {
        return part_of_speech;
    }

    public String getDefinition(String part_of_speech) {
        return hashMap.get(part_of_speech);
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    private String decodePOS() {
        String pos = part_of_speech;
        if (pos.contains("danh từ")) return "noun";
        if (pos.contains("động từ") || pos.contains("nội động từ")) return "verb";
        if (pos.contains("tính từ")) return "adjective";
        if (pos.contains("giới từ")) return "preposition";
        if (pos.contains("liên từ")) return "conjunction";
        if (pos.contains("trạng từ") || pos.contains("phó từ")) return "adverb";
        if (pos.contains("đại từ")) return "pronoun";
        if (pos.contains("thán từ")) return "interjection";
        return "more";
    }
}