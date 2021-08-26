public class RankFromStream {
    /*
    Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
    getRankOfNumber(1) = 0
    getRankOfNumber(3) = 1
    getRankOfNumber(4) = 3

    BST
     */
    RankNode root = null;

    public int getRankOfNumber(int number) {
        return root.getRank(number);
    }

    public void track(int number) {
        if (root == null) {
            root = new RankNode(number);
        } else {
            root.insert(number);
        }
    }

    private class RankNode {
        public int left_size = 0;
        public RankNode left, right;
        public int data = 0;

        public RankNode(int d) {
            data = d;
        }

        public void insert(int d) {
            if (d < data) {
                if (left != null) left.insert(d);
                else left = new RankNode(d);
                left_size++;
            } else {
                if (right != null) right.insert(d);
                else right = new RankNode(d);
            }
        }

        public int getRank(int d) {
            if (d == data) {
                return left_size;
            } else if (d < data) {
                return left == null ? -1 : left.getRank(d);
            } else {
                int right_rank = right == null ? -1 : right.getRank(d);
                if (right_rank == -1) return -1;
                else return left_size + 1 + right_rank;
            }
        }
    }
}


