package com.exclus.talkincolorsquares;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ViewFlipper;
import android.view.*;
import android.widget.Toast;

import android.view.animation.Animation;
import com.tekle.oss.android.animation.AnimationFactory;
import com.tekle.oss.android.animation.AnimationFactory.FlipDirection;


public class MainActivity extends Activity {
	
	private ViewFlipper vf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vf = (ViewFlipper) findViewById(R.id.view_flipper);
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
		
		Animation[] animc = AnimationFactory.flipAnimation(vf.getCurrentView(), vf.getCurrentView(), FlipDirection.RIGHT_LEFT, 100, null);
		
//        vf.setOutAnimation(animc[0]);
//        vf.setInAnimation(animc[1]);
        
		
//		vf.showNext();
        
		View sux = vf.getCurrentView();
		
		animc = AnimationFactory.flipAnimation(vf.getCurrentView(), sux, FlipDirection.LEFT_RIGHT, 1000, null);
		
        vf.setOutAnimation(animc[1]);
        vf.setInAnimation(animc[0]);
		
		vf.showNext();

//	       vf.setOutAnimation(animc[0]);
//	        vf.setInAnimation(animc[1]);
			
			vf.showPrevious();

	}
}
