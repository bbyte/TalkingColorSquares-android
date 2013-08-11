package com.exclus.talkincolorsquares;

/**
 * Created with IntelliJ IDEA.
 * User: bbyte
 * Date: 5/5/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.*;
import android.view.*;

import android.view.animation.Animation;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

import android.media.MediaPlayer;

import static com.exclus.talkincolorsquares.R.id.button1;

public class SuxActivity extends Activity
{
    private ViewFlipper[] vf;
    private ViewFlipper bigVf;
    private ImageButton[] buttons;
    private ImageButton sButton;
    private String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "eighteen", "nineteen", "twenty"};
    private String[] colors = {"blue", "yellow", "green", "orange", "black", "white", "brown", "pink", "gray", "red"};

    private boolean screenLock = false;

    private boolean modeIsNumbers = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sux);

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

//            Log.v("i: ",Integer.toString(i));

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

        MediaPlayer mediaPlayer = MediaPlayer.create(SuxActivity.this, getResources().getIdentifier(vx.getTag().toString(),
                "raw", getPackageName()));
//        MediaPlayer mediaPlayer = MediaPlayer.create(SuxActivity.this, R.raw.six);

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
            mediaPlayer = MediaPlayer.create(SuxActivity.this, R.raw.voice2);
        else
            mediaPlayer = MediaPlayer.create(SuxActivity.this, R.raw.voice1);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

        AnimationFactory.flipTransition(bigVf, FlipDirection.LEFT_RIGHT);

        modeIsNumbers = (modeIsNumbers ? false : true);
    }

    public void playSongClicked(View v)
    {
        if (screenLock) return;
        screenLock = true;

        MediaPlayer mediaPlayer;

        mediaPlayer = MediaPlayer.create(SuxActivity.this, R.raw.numberssong);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //To change body of implemented methods use File | Settings | File Templates.
                screenLock = false;
            }
        });
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }
}
