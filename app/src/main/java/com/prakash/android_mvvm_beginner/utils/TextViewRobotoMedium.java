package com.prakash.android_mvvm_beginner.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewRobotoMedium extends AppCompatTextView {

    public TextViewRobotoMedium(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TextViewRobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TextViewRobotoMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("roboto.medium.ttf", context);
        setTypeface(customFont);
    }
}

