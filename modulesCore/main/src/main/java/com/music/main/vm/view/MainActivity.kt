package com.music.main.vm.view

import android.Manifest
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.music.common.TzDBActivity
import com.music.common.constant.RoutePath
import com.music.common.impl.viewModels
import com.music.main.R
import com.music.main.vm.model.MainViewModel
import com.music.main.databinding.ActivityMainBinding
import com.music.main.ui.ScheduleFragment
import com.music.main.ui.VipFragment
import com.music.main.vm.UserInfoFragment
import com.music.main.vm.main.view.HomeFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@Route(path = RoutePath.MAIN_ACTIVITY)
class MainActivity : TzDBActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun bindLayoutId(): Int = R.layout.activity_main

    private val homeFragment by lazy { HomeFragment() }
    private val rankingFragment by lazy { VipFragment() }
    private val scheduleFragment by lazy { ScheduleFragment() }
    private val mineFragment by lazy { UserInfoFragment() }

    private val TIME_EXIT = 2000
    private var mBackPressed: Long = 0

    @Inject
    lateinit var rxPermissions: RxPermissions

    override fun initData() {
        val job = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe {
            if (!it)
                ToastUtils.showLong("请开启读写数据权限!")
        }

        mViewModel.initData()


    }

    override val topBarView: View? = null


    override fun initView(view: View) {
        mDataBinding?.apply {
            model = mViewModel
        }
        supportFragmentManager.beginTransaction().add(R.id.main_content, getFragment(0) ?: return)
            .commit()
//        setTabIcon(0)
    }

    fun onChangeTo1(view: View?) {
        switchFragment(0)
    }

    fun onChangeTo2(view: View) {
        switchFragment(1)
    }

    fun onChangeTo3(view: View) {
        switchFragment(2)
    }

    fun onChangeTo4(view: View) {
        switchFragment(3)
    }

    private fun switchFragment(index: Int) {
        val fragment = getFragment(index) ?: return
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction().hide(getFragment() ?: return).show(fragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction().hide(getFragment() ?: return)
                .add(R.id.main_content, fragment)
                .commit()
        }
        mViewModel.changeTabIndex(index)

//        setTabIcon(index)
        //处理点击动画
        setIndexAnim(index)
        //设置默认图标
        setIndexIcon(index)
    }

    /**
     * 设置点击动画
     */
    private fun setIndexAnim(index: Int) {
        when (index) {
            0 -> {
                mDataBinding?.mainHomeIcon?.setAnimation("首页.json");
                mDataBinding?.mainHomeIcon?.playAnimation()
            }
            1 -> {
                mDataBinding?.mainScheduleIcon?.setAnimation("学习.json");
                mDataBinding?.mainScheduleIcon?.playAnimation()
            }
            2 -> {
                mDataBinding?.mainMessageIcon?.setAnimation("消息.json");
                mDataBinding?.mainMessageIcon?.playAnimation()
            }
            3 -> {
                mDataBinding?.mainMineIcon?.setAnimation("我的.json");
                mDataBinding?.mainMineIcon?.playAnimation()
            }
        }
    }

    /**
     * 设置默认图片
     */
    private fun setIndexIcon(index: Int) {
        if (index != 0)
            mDataBinding?.mainHomeIcon?.setImageResource(com.music.common.R.mipmap.main_home_nor)
        if (index != 1)
            mDataBinding?.mainScheduleIcon?.setImageResource(com.music.common.R.mipmap.main_schedule_nor)
        if (index != 2)
            mDataBinding?.mainMessageIcon?.setImageResource(com.music.common.R.mipmap.main_message_nor)
        if (index != 3)
            mDataBinding?.mainMineIcon?.setImageResource(com.music.common.R.mipmap.main_mine_nor)
    }

//    private fun setTabIcon(index: Int) {
//        mDataBinding?.apply {
//            mainHomeIcon.setImageResource(if (index == 0) com.music.common.R.mipmap.icon_home_s else com.music.common.R.mipmap.icon_home)
//            mainScheduleIcon.setImageResource(if (index == 1) com.music.common.R.mipmap.icon_vip_s else com.music.common.R.mipmap.icon_vip)
//            mainMessageIcon.setImageResource(if (index == 2) com.music.common.R.mipmap.icon_timetable_s else com.music.common.R.mipmap.icon_timetable)
//            mainMineIcon.setImageResource(if (index == 3) com.music.common.R.mipmap.icon_mine_s else com.music.common.R.mipmap.icon_mine)
//        }
//    }

    private fun getFragment(index: Int? = mViewModel.tabIndex.value): Fragment? {
        return when (index) {
            0 -> homeFragment
            1 -> rankingFragment
            2 -> scheduleFragment
            3 -> mineFragment
            else -> null
        }
    }

    override fun onBackPressed() {
        if (mBackPressed + TIME_EXIT > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            toast("再点击一次返回退出程序")
            mBackPressed = System.currentTimeMillis()
        }
    }
}