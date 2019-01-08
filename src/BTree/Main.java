package BTree;

public class Main {

	public static void main(String[] args) {
		BTree tree = new BTree(5);
		for (int i = 0;i<20;i++) {
			tree.insert(i);
		}
		System.out.println(tree);
		tree.delete(16);
		
		System.out.println(tree);
		tree.delete(13);
		System.out.println(tree);
		tree.delete(7);
		System.out.println(tree);
		tree.delete(4);
		System.out.println(tree);
		tree.delete(6);
		System.out.println(tree);
		tree.delete(5);
		System.out.println(tree);
		tree.delete(11);
		System.out.println(tree);
		tree.delete(12);
		System.out.println(tree);
		tree.delete(10);
		System.out.println(tree);
		
		
		//System.out.println(tree);
		//tree.delete(26);
		//System.out.println(tree);
		//tree.delete(8);
		//System.out.println(tree);
		//tree.delete(34);
		//System.out.println(tree);

	}
	

}
