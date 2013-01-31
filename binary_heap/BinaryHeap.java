package Keko;
/**
 *
 * @author Noemj
 */
public class BinaryHeap {

    int size;
    BinaryNode root;

    public BinaryHeap() {
        size = 0;
    }

    public void insert(int value) {
        BinaryNode BinaryNode = new BinaryNode(value);
        size++;
        if (size == 1) {
            root = BinaryNode;
            return;
        }

        BinaryNode help = root;
        String binary = Integer.toBinaryString(size);

        for (int i = 1; i < binary.length() - 1; i++) {
            if (binary.charAt(i) == '0') {
                help = help.left;
            } else {
                help = help.right;
            }
        }

        if (binary.charAt(binary.length() - 1) == '0') {
            help.left = BinaryNode;
            BinaryNode.parent = help;
        } else {
            help.right = BinaryNode;
            BinaryNode.parent = help;
        }
        heap_up(BinaryNode);
    }

    public int delete() {
        if (size == 1) {
            int helpvalue = root.value;
            root = null;
            return helpvalue;
        }
        int ret = root.value;

        BinaryNode help = root;
        help.value = root.value;
        String binary = Integer.toBinaryString(size);
        for (int i = 1; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                help = help.left;
            } else {
                help = help.right;
            }
        }
        root.value = help.value;
        if (binary.charAt(binary.length() - 1) == '0') {
            help.parent.left = null;
        } else {
            help.parent.right = null;
        }

        size--;
        heap_down();
        return ret;

    }

    public void print_heap(BinaryNode root) {
        System.out.println(root.value);
        if (root.left != null) {
            print_heap(root.left);
        }
        if (root.right != null) {
            print_heap(root.right);
        }
    }

    private void heap_up(BinaryNode BinaryNode) {
        if (BinaryNode == root) {
            return;
        }

        int valuehelper;
        if (BinaryNode.parent.value > BinaryNode.value) {
            valuehelper = BinaryNode.value;
            BinaryNode.value = BinaryNode.parent.value;
            BinaryNode.parent.value = valuehelper;
        } else {
            return;
        }
        heap_up(BinaryNode.parent);

    }

    private void heap_down() {
        BinaryNode BinaryNode = root;
        BinaryNode help;
        int valuehelper;
        while (true) {
            if (BinaryNode.right == null || BinaryNode.left.value > BinaryNode.right.value) {
                help = BinaryNode.right;
            } else {
                help = BinaryNode.left;
            }

            if (help != null && help.value < BinaryNode.value) {
                valuehelper = BinaryNode.value;
                BinaryNode.value = help.value;
                help.value = valuehelper;
            } else {
                return;
            }
            BinaryNode = help;
        }
    }
}
