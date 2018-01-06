import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	private ArrayList<LineSegment> tempSegments = null;
	
	public FastCollinearPoints(Point[] points) {
		
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
		
		/*
		for (Point p : points) {
	    	System.out.println("Natural sorted: " + p);
	    }*/
		
		
		Point[] slopeSorted = new Point[copyOfPoints.length - 1];
		for (int i = 0; i < copyOfPoints.length; i++) {
			
			int idx = 0;
			int numOfCollinear = 0;
			int lastIndex = 0;
			for (int k = 0; k < copyOfPoints.length; k++) {
				
				if (k == i)
					continue;
				slopeSorted[idx++] = copyOfPoints[k];
			}	
			Arrays.sort(slopeSorted, copyOfPoints[i].slopeOrder());
			
			//for (int l = 0; l < slopeSorted.length; l++)
				//System.out.println("Slope of " + points[i] + " to " + slopeSorted[l] + " " + points[i].slopeTo(slopeSorted[l]));
			
			for (int k = 1; k < slopeSorted.length; k++) {
				
				//System.out.println("Current slope: " + points[i].slopeTo(slopeSorted[k]));
				
				if (!(copyOfPoints[i].slopeTo(slopeSorted[k]) == copyOfPoints[i].slopeTo(slopeSorted[k-1]))) {
					
				} else {
					
					numOfCollinear++;
					lastIndex = k;
				
					//System.out.println("Num of Collinear " + numOfCollinear);
					//System.out.println("Last Index " + lastIndex);
				
					if (numOfCollinear > 1) {
						Double slopeToLast = copyOfPoints[i].slopeTo(slopeSorted[lastIndex]);
						Boolean maxSegment = true;
						int numOfSame = 0;
						for (int n = 0; n < copyOfPoints.length; n++)
							if (copyOfPoints[i].slopeTo(slopeSorted[lastIndex]) == copyOfPoints[i].slopeTo(copyOfPoints[n]))
								numOfSame++;
						
						for (int m = i-1; m >= 0; m--) {
							if (slopeSorted[lastIndex].slopeTo(copyOfPoints[m]) == slopeToLast)
								maxSegment = false;
						}
						//System.out.println("NUM OF COLLINEAR: " + numOfCollinear + " NUM OF SAME: " + numOfSame);
						if (maxSegment && numOfCollinear+2 == numOfSame+1) {
							//System.out.println();
							//System.out.println("Segment " + points[i] + " to " + slopeSorted[lastIndex]);
							//System.out.println();
							LineSegment t = new LineSegment(copyOfPoints[i], slopeSorted[lastIndex]);
							tempSegments.add(t);
						}
					}
				}
				
				if (k < slopeSorted.length - 1 && 
						copyOfPoints[i].slopeTo(slopeSorted[k]) != copyOfPoints[i].slopeTo(slopeSorted[k+1])) {
					numOfCollinear = 0;
				}
				
			}
			
			//for (Point p : slopeSorted)
				//System.out.println(p);
			
			//System.out.println();

		} 
	} 
	
	public int numberOfSegments() {
		return tempSegments.size();	
	}
	
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
	    StdDraw.setPenRadius(.002);
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	    	//System.out.println(p);
	        p.draw();
	    }

	    //System.out.println();
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    System.out.println("Number of segments: " + collinear.numberOfSegments());
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    //collinear.segments()[1].draw();
	    //StdOut.println("SEGMENT " + collinear.segments()[1]);
	    StdDraw.show(); 
	}

}
