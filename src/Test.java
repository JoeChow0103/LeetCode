import java.util.*;

class Solution14 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}

class Solution13 {
    public static List<Integer> personKnowSecret(int[][] list, int person) {
        List<int[]> sortList = Arrays.asList(list);
        Collections.sort(sortList, (l1, l2) -> l1[2] - l2[2]);
        UnionFind uf = new UnionFind(list.length * 2);

        int curTime = sortList.get(0)[2];

        for (int[] l : sortList) {
            int x = l[0];
            int y = l[1];
            if (curTime != l[2]) {
                curTime = l[2];
                uf.un_union(person);
            }
            uf.union(x, y);
        }

        return uf.getRes(person);
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x){
            int cur = x;
            while (cur != parent[cur]) {
                parent[cur] = parent[parent[cur]];
                cur = parent[cur];
            }
            parent[x] = cur;
            return cur;
        }

        public void union(int x, int y) {
            int rootX = parent[x];
            int rootY = parent[y];
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) {
                union(y, x);
            } else {
                parent[rootY] = rootX;
                rank[rootX] += rank[rootY];
            }
        }

        public void un_union(int x) {
            for (int i = 0; i < parent.length; i++) {
                if (find(i) != x) {
                    parent[i] = i;
                    rank[i] = 1;
                }
            }
        }

        public List<Integer> getRes(int x) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < parent.length; i++) {
                if (find(i) == x) {
                    res.add(i);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] list = {{1, 2, 100},{1, 4, 200},{2, 4, 200},{5, 6, 200},{7, 8, 400}};
        List<Integer> people = personKnowSecret(list, 4);
        for(int p : people) System.out.println(p);
    }
}

class Solution12 {
    /*
    1. create a circle of radius R1 centered at R2
    2. create a donut by rotating about the y axis
    3. spin the donut around the x and z axes
    4. project donut onto 2D screen
    5. determine illumination by calculating surface normal (given a light source)
     */
    public static void main(String[] args) {
        int k;
        double A = 0, B = 0, i, j;
        double[] z = new double[1760];
        char[] b = new char[1760];
        System.out.print("\u001b[2J");
        for (; ; ) {
            Arrays.fill(b, 0, 1760, ' ');
            Arrays.fill(z, 0, 1760, 0);
            for (j = 0; 6.28 > j; j += 0.07)
                for (i = 0; 6.28 > i; i += 0.02) {
                    double c = Math.sin(i),
                            d = Math.cos(j),
                            e = Math.sin(A),
                            f = Math.sin(j),
                            g = Math.cos(A),
                            h = d + 2,
                            D = 1 / (c * h * e + f * g + 5),
                            l = Math.cos(i),
                            m = Math.cos(B),
                            n = Math.sin(B),
                            t = c * h * g - f * e;
                    int x = (int) (40 + 30 * D * (l * h * m - t * n)),
                            y = (int) (12 + 15 * D * (l * h * n + t * m)),
                            o = x + 80 * y,
                            N = (int) (8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));
                    if (22 > y && y > 0 && x > 0 && 80 > x && D > z[o]) {
                        z[o] = D;
                        b[o] = new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'}[Math.max(N, 0)];
                    }
                }
            System.out.print("\u001b[H");
            for (k = 0; 1761 > k; k++)
                System.out.print(k % 80 > 0 ? b[k] : 10);
            A += 0.04;
            B += 0.02;
        }
    }
}

class Solution11 {
    public int numSquare(int[][] points) {
        HashSet<int[]> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                set.add(p1);
                set.add(p2);
                int[][] possiblePairs = possiblePairs(p1, p2);
                int[] p3 = possiblePairs[0];
                int[] p4 = possiblePairs[1];
                if (set.contains(p3) && set.contains(p4)) {
                    count++;
                }
            }
        }
        return count / 2;
    }
    public int[][] possiblePairs(int[] p1, int[] p2) {//diagonal
        int x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1];
        int x3 = (x1 + x2 + y2 - y1) / 2;
        int y3 = (y1 + y2 + x1 - x2) / 2;
        int x4 = (x1 + x2 - y2 + y1) / 2;
        int y4 = (y1 + y2 - x1 + x2) / 2;
        return new int[][] {{x3, y3}, {x4, y4}};
    }
}

class Solution10 {
    public static int binaryProduct(int a, int b) {
        int b1 = a, b2 = b;
        int digit,factor = 1;
        int multiply = 0;
        while (b2 != 0) {
            digit = (b2 % 10);
            if (digit == 1) {
                b1 = b1 * factor;
                multiply = cal(b1, multiply);
            } else {
                b1 = b1 * factor;
            }
            b2 = b2 / 10;
            factor = 10;
        }
        return multiply;
    }

    public static int cal(int b1, int b2) {
        int i = 0;
        int remainder = 0;
        int result = 0;
        int sum[] = new int[50];
        while (b1 != 0 || b2 != 0) {
            sum[i++] = (b1 % 10 + b2 % 10 + remainder) % 2;
            remainder = (b1 % 10 + b2 % 10 + remainder) / 2;
            b1 = b1 / 10;
            b2 = b2 / 10;
        }

        if (remainder != 0) {
            sum[i++] = remainder;
        }

        while (--i >= 0) {
            result = result * 10 + sum[i];
        }
        return result;
    }

    public static int toBinary(int num) {
        int binary = 0;
        int bi[] = new int[20];
        int i = 0;
        while (num > 0) {
            bi[i++] = num % 2;
            num = num / 2;
        }
        while ( --i >= 0) {
            binary = binary * 10 + bi[i];
        }
        return binary;
    }

    public static void main(String[] args) {
        System.out.println(binaryProduct(11100, 10101));
    }
}

class Solution9 {
    static int splitIntoTwo(List<Integer> arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        int sum1 = 0, sum2 = 0;
        int count = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            sum1 += arr.get(i);
            sum2 = total - sum1;
            if (sum1 > sum2) count++;
            System.out.println("sum1 "+sum1);
            System.out.println("sum2 "+sum2);
            System.out.println("count " + count);
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(5, -3, -2, 10, 20, -30);
        /*
        left: 5
        right: -5
         */
        System.out.println(splitIntoTwo(arr));
    }
}

class Solution8 { // don't change anything
    public static List<String> process(String s) {
        String[] arr = s.split("\n");
        List<String> res = new ArrayList<>();
        String num;

        for (String str : arr) {
            if (str.charAt(0) == '#') {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < str.length(); i++) {
                    Character c = str.charAt(i);
                    if (c == ' ') break;
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    }
                }
                num = sb.toString();
                res.add("[" + str + "]" + "(src/LeetCode" + num + ".java)");
            }
        }
        return res;
    }

    public static List<String> merge(List<String> l1, List<String> l2, List<String> l3, List<String> l4) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            sb.append(l1.get(i));
            sb.append("|");
            sb.append(l2.get(i));
            sb.append("|");
            sb.append(l3.get(i));
            sb.append("|");
            sb.append(l4.get(i));
            sb.append("|");
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        List<String> l1 = process(s1);
        List<String> l2 = process(s2);
        List<String> l3 = process(s3);
        List<String> l4 = process(s4);
        List<String> res = merge(l1, l2, l3, l4);
        for (String str : res) System.out.println(str);
    }
}

class Solution7 {
    int[] clockDiditCount(int[] startTime, int[] finishTime) {
        int[] res = new int[10];

        int entrySecond, entryMinute;
        int second = startTime[2];
        int minute = startTime[1];
        int hour = startTime[0];
        while (hour != finishTime[0] || minute != finishTime[1] || second != finishTime[2]) {
            process(hour, res);
            process(minute, res);
            process(second, res);
            second++;
            entrySecond = second / 60;
            second = second % 60;

            minute += entrySecond;
            entryMinute = minute / 60;
            minute = minute % 60;

            hour += entryMinute;
            hour = hour % 24;
        }
        process(hour, res);
        process(minute, res);
        process(second, res);
        return res;
    }

    public void process(int time, int[] res) {
        if (time < 10) {
            res[0]++;
        }
        while (time > 0) {
            int idx = time % 10;
            res[idx] += 1;
            time = time / 10;
        }
    }

    public static void main(String[] args) {
        Solution7 solution = new Solution7();
//        int[] res = new int[10];
        int start[] = {13, 20, 58};
        int finish[] = {14, 21, 00};
//        int start[] = {13, 24, 5};
//        int finish[] = {13, 24, 20};
        int res[] = solution.clockDiditCount(start, finish);
        for (int digit : res) System.out.println(digit);
//        solution.process(123, res);
//        for (int digit : res) System.out.println(digit);
    }

}

class Solution6 {
    static int distanceTraversal(List<List<Integer>> lot) {
        int cols = lot.size();
        int rows = lot.get(0).size();
        // c.c.

        int[][] dirs = new int[][]{{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int curRow = cur / cols;
                int curCol = cur % cols;
                for (int[] dir : dirs) {
                    int nextRow = curRow + dir[0];
                    int nextCol = curCol + dir[1];
                    if (nextRow >= 0 && nextRow < rows
                    && nextCol >= 0 && nextCol < cols
                    && lot.get(nextRow).get(nextCol) == 1) {
                        lot.get(nextRow).set(nextCol, 0);
                        queue.offer(nextRow * cols + nextCol);
                    }
                    if (nextRow >= 0 && nextRow < rows
                            && nextCol >= 0 && nextCol < cols
                            && lot.get(nextRow).get(nextCol) == 9) {
                        return step + 1;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

class Solution5 {
    private enum Size {
        SMALL, MEDIUM, LARGE
    }
    private class Locker{
        private Size size;
        private String id;
        private int[] location;
        private boolean isEmpty;
    }

    private class Package {
        private Size size;
        private String id;
    }

    private Locker[] lockers;

    public String findBestLocker(Package pkg) {
        for (Locker locker : lockers) {
            if (locker.isEmpty && locker.size == pkg.size) {
                locker.isEmpty = false;
                return locker.id;
            }
        }
        return null;
    }
}

class Solution4 {
    public static final String SECRECT = "SLICES";

    public String findSecret(String guess) {
        char[] resChars = new char[guess.length()];
        char[] secChars = SECRECT.toCharArray();
        char[] chars = guess.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char secCur = secChars[i];
            char temp = chars[i];
            if (secCur == temp) {
                resChars[i] = temp;
            }
        }
        return resChars.toString();
    }
}

class Solution3 {
    int ans = 0;
    public boolean isCousin(TreeNode root, int x, int y) {
        // corner case
        if (root == null) return false;

        return getHeight(root, x) == getHeight(root, y);
    }

    private int getHeight(TreeNode root, int x) {
        if (root == null) {
            return - 1;
        }
        int leftHeight = getHeight(root.left, x);
        int rightHeight = getHeight(root.right, x);
        int height = Math.max(leftHeight, rightHeight);
        if (root.val == x) {
            height = ans;
        }
        return ans;
    }

    public static String getArrayString(String[] array) {
        return String.join("\n", array);
    }

    public static void main(String[] args) {
        String[] input =  { "DEPEND TELNET TCPIP NETCARD\n",
                "DEPEND TCPIP NETCARD\n",
                "DEPEND NETCARD TCPIP\n",
                "DEPEND TCPIP TELNET\n",
                "END\n",
        };
        System.out.println(getArrayString(input));
    }
}

class Solution2 {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xHeight = -1;
    int yHeight = -1;

    public boolean isSibling(TreeNode root, int x, int y) {
        getHeightAndParent(root, x, y, 0, null);
        return xHeight == yHeight && xParent != yParent ? true : false;
    }

    private void getHeightAndParent(TreeNode root, int x, int y, int height, TreeNode parent) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            xParent = parent;
            xHeight = height;
        } else if (root.val == y) {
            yParent = parent;
            yHeight = height;
        }
        getHeightAndParent(root.left, x, y, height + 1, root);
        getHeightAndParent(root.right, x, y, height + 1, root);
    }

}

class Solution1 {
    public static char[] generator() {
        int len = (int) (Math.random()*10+10);
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";
        String values = Capital_chars + Small_chars +
                numbers + symbols;

        Random random = new Random();

        char[] password = new char[len];

        // insert random (len-4) random element to password
        for (int i = 0; i < len - 4; i++)
        {
            password[i] =
                    values.charAt(random.nextInt(values.length()));

        }
        int lenUpper = Capital_chars.length();
        int lenLower = Small_chars.length();
        int lenNumber = numbers.length();
        int lenSymbol = symbols.length();
        int idxUpper = (int) (Math.random() * lenUpper + 0);
        int idxLower = (int) (Math.random() * lenLower + lenUpper);
        int idxNumber = (int) (Math.random() * lenNumber + (lenUpper + lenLower));
        int idxSymbol = (int) (Math.random() * lenSymbol+ (lenUpper + lenLower + lenNumber));
        int[] alternative = new int[] {idxUpper, idxLower, idxNumber, idxSymbol};
        int[] post = new int[4];
        for (int i = 0; i < 4; i++) {
            post[random.nextInt(4)] = alternative[i];
        }

        int count = 0;
        for (int i = len - 4; i < len; i++) {
            password[i] = values.charAt(post[count++]);
        }

        return password;
    }
    public static void main(String[] args)
    {
        // Length of your password as I have choose
        // here to be 8
        Solution1 solution = new Solution1();
        System.out.println(solution.generator());
    }
}

class Solution {
    public List<String> sortBoxes(List<String> boxList) {
        boxList.sort(new Comparator<String>() {
            @Override
            public int compare(String log1, String log2) {
                String[] split1 = log1.split(" ", 2);
                String[] split2 = log2.split(" ", 2);
                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
                if (!isDigit1 && !isDigit2) {
                    int cmp = split1[1].compareTo(split2[1]);
                    if (cmp != 0) return cmp;
                    return split1[0].compareTo(split2[0]);
                }
                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            }
        });
        return boxList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] array = {"ykc 82 01",
                "eo first qpx",
                "09z cat hamster",
                "06f 12 25 6",
                "az0 first qpx",
                "236 cat dog rabbit snake"};
        List<String> boxList = Arrays.asList(array);
        System.out.println(solution.sortBoxes(boxList));
    }
}

class SimpleDate {
    public int month;
    public int year;

    public SimpleDate(){

    }

    public static SimpleDate now(){
        // return the current date
        return null;
    }
}

//class Package {
//    SimpleDate startDate;
//    int baseSalary;
//    int RSU;
//    int bonus;
//
//    public Package(SimpleDate startDate, int baseSalary, int RSU, int bonus) {
//        this.startDate = startDate;
//        this.baseSalary = baseSalary;
//        this.RSU = RSU;
//        this.bonus = bonus;
//    }
//}

class Calculator {
    /*
    1. situation 1: the baseSalary don't change --> final
    2. situation 2: the month change, the salary change, hashmap, a wrapper
     */
    private final SimpleDate startDate;
    private int baseSalary;
    private final int RSU;
    private final int bonus;

    public Calculator(SimpleDate startDate, int baseSalary, int RSU, int bonus) {
        this.startDate = startDate;
        this.baseSalary = baseSalary;
        this.RSU = RSU;
        this.bonus = bonus;
    }

    public void updateBaseSalary(int newBaseSalary) {
        this.baseSalary = newBaseSalary;
    }

    public void calculate() {
        SimpleDate currentDate = SimpleDate.now();
        int startYear = startDate.year;
        int startMonth = startDate.month;
        int currentYear = currentDate.year;
        int currentMonth = currentDate.month;
        int salary = 0;
        for (int year = startYear; year <= currentYear; year++) {
            if (year == startYear) {
                int rangeMonth = 12 - startDate.month + 1;
                salary = (baseSalary + RSU) * rangeMonth / 12 + bonus;
//                System.out.println(year + ": " + salary);
            } else if (year == currentYear) {
                int rangeMonth = currentMonth - 1;
                salary  = (baseSalary + RSU) * rangeMonth / 12;
//                System.out.println(year + ": " + salary);
            } else {
                salary = baseSalary + RSU;
//                System.out.println(year + ": " + salary);
            }
            System.out.println(year + ": " + salary);
        }
//        System.out.println(); // "year : (salary + bonus) [salary + bonus]"
    }
}

public class Test {

}
