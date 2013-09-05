package com.exclus.NumbersAndColors;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: bbyte
 * Date: 8/26/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener
{
    static String currentGestureDetected;

    static boolean fling = false;

    // Override s all the callback methods of GestureDetector.SimpleOnGestureListener
    @Override
    public boolean onSingleTapUp(MotionEvent ev) {
//        currentGestureDetected=ev.toString();

        return true;
    }
    @Override
    public void onShowPress(MotionEvent ev) {
//        currentGestureDetected=ev.toString();

    }
    @Override
    public void onLongPress(MotionEvent ev) {
//        currentGestureDetected=ev.toString();

    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        currentGestureDetected=e1.toString()+ "  "+e2.toString();

        return true;
    }
    @Override
    public boolean onDown(MotionEvent ev) {
//        currentGestureDetected=ev.toString();

        return true;
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        currentGestureDetected = Float.toString(e1.getX()) + "  " + Float.toString(e2.getX()) + " " + Float.toString(velocityX) + Float.toString(velocityY);

        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //do your code

                currentGestureDetected = "right to left";

            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //left to right flip

                currentGestureDetected = "left to right";
            }

            fling = true;
        } catch (Exception e) {
        // nothing
        }

        return true;
    }
}
