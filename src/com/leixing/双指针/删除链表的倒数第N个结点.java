package com.leixing.双指针;

/**
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *快慢指针
 * 删除链表的倒数第 n 个结点，首先要确定倒数第 n 个节点的位置。
 * 我们可以设定两个指针，分别为 slow 和 fast，刚开始都指向 head。
 * 然后先让 fast 往前走 n 步，slow 指针不动，这时候两个指针的距离为 n。
 * 再让 slow 和 fast 同时往前走（保持两者距离不变），直到 fast 指针到达结尾的位置。
 * 这时候 slow 会停在待删除节点的前一个位置，让 slow.next = slow.next.next 即可。
 * 但这里有一个需要注意的边界情况是：如果链表的长度是 L，而我们恰好要删除的是倒数第 L 个节点
 * （删除头节点），这时候 fast 往前走 n 步之后会变为 null，此时我们只需要让 head = slow.next 即可删除。
 */
public class 删除链表的倒数第N个结点 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head.next==null) return null;

        ListNode slow=head;
        ListNode fast=head;
        while (n-- > 0){
            fast=fast.next;
        }
        if (fast == null){
            head=slow.next;
        }
        else {
            while (fast.next !=null){
                slow=slow.next;
                fast=fast.next;
            }
            slow.next=slow.next.next;
        }
        return head;
    }
}
 class ListNode {
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
}
