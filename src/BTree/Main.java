package BTree;

public class Main {

	public static void main(String[] args) {
		BTree tree = new BTree(5);
		for (int i = 0;i<35;i++) {
			tree.insert(i);
		}
		System.out.println(tree);
		tree.delete(23);
		
		System.out.println(tree);
		tree.delete(26);
		System.out.println(tree);
		//tree.delete(8);
		System.out.println(tree);

	}
	

}
