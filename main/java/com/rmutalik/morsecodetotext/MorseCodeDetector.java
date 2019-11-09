package com.rmutalik.morsecodetotext;

import android.view.GestureDetector;
import android.view.MotionEvent;

// Gesture Detector - Morse Code
public class MorseCodeDetector extends GestureDetector.SimpleOnGestureListener {

    private String morseCodeString;

    public MorseCodeDetector() { morseCodeString = "_"; }

    @Override
    public boolean onDown(MotionEvent e) {
        System.out.println("Down down down");
        return true;
    }

    // 'Dot'
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        morseCodeString += '.';
        return super.onSingleTapConfirmed(e);
    }

    // 'Dash'
    @Override
    public void onLongPress(MotionEvent e) {
        morseCodeString += '-';
        super.onLongPress(e);
    }

    public String getMorseCodeString() {
        return morseCodeString;
    }

}
