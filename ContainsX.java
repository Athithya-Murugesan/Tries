import java.util.*;

class TreeNode {
    int data;
    List<TreeNode> children;

    public TreeNode(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}

public class Main {
    
    public static TreeNode buildTree(Scanner sc) {
        if (!sc.hasNext()) {
            return null;
        }

        int rootData = sc.nextInt();
        TreeNode root = new TreeNode(rootData);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            int numChildren = sc.nextInt();
            for (int i = 0; i < numChildren; i++) {
                int childData = sc.nextInt();
                TreeNode childNode = new TreeNode(childData);
                currentNode.children.add(childNode);
                queue.add(childNode);
            }
        }

        return root;
    }

    public static boolean findInTree(TreeNode root, int x) {
        if (root == null) {
            return false;
        }
        
        if (root.data == x) {
            return true;
        }

        for (TreeNode child : root.children) {
            if (findInTree(child, x)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the tree from input
        TreeNode root = buildTree(sc);

        // Read the integer x to search for
        int x = sc.nextInt();

        // Search for x in the tree and print the result
        boolean result = findInTree(root, x);
        System.out.println(result);
    }
}
