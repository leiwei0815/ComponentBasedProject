package com.music.common.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.music.common.R
import com.music.common.dazzle.Item

class NoMoreItem : Item<NoMoreModel>() {

    override fun itemCreate(context: Context, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.item_no_more, parent, false)
    }

    override fun itemBuild(item: View?, index: Int, data: NoMoreModel) {

    }

}

class NoMoreModel(

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}