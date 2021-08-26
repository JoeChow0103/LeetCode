import java.util.*;

public class LeetCode155 {
}
class MinStack {
    /*
    solution1: use two stack, one push, one push min, min will be duplicate pushed
    solution2: using one stack, push (num, min_current)
     */
    /** initialize your data structure here. */
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()){
            minStack.push(x);
        }
    }

    public void pop() {
        int temp = stack.pop();
        if(minStack.peek() == temp){
            minStack.pop();

        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}