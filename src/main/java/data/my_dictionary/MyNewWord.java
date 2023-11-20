package data.my_dictionary;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class MyNewWord {
    private final SimpleIntegerProperty order;
    private final SimpleStringProperty word;
    private final SimpleStringProperty pronunciation;
    private final SimpleStringProperty update;
    private final SimpleObjectProperty<Date> date;
    private final SimpleStringProperty definition;

    public MyNewWord(Integer order, String word, String pronunciation, Date update, String definition) {
        this.order = new SimpleIntegerProperty(order);
        this.word = new SimpleStringProperty(word);
        this.pronunciation = new SimpleStringProperty(pronunciation);
        this.date = new SimpleObjectProperty<>(update);
        this.definition = new SimpleStringProperty(definition);
        this.update = new SimpleStringProperty(new SimpleDateFormat("dd MMM, yyyy").format(date.get()));
    }

    public Integer getOrder() {
        return order.get();
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

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     *
     * <p>
     * An equivalence relation partitions the elements it operates on
     * into <i>equivalence classes</i>; all the members of an
     * equivalence class are equal to each other. Members of an
     * equivalence class are substitutable for each other, at least
     * for some purposes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @implSpec The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * In other words, under the reference equality equivalence
     * relation, each equivalence class only has a single element.
     * @apiNote It is generally necessary to override the {@link #hashCode() hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyNewWord myNewWord)) return false;
        return this.getWord().equals(myNewWord.getWord()) &&
                this.getPronunciation().equals(myNewWord.getDefinition());
    }
}
