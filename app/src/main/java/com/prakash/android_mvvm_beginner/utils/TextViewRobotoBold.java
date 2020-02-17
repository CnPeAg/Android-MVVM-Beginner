package com.prakash.android_mvvm_beginner.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewRobotoBold extends AppCompatTextView {

    public TextViewRobotoBold(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TextViewRobotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TextViewRobotoBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("roboto.bold.ttf", context);
        setTypeface(customFont);
    }
}