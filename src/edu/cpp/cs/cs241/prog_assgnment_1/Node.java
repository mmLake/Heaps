package edu.cpp.cs.cs241.prog_assgnment_1;
/*Node class done with generics. Has two 'next' links, or pointers, called LeftChild and RightChild as well as a 
 * 'previous' link called Parent*/
public class Node<V> {
	
	private V value;
	private Node<V> parent;
	private Node<V> leftChild;
	private Node<V> rightChild;

	public Node(V value, Node<V> parent) {
		this.value = value;
		this.parent = parent;
	}
	
	public V getValue() {
		return value;
	}


	public void setValue(V value) {
		this.value = value;
	}


	public Node<V> getParent() {
		return parent;
	}

	public void setParent(Node<V> parent) {
		this.parent = parent;
	}
	
	public Node<V> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<V> newChild) {
		leftChild = newChild;
	}

	public Node<V> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<V> newChild) {
		rightChild = newChild;
	}
	
	public Boolean isLeaf(Node<V> current){
		return ((leftChild == null) && (rightChild == null));
	}
	
}
