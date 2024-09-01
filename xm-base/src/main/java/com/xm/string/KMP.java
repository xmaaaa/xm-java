package com.xm.string;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * KMP算法
 * 时间复杂度 O(n + m)
 *
 * @author XM
 * @date 2024/9/1
 */
public class KMP {


    public static int[] buildNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        // 一定要是0，只考虑不包括自身的真前缀
        next[0] = 0;
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                // 此时表示前j-1个匹配，但是第j个不匹配,此时看前j-1是否可以回推
                j = next[j - 1];
            }
            // 如果j会持续变大，表示前面的一定匹配上
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


    public static List<Integer> kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] next = buildNext(pattern);
        int j = 0;
        List<Integer> result = Lists.newArrayList();
        // 注意一个从0开始，这个算法类似上面，只不过j == m 时表示完全匹配了, 上面的方法从1开始不可能j = m
        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                result.add(i - m + 1);
                j = next[j - 1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABAB";
        List<Integer> result = kmpSearch(text, pattern);
        System.out.println("Pattern found at index: " + result);
    }


}
