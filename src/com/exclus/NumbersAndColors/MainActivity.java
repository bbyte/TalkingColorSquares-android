package com.exclus.NumbersAndColors;

/**
 * Created with IntelliJ IDEA.
 * User: bbyte
 * Date: 5/5/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.*;
import android.view.*;

import android.view.animation.Animation;
import com.flurry.android.FlurryAgent;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

import android.media.MediaPlayer;

import android.view.GestureDetector;
import android.view.MotionEvent;

//import com.exclus.talkincolorsquares.GestureListener;


import java.util.Random;

public class MainActivity extends Activity
{
    private ViewFlipper[] vf;
    private ViewFlipper bigVf;
    private ImageButton[] buttons;
    private ImageButton sButton;
    private String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};
    private String[] colors = {"blue", "yellow", "green", "orange", "black", "white", "brown", "pink", "gray", "red"};

    private boolean screenLock = false;

    private boolean modeIsNumbers = true;
    private boolean isRandom = false;


    private Handler newHandler = new Handler();
    private Integer currentNumber = 0;
    private Integer[] songMarkers = {0, 1000, 500, 1000, 900, 700, 800, 1000,1000,1000};

    private boolean isMoreNumbers = false;
    private Integer[] buttonArrangment;


    private GestureDetector mGestureDetector;

    private static MediaPlayer mediaPlayer;

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlurryAgent.onStartSession(this, "H8G32BXN6G7QMVWWKTKP");

        prefs = getSharedPreferences(this.getPackageName(), MODE_PRIVATE);

        if (isFirstRun()) {

            addShortcut(getString(R.string.app_name));

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle(R.string.alert_title);
            alertDialog.setMessage(R.string.alert_content);
            alertDialog.setNeutralButton(R.string.alert_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            });

            alertDialog.show();

            setFirstRun();
        }


        // we want to control audio volume on the app from hardware buttons
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        screenLock = true;

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hello);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you


        buttonArrangment = createButtonArrangment();

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

        // Bind the gestureDetector to GestureListener
        mGestureDetector = new GestureDetector(this, new GestureListener());
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    public void onClickBtn(View v)
    {
        if (screenLock) return;
        screenLock = true;

        ViewFlipper vf;
        if (modeIsNumbers)
            vf = (ViewFlipper) v.getParent().getParent();
        else
            vf = (ViewFlipper) v.getParent();

//        Toast.makeText(this, vf.getTag().toString(), Toast.LENGTH_LONG).show();

        mediaPlayer = MediaPlayer.create(MainActivity.this, getResources().getIdentifier(vf.getTag().toString(),
                "raw", getPackageName()));

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        AnimationFactory.flipTransition(vf, FlipDirection.LEFT_RIGHT);
    }

    public void onClickChangeMode(View v)
    {
        if (screenLock) return;
        screenLock = true;

        FlurryAgent.logEvent("Change mode clicked");

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

        FlurryAgent.logEvent("Play song clicked");

//        MediaPlayer mediaPlayer;

        if (isMoreNumbers)
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mathsong);

        else
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.numberssong);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        if (! isMoreNumbers) {
            newHandler.removeCallbacks(newUpdateTask);
            newHandler.post(newUpdateTask);
        }
    }

    //        #define randrange(N) rand() / (RAND_MAX/(N) + 1)
    private int randrange(int N)
    {
        Integer RAND_MAX = 2147483647;  // from C++

        Random rand = new java.util.Random();

//        Log.v("nextint: ", Integer.toString(rand.nextInt(RAND_MAX)));

        return(rand.nextInt(RAND_MAX) / (RAND_MAX / (N) + 1));
    }

    private Integer[] createButtonArrangment()
    {
        Integer[] a = new Integer[10];
        int i;
        int nvalues = 10;

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

//        buttonArrangment = a;

        return a;
    }

    public void randomClicked(View v)
    {
        int i;
        int nvalues = 10;

        if (screenLock) return;
        screenLock = true;

        isRandom = ! isRandom;

        buttonArrangment = createButtonArrangment();

        Integer[] a = buttonArrangment;

        int startI = 0;

        if (modeIsNumbers)
            startI = 0;

        else if (! modeIsNumbers)
            startI = 10;


        if (modeIsNumbers) {

            setupNumbers(isMoreNumbers);
        } else {

            for (i = startI; i < (nvalues + startI); i++) {

                AnimationFactory.flipTransition(vf[i], FlipDirection.LEFT_RIGHT);
            }

            for (i = 0; i < a.length; i++) {

                vf[i + startI].setTag(colors[buttonArrangment[i] - 1]);

                ImageButton ss[] = {(ImageButton) vf[i + startI].getChildAt(0), (ImageButton) vf[i + startI].getChildAt(1)};

                ss[0].setImageResource(getResources().getIdentifier(colors[buttonArrangment[i] - 1],
                        "drawable", getPackageName()));
                ss[1].setImageResource(getResources().getIdentifier(colors[buttonArrangment[i] - 1],
                        "drawable", getPackageName()));
            }
        }

        screenLock = false;
    }

    private void setupNumbers(boolean isMoreNumbers)
    {
        for (int i = 0; i < 10; i++) {

            AnimationFactory.flipTransition(vf[i], FlipDirection.LEFT_RIGHT);
        }


        for(int i = 0; i < 10; i++){


            FrameLayout frameLayouts[] = {(FrameLayout) vf[i].getChildAt(0), (FrameLayout) vf[i].getChildAt(1)};

            TextView textView[] = {(TextView) frameLayouts[0].getChildAt(1), (TextView) frameLayouts[1].getChildAt(1)};

            int index;

            if (isMoreNumbers) {

                index = 10;
            } else {

                index = 0;
            }

            vf[i].setTag(numbers[buttonArrangment[i] + index - 1]);

//            Log.v("numbers", numbers[i + index - 1]);

            textView[0].setText(Integer.toString(buttonArrangment[i] + index));
            textView[1].setText(Integer.toString(buttonArrangment[i] + index));

            ImageButton ss[] = {(ImageButton) frameLayouts[0].getChildAt(0), (ImageButton) frameLayouts[1].getChildAt(0)};

            Log.v("button arrangment", Integer.toString(buttonArrangment[i]));
            Log.v("Tag", vf[i].getTag().toString());

            ss[0].setImageResource(getResources().getIdentifier(colors[buttonArrangment[i] - 1],
                    "drawable", getPackageName()));
            ss[1].setImageResource(getResources().getIdentifier(colors[buttonArrangment[i] - 1],
                    "drawable", getPackageName()));
        }
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

            int vfNumber = currentNumber;

            for (int i = 0; i < 10; i++) {

                FrameLayout temp = (FrameLayout) vf[i].getChildAt(0);
                TextView tempText = (TextView) temp.getChildAt(1);

                if (Integer.parseInt(tempText.getText().toString()) == (currentNumber + 1)) {

                    vfNumber = i;
                    break;
                }
            }

            vf[vfNumber].startAnimation(bumpAnimationSet);

//            Log.v("current", Integer.toString(buttonArrangment[currentNumber] - 1));

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
        isMoreNumbers = ! isMoreNumbers;
        isRandom = false;

        buttonArrangment = createButtonArrangment();
        setupNumbers(isMoreNumbers);

        ImageButton button = (ImageButton) v;

        if (isMoreNumbers)
            ((ImageButton) v).setImageResource(R.drawable.numbers);
        else
            ((ImageButton) v).setImageResource(R.drawable.buy);

        FlurryAgent.logEvent("Numbers change clicked");
    }

    // onTouch() method gets called each time you perform any touch event with screen
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //method onTouchEvent of GestureDetector class Analyzes the given motion event
        //and if applicable triggers the appropriate callbacks on the GestureDetector.OnGestureListener supplied.
        //Returns true if the GestureDetector.OnGestureListener consumed the event, else false.

        if (screenLock) return(false);

        boolean eventConsumed = mGestureDetector.onTouchEvent(event);
        if (eventConsumed)
        {
            if (GestureListener.fling) {
//                Toast.makeText(this,GestureListener.currentGestureDetected,Toast.LENGTH_SHORT).show();

                buyClicked(findViewById(R.id.buyButton)); // bigVF must to have some View

                GestureListener.fling = false;
            }

            return true;
        }
        else
            return false;
    }

    private boolean isFirstRun()
    {
        if (prefs.getBoolean("firstrun", true)) {

            return(true);
        } else {
            return(false);
        }
    }

    private void setFirstRun()
    {
        // Do first run stuff here then set 'firstrun' as false
        // using the following line to edit/commit prefs
        prefs.edit().putBoolean("firstrun", false).commit();
    }

    private void addShortcut(String shortcutName)
    {
        // create shortcut if requested
        Intent.ShortcutIconResource icon =
                Intent.ShortcutIconResource.fromContext(this, R.drawable.numbers);

        Intent intent = new Intent();

        Intent launchIntent = new Intent(this,MainActivity.class);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

        setResult(RESULT_OK, intent);

        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(intent);
    }
}
