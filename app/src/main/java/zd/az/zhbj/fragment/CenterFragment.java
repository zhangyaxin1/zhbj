package zd.az.zhbj.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

import zd.az.zhbj.R;
import zd.az.zhbj.fragment.Pager.BasePager;
import zd.az.zhbj.fragment.Pager.GovAffarisPager;
import zd.az.zhbj.fragment.Pager.HomePager;
import zd.az.zhbj.fragment.Pager.NewsCenterPager;
import zd.az.zhbj.fragment.Pager.SettingPager;
import zd.az.zhbj.fragment.Pager.SmartServicePager;
import zd.az.zhbj.view.NoScrollViewPager;

/**
 * 二级页面  新闻
 * Created by Administrator on 2016/6/24.
 */
public class CenterFragment extends Fragment  {
/*
  public View initView() {
        View view  =View.inflate( R.layout.fragment_center_menu,container,false);
        return view;
    }
*/
    public Activity mActivity;
    public View mRootView;//当前页面的视图
    private  NoScrollViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private  ArrayList<BasePager> mPagers;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//保存上下文
        Log.d("jhji", "+++++++++" + mActivity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View  mRootView= inflater.inflate( R.layout.fragment_center_menu,container,false);
        mViewPager  = (NoScrollViewPager) mRootView.findViewById(R.id.vp_ViewPager);
        mRadioGroup = (RadioGroup) mRootView.findViewById(R.id.rg_group);
    ContentPagerAdapter1 pagerAdapter = new ContentPagerAdapter1();
    mViewPager.setAdapter(pagerAdapter);
    initListeners();
        return mRootView;
    }

//     初始化二级页面的监听器

    private void initListeners() {

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                int positionPager = 0;
                switch (checkedId){
                    case R.id.rb_home1:
                        positionPager=0;
                        break;

                    case R.id.rb_new_center:
                        positionPager=1;
                        break;

                    case R.id.rb_smart_service:
                        positionPager=2;
                        break;

                    case R.id.rb_gov_affari:
                        positionPager=3;
                        break;

                    case R.id.rb_setting:
                        positionPager=4;
                        break;
                }

//                            mViewPager.setCurrentItem(item 页面下标,smoothScroll 是否支持滚动效果);

                //切换viewPager显示页面
                mViewPager.setCurrentItem(positionPager,false);//不支持动画效果
                                                   }
        });

    }


//    private  class ContentPagerAdapter extends PagerAdapter{
//
////装载三级页面的集合
//        public ContentPagerAdapter() {
//            mPagers = new ArrayList<>();
//            mPagers.add(new HomePager(mActivity));//主页
//            mPagers.add(new NewsCenterPager(mActivity));//新闻中心
//            mPagers.add(new SmartServicePager(mActivity)); //智慧服务
//            mPagers.add(new GovAffarisPager(mActivity));//政务
//            mPagers.add(new SettingPager(mActivity));//设置
//        }
//
//        @Override
//        public int getCount() {
//            return mPagers.size();
//        }
//
//        /**
//         * 官方要求
//         * @param view
//         * @param object
//         * @return
//         */
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        //加载页面
//
//        /**
//         *
//         * @param container  ViewPager
//         * @param position  页面下标
//         * @return
//         */
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//           /* return super.instantiateItem(container, position);  删除super*/
//            BasePager pager = mPagers.get(position);
//            pager.initData();// 加载数据
//            View rootView = pager.mRootView;
//            container.addView(rootView);// 添加到二级面页面的ViewPager来显示
//            return rootView;// 返回三级页面的内容
//
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//          /*  super.destroyItem(container, position, object);*/
//           // container.removeAllViews();
//           container.removeView((View) object);
//        }
//
//    }

    /**
     * @author itheima 加载五个三级页面
     */
    private class ContentPagerAdapter1 extends PagerAdapter {

        /** 装载三级页面的集合 **/
        private ArrayList<BasePager> mPagers;

        public ContentPagerAdapter1() {
            mPagers = new ArrayList<BasePager>();
            mPagers.add(new HomePager(mActivity));// 首页
            mPagers.add(new NewsCenterPager(mActivity));// 新闻中心
            mPagers.add(new SmartServicePager(mActivity));// 智慧服务
            mPagers.add(new GovAffarisPager(mActivity));// 政务
            mPagers.add(new SettingPager(mActivity));// 设置
        }

        /***
         * 方法:页数
         *
         * @return
         */
        @Override
        public int getCount() {
            return mPagers.size();
        }

        /***
         * 方法:官方要求
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /***
         * 方法 加载页面
         *
         * @param container
         *            ViewPager
         * @param position
         *            页面下标
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // // TODO Auto-generated method stub
            // return super.instantiateItem(container, position); 删除super
            BasePager pager = mPagers.get(position);
            pager.initData();// 加载数据
            View rootView = pager.mRootView;
            container.addView(rootView);// 添加到二级面页面的ViewPager来显示
            return rootView;// 返回三级页面的内容
        }

        /***
         * 方法
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            // super.destroyItem(container, position, object); 删除
            container.removeView((View) object);
        }
    }

}
