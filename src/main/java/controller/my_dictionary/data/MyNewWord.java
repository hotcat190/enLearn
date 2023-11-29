package controller.my_dictionary.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class MyNewWord {
    private final SimpleIntegerProperty order;
    private final SimpleStringProperty word;
    private final SimpleStringProperty pronunciation;
    private final SimpleStringProperty update;
    private final SimpleObjectProperty<Date> date;
    private final SimpleObjectProperty<Time> hour;
    private final SimpleStringProperty definition;
    private final SimpleStringProperty hourFormat;
    private final SimpleStringProperty edit;
    public MyNewWord(Integer order, String word, String pronunciation, Date update, Time hour, String definition) {
        this.order = new SimpleIntegerProperty(order);
        this.word = new SimpleStringProperty(word);
        this.pronunciation = new SimpleStringProperty(pronunciation);
        this.date = new SimpleObjectProperty<>(update);
        this.hour = new SimpleObjectProperty<>(hour);
        this.definition = new SimpleStringProperty(definition);
        this.update = new SimpleStringProperty(new SimpleDateFormat("dd MMM, yyyy").format(date.get()));
        this.hourFormat = new SimpleStringProperty(hour.toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a")));
        this.edit = new SimpleStringProperty("");
    }

    /**
     * Constructor deep copy.
     */
    public MyNewWord(MyNewWord myNewWord) {
        this.order = new SimpleIntegerProperty(0);
        this.word = new SimpleStringProperty(myNewWord.getWord());
        this.pronunciation = new SimpleStringProperty(myNewWord.getPronunciation());
        this.definition = new SimpleStringProperty(myNewWord.getDefinition());
        this.update = new SimpleStringProperty(myNewWord.getUpdate());
        this.hourFormat = new SimpleStringProperty(myNewWord.getHour());
        this.edit = new SimpleStringProperty("");
        this.date = null;
        this.hour = null;
    }


    public String getWord() {
        return word.get();
    }

    public String getPronunciation() {
        return pronunciation.get();
    }

    public String getUpdate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        return dateFormat.format(date.get());
    }


    public String getDefinition() {
        return definition.get();
    }

    public void setOrder(int order) {
        this.order.set(order);
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation.set(pronunciation);
    }

    public void setUpdate(Date date) {
        this.update.set(new SimpleDateFormat("dd MMM, yyyy").format(date));
    }

    public void setDefinition(String definition) {
        this.definition.set(definition);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyNewWord myNewWord)) return false;
        return this.getWord().equals(myNewWord.getWord()) &&
                this.getPronunciation().equals(myNewWord.getDefinition());
    }

    public String getHour() {
        return hourFormat.get();
    }

    public void setHour(Time time) {
        this.hour.set(time);
        this.hourFormat.set(time.toLocalTime().format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    public SimpleStringProperty hourProperty() {
        return hourFormat;
    }

    public SimpleObjectProperty<Time> getTime() {
        return hour;
    }

    public SimpleObjectProperty<Date> getDate() {
        return date;
    }

    public String getEdit() {
        return edit.get();
    }
}
