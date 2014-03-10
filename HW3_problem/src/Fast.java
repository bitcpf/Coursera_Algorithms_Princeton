import java.util.Arrays;


public class Fast {

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        String fileName = args[0];
        Point[] drawpoints = extractdrawpoints(fileName);
        Point[] points = extractPoints(fileName);
        
        drawPoints(drawpoints);

        findCollinearPoints(points);

        StdDraw.show(0);
    }
    private static Point[] extractdrawpoints(String fileName) {
		// TODO Auto-generated method stub
        In in = new In(fileName);

        int n = in.readInt();
        int dflag = 0;

        Point[] drawpoints = new Point[n];
        int cnt=0;
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();

            Point point = new Point(x, y);
            for(int j = 0; j < cnt;j++)
            {
            	if(drawpoints[j].compareTo(point)==0)
            		dflag = 1;
            }
            
            if(dflag == 0)
            {
            	drawpoints[cnt]=point;
            	cnt++;
            }
            
            
         

            // point.draw();
        }

  
		return drawpoints;
	}
	private static Point[] extractPoints(String fileName) {
        In in = new In(fileName);

        int n = in.readInt();
        int dflag = 0;
        

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();

            Point point = new Point(x, y);
            points[i] = point;

            // point.draw();
        }

        return points;
    }
    
    private static void drawPoints(Point[] drawpoints)
    {
    	
    	for(Point point : drawpoints)
    	{
    		point.draw();
    	}
    }
    private static void findCollinearPoints(Point[] points) {
        // Sort point in natural order.
        Arrays.sort(points);

        int n = points.length;
        Point[] sortedPoints = new Point[n]; // Copy array
        for (int a = 0; a < n; a++) {
            Point aPoint = points[a]; // Origin point

            // Sort all points with respect to slope to origin point.
            // Since Java uses stable mergesort for sorting objects,
            // order of points with equal slope value will be natural.
            System.arraycopy(points, 0, sortedPoints, 0, n);
            Arrays.sort(sortedPoints, aPoint.SLOPE_ORDER);

            int i = 0;
            int j = 1;
            // Walk through sorted array trying to isolate sequences
            // of equal slope values.
            while (i < n - 2 && j < n) {
                double aiSlope = aPoint.slopeTo(sortedPoints[i]);
                double ajSlope = aPoint.slopeTo(sortedPoints[j]);

                // Check if the sequence was broken.
                if (Double.compare(aiSlope, ajSlope) != 0) {
                    // If the sequence was more than 3 items.
                    if (j - i >= 3) {
                        drawSegment(sortedPoints, aPoint, i, j - 1);
                    }
                    i = j;
                } else {
                    // If we reached the end of the array and
                    // if the sequence was more than 3 items.
                    if (j == n - 1 && j - i >= 2) {
                        drawSegment(sortedPoints, aPoint, i, j);
                    }
                }
                j++;
            }
        }
    }
    private static void drawSegment(Point[] points, Point a, int i, int j) {
        // If aPoint and iPoint are out of order, don't draw the segment.
        if (a.compareTo(points[i]) > 0) {
            return;
        }

        // Prepare an array for the segment
        Point[] collinear = new Point[j - i + 2];

        collinear[0] = a;
        for (int k = i; k <= j; k++) {
            collinear[k - i + 1] = points[k];
        }

        // Print out the segment
        for (int k = 0; k < collinear.length; k++) {
            String formatString = "%s";
            if (k > 0) formatString = " -> %s";

            StdOut.printf(formatString, collinear[k]);
        }
        StdOut.println();

        // Draw the segment
        collinear[0].drawTo(collinear[collinear.length - 1]);
    }
   
    
    
}