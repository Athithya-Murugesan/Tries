
import java.util.*;

public class Main {
    // Node structure for Trie
    static class Node {
        // Array to store links to child nodes, each index represents a letter
        Node[] links = new Node[26];
        // Flag indicating if the node marks the end of a word
        boolean flag = false;
        // Set of words that pass through this node, sorted lexicographically
        Set<String> words = new TreeSet<>();

        // Check if the node contains a specific key (letter)
        boolean containsKey(char ch) {
            return (links[ch - 'a'] != null);
        }

        // Insert a new node with a specific key (letter) into the Trie
        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        // Get the node with a specific key (letter) from the Trie
        Node get(char ch) {
            return links[ch - 'a'];
        }

        // Set the current node as the end of a word
        void setEnd() {
            flag = true;
        }

        // Check if the current node marks the end of a word
        boolean isEnd() {
            return flag;
        }
    }

    // Trie class
    private Node root;

    // Constructor to initialize the trie with an empty root node
    public Main() {
        root = new Node();
    }

    // Inserts a word into the Trie
    // Time Complexity O(len), where len is the length of the word
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                // Create a new node for the letter if not present
                node.put(word.charAt(i), new Node());
            }
            // Move to the next node
            node = node.get(word.charAt(i));
            node.words.add(word);  // Add the word to the words set of each node it passes through
        }
        // Mark the end of the word
        node.setEnd();
    }

    // Returns a list of words in the Trie that start with the given prefix
    public List<String> search(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.containsKey(prefix.charAt(i))) {
                // If a letter is not found, there are no words with the given prefix in the Trie
                return new ArrayList<>();
            }
            node = node.get(prefix.charAt(i));  // Move to the next node
        }
        // Return the list of words with the given prefix
        return new ArrayList<>(node.words);
    }

    // Returns if there is any word in the Trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.containsKey(prefix.charAt(i))) {
                // If a letter is not found, there is no word with the given prefix
                return false;
            }
            // Move to the next node
            node = node.get(prefix.charAt(i));
        }
        // The prefix is found in the Trie
        return true;
    }

    public static void main(String[] args) {
        Main trie = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // consume the newline character
        for (int i = 0; i < n; i++) {
            trie.insert(sc.nextLine().trim());
        }
        String query = sc.nextLine().trim();
        for (int i = 1; i <= query.length(); i++) {
            List<String> results = trie.search(query.substring(0, i));
            if (results.isEmpty()) {
                System.out.println("0");
            } else {
                for (String result : results) {
                    System.out.print(result + " ");
                }
                System.out.println();
            }
        }
        sc.close();
    }
}
