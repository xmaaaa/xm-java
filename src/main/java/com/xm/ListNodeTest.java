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
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode temp;
        while (head != null) {
            length++;
            head = head.next;
        }
        for (int i = 0; i < length / k; i++) {
            for (int j = 1; j < k; j++) {
                temp = cur.next;
                if (temp == null) {
                    continue;
                }
                cur.next = temp.next;
                temp.next = pre.next;
                pre.next = temp;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4,
                        new ListNode(5)))));
        System.out.println(listNode);
    }

}