package com.exclus.talkincolorsquares;

/**
 * Created with IntelliJ IDEA.
 * User: bbyte
 * Date: 5/5/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.*;
import android.view.*;

import android.view.animation.Animation;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

import android.media.MediaPlayer;

import java.util.Random;

public class MainActivity extends Activity
{
    private ViewFlipper[] vf;
    private ViewFlipper bigVf;
    private ImageButton[] buttons;
    private ImageButton sButton;
    private String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "eighteen", "nineteen", "twenty"};
    private String[] colors = {"blue", "yellow", "green", "orange", "black", "white", "brown", "pink", "gray", "red"};

    private boolean screenLock = false;

    private boolean modeIsNumbers = true;
    private boolean isRandom = false;


    private Handler newHandler = new Handler();
    private Integer currentNumber = 0;
    private Integer[] songMarkers = {0, 1000, 500, 1000, 900, 700, 800, 1000,1000,1000};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vf = new ViewFlipper[20];

        vf[0] = (ViewFlipper) findViewById(R.id.viewFlipper1);
        vf[1] = (ViewFlipper) findViewById(R.id.viewFlipper2);
        vf[2] = (ViewFlipper) findViewById(R.id.viewFlipper3);
        vf[3] = (ViewFlipper) findViewById(R.id.viewFlipper4);
        vf[4] = (ViewFlipper) findViewById(R.id.viewFlipper5);
        vf[5] = (ViewFlipper) findViewById(R.id.viewFlipper6);
        vf[6] = (ViewFlipper) findViewById(R.id.viewFlipper7);
        vf[7] = (ViewFlipper) findViewById(R.id.viewFlipper8);
        vf[8] = (ViewFlipper) findViewById(R.id.viewFlipper9);
        vf[9] = (ViewFlipper) findViewById(R.id.viewFlipper10);

        vf[10] = (ViewFlipper) findViewById(R.id.viewFlipperc1);
        vf[11] = (ViewFlipper) findViewById(R.id.viewFlipperc2);
        vf[12] = (ViewFlipper) findViewById(R.id.viewFlipperc3);
        vf[13] = (ViewFlipper) findViewById(R.id.viewFlipperc4);
        vf[14] = (ViewFlipper) findViewById(R.id.viewFlipperc5);
        vf[15] = (ViewFlipper) findViewById(R.id.viewFlipperc6);
        vf[16] = (ViewFlipper) findViewById(R.id.viewFlipperc7);
        vf[17] = (ViewFlipper) findViewById(R.id.viewFlipperc8);
        vf[18] = (ViewFlipper) findViewById(R.id.viewFlipperc9);
        vf[19] = (ViewFlipper) findViewById(R.id.viewFlipperc10);

        bigVf = (ViewFlipper) findViewById(R.id.mainFlipper);
//        vf = (ViewFlipper) findViewById(R.id.viewFlipper1);



        for(int i = 0; i < 10; i++){

            int j = i + 10;
            vf[i].setTag(numbers[i]);

            FrameLayout frameLayouts[] = {(FrameLayout) vf[i].getChildAt(0), (FrameLayout) vf[i].getChildAt(1)};

            TextView textView[] = {(TextView) frameLayouts[0].getChildAt(1), (TextView) frameLayouts[1].getChildAt(1)};

            textView[0].setText(Integer.toString(i + 1));
            textView[1].setText(Integer.toString(i + 1));

            ImageButton ss[] = {(ImageButton) frameLayouts[0].getChildAt(0), (ImageButton) frameLayouts[1].getChildAt(0)};

            ss[0].setImageResource(getResources().getIdentifier(colors[i],
                    "drawable", getPackageName()));
            ss[1].setImageResource(getResources().getIdentifier(colors[i],
                    "drawable", getPackageName()));

            vf[j].setTag(colors[i]);
            ss[0] = (ImageButton) vf[j].getChildAt(0);
            ss[1] = (ImageButton) vf[j].getChildAt(1);

            ss[0].setImageResource(getResources().getIdentifier(colors[i],
                    "drawable", getPackageName()));
            ss[1].setImageResource(getResources().getIdentifier(colors[i],
                    "drawable", getPackageName()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickBtn(View v)
    {
        if (screenLock) return;
        screenLock = true;

        ViewFlipper vx;
        if (modeIsNumbers)
            vx = (ViewFlipper) v.getParent().getParent();
        else
            vx = (ViewFlipper) v.getParent();

        Toast.makeText(this, vx.getTag().toString(), Toast.LENGTH_LONG).show();
//        Toast.makeText(this, v.getId(), Toast.LENGTH_LONG).show();

        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, getResources().getIdentifier(vx.getTag().toString(),
                "raw", getPackageName()));
//        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.six);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        ViewFlipper vf;

        if (modeIsNumbers)
            vf = (ViewFlipper) v.getParent().getParent();
        else
            vf = (ViewFlipper) v.getParent();

        AnimationFactory.flipTransition(vf, FlipDirection.LEFT_RIGHT);
    }

    public void onClickBtn1(View v)
    {
        if (screenLock) return;
        screenLock = true;

        MediaPlayer mediaPlayer;

        if (modeIsNumbers)
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.voice2);
        else
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.voice1);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        AnimationFactory.flipTransition(bigVf, FlipDirection.LEFT_RIGHT);

        modeIsNumbers = ! modeIsNumbers;
    }

    public void playSongClicked(View v)
    {
        if (screenLock) return;
        screenLock = true;

        MediaPlayer mediaPlayer;

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.numberssong);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        newHandler.removeCallbacks(newUpdateTask);
        newHandler.post(newUpdateTask);
    }

    //        #define randrange(N) rand() / (RAND_MAX/(N) + 1)
    private int randrange(int N)
    {
        Integer RAND_MAX = 2147483647;

        Random rand = new java.util.Random();

        Log.v("nextint: ", Integer.toString(rand.nextInt(RAND_MAX)));

        return(rand.nextInt(RAND_MAX) / (RAND_MAX / (N) + 1));
    }

    public void randomClicked(View v)
    {
        if (screenLock) return;
        screenLock = true;

        Integer[] a = new Integer[10];
        int i;
        int nvalues = 10;

        isRandom = ! isRandom;

        if (isRandom) {

            // random code

            for(i = 0; i < nvalues; i++)
                a[i] = i + 1;

            for(i = 0; i < nvalues - 1; i++) {
                int c = randrange(nvalues - i);
                int t = a[i]; a[i] = a[i+c]; a[i+c] = t;	/* swap */
            }

        } else {

            for(i = 0; i < nvalues; i++){

                a[i] = i + 1;
            }
        }

        int startI = 0;

        if (modeIsNumbers)
            startI = 0;

        else if (! modeIsNumbers)
            startI = 10;

        for (i = startI; i < (nvalues + startI); i++) {

            AnimationFactory.flipTransition(vf[i], FlipDirection.LEFT_RIGHT);
        }

        for (i = 0; i < a.length; i++) {

            vf[i + startI].setTag(numbers[a[i] - 1]);

//            if (modeIsNumbers){

            FrameLayout frameLayouts[] = {(FrameLayout) vf[i].getChildAt(0), (FrameLayout) vf[i].getChildAt(1)};

            TextView textView[] = {(TextView) frameLayouts[0].getChildAt(1), (TextView) frameLayouts[1].getChildAt(1)};

            textView[0].setText(Integer.toString(a[i]));
            textView[1].setText(Integer.toString(a[i]));

            ImageButton ss[] = {(ImageButton) frameLayouts[0].getChildAt(0), (ImageButton) frameLayouts[1].getChildAt(0)};

            ss[0].setImageResource(getResources().getIdentifier(colors[a[i] - 1],
                    "drawable", getPackageName()));
            ss[1].setImageResource(getResources().getIdentifier(colors[a[i] - 1],
                    "drawable", getPackageName()));
        }

        screenLock = false;
    }


    private Runnable newUpdateTask = new Runnable()
    {
        public void run()
        {
//            mTimeLabel.setText(Integer.toString(currentNumber + 1));

            Animation scaleUp = new ScaleAnimation(1f, 2.2f, 1f, 2.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            Animation scaleDown = new ScaleAnimation(1.2f, 0.5f, 1.2f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            scaleUp.setDuration(500);
            scaleDown.setDuration(500);

            AnimationSet bumpAnimationSet = new AnimationSet(true);
            bumpAnimationSet.setFillEnabled(true);
            bumpAnimationSet.addAnimation(scaleUp);
            bumpAnimationSet.addAnimation(scaleDown);

            vf[currentNumber].startAnimation(bumpAnimationSet);

            currentNumber++;

            if (currentNumber >= songMarkers.length) {

                newHandler.removeCallbacks(newUpdateTask);
                currentNumber = 0;
            } else {

                newHandler.postDelayed(this, songMarkers[currentNumber]);
            }
        }
    };

    public void buyClicked(View v)
    {

    }
}
