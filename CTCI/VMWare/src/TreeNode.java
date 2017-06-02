public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int data, TreeNode left) {
        this.data = data;
        this.left = left;
    }

    public TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }

    public TreeNode() {
        this.left = null;
        this.right = null;
    }
}