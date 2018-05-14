package com.liuran.utils.sort;
/**
 * 1、堆编码规则(逻辑上):从上到下,从左到右,按照数组顺序建立一个完全二叉树
 * 2、大顶堆定义:Ai > A(i+1) 且 Ai > A(i+2),即堆的父节点大于两个子节点(二叉树的部分性质)
 * 3、第n个非叶子节点计算公式:node = array.length/2 - n;
 * 4、调整规程:
 *    A、建立初始堆,从最后一个非叶子节点开始,以该节点开始作为子树,调整子树为大顶堆(或小顶堆)。循环array.length/2 - 1次,直到结束。
 *    B、在初始堆的的基础之上进行调整
 *      B1、堆顶元素和堆末尾元素进行交换(找到一个最大的元素,并放到该放的位置)
 *      B2、调整以0节点开始调整堆(不需要调整array.length/2 - 1次,因为B1步骤只有一个元素破坏了堆结构)
 *      B3、重复B1、B2过程直到结束(每次循排序好的数不需要加入堆调整过程中,因此随着循环的进行,堆在不断的减小)
 *    C、堆调整算法
 *      算法:找到parent节点的叶子节点,并根据2中堆的定义进行交换,依次循环直到结束
 *      结束条件:当前节点满足堆的定义 或 循环到堆得底部
 * */
public class HeapSort extends AbstractSort{

    public HeapSort(){

    }

    public HeapSort(boolean invert){
        super(invert);
    }

    @Override
    public Comparable[] sort(Comparable[] array) {
        /*
        * i = array.length / 2 - 1,找到最后一个非叶子节点,并从该非叶子节点遍历后面所有非叶子节点
        * */
        for (int i = array.length / 2 -1; i >= 0; i --){
            //从当前非叶子节点开始,从上至下调整堆
            adjustHeap(array, i, array.length);
        }

        for (int i = 1; i < array.length ;i ++){
            swap(array, 0, array.length - i);
            adjustHeap(array, 0, array.length - i);
        }
        return array;
    }

    /**
     * @param array 待排序数组
     * @param node 开始调整的非叶子节点
     * @param length 数组长度(每次调整堆,数组的大小可能不一样)
     * */
    public void adjustHeap(Comparable[] array, int node, int length){
        //父节点
        int parent = node;
        //parent的子节点,每次循环默认子节点是左子节点(child < length保证左子节点一定存在,否则循环自动结束)
        int child;
        for (child = parent * 2 + 1 ;child < length; child = parent * 2 +1){
            //k parent的叶子节点,
            //判断是否有右节点,如果存在右节点,且右节点大于左节点，把k指向右节点,否则k仍然指向左节点
            if (child + 1 < length && fistGtSecond(array[child + 1], array[child])){
                child ++;
            }

            //判断最大的子节点(k可能是左节点也可能是右节点)和当前节点是否需要进行交换
            if (fistGtSecond(array[child], parent)){
                swap(array, parent, child);
            } else {
                //循环结束
                break;
            }

            //缓存父节点
            parent = child;
        }
    }
}
