package com.inno.bourdrbij.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Ruben on 24-2-2016.
 */
public class MetamorphousTextView extends TextView {
    /*
    * Caches typefaces based on their file path and name, so that they don't have to be created every time when they are referenced.
    */
    private static Typeface mTypeface;

    public MetamorphousTextView(final Context context) {
        this(context, null);
    }

    public MetamorphousTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetamorphousTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Metamorphous-Regular.otf");
        }
        setTypeface(mTypeface);
    }

}
