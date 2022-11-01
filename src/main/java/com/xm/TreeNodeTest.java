package com.xm;

import com.google.common.collect.Lists;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hongwan
 * @date 2022/10/20
 */
public class TreeNodeTest {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> pre = Lists.newArrayList();
    List<Integer> in = Lists.newArrayList();
    List<Integer> next = Lists.newArrayList();

    public void pre(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() ||root != null){
            if(root != null){
                pre.add(root.val);
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                root = root.right;
            }
        }
        return;
    }

    public void in(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                in.add(root.val);
                root = root.right;
            }
        }
        return;
    }

    public void next(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.peek();
                if (root.right == null || root.right == pre) {
                    next.add(root.val);
                    stack.pop();
                    pre = root;
                    root = null;
                } else {
                    root = root.right;
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));

    }
}
