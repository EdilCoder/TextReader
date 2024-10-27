import java.util.Arrays;

public class MyHashTest {

    public static void main(String[] args) {

        // The list of hash functions to test
        String[] hashFunctions = {"DivisionMethodHash", "DivisionMethodHash2", "MultiplicationMethodHash", "UniversalHash"};

        String[] words = {"apple", "banana", "orange", "grape", "melon",
                "Aberration", "Cacophony", "Dearth", "Ebullient", "Facetious",
                "Garrulous", "Harangue", "Iconoclast", "Juxtapose", "Kaleidoscope",
                "Labyrinth", "Maelstrom", "Nefarious", "Obfuscate", "Palliate",
                "Quixotic", "Rambunctious", "Sagacious", "Taciturn", "Ubiquitous",
                "Vivacious", "Cacophony", "Xenophile", "Yesteryear", "Zephyr",
                "Quintessential", "Blasphemy", "Capricious", "Juxtapose", "Euphoria",
                "Flabbergasted", "Gregarious", "Hypocrisy", "Idiosyncratic", "Jovial",
                "Kowtow", "Ludicrous", "Misanthrope", "Juxtapose", "Obsequious",
                "Paradox", "Querulous", "Rhetoric", "Sycophant", "Tranquil",
                "Unorthodox", "Voracious", "Wistful", "Xenophobia", "Yearning",
                "Zealot", "Aplomb", "orange", "Cajole", "Decipher", "Elusive",
                "Fickle", "Gumption", "Harbinger", "Impeccable", "Jubilant",
                "Knack", "Lethargic", "orange", "Nebulous", "Ostentatious",
                "Pertinent", "Quaint", "Ravenous", "Scintillating", "Tenacious",
                "Uncanny", "Vex", "Winsome", "Exuberant", "orange", "Zenith",
                "Ardent", "Beguile", "Candid", "Defiant", "Exhilarating",
                "Fervent", "orange", "orange", "Ingenious", "Jocular",
                "Keen", "Luminous", "Munificent", "Noble", "Optimistic",
                "Passionate", "Quixotic", "Radiant", "Sincere", "Thrive",
                "Unwavering", "Vibrant", "Warm", "Exultant", "Yearn", "Zealous", "optimistic", "ouixotic" ,"orange", "orange"};

        // Test each hash function
        for (String hashFunction : hashFunctions) {
            // Create a hash table with a size of 10 and using the current hash function
            MyHashTable hashTable = new MyHashTable(30, hashFunction);

            // Get the start time
            long startTime = System.nanoTime();

            // Add the words to the hash table
            //hashTable.addWords(words);

            // Get the end time
            long endTime = System.nanoTime();

            // Calculate and print the elapsed time
            long elapsedTime = endTime - startTime;
            System.out.println(hashFunction + " elapsed time: " + elapsedTime + " nanoseconds");

            // Get and print the linked list lengths
            int[] lengths = hashTable.getLinkedListLengths();
            System.out.println("Linked list lengths: " + Arrays.toString(lengths));

            hashTable.displayHashTableContent();
            System.out.println("=========================");
        }
    }
}
