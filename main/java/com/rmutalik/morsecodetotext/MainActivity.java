package com.rmutalik.morsecodetotext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements KeyEvent.Callback {

    private GestureDetector mDetector;
    private MorseCodeGestureListener mListener;

    private TextView txt;
    private TextView result;
    private Button toMorseBtn;
    private Button toAlphaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);
        result = (TextView) findViewById(R.id.result);
        toMorseBtn = (Button) findViewById(R.id.toMorseBtn);
        toAlphaBtn = (Button) findViewById(R.id.toAlphaBtn);

        // this is the view we will add the gesture detector to
        View myView = findViewById(R.id.button);

        mListener = new MorseCodeGestureListener();

        // get the gesture detector
        mDetector = new GestureDetector(this, mListener);

        // Add a touch listener to the view
        // The touch listener passes all its events on to the gesture detector
        myView.setOnTouchListener(touchListener);

//        System.out.println(morseCode.getMorseCodeString());

        toMorseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtToConvert = txt.getText().toString();
                String convertedTxt = MorseCode.alphaToMorse(txtToConvert);
                result.setText(convertedTxt);
            }
        });

        toAlphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtToConvert = mListener.getMorseCodeString();
                String convertedTxt = MorseCode.morseToAlpha(txtToConvert);
                result.setText(convertedTxt);
            }
        });
    }

    // This touch listener passes everything on to the gesture detector.
    // That saves us the trouble of interpreting the raw touch events
    // ourselves.
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // pass the events to the gesture detector
            // a return value of true means the detector is handling it
            // a return value of false means the detector didn't
            // recognize the event
            return mDetector.onTouchEvent(event);

        }
    };

    class MorseCodeGestureListener extends GestureDetector.SimpleOnGestureListener {

        private String morseCodeString;

        public MorseCodeGestureListener() { morseCodeString = ""; }

        @Override
        public boolean onDown(MotionEvent event) {
            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        // 'Dot'
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            morseCodeString += '.';
            Log.i("onSingleTapConfirmed", morseCodeString);
            return true;
        }

        // 'Dash'
        @Override
        public void onLongPress(MotionEvent e) {
            morseCodeString += '-';
            Log.i("onLongPress", morseCodeString);
        }

        public String getMorseCodeString() {
            return morseCodeString;
        }

    }

/*    public boolean onTouchEvent(MotionEvent e) {
        float touchX = e.getX();
        float touchY = e.getY();
        switch (e.getAction()) {
*//*
            case MotionEvent.ACTION_DOWN:
                thread.setBubble(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                thread.setBubble(touchX, touchY);
                break;
*//*
            case MotionEvent.ACTION_UP:
                System.out.println(touchX + " " + touchY);
                break;
        }
        System.out.println(morseCode.getMorseCodeString());
        return true;
    }*/

/*    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            System.out.println("Volume down pressed.");
            return true;
        } else {
            return false;
        }
    }*/

}
