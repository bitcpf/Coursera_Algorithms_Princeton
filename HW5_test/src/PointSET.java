import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class PointSET {

	private Set<Point2D> points;
	
	public PointSET(){
		points = new TreeSet<Point2D>();
	}
	
	public boolean isEmpty(){
		return points.isEmpty();
	}
	
	public int size(){
		return points.size();
	}
	
	public void insert(Point2D p){
		points.add(p);
	}
	
	public boolean contains(Point2D p){
		return points.contains(p);
	}
	public Iterable<Point2D> range(RectHV rect){
		List<Point2D> range = new ArrayList<Point2D>();
		for (Point2D point : points){
			if (rect.contains(point)){
				range.add(point);
			}
		}
		return range;
		
	}
	public Point2D nearest(Point2D p){
		Point2D minPoint = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for (Point2D point : points){
			double distance = p.distanceSquaredTo(point);
			if (Double.compare(distance, minDistance)<0){
				minPoint = point;
				minDistance = distance;
			}
		}
		return minPoint;
	}
	public void draw(){
		for (Point2D point : points){
			point.draw();
		}
	}
}
