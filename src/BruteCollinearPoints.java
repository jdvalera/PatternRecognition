import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	private ArrayList<LineSegment> tempSegments = null;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		
		Point[] copyOfPoints = points;
		
		if (points == null)
			throw new java.lang.IllegalArgumentException();
		
		for (Point p:points)
			if (p == null)
				throw new java.lang.IllegalArgumentException();
		
		tempSegments = new ArrayList<LineSegment>();
		Arrays.sort(copyOfPoints);
		
		for(int i = 0; i < copyOfPoints.length; i++) {
			if (i < copyOfPoints.length - 1)
				if (copyOfPoints[i].compareTo(copyOfPoints[i+1]) == 0)
					throw new java.lang.IllegalArgumentException();
		}
		
		for (int i = 0; i < copyOfPoints.length; i++) {
			for (int j = i+1; j < copyOfPoints.length; j++) {
				for (int k = j+1; k < copyOfPoints.length; k++) {
					for (int l = k+1; l < copyOfPoints.length; l++) {
						if (copyOfPoints[i].slopeTo(copyOfPoints[j]) == copyOfPoints[i].slopeTo(copyOfPoints[k])
								&& copyOfPoints[i].slopeTo(copyOfPoints[k]) == copyOfPoints[i].slopeTo(copyOfPoints[l])) {
							LineSegment t = new LineSegment(copyOfPoints[i], copyOfPoints[l]);
							tempSegments.add(t);
						}
					}
				}
			}
		}
	}
	
	// the number of line segments
	public int numberOfSegments() {
		return tempSegments.size();
	}
	
	// the line segments
	public LineSegment[] segments() {
		LineSegment[] copy = new LineSegment[tempSegments.size()];
		copy = tempSegments.toArray(copy);
		return copy;		
	}

	public static void main(String[] args) {
		// read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    System.out.println(n);
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }
	    
	    Point[] ps = new Point[2];
	    ps[0] = points[0];
	    ps[1] = null;
	    
	    System.out.println(ps[0]);
	    
	    BruteCollinearPoints test = new	BruteCollinearPoints(ps);

	    // draw the points
	    StdDraw.setPenRadius(.01);
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	    	//System.out.println(p);
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    //System.out.println("Number of segments: " + collinear.numberOfSegments());
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show(); 
	}

}
