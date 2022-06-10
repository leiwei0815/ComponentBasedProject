package com.music.common.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.music.common.R
import com.music.common.dazzle.Item

class MoreDataItem : Item<MoreDataModel>() {

    override fun itemCreate(context: Context, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.item_more_data, parent, false)
    }

    override fun itemBuild(item: View?, index: Int, data: MoreDataModel) {
        if (ctx is ComponentActivity) {
            (ctx as ComponentActivity).lifecycleScope.launchWhenResumed {
                data.call()
            }
        }
    }

}

class MoreDataModel(
    val call: suspend () -> Unit,
)

