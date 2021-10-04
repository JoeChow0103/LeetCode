class ArraySegmentTree<T> {
    private T tree[];
    private T data[];

    private Merger<T> merger;

    public interface Merger<T> {
        T merge(T a, T b);
    }

    public ArraySegmentTree(T[] arr, Merger<T> merger) {
        this.merger = merger;
        data = (T[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        this.tree = (T[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int treeLeft, int treeRight) {
        if (treeLeft == treeRight) {
            tree[treeIndex] = data[treeLeft];
            return;
        }

        int leftTreeIndex = getLeft(treeIndex);
        int rightTreeIndex = getRight(treeIndex);
        int mid = treeLeft + (treeRight - treeLeft) / 2;
        buildSegmentTree(leftTreeIndex, treeLeft, mid);
        buildSegmentTree(rightTreeIndex, mid, treeRight);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public T query(int start, int end) {
        return query(0, 0, data.length - 1, start, end);
    }

    private T query(int treeIndex, int treeLeft, int treeRight, int queryL, int queryR) {
        if (treeLeft == queryL && treeRight == queryR) {
            return tree[treeIndex];
        }

        int mid = treeLeft + (treeRight - treeLeft) / 2;
        int leftTreeIndex = getLeft(treeIndex);
        int rightTreeIndex = getRight(treeIndex);
        if (queryR <= mid) {
            return query(leftTreeIndex, treeLeft, mid, queryL, queryR);
        }
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, treeRight, queryL, queryR);
        }
        T left = query(leftTreeIndex, treeLeft, mid, queryL, queryR);
        T right = query(rightTreeIndex, mid + 1, treeRight, queryL, queryR);
        return merger.merge(left, right);
    }

    private void update(int index, T e) {
        data[index] = e;
        update(0, 0, data.length - 1,index, e);
    }

    private void update(int treeIndex, int treeLeft, int treeRight, int index, T e) {
        if (treeLeft == treeRight) {
            tree[treeIndex] = e;
            return;
        }

        int mid = treeLeft + (treeRight - treeLeft) / 2;
        int leftChildIndex = getLeft(treeIndex);
        int rightChildIndex = getRight(treeIndex);

        if (index <= mid) {
            update(leftChildIndex, treeLeft, mid, index, e);
        } else if (index >= mid + 1) {
            update(rightChildIndex, mid + 1, treeRight, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public int getLeft(int index) {
        return index * 2 + 1;
    }

    public int getRight(int index) {
        return index * 2 + 2;
    }
}
