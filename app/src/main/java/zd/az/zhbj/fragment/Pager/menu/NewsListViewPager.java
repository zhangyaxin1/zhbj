package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import zd.az.zhbj.R;
import zd.az.zhbj.ben.NewsListData;
import zd.az.zhbj.constant.GloableConstant;
import zd.az.zhbj.utils.Cache_utils;

/**
 *
 * 显示新闻列表接界面,
 * 列表的头部--轮播大图+指示器
 * 列表本身-访问服务获取数据-解析数据-创建Adapter-设置listView
 * Created by Administrator on 2016/7/5.
 */
public class NewsListViewPager extends  BaseMenuDetailPager {


    /**
     * 加载新闻纪录
     */
    private List<NewsListData.NewItem> newsList;
    private NewsItemAdapter mNewsItemAdapter;
    //    private NewsItemAdapter mNewsItemAdapter;

    public NewsListViewPager(Activity activity) {
        super(activity);
        newsList=new ArrayList<NewsListData.NewItem>();
    }

    @Override
    public View initview() {

        View view = View.inflate(mActivity, R.layout.base_pager_news_list, null);
        View headview = View.inflate(mActivity, R.layout.base_pager_news_header, null);
        //列表头部视图通过addheaderView方法添加到列表的顶部
        ListView listview = (ListView) view.findViewById(R.id.list_news);
        listview.addHeaderView(headview);
        mNewsItemAdapter = new NewsItemAdapter();


        listview.setAdapter(mNewsItemAdapter);

        return view;
    }
    /**
     * 列表的适配器
     */
    class NewsItemAdapter extends BaseAdapter {


        @Override
        public int getCount() {

//            Log.e("新闻的条数","+++++++"+newsList.size());
            return newsList.size();

        }


        class ViewHolderNewsItem {

            public ImageView icom;

            public TextView title;

            public TextView date;

        }

        /**
         * 返回条目视图
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderNewsItem holder = null;
            //1.数据
            NewsListData.NewItem newItem = newsList.get(position);

            //优化写法
            //2.视图
            if (convertView == null) {
                holder = new ViewHolderNewsItem();
                convertView = View.inflate(mActivity, R.layout.item_news, null);
                holder.icom = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.title = (TextView) convertView.findViewById(R.id.tv_tl);
                holder.date = (TextView) convertView.findViewById(R.id.tv_date);
                //绑定
                convertView.setTag(holder);
                return null;
            } else {
                //重用
                holder = (ViewHolderNewsItem) convertView.getTag();

            }

            //赋值
            holder.title.setText(newItem.title);
            holder.date.setText(newItem.pubdate);

//            //加载图片
//            bitmapUtils.display(holder.icom, newItem.listimage);


            return convertView;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

//        private BitmapUtils bitmapUtils;
//
//        public NewsItemAdapter() {
//
//            //图片加载工具
//            bitmapUtils = new BitmapUtils(mActivity);
//            //配置加载前的默认图
//            bitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
//
//
//
//
//        }



     public void initData(Object params) {
        super.initData(params);

        //缓存
        final String realtivePath = (String) params;
        String url = GloableConstant.SERVER_HOST + realtivePath;

        //从sp缓存中读取json
         String cache = Cache_utils.getString(mActivity, realtivePath);

        if (!TextUtils.isEmpty(cache)) {

            processData(cache);
        }
        //  发请求
//发请求
        HttpUtils httpUtils = new HttpUtils();
        //创建回调对象
        RequestCallBack<String> requestcallBack = new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> result) {
                /**
                 * 方法成功
                 */
                Log.i("qawd", "成功+++++" + result.result);
                processData(result.result);
                //为下次界面可以使用缓存

                Cache_utils.saveString(mActivity, realtivePath, result.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                /**
                 * 方法失败
                 */
                Log.i("qawd", "onFailure" + e.getMessage());

            }
        };
        httpUtils.send(HttpRequest.HttpMethod.GET, url, requestcallBack);

    }

    private void processData(String json) {

        //获取服务端数据进行解析
//        Log.i("qd", "processData________" + json);

        Gson gson = new Gson();
        NewsListData data = gson.fromJson(json, NewsListData.class);

//        //加载服务端解析的数据
//        newsList.clear();
        newsList.addAll(data.data.news);
//        //刷新列表
        mNewsItemAdapter.notifyDataSetChanged();
//        Log.i("kijji","获取新闻页面数据"+data.toString());
    }

}