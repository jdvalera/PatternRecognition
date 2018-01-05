/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    private double slope(int p1x, int p1y, int p2x, int p2y) {
    	return java.lang.Math.abs( (double) (p1y - p2y)/(p1x - p2x));
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
    	/* YOUR CODE HERE */
    	if (x == that.x && y == that.y)
    		return Double.NEGATIVE_INFINITY;
    	else if (x == that.x)
    		return Double.POSITIVE_INFINITY;
    	else if (y == that.y)
    		return 0.0;
    	else
    		return slope(x, y, that.x, that.y);
    		//return java.lang.Math.abs( (double) (that.y - y)/(that.x - x));
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
    	/* YOUR CODE HERE */
    	if (y > that.y)
    		return 1;
    	else if (y < that.y)
    		return -1;
    	else if (y == that.y && x < that.x)
    		return -1;
    	else if (y == that.y && x > that.x)
    		return 1;
    	else
    		return 0; 
    }
    
    private class SlopeOrder implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			Double s1 = 0.0 , s2 = 0.0;
			if (x == p1.x && y == p1.y)
				s1 = Double.NEGATIVE_INFINITY;
			else if (x == p1.x)
				s1 = Double.POSITIVE_INFINITY;
			else if (y == p1.y)
				s1 = 0.0;
			else
				s1 = slope(x, y, p1.x, p1.y);
			
			if (x == p2.x && y == p2.y)
				s2 = Double.NEGATIVE_INFINITY;
			else if (x == p2.x)
				s2 = Double.POSITIVE_INFINITY;
			else if (y == p2.y)
				s2 = 0.0;
			else
				s2 = slope(x, y, p2.x, p2.y);
			
			if (s1 < s2)
				return -1;
			else if (s1 > s2)
				return 1;
			else if (p1.y > p2.y)
				return 1;
			else if (p1.y < p2.y)
				return -1;
			else if (p1.y == p2.y && p1.x < p2.x)
				return -1;
			else if (p1.y == p2.y && p1.x > p2.x)
				return 1;
			else
				return 0;
			
			/*
			if (Point.this.slopeTo(p1) < Point.this.slopeTo(p2))
				return -1;
			else if (Point.this.slopeTo(p1) > Point.this.slopeTo(p2))
				return 1;
			else
				return 0;
				*/
		}
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
    	/* YOUR CODE HERE */
		return new SlopeOrder();
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point p1 = new Point(1,1);
    	Point p2 = new Point(1,1);
    	Point p3 = new Point(1,2);
    	Point p4 = new Point(2,1);
    	Point p5 = new Point(5,10);
    	Point p6 = new Point(2,2);
    	Point p7 = new Point(2,4);
    	
    	System.out.println("Degenerate line: " + p1.slopeTo(p2));
    	System.out.println("Vertical line: " + p1.slopeTo(p3));
    	System.out.println("Horizontal line: " + p1.slopeTo(p4));
    	System.out.println("Slope of normal line segment: " + p1.slopeTo(p5));
    	
    	System.out.println("Point 1 bigger y value than point 2: " + p4.compareTo(p1));
    	System.out.println("Point 1 smaller y value than point 2: " + p1.compareTo(p4));
    	System.out.println("Same y value bigger x: " + p4.compareTo(p1));
    	System.out.println("Same y value smaller x: " + p1.compareTo(p4));
    	System.out.println("Same point values: " + p1.compareTo(p2));
    	
    	Point[] A = new Point[7];
    	A[0] = p1;
    	A[1] = p2;
    	A[2] = p3;
    	A[3] = p4;
    	A[4] = p5;
    	A[5] = p6;
    	A[6] = p7;
    	
    	for (int i = 0; i < A.length; i++) {
    		System.out.println("Slope to A[" + i + "]: " + A[0].slopeTo(A[i]));
    	}
    	
    	for (Point a : A) {
    		System.out.println("Before Sort: " + a);
    	}
    	
    	Arrays.sort(A, p2.slopeOrder());
    	
    	for (Point a : A) {
    		System.out.println("After Sort: " + a);
    	}

    	
    }
}