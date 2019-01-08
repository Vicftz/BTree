package BTree;

public class Main {

	public static void main(String[] args) {

		int order = 5;

		BTree tree = new BTree(order);
		BTree tree2 = new BTree(order);


		System.out.println(" ");
		System.out.println(" ----------------------------------------------TEST INSERTION -----------------------------------------------------");
		System.out.println(" On insère des valeurs quelconques non ordonnées dans un arbre d'ordre "+order +".");

		Integer[] tab = {10, 15, 30, 27, 35, 40, 45, 37, 20, 50, 55, 46, 71, 66, 74, 85, 90, 79, 78, 95, 25, 81, 68, 60, 65};
		for (Integer key: tab) {
			tree.insert(key);
			tree2.insert(key);
		}

		System.out.println(" ");
		System.out.println(tree);

		System.out.println(" On rajoute la valeur 100, qui va entraîner un split d'un noeud feuille.");
		System.out.println(" ");
		tree.insert(100);
		System.out.println(tree);
		System.out.println(" ");



		System.out.println(" ---------------------------------------------- TEST DELETION -----------------------------------------------------");
		System.out.println(" On supprime la valeur 60 (qui n'implique pas de merge et qui est dans une feuille");
		System.out.println(" ");
		tree.delete(60);
		System.out.println(tree);

		System.out.println(" ");
		System.out.println(" On supprime la valeur 85 (qui implique un merge)");
		System.out.println(" ");
		tree.delete(85);
		System.out.println(tree);

		System.out.println(" ");
		System.out.println(" On supprime les valeur 45(qui implique un merge par recursivité et une décrémentation du niveau de l'arbre");
		System.out.println(" ");
		tree.delete(45);
		System.out.println(tree);


		System.out.println(" ");
		System.out.println(" On supprime les valeur 65 et 55 (qui implique une rotation");
		System.out.println(" ");
		tree.delete(65);
		tree.delete(55);
		System.out.println(tree);


		System.out.println(" ");
		System.out.println(" On teste une suppression de la racine sur un nouvel arbre adapté");
		System.out.println(" ");
		System.out.println(" ");
		tree2.insert(67);
		tree2.insert(75);
		System.out.println(" NOUVEL ARBRE : ");
		System.out.println(tree2);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" APRES DELETION DU 46 (RACINE) : ");
		tree2.delete(46);
		System.out.println(" ");
		System.out.println(tree2);
		System.out.println(" ");











	}
	

}
