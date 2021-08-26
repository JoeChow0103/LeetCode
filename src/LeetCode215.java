import java.util.PriorityQueue;

public class LeetCode215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < k; i++){
            heap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++){
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.add(nums[i]);
            }
        }
        return heap.peek();
    }
//    public int findKthLargest(int[] nums, int k) {
//        if(nums == null || nums.length == 0) return -1;
//
//        quickSort(nums, 0, nums.length - 1, k, nums.length);
//
//        return nums[nums.length - k];
//    }
//
//    private void quickSort(int[] nums, int low, int high, int k, int length){
//        if(low >= high) return;
//
//        int pivotIndex = findPivotPartition(nums, low, high);
//        if(pivotIndex == length - k) return;
//        if(pivotIndex > length - k){
//            quickSort(nums, low, pivotIndex - 1, k, length);
//        } else {
//            quickSort(nums, pivotIndex + 1, high , k, length);
//        }
//
//
//    }
//
//    private int findPivotPartition(int[] nums, int low, int high){
//        int left = low;
//        int right = high;
//        int pivotIndex = (int)Math.random()*(right - left + 1) + left;
//        int pivotVal = nums[pivotIndex];
//        swap(nums, pivotIndex, right);
//        right--;
//        while(left <= right){
//            if(nums[left] < pivotVal){
//                left++;
//
//            } else if(nums[right] >= pivotVal){
//                right--;
//            } else {
//                swap(nums, left++, right++);
//            }
//        }
//        swap(nums, left, high);
//        return left;
//    }
//
//    private void swap(int[] nums, int i, int j){
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
}
