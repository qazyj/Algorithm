import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Node node = new Node(N);
		String s;
		while ((s = br.readLine()) != null) {
			N = Integer.parseInt(s);
			node = InsertNode(node, N);
		}
		
		PostOrder(node);
	}

	private static Node InsertNode(Node node, int N) {
		Node newNode = null;
		if (node == null) 	return new Node(N);

		if (node.value > N) {
			newNode = InsertNode(node.left, N);
			node.left = newNode;
		} 
		else {
			newNode = InsertNode(node.right, N);
			node.right = newNode;
		}
		
		return node;
	}

	private static void PostOrder(Node node) {
		if (node != null) {
			PostOrder(node.left);
			PostOrder(node.right);
			System.out.println(node.value);
		}
	}
	
	private static class Node {
		Node left;
		Node right;
		int value;

		public Node(int value) {
			this.value = value;
		}

	}
}