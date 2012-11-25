package com.obriand.android_imagemasking;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.widget.ImageView;

public class HeroImageView extends ImageView {
	
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    private Bitmap mIcon;
    private Bitmap mIconMask;

	public HeroImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
        // Prepares the paint that will be used to draw our icon mask. Using
        // PorterDuff.Mode.DST_IN means the image that will be drawn will
        // mask the already drawn image.
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // Let's retrieve all icon pieces as Bitmaps.
        final Resources res = context.getResources();
        this.
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
        canvas.drawBitmap(mIcon, 0, 0, null);
        //canvas.drawBitmap(mIconGlossy, 0, 0, null);
        
        Bitmap mIconScaledMask = mIconMask.createScaledBitmap(mIconMask, mIcon.getWidth(), mIcon.getHeight(), true);
        
        canvas.drawBitmap(mIconScaledMask, 0, 0, mPaint);

        canvas.restore();
    }
	
}

