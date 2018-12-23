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
	 * InsÃ¨re une clÃ© en faisant la recherche pour savoir dans quelle feuille
	 * l'insÃ©rer puis appelle Split qui appelera insertIn pour la recursivitÃ©
	 * 
	 * @param key
	 */
	public void insert(Integer key) {
		// Il faut pouvoir rappeler Insert sans utiliser la fonction de recherche pour
		// modifier le père du noeud auquel on a rajouté la key (dans le cas où le noeud
		// est full)
		Node node = recherche(key, root);
		if (!node.isFull()) {
			node.add(key);
		} else {
			split(node, key);

		}

	}

	/**
	 * Insert une valeur précise dans un noeud donné, et gère la progéniture
	 * (appelée dans split)
	 * 
	 * @param key
	 * @param node
	 * @param children
	 */
	public void insertIn(Integer key, Node node, Node children) {
		if (!node.isFull()) {
			node.add(key);
			node.addChildren(children);
		} else {
			split(node, key);

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
	 * Split un noeud Q1 (de père Q) full en deux noeuds Q1 et Q2, puis appelle la
	 * fonction insertIn qui ajoute la médiane dans le père de Q1 et Q2;
	 * 
	 * @param Q1
	 * @param key
	 */
	public void split(Node Q1, Integer key) {
		
		List<Integer> temporary = new ArrayList<Integer>(Arrays.asList(Q1.getKeys()));
		//ArrayList<Integer> temporary = (ArrayList<Integer>) Arrays.asList(Q1.getKeys());
		temporary.add(key);
		temporary.sort(null);
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
		for (int i = 0; i < order; i++) {
			Integer val = temporary.get(i);
			
			
			//LES FONCTIONS ADD N'ADD RIEN PUTAIN
			
			
			if (i < Math.round(order / 2)) {
				Q1.add(val);
			}
			if (i > Math.round(order / 2)) {
				Q2.add(val);
			}
			
		}
		
		insertIn(median, Q, Q2);

	}

	public boolean rechercheElem(int key, Node node) {
		if (node == null) {
			return false;
		}
		Integer[] keys = node.getKeys();
		Node[] childrens = node.getChildren();
		if(Arrays.asList(keys).contains(key)){
			return true;
		}else {
			return rechercheElem(key,node.goodChildren(key));
		}
	}
	
	public String toString() {
		return root.toString();
	}

}
