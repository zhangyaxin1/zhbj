package zd.az.zhbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

import zd.az.zhbj.R;
import zd.az.zhbj.utils.Cache_utils;

/**引导用户熟悉功能
 * Created by Administrator on 2016/6/22.
 *
 * 使用第三方库实现小圆点
 */
public class GuidActivity1 extends Activity{
private  ViewPager viewPager;
    private   Button button;
    private   MyAdapter adapter;

    private    CirclePageIndicator ciecle_indicato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);
        initData();
        initView();
       initListener();
    }

    /**
     * 初始化页面所有的监听器
     */
    private void initListener() {
//把viewPager换成ciecle_indicato,配置监听器才能根据滑动页面指示
        ciecle_indicato.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             *
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

            /**
             * 页面切换完成
             * @param position  下标
             */
        @Override
        public void onPageSelected(int position) {

//只有第三页才显示开始按钮
            if (position == adapter.getCount() - 1) {
                //显示
                button.setVisibility(ViewPager.VISIBLE);
            } else {
                //隐藏
                button.setVisibility(ViewPager.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存变量值  inidata get
                 Cache_utils.saveString(getApplicationContext(),"isFirstShow","false");
                startActivity(new Intent(GuidActivity1.this,Main2Activity.class));
                finish();

            }
        });

    }


    //初始化页面的所有的控件
    private void initView() {

        viewPager  = (ViewPager) findViewById(R.id.vp_viewpager);

         button  = (Button) findViewById(R.id.btn_button);

        ciecle_indicato = (CirclePageIndicator) findViewById(R.id.circle_indicator);

        //创建适配器
        adapter    = new MyAdapter();
        //设置内容给高级控件
        viewPager.setAdapter(adapter);

        //给指示器设置viewPager
        ciecle_indicato.setViewPager(viewPager);

        //滑动监听器

    }

    /**
     * 显示图片内容
     *
     * @return
     */
    //适配器,页面相关的适配器
    private class MyAdapter extends PagerAdapter{


        private   int []imageResIds = new int[]{
                R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3

        };

        /**
         * 方法 页数据
         */

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        /**
         * 方法 官方建议
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        /**
         * 显示页面
         * @param container  指viewPager
         * @param position 当前页面对应的下标
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);//一定要求删除该代码,super.默认抛出异常
//单个页面的view
            ImageView itemView = new ImageView(getBaseContext());
        itemView.setBackgroundResource(imageResIds[position]);

            //添加显示
            container.addView(itemView);
            return  itemView;
        }


        /**
         * 移除显示
         * @param container
         * @param position
         * @param object  页面控件
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);

            container.removeView((View) object);
        }
    }


    private void initData() {

    }

}
