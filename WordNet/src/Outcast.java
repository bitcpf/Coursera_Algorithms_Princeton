import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;


public class Outcast {
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet){
		this.wordnet = wordnet;
	}
	
	public String outcast(String[] nouns){
		int[] distance = new int[nouns.length];
		for (int i=0;i<nouns.length;i++){
			for (int j=i+1;j<nouns.length;j++){
				int dist = wordnet.distance(nouns[i], nouns[j]);
				distance[i] += dist;
				if (i !=j){
					distance[j] += dist;
				}
			}
		}
		int maxdist = 0;
		int maxidx = 0;
		for (int i=0;i<distance.length;i++){
			if (distance[i] > maxdist){
				maxdist = distance[i];
				maxidx = i;
			}
		}
			
		return nouns[maxidx];
	}

	public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
	
}
