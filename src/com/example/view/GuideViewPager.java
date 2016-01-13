package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 重写ViewPager  主要做一个切换背景的功能
 * @author ansen
 * @create time 2015-08-07
 */
public class GuideViewPager extends ViewPager {
	private Bitmap bg;
	private Paint b = new Paint(1);
	
	public GuideViewPager(Context context) {
		super(context);
	}

	public GuideViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (this.bg != null) {
			int width = this.bg.getWidth();
			int height = this.bg.getHeight();
			int count = getAdapter().getCount();
			int x = getScrollX();
			// 子View中背景图片需要显示的宽度，放大背景图或缩小背景图。
			int n = height * getWidth() / getHeight();
			
			/**
			 * (width - n) / (count - 1)表示除去显示第一个ViewPager页面用去的背景宽度，剩余的ViewPager需要显示的背景图片的宽度。
			 * getWidth()等于ViewPager一个页面的宽度，即手机屏幕宽度。在该计算中可以理解为滑动一个ViewPager页面需要滑动的像素值。
			 * ((width - n) / (count - 1)) /getWidth()也就表示ViewPager滑动一个像素时，背景图片滑动的宽度。
			 * x * ((width - n) / (count - 1)) /  getWidth()也就表示ViewPager滑动x个像素时，背景图片滑动的宽度。
			 * 背景图片滑动的宽度的宽度可以理解为背景图片滑动到达的位置。
			 */
			int w = x * ((width - n) / (count - 1)) / getWidth();
			canvas.drawBitmap(this.bg, new Rect(w, 0, n + w, height), new Rect( x, 0, x + getWidth(), getHeight()), this.b);
		}
		super.dispatchDraw(canvas);
	}
	
	public void setBackGroud(Bitmap paramBitmap) {
		this.bg = paramBitmap;
		this.b.setFilterBitmap(true);
	}
}
