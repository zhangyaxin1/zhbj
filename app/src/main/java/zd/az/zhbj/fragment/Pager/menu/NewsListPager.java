package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import zd.az.zhbj.R;
import zd.az.zhbj.activity.NewDetailActivity;
import zd.az.zhbj.ben.NewsListData;
import zd.az.zhbj.constant.GloableConstant;
import zd.az.zhbj.utils.Cache_utils;
import zd.az.zhbj.view.InnerViewPager;

/**
 * Created by Administrator on 2016/7/5.
 */
public class NewsListPager extends BaseMenuDetailPager {

    /**
     * 加载新闻记录
     **/
    private List<NewsListData.NewItem> newsList;
    private ListView listview;
    private NewsItemAdapter mNewsAdapter;
    private InnerViewPager mTopViewPager;
    private TextView mTopTitle;
    private CirclePageIndicator mPageIndicator;
    private Handler mHandler = null;

    /***
     * 构造方法
     *
     * @param activity
     */
    public NewsListPager(Activity activity) {
        super(activity);

    }

    @Override
    public View initview() {
        View view = View.inflate(mActivity, R.layout.base_pager_news_list, null);
        View headView = View.inflate(mActivity, R.layout.base_pager_news_header, null);
        mTopViewPager = (InnerViewPager) headView.findViewById(R.id.vp_ViewPager_topnew);

        mTopViewPager.setId((int) System.currentTimeMillis());
        mTopTitle = (TextView) headView.findViewById(R.id.tv_tt);
        mPageIndicator = (CirclePageIndicator) headView.findViewById(R.id.circle_pageindicator);

        mPageIndicator.setId((int) System.currentTimeMillis());
        // 列表头部视图通过addHeadView方法添加列表的顶部
        listview = (ListView) view.findViewById(R.id.list_news);
        listview.addHeaderView(headView);

        listview.setId((int) System.currentTimeMillis());

        // 大图集合
        topNewsItemsList = new ArrayList<NewsListData.TopNewsItem>();
        // 大图适配器
        mTopNewAdapter = new TopNewAdapter();
        mTopViewPager.setAdapter(mTopNewAdapter);

        // 大图设置给指示器
        mPageIndicator.setViewPager(mTopViewPager);

        newsList = new ArrayList<NewsListData.NewItem>();
        mNewsAdapter = new NewsItemAdapter();
        listview.setAdapter(mNewsAdapter);

        initListeners();
        return view;
    }

    private List<NewsListData.TopNewsItem> topNewsItemsList;
    private TopNewAdapter mTopNewAdapter;

    /**
     * @author itheima 显示轮播图片的适配器
     */
    private class TopNewAdapter extends PagerAdapter {

        private BitmapUtils bitmapUtils;

        public TopNewAdapter() {
            bitmapUtils = new BitmapUtils(mActivity);
            bitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
        }

        /***
         * 方法:大图的数量
         *
         * @return
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub

//            Log.i("轮播图片的个数",topNewsItemsList.size()+"+++++");
            return topNewsItemsList.size();

        }

        /***
         * 方法
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
         * 方法 显示大图
         *
         * @param container
         * @param position  下标
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            // return super.instantiateItem(container, position);
            ImageView image = new ImageView(mActivity);
            image.setImageResource(R.drawable.topnews_item_default);

            // 获取显示数据
            NewsListData.TopNewsItem topNewsItem = topNewsItemsList.get(position);
//            Log.i("显示的默认图片",topNewsItem.topImage.toString()+"++++");
            //显示完标题
            mTopTitle.setText(topNewsItem.title);
            bitmapUtils.display(image, topNewsItem.topimage);
            // 图片等待加载器去加载
            container.addView(image);
            return image;
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
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

    }

    public static    class ViewHolderNewsItem {
        public ImageView icon;
        public TextView title;
        public TextView date;
    }


    /**
     * @author itheima 新闻列表的适配器
     */
    private class NewsItemAdapter extends BaseAdapter {
        /**
         * 图片加载工具
         **/
        private BitmapUtils bitmapUtils;

        /***
         * 构造方法
         */
        public NewsItemAdapter() {
            bitmapUtils = new BitmapUtils(mActivity);
            // 配置加载前的缓冲图
            bitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
        }

        /***
         * 方法
         *
         * @return
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return newsList.size();
        }


        /***
         * 方法 返回条目视图
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 1.数据
            NewsListData.NewItem newItem = newsList.get(position);
//            Log.d("打印新数据",newItem.toString());
            // 优化写法
            // 2.视图
            ViewHolderNewsItem holder = null;
            if (convertView == null) {
                holder = new ViewHolderNewsItem();
                convertView = View.inflate(mActivity, R.layout.item_news1, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.date = (TextView) convertView.findViewById(R.id.tv_date);
                // 绑定
                convertView.setTag(holder);
            } else {

                holder = (ViewHolderNewsItem) convertView.getTag();
            }
            try {
                // 3、赋值
                holder.title.setText(newItem.title);
                holder.date.setText(newItem.pubdate);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // bitmapUtils 加载图片
            bitmapUtils.display(holder.icon, newItem.listimage);

            //显示已读与未读的状态
            String read_ids = Cache_utils.getString(mActivity, "read_ids");
//            Log.i("读取的数据",""+read_ids);
            //判断一条信息的id是已读还是未读
            if (read_ids.contains(newItem.id + "")) {
                //已读 为灰色
                holder.title.setTextColor(Color.GRAY);
            } else {
                //未读为黑色
                holder.title.setTextColor(Color.BLACK);
            }
            return convertView;
        }

        /***
         * 方法
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        /***
         * 方法
         *
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    /***
     * 方法:初始化页面数据的方法
     *
     * @param params
     */
    @Override
    public void initData(Object params) {
        // TODO Auto-generated method stub
        super.initData(params);
        // 缓存
        final String relativePath = (String) params;
        String url = GloableConstant.SERVER_HOST + relativePath;

        // 从sp缓存读取出json
        String cache = Cache_utils.getString(mActivity, relativePath);
        if (!TextUtils.isEmpty(cache)) {
            processData(cache);
        }
        // 发请求
        HttpUtils httpUtils = new HttpUtils();
        // 创建回调对象处理返回值
        RequestCallBack<String> requestCallBack = new RequestCallBack<String>() {

            /***
             * 方法 失败
             *
             * @param arg0
             * @param arg1
             */
            @Override
            public void onFailure(HttpException arg0, String arg1) {
//                Log.i("wzx", "onFailure " + arg0.getMessage());
            }

            @Override
            public void onSuccess(ResponseInfo<String> result) {
//                Log.i("wzx", "onSuccess " + result.result);
                processData(result.result);
                // 为下次进页面可以使用到缓存


                Cache_utils.saveString(mActivity, relativePath, result.result);
            }
        };
        httpUtils.send(HttpRequest.HttpMethod.GET, url, requestCallBack);
    }

    /***
     * 方法 替换主机地址
     *
     * @param
     */
    private void processData(String json) {
        // 获取服务端数据进行解析
//        Log.i("wzx", "processData" + json);
        json = json.replaceAll("http://10.0.2.2:8080/zhbj/", GloableConstant.SERVER_HOST);
        Gson gson = new Gson();
        NewsListData data = gson.fromJson(json, NewsListData.class);
//        Log.i("wzx", data.toString());

        // 加载刷新大图数据
        topNewsItemsList.clear();
        topNewsItemsList.addAll(data.data.topnews);

//        Log.i("大图信息","_____"+topNewsItemsList.toString());
        // 调用指示器即可刷新大图
//       mPageIndicator.notifyDataSetChanged();
        // 虽然指示器建议我们使用它的刷新方法，但是效果上还是适配好用！
        mTopNewAdapter.notifyDataSetChanged();
        // 加载服务端解析的数据
        newsList.clear();
        newsList.addAll(data.data.news);
        // 刷新列表
        mNewsAdapter.notifyDataSetChanged();

        if (mHandler == null) {
            mHandler = new Handler() {
                /***
                 * 方法
                 *
                 * @param msg
                 */
                @Override
                public void handleMessage(Message msg) {
                    // TODO Auto-generated method stub
                    super.handleMessage(msg);

                    // 获取当前页面下标
                    int currentItem = mTopViewPager.getCurrentItem();
                    currentItem++;// 显示下一页
                    if (currentItem > topNewsItemsList.size() - 1) {
                        // 从第一页重新开始
                        currentItem = 0;
                    }
                    mTopViewPager.setCurrentItem(currentItem);
                    // 已经执行了handleMessage;
                    mHandler.removeMessages(1);

                    // 循环进下一页播放
                    Message msg2 = new Message();
                    msg2.what = 1;
                    mHandler.sendMessageDelayed(msg2, 3000);

                }
            };
        }

        if (topNewsItemsList.size() > 0) {
            Message msg = new Message();
            msg.what = 1;
            // 延时发送
            // mHandler.sendMessageDelayed(msg 消息, delayMillis延时的毫秒数 )
            mHandler.sendMessageDelayed(msg, 3000);
        }
    }

    /***
     * 方法 给当前页面的控件添加监听器
     */
    private void initListeners() {
        mTopViewPager.setOnTouchListener(new View.OnTouchListener() {
            /*** 方法 处理触摸事件
             @param v
             @param event 动作的封装对象ACTION_DOWN ACTION_MOVE ACTION_UP
             @return
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下
                        Log.i("wzx", "onTouch ACTION_DOWN");
                        //handleMessage
                        //停止循环
                        mHandler.removeCallbacksAndMessages(null);//让消息的处理方法失效！
                        break;
                    case MotionEvent.ACTION_UP://重新发一个消息
                        Log.i("wzx", "onTouch ACTION_UP");
                        //开始循环
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessageDelayed(msg, 3000);
                        break;
                    case MotionEvent.ACTION_CANCEL://滑到别的控件 造成原有控件没发响应ACTION_UP只能使用ACTION_CANCLE代替
                        Log.i("wzx", "onTouch ACTION_CANCEL");
                        //开始循环
                        Message msg2 = new Message();
                        msg2.what = 1;
                        mHandler.sendMessageDelayed(msg2, 3000);
                        break;
                }

                return false;//不轻易消费事件
            }
        });


        //添加监听
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
              * @param parent
             * @param view 点击中的item视图
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //由于listview.addview添加轮播图headview 也是一个item,
                //只需减去headview数量(getHeaderViewsCount)
                position = position - listview.getHeaderViewsCount();
                //根据下标获取新闻数据
                NewsListData.NewItem newItem = newsList.get(position);
                Log.e("新闻数据的信息", "" + newItem.toString());
                Toast.makeText(mActivity, position + ":" + newItem.title, Toast.LENGTH_SHORT).show();

                //使用一个sp变量累加已读新闻的id
                //存起来:id|id|....
                String readIds = Cache_utils.getString(mActivity, "read_ids");

                if (readIds.contains(newItem.id + "")) {
                    Cache_utils.saveString(mActivity, "read_ids", readIds + "|" + newItem.id);

                }


                //整表刷新不可取,性能比较低
               /* //刷新整体的新闻列表
                mNewsAdapter.notifyDataSetChanged();//列表ListView整体刷新 通知ui*/

//                //性能较高
//                TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
//                tv_title.setTextColor(Color.GRAY);

                //holder里面存是find过的控件
          ViewHolderNewsItem holder = (ViewHolderNewsItem) view.getTag();
                holder.title.setTextColor(Color.GRAY);
                // getview 根据保存的id判断显示黑色还是灰色
                Intent intent = new Intent(mActivity, NewDetailActivity.class);
                intent.putExtra("url",newItem.url);
                mActivity.startActivity(intent);

            }
        });

    }
}
