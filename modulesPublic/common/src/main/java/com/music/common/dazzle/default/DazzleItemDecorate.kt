package com.music.common.dazzle.default

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.music.common.dazzle.Item
import kotlin.math.roundToInt

class DazzleItemDecorate<T : Item<*>>(val views: MutableList<T>, var datas: List<Any> = emptyList()) :
    RecyclerView.ItemDecoration() {
    private val datasIndex by lazy { mutableListOf(0) }
    private var keys: List<String> = views.map { it.type }.toList()

    //数据变化后重新计算index
    fun computDatasIndex() {
        if (datas.isEmpty()) return
        var oldType = datas.first()::class.java.toString()
        datas.forEachIndexed { index, any ->
            val type = any::class.java.toString()
            if (oldType != type) {
                datasIndex.add(index)
                oldType = type
            }
        }
    }

    //匹配该位置的第一个对象下标，和该组数据截止下标
    private fun matchIndex(position: Int): Pair<Int, Int> {
        val index = datasIndex.indexOfFirst { dataIndex -> dataIndex > position }
        if (index == -1) return Pair(datasIndex.last(), datas.size)
        return Pair(datasIndex[index - 1], datasIndex[index])
    }

    //计算当前item如何摆放
    private fun computItemState(position: Int, isReverse: Boolean, orientation: Int): Rect {
        val item = currentItem(position) ?: return Rect(0, 0, 0, 0)
        if (item.mainAxisSpacing == 0 && item.crossAxisSpacing == 0 && item.padding.left == 0 && item.padding.right == 0 && item.padding.top == 0 && item.padding.bottom == 0) {
            return Rect(0, 0, 0, 0)
        }
        //spec计算
        val mainSpec = (item.mainAxisSpacing / 2f).roundToInt()
        val crossSpec = (item.crossAxisSpacing / 2f).roundToInt()
        val padding = item.padding
        //获取下标在数据列表中的第一个item
        val keyPoint = matchIndex(position)
        val realIndex = position - keyPoint.first
        //0-x 取值为0则确定为第一行
        val isTopLine = (realIndex / item.crossCount) == 0
        val isStartLine = (realIndex % item.crossCount) == 0
        val isEndLine = (realIndex % item.crossCount) == item.crossCount - 1
        val count = keyPoint.second - keyPoint.first
        val isBottomLine = (count - realIndex) <= item.crossCount
        val isCenter = !isTopLine && !isStartLine && !isEndLine && !isBottomLine

        val rect = Rect(-1, -1, -1, -1)
        if (isCenter) {
            if (orientation == RecyclerView.VERTICAL) {
                rect.set(crossSpec, mainSpec, crossSpec, mainSpec)
            } else {
                rect.set(mainSpec, crossSpec, mainSpec, crossSpec)
            }
            return rect
        }
        when (orientation) {
            RecyclerView.HORIZONTAL -> {
                if (isStartLine) rect.top = padding.top
                if (isTopLine) rect.left = padding.left
                if (isBottomLine) rect.right = padding.right
                if (isEndLine) rect.bottom = padding.bottom
            }
            RecyclerView.VERTICAL -> {
                if (isTopLine) rect.top = padding.top
                if (isStartLine) rect.left = padding.left
                if (isEndLine) rect.right = padding.right
                if (isBottomLine) rect.bottom = padding.bottom
            }
        }
        rect.apply {
            if (left == -1) left = if (orientation == RecyclerView.VERTICAL) crossSpec else mainSpec
            if (top == -1) top = if (orientation == RecyclerView.VERTICAL) mainSpec else crossSpec
            if (right == -1) right = if (orientation == RecyclerView.VERTICAL) crossSpec else mainSpec
            if (bottom == -1) bottom = if (orientation == RecyclerView.VERTICAL) mainSpec else crossSpec
        }
        if (isReverse) {
            when (orientation) {
                RecyclerView.HORIZONTAL -> rect.set(rect.right, rect.top, rect.left, rect.bottom)
                RecyclerView.VERTICAL -> rect.set(rect.left, rect.bottom, rect.right, rect.top)
            }
        }
        return rect
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        when (val layoutManager = parent.layoutManager) {
            is LinearLayoutManager -> {
                outRect.set(computItemState(position, layoutManager.reverseLayout, layoutManager.orientation))
            }
            is GridLayoutManager -> {
                outRect.set(computItemState(position, layoutManager.reverseLayout, layoutManager.orientation))
            }
            else -> {
                outRect.set(Rect(0, 0, 0, 0))
            }
        }
    }

    //获取当前对应的item模板
    private fun currentItem(position: Int): T? {
        val t = currentPosition(position)
        return views.getOrNull(keys.indexOf(t::class.java.toString()))
    }

    //获取当前对应的对象
    private fun currentPosition(position: Int): Any {
        return datas[position]
    }
}