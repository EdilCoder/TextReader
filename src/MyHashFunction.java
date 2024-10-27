import java.util.Random;

public abstract class MyHashFunction {

    protected int hashTableSize;

    public MyHashFunction(int size) {
        this.hashTableSize = size;
    }

    public abstract int hash(String word);
}


class DivisionMethodHash extends MyHashFunction {

    public DivisionMethodHash(int size) {
        super(size);
    }

    @Override
    public int hash(String word) {

        //Get the Unicode code of the first letter of the word sequence.
        int firstLetter = word.charAt(0);

        //Use division hashing to compute the remainder as a hash value
        int divisionMethodHash = firstLetter % hashTableSize;
        return divisionMethodHash;
    }
}

class  DivisionMethodHash2 extends MyHashFunction {

    public DivisionMethodHash2(int size) {
        super(size);
    }

    @Override
    public int hash(String word) {

        //Here the Unicode sum of the words is used
        int unicodeSum = 0;
        //Unicode each character of a word sequence
        for (char c : word.toCharArray()) {
            unicodeSum += c;
        }

        //Use division hashing to compute the remainder as a hash value
        int divisionMethodHash = unicodeSum % hashTableSize;
        return divisionMethodHash;
    }
}

class  MultiplicationMethodHash extends MyHashFunction {

    //The multiplicative hash constant
    private final double A = (Math.sqrt(5) - 1) / 2;//The golden section constant is used here

    public MultiplicationMethodHash(int size) {
        super(size);
    }

    @Override
    public int hash(String wordSequence) {

        int unicodeSum = 0;

        for (char c : wordSequence.toCharArray()) {
            unicodeSum += c;
        }

        double multiplicationMethodHash = Math.floor(hashTableSize * (unicodeSum * A % 1));
        return (int)multiplicationMethodHash;
    }
}

class UniversalHash extends MyHashFunction {
    private int a, b; // Arguments for the full-domain hash function

    public UniversalHash(int size) {
        super(size);
        Random rand = new Random();
        a = rand.nextInt(size);
        b = rand.nextInt(size);
    }

    @Override
    public int hash(String wordSequence) {
        int unicodeSum = 0;
        for (char c : wordSequence.toCharArray()) {
            unicodeSum += c;
        }

        double universalMethodHash = ((a * unicodeSum + b) % hashTableSize);
        return (int)universalMethodHash;
    }
}
