package com.obriand.android_imagemasking;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HeroImageView extends ImageView {
	
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    private Bitmap mImage;
    private Bitmap mImageMask;
    private Bitmap mImageOmbre;

	public HeroImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
    public HeroImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub   
        init(context);
    }

    public HeroImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }
    
    public void init(Context context) {

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        final Resources res = context.getResources();
        mImageMask = BitmapFactory.decodeResource(res, R.drawable.vignette_hero_02);       
        mImageOmbre = BitmapFactory.decodeResource(res, R.drawable.ombre_vignette_hero_02);
    }
	
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        
        Bitmap mImageScaledMask = mImageMask.createScaledBitmap(mImageMask, getWidth(), getHeight(), true);
        Bitmap mtImageScaledMask = replaceColor(mImageScaledMask);
        canvas.drawBitmap(mtImageScaledMask, 0, 0, mPaint); 
        
        Bitmap mImageScaledOmbre = mImageOmbre.createScaledBitmap(mImageOmbre, getWidth(), getHeight(), true);        
        canvas.drawBitmap(mImageScaledOmbre, 0, 0, null);
        
        canvas.restore();
    }
    
	private Bitmap replaceColor(Bitmap bm) {
		Bitmap newBm = bm;
		int[] allpixels = new int[bm.getHeight() * bm.getWidth()];
		bm.getPixels(allpixels, 0, bm.getWidth(), 0, 0, bm.getWidth(),
				bm.getHeight());
		for (int i = 0; i < newBm.getHeight() * newBm.getWidth(); i++) {
			if (allpixels[i] == Color.BLACK)
				allpixels[i] = Color.TRANSPARENT;
		}
		newBm.setPixels(allpixels, 0, newBm.getWidth(), 0, 0, newBm.getWidth(),
				newBm.getHeight());

		return newBm;
    }
	
}

