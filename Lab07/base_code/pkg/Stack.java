package pkg;
import java.util.Scanner;
import java.util.Random;


public class Stack {
	/*  LAST IN FIRST OUT  */
	Node top;

	/* 
		Postcondition: The top will be null.
	*/
	public Stack() {
		top = null;
	}

	/*
		Insert a new Node on top of the stack
	*/
	public void push(int data){
		Node node = new Node(data);
		if (top == null) {
			top = node;
			return;
		}
		node.setNext(top);
		top = node;
	}

	/*
		Removes the top node of the stack
	*/
	public int pop(){
		int val = top.getData();
		top = top.getNext();
		return val;
	}

	/*
		Returns the top value of the stack. Doesn't pop. 
	*/
	public int peek(){
		if (top == null) {
			return -1;
		}
		else {
		return top.getData();
		}
	}

	/*
		Checks if the stack is empty. 
	*/
	public boolean isEmpty(){
		if (top == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
