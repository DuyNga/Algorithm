package labw2d4;

class Node {

	Node left = null;
	Node right = null;
	int data = 0;

	public Node() {};

	public Node(int data) {
		this.left = null;
		this.right = null;
		this.data = data;
	}
}

class MyBST {

	Node root = null;

	public MyBST() {

	}
	
	public void print(Node root, int level) {
		if (root == null)
			return;
		
		print(root.right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			
			System.out.println("|-------" + root.data);
		} else
			System.out.println(root.data);
		print(root.left, level + 1);
	}

	public void insert(int data) {
		Node node = new Node(data);
		if (root == null) {
			root = node;
			return;
		}

		Node currentNode = root;
		Node parentNode = null;

		while (true) {
			parentNode = currentNode;
			
			if (currentNode.data > data) {
				currentNode = currentNode.left;
				if (currentNode == null) {
					parentNode.left = node;
					return;
				}
			} else {
				currentNode = currentNode.right;
				if (currentNode == null) {
					parentNode.right = node;
					return;
				}
			}
		}
	}

	public int countNodes(Node node) {
		int count = 0;
		if (node == null) {
			return 0;
		} else {
			count = 1;
			count += countNodes(node.left);
			count += countNodes(node.right);
		}

		return count;
	}

	public int countLeaves(Node node) {
		int count = 0;

		if (node == null)
			return 0;
		else if (node.left == null && node.right == null)
			count++;

		return count + countLeaves(node.left) + countLeaves(node.right);
	}

	public void preorder(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}

	public void inorder(Node node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.data + " ");
			inorder(node.right);
		}
	}

	public void postorder(Node node) {
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.data + " ");
		}
	}

	void mirror(Node node) {
		if (node != null) {
			mirror(node.left);
			mirror(node.right);

			Node temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}
	
	public static void main(String[] l) {
		MyBST tree = new MyBST();
		tree.insert(25);
		tree.insert(20);
		tree.insert(30);
		tree.insert(28);
		tree.insert(29);
		tree.insert(15);
		tree.insert(18);
		tree.insert(23);
		tree.insert(10);
		tree.insert(35);
		tree.insert(45);
		tree.insert(33);
		tree.insert(29);

		tree.print(tree.root, 0);
		
		// Question 3
		System.out.println("");
		System.out.println("Pre-order traversal: ");
		tree.preorder(tree.root);
		System.out.println();
		
		System.out.println("Post-order traversal: ");
		tree.postorder(tree.root);
		System.out.println();
		
		System.out.println("In-order traversal: ");
		tree.inorder(tree.root);
		System.out.println();
		
		//Question 4
		// (a) Count nodes
		System.out.println("");
		System.out.println("Total number of nodes: " + tree.countNodes(tree.root));
		
		// (b) Count leaves
		System.out.println("Total number of leaves: " + tree.countLeaves(tree.root));
		
		// (c) Create a mirror image of BST
		System.out.println("");
		System.out.println("The mirror image of the BST: ");
		tree.mirror(tree.root);
		tree.print(tree.root, 0);
	}
}