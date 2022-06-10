package com.music.common.dazzle

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by dengyulin on 2018/4/12.
 */
abstract class BpAdapter<T> : RecyclerView.Adapter<BpAdapter.BpItem>() {
    open var list: MutableList<T> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BpItem =
        BpItem(getView(parent.context, viewType, parent))

    override fun onBindViewHolder(holder: BpItem, position: Int) {
        onNotify(holder.itemView, position, currentPosition(position))
    }

    override fun getItemId(position: Int): Long {
        try {
            return currentPosition(position).hashCode().toLong()
        } catch (e: Exception) {
        }
        return 0
    }

    open fun onNotify(v: View, index: Int, data: T) {

    }

    override fun getItemCount(): Int = list.size

    fun currentPosition(position: Int): T = list[position]

    abstract fun getView(context: Context, type: Int, parent: ViewGroup): View

    class BpItem(itemView: View) : RecyclerView.ViewHolder(itemView)


    @SuppressLint("NotifyDataSetChanged")
    fun notifyDataSetChanged(list: Collection<T>): BpAdapter<T> {
        this.list = list.toMutableList()
        notifyDataSetChanged()
        return this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addListAndNotifyData(list: Collection<T>): BpAdapter<T> {
        this.list.addAll(list)
        notifyDataSetChanged()
        return this
    }

    fun addAndNotifyData(t: T, index: Int = 0): BpAdapter<T> {
        list.add(index, t)
        notifyItemInserted(index)
        return this
    }

    fun notifyRangeData(t: List<T>) {
        val size = list.size
        list = t.toMutableList()
        if (size > list.size) {
            notifyItemRangeChanged(list.size, size)
        } else {
            notifyItemRangeChanged(size, list.size)
        }

    }

    fun notifyRangeData2(t: List<T>) {
//        if (list.isEmpty()) {
//            notifyDataSetChanged(t)
//            return
//        }
//        t.forEachIndexed { index, it ->
//            val last = list.getOrElse(index) { _ ->
//                addAndNotifyData(it, index)
//                return@forEachIndexed
//            }
//            if (it == null) return@forEachIndexed
//            if (!last!!::class.java.isAssignableFrom(it!!::class.java)) {
//                changeAndNotifyData(it, index)
//            }
//        }
        notifyDataSetChanged(t)
    }

    fun addRangeData(t: List<T>) {
        val size = list.size
        list = t.toMutableList()
        if (size > list.size) {
            notifyItemRangeRemoved(list.size, size - list.size)
        } else {
            notifyItemRangeInserted(size, list.size - size)
        }
    }

    fun addRangeData2(t: List<T>) {
        val size = list.size
        list.addAll(t)
        notifyItemRangeInserted(size, list.size - size)

//        notifyDataSetChanged()
    }

    fun rmAndNotifyData(index: Int = 0): BpAdapter<T> {
        list.removeAt(index)
        notifyItemRemoved(index)
        return this
    }

    fun moveAndNotifyData(from: Int, to: Int): BpAdapter<T> {
        Collections.swap(list, from, to)
        notifyItemMoved(from, to)
        return this
    }

    fun changeAndNotifyData(t: T, index: Int = 0): BpAdapter<T> {
        list[index] = t
        notifyItemChanged(index)
        return this
    }

}

//fun <T> View.onNotifyData(call: suspend CoroutineScope.(index: Int, data: T) -> Unit) {
//    setTag(notifyTag, NTFModel(3, call, if (getTag(notifyTag) is NTFModel) (getTag(notifyTag) as NTFModel).parameter else NULL_VALUE))
//}

internal fun <T> RecyclerView.toBpAdapter() = adapter as BpAdapter<*>

fun RecyclerView.vertical() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

fun RecyclerView.horizontal() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.invalidOffset() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.INVALID_OFFSET, false)
}

fun RecyclerView.vertical(spanCount: Int) {
    layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
}

fun RecyclerView.horizontal(spanCount: Int) {
    layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.invalidOffset(spanCount: Int) {
    layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.INVALID_OFFSET, false)
}

fun RecyclerView.isPager() {
    PagerSnapHelper().attachToRecyclerView(this)
}


fun RecyclerView.verticalVariableMananger(spanCount: Int = 1, call: (position: Int) -> Int) {
    layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return call.invoke(position)
            }
        }
    }
}

fun RecyclerView.horizontalVariableMananger(spanCount: Int = 1, call: (position: Int) -> Int) {
    layoutManager =
        GridLayoutManager(context, spanCount, GridLayoutManager.HORIZONTAL, false).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return call.invoke(position)
                }
            }
        }
}

