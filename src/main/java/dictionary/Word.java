package dictionary;

import java.sql.*;
import java.util.*;
import java.util.stream.Stream;

public class Word {
    private final String word ;
    private String pronunciation = "";
    private String part_of_speech = "";
    private final ArrayList<String> pastTenseList = new ArrayList<>();
    private final HashMap<String, String> hashMap = new HashMap<>();
    private final HashMap<String, List<String>> linesDefHashMap=new HashMap<>();
    private final ArrayList<String> synonymsList ;
    private final ArrayList<String> antonymsList ;
    private String priorityDefinition;

    private boolean isIrregularVerb=false;
    public Word(String word) throws SQLException {
        ResultSet resultSet = Dictionary.getWord("^" + word.trim() + " $");
        this.word = word;
        while (resultSet.next()) {
            pronunciation = resultSet.getString(2);
            part_of_speech = resultSet.getString(3);
            part_of_speech = decodePOS();
            String definition = resultSet.getString(4);
            definition = definition.replaceAll("[=]", "• ");
            definition = definition.replaceAll("[+]", ":");
            definition = definition.replaceAll("[\n][!]", "\n▶ ");
            if (!definition.isEmpty()) {
                hashMap.put(part_of_speech, definition);
                linesDefHashMap.put(part_of_speech, definition.lines().toList());
                priorityDefinition = linesDefHashMap.get(part_of_speech).get(0);
            }
        }
        if(Dictionary.getIrregularVerb(word)!=null) {
            pastTenseList.addAll(Dictionary.getIrregularVerb(word));
            isIrregularVerb = true;
        }
        antonymsList = Dictionary.getAntonyms(word);
        synonymsList = Dictionary.getSynonyms(word);
    }

    public String getWord() {
        return word;
    }

    public String getPronunciation() {
        return pronunciation;
    }


    public List<String> getLinesDefinitionOf(String part_of_speech) {
        return linesDefHashMap.get(part_of_speech);
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public ArrayList<String> getPastTenseList() {
        return pastTenseList;
    }

    public boolean isIrregularVerb() {
        return isIrregularVerb;
    }

    public ArrayList<String> getSynonymsList() {
        return synonymsList;
    }

    public ArrayList<String> getAntonymsList() {
        return antonymsList;
    }

    public String getPriorityDefinition() {
        return priorityDefinition;
    }

    private String decodePOS() {
        String pos = part_of_speech;
        if (pos.contains("danh từ")) return "noun";
        if (pos.contains("động từ") || pos.contains("nội động từ")) return "verb";
        if (pos.contains("tính từ")) return "adj";
        if (pos.contains("giới từ")) return "prep";
        if (pos.contains("liên từ")) return "conj";
        if (pos.contains("trạng từ") || pos.contains("phó từ")) return "adv";
        if (pos.contains("đại từ")) return "pron";
        if (pos.contains("thán từ")) return "inter";
        return "more";
    }
}