package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.view.View;

/**
 * 四级页面基类
 * 四级页面和三级页面的区别是:四级页面没有标题栏
 * 创建基类是以最少的代码实现相同的功能
 * Created by Administrator on 2016/6/30.
 */
public  abstract class BaseMenuDetailPager {
/*
* 上下文*/
public Activity mActivity;

/*
* 四级页面视图
* */

    public View mRootView;
    /*
    *构造方法:需要传入上下文
    * */


    public BaseMenuDetailPager(Activity activity) {
        super();
        this.mActivity = activity;
        mRootView = initview();
    }


    /**
     * 初始化四级页面数据
     * @return
     */
    public abstract View initview();

    /**
     * 初始化四级页面数据
     */
    public void initData(Object params) {
        //
    }

}
