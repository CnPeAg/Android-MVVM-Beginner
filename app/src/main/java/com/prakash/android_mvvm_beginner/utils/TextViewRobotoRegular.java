package com.prakash.android_mvvm_beginner.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewRobotoRegular extends AppCompatTextView {

    public TextViewRobotoRegular(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TextViewRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TextViewRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("roboto.regular.ttf", context);
        setTypeface(customFont);
    }
}