package BTree;

public class Main {

	public static void main(String[] args) {
		/*BTree tree = new BTree(5);
		tree.insert(0);
		tree.insert(100);
		tree.insert(300);
		tree.insert(400);
		System.out.println(tree);
		System.out.println("\n");
		tree.insert(200);
		System.out.println(tree);
		System.out.println("\n");
		tree.insert(500);
		tree.insert(600);
		tree.insert(700);
		System.out.println(tree);
		tree.insert(403);
		System.out.println(tree);
		tree.insert(404);
		System.out.println(tree);
		System.out.println("\n");
		tree.insert(12);
		System.out.println(tree);
		tree.insert(401);
		System.out.println(tree);
		tree.insert(405);
		tree.insert(406);
		tree.insert(407);
		System.out.println(tree);
		System.out.println(tree.countOfLeaf());
		tree.insert(408);
		tree.insert(409);
		System.out.println(tree);
		tree.insert(410);
		System.out.println(tree);
		System.out.println(tree.countOfLeaf());
		tree.insert(411);
		tree.insert(412);
		System.out.println(tree);
		tree.insert(409);
		tree.insert(410);
		System.out.println(tree);*/
		
		BTree tree = new BTree(3);
		for (int i = 0;i<10;i++) {
			tree.insert(i);
		}
		
	
		System.out.println(tree);
	}
	

}
