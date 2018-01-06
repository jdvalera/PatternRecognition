import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	private ArrayList<LineSegment> tempSegments = null;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new java.lang.IllegalArgumentException();
		
		tempSegments = new ArrayList<LineSegment>();
		Arrays.sort(points);
		
		for(int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new java.lang.IllegalArgumentException();
			if (i < points.length - 1)
				if (points[i].compareTo(points[i+1]) == 0)
					throw new java.lang.IllegalArgumentException();
		}
		
		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				for (int k = j+1; k < points.length; k++) {
					for (int l = k+1; l < points.length; l++) {
						if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
								&& points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
							LineSegment t = new LineSegment(points[i], points[l]);
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
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    System.out.println("Number of segments: " + collinear.numberOfSegments());
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show(); 
	}

}
