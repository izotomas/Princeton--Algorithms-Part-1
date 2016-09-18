package w4.event_driven_simulation.balls;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by tomasizo on 9/18/16.
 */

public class BouncingBalls {
    public static void main(String[] args)
    {
//        int N = Integer.parseInt(args[0]);
        int N = 3;
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            balls[i] = new Ball();
        }
        while (true)
        {
            StdDraw.clear();
            for (int i = 0; i < N; i++)
            {
                balls[i].move(0.2);
                balls[i].draw();
            }
            StdDraw.show();
        }
    }
}
