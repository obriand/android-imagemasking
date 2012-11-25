package com.obriand.android_imagemasking;

import com.obriand.android_imagemasking.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
	
    // Class IconView from View
	private class IconView extends View {
    	 
        private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
 
        private Bitmap mIcon;
        private Bitmap mIconGlossy;
        private Bitmap mIconMask;
 
        public IconView(Context context) {
            super(context);
 
            // Prepares the paint that will be used to draw our icon mask. Using
            // PorterDuff.Mode.DST_IN means the image that will be drawn will
            // mask the already drawn image.
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
 
            // Let's retrieve all icon pieces as Bitmaps.
            final Resources res = context.getResources();
            mIcon = BitmapFactory.decodeResource(res, R.drawable.igadget);
            //mIconGlossy = BitmapFactory.decodeResource(res, R.drawable.icon_glossy);
            mIconMask = BitmapFactory.decodeResource(res, R.drawable.vignette_hero_01);
        }
 
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
 
            // Translate the canvas in order to draw the icon in the center of
            // the view
            canvas.translate((getWidth() - mIcon.getWidth()) >> 1, (getHeight() - mIcon.getHeight()) >> 1);
 
            // We're now ready to drawn our iPhone-like icon :)
            //canvas.drawBitmap(mIcon, 0, 0, null);
            //canvas.drawBitmap(mIconGlossy, 0, 0, null);
            
            Bitmap mIconScaledMask = mIconMask.createScaledBitmap(mIconMask, mIcon.getWidth(), mIcon.getHeight(), true);
            
            canvas.drawBitmap(mIconScaledMask, 0, 0, mPaint);
 
            canvas.restore();
        }
 
    }
	
}
