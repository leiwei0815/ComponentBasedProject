# ComponentBasedProject

**支持MVP/MVVM的组件化框架, 主要是通过组件化的思路,把项目拆分为主工程, 业务组件层, 功能组件层, 基础组件层**

![image](https://user-images.githubusercontent.com/72546851/173012428-25cdc8a8-6c28-44aa-bb1b-1bbda533685b.png)


### 技术要点:
	
* 组件化开发

* ARouter
	
* MVP,MVVM架构模式
	
* Retrofit, OkHttp, RxJava, Dagger&Hilt
	
* Glide:Gernerated API
	
* Jetpack: ViewModel, LiveData, Lifecycles, Startup
	
* ViewBinding, DataBinding
	
* 文件处理, 权限管理, 自定义控件等

![Image](https://user-images.githubusercontent.com/72546851/173012573-7faabafd-8958-41d9-84fb-fcdb0859fdc8.png)

### 多组件项目中复用问题

* 由于业务组件要在application和library之间进行切换, 如何保证组件切换到application之后与统一打包App运行时的配置一致

* 多组件中项目资源文件比如图片资源的重复添加, 导致不必要的apk包变大

* 自定义的一些资源文件重复编写, 增加了必要的工作量


