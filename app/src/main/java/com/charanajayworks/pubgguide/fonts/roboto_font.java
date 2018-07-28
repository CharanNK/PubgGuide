package com.charanajayworks.pubgguide.fonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class roboto_font extends TextView {
    public roboto_font(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs, defStyle);
        init();
    }

    public roboto_font(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public roboto_font(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/roboto.ttf" );
        setTypeface(tf);
    }

}
