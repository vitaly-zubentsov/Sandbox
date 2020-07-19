package threads.forkjoinpool;

import java.util.Random;

public class Tree {

    private Random random = new Random();

    TreeNode rootOfTree = new TreeNode();

    //Для глубины равной двадцати семи, дерева требуется выделить около 6.5Gb для jvm
    private int deepOfTree = 27;
    public long totalWeightOfTree;


    public Tree() {
        createBranch(rootOfTree,this.deepOfTree);
    }

    private void createBranch(TreeNode node, int deepOfTree) {

        node.right = new TreeNode();
        node.left = new TreeNode();
        node.weight = random.nextInt(100);
        totalWeightOfTree += node.weight;
        deepOfTree--;

        if (deepOfTree == 0) {
            node.left.weight =  random.nextInt(100);
            node.right.weight =  random.nextInt(100);
            totalWeightOfTree += node.right.weight;
            totalWeightOfTree += node.left.weight;
            return;
        }
        createBranch(node.right, deepOfTree);
        createBranch(node.left, deepOfTree);
    }

    public long calculateWeightOfTree(TreeNode node) {
       return (node.weight +
               (node.left!=null?calculateWeightOfTree(node.left):0)+
               (node.right!=null?calculateWeightOfTree(node.right):0));
    }


}
