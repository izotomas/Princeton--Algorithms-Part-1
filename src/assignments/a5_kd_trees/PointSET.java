package assignments.a5_kd_trees;

import edu.princeton.cs.algs4.*;

/**
 * Created by tomasizo on 9/25/16.
 */
public class PointSET {

    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET(){
        points = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty(){
        return points.isEmpty();
    }

    // number of points in the set
    public int size(){
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        if (p == null) throw new NullPointerException();
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p){
        if (p == null) throw new NullPointerException();
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw(){
        for(Point2D p: points) p.draw();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new NullPointerException();
        SET<Point2D> inside = new SET<>();
        for(Point2D p: points) { if (rect.contains(p)) inside.add(p); }
        return inside;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        if (p == null) throw new NullPointerException();
        Point2D nearest = null;
        double distance = Double.POSITIVE_INFINITY;
        for (Point2D point: points){
            if (p.distanceTo(point) < distance) {
                nearest = point;
                distance = point.distanceTo(p);
            }
        }
        return nearest;
    }

    // unit-testing
    public static void main(String[] args) {
        PointSET ps = new PointSET();
        for (int i = 0; i < 25; i++) {
            ps.insert(new Point2D(StdRandom.uniform(0.0,1.0), StdRandom.uniform(0.0,1.0)));
        }
        ps.draw();
    }
}
