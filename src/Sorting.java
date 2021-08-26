import java.util.*;

public class Sorting {
}

class BuckSort {
    public double[] sort(double[] data) {
        return bucket_sort(data);
    }

    public double[] bucket_sort(double[] data) {
        double[] des = new double[data.length];
        Bucket[] tmp = new Bucket[10];
        // initial bucket
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new Bucket(0, null);
        }

        for (int i = 0; i < data.length; i++) {
            Bucket bucket = new Bucket(data[i], null);
            int bucket_list_index = (int) (data[i] * 10);
            bucket_in_sort(tmp[bucket_list_index], bucket);
        }

        int j = 0;
        for (int i = 0; i < tmp.length; i++) {
            Bucket tmp_bucket = tmp[i].next;
            while (tmp_bucket != null) {
                des[j] = tmp_bucket.value;
                tmp_bucket = tmp_bucket.next;
                j++;
            }
        }
        return des;
    }

    public void bucket_in_sort(Bucket sourct_bucket, Bucket bucket) {
        Bucket tmp = sourct_bucket.next;
        if (tmp == null) {
            sourct_bucket.next = bucket;
            return;
        }

        while (tmp.next != null) {
            if (tmp.value > bucket.value) {
                bucket.next = sourct_bucket.next;
                sourct_bucket.next = bucket;
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = bucket;
    }

    public class Bucket {
        double value;
        public Bucket next;

        public Bucket(double value, Bucket bucket) {
            this.value = value;
            this.next = bucket;
        }

        public double getValue(double value) {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public Bucket getBucketList() {
            return next;
        }

        public void setBucketList(Bucket next) {
            this.next = next;
        }
    }
}

class MergeSort {
//        s1: Array with helper
// carry on the same size helper array during recursion, space O(n)
        public int[] mergeSort(int[] array) {
            // corner case
            if (array == null || array.length <= 1) {
                return array;
            }
            int[] helper = new int[array.length];
            // method overloading
            mergeSort(array, helper, 0, array.length - 1);
            return array;
        }

        private void mergeSort(int[] array, int[] helper, int left, int right) {
            // recursion base case
            if (left == right) {
                return;
            }
            int mid = left + (right - left) / 2;
            mergeSort(array, helper, left, mid);
            mergeSort(array, helper, mid + 1, right);
            merge(array, helper, left, mid, right);
        }

        private	 void merge(int[] array, int[] helper, int left, int mid, int right) {
            for (int i = left; i <= right; i++) {
                helper[i] = array[i];
            }
            int leftIndex = left;
            int rightIndex = right;
            int cur = left;
            while (leftIndex <= mid && rightIndex <= right) {
                if (helper[leftIndex] < helper[rightIndex]) {
                    array[cur++] = helper[leftIndex++];
                } else { // for duplicate
                    array[cur++] = helper[rightIndex++];
                }
            }

            // remaining elements on left side
            while (leftIndex <= mid) {
                array[left++] = helper[leftIndex++];
            }
            // remaining elements on right side
            while (rightIndex <= right) {
                array[left++] = helper[rightIndex++];
            }
        }


//        S2: ArrayList
// Classical Merge Sort with new ArrayList each recursion level
        public ArrayList<Integer> mergeSort(ArrayList<Integer> array) {
            // Corner case
            if (array == null || array.size() <= 1) {
                return array;
            }
            return mergeSort(array, 0, array.size() - 1);
        }

        private ArrayList<Integer> mergeSort(ArrayList<Integer> array, int left, int right) { // overloading
            ArrayList<Integer> res = new ArrayList<>();
            // base case
            if (left == right) {
                res.add(array.get(left));
                return res;
            }

            int mid = left + (right - left) / 2;
            ArrayList<Integer> leftRes = mergeSort(array, left, mid);
            ArrayList<Integer> rightRes = mergeSort(array, mid + 1, right);
            merge(leftRes, rightRes, res);
            return res;
        }

        private void merge(ArrayList<Integer> leftRes, ArrayList<Integer> rightRes, ArrayList<Integer> res) {
            int leftIndex = 0;
            int rightIndex = 0;
            while (leftIndex < leftRes.size() && rightIndex < rightRes.size()) {
                if (leftRes.get(leftIndex) < rightRes.get(rightIndex)) {
                    res.add(leftRes.get(leftIndex++));
                } else {
                    res.add(rightRes.get(rightIndex++));
                }
            }

            // remaining element on left side
            while (leftIndex < leftRes.size()) {
                res.add(leftRes.get(leftIndex++));
            }
            // remaining element on right side
            while (rightIndex < rightRes.size()) {
                res.add(rightRes.get(rightIndex++));
            }
        }

    }


class QuickSort {
//    s1: towards pointers
    public int[] quickSort(int[] array) {
        // cc
        if (array == null || array.length <= 1) return array;
        int left = 0, right = array.length - 1;
        quickSort(array, left, right);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) return; // what if left > right
        // find pivot position and do partition
        int pivotIdx = findPivotAndPartition(array, left, right);
        quickSort(array, left, pivotIdx - 1);
        quickSort(array, pivotIdx + 1, right);
    }

    private int findPivotAndPartition(int[] array, int left, int right) {
        int leftIdx = left, rightIdx = right;
        Random rand = new Random();
        int pivot = left + rand.nextInt(right - left + 1);
        int pivotVal = array[pivot];

        swap(array, pivot, right);
        rightIdx--;
        while (leftIdx <= rightIdx) {
            if (array[leftIdx] < pivotVal) {
                leftIdx++;
            } else if (array[rightIdx] >= pivotVal) { // for duplicate
                rightIdx--;
            } else {
                swap(array, leftIdx++, rightIdx--);
            }
        }
        swap(array, leftIdx, right);
        return leftIdx;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

//    s2: slow & fast
    public int[] quickSort1(int[] array) {
        // cc
        if (array == null || array.length <= 1) return array;
        int left = 0, right = array.length - 1;
        quickSort1(array, left, right);
        return array;
    }

    private void quickSort1(int[] array, int left, int right) {
        if (left >= right) return; // what if left > right
        // find pivot position and do partition
        int pivotIdx = findPivotAndPartition1(array, left, right);
        quickSort(array, left, pivotIdx - 1);
        quickSort(array, pivotIdx + 1, right);
    }

    private int findPivotAndPartition1(int[] array, int left, int right) {
        int leftIdx = left, rightIdx = right;
        Random rand = new Random();
        int pivot = left + rand.nextInt(right - left + 1);
        int pivotVal = array[pivot];
        swap(array, pivot, right);

        int slow = left, fast = left;
        while (fast <= right) {
            if (array[slow] < pivotVal) {
                slow++;
            } else if (array[fast] > pivotVal) {
                fast++;
            } else {
                swap(array, slow, fast);
            }
        }
        swap(array, slow, right);
        return slow;
    }

}
