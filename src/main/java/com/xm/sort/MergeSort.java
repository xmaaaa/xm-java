package com.xm.sort;

import java.util.Arrays;

/**
 * @author hongwan
 * @date 2022/8/23
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{80, 20, 6, 45, 35, 19, 100, 64};
        int length = arr.length;
        int[] tem = new int[length];
        mergeSort(arr, 0, length - 1, tem);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] tem) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        // 递归子数组, 然后合并
        mergeSort(arr, left, mid, tem);
        mergeSort(arr, mid + 1, right, tem);
        merge(arr, left, mid, right, tem);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] tem) {
        // temp可以从0开始反复利用
        int index = 0;
        // r需要从mid + 1开始
        int l = left, r = mid + 1;
        // 按顺序排好
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                tem[index++] = arr[l++];
            } else {
                tem[index++] = arr[r++];
            }
        }
        // 由于排序时，子序列都有序，所以可以直接放入尾部
        while (l <= mid) {
            tem[index++] = arr[l++];
        }
        while (r <= right) {
            tem[index++] = arr[r++];
        }
        // 将排好序的放回原数组
        for (int i = 0; i < (right - left + 1); i++) {
            arr[left + i] = tem[i];
        }
    }
}
