package BTree;

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
		if(key > keys[countOfRecords-1]) {
			return children[countOfRecords];
		}
		int i =0;
		while(key > keys[i]) {
			i++;
		}
		return children[i];
	}
	
	//Fonction appelée uniquement dans le cas où il est possible d'ajouter l'enfant. Cette fonction ne peut pas traiter d'erreur
	public boolean addChildren(Node node) {
		
		//Cas où il n'y a pas encore d'enfant
		if(children[0] == null) {
			children[0] = node;
			return true;
		}
		
		int valChild = node.getKeys()[0];
		int i;
		Node aux1;
		Node aux2;
		for(i =0; i<order-1; i++) {	
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
		children[order-1] = node;
		return true;
	}
	
	public String toString() {
		String s = "Key : [";
		for (int i =0; i<order-2; i++) {
			s = s+ keys[i] + ",";
		}
		s = s+ keys[order-2] + "]";
		s+="\nChildren : [";
		for (int i =0; i<order-1; i++) {
			s = s+ children[i] + ",";
		}
		s = s+ children[order-1] + "]";
		return s;
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
