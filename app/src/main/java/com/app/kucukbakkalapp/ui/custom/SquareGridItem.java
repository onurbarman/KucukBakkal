package com.app.kucukbakkalapp.ui.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.cardview.widget.CardView;

public class SquareGridItem extends CardView {
    public SquareGridItem(Context context) {
        super(context);
    }

    public SquareGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareGridItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}