public class LeetCode42 {
    /*
    solution 1: every point, find the left highest height, and the right highest height, the smaller
    for every bar, find min(left_max, right_max), the count is the result - height; O(n^2)
    solution 2: every time, we repeatedly find the max height, can store reuse the left and right max height;
    preprocessing DP: store the minimum max height for the current point
    minus the height, and sum them to get the result; Time: O(N), Space: O(2N)
    solution 3: DP update the left_max and right_max
    left, right who's max small move who
    case1: if left_max < right_max
        left++, update left_max to minMax if possilbe
    case2: if left_max > right_max
        right--, update right_max to minMax if possible
    case3: left_max == right_max
    left++;
        right--;
        update;
    Time O(n)	Space O(n)
    solution 4: Time O(n), Space O(1)
     */
//    public int trap(int[] height){
    // solution 2
//        if(height == null || height.length ==0) return 0;
//        int len = height.length;
//        int[] leftMax = new int[len];
//        leftMax[0] = height[0];
//        int[] rightMax = new int[len];
//        rightMax[len-1] = height[len-1];
//        int[] minMax = new int[len];
//        int leftCurMax = leftMax[0];
//        int rightCurMax = rightMax[len-1];
//        int trapWater = 0;
//        for(int i = 1; i < len; i++){
//            if(height[i] > leftCurMax){
//                leftCurMax = height[i];
//            }
//            leftMax[i] = leftCurMax;
//        }
//
//        for(int j = len - 2; j>=0; j--){
//            if(height[j] > rightCurMax){
//                rightCurMax = height[j];
//            }
//            rightMax[j] = rightCurMax;
//        }
//
//        for(int i = 0; i < len; i++){
//            int h = Math.min(leftMax[i], rightMax[i]);
//            trapWater += (h-height[i]);
//        }
//        return trapWater;
//    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int leftMax = height[0];
        int rightMax = height[len - 1];
        int water = 0;
        while(left <= right){//最好写成<=, when two same peak, always move one point
            if(leftMax < rightMax){
                leftMax = Math.max(height[left], leftMax);
                water += (leftMax - height[left]);//移动left之前先把水量加上去
                left++;
            }else{//把==的情况吸收进去，虽然只移动一边，但是再次进入while loop的时候会更新
                rightMax = Math.max(height[right], rightMax);
                water += (rightMax - height[right]);
                right--;
            }
        }
        return water;

    }
}
