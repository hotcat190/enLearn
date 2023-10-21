package dictionary;

import java.sql.*;

public class Word {
    private String word="";
    private String pronunciation="";
    private String part_of_speech="";
    private String definition="";

    public Word(ResultSet resultSet) throws SQLException {
        word = resultSet.getString(1);
        pronunciation = resultSet.getString(2);
        part_of_speech = resultSet.getString(3);
        definition = resultSet.getString(4);
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

    public String getDefinition() {
        return definition;
    }
}
