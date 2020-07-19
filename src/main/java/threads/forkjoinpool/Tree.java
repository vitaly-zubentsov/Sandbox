package threads.forkjoinpool;

import java.util.Random;

public class Tree {

    private Random random = new Random();

    TreeNode rootOfTree = new TreeNode();

    //Для глубины дерева равной двадцати семи, требуется выделить около 6.5Gb ОЗУ для jvm
    private int deepOfTree = 27;
    public long totalWeightOfTree;


    public Tree() {
        createBranch(rootOfTree,this.deepOfTree);
    }

    /**
     * Рекурсивное создание дервера требуемой глубины
     * @param node
     * @param deepOfTree
     */
    private void createBranch(TreeNode node, int deepOfTree) {

        node.right = new TreeNode();
        node.left = new TreeNode();
        node.weight = random.nextInt(100);
        totalWeightOfTree += node.weight;
        deepOfTree--;

        // После того как глубина создаваемого дерева будет равна требуемой, рандомизируется значение левой
        //  и правой ветви, добавляются веса для проверочного параметра totalWeightOfTree и начинается выход из рекурсии;
        if (deepOfTree == 0) {
            node.left.weight =  random.nextInt(100);
            node.right.weight =  random.nextInt(100);
            totalWeightOfTree += node.right.weight;
            totalWeightOfTree += node.left.weight;
            return;
        }

        //Рекурсивно вызываем метод, для создния дерева требуемой глубины
        createBranch(node.right, deepOfTree);
        createBranch(node.left, deepOfTree);
    }

    /**
     * Расчёт веса всего дерева
     * @param node
     * @return
     */
    public long calculateWeightOfTree(TreeNode node) {
        //Вызываем рекурсивно функцию подсчета каждой ветви, до тех пор, пока не достигнем объекта, у которого поля
        // left и right будут null (то есть, до тех пор пока не достигнем ветви, не имеющей ответвлений)
       return (node.weight +
               (node.left!=null?calculateWeightOfTree(node.left):0)+
               (node.right!=null?calculateWeightOfTree(node.right):0));
    }


}
