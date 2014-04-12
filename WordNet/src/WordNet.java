import java.lang.IllegalArgumentException;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.introcs.In;



public class WordNet {
	
	private SeparateChainingHashST<String, Bag<Integer>> nouns;
	private Digraph G;
	private ArrayList<String> synsets = new ArrayList<String>();
	private SAP sap;
	private DirectedCycle dc;
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms){
		String[] thisline;
		
		String[] thissynset;
		
		nouns = new SeparateChainingHashST<String, Bag<Integer>>();
		In in = new In(synsets);
		
		int counter = 0;
		while(in.hasNextLine()){
			thisline = in.readLine().split(",");
			int v = Integer.parseInt(thisline[0]);
			this.synsets.add(thisline[1]);
			thissynset = thisline[1].split(" ");
			
			for (String noun : thissynset){
				Bag<Integer> synsetIDs;
				if (!nouns.contains(noun)){
					synsetIDs = new Bag<Integer>();
				}
				else
				{
					synsetIDs = nouns.get(noun);
				}
				synsetIDs.add(v);
				nouns.put(noun, synsetIDs);
			}
			counter ++;
			
		}
		G = new Digraph(counter);
		in = new In(hypernyms);
		while (in.hasNextLine()){
			thisline = in.readLine().split(",");
			for (int i = 1;i < thisline.length; i++){
				G.addEdge(Integer.parseInt(thisline[0]),Integer.parseInt(thisline[i]));
			}
		}
		
		int rootvertex = 0;
		for (int v = 0; v < G.V();v++){
			if (!G.adj(v).iterator().hasNext())
				rootvertex ++;
		}
		if (rootvertex > 1){
			throw new java.lang.IllegalArgumentException("there were" + rootvertex);
		}
		
		dc = new DirectedCycle(G);
		if (dc.hasCycle()){
			throw new java.lang.IllegalArgumentException();
			
		}
		sap = new SAP(G);
		
	}

	// the set of nouns (no duplicates), returned as an Iterable
	public Iterable<String> nouns(){
		return nouns.keys();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word){
		return nouns.contains(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB){
		checkNoun(nounA);
		checkNoun(nounB);
		return sap.length(nouns.get(nounA), nouns.get(nounB));
	}

	private void checkNoun(String chknoun) {
		// TODO Auto-generated method stub
		if (!isNoun(chknoun)){
			throw new java.lang.IllegalArgumentException();
		}
		
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB){
		checkNoun(nounA);
		checkNoun(nounB);
		int sysnetID = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
		return synsets.get(sysnetID);
	}

	// for unit testing of this class
	public static void main(String[] args){
		
	}

}
