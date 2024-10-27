import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyHashTable {

    private MyLinkedObject[] table;
    private MyHashFunction hashFunction;

    public MyHashTable(int s1, MyHashFunction hashFunction) {
        this.table = new MyLinkedObject[s1];
        this.hashFunction = hashFunction;
    }

    // Constructors for testing
    public MyHashTable(int size, String hashFunctionType) {
        this.table = new MyLinkedObject[size];  // Initialize the hash table
        this.hashFunction = getHashFunction(hashFunctionType, size);  // Set the hash function
    }

    public void addWords(String[] words, int n) {
        for (int i = 0; i <= words.length - n; i++) {
            String[] nGramWords = Arrays.copyOfRange(words, i, i + n);
            addNgram(nGramWords);
        }
    }

    private void addNgram(String[] words) {
        int index = hashFunction.hash(Arrays.toString(words));  // Calculate the hash value
        if (table[index] == null) {
            // If the linked list is empty, create a new linked list
            table[index] = new MyLinkedObject(words);
        } else {
            // Otherwise, add the words to the linked list
            table[index].setWords(words);
        }
    }

    public MyLinkedObject[] getTable() {
        return table;
    }

    // Choose your function here
    public MyHashFunction getHashFunction(String type, int size) {
        switch (type) {
            case "DivisionMethodHash":
                return new DivisionMethodHash(size);
            case "DivisionMethodHash2":
                return new DivisionMethodHash2(size);
            case "MultiplicationMethodHash":
                return new MultiplicationMethodHash(size);
            case "UniversalHash":
                return new UniversalHash(size);
            default:
                throw new IllegalArgumentException("Invalid hash function type: " + type);
        }
    }

    // Methods to get the length
    public int[] getLinkedListLengths() {
        int[] lengths = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            MyLinkedObject linkedObject = table[i];
            int length = 0;
            while (linkedObject != null) {
                length++;
                linkedObject = linkedObject.getNext();
            }
            lengths[i] = length;
        }
        return lengths;
    }

    public Map<Integer, Integer> getLinkedListLengthDistribution() {
        Map<Integer, Integer> distribution = new HashMap<>();
        for (MyLinkedObject linkedObject : table) {
            int length = 0;
            while (linkedObject != null) {
                length++;
                linkedObject = linkedObject.getNext();
            }
            distribution.put(length, distribution.getOrDefault(length, 0) + 1);
        }
        return distribution;
    }

    // Display Hash Table Content
    public void displayHashTableContent() {
        System.out.println("Hash Table Content:");
        for (int i = 0; i < table.length; i++) {
            MyLinkedObject linkedObject = table[i];
            System.out.print("Index " + i + ": ");
            while (linkedObject != null) {
                System.out.print(Arrays.toString(linkedObject.getWords()) + "【" + linkedObject.getCount() + "】 ");
                linkedObject = linkedObject.getNext();
            }
            System.out.println();
        }
    }

    public Map<String, Integer> getAllNextWordsWithFrequency(String[] prefix) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        // Traverse the entire hash table to find all matching prefixes
        for (MyLinkedObject linkedObject : table) {
            while (linkedObject != null) {
                // Check if the linked list node matches the prefix
                if (Arrays.equals(prefix, Arrays.copyOf(linkedObject.getWords(), prefix.length))) {
                    String nextWord = linkedObject.getWords()[prefix.length];
                    wordFrequencyMap.put(nextWord, wordFrequencyMap.getOrDefault(nextWord, 0) + linkedObject.getCount());
                }
                linkedObject = linkedObject.getNext();
            }
        }

        return wordFrequencyMap;
    }
}
