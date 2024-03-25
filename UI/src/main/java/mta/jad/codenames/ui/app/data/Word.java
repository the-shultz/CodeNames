package mta.jad.codenames.ui.app.data;

public class Word {

    private final String word;
    private final Color color;

    public Word(String word, Color color) {
        this.word = word;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String get() {
        return word;
    }
}
