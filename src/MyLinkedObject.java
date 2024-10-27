import java.util.Arrays;

public class MyLinkedObject {
    private String[] words;
    private int count;
    private MyLinkedObject next;

    public MyLinkedObject(String[] words) {
        this.words = words;
        this.count = 1;
        this.next = null;
    }

    public void setWords(String[] words) {
        if (Arrays.equals(words, this.words)) {
            count++;
        } else {
            if (next == null || words[words.length - 1].compareTo(next.words[next.words.length - 1]) < 0) {
                MyLinkedObject newObject = new MyLinkedObject(words);
                newObject.next = next;
                next = newObject;
            } else {
                next.setWords(words);
            }
        }
    }

    public String[] getWords() {
        return words;
    }

    public void setNext(MyLinkedObject next) {
        this.next = next;
    }

    public MyLinkedObject getNext() {
        return next;
    }

    public int getCount() {
        return count;
    }
}
