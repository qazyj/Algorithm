import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class NODE {
		String val;
		int parent, right, left;

		public NODE(String val) {
			this.val = val;
			this.parent = this.right = this.left = -1;
		}
	};

	static int N;
	static NODE A[] = new NODE[26];
	static String tmpstr;

	static void preOrder(int node) {
		System.out.print(A[node].val);
		if (A[node].left > -1)
			preOrder(A[node].left);
		if (A[node].right > -1)
			preOrder(A[node].right);
	}

	static void inOrder(int node) {
		if (A[node].left > -1)
			inOrder(A[node].left);
		System.out.print(A[node].val);
		if (A[node].right > -1)
			inOrder(A[node].right);
	}

	static void postOrder(int node) {
		if (A[node].left > -1)
			postOrder(A[node].left);
		if (A[node].right > -1)
			postOrder(A[node].right);
		System.out.print(A[node].val);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < 26; i++) {
			A[i] = new NODE(Character.toString((char) (i + 'A')));
		}
		for (int i = 0; i < N; i++) {
			char n, l, r;
			tmpstr = br.readLine();
			n = tmpstr.charAt(0);
			l = tmpstr.charAt(2);
			r = tmpstr.charAt(4);
			if (l != '.') {
				A[n - 'A'].left = l - 'A';
				A[l - 'A'].parent = n - 'A';
			}
			if (r != '.') {
				A[n - 'A'].right = r - 'A';
				A[r - 'A'].parent = n - 'A';
			}
		}
		preOrder(0);
		System.out.println();
		inOrder(0);
		System.out.println();
		postOrder(0);
		System.out.println();
	}
}
