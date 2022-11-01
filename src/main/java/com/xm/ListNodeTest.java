package com.xm;

/**
 * @author XM
 * @date 2021/6/9
 */
public class ListNodeTest {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    public static ListNode reverseList(ListNode head) {
        ListNode pre = head;
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre;
            pre = next;
        }
        return  pre;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode cur = head;
        while (head != null) {
            length++;
            head = head.next;
        }
        ListNode pre = dummy;
        for (int i = 0; i < length / k; i++) {
            ListNode head2 = cur;
            int flag = 1;
            while (cur.next != null && flag < k) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = head2;
                head2 = next;
                flag++;
            }
            pre.next = head2;
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

    }

}