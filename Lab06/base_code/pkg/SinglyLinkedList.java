package pkg;
import java.util.Scanner;

import javax.swing.text.PlainDocument;

import java.util.Random;


public class SinglyLinkedList {
	Node head;
	int idx;

	/* 
		Postcondition: The head will be null 
	*/

	//head is a copy of the first value
	public SinglyLinkedList() {
		head = null;

	}

	/* 
		Receives an integer position, searches through the SinglyLinkedList for the position and returns the data at that positon
	   	If the position doesn't exist, it returns -1
	*/ 
	public int get(int pos){
		Node next = head;
		int c = 0;
		while(c < pos) {
			next = next.getNext();
			c++;
			if (next == null) {
				return -2;
			}
		}
		return next.getData();
	}

	/*
		Insert a new Node at the given position with the data given
	*/
	public void insert(int pos, int data){
		Node node = new Node(data);
		if (head == null) {
			head = node;
			return;
		}
		if (pos == 0) {
			node.setNext(head);
			head = node;
			return;
		}

		Node posNode = head;
		int c = 0;
		while(c < pos - 1) {
			posNode = posNode.getNext();
			c++;
		}
		Node next = posNode.getNext();
		posNode.setNext(node);
		node.setNext(next);
	}

	/*
		Remove the node at the given position
		If no position exists, don't change the list
	*/
	public void remove(int pos){
		Node currNode = head;
		int c = 0;
		while(c < pos - 1) {
			currNode = currNode.getNext();
			c++;
		}
		currNode.setNext(currNode.getNext().getNext());
	}

	/*
		Swap two Nodes with the two positions given
	*/
	public void swap(int pos1, int pos2){
		int data1 = get(pos1);
		int data2 = get(pos2);
		insert(pos2, data1);
		remove(pos2 + 1);

		insert(pos1, data2);
		remove(pos1 + 1);
	}

	/*
		Print all data values in the LinkedList 
	*/
	public void printList(){
		int pos = 0;
		while(true) {
			if (get(pos) == -2) {
				return;
			}
			System.out.println("Position: " + pos + ", Data: " + get(pos));
			pos++;
		}
	}
}
