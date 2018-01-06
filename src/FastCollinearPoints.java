import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	private LineSegment[] lineSegments = null;
	private ArrayList<LineSegment> tempSegments = null;
	private int numOfSegments = 0;
	
	public FastCollinearPoints(Point[] points) {
		
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
		
		for (Point p : points) {
	    	System.out.println("Natural sorted: " + p);
	    }
		
		
		Point[] slopeSorted = new Point[points.length - 1];
		for (int i = 0; i < points.length; i++) {
			
			int idx = 0;
			int numOfCollinear = 0;
			int lastIndex = 0;
			for (int k = 0; k < points.length; k++) {
				
				if (k == i)
					continue;
				slopeSorted[idx++] = points[k];
			}	
			Arrays.sort(slopeSorted, points[i].slopeOrder());
			
			for (int l = 0; l < slopeSorted.length; l++)
				System.out.println("Slope of " + points[i] + " to " + slopeSorted[l] + " " + points[i].slopeTo(slopeSorted[l]));
			
			for (int k = 1; k < slopeSorted.length; k++) {
				
				if (!(points[i].slopeTo(slopeSorted[k]) == points[i].slopeTo(slopeSorted[k-1]))) {
					
				} else {
					
					numOfCollinear++;
					lastIndex = k;
				
					System.out.println("Num of Collinear " + numOfCollinear);
					System.out.println("Last Index " + lastIndex);
				
					if (numOfCollinear > 1) {
						Double slopeToLast = points[i].slopeTo(slopeSorted[lastIndex]);
						Boolean maxSegment = true;
						int numOfSame = 0;
						for (int n = 0; n < points.length; n++)
							if (points[i].slopeTo(slopeSorted[lastIndex]) == points[i].slopeTo(points[n]))
								numOfSame++;
						
						for (int m = i-1; m >= 0; m--) {
							if (slopeSorted[lastIndex].slopeTo(points[m]) == slopeToLast)
								maxSegment = false;
						}
						
						if (maxSegment && numOfCollinear+2 == numOfSame+1) {
							numOfSegments++;
							System.out.println("Segment " + points[i] + " to " + slopeSorted[lastIndex]);
							LineSegment t = new LineSegment(points[i], slopeSorted[lastIndex]);
							tempSegments.add(t);
						}
					}
				}
				
			}
			
			for (Point p : slopeSorted)
				System.out.println(p);
			
			System.out.println();

		} 
		
		/*
		int i = 0;
		int j = 0;
		int max = 0;
		int numOfCollinear = 0;
		
		while (i < points.length) {
			System.out.println("Index of i: " + i);
			//Arrays.sort(points, i, points.length);
			Arrays.sort(points, i, points.length, points[i].slopeOrder());
			j = 0;
			
			for (int k = 0; k < points.length - 1; k++) {
				System.out.println("Slope " + points[i] + "to " + points[k+1] + " : " + points[i].slopeTo(points[k+1])); 
			}
			
			
			   while(j < points.length) {
				if (j > 0) {
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
						System.out.println("INCREMENTING j: " + j);
					}
					System.out.println("Num of collinear: " + numOfCollinear);
					System.out.println("Index of j: " + j);
					max = j;
				}
				
				
				if(numOfCollinear > 2) {
					System.out.println("INSIDE: " + points[i] + " " + points[j-1] + " " + points[i].compareTo(points[j-1]));
					//numOfSegments++;
					//System.out.println("Segment " + points[i] + " to " + points[j-1]);
					//LineSegment t = new LineSegment(points[i], points[j-1]);
					//tempSegments.add(t); 
					
					if (points[i].compareTo(points[j-1]) < 0) {
						numOfSegments++;
						System.out.println("Segment " + points[i] + " to " + points[j-1]);
						LineSegment t = new LineSegment(points[i], points[j-1]);
						tempSegments.add(t);
					} 
						
				}
				numOfCollinear = 0;
				} else {
					j++;
				}
				
			}
			j = 0;
			i++;
			max = 0;
			
		} */
	} 
	
	public int numberOfSegments() {
		return numOfSegments;	
	}
	
	public LineSegment[] segments() {
		lineSegments = new LineSegment[tempSegments.size()];
		lineSegments = tempSegments.toArray(lineSegments);
		LineSegment[] copy = lineSegments;
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
	    System.out.println();
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
