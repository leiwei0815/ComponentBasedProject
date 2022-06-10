package com.music.common.impl;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by linjiang on 2018/9/6.
 */

public class ProxyDrawable extends StateListDrawable {

    private Drawable originDrawable;

    @Override
    public void addState(int[] stateSet, Drawable drawable) {
        if (stateSet != null && stateSet.length == 1 && stateSet[0] == 0) {
            originDrawable = drawable;
        }
        super.addState(stateSet, drawable);
    }

    public Drawable getOriginDrawable() {
        return originDrawable;
    }
}
