package BTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BTree {

	private final int order;
	public Node root;
	private int height;

	public BTree(int order) {
		this.order = order;
		root = new Node(order);
		height = 1;
	}

	/**
	 * Insère une clé en faisant la recherche pour savoir dans quelle feuille
	 * l'insérer puis appelle Split qui appelera insertIn pour la recursivité
	 * 
	 * @param key
	 */
	public void insert(Integer key) {
		if (rechercheElem(key, root)) {
			throw new IllegalArgumentException(key + " is already in the tree");
		}
		Node node = recherche(key, root);
		if (!node.isFull()) {
			node.add(key);
		} else {
			split(node, key,null);

		}

	}

	/**
	 * Insert une valeur precise dans un noeud donne, et gere la progeniture
	 * (appelee dans split)
	 * 
	 * @param key
	 * @param node
	 * @param children
	 */
	public void insertIn(Integer key, Node node, Node children) {
		if (!node.isFull()) {
			node.add(key);
			node.addChildren(children);
		} else {    //Si le noeud est full, on laisse tomber le children
			split(node, key,children);

		}

	}

	public Node recherche(Integer key, Node node) {
		if (node.isLeaf()) {
			return node;
		} else {
			Node children = node.goodChildren(key);
			return recherche(key, children);
		}

	}

	/**
	 * Split un noeud Q1 (de pere Q) full en deux noeuds Q1 et Q2, puis appelle la
	 * fonction insertIn qui ajoute la mediane dans le pere de Q1 et Q2;
	 * 
	 * @param Q1
	 * @param key
	 */
	public void split(Node Q1, Integer key,Node Qchild) {

		List<Integer> temporary = new ArrayList<Integer>(Arrays.asList(Q1.getKeys()));
		List<Node> tempChildren = new ArrayList<Node>(Arrays.asList(Q1.getChildren()));
		//ArrayList<Integer> temporary = (ArrayList<Integer>) Arrays.asList(Q1.getKeys());
		temporary.add(key);
		temporary.sort(null);
		tempChildren.add(Qchild);
		Integer median = temporary.get(Math.round(order / 2));
		Node Q = Q1.getFather();
		if(Q == null) {
			Q = new Node(order);
			Q1.setFather(Q);
			Q.addChildren(Q1);
			root = Q;
		}
		Node Q2 = new Node(order, Q);
		Q1.removeAllKeys();
		Q1.removeAllChild();
		for (Integer val : temporary) {


			//LA FONCTION ADD N'ADD RIEN SUR Q1 PUTAIN


			if (val < median) {
				Q1.add(val);
			}
			if (val > median) {
				Q2.add(val);
			}	
		}
		for (Node child : tempChildren) {
			if (child != null) {
				if (child.getKeys()[child.getCountOfRecords()-1]<median) {
					Q1.addChildren(child);
					child.setFather(Q1);
				}
				else {
					Q2.addChildren(child);
					child.setFather(Q2);
				}
			}
		}
		/**
		System.out.println(Q1);
		System.out.println("\n");
		System.out.println(Q2);
		System.out.println("\n");
		System.out.println(Q);
		 **/
		insertIn(median, Q, Q2);


	}

	public boolean rechercheElem(int key, Node node) {
		if (node == null) {
			return false;
		}
		if(Arrays.asList(node.getKeys()).contains(key)){
			return true;
		}else {
			return rechercheElem(key,node.goodChildren(key));
		}
	}

	public String toString() {
		return root.toString();
	}


	public int countOfLeaf() {
		return root.countOfLeaf();
	}

}
