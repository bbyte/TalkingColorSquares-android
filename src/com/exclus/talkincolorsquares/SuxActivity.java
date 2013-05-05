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
import android.view.Menu;
import android.widget.ViewFlipper;
import android.view.*;
import android.widget.Toast;

import android.view.animation.Animation;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;

public class SuxActivity extends Activity
{
    private ViewFlipper[] vf;
    private ViewFlipper bigVf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sux);

        vf = new ViewFlipper[10];

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

        bigVf = (ViewFlipper) findViewById(R.id.mainFlipper);
//        vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
//		vf.setInAnimation(this, R.anim.in_from_left);
//
//		vf.setOutAnimation(this, R.anim.out_to_right);

//		AnimationFactory.flipTransition(vf, FlipDirection.LEFT_RIGHT);
//		vf.showNext();
//		AnimationFactory.flipTransition(vf, FlipDirection.RIGHT_LEFT);
//		vf.showNext();

//        Animation[] animc = AnimationFactory.flipAnimation(vf.getCurrentView(), vf.getCurrentView(), FlipDirection.RIGHT_LEFT, 100, null);


//        vf.setOutAnimation(animc[0]);
//        vf.setInAnimation(animc[1]);


//		vf.showNext();

//        View sux = vf.getCurrentView();

        View sux = (View) v.getParent();
        ViewFlipper vf = (ViewFlipper) v.getParent();
        Animation[] animc = AnimationFactory.flipAnimation(vf.getCurrentView(), vf.getCurrentView(), FlipDirection.LEFT_RIGHT, 1000, null);
        Animation[] animx = AnimationFactory.flipAnimation(vf.getCurrentView(), vf.getCurrentView(), FlipDirection.RIGHT_LEFT, 1000, null);

        vf.setOutAnimation(animc[1]);
        vf.setInAnimation(animc[0]);

//        vf.showNext();

        vf.setFlipInterval(1000);
        vf.startFlipping();
//	       vf.setOutAnimation(animc[0]);
//	        vf.setInAnimation(animc[1]);

        vf.showPrevious();

    }
}
