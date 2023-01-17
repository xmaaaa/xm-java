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


    /**
     * 反转链表
     *
     * @param head
     * @return
     */
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

    /**
     * 列表的归并
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail) {
            fast = fast.next;
            slow = slow.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode list1 = sortList(head, slow);
        ListNode list2 = sortList(slow, tail);
        return merge(list1, list2);
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4,
                        new ListNode(5)))));
        System.out.println(listNode);
    }

}