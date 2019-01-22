package com.jianjunhuang.demo.viewpagerindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class StripIndicatorView extends View {

  private static final String TAG = StripIndicatorView.class.getName();

  private float mItemWidth;
  private float mItemHeight;
  private float mMargin;
  private float mRadius;

  private int mSelectedColor = Color.WHITE;
  private int mUnSelectedColor = Color.parseColor("#80FFFFFF");
  //  private int mUnSelectedColor = Color.GREEN;
  private int mItemSize;
  private int mSelectedPos;

  private Paint mSelectedPaint;
  private Paint mUnselectedPaint;

  private RectF mRectF;

  public StripIndicatorView(Context context) {
    this(context, null);
  }

  public StripIndicatorView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public StripIndicatorView(Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    mItemHeight = dpToPx(2);
    mItemWidth = dpToPx(4);
    mMargin = dpToPx(2);
    mRadius = dpToPx(1);

    mSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mSelectedPaint.setColor(mSelectedColor);

    mUnselectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mUnselectedPaint.setColor(mUnSelectedColor);

    mRectF = new RectF();
    setPadding(2, 2, 2, 2);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(measuredWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec));
  }

  private int measuredHeight(int heightSpec) {
    int mode = MeasureSpec.getMode(heightSpec);
    int size = MeasureSpec.getSize(heightSpec);

    if (mode == MeasureSpec.EXACTLY) {
      return size;
    }
    return (int) mItemHeight + getPaddingTop() + getPaddingBottom();
  }

  private int measuredWidth(int widthSpec) {
    int mode = MeasureSpec.getMode(widthSpec);
    int size = MeasureSpec.getSize(widthSpec);
    if (mode == MeasureSpec.EXACTLY) {
      return size;
    }
    return (int) (mItemSize * mItemWidth + (mItemSize - 1) * mMargin + getPaddingLeft()
        + getPaddingRight());
  }

  @Override
  protected void onDraw(Canvas canvas) {

    canvas.drawColor(Color.TRANSPARENT);
    float centerY = getHeight() / 2;
    float itemCenterY = mItemHeight / 2;
    float top = centerY - itemCenterY;
    float bottom = centerY + itemCenterY;
    //draw unselected item
    for (int i = 0; i < mItemSize; i++) {
      mRectF.set(getItemLeft(i), top, getItemLeft(i) + mItemWidth,
          bottom);
      canvas.drawRoundRect(mRectF, mRadius, mRadius, mUnselectedPaint);
    }

    //draw selected item
    mRectF.set(getPaddingLeft(), top, getItemLeft(mSelectedPos) + mItemWidth, bottom);
    canvas.drawRoundRect(mRectF, mRadius, mRadius, mSelectedPaint);
  }

  private float getItemLeft(int pos) {
    return getPaddingLeft() + mItemWidth * pos + mMargin * pos;
  }

  public void setSelectedItem(int pos) {
    this.mSelectedPos = pos;
    invalidate();
  }

  public void setIndicatorSize(int size) {
    this.mItemSize = size;
    invalidate();
  }

  private float dpToPx(float dp) {
    return dp * getScreenDensity();
  }

  public DisplayMetrics getDisplayMetrics() {
    return getResources().getDisplayMetrics();
  }

  public float getScreenDensity() {
    return getDisplayMetrics().density;
  }

  public void bindViewPager(ViewPager viewPager) {
    if (viewPager.getAdapter() == null) {
      Log.e(TAG, "ViewPager's adapter is null!!!");
      return;
    }
    setIndicatorSize(viewPager.getAdapter().getCount());
    viewPager.addOnPageChangeListener(new OnPageChangeListener() {
      @Override
      public void onPageScrolled(int i, float v, int i1) {

      }

      @Override
      public void onPageSelected(int i) {
        setSelectedItem(i);
      }

      @Override
      public void onPageScrollStateChanged(int i) {

      }
    });
  }
}
