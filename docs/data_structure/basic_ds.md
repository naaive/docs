# Basic Datastructures

## Reverse Linked List

```java

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
```

```java
public DoubleNode reverseList(DoubleNode head){
        DoubleNode prev=null;
        while(head!=null){
        DoubleNode next=head.next;
        head.next=prev;
        head.prev=next;
        prev=head;
        head=next;
        }
        return prev;
        }
```

## Remove Linked List Elements

```java

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            if (curr.val == val) {
                if (prev != null) {
                    prev.next = curr.next;
                    curr = prev.next;
                    continue;
                }
            }
            prev = curr;
            curr = curr.next;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        return head;
    }
}
```


## Merge Sort

```java
//recursive
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        doSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void doSort(int[] nums, int from, int to) {
        if (from < to) {
            int mid = from + ((to - from) >> 1);
            doSort(nums, from, mid);
            doSort(nums, mid + 1, to);
            merge(nums, from, to, mid);
        }
    }

    private void merge(int[] nums, int from, int to, int mid) {
        int a = from;
        int b = mid + 1;
        int[] help = new int[to - from + 1];
        int i = 0;
        for (; a <= mid && b <= to; i++) {
            help[i] = nums[a] < nums[b] ? nums[a++] : nums[b++];
        }
        for (; a <= mid; i++) {
            help[i] = nums[a++];
        }
        for (; b <= to; i++) {
            help[i] = nums[b++];
        }
        for (int j = 0; j < help.length; j++) {
            nums[from + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 1, 4, 2, 4, 5, 6};
        new Solution().sortArray2(nums);
        System.out.println(Arrays.toString(nums));
    }
}

```

```java
//iteration
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int len = nums.length;
        int step = 1;
        for (; step <= len; ) {

            for (int from = 0; from < len; from = from + step * 2) {
                int to = Math.min(from + step * 2 - 1, len - 1);
                int mid = from + step - 1;
                if (mid > len - 1) {
                    mid = from + ((to - from) >> 1);
                }
                merge(nums, from, to, mid);

            }

            step = step << 1;
        }

        return nums;
    }

//    public int[] sortArray(int[] nums) {
//        return doSortArray0(nums, 0, nums.length - 1);
//    }
//
//    private int[] doSortArray0(int[] nums, int from, int to) {
//        if (from < to) {
//            int mid = from + ((to - from) >> 1);
//            doSortArray0(nums, from, mid);
//            doSortArray0(nums, mid + 1, to);
//            merge(nums, from, to, mid);
//        }
//        return nums;
//    }

    private void merge(int[] nums, int from, int to, int mid) {
        if (from > to) {
            System.out.println(123);
        }
        int a = from;
        int b = mid + 1;
        int[] help = new int[to - from + 1];
        int i = 0;
        for (; a <= mid && b <= to; i++) {
            help[i] = nums[a] < nums[b] ? nums[a++] : nums[b++];
        }
        for (; a <= mid; i++) {
            help[i] = nums[a++];
        }
        for (; b <= to; i++) {
            help[i] = nums[b++];
        }
        for (int j = 0; j < help.length; j++) {
            nums[j + from] = help[j];
        }
    }


    public static void main(String[] args) {
//        [5,1,1,2,0,0]
        int[] nums = {-96, -91, -90, -87, -85, -84, -84, -78, -74, -74, -72, -71, -69, -68, -63, -58, -56, -55, -46, -46, -45, -44, -44, -30, -28, -28, -25, -24, -24, -22, -20, -20, -13, -13, -12, -11, -10, -9, -8, -6, -5, -3, -2, -2, 2, 2, 8, 10, 10, 11, 12, 13, 14, 16, 21, 23, 25, 25, 26, 26, 28, 29, 29, 30, 34, 35, 35, 37, 45, 47, 48, 50, 50, 51, 52, 54, 55, 56, 57, 57, 63, 64, 65, 65, 72, 73, 80, 85, 86, 86, 91, 92, 92, 93, 95, 98, -39, 77};
        new Solution().sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }
}
```


```java
//剑指 Offer 51. 数组中的逆序对  LCOF
class Solution {
    public int reversePairs(int[] nums) {
        return doSort(nums, 0, nums.length - 1);
    }

    private int doSort(int[] nums, int from, int to) {
        int ans = 0;
        if (from < to) {
            int mid = from + ((to - from) >> 1);
            ans = ans + doSort(nums, from, mid);
            ans = ans + doSort(nums, mid + 1, to);
            ans = ans + merge(nums, from, to, mid);
        }
        return ans;
    }

    private int merge(int[] nums, int from, int to, int mid) {
        int ans = 0;
        int a = from;
        int b = mid + 1;
        int[] help = new int[to - from + 1];
        int i = 0;
        for (; a <= mid && b <= to; ) {
            if (nums[a] > nums[b]) {
                ans = ans + (to - b + 1);
                help[i++] = nums[a++];
            } else {
//                if (nums[a] != nums[b]) {

//                }
                help[i++] = nums[b++];
            }
        }
        for (; a <= mid; ) {
            help[i++] = nums[a++];
        }
        for (; b <= to; ) {
            help[i++] = nums[b++];
        }

        for (int j = 0; j < help.length; j++) {
            nums[j + from] = help[j];
        }
        return ans;
    }

    public int reversePairs0(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        //5
        int i = new Solution().reversePairs(new int[]{7, 5, 6, 4});
        System.out.println(i);
    }
}
```


## Small Sum
```java

class Solution {

    private int smallSum(int[] arr) {
        return doSort(arr, 0, arr.length - 1);
    }

    private int doSort(int[] arr, int from, int to) {
        int ans = 0;
        if (from < to) {
            int mid = from + ((to - from) >> 1);
            ans = ans + doSort(arr, from, mid);
            ans = ans + doSort(arr, mid + 1, to);
            ans = ans + merge(arr, from, to, mid);
        }
        return ans;
    }

    private int merge(int[] arr, int from, int to, int mid) {
        int ans = 0;
        int a = from;
        int b = mid + 1;
        int[] help = new int[to - from + 1];
        int i = 0;
        for (; a <= mid && b <= to; ) {
            if (arr[a] < arr[b]) {
                help[i++] = arr[a++];
                ans = ans + (to - b + 1);
            } else {
                help[i++] = arr[b++];
            }
        }
        for (; a <= mid; ) {
            help[i++] = arr[a++];
        }
        for (; b <= to; ) {
            help[i++] = arr[b++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[j + from] = help[j];
        }
        return ans;
    }

    private int smallSum0(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    ans = ans + 1;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {1, 45, 125, 2};
        System.out.println(new Solution().smallSum0(arr));

        System.out.println(new Solution().smallSum(arr));

    }
}
```



```java
// Top K Frequent Elements

class Solution {
    public static void main(String[] args) {
        int[] ints = new Solution().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> num2count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (num2count.containsKey(nums[i])) {
                num2count.put(nums[i], num2count.get(nums[i]) + 1);
            } else {
                num2count.put(nums[i], 1);
            }
        }

        PriorityQueue<Integer[]> queue = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        num2count.forEach((num,count)->{
            queue.offer(new Integer[]{count, num});
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[1];
        }
        return ans;
    }
}
```


```java


// recursive reverse stack

class Solution {

    public static void reverse(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty() || stack.size() == 1) {
            return;
        }
        Integer pop = stack.pop();
        reverse(stack);
        insert2bottom(stack, pop);
    }

    private static void insert2bottom(Stack<Integer> stack, Integer el) {
        if (stack.isEmpty()) {
            stack.push(el);
            return;
        }
        Integer pop = stack.pop();
        insert2bottom(stack, el);
        stack.push(pop);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        reverse(stack);

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

    }
}
```