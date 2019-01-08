package BTree;

public class Main {

	public static void main(String[] args) {
		BTree tree = new BTree(4);
		for (int i = 0;i<35;i++) {
			tree.insert(i++);
		}
		
		tree.insert(3);
		tree.insert(1);
		tree.delete(16);
		
		System.out.println(tree);


	}
	

}
