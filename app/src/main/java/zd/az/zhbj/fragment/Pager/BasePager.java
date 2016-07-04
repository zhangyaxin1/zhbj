package zd.az.zhbj.fragment.Pager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import zd.az.zhbj.R;
import zd.az.zhbj.constant.GloableConstant;

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

   public  BasePager(final Activity activity){
       super();
       this.mActivity=activity;
       Log.e("klkk","+++++++++"+mActivity);
       mRootView = View.inflate(mActivity, R.layout.pager_base,null);
       mTvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
       mIVMenu = (ImageView) mRootView.findViewById(R.id.im_menu);
       mIVRight = (ImageView) mRootView.findViewById(R.id.im_menu_right);
       mContentView = (FrameLayout) mRootView.findViewById(R.id.fl_center_view);
       mIVMenu.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Log.e("KLKJ","点击事件开始起作用喽+++++++++");
              //广播就是意图
               Intent intent = new Intent();
               intent.setAction(GloableConstant.ACION_SLIDING_TOOGLE);
               //发送广播,发给一级页面
               activity.sendBroadcast(intent);
           }
       });

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
