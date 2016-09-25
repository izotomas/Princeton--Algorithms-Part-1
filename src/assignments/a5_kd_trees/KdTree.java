package assignments.a5_kd_trees;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by tomasizo on 9/25/16.
 */

public class KdTree {

    private static final boolean X   = true;  // compare by X coordinate
    private static final boolean Y  = false;  // compare by Y coordinate

    private Node root;

    private Point2D nearest;
    private double nearestD;

    private class Node {
        private final boolean type;
        private final Point2D point;
        private Node left, right;
        private int size;

        public Node(Point2D point, boolean type, int size) {
            this.type  = type;
            this.point = point;
            this.size  = size;
        }
    }

    // construct an empty set of points
    public KdTree(){    }


    // is the set empty?
    public boolean isEmpty(){
        return root == null;
    }

    // number of points in the set
    public int size(){
        return size(root);
    }

    private int size(Node h){
        if (h == null) return 0;
        return h.size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("first argument to put() is null");
        root = insert(root, p, X);
    }

    // insert the key-value pair in the subtree rooted at h
    private Node insert(Node h, Point2D p, boolean type) {
        if (h == null) return new Node(p, type, 1);
        else if (h.point.compareTo(p) == 0) return h; // duplicate insertion

        double cmp;
        if (type == X)  cmp = p.x() - h.point.x();
        else            cmp = p.y() - h.point.y();

        if  (cmp < 0.0) h.left  = insert(h.left, p, !type);
        else            h.right = insert(h.right, p, !type);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    // does the set contain point p?
    public boolean contains(Point2D p){
        if (p == null) throw new NullPointerException();
        return get(root, p) != null;
    }

    private Node get(Node x, Point2D p) {
        if (x == null) return null;
        if (x.point.compareTo(p) == 0) return x;

        double cmp;
        if (x.type == Y) cmp = p.y() - x.point.y();
        else             cmp = p.x() - x.point.x();

        if (cmp < 0) return get(x.left, p);
        else         return get(x.right, p);
    }

    // draw all points to standard draw
    public void draw(){
        Node tmp = new Node(new Point2D(0,0), Y, 0); // because root has no parent...
        draw(root, tmp, 0, 1, 0, 1);
    }

    private void draw(Node n, Node parent, double xmin, double xmax, double ymin, double ymax) {
        if (n == null) return;

        double[] x = {n.point.x(), parent.point.x()}; // x-coordinates for both points
        double[] y = {n.point.y(), parent.point.y()}; // x-coordinates for both points

        if (n.type == Y) {     // line is horizontal
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            if (x[0] < x[1]) { // point is to the left from parent
                StdDraw.line(xmin, y[0] , x[1], y[0]);
                xmax = x[1];
            }
            else {
                StdDraw.line(x[1], y[0] , xmax, y[0]);
                xmin = x[1];
            }
            draw( n.left, n, xmin, xmax, ymin, y[0]);
            draw(n.right, n, xmin, xmax, y[0], ymax);
        }
        else {                 // line is vertical
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            if (y[0] < y[1]) { // point is bellow its parent
                StdDraw.line(x[0], ymin , x[0], y[1]);
                ymax = y[1];
            }
            else {
                StdDraw.line(x[0], y[1] , x[0], ymax);
                ymin = y[1];
            }
            draw( n.left, n, xmin, x[0], ymin, ymax);
            draw(n.right, n, x[0], xmax, ymin, ymax);
        }
        n.point.draw();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new NullPointerException();
        SET<Point2D> in = new SET<>();
        range(root, rect, in);
        return in;
    }

    private void range(Node n, RectHV rect, SET<Point2D> list) {
        if      (n == null) return;
        else if (rect.contains(n.point)) list.add(n.point);

        double x  = n.point.x();
        double y  = n.point.y();
        boolean t = n.type;

        if      ( (t == X && rect.xmax() <  x) || (t == Y && rect.ymax() <  y) )
            range( n.left, rect, list);
        else if ( (t == X && rect.xmin() >= x) || (t == Y && rect.ymin() >= y) )
            range( n.right, rect, list);
        else {
            range(n.left, rect, list);
            range(n.right, rect, list);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        nearest = null;
        nearestD = Double.POSITIVE_INFINITY;
        nearest(root, p);
        return nearest;
    }

    private void nearest(Node n, Point2D p) {
        if (n == null) return;

        double dist = p.distanceTo(n.point);
        if (dist < nearestD) {
            nearestD = dist;
            nearest = n.point;
        }

        boolean t = n.type;
        double x = n.point.x();
        double y = n.point.y();

        if ((t == X && p.x() < x) || (t == Y && p.y() < y) ) {
            nearest(n.left, p);
            if ((t == X && nearestD >= x - p.x()) || (t == Y && nearestD >= y - p.y()))
                nearest(n.right, p);
        } else {
            nearest(n.right, p);
            if ((t == X && nearestD >= p.x() - x) || (t == Y && nearestD >= p.y() - y))
                nearest(n.left, p);
        }
    }

    public static void main(String[] args) {
        KdTree t = new KdTree();
        System.out.println(t.isEmpty());
        t.insert(new Point2D(0.4,0.5));
        t.insert(new Point2D(0.6,0.4));
        t.insert(new Point2D(0.25,0.6));
        t.insert(new Point2D(0.15,0.2));
        t.insert(new Point2D(0.1,0.5));
        t.insert(new Point2D(0.35,0.8));
        t.insert(new Point2D(0.5,0.2));
        t.insert(new Point2D(0.8,0.9));
        t.insert(new Point2D(0.7,0.8));
        t.insert(new Point2D(0.65,0.15));

//        t.draw();
        System.out.println(t.contains(new Point2D(0.4,0.5)));
        System.out.println(t.contains(new Point2D(0.6,0.4)));
        System.out.println(t.contains(new Point2D(0.25,0.6)));
        System.out.println(t.contains(new Point2D(0.15,0.2)));
        System.out.println(t.contains(new Point2D(0.1,0.5)));
        System.out.println(t.contains(new Point2D(0.35,0.8)));
        System.out.println(t.contains(new Point2D(0.5,0.2)));
        System.out.println(t.contains(new Point2D(0.8,0.9)));
        System.out.println(t.contains(new Point2D(0.7,0.8)));
        System.out.println(t.contains(new Point2D(0.65,0.15)));

        System.out.println(t.contains(new Point2D(0.64,0.15)));
        System.out.println(t.size());
    }

}
