package w4.event_driven_simulation.balls;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by tomasizo on 9/18/16.
 */
public class Ball {

    private double rx, ry;          //position
    private double vx, vy;          //velocity
    private final double radius;    //radius

    public Ball() {
        radius = 1.0/100;
        rx = StdRandom.uniform();
        ry = StdRandom.uniform();
        vx = StdRandom.uniform(0,0.05);
        vy = StdRandom.uniform(0,0.05);
        /* init position and velocity */
    }

    public void move(double dt) {
         //check for collision with walls
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) { vx = -vx;}
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) { vy = -vy;}
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw() { StdDraw.filledCircle(rx, ry, radius); }
}
