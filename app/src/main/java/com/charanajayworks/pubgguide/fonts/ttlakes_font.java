package com.charanajayworks.pubgguide.fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ttlakes_font extends TextView {
    public ttlakes_font(Context context) {
        super(context);
    }

    public ttlakes_font(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ttlakes_font(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/ttlakes_font.ttf" );
        setTypeface(tf);
    }
}
