import java.text.SimpleDateFormat;
import java.util.*;

/*
Classes: File, Directory, File System
Relationship: Directory contains files or sub directory or empty

Because many common fields and functionalities between file and directory, so we want to
option1: Directory to extend File
option2: abstract class and extends to Directory File, AB abstract to C
option3: interface

File:
String byte[] content; // aligment
long size; ⇔ getSize();

Directory:
List<Node> content;
size → getSize();
 */

abstract class FNode {
    String name;
    boolean[] permission; // chmod 777
    FNode parent;
    final Date created; //Date class
    Date modified;

    protected FNode(String n, FNode p) { // cannot be new, but inherit
        name = n;
        parent = p;
        long mseconds = System.currentTimeMillis();
        created = new Date(mseconds * 1000);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String str = sdf.format(created);
//        System.out.println(str);
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public Date setModified(Date time) {
        modified = time;
        return modified;
    }

    public void reName(String n) {
        name = n;
    }

    public abstract long getSize(); // derived class must concrete but in different way

    public String getCurrentPath() { // recursion, subproblem
        // base case
        if (parent == this) return ""; // Mac: "", Windows: "c:/"
        return parent.getCurrentPath() + "/" + name; // don't use this is ok, represent current class parent
    }
}

class File extends FNode {
    /*
    Fields:	permission, name, created time, modified time, size, kind, content(Byte[])
    Methods:
     */
    private String content;
    private long size;

    public File(String name, FNode parent, long size, boolean[] pm) {
        super(name, parent);
        this.size = size;
        permission = pm; // protected, can be accessed
    }

    public String setContent(String content, long size) {
        this.size = size;
        this.content = content;
        return content;
    }

    @Override
    public long getSize() {
        return size;
    }

    public String getContent() {
        return this.content;
    }
}

class Directory extends FNode {
    /*
    Directory:
    Fields: permission, name, created time, modified time, size, kind, content(List[?])
    Methods:
     */

    private List<FNode> content;

    public Directory(String n, FNode p, boolean[] pm) {
        super(n, p);
        permission = pm;
        content = new ArrayList<FNode>();
    }

    public void addNode(FNode node) {
        content.add(node);
    }

    public void deleteNode(FNode node) {
        content.remove(node);
    }

    @Override
    public long getSize() {
        long size = 0;
        for (FNode n : content) {
            size += n.getSize();
        }
        return size;
    }

    public int countFiles() {
        int count = 0;
        for (FNode n : content) {
            if (n instanceof Directory) {
                Directory d = (Directory) n; // must enforce to Directory since the FNode doesn't have countFile()
                count += d.countFiles();
            } else {
                count++;
            }
        }
        return count;
    }

    public List<FNode> getContent() {
        return this.content;
    }
}

public class InMemoryFileSystem {
    // fields
    FNode root;

    // methods
    InMemoryFileSystem() {
        root = new Directory("untitled", null, null);
        // root.setParent(root); 先有鸡？先有蛋？
    }

//    public void mkDir(String path) {
//        if (path == null || path.length() == 0) return;
//
//        String[] dirs = path.split("/");
//        FNode cur = root;
//        boolean flag = false;
//        for (String dir : dirs) {
//            for (FNode child : cur.getContent()) {
//                if (child.name.equals(dir)) {
//                    cur = child;
//                    flag = true;
//                    break; // must break after change cur otherwise, iterator
//                }
//            }
//            if (!flag) {
//                FNode newDir = new Directory(dir, cur, null);
//                cur.content.add(newDir);
//                cur = newDir;
//            }
//        }
//    }

//    createFile(String path)
//
//    cd(String path)
//
//    delete(String path) {
//
//    }

    int countFiles() {
        int count = ((Directory) root).countFiles();
        return count;
    }

    long getSize() {
        long size = ((Directory) root).getSize();
        return size;
    }

}

