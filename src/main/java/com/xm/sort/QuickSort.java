package com.xm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author hongwan
 * @date 2022/8/19
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 52, 6, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && arr[right] >= arr[start]) {
                right--;
            }
            while (left < right && arr[left] <= arr[start]) {
                left++;
            }
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        int temp = arr[right];
        arr[right] = arr[start];
        arr[start] = temp;
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }
}
