package zd.az.zhbj.fragment.Pager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import zd.az.zhbj.MainActivity;
import zd.az.zhbj.activity.Main2Activity;
import zd.az.zhbj.ben.LeftMenuData;
import zd.az.zhbj.constant.GloableConstant;
import zd.az.zhbj.fragment.Pager.menu.BaseMenuDetailPager;
import zd.az.zhbj.fragment.Pager.menu.InteractMenuDetailPager;
import zd.az.zhbj.fragment.Pager.menu.NewsMenuDetailPager;
import zd.az.zhbj.fragment.Pager.menu.PhotoMenuDetailPager;
import zd.az.zhbj.fragment.Pager.menu.TopicMenuDetailPager;

/**
 * Created by Administrator on 2016/6/27.
 */
public class NewsCenterPager extends BasePager{


    private  ArrayList<BaseMenuDetailPager> mMenuPagers;

    public NewsCenterPager(final Activity activity) {
        super(activity);

        /**
         * 使用list管理四个四级界面
         */
       mMenuPagers = new ArrayList<BaseMenuDetailPager>();

     mMenuPagers.add(new NewsMenuDetailPager(mActivity));//新闻 许多报道
        mMenuPagers.add(new TopicMenuDetailPager(mActivity));//专题
        mMenuPagers.add(new PhotoMenuDetailPager(mActivity));//组图
        mMenuPagers.add(new InteractMenuDetailPager(mActivity));//交互

        //注册一个广播接收器,发送者是二级页面left页面的Intent

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            /**
             * 接收左侧菜单的信号,更新标题与内容
             * @param context
             * @param intent
             */
            @Override
            public void onReceive(Context context, Intent intent) {
//                Toast.makeText(mActivity,"ji"+intent.getAction(),0).show();
                //获取数据



                //以下是显示标题与内容的
                Toast.makeText(mActivity,"菜单信号:"+intent.getAction(),Toast.LENGTH_SHORT).show();
                mIVRight.setVisibility(View.GONE);
               BaseMenuDetailPager currpager =null;

                //设置标题切换
                if(intent.getAction().equals(GloableConstant.ACION_LEFT_MENU_NEWS)){
                    mTvTitle.setText("新闻");
                    currpager=mMenuPagers.get(0);

                    //获取数据
                    //设置给新闻频道页面
              LeftMenuData data = (LeftMenuData) intent.getSerializableExtra("data");

                   Log.i("sdsd","在四级页面获取数据++++"+data.toString());
                    //获取服务端数据
                    NewsMenuDetailPager  newpager = (NewsMenuDetailPager) currpager;
                    newpager.initData(data);
                }
               else if(intent.getAction().equals(GloableConstant.ACION_LEFT_MENU_TOPIC)){
                    mTvTitle.setText("专题");
                    currpager=mMenuPagers.get(1);
                }
               else if(intent.getAction().equals(GloableConstant.ACION_LEFT_MENU_INTERACT)){
                    mTvTitle.setText("交互");
                    currpager =mMenuPagers.get(3);
                }
              else  if(intent.getAction().equals(GloableConstant.ACION_LEFT_MENU_PHOTO)){
                    mTvTitle.setText("组图");
                    mIVRight.setVisibility(View.VISIBLE);
                    currpager=mMenuPagers.get(2);
                }


                //获取四级页面的视图
                View pageView = currpager.mRootView;
                //清空三级页面中间内容
                mContentView.removeAllViews();
                //添加新的视图
                mContentView.addView(pageView);

                Log.d("ssds","+++++++++"+mActivity.toString());
                //每个页面(二级开始的上下文都是MainActivity)
                Main2Activity main2Activity = (Main2Activity) mActivity;
                //侧滑控件
         SlidingMenu slidingMenu = main2Activity.getSlidingMenu();
                Log.d("kjdjkj","关闭侧滑菜单");
                //合上菜单,即显示中间内容
                slidingMenu.showContent();

            }
        };
        //绑定广播信号
        IntentFilter intentFilter = new IntentFilter();

        //注册几种信号就可接收对应的信号,未注册的接收不到
        intentFilter.addAction(GloableConstant.ACION_LEFT_MENU_NEWS);
        intentFilter.addAction(GloableConstant.ACION_LEFT_MENU_TOPIC);
        intentFilter.addAction(GloableConstant.ACION_LEFT_MENU_PHOTO);
        intentFilter.addAction(GloableConstant.ACION_LEFT_MENU_INTERACT);
//注册信号
        mActivity.registerReceiver(broadcastReceiver,intentFilter);

    }

    @Override
    public void initView(FrameLayout contentView) {

        mTvTitle.setText("新闻中心");
        mIVMenu.setVisibility(View.VISIBLE);
        TextView view = new TextView(mActivity);
        view.setText("三级页面-新闻中心");
        view.setTextSize(22);
        view.setTextColor(Color.RED);
        contentView.addView(view);
        view.setGravity(Gravity.CENTER);
    }


}
