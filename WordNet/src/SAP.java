import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;


public class SAP {
	
	private Digraph G;
	
	
	
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G){
		this.G = new Digraph(G);
	}

	
	// length of shortest ancestral path between v and w; -1 if no such path
	
	public int length(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		return sap(v, w)[0];
	}
	
	// a common ancestor of v and w that participates in a shortest ancestral
	// path; -1 if no such path
	public int ancestor(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		return sap(v, w)[1];
	}
	
	private int[] sap(int v, int w) {
		// TODO Auto-generated method stub
		int min2talDis = Integer.MAX_VALUE;
		int new2talDis;
		int commonancestor = -1;
		BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(G,v);
		BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(G,w);
		
		for (int s = 0; s<G.V();s++){
			new2talDis = bfs_v.distTo(s)+bfs_w.distTo(s);
			if (new2talDis >= 0 && new2talDis < min2talDis){
				min2talDis = new2talDis;
				commonancestor = s;
			}
			
		}
		if (min2talDis == Integer.MAX_VALUE)
			min2talDis = -1;
		return new int[] {min2talDis,commonancestor};
	}


	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		for (int i : v)
			checkVertex(i);
		for (int i : w)
			checkVertex(i);
		return sap(v, w)[0];
		
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	private int[] sap(Iterable<Integer> v, Iterable<Integer> w) {
		// TODO Auto-generated method stub
		int min2talDis = Integer.MAX_VALUE;
		int new2talDis;
		int commonancestor = -1;
		BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(G,v);
		BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(G,w);
		
		for (int s = 0; s<G.V();s++){
			new2talDis = bfs_v.distTo(s)+bfs_w.distTo(s);
			if (new2talDis >= 0 && new2talDis < min2talDis){
				min2talDis = new2talDis;
				commonancestor = s;
			}
			
		}
		if (min2talDis == Integer.MAX_VALUE)
			min2talDis = -1;
		return new int[] {min2talDis,commonancestor};
	}

	private void checkVertex(int v) {
		// TODO Auto-generated method stub
		if (v < 0 || v >= G.V())
			throw new java.lang.IndexOutOfBoundsException();
		
	}

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		for (int i : v)
			checkVertex(i);
		for (int i : w)
			checkVertex(i);
		return sap(v, w)[1];
	}




	// for unit testing of this class (such as the one below)
	public static void main(String[] args) {
	    In in = new In(args[0]);
	    
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
//	    while (!StdIn.isEmpty()) {
//	        int v = StdIn.readInt();
//	        int w = StdIn.readInt();
//	        int length   = sap.length(v, w);
//	        int ancestor = sap.ancestor(v, w);
//	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//	    }
	    
	    int v = 3;
        int w = 11;
        int length   = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    
	}

}
