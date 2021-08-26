public class Singleton {
    private static Singleton instance;
    private Singleton(){}

    public static Singleton getInstance(){
        //加null：避免以后不需check null的时候加锁解锁, 提高performance
        if(instance == null){
            //前边的check null可能是多个一起check
            // 所以这里还需check null
            // 避免多次initiate new Singleton
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
