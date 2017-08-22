package com.gst.frame.custom;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 *  自定义 点击图片，出现点击效果
 */
public class ClickImageView  extends ImageView{


    public ClickImageView(Context context, AttributeSet attrs, int defStyleAttr) {
       super(context, attrs, defStyleAttr);
       init();
    }

    public ClickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClickImageView(Context context) {
        super(context);
        init();
    }


    private void init() {
        setOnTouchListener(onTouchListener);
        setOnClickListener(onClickListener);
    }
    
    private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
    
    private OnTouchListener onTouchListener=new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    setColorFilter(null);
                    break;
                case MotionEvent.ACTION_DOWN:
                    changeLight();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    setColorFilter(null);
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    private void changeLight() {
       int brightness=-80;
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        setColorFilter(new ColorMatrixColorFilter(matrix));

    }

}
