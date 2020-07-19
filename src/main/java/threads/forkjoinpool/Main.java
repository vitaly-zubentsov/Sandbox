package threads.forkjoinpool;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        Tree tree = new Tree();
        System.out.println("Done!");
        System.out.printf("Sum from constructor %d sum from calculate %d", tree.totalWeightOfTree, tree.calculateWeightOfTree(tree.rootOfTree));
    }
}
