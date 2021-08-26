import java.util.*;

class FileFilterProcessor {
    /*
    if need add more operator, use interface OptrBase{} to abstract
    interface OptrBase{
        abastract boolean combine(boolean b1, boolean b2);
    }
     */
    public static List<MyFile> filterGo(List<MyFile> files, FileFilterNode root) {
        List<MyFile> res = new ArrayList<>();
        if (files == null || files.size() == 0)
            return res;

        for (MyFile f : files) {
            if (filt(f, root))
                res.add(f);
        }
        return res;
    }

    private static boolean filt(MyFile fileName, FileFilterNode root) {
        if (root == null) {
            return true;
        }
        boolean ans = false;
        if (root.isOperatorNode()) {
            boolean left = filt(fileName, root.left);
            boolean right = filt(fileName, root.right);
            ans = root.operator.check(left, right);
        } else
            ans = root.filter.filt(fileName);
        return ans;
    }
}

interface FileFilterInterface {
    abstract boolean filt(MyFile file);
}

class SizeFileFilter implements FileFilterInterface {
    private int size;

    public SizeFileFilter(int size) {
        this.size = size;
    }

    @Override
    public boolean filt(MyFile fileName) {
        return fileName.getSize() == size;
    }
}

class SuffixFileFilter implements FileFilterInterface {
    private String suffix;

    public SuffixFileFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean filt(MyFile fileName) {
        return fileName.getSuffix().equals(suffix);
    }
}

class CreatorFileFilter implements FileFilterInterface {
    private String creator;

    public CreatorFileFilter(String creator) {
        this.creator = creator;
    }

    @Override
    public boolean filt(MyFile fileName) {
        return fileName.getCreator().equals(creator);
    }
}

interface OperatorInterface {
    abstract boolean check(boolean op1, boolean op2);
}

class OrOperator implements OperatorInterface {
    @Override
    public boolean check(boolean op1, boolean op2) {
        return op1 || op2;
    }
}

class AndOperator implements OperatorInterface {
    @Override
    public boolean check(boolean op1, boolean op2) {
        return op1 && op2;
    }
}

class FileFilterNode {
    OperatorInterface operator;
    FileFilterInterface filter;
    FileFilterNode left;
    FileFilterNode right;

    public FileFilterNode(FileFilterNode leftC, FileFilterNode rightC, OperatorInterface oper) {
        this.operator = oper;
        if (leftC != null)
            this.left = leftC;
        if (rightC != null)
            this.right = rightC;
    }

    public FileFilterNode(FileFilterInterface filter) {
        this.filter = filter;
        this.left = null;
        this.right = null;
        this.operator = null;
    }

    public boolean isOperatorNode() {
        return operator != null;
    }
}

class MyFile {
    private String name;
    private String suffix;
    private String creator;
    private int size;

    public MyFile(String name, String suffix, String creator, int size) {
        this.name = name;
        this.suffix = suffix;
        this.creator = creator;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }
    public String getSuffix() {
        return this.suffix;
    }
    public String getCreator() {
        return this.creator;
    }
    public int getSize() {
        return this.size;
    }
}

class Main {
    public static void main(String[] args) {
        String name1 = "Suanfage wudi created txt file with size 100";
        String name2 = "Peter created xml file with size 30";
        String name3 = "Tom created txt file with size 50";

        MyFile suanfage = new MyFile(name1, "txt", "Suanfage", 100);
        MyFile peter = new MyFile(name2, "xml", "Peter", 100);
        MyFile tom = new MyFile(name3, "txt", "Tom", 50);


      /*
         该函数创建了一个filter tree, 包含有两个filter node, 一个是suffixfilter以txt结尾。
         另一个node是sizefilter, size为100。operator node为And类型。
      */
        FileFilterNode root = generateRoot("txt", 100, new AndOperator());

        List<MyFile> files = List.of(suanfage, peter, tom);

        List<MyFile> res = FileFilterProcessor.filterGo(files, root);
        for (MyFile f : res)
            System.out.println(f.getName());
    }

    private static FileFilterNode generateRoot(String s, int size, OperatorInterface combineType) {
        FileFilterInterface suffixFilter = new SuffixFileFilter(s);
        FileFilterInterface sizeFilter = new SizeFileFilter(size);
        return new FileFilterNode(new FileFilterNode(suffixFilter),
                new FileFilterNode(sizeFilter), combineType);
    }
}
