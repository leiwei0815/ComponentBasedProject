package com.music.common.impl;

import android.annotation.SuppressLint;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.databinding.BindingAdapter;
import com.google.android.material.button.MaterialButton;
import com.music.common.glide.GlideImageApiKt;
import com.zzhoujay.richtext.RichText;

public class DefaultDBAdapter {

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView image, Object imageUrl) {
        GlideImageApiKt.loadImage(image, imageUrl);
    }

    @BindingAdapter({"imageCircle"})
    public static void setImageCircle(ImageView image, Object imageUrl) {
        GlideImageApiKt.loadImage(image, imageUrl, -1, -1,-1);
    }

    @BindingAdapter({"blurImage"})
    public static void setBlurImage(ImageView image, Object imageUrl) {
        GlideImageApiKt.loadBlurImage(image, imageUrl);
    }

    @SuppressLint("ResourceType")
    @BindingAdapter({"richText"})
    public static void setRichText(TextView textView, String text) {
        //新的反馈默认占位图不能以白色为背景,体验不好
        //RichText.fromHtml(text).placeHolder(R.drawable.white_circle_bg).into(textView);
        RichText.fromHtml(text).into(textView);
    }

    @BindingAdapter(value = {"viewRadius", "viewRadiusLT", "viewRadiusLB", "viewRadiusRT", "viewRadiusRB"}, requireAll = false)
    public static void setViewRadius(View view, int viewRadius, int viewRadiusLT, int viewRadiusLB, int viewRadiusRT, int viewRadiusRB) {
        float density = view.getResources().getDisplayMetrics().density;
        if (viewRadius == 0) {
            ViewGroup viewGroup = ((ViewGroup) view.getParent());
            RoundLayout layout = new RoundLayout(view.getContext());
            float[] radii = new float[8];
            radii[0] = viewRadiusLT * density;
            radii[1] = viewRadiusLT * density;
            radii[2] = viewRadiusRT * density;
            radii[3] = viewRadiusRT * density;
            radii[4] = viewRadiusRB * density;
            radii[5] = viewRadiusRB * density;
            radii[6] = viewRadiusLB * density;
            radii[7] = viewRadiusLB * density;
            layout.setCornerRadius(radii);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) == view) {
                    viewGroup.removeView(view);
                    layout.addView(view);
                    viewGroup.addView(layout, i, view.getLayoutParams());
                    view.setLayoutParams(new FrameLayout.LayoutParams(view.getLayoutParams().width, view.getLayoutParams().height));
                    return;
                }
            }
        } else {
            view.setClipToOutline(true);
            view.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), density * viewRadius);
                }
            });
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageSrc(ImageView image, int imageRes) {
        image.setImageResource(imageRes);
    }

    @BindingAdapter({"android:background"})
    public static void setImageBackground(ImageView image, int imageRes) {
        image.setBackgroundResource(imageRes);
    }

    @BindingAdapter({"android:textColor"})
    public static void setTextColor(TextView text, @ColorRes int colorRes) {
        text.setTextColor(text.getResources().getColor(colorRes));
    }

    @BindingAdapter({"isBold"})
    public static void setBold(TextView text, boolean face) {
        text.setTypeface(Typeface.defaultFromStyle(face ? Typeface.BOLD : Typeface.NORMAL));
    }


    @BindingAdapter({"backgroundTint"})
    public static void setBackgroundTint(MaterialButton button, int colorRes) {
        button.setBackgroundColor(colorRes);
    }
}
