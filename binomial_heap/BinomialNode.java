package Keko;

/**
 *
 * @author oorissan
 */
public class BinomialNode {

    int value;
    int degree;
    BinomialNode sibling;
    BinomialNode parent;
    BinomialNode child;

    public BinomialNode(){
        
    }

    public BinomialNode(int value){
        this.value = value;
    }

    public String printTree(int depth){
        String result = "";

        for (int i = 0; i < depth; i++){
            result +="  ";
        }
        result += toString() + "\n";

        BinomialNode x = child;
        while (x != null){
            result += x.printTree(depth +1);
            x = x.sibling;
        }

        return result;
    }

    public String toString(){
        return ("Value: " + value + ", Degree: " + degree);
    }

}
