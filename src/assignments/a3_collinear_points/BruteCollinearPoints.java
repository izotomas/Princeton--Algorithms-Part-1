package assignments.a3_collinear_points;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tomasizo on 9/15/16.
 */

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BruteCollinearPoints {

    private final LineSegment[] segments;
    private final Point[] points;

    // finds all line segments containing 4 points
    // points p, q, r, s are collinear, if slopes p-q, p-r and p-s are all equal
    public BruteCollinearPoints(Point[] points) {
        if (nullpoints(points)) throw new NullPointerException();
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
           this.points[i] = points[i];
        }
        Arrays.sort(this.points);
        if (duplicates(this.points)) throw new IllegalArgumentException();
        ArrayList<LineSegment> segments_list = new ArrayList<>();

        int N = this.points.length;
        for (int p = 0; p < N - 3; p++) {
            for (int q = p + 1; q < N - 2; q++) {
                for (int r = q + 1; r < N - 1; r++) {
                    double PQ = this.points[p].slopeTo(this.points[q]);
                    double PR = this.points[p].slopeTo(this.points[r]);
                    if (PQ == PR){
                        for (int s = r + 1; s < N; s++) {
                            double PS = this.points[p].slopeTo(this.points[s]);
                            if (PQ == PS) {
                                segments_list.add(new LineSegment(this.points[p], this.points[s]));
                            }
                        }
                    }
                }
            }
        }
        segments = segments_list.toArray(new LineSegment[segments_list.size()]);
    }

    private boolean nullpoints(Point[] points) {
        if  (points == null)                return true;
        for (Point p: points) if(p == null) return true;
        return false;
    }

    private boolean duplicates(Point[] points){
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i-1]) == 0) return true;
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

}
