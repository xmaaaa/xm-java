package com.xm.sort;

import java.util.Arrays;

/**
 * 堆排序，不稳定排序
 * 时间复杂度 最好最差 O(nlogn)
 * 空间复杂度 非递归O(1)
 *
 * @author xiaoming
 * @date 2022/8/23
 */
public class HeapSort {


    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 52, 6, 7};
        creatBigHeap(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static void creatBigHeap(int[] a, int len) {
        // 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustDown(a, i, len);
        }
        // 将堆顶元素与末尾元素交换。将最大的元素沉到数组末端
        for (int j = a.length - 1; j > 0; j--) {
            swap(a, 0, j);
            // 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
            adjustDown(a, 0, j);
        }
    }

    /**
     * 递归调整
     *
     * @param a
     * @param i
     * @param length
     */
    private static void adjustDown(int[] a, int i, int length) {
        if (i < length) {
            int largest = i;
            // 左子节点, 右子节点
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            if (left < length && a[left] > a[largest]) {
                largest = left;
            }
            if (right < length && a[right] > a[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(a, i, largest);
                // 调整完，继续向下递归调整, 核心点就是将该值放入子树中合适的位置，可以非递归
                adjustDown(a, largest, length);
            }
        }
    }

    /**
     * 非递归调整
     *
     * @param a
     * @param i
     * @param length
     */
    public static void adjustDown2(int[] a, int i, int length) {
        // 先记录当前调整的堆的父节点的值
        int temp = a[i];
        // 初始化 k 指向数组中 i 至 length 之间子数组对应的堆中 i 节点的左子节点
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            // 若右子节点值比左子节点值大，则改变指向
            if (k + 1 < length && a[k] < a[k + 1]) {
                k++;
            }
            if (temp < a[k]) {
                // 交换
                a[i] = a[k];
                // 将 i 指向 k， 继续调整以 k 为父节点的堆
                i = k;
            } else {
                // 直接退出
                break;
            }
        }
        // 此时已经找到了父节点应该在子树中的位置
        a[i] = temp;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
