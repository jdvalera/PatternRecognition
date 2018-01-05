import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	LineSegment[] lineSegments = null;
	private ArrayList<LineSegment> tempSegments = null;
	private int numOfSegments = 0;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		tempSegments = new ArrayList<LineSegment>();
		Arrays.sort(points);
		for(int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				for (int k = j+1; k < points.length; k++) {
					for (int l = k+1; l < points.length; l++) {
						if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
								&& points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
							numOfSegments++;
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
		return numOfSegments;
	}
	
	// the line segments
	public LineSegment[] segments() {
		lineSegments = new LineSegment[tempSegments.size()];
		lineSegments = tempSegments.toArray(lineSegments);
		return lineSegments;	
	}

	public static void main(String[] args) {
		/*
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,2);
		Point p3 = new Point(3,3);
		Point p4 = new Point(4,4);
		
		Point[] points = new Point[4];
		points[0] = p1;
		points[1] = p2;
		points[2] = p3;
		points[3] = p4;
		
		BruteCollinearPoints b = new BruteCollinearPoints(points);
		System.out.println(b.numOfSegments);
		LineSegment[] lines;
		lines = b.segments();
		for(LineSegment l : lines) {
			System.out.println(l);
		}*/
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
	    	System.out.println(p);
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
