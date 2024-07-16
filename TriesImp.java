import java.util.Scanner;

class Node {
    Node[] children;
    boolean isEndOfWord;

    Node() {
        children = new Node[26];
        isEndOfWord = false;
    }

    boolean containskey(char ch) {
        return children[ch - 'a'] != null;
    }

    Node get(char ch) {
        return children[ch - 'a'];
    }

    void put(char ch, Node node) {
        children[ch - 'a'] = node;
    }

    void setEndOfWord() {
        isEndOfWord = true;
    }

    boolean isEndOfWord() {
        return isEndOfWord;
    }
}

class Tries{
    static Node root = new Node();

    static int search(String key) {
        Node temp = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!temp.containskey(ch)) {
                return 0;
            }
            temp = temp.get(ch);
        }
        return (temp != null && temp.isEndOfWord())?1:0;
    }

    static void insert(String key) {
        Node temp = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!temp.containskey(ch)) {
                temp.put(ch, new Node());
            }
            temp = temp.get(ch);
        }
        temp.setEndOfWord();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            insert(sc.next());
        }
        String s = sc.next();
        System.out.print(search(s));
    }
}
