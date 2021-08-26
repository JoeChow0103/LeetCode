import java.util.*;

public class LeetCode341 {
}
//class NestedIterator implements Iterator<Integer> {

//    Stack<NestedInteger> stack;
//    public NestedIterator(List<NestedInteger> nestedList) {
//        stack = new Stack<>();
//        for(int i = nestedList.size() - 1; i >= 0; i--){
//            stack.push(nestedList.get(i));
//        }
//    }
//
//    private void makeStackTopAnInteger(){
//        while(!stack.isEmpty() && !stack.peek().isInteger()){
//            List<NestedInteger> temp = stack.pop().getList();
//            for(int i = temp.size() - 1; i >= 0; i--){
//                stack.push(temp.get(i));
//            }
//        }
//    }
//
//    @Override
//    public Integer next() {
//
//        // if(!stack.peek().isInteger()){
//        //     makeStackTopAnInteger();
//        // }
//
//        return stack.pop().getInteger();
//    }
//
//    @Override
//    public boolean hasNext() {
//        makeStackTopAnInteger();
//        return !stack.isEmpty();
//    }
//}