package BTree;

public class Main {

	public static void main(String[] args) {
		BTree tree = new BTree(5);
		for (int i = 0;i<15;i++) {
			tree.insert(i);
		}
		System.out.println(tree);
		tree.delete(14);
		System.out.println(tree);
		tree.delete(13);
		System.out.println(tree);
		tree.delete(7);
		System.out.println(tree);
		tree.delete(1);
		System.out.println(tree);
		tree.delete(0);
		System.out.println(tree);
		tree.delete(3);
		System.out.println(tree);
		tree.delete(8);
		System.out.println(tree);
		System.out.println(tree);


	}
	

}
