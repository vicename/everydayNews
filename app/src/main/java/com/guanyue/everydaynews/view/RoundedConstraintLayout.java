package com.guanyue.everydaynews.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.guanyue.everydaynews.R;


/**
 * Created by LiDaChang on 17/6/20.
 * __--__---__-------------__----__
 */

public class RoundedConstraintLayout extends ConstraintLayout {

    private float mRadius;
    private float mTopLeftRadius;
    private float mTopRightRadius;
    private float mBottomLeftRadius;
    private float mBottomRightRadius;

    private Paint imagePaint;

    RoundedController mRoundedController;

    public RoundedConstraintLayout(Context context) {
        this(context, null);
    }

    public RoundedConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray b = getContext().obtainStyledAttributes(attrs, R.styleable.RoundCorner, defStyle, 0);
            mRadius = b.getDimension(R.styleable.RoundCorner_cornerRadius, 0);
            mTopLeftRadius = b.getDimension(R.styleable.RoundCorner_topLeftRadius, mRadius);
            mTopRightRadius = b.getDimension(R.styleable.RoundCorner_topRightRadius, mRadius);
            mBottomRightRadius = b.getDimension(R.styleable.RoundCorner_bottomRightRadius, mRadius);
            mBottomLeftRadius = b.getDimension(R.styleable.RoundCorner_bottomLeftRadius, mRadius);
            b.recycle();
        }
        mRoundedController = new RoundedController(mRadius);
        mRoundedController.setRadius(mTopLeftRadius, mTopRightRadius, mBottomRightRadius, mBottomLeftRadius);
        imagePaint = new Paint();
        imagePaint.setXfermode(null);
    }

    //实现4
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);

        super.dispatchDraw(canvas);
        if (mRoundedController != null) {
            mRoundedController.drawCorners(canvas, getWidth(), getHeight());
        }
        canvas.restore();
    }

    public void setConnerRadius(float radius) {
        mRadius = radius;
        mTopLeftRadius = radius;
        mTopRightRadius = radius;
        mBottomRightRadius = radius;
        mBottomLeftRadius = radius;
        if (mRoundedController != null) {
            mRoundedController.setRadius(mRadius);
        } else {
            mRoundedController = new RoundedController(radius);
        }
        invalidate();
    }

    public void setConnerRadius(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        mTopLeftRadius = topLeftRadius;
        mTopRightRadius = topRightRadius;
        mBottomRightRadius = bottomRightRadius;
        mBottomLeftRadius = bottomLeftRadius;
        if (mRoundedController != null) {
            mRoundedController.setRadius(topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
        } else {
            mRoundedController = new RoundedController(topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
        }
        invalidate();
    }

}
