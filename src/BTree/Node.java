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


	public boolean removeKey(Integer key){
		boolean removed = false;
		for (int i=0; i < countOfRecords; i++){
			if(removed){
				keys[i-1]=keys[i];
			}
			if(keys[i].equals(key)){
				keys[i]=null;
				removed = true;
			}
		}
		keys[countOfRecords-1]=null;
		countOfRecords --;
		return true;
	}


	public Integer getAndDeleteSubtreeMinimumValue(){
		if (this.isLeaf()){
			int temp = keys[0];
			for (int i=0;i<countOfRecords-1;i++) {
				keys[i] = keys[i+1];
			}
			keys[countOfRecords]=null;
			countOfRecords--;
			return temp;
		}else{
			return children[0].getAndDeleteSubtreeMinimumValue();
		}
	}
	
	/**
	 * Récupère la valeur maximale de l'arbre désigné, et la supprime
	 * @return la valeur maximal de l'arbre
	 */
	public Integer getAndDeleteSubtreeMaximumValue(){
		if (this.isLeaf()){
			int temp = keys[countOfRecords-1];
			keys[countOfRecords-1] = null;
			countOfRecords--;
			return temp;
		}else{
			return children[countOfRecords].getAndDeleteSubtreeMaximumValue();
		}
	}

	public Node getNodeContainingMinimumValueChildren(){
		if (this.isLeaf()){
			return this;
		}else{
			return children[0].getNodeContainingMinimumValueChildren();
		}
	}
	
	
	/**
	 * Fonction récursive donnant le noeud contenant la valeur maximal à partir de l'arbre dont le noeud est racine
	 * @return
	 */
	public Node getNodeContainingMaximumValueChildren(){
		if (this.isLeaf()){
			return this;
		}else{
			return children[countOfRecords].getNodeContainingMaximumValueChildren();
		}
	}

	/**
	 * Cherche le noeud qui contient la valeur maximal dans l'arbre dont le fils gauche de la valeur key est racine
	 * @param key
	 * @return
	 */
	public Node getNodeContainingMaximumValue(Integer key){
		for(int i=0; i<countOfRecords; i++){
			if(keys[i].equals(key)){
				return children[i].getNodeContainingMaximumValueChildren();
			}
		}
		return null;
	}


	/**
	 * Remplace key1 par la valeur de son sous arbre de gauche
	 * @param key1
	 * @throws IllegalArgumentException if key1 isn't in the node
	 */
	public boolean replaceByMaximumValue(Integer key1) throws IllegalArgumentException{
		if (! this.contains(key1)){
			throw new IllegalArgumentException(key1 + " is not in the node");
		}
		for(int i=0; i<countOfRecords; i++){
			if(keys[i].equals(key1)){
				keys[i] = children[i].getAndDeleteSubtreeMaximumValue();
				return true;
			}
		}
		return false;
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
	 * Renvoie un noeud adjascent avec lequel la fusion est possible ou null s'il n'en existe pas
	 * @return
	 */
	public Node mergeableNode(){
		int i=0;
		while (father.getChildren()[i] != this){
			i++;
		}
		if (i == 0) {
			if(father.getChildren()[i + 1].hasEnoughPlace()){
				return father.getChildren()[i+1];
			}
		}else if(i == father.getCountOfRecords()){
			if(father.getChildren()[i-1].hasEnoughPlace()){
				return father.getChildren()[i-1];
			}
		}else{
			if(father.getChildren()[i+1].hasEnoughPlace()){
				return father.getChildren()[i+1];
			}else if(father.getChildren()[i - 1].hasEnoughPlace()){
				return father.getChildren()[i - 1];
			}
		}
		return null;
	}

	/**
	 * teste si le noeud a assez de place pour être fusionné aec un autre
	 * @return
	 */
	public boolean hasEnoughPlace(){
		return ((order -1) - countOfRecords >= Math.ceil((double)(order)/2)-1);
	}

	public void merge(Node mergeableNode) {
		Node Q = father;
		Node Q1, Q2;
		if(keys[0] < mergeableNode.getKeys()[0]){
			Q1 = this;
			Q2 = mergeableNode;
		}else{
			Q1 = mergeableNode;
			Q2 = this;
		}
		int i=0;
		while (Q.getChildren()[i] != Q1){
			i++;
		}
		Q1.add(Q.getKeys()[i]);
		Q.removeKey(Q.getKeys()[i]);
		for (Integer key : Q2.getKeys()) {
			Q1.add(key);
		}
		for (Node children : Q2.getChildren()) {
			if (children != null) {
				Q1.addChildren(children);
				children.setFather(Q1);
			}
		}
		for (int j = i+1; j < Q.getOrder()-1; j++){
			Q.getChildren()[j] = Q.getChildren()[j+1];
		}
		Q.getChildren()[Q.getOrder() -1] = null;
	}
	
	public Node rotation() {
		Node leaf;
		int i=0;
		while (father.getChildren()[i] != this){
			i++;
		}
		if (i == 0) {
			leaf = this.rotateWithNodeMin(father.getChildren()[i+1]);
		}else{
			leaf = this.rotateWithNode(father.getChildren()[i-1]);
			}
		return leaf;
	}
	
	public Node rotateWithNode(Node Q1) {
		Node leaf = Q1.getNodeContainingMaximumValueChildren();
		Node Q = Q1.getFather();
		Node Q2 = this;
		int i=0;
		while (Q.getChildren()[i] != this){
			i++;
		}
		Integer maxq1 = Q1.getAndDeleteSubtreeMaximumValue();
		Integer valQ = Q.getKeys()[i-1] ;
		Q.getKeys()[i-1] = maxq1;
		Q2.add(valQ);
		return leaf;
		
	}
	
	public Node rotateWithNodeMin(Node Q1) {
		Node leaf = Q1.getNodeContainingMinimumValueChildren();
		Node Q = Q1.getFather();
		Node Q2 = this;
		Integer minq1 = Q1.getAndDeleteSubtreeMinimumValue();
		Integer valQ = Q.getKeys()[0] ;
		Q.getKeys()[0] = minq1;
		Q2.add(valQ);
		return leaf;
		
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

	public boolean contains(Integer key){
		if(Arrays.asList(keys).contains(key)) {
			return true;
		}
		return false;
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
	
	public int getOrder() {
		return order;
	}
	
	public int getMaxNode() {
		return keys[countOfRecords-1];
	}


}
