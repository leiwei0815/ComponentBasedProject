package com.music.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.music.common.R
import com.music.common.constant.RoutePath
import com.music.common.databinding.ActivitySimpleBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException

/**
 * 标准的Fragment容器Activ
 * 使用ARouter对组件化中Fragment的支持
 */
@Route(path = RoutePath.SIMPLE_PAGE_ACTIVITY)
@AndroidEntryPoint
class SimplePageActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySimpleBinding.inflate(layoutInflater)
    }

    @Autowired
    lateinit var title: String

    @Autowired
    lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ARouter.getInstance().inject(this)
        val clz = ARouter.getInstance().build(path).navigation().javaClass
        if (Fragment::class.java.isAssignableFrom(clz)) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.layou_content, clz as java.lang.Class<out Fragment>, null)
                .commit()
        } else {
            throw RuntimeException("can not find Fragment class!")
        }

        initView()
    }

    private fun initView() {
        binding.title.text = title
        binding.back.setOnClickListener {
            finish()
        }
    }
}