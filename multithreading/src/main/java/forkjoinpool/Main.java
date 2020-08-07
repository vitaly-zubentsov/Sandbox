package forkjoinpool;

/**
 * Класс для подсчёта весов бинарного дерева с использованием одного потока и рекурсивного fork join pool'а
 */
public class Main {

    public static void main(String[] args) {

        Tree tree = new Tree();

        //Расчет в одном потоке
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        tree.calculateWeightOfTreeInSingleThread(tree.rootOfTree);
        tree.calculateWeightOfTreeInSingleThread(tree.rootOfTree);

        long timeOfStart = System.currentTimeMillis();
        long weightOfTree = tree.calculateWeightOfTreeInSingleThread(tree.rootOfTree);
        long timeOfFinish = System.currentTimeMillis();

        System.out.printf("Calculating weight of tree in single thread: %d%n Time: %d\n", weightOfTree, timeOfFinish - timeOfStart);

        //Расчет с использованием Recursive task
        //несколько раз считаем для того, чтобы виртуальная машина оптимизировала код
        tree.calculateWeightOfTreeInForkJoinPool(tree.rootOfTree);
        tree.calculateWeightOfTreeInForkJoinPool(tree.rootOfTree);

        long timeOfStart2 = System.currentTimeMillis();
        long weightOfTree2 = tree.calculateWeightOfTreeInForkJoinPool(tree.rootOfTree);
        long timeOfFinish2 = System.currentTimeMillis();

        System.out.printf("Calculating weight of tree in recursive task: %d%n Time: %d\n", weightOfTree2, timeOfFinish2 - timeOfStart2);
    }
}
