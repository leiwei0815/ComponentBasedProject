package com.music.main.mvp

import com.blankj.utilcode.util.LogUtils
import com.music.common.base.BaseFragment
import com.music.common.base.SimplePage
import com.music.common.utils.PageHelper
import com.music.main.databinding.FragmentBlankBinding
import com.music.main.vm.MusicViewModelVm
import com.music.player.CourseBrandItemModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
open class BlankFragment : BaseFragment<FragmentBlankBinding>(),MusicContract.View{

    @Inject
    lateinit var presenter:MusicPresenter

    @Inject
    lateinit var mViewModel: MusicViewModelVm


    override fun initViews() {
        super.initViews()
    }

    override fun loadData() {
        super.loadData()

        presenter.loadMusics(0)

        mViewModel.loadMusics()
        mViewModel.musicData.observe(this){
            LogUtils.d("onCreate data: $it")
        }
        binding.ivClick.setOnClickListener {
            PageHelper.showSimplePage(SimplePage.USER_INFO)
        }
    }

    override fun onLoadMusicSuccess(list: List<CourseBrandItemModel>) {
        LogUtils.d("onCreate data: $list")
    }

    override fun onLoadMusicFailure(msg: String) {

    }


}