

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Solver {
  
	private SearchNode result;
	private class SearchNode implements Comparable<SearchNode> 
	{
		private final Board board;
		private final int moves;
		private final SearchNode previous;
		private final int priority;
	
		private SearchNode(Board b, SearchNode p)
		{
			board = b;
			previous = p;
			if(previous == null) moves = 0;
			else moves = previous.moves+1;
			
			priority = board.manhattan() + moves;
			assert previous == null || priority >= previous.priority;
		}
		
		public int compareTo(SearchNode that)
		{
			return this.priority-that.priority;
		}
	
	}
	
	public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
	{
        if (initial.isGoal())
            result = new SearchNode(initial, null);
        else
            result = solve(initial, initial.twin());
	}

	private SearchNode step(MinPQ<SearchNode> pq) {
		// TODO Auto-generated method stub
		SearchNode least = pq.delMin();
        for (Board neighbor: least.board.neighbors()) {
            if (least.previous == null || !neighbor.equals(least.previous.board))
                pq.insert(new SearchNode(neighbor, least));
        }
        return least;
		
	}	
	private SearchNode solve(Board initial, Board twin) {
		// TODO Auto-generated method stub
		SearchNode last;
        MinPQ<SearchNode> mainpq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinpq = new MinPQ<SearchNode>();
        mainpq.insert(new SearchNode(initial, null));
        twinpq.insert(new SearchNode(twin, null));
        while (true) {
            last = step(mainpq);
            if (last.board.isGoal()) return last;
            if (step(twinpq).board.isGoal()) return null;
        }
		
	}
	public boolean isSolvable()             // is the initial board solvable?
	{
		return result != null;
	}
	public int moves()                      // min number of moves to solve initial board; -1 if no solution
	{
        if (result != null)
            return result.moves;
        return -1;
	}
	public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
	{
        if (result == null)
            return null;
        Stack<Board> s = new Stack<Board>();
        SearchNode temp = result;
        while(temp!=null)
        {
        	s.push(temp.board);
        	temp = temp.previous;
        }
        
        
        
        
        return s;
	}
	public static void main(String[] args)  // solve a slider puzzle (given below)
	{
	     In in = new In(args[0]);
	        int N = in.readInt();
	        int[][] blocks = new int[N][N];
	        for (int i = 0; i < N; i++)
	            for (int j = 0; j < N; j++)
	                blocks[i][j] = in.readInt();
	        Board initial = new Board(blocks);

	        // solve the puzzle
	        Solver solver = new Solver(initial);

	        // print solution to standard output
	        if (!solver.isSolvable())
	            StdOut.println("No solution possible");
	        else {
	            StdOut.println("Minimum number of moves = " + solver.moves());
	            for (Board board : solver.solution())
	                StdOut.println(board);
	        }
	}
	
}