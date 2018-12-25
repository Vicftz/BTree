package BTree;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		BTree tree = new BTree(5);
		tree.insert(0);
		tree.insert(100);
		tree.insert(300);
		tree.insert(400);
		//System.out.println(tree);
		System.out.println(tree.root.toString2());
		System.out.println(tree.countOfLeaf());
		System.out.println("\n");
		tree.insert(200);
		System.out.println(tree.root.toString2());
		//System.out.println(tree);
		System.out.println(tree.countOfLeaf());
		System.out.println("\n");
		tree.insert(500);
		tree.insert(600);
		tree.insert(700);
		tree.insert(50);
		tree.insert(25);
		tree.insert(20);
		tree.insert(150);
		tree.insert(180);
		tree.insert(190);
		tree.insert(10);
		tree.insert(15);
		tree.insert(500);
		//System.out.println(tree);
		System.out.println(tree.root.toString2());
		System.out.println(tree.countOfLeaf());
		System.out.println("\n");
		tree.insert(12);
		System.out.println(tree.root.toString2());
		//System.out.println(tree);
		System.out.println(tree.countOfLeaf());
		System.out.println(tree.root.countOfChildren());
		
	}
	

}
