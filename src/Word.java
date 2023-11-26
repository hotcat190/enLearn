public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    public Word(String target, String explain) {
        this.word_target = target;
        this.word_explain = explain;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Word) {
            Word temp = (Word) o;
            return this.word_target.equals(temp.word_target);
        }
        return false;
    }

    @Override
    public int compareTo(Word other) {
        return this.word_target.compareToIgnoreCase(other.getWord_target());
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
}
