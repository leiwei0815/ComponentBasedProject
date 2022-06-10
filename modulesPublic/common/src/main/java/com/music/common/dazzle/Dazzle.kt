package com.tz.dazzle

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import androidx.viewpager2.widget.ViewPager2
import com.music.common.dazzle.BpAdapter
import com.music.common.dazzle.Item
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.music.common.dazzle.default.DazzleItemDecorate
import com.music.libase.base.BaseApp
import com.music.libase.base.castCtx
import com.music.common.dazzle.default.NoMatchItem
import com.music.common.dazzle.default.NoMathData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun <T : Item<*>> RecyclerView.dazzle(
    views: MutableList<T>,
    onRefresh: (suspend () -> Boolean)? = null,
    refreshHeader: RefreshHeader? = null
): DazzleAdapter<T> {
    if (onRefresh != null) {
        createRefrensh(onRefresh, refreshHeader)
    }
    adapter = DazzleAdapter(this, views).apply {
        setHasStableIds(true)
        val line = maxCrossCount()
        if (line == 1) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        } else {
            layoutManager = GridLayoutManager(
                context,
                maxCrossCount(),
                GridLayoutManager.VERTICAL,
                false
            ).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return spanCount / views[getItemViewType(position)].crossCount
                    }
                }
            }
        }
    }
    return adapter as DazzleAdapter<T>
}

fun <T : Item<*>> ViewPager2.dazzle(
    views: MutableList<T>,
): ViewPager2DazzleAdapter<T> {
    adapter = ViewPager2DazzleAdapter(this, views).apply {
        setHasStableIds(true)
    }
    return adapter as ViewPager2DazzleAdapter<T>
}

fun RecyclerView.hasRefrensh(): Boolean {
    return parent is SmartRefreshLayout
}

fun RecyclerView.createRefrensh(onRefresh: (suspend () -> Boolean)?, refreshHeader: RefreshHeader?) {
    val viewGroup = (parent as ViewGroup)
    val index = viewGroup.indexOfChild(this)
    viewGroup.removeViewAt(index)
    viewGroup.addView(SmartRefreshLayout(context).apply {
        layoutParams = ViewGroup.LayoutParams(this@createRefrensh.layoutParams)

        if (refreshHeader == null) {
            ClassicsHeader(context).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
        setRefreshHeader(refreshHeader ?: ClassicsHeader(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        })
        addView(this@createRefrensh, this@createRefrensh.layoutParams)
        setOnRefreshListener {
            GlobalScope.launch(Dispatchers.Main) {
                val isSuccess = onRefresh?.invoke() ?: false
                finishRefresh(isSuccess)
            }
        }
    }, index)
}

fun RecyclerView.replaceRefrensh(onRefresh: (suspend () -> Boolean)?) {
    if (parent is SmartRefreshLayout) {
        (parent as SmartRefreshLayout).apply {
            setOnRefreshListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val isSuccess = onRefresh?.invoke() ?: false
                    finishRefresh(isSuccess)
                }
            }
        }
    }
}

class DazzleAdapter<T : Item<*>>(private val rcy: RecyclerView, private val views: MutableList<T>) :
    BpAdapter<Any>() {
    private val itemDecorate by lazy { DazzleItemDecorate(views, list) }
    override var list: MutableList<Any> = mutableListOf()
        set(value) {
            field = value
            itemDecorate.datas = value
            itemDecorate.computDatasIndex()
        }

    init {
        if (BaseApp.app?.isDebug() == true) views.add(NoMatchItem() as T)
        views.forEach {
            it.perfromInit(rcy.context.castCtx()?.getBeforeContext() ?: rcy.context)
        }
        rcy.addItemDecoration(itemDecorate.apply { computDatasIndex() })
    }


    private var keys: List<String> = views.map { it.type }.toList()

    override fun getView(context: Context, type: Int, parent: ViewGroup): View {
        if (type == -1) return View(context)
        return views[type].itemCreate(context, parent).apply {}
    }

    override fun onNotify(v: View, index: Int, data: Any) {
        val type = getItemViewType(index)
        if (type == -1) return
        views[type].performItemBuild(v, index, data)
    }


    override fun getItemViewType(position: Int): Int {
        val t = currentPosition(position)
        val type = keys.indexOf(t::class.java.toString())
        if (type == -1) return keys.indexOf(NoMathData::class.java.toString())
        return type
    }

    fun maxCrossCount(): Int {
        var lcm = 1
        views.forEach {
            lcm = getLcm(lcm, it.crossCount)
        }
        return lcm
    }

    private fun getGcd(m: Int, n: Int): Int {
        var m = m
        var n = n
        while (n > 0) {
            val temp = m % n
            m = n
            n = temp
        }
        return m
    }

    //求解数m和n和最小公倍数
    private fun getLcm(m: Int, n: Int): Int {
        val gcd = getGcd(m, n)
        return m * n / gcd
    }

    fun reverse(): DazzleAdapter<*> {
        val layoutManager = rcy.layoutManager
        if (layoutManager is LinearLayoutManager) {
            layoutManager.reverseLayout = true
//            layoutManager.stackFromEnd=true
        }
        return this
    }

    fun vertical(): DazzleAdapter<*> {
        val layoutManager = rcy.layoutManager
        if (layoutManager is LinearLayoutManager) {
            layoutManager.orientation = LinearLayoutManager.VERTICAL
        }
        return this
    }

    fun horizontal(): DazzleAdapter<*> {
        val layoutManager = rcy.layoutManager
        if (layoutManager is LinearLayoutManager) {
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        }
        return this
    }

    fun invalidOffset(): DazzleAdapter<*> {
        val layoutManager = rcy.layoutManager
        if (layoutManager is LinearLayoutManager) {
            layoutManager.orientation = LinearLayoutManager.INVALID_OFFSET
        }
        return this
    }

    fun toPager(): DazzleAdapter<*> {
        PagerSnapHelper().attachToRecyclerView(rcy)
        return this
    }

    fun linerSnap(): DazzleAdapter<*> {
        LinearSnapHelper().attachToRecyclerView(rcy)
        return this
    }

}

class ViewPager2DazzleAdapter<T : Item<*>>(private val vp: ViewPager2, private val views: MutableList<T>) :
    BpAdapter<Any>() {
    init {

        if (BaseApp.app?.isDebug() == true) views.add(NoMatchItem() as T)
        views.forEach {
            it.perfromInit(vp.context.castCtx()?.getBeforeContext() ?: vp.context)
        }
    }

    private var keys: List<String> = views.map { it.type }.toList()

    override fun getView(context: Context, type: Int, parent: ViewGroup): View {
        if (type == -1) return View(context)
        return views[type].itemCreate(context, parent).apply {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    override fun onNotify(v: View, index: Int, data: Any) {
        val type = getItemViewType(index)
        if (type == -1) return
        views[type].performItemBuild(v, index, data)
    }

    override fun getItemViewType(position: Int): Int {
        val t = currentPosition(position)
        val type = keys.indexOf(t::class.java.toString())
        if (type == -1) return keys.indexOf(NoMathData::class.java.toString())
        return type
    }


    fun vertical(): ViewPager2DazzleAdapter<*> {
        vp.orientation = ViewPager2.ORIENTATION_VERTICAL
        return this
    }

    fun horizontal(): ViewPager2DazzleAdapter<*> {
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        return this
    }


}