//package com.music.main
//
//import android.Manifest
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import com.alibaba.android.arouter.facade.annotation.Route
//import com.blankj.utilcode.util.ToastUtils
//import com.music.common.constant.RoutePath
//import com.music.common.net.ApiService
//import com.music.main.databinding.ActivityMainBinding
//import com.music.main.mvp.BlankFragment
//import com.music.main.ui.ScheduleFragment
//import com.music.main.ui.VipFragment
//import com.music.main.vm.MainViewModelVm
//import com.music.main.vm.UserInfoFragment
//import com.tbruyelle.rxpermissions2.RxPermissions
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject
//
//
//@Route(path = RoutePath.MAIN_ACTIVITY)
//@AndroidEntryPoint
//class MainActivity : AppCompatActivity() {
//
//    val binding: ActivityMainBinding by lazy {
//        ActivityMainBinding.inflate(layoutInflater)
//    }
//
//    private val homeFragment by lazy { BlankFragment() }
//    private val rankingFragment by lazy { VipFragment() }
//    private val scheduleFragment by lazy { ScheduleFragment() }
//    private val mineFragment by lazy { UserInfoFragment() }
//
//    @Inject
//    lateinit var mainViewModel: MainViewModelVm
//
//    @Inject
//    lateinit var apiService: ApiService
//
//    @Inject
//    lateinit var rxPermissions: RxPermissions
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe {
//            if (!it)
//                ToastUtils.showLong("请开启读写数据权限!")
//        }
//
//        binding.lifecycleOwner = this
//        binding.model = mainViewModel
////        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
////        binding.model = UserInfo()
////
////        //   supportFragmentManager.beginTransaction()
////        //                .setReorderingAllowed(true)
////        //                .add(R.id.layou_content, clz as java.lang.Class<out Fragment>, null)
////        //                .commit()
////        supportFragmentManager.beginTransaction()
////            .setReorderingAllowed(true)
////            .add(R.id.fg_blank,BlankFragment::class.java,null)
////            .commit()
////       val view = findViewById<ImageView>(R.id.iv_main)
//
//
////        val fade = DrawableTransitionOptions.withCrossFade()
////        GlideApp.with(this)
////            .load("https://res.shiguangkey.com//20211103/16/1635926897157che24X.png")
////            .applyAvatar(100.dp)
////            .transition(fade)
////            .into(findViewById(R.id.iv_main))
//
////        RequestModel.request(apiService.test("1", "10"), this, object : ICallback<SearchData> {
////            override fun onSuccess(data: SearchData) {
////                LogUtils.d("onCreate data: $data")
////            }
////
////            override fun onFailure(e: ApiException) {
////                LogUtils.d("onCreate e: $e")
////            }
////        })
//
//        supportFragmentManager.beginTransaction().add(R.id.main_content, getFragment(0) ?: return)
//            .commit()
//        setTabIcon(0)
//    }
//
//    fun onChangeTo1(view: View?) {
//        switchFragment(0)
//    }
//
//    fun onChangeTo2(view: View) {
//        switchFragment(1)
//    }
//
//    fun onChangeTo3(view: View) {
//        switchFragment(2)
//    }
//
//    fun onChangeTo4(view: View) {
//        switchFragment(3)
//    }
//
//    private fun switchFragment(index: Int) {
//        val fragment = getFragment(index) ?: return
//        if (fragment.isAdded) {
//            supportFragmentManager.beginTransaction().hide(getFragment() ?: return).show(fragment)
//                .commit()
//        } else {
//            supportFragmentManager.beginTransaction().hide(getFragment() ?: return)
//                .add(R.id.main_content, fragment)
//                .commit()
//        }
//        mainViewModel.changeTabIndex(index)
//
//        setTabIcon(index)
//    }
//
//    private fun setTabIcon(index: Int) {
//        binding.apply {
//            mainHomeIcon.setImageResource(if (index == 0) com.music.common.R.mipmap.icon_home_s else com.music.common.R.mipmap.icon_home)
//            mainScheduleIcon.setImageResource(if (index == 1) com.music.common.R.mipmap.icon_vip_s else com.music.common.R.mipmap.icon_vip)
//            mainMessageIcon.setImageResource(if (index == 2) com.music.common.R.mipmap.icon_timetable_s else com.music.common.R.mipmap.icon_timetable)
//            mainMineIcon.setImageResource(if (index == 3) com.music.common.R.mipmap.icon_mine_s else com.music.common.R.mipmap.icon_mine)
//        }
//    }
//
//    private fun getFragment(index: Int? = mainViewModel.tabIndex.value): Fragment? {
//        return when (index) {
//            0 -> homeFragment
//            1 -> rankingFragment
//            2 -> scheduleFragment
//            3 -> mineFragment
//            else -> null
//        }
//    }
//}