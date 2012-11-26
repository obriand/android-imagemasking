package com.obriand.android_imagemasking;

import com.obriand.android_imagemasking.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HeroImageView iv = (HeroImageView)findViewById(R.id.hero_item_hiv);
		iv.setImageResource(R.drawable.igadget);
		
		TextView tv = (TextView)findViewById(R.id.hero_name_tv);
		tv.setText(R.string.hello_world);
		
//		View iv = new IconView(this);
//		RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
//		mainLayout.addView(iv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
