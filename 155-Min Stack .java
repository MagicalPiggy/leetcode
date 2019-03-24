// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack.
// Example:

// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin();   --> Returns -3.
// minStack.pop();
// minStack.top();      --> Returns 0.
// minStack.getMin();   --> Returns -2.


// 题目的关键是如何实现getMin()的功能，如果调用此方法时要遍历并比较不是一件容易的事情，那么我们可以在push和pop操作时维护一个min变量，来记录栈中元素的最小值。

// push的时候可以将min更新为最小值，但是pop的时候，若刚好把这个min给pop掉了，那么min将如何更新为次小值呢？这又成了一个关键。

// 没有必要再新建另外的结构来保存原来的序列，我们可以直接在栈中记录这样的大小顺序。每次push的时候若发生min的更新，那么我们可以在新的min入栈之前，先将原来的min再入栈一次，以保证新的min值压在原min值之上，这样pop的时候，若pop的值为min，则再pop一次（这次pop的就是次小值），并将这个值赋给min，完成了min的更新，同时栈顶依然指向正确的位置。
// --------------------- 
// 作者：Mr.Bean-Pig 
// 来源：CSDN 
// 原文：https://blog.csdn.net/z714405489/article/details/88774949 
// 版权声明：本文为博主原创文章，转载请附上博文链接！

class MinStack {

	int min = Integer.MAX_VALUE;
	Stack<Integer> stack = new Stack<Integer>();

    
    public void push(int x) {
        //push的同时更新最小值
        if(x <= min){
        	stack.push(min);//先push原来的min是为了pop时可以使min始终保持为栈中元素的最小值
        	min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
    	//pop的元素若是最小值，那么需要将min更新为栈中剩余元素的最小值，这个值就在min下一层
         if(stack.pop() == min) min = stack.pop();
         
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */