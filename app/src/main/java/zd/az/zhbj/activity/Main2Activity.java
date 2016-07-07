package zd.az.zhbj.activity;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import zd.az.zhbj.R;
import zd.az.zhbj.constant.GloableConstant;
import zd.az.zhbj.fragment.CenterFragment;
import zd.az.zhbj.fragment.LeftFragment;

/**
 * 使用了SlidingMenu控件
 */
public class Main2Activity extends SlidingFragmentActivity{
    private BroadcastReceiver broadcastReceiver;

    /**
     * 方法:修改protect 为public
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //显示中间的视图,将视图放到SlidingMenu中间
        setContentView(R.layout.activity_main2);
      initview();

        //[1]新建一个广播接收者,接收菜单修改的广播
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


//                Log.e("kjjj","onReceive_________"+intent.getAction());
                //[3]逻辑处理

                if (GloableConstant.ACION_SLIDING_DISABLE.equals(intent.getAction())) {

                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//不支持滑动
                } else if (GloableConstant.ACION_SLIDING_ENABLE.equals(intent.getAction())) {

                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//支持滑动
                }else if(GloableConstant.ACION_SLIDING_TOOGLE.equals(intent.getAction())){
                    getSlidingMenu().toggle();

                }
                else if(GloableConstant.ACION_CLEAR_ALL_RECEVERS.equals(intent.getAction())){
                    Toast.makeText(getApplicationContext(),"清理广播接收器了",Toast.LENGTH_SHORT).show();
                    //移除广播接收器
                    unregisterReceiver(this);
                }

            }
        };

       //[2]
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GloableConstant.ACION_SLIDING_DISABLE);
        intentFilter.addAction(GloableConstant.ACION_SLIDING_ENABLE);
        intentFilter.addAction(GloableConstant.ACION_SLIDING_TOOGLE);
        intentFilter.addAction(GloableConstant.ACION_CLEAR_ALL_RECEVERS);
        //广播接收器就注册了
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//    unregisterReceiver(broadcastReceiver);//移除

        Intent cleanAllBroad = new Intent();
        cleanAllBroad.setAction(GloableConstant.ACION_CLEAR_ALL_RECEVERS);
        sendBroadcast(cleanAllBroad);

    }

    /**
     * 初始化页面的所有控件
     */
    private void initview() {

//放置左侧视图在左侧菜单里
      setBehindContentView(R.layout.fragment_left_menu);

        //获取侧滑控件
        SlidingMenu slidingMenu = super.getSlidingMenu();
        //添加右边的侧滑界面
    //  slidingMenu.setSecondaryMenu(R.layout.activity_right_menu);
        //核心参数1 Mode
        //SlidingMenu.LEFT表示左侧有菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置左右都有侧滑菜单
//        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        //核心参数2
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //SlidingMenu.TOUCHMODE_MARGIN 范围是菜单间距
        //SlidingMenu.TOUCHMODE_FULLSCREEN 触摸的范围是全屏
        //SlidingMenu.TOUCHMODE_NONE  范围是0


        //获取屏幕的宽度
        //Display屏幕的显示对象: 宽 高
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        //宽
        int centerwidth = (int) (defaultDisplay.getWidth() * 2f / 3);
        //BehindOffset:整个屏幕减去侧滑的屏幕宽度就是中间的宽度了
        slidingMenu.setBehindOffset(centerwidth);

        initFragments();
    }

    private void initFragments() {
//创建二级页面
        LeftFragment leftFragment = new LeftFragment();
      CenterFragment centerFragment=  new CenterFragment();
       FragmentTransaction ft = super.getFragmentManager().beginTransaction();
    // ft.add(containerViewId 布局id,fragment 片段,tag 编号string);
        ft.add(R.id.fl_left_menu, leftFragment, LeftFragment.class.getSimpleName());
        //ft.add(R.id.center, centerFragment, CenterFragment.class.getSimpleName());
          ft.commit();
    }

}
