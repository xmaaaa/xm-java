package com.xm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author XM
 * @date 2021/6/9
 */
public class Test {
    private static final Random random = new Random();


    public static void main(String[] args) {
        int[] nums = new int[]{2,3,3,2,2,2,1,1};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void wiggleSort(int[] nums) {
        int n = nums.length;
        int x = (n + 1) / 2;
        int mid = x - 1;
        int target = findKthLargest(nums, mid);
        for (int k = 0, i = 0, j = n - 1; k <= j; k++) {
            if (nums[k] > target) {
                swap(nums, k, j--);
            }
            if (nums[k] < target) {
                swap(nums, k, i++);
            }
        }
        int[] arr = nums.clone();
        for (int i = 0, j = mid, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    public static int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public static int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public static int partition(int[] a, int l, int r) {
        int x = a[r], i = l, j =r;
        while (i < j) {
            while (i < j && a[i] <= x) {
                i++;
            }
            while (i < j && a[j] >= x) {
                j--;
            }
            swap(a, i, j);
        }
        swap(a, i, r);
        return i;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}