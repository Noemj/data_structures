package Keko;

/**
 *
 * @author Noemj
 */
public class BinomialHeap {

    BinomialNode root;
    int size;

    public BinomialHeap() {
    }

    public BinomialHeap(BinomialNode node, int size) {
        this.size = size;
        this.root = node;
    }

    public void insert(int value) {
        BinomialHeap h = new BinomialHeap();
        BinomialNode node = new BinomialNode(value);
        node.parent = null;
        node.child = null;
        node.sibling = null;
        node.degree = 0;
        h.root = node;
        BinomialHeap uusi = union(h, this);
        this.root = uusi.root;
        size++;
    }

    public BinomialNode find_min() {
        int value = Integer.MAX_VALUE;
        BinomialNode helpernode = root;
        BinomialNode min = null;
        while (helpernode != null) {
            if (helpernode.value < value) {
                value = helpernode.value;
                min = helpernode;
            }
            helpernode = helpernode.sibling;
        }
        return min;
    }

    public void extract_min() {
        BinomialHeap helper = new BinomialHeap();
        BinomialNode min = find_min();
        BinomialNode temp = min.child;
        BinomialNode current = root;
        BinomialNode helper = root;

        if (root.value != min.value) {
            while (helper.sibling.value != min.value) {
                helper = helper.sibling;
            }
            helper = helper.sibling.sibling;
        }
        if (root.value == min.value) {
            root = root.sibling;
        }

        if (min.child != null) {
            while (temp != null) {
                BinomialNode next = temp.sibling;
                temp.sibling = helper.root;
                helper.root = temp;
                temp = next;
            }

            BinomialHeap uusi = union(this, helper);
            this.root = uusi.root;
        }
    }

    public void decreaseKey(BinomialNode x, int k) {
        if (k > x.value) {
            System.out.println("Parameter value higher than node value! Node value: " + x.value);
            return;
        }
        x.value = k;
        BinomialNode y = x;
        BinomialNode z = y.parent;

        while ((z != null) && y.value < x.value) {
            int helpervalue = y.value;
            y.value = x.value;
            x.value = helpervalue;
            y = z;
            z = y.parent;
        }
    }

    public void binomial_link(BinomialNode y, BinomialNode z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }

    public BinomialHeap union(BinomialHeap h1, BinomialHeap h2) {
        BinomialHeap h = new BinomialHeap();
        h.root = merge(h1, h2);
        if (h.root == null) {
            return h;
        }
        BinomialNode prev_x = null;
        BinomialNode x = h.root;
        BinomialNode next_x = x.sibling;
        while (next_x != null) {
            if (x.degree != next_x.degree || (next_x.sibling != null) && (next_x.sibling.degree == x.degree)) {
                prev_x = x;
                x = next_x;
            } else {
                if (x.value <= next_x.value) {
                    x.sibling = next_x.sibling;
                    binomial_link(next_x, x);
                } else {
                    if (prev_x == null) {
                        h.root = next_x;
                    } else {
                        prev_x.sibling = next_x;
                    }
                    binomial_link(x, next_x);
                    x = next_x;
                }
            }
            next_x = x.sibling;
        }
        return h;
    }

    BinomialNode merge(BinomialHeap h1, BinomialHeap h2) {
        BinomialNode node1 = null;
        BinomialNode node2 = null;
        if (h1 != null && h1.root != null) {
            node1 = h1.root;
        }
        if (h2 != null && h2.root != null) {
            node2 = h2.root;
        }
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }
        BinomialNode h;
        if (node1.degree < node2.degree) {
            h = node1;
            node1 = node1.sibling;
        } else {
            h = node2;
            node2 = node2.sibling;
        }
        BinomialNode curr = h;
        while (node1 != null && node2 != null) {
            if (node1.degree < node2.degree) {
                curr.sibling = node1;
                curr = node1;
                node1 = node1.sibling;
            } else {
                curr.sibling = node2;
                curr = node2;
                node2 = node2.sibling;
            }
        }
        if (node1 == null) {
            curr.sibling = node2;
        } else {
            curr.sibling = node1;
        }
        return h;
    }

    public String toString() {
        String result = "";

        BinomialNode x = root;
        while (x != null) {
            result += x.printTree(0);
            x = x.sibling;
        }
        return result;
    }
}



