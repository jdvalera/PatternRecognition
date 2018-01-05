import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	LineSegment[] lineSegments = null;
	private ArrayList<LineSegment> tempSegments = null;
	private int numOfSegments = 0;
	
	public FastCollinearPoints(Point[] points) {
		tempSegments = new ArrayList<LineSegment>();
		Arrays.sort(points);

		int i = 0;
		int j = 0;
		int numOfCollinear = 0;
		
		while (i < points.length) {
			System.out.println("Index of i: " + i);

			Arrays.sort(points, i, points.length, points[i].slopeOrder());
			j = i + 1;
			
			for (int k = 0; k < points.length - 1; k++) 
				System.out.println("Slope " + points[i] + "to " + points[k+1] + " : " + points[i].slopeTo(points[k+1])); 
			
			while(j < points.length) {

				if (!(points[i].slopeTo(points[j]) == Double.POSITIVE_INFINITY || points[i].slopeTo(points[j]) == 0.0
					|| points[i].slopeTo(points[j]) == points[i].slopeTo(points[j-1])))
				{
					j++;
				} else {
					numOfCollinear++;
					while(j < points.length && (points[i].slopeTo(points[j]) == Double.POSITIVE_INFINITY || points[i].slopeTo(points[j]) == 0.0
							|| points[i].slopeTo(points[j]) == points[i].slopeTo(points[j-1]))) {
						numOfCollinear++;
						j++;
					}
					System.out.println("Num of collinear: " + numOfCollinear);
					System.out.println("Index of j: " + j);
				}
				
				
				if(numOfCollinear > 2) {
					/*
					numOfSegments++;
					System.out.println("Segment " + points[i] + " to " + points[j-1]);
					LineSegment t = new LineSegment(points[i], points[j-1]);
					tempSegments.add(t); */
					
					if (points[i].compareTo(points[j-1]) < 0) {
						numOfSegments++;
						System.out.println("Segment " + points[i] + " to " + points[j-1]);
						LineSegment t = new LineSegment(points[i], points[j-1]);
						tempSegments.add(t);
					} 
						
				}
				numOfCollinear = 0;
			}
			i++;
		}
	}
	
	public int numberOfSegments() {
		return numOfSegments;	
	}
	
	public LineSegment[] segments() {
		lineSegments = new LineSegment[tempSegments.size()];
		lineSegments = tempSegments.toArray(lineSegments);
		return lineSegments;
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
	    	System.out.println(p);
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    System.out.println("Number of segments: " + collinear.numberOfSegments());
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show(); 
	}

}
