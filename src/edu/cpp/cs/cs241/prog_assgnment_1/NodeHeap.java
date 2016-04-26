package edu.cpp.cs.cs241.prog_assgnment_1;
import java.math.*;
import java.util.ArrayList;

public class NodeHeap<V extends Comparable<V>> {

	public int size = 0;
	Node<V> root;
	 
	/*returns the depth, or the number of levels in the heap. The root level is counted 
	 * as level 0*/
	public int numOfLevels(){
		return ((int) Math.ceil(Math.log(size + 1) / Math.log(2)) - 1);
	}
	
	/*returns the number of nodes that should exist if the heap were hypothetically complete*/
	public int completeHeap(){
		return (int) (Math.pow(2, (numOfLevels() + 1)) - 1);
	}
	
	/*disregards the last level and returns the number of nodes in the rest of the heap,
	 * which is also a complete heap */
	public int completeSubHeap(){
		return (completeNumNodesInLastLevel() - 1);
	}
	
	/*the actual number of nodes in the last level*/
	public int numNodesInLastLevel(){
		return (size - completeSubHeap());
	}
	
	/*returns the hypothetical number of nodes that are in the last level 
	 *if the heap were complete*/
	public int completeNumNodesInLastLevel(){
		return (int) Math.pow(2, numOfLevels());
	}
	
	
	/*Adds a new node in the correct place in the node heap. Calls siftUp() method to sort the heap
	 * once the node is added*/
	public void add(V value){
		
		//case 1: empty heap
		if (size == 0){
			root = new Node<V>(value, null);
			size++;
		}
		//case 2: heap with just root
		else if (size == 1){
			Node<V> newNode = new Node<V>(value, root);
			root.setLeftChild(newNode);
			siftUp(newNode);
			size++;
		}
		//case 3: if heap is complete
		else if (size == completeHeap()){
			Node<V> temp = root;
			
			while(temp.getLeftChild() != null){
				temp = temp.getLeftChild();
			}
			Node<V> newNode = new Node<V>(value, temp);
			temp.setLeftChild(newNode);
			siftUp(newNode);
			size++;
		}
		else {
			siftUp(addToIncompleteHeap(value));
		}	
	}
	
	/*When adding a node, if the node is being added to an incomplete heap, the add method will
	 * call the addToIncompleteHeap method*/
	public Node<V> addToIncompleteHeap(V value){
		size++;
		
		/*my algorithm for finding the location of where to add the new node. tempRoot will be the parent
		of the new Node that is being added*/
		Node<V> tempRoot = root;
		int ideal = completeNumNodesInLastLevel() / 2;
		int real = numNodesInLastLevel() - 1;
		
		while (ideal >= 2){
			if (real < ideal){
				tempRoot = tempRoot.getLeftChild();
				ideal /= 2;
			}
			else if (real >= ideal){
				tempRoot = tempRoot.getRightChild();
				real -= ideal;
				ideal /= 2;
			}
		}
		
		//adding the Node to the Node heap
		if (tempRoot.getLeftChild() == null){
			Node<V> newNode = new Node<V>(value, tempRoot);
			tempRoot.setLeftChild(newNode);
			return newNode;
		}
		
		else{
			Node<V> newNode = new Node<V>(value, tempRoot);
			tempRoot.setRightChild(newNode);
			return newNode;
		}	
	}
	
	
	/*removes the root, and then returns the removed node. Makes sure the heap is still sorted
	 * by calling the siftDown method after removing the node. */
	public V remove(){
		//case 1: empty heap
		if (size == 0){
			return null;
		}
		//case 2: heap with only root
		else if (size == 1){
			V value = root.getValue();
			root = null;
			size--;
			return value;
		}
		//case 3: heap with only one child
		else if (size == 2){
			V value = root.getValue();
			swap(root, root.getLeftChild());
			root.setLeftChild(null);
			size--;
			return value;
		}
		//case 4: complete heap
		else if (size == completeHeap()){
			Node<V> delete = root;
			
			while(delete.getRightChild() != null){
				delete = delete.getRightChild();
			}
	
			swap(root, delete);
			
			delete.getParent().setRightChild(null);
			delete.setParent(null);
			size--;
			
			siftDown(root);
			return delete.getValue();
		}
		
		/*remove a node from incomplete heap*/
		else {
			Node<V> delete = root;
			int ideal = completeNumNodesInLastLevel() / 2;
			int real = numNodesInLastLevel();
			
			//algorithm to find the last node
			while (ideal >= 1){
				if (real <= ideal){
					delete = delete.getLeftChild();
					ideal /= 2;
				}
				else if (real > ideal){
					delete = delete.getRightChild();
					real -= ideal;
					ideal /= 2;
				}
			}
			
			swap(root, delete);
			
			if (numNodesInLastLevel()%2 == 1){
				delete.getParent().setLeftChild(null);
			}
			else {
				delete.getParent().setRightChild(null);
			}
			delete.setParent(null);
			
			size--;
			siftDown(root);
			return delete.getValue();
		}
	}
	
	
	/*sift down for heap. I overwrote the compareTo method in the Customer class, which is where
	 * the logic for this method comes from. When comparing A.value to B.value, if B has a greater 
	 * value, or in this case a lower priority, it means A has priority, and the compareTo method returns -1. 
	 * If B has priority over A, compareTo returns +1. */
	public void siftDown(Node<V> tempRoot){
		//base case
		if (tempRoot.getLeftChild() == null){
			return;
		}
		/*checking conditions; if only a left child exists, checks to see if left child has a higher priority than
		 * its parent node. If so, will call swap method then call siftDown in recursion*/ 
		else if (tempRoot.getRightChild() == null){
			if (1 == tempRoot.getValue().compareTo(tempRoot.getLeftChild().getValue())){
				swap(tempRoot, tempRoot.getLeftChild());
				siftDown(tempRoot.getLeftChild());
			}	
		}	
		/*checking conditions; if both a left and right child exists, first checks to see which child has a higher
		 * priority, then checks to see if child has a higher priority than its parent node */
		else {
			if (1 == tempRoot.getRightChild().getValue().compareTo(tempRoot.getLeftChild().getValue())){
				if (1 == tempRoot.getValue().compareTo(tempRoot.getLeftChild().getValue())){
					swap(tempRoot, tempRoot.getLeftChild());
					siftDown(tempRoot.getLeftChild());
				}
			}
			else {
				if (1 == tempRoot.getValue().compareTo(tempRoot.getRightChild().getValue())){
					swap(tempRoot, tempRoot.getRightChild());
					siftDown(tempRoot.getRightChild());
				}
			}
		}
	}	
	
	/*Swap node with the node's parent*/
	public void swap(Node<V> top, Node<V> bottom){
		V temp = top.getValue();
		top.setValue(bottom.getValue());
		bottom.setValue(temp);
	}
	
	/*CHECK TO SEE IF IT WORKS*/
	public void siftUp(Node<V> traverse){
		if (traverse.getParent() != null){
			if (-1 == traverse.getValue().compareTo(traverse.getParent().getValue())){
				swap(traverse, traverse.getParent());
				siftUp(traverse.getParent());
			}
		}
	}
	
	/*sift down for array. sift down index 0*/
	public V[] siftDownArray(V[] array, int parent, int arraySize){
		boolean notDone = true;
		
		while (notDone){
			int rightChild = parent * 2 + 2;
			int leftChild = parent * 2 + 1;
			
			if (rightChild <= arraySize){
				if ((1 == array[leftChild].compareTo(array[rightChild])) && (1 == array[parent].compareTo(array[rightChild]))){
					array = swap(array, parent, rightChild);
					parent = rightChild;
				}	
			}	
			else if (leftChild <= arraySize){
				if	((1 == array[leftChild].compareTo(array[rightChild])) && (1 == array[parent].compareTo(array[leftChild]))){
				array = swap(array, parent, leftChild);	
				parent = leftChild;
				}
			}
			else{	
				notDone = false;
			}
		}	
		return array;
	}

	public V[] toArray(V[] array){
		V[] result = (V[])java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
		ArrayList<Node<V>> storeNode = new ArrayList();
		storeNode.add(root);
		for (int i = 0; i < size; i++){
			Node<V> temp = storeNode.remove(0);
			if (temp.getLeftChild() != null){
				storeNode.add(temp.getLeftChild());
			}
			if (temp.getRightChild() != null){
				storeNode.add(temp.getRightChild());
			}
			System.out.println(temp.getValue());
			result[i] = temp.getValue();
		}
		return result;
	}
	
	public void fromArray(V[] array){
		size = 0;
		for (int i = 0; i < array.length; i++){
			add(array[i]);
		}
	}
	
	public V[] getSortedContents(V[] array){
		return (heapSort(toArray(array)));
	}
	
	
	public V[] swap(V[] array, int parent, int child){
		V temp = array[child];
		array[child] = array[parent];
		array[parent] = temp;
		return array;
	}
	
	/*converts the unsorted array to a sorted array. swap the first and last value,
	 * then make sure first value is in correct location. repeats until it has traversed through 
	 * the array list.*/
	private V[] heapSort(V[] array) {
		for (int i = size - 1; i > 0; i--){
			array = swap(array, 0, i);
			if (i != 1){
				array = siftDownArray(array, 0, i);
			}
		}
		return null;
	}	
}
