package BTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

	private int order;
	private Integer[] keys;
	private Node[] children;
	private Node father;
	private int countOfRecords = 0;

	public Node(int order, Node father) {
		this.order = order;
		this.father = father;
		keys = new Integer[order-1];
		children = new Node[order];
	}

	public Node(int order) {
		this.order = order;
		keys = new Integer[order-1];
		children = new Node[order];
	}

	public boolean add(Integer key) {
		
		if(Arrays.asList(keys).contains(key)){
			throw new IllegalArgumentException(key + " is already in this Node");
		}

		if ((countOfRecords == (order-1))||(key == null)) {
			return false;
		}

		else {
			countOfRecords++;
			int i=0, j=0;
			Integer aux1, aux2;
			while (!(keys[j] == null)) {
				if(key > keys[i]) {
					i++;
				}
				j++;
			}
			aux1 = keys[i];
			keys[i]= key;
			for (int y =i+1; y<countOfRecords ; y++) {
				aux2 = keys[y];
				keys[y]= aux1;
				aux1 = aux2;
			}
			return true;

		}
	}


	public int positionInsert(Integer key) {
		if(key == null) {
			return 0;
		}
		if(key > keys[countOfRecords-1]) {
			return countOfRecords;
		}
		int i =0;
		while(keys[i] < key) {
			i++;
		}
		return i;	
	}

	/**
	 * Indique par un boolean si le noeud est une feuille de l'arbre
	 * @return 
	 */
	public boolean isLeaf() {
		return (children[0] == null);
	}


	/**
	 * To look for the corresponding children for a given key 
	 * @param key the key for which the corresponding children is searched
	 * @return the children of the node in which the key is 
	 */
	public Node goodChildren(Integer key) {
		if (countOfRecords == 0) {
			return null;
		}
		if(key > keys[countOfRecords-1]) {
			return children[countOfRecords];
		}
		int i =0;
		while(key > keys[i]) {
			i++;
		}
		return children[i];
	}

	//Fonction appelee uniquement dans le cas ou il est possible d'ajouter l'enfant. Cette fonction ne peut pas traiter d'erreur
	public boolean addChildren(Node node) {

		int valChild = node.getKeys()[0];
		int i;
		Node aux1;
		Node aux2;
		for(i =0; i<order-1; i++) {	
			if(children[i] == null) {
				children[i] = node;
				return true;
			}
			if ((valChild < keys[i])) {
				aux2 = node;
				for(int j = i; j< order; j++) {
					aux1 = children[j];
					children[j] = aux2;
					aux2 = aux1;
				}
				return true;
			}

		}
		children[this.countOfRecords] = node;
		return true;
	}


	public String display() {
		String n = "";
		for (int i = 0;i<order+2; i++) {
			n = n + " ";
		}
		String s = "";
		for (int j = 0; j < Math.floor(countOfLeaf()/2);j++) {
			s = s + n;
		}
		s = s + "[";
		for (int i =0; i<order-2; i++) {
			s = s+ keys[i] + ",";
		}
		s = s+ keys[order-2] + "]";
		return s;
	}

	private static String displayLevel(List<Node[]> childrenList) {
		String s = "";
		List<Node[]> level = childrenList;
		List<Node[]> temp = new ArrayList<>();
		Node[] currentChildren;

		while (!level.isEmpty()) {
			if (!(temp.isEmpty())) {
				temp.removeAll(temp);
			}
			for (int i = 0; i < level.size();i++) {
				currentChildren = level.get(i);
				for (Node child : currentChildren) {
					if (child != null) {
						s = s + child.display() + " ";
						temp.add(child.getChildren());
					}
				}
			}
			s = s+ "\n"+ "\n";
			
			level.removeAll(level);
			level.addAll(temp);

		}


		return s;
	}

	public String toString() {
		String s = this.display() + "\n" + "\n";
		List<Node[]> init = new ArrayList<>();
		init.add(children);
		if (children[0]==null) {
			return s;
		}
		return s+displayLevel(init);
	}



	public int countOfChildren() {
		int s = 0;
		Node child = children[s];
		while ((child != null)&&s<order-1) {
			s++;
			child=children[s];
		}
		return s;
	}

	public int countOfLeaf() {
		if (this.isLeaf()) {
			return 1;
		}
		int countOfLeaf = 0;
		for (Node node : children) {
			if (node != null) {
				if (node.isLeaf()) {
					countOfLeaf++;
				}
				else {
					countOfLeaf = countOfLeaf + node.countOfLeaf();
				}
			}

		}
		return countOfLeaf;
	}

	public boolean isFull() {
		return (countOfRecords == order -1);
	}

	public Integer[] getKeys() {
		return keys;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Node[] getChildren() {
		return children;
	}

	public void removeAllKeys() {
		for(int i =0; i<order-1; i++) {
			keys[i] = null;
		}
		countOfRecords = 0;
	}
	
	public int getCountOfRecords() {
		return countOfRecords;
	}
	
	public void removeAllChild() {
		children = new Node[order];
	}

	/*	public Node split() {
		Integer mediane = 40;
		Node root = new Node(order);
		root.add(mediane);
		Node children1 = new Node(order);
		Node children2 = new Node(order);
		for (Integer key : keys) {
			if (key<mediane) {
				children1.add(i);
			}
			if (key>mediane) {
				children2.add(i);
			}
		}
		root.setChildren(children1);
		root.setChildren(children2);
		return root;
	}
	 */


}
