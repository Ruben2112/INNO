package com.inno.bourdrbij.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Ruben on 27-5-2016.
 */
public class MetamorphousEditText extends EditText {
    public MetamorphousEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MetamorphousEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MetamorphousEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Metamorphous-Regular.otf");
            setTypeface(tf);
        }
    }
}
