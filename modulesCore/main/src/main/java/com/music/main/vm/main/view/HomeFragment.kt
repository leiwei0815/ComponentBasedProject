package com.music.main.vm.main.view

import android.view.View
import com.music.common.TzDBFragment
import com.music.common.impl.viewModels
import com.music.common.route.RouterPath
import com.alibaba.android.arouter.facade.annotation.Route

import com.music.main.R
import com.music.main.vm.main.model.HomeViewModel
import com.music.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : TzDBFragment<HomeViewModel, FragmentHomeBinding>() {

    override val mViewModel: HomeViewModel by viewModels()

    override fun bindLayoutId(): Int = R.layout.fragment_home

    override fun initData() {
        mViewModel.initData()
    }

    override val topBarView: View? = null

    override fun initView(view: View) {
        mDataBinding?.apply {
            model = mViewModel
        }

    }
}