package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import zd.az.zhbj.R;
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


    public NewsListViewPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initview() {

        View view = View.inflate(mActivity, R.layout.base_pager_news_list, null);
        View headview = View.inflate(mActivity, R.layout.base_pager_news_header, null);
        //列表头部视图通过addheaderView方法添加到列表的顶部
        ListView listview = (ListView) view.findViewById(R.id.list_news);
        listview.addHeaderView(headview);

        return view;
    }


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
        Log.i("qd", "processData________" + json);


    }
}
