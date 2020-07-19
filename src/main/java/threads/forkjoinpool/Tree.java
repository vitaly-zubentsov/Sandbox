package threads.forkjoinpool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Tree {

    private Random random = new Random();

    TreeNode rootOfTree = new TreeNode();

    //Для глубины дерева равной двадцати семи, требуется выделить около 6.5Gb ОЗУ для jvm
    private int deepOfTree = 22;
    public long totalWeightOfTree;


    public Tree() {
        createBranch(this.rootOfTree,this.deepOfTree);
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
    public long calculateWeightOfTreeInSingleThread(TreeNode node) {
        //Вызываем рекурсивно функцию подсчета каждой ветви, до тех пор, пока не достигнем объекта, у которого поля
        // left и right будут null (то есть, до тех пор пока не достигнем ветви, не имеющей ответвлений)
       return (node.weight +
               (node.left!=null? calculateWeightOfTreeInSingleThread(node.left):0)+
               (node.right!=null? calculateWeightOfTreeInSingleThread(node.right):0));
    }

    public long calculateWeightOfTreeInForkJoinPool(TreeNode node) {

        class TaskForCalculatingWeightOfTree extends RecursiveTask<Long> {

            private TreeNode node;
            private int countOfThreads;
            private long totalWeightOfTree;

            TaskForCalculatingWeightOfTree(TreeNode rootOfTree, int countOfThreads) {
                this.node = rootOfTree;
                this.countOfThreads = countOfThreads;
            }

            @Override
            protected Long compute() {
                int newCountOfThreads = countOfThreads - 1;
                //Если количество тредов закончилось, то продолжаем расчёт в одном потоке
                if (countOfThreads<=0) {
                    calculateWeightOfTreeInSingleThread(node);
                }

                this.totalWeightOfTree += node.weight;

                //Если у ветви есть ответвления, то запускаем их расчет в отдельном потоке.
                TaskForCalculatingWeightOfTree taskForCalculateRightNode = null, taskForCalculateLeftNode = null;
                if (node.right!=null) {
                    taskForCalculateRightNode = new TaskForCalculatingWeightOfTree(node.right,newCountOfThreads);
                    taskForCalculateRightNode.fork();
                }

                if (node.left!=null) {
                    taskForCalculateLeftNode = new TaskForCalculatingWeightOfTree(node.left,newCountOfThreads);
                    taskForCalculateLeftNode.fork();
                }

                if (taskForCalculateRightNode!=null) {
                    this.totalWeightOfTree+= taskForCalculateRightNode.join();
                }

                if (taskForCalculateLeftNode!=null) {
                    this.totalWeightOfTree+= taskForCalculateLeftNode.join();
                }

                return this.totalWeightOfTree;
            }

        }

        return ForkJoinPool.commonPool().invoke(
                new TaskForCalculatingWeightOfTree(
                        rootOfTree,
                        Runtime.getRuntime().availableProcessors())
        );
    }


}
