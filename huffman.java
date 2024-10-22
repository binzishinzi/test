import java.util.*;

class Node implements Comparable<Node> {
    int freq;
    String symbol;
    Node left, right;
    String huff = "";

    public Node(int freq, String symbol) {
        this.freq = freq;
        this.symbol = symbol;
        this.left = this.right = null;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.freq, other.freq);
    }
}

public class test {

    public static void printNodes(Node node, String val) {
        String newval = val + node.huff;
        if (node.left != null) {
            printNodes(node.left, newval);
        }
        if (node.right != null) {
            printNodes(node.right, newval);
        } else {
            System.out.println(node.symbol + " -> " + newval);
        }
    }

    public static void main(String[] args) {
        String[] chars = {"a", "b", "c", "d", "e", "f"};
        int[] freqs = {5, 9, 12, 13, 16, 45};

        PriorityQueue<Node> nodes = new PriorityQueue<>();

        // Step 1: Create a list of nodes and add them to the priority queue
        for (int i = 0; i < chars.length; i++) {
            nodes.add(new Node(freqs[i], chars[i]));
        }

        // Step 2: Build the Huffman tree by combining nodes
        while (nodes.size() > 1) {
            // Remove the two nodes with the lowest frequency
            Node left = nodes.poll();
            Node right = nodes.poll();

            // Assign '0' to left node and '1' to right node
            left.huff = "0";
            right.huff = "1";

            // Create a new internal node with the combined frequency and add it back to the queue
            Node newNode = new Node(left.freq + right.freq, left.symbol + right.symbol);
            newNode.left = left;
            newNode.right = right;

            nodes.add(newNode);
        }

        // Step 3: Print the Huffman codes (starting from the root node)
        printNodes(nodes.peek(), "");
    }
}
