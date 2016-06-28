package zd.az.zhbj.fragment.Pager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import zd.az.zhbj.R;

/**
 * Created by Administrator on 2016/6/27.
 */
public abstract class BasePager {
/**
 * 视图
 */
    public View mRootView;
    /**
     * 中间内容
     */

    public FrameLayout mContentView;
    /**
     * 上下文
     */
    public Activity mActivity;

    /**
     * 标题
     */
    public TextView mTvTitle;

    /**
     *菜单按钮
     */
    public ImageView mIVMenu;

    /**
     * 右边按键
     */
   public ImageView mIVRight;

   public  BasePager(Activity mActivity){
       super();
       this.mActivity=mActivity;
       Log.e("klkk","+++++++++"+mActivity);
       mRootView = View.inflate(mActivity, R.layout.pager_base,null);
       mTvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
       mIVMenu = (ImageView) mRootView.findViewById(R.id.im_menu);
       mIVRight = (ImageView) mRootView.findViewById(R.id.im_menu_right);
       mContentView  = (FrameLayout) mRootView.findViewById(R.id.fl_center_view);
       //初始化三级页面内容
       initView(mContentView);
   }

    public abstract void initView(FrameLayout ContentView);
    /**
     * 加载三级页面的数据
     */
    public  void initData(){

    }
}
