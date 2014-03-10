import java.util.Stack;


public class Board {
	
	private final int dimension;

	private final int[][] board;
	
	
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    {
    	
    	dimension = blocks.length;
    	board = new int[dimension][dimension];
    	for(int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension;j++)
    		{
    			board[i][j]=blocks[i][j];
    		}
    	}
    }
    public int dimension()                 // board dimension N
    {
    	return dimension;
    }
    public int hamming()                   // number of blocks out of place
    {
    	int hamming = 0;
    	for(int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension;j++)
    		{
    			
    			if(i*dimension+j+1 != board[i][j] && board[i][j]!=0)
    				hamming ++;
    		}
    	}
    	return hamming;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	// Goal row, goal column, current row, current column
    	int g_r,g_c;
    	int manhattan = 0;
    	for(int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension;j++)
    		{
    			if(board[i][j]!=0)
    			{
    				g_r=(board[i][j]-1)/dimension;
    				g_c=(board[i][j]-1)%dimension;
    			
    				manhattan += Math.abs(i-g_r)+Math.abs(j-g_c);
    			}
    		}
    	}
    	
    	return manhattan;
    }
    public boolean isGoal()                // is this board the goal board?
    {
    	return this.hamming() == 0;
    }
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    {
    	int[][] twinb = new int[dimension][dimension];
    	
    	if(dimension < 1)
    		new Board(twinb);
    	
    	for(int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension;j++)
    		{
    			twinb[i][j]=this.board[i][j];
    		}
    	}
    	
    	for(int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension-1;j++)
    		{
    			if(twinb[i][j]!=0 && twinb[i][j+1]!=0)
    			{
    				int temp = twinb[i][j];
    				twinb[i][j] = twinb[i][j+1];
    				twinb[i][j+1] = temp; 
    				return new Board(twinb);
    			}
    		}
    	}
 
    	return new Board(twinb);

    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board temp = (Board) y;
        if(this.board.length != temp.board.length) return false;
        for(int i = 0;i<dimension;i++)
        	for(int j = 0;j<dimension;j++)
        	if(this.board[i][j]!=temp.board[i][j]) return false;
        
        return true;
        	
    }
    
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {
    	Stack<Board> boards = new Stack<Board>();
      	int zeror = 0;
    	int zeroc = 0;
    	for (int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension;j++)
    		
    		{
    			if(board[i][j]==0) 
    			{
    				zeror = i;
    				zeroc = j;   				
    			}
    		}
    	}
    		
    	
    
  
		if(zeror>0)
    	{
    	     int[][] boardUP;
			 boardUP = this.swap(zeror,zeroc,-1,0);
    	     boards.push(new Board(boardUP));
    	}
    	if(zeroc<dimension-1)
    	{
    	     int[][] boardR;
			 boardR = this.swap(zeror,zeroc,0,1);
    	     boards.push(new Board(boardR));
    	}
    	if(zeror<dimension-1)
    	{
    	     int[][] boardD;
			 boardD = this.swap(zeror,zeroc,1,0);
    	     boards.push(new Board(boardD));
    	}
    	
    	if(zeroc>0)
    	{
    	     int[][] boardL;
			 boardL = this.swap(zeror,zeroc,0,-1);
    	     boards.push(new Board(boardL));
    	}
    	
    	return boards;
    }
    private int[][] swap(int zr,int zc, int roffset, int coffset) {
		// TODO Auto-generated method stub
    	int[][] bswap = new int[dimension][dimension];
    	for(int i=0;i<dimension;i++)
    	{
    		for(int j=0;j<dimension;j++)
    		{
    			bswap[i][j]=this.board[i][j];
    		}
    	}
    	bswap[zr][zc]=bswap[zr+roffset][zc+coffset];
    	bswap[zr+roffset][zc+coffset] = 0;
    	
		return bswap;
	}
	public String toString()               // string representation of the board (in the output format specified below)
    {
	      int N = dimension;
	      StringBuilder s = new StringBuilder();
	      s.append(N + "\n");
	      for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	          s.append(String.format("%2d ", board[i][j]));
	        }
	        s.append("\n");
	      }
	      return s.toString();
    }
}