package com.xm.sort;

import java.util.Arrays;

/**
 * 堆排序，不稳定排序
 * 时间复杂度 O(nlogn)
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
                // 调整完，继续向下递归调整
                adjustDown(a, largest, length);
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
