import java.util.*;

class Node {
    Node[] children;
    boolean isEndOfWord;
    boolean isCapital;

    Node() {
        children = new Node[26];
        isEndOfWord = false;
        isCapital = false;
    }

    boolean containsKey(char ch) {
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

class CamelCase {
    static Node root = new Node();

    static boolean search(String key) {
        Node temp = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!temp.containsKey(Character.toLowerCase(ch))) {
                return false;
            }
            temp = temp.get(Character.toLowerCase(ch));
        }
        return temp != null && temp.isEndOfWord();
    }

    static boolean searchCamelCase(String pattern, String key) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (Character.isUpperCase(ch)) {
                q.add(ch);
            }
        }

        Node temp = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!temp.containsKey(Character.toLowerCase(ch))) {
                return false;
            }
            temp = temp.get(Character.toLowerCase(ch));

            if (Character.isUpperCase(ch)) {
                if (!q.isEmpty() && q.peek() == ch) {
                    q.poll();
                } else if (!q.isEmpty() && q.peek() != ch) {
                    return false;
                }
            }
        }
        return q.isEmpty();
    }

    static void insert(String key) {
        Node temp = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!temp.containsKey(Character.toLowerCase(ch))) {
                temp.put(Character.toLowerCase(ch), new Node());
            }
            temp = temp.get(Character.toLowerCase(ch));
            if (Character.isUpperCase(ch)) {
                temp.isCapital = true;
            }
        }
        temp.setEndOfWord();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int j = 0; j < t; j++) {
            root = new Node();
            int n = sc.nextInt();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.next();
                insert(arr[i]);
            }
            String s = sc.next();
            if (root.containsKey(Character.toLowerCase(s.charAt(0)))) {
                int f = 0;
                for (String str : arr) {
                    if (searchCamelCase(s, str)) {
                        System.out.print(str + " ");
                        f = 1;
                    }
                }
                if (f == 0) {
                    System.out.print("No match found");
                }
            } else {
                System.out.print("No match found");
            }
            System.out.println(); 
        }
        sc.close();
    }
}
