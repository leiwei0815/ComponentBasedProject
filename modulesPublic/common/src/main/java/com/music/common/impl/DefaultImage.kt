package com.music.common.impl

import android.content.Context
import android.util.AttributeSet
import com.music.common.glide.GlideImageApi
import com.music.common.R


class DefaultImage :androidx.appcompat.widget.AppCompatImageView , GlideImageApi {

    override val ctx: Context? get() = context

    constructor(context: Context) : super(context,null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        if(attrs!=null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.DefaultImage)
            val imageUrl = ta.getString(R.styleable.DefaultImage_imageUrl)
            ta.recycle()
            loadImage(imageUrl)
        }
    }

    fun setImageUrl(imageUrl:String){
        loadImage(imageUrl)
    }
}