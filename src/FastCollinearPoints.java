import java.util.ArrayList;

public class FastCollinearPoints {
	
	LineSegment[] lineSegments = null;
	private ArrayList<LineSegment> tempSegments = null;
	private int numOfSegments = 0;
	
	public FastCollinearPoints(Point[] points) {
		
	}
	
	public int numberOfSegments() {
		return numOfSegments;	
	}
	
	public LineSegment[] segments() {
		return lineSegments;
	}

	public static void main(String[] args) {

	}

}
