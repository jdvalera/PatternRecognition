import java.util.ArrayList;

public class BruteCollinearPoints {
	
	LineSegment[] lineSegments = null;
	private ArrayList<LineSegment> tempSegments = null;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		tempSegments = new ArrayList<LineSegment>();
		
		for(int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				for (int k = j+1; k < points.length; k++) {
					for (int l = k+1; l < points.length; l++) {
						
					}
				}
			}
		}
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return 0;
	}
	
	// the line segments
	public LineSegment[] segments() {
		return lineSegments;	
	}

	public static void main(String[] args) {

	}

}
