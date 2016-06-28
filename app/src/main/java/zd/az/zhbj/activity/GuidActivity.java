package zd.az.zhbj.activity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import zd.az.zhbj.R;
import zd.az.zhbj.utils.Cache_utils;

/**引导用户熟悉功能
 * 自己实现小圆点底层
 * Created by Administrator on 2016/6/22.
 */
public class GuidActivity  extends Activity{
private  ViewPager viewPager;
    private   Button button;
    private   MyAdapter adapter;
    private  LinearLayout ll_dots;
    private int  mDotDistance;

    private  ImageView     iv_red_dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        initView();
       initListener();
    }

    /**
     * 初始化页面所有的监听器
     */
    private void initListener() {
//把viewPager换成ciecle_indicato,配置监听器才能根据滑动页面指示
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             *
             * @param position    当前页面的下标
             * @param positionOffset   页面切换的 百份比 0.3 0.5f
             * @param positionOffsetPixels  滑动像素
             */


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
Log.i("kji","+++++++"+positionOffset);
//
////            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_dots.getLayoutParams();
//////     RelativeLayout.LayoutParams  params= (RelativeLayout.LayoutParams) iv_red_dots.getLayoutParams();
//            //currPosition处理了左滑为减  右滑为加
//            int newRedLeftMargin=(int) (position*mDotDistance+positionOffset*mDotDistance);
//            params.leftMargin=newRedLeftMargin;
//            iv_red_dots.setLayoutParams(params);
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
                startActivity(new Intent(GuidActivity.this,Main2Activity.class));
                finish();

            }
        });

    }

    //初始化页面的所有的控件
    private void initView() {
        viewPager  = (ViewPager) findViewById(R.id.vp_viewpager);
         button  = (Button) findViewById(R.id.btn_button);
    iv_red_dots = (ImageView) findViewById(R.id.iv_red_dots);
        //创建适配器
        adapter    = new MyAdapter();
        //设置内容给高级控件
        viewPager.setAdapter(adapter);
//获取灰色的圆点
        ll_dots  = (LinearLayout) findViewById(R.id.ll_gray_dots);
             //循环获取页面个数,添加标签
        for(int i=0;i<adapter.getCount();i++) {
            //在线性布局中加标签
            ImageView grayDot = new ImageView(this);

            //显示灰色效果
            grayDot.setBackgroundResource(R.drawable.shap_gray_dot);

            //添加进线性布局
            ll_dots.addView(grayDot);

            //XXXX.LayoutParams 用来java代码布局控件
            //选择哪个类型看一下控件放到哪种布局里
            if (i > 0) {

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,//宽
                        LinearLayout.LayoutParams.WRAP_CONTENT);//高

                params.leftMargin = 10;
                grayDot.setLayoutParams(params);

            }

        }


        // 了解View的工作原理？
        // 需要监听onLayout执行完毕 如果该方法不执行 未执行完 getLeft..都为0
        // OnGlobalLayoutListener监听布局完毕
        ll_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 获取任意两个点
                // getChildAt获取布局上面的子控件
                int xDot2=ll_dots.getChildAt(1).getLeft();
                int  xDot1  =ll_dots.getChildAt(0).getLeft();
                mDotDistance = xDot2 - xDot1;
                Log.i("wzx", "mDotDistance:" + mDotDistance);

                // 使用该监听器 已经获取了点的间距
                ll_dots.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


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
