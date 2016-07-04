package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import zd.az.zhbj.R;
import zd.az.zhbj.ben.LeftMenuData;

/**
 * 新闻频道页面
 * Created by Administrator on 2016/6/30.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager{


    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;

//    private  List<NewsListViewPager> mPagers = null;

    /*
            构造方法
             */
    public NewsMenuDetailPager(Activity activity) {
        super(activity);
//
//        mPagers = new ArrayList<NewsListViewPager>();
    }

    /**
     * 初始化四级页面的控件
     * @return
     */
    @Override
    public View initview() {
//        TextView contentView = new TextView(mActivity);
        /*contentView.setText("四级页面:频道");
        contentView.setBackgroundColor(Color.BLUE);
        contentView.setTextColor(Color.RED);
        contentView.setGravity(Gravity.CENTER);*/
        View view = View.inflate(mActivity, R.layout.menu_detail_news_pager,null);
        //TabPageIndicator带文字与下划线的指示效果
        mTabPageIndicator = (TabPageIndicator) view.findViewById(R.id.viewpager_tabinidcator);
        mViewPager = (ViewPager) view.findViewById(R.id.menu_deital_pager_viewpager);
//       mTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//           @Override
//           public void onPageSelected(int position) {
//
//               LoadNewsListPagerData(position);
//           }
//
//           @Override
//           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//           }
//           @Override
//           public void onPageScrollStateChanged(int state) {
//
//           }
//       });
        return view;
    }

    /**
     * 加载新闻页面数据
     * @param position
     */
//    private void LoadNewsListPagerData(int position) {
//        NewsListViewPager newsListViewPager =  mPagers.get(position);
//        LeftMenuData.MenuData.CategoryData categoryData = categorys.get(position);
//        //相对地址://10008/list_1.json
////        newsListViewPager.initData(categoryData.url);
//    }

    private List<LeftMenuData.MenuData.CategoryData> categorys = new ArrayList<LeftMenuData.MenuData.CategoryData>();
    //获取数据
    @Override
    public void initData(Object params) {
        super.initData(params);
        LeftMenuData data = (LeftMenuData) params;
        //编写显示代码
        categorys.clear();
        //访问数据的第一个新闻children
        categorys.addAll(data.data.get(0).children);
        //显示viewpager
        CategoryPager adapter = new CategoryPager();
        mViewPager.setAdapter(adapter);
        //再显示指示器:指示器必须要求加入viewPager 不然报错
        mTabPageIndicator.setViewPager(mViewPager);

        //重写adpter的getPageTitle方法 拿到文字
//for (int i=0;i<categorys.size();i++){
//
//NewsListViewPager newsListViewPager =new NewsListViewPager(mActivity);
//    mPagers.add(newsListViewPager);
//}
//
//        if(categorys.size()>0) {
//            LoadNewsListPagerData(0);
//        }


    }

    /**
     * 显示多个新闻频道数据
     */
    private  class CategoryPager extends PagerAdapter{

        /**
         * 返回指示器的文字
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return categorys.get(position).title;
        }

        @Override
        public int getCount() {
            return categorys.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);

            TextView textView =new TextView(mActivity);
       LeftMenuData.MenuData.CategoryData categoryData = categorys.get(position);
            textView.setText("当前频道为"+categoryData.title);
      textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.BLUE);
            //显示在滑动控件上面
container.addView(textView);
//        NewsListViewPager newsListViewPager = mPagers.get(position);
//            //添加新闻列表页面
//           View view = newsListViewPager.mRootView;
//            container.addView(view);
            return textView;
        }

        /**
         * 销毁页面
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           /* super.destroyItem(container, position, object);*/

         container.removeView((View) object);
        }
    }

}
