import java.util.*;



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
