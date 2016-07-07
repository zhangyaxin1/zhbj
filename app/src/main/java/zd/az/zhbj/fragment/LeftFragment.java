package zd.az.zhbj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.security.PublicKey;

import zd.az.zhbj.R;
import zd.az.zhbj.ben.LeftMenuData;
import zd.az.zhbj.constant.GloableConstant;
import zd.az.zhbj.utils.Cache_utils;

/**
 * Created by Administrator on 2016/6/24.
 */
public class LeftFragment extends BaseFragment {

    private LeftMenuData mLeftMenuData;
    private ListView mListView;
    //设置高亮
    private int positionHighLight = 0;
    private MenuAdapter adapter;

    /**
     * 初始化片段的控件
     *
     * @return /*
     *//*

    public View initView() {
        View view  =View.inflate(mActivity, R.layout.fragment_left_menu,null);
        return view;
    }
*/
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView= inflater.inflate(R.layout.fragment_left_menu,container,false);//初始化控件
//        return mRootView;
//    }
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        mListView = (ListView) view.findViewById(R.id.listview_menu);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             *
             * @param parent
             * @param view
             * @param position 点中的下标
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switchMenuItem(position);

            }
        });

        initData();//调用
        return view;
    }

    /**
     * 切换菜单的时候完成高亮显示
     * 完成三级页面NewsCenterPager的标题与内容的切换
     * 使用广播与广播接收器最简洁
     *
     * @param position
     */
    private void switchMenuItem(int position) {
        //保存点中位置为高亮
        positionHighLight = position;
        //重新显示列表
        adapter.notifyDataSetChanged();//==>getview 处理逻辑,选中的高亮,反之不高亮

        //创建广播
        Intent broadcast = new Intent();
        switch (position) {
            //新闻
            case 0:
                broadcast.setAction(GloableConstant.ACION_LEFT_MENU_NEWS);
                //左侧菜单的数据处理后顺便发给新闻中心页面
                broadcast.putExtra("data",mLeftMenuData);//javaBean对象要求实现序列化接口包含它的子元素也实现了序列化
                break;
            //专题
            case 1:
                broadcast.setAction(GloableConstant.ACION_LEFT_MENU_TOPIC);
                break;
            //组图
            case 2:
                broadcast.setAction(GloableConstant.ACION_LEFT_MENU_PHOTO);
                break;
            //互动
            case 3:
                broadcast.setAction(GloableConstant.ACION_LEFT_MENU_INTERACT);
                break;

        }
        //发送广播
        mActivity.sendBroadcast(broadcast);


    }

    /**
     * 获取服务端的数据到本地界面显示
     */
    @Override
    public void initData() {

        super.initData();
//拼接地址
        String url = GloableConstant.SERVER_HOST + GloableConstant.URL_LEFT_MENU_DATA;
        String cache = Cache_utils.getString(mActivity,GloableConstant.URL_LEFT_MENU_DATA);
        if (!TextUtils.isEmpty(cache)){
            //显示到界面上,缓存数据有可能是旧数据
            processData(cache);
        }
//向服务端发起请求
        //Xutils有一个http工具类HttpUtils 能够进行http请求的工具类send就可以向服务器发送请求
        //get/post\
        //接收服务端 返回数据
        HttpUtils httpUtils = new HttpUtils();
//        httpUtils.send(method请求方式:post/get,url: 服务端地址,callBack:回调,当服务端数据返回时需的一个接收数据对象)
        //new一个回调的请求对象 JIE
        RequestCallBack<String> requestCallBack = new RequestCallBack<String>() {

            /**
             * 处理请求成功后的显示
             * @param responseInfo
             */
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

//ResponseInFo:响应信息 就是一个javabean 封装了服务端返回的数据
                Log.i("hjhi", "onSuccess++++++请求成功" + responseInfo.result);
                //新数据显示在界面上 同时保存一份到本地
                processData(responseInfo.result);
                //更新缓存
                Cache_utils.saveString(mActivity,GloableConstant.URL_LEFT_MENU_DATA, responseInfo.result);
            }

            /**
             * 处理请求失败后的显示
             * @param e
             * @param s
             */
            @Override
            public void onFailure(HttpException e, String s) {


                Log.i("ooioi", "onFailure++++++++请求失败");
            }
        };
        httpUtils.send(HttpRequest.HttpMethod.GET, url, requestCallBack);
    }

    /**
     * 方法 解析服务端返回的json数据
     *
     * @param result
     */
    private void processData(String result) {

        //创建核心对象
        Gson gson = new Gson();
 /*       gson.fromJson(json :json字符串,classofT:字符串对应的类,已经在ben包下创建过json解析的类);*/
        mLeftMenuData = gson.fromJson(result, LeftMenuData.class);
        Log.e("hhii", "解析后的数据对象____________" + mLeftMenuData.toString());
        showListView();
    }

    /*
    *方法 高级控件ListView显示集合 ,优化ListView
    * */
    private void showListView() {

        adapter = new MenuAdapter();
        mListView.setAdapter(adapter);
        //    switchMenuItem(0);

//        new Thread(){
//            public void run(){
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            };
//
//
//        }.start();

//        new Handler().postDelayed(r:要求执行的代码,delayMillis:延时时间);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switchMenuItem(0);
            }
        }, 500);//handleMessage(0)  r.run
    }

    private class MenuAdapter extends BaseAdapter {

        /**
         * 左侧菜单的适配器
         *
         * @return
         */
        @Override
        public int getCount() {
//            Log.e("hjiji", "+++++++" + mLeftMenuData.data.size());
            return mLeftMenuData.data.size();
        }

        /**
         * 控件集合
         */
        class ViewHolderMunu {
            public TextView menu_title;
        }

        /**
         * 返回条目的视图
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //1.数据
            LeftMenuData.MenuData menudata = mLeftMenuData.data.get(position);
//            Log.d("获取到的Item数据", menudata + "");
            ViewHolderMunu holder = null;
            //2视图
            //-----startView
            if (convertView == null) {//1.优化inflate 2.优化findViewById

                convertView = View.inflate(mActivity, R.layout.item_menu, null);
                holder = new ViewHolderMunu();
                holder.menu_title = (TextView) convertView.findViewById(R.id.menu_title);

                //绑定给视图,有视图就有holder
                convertView.setTag(holder);

            } else {//重用视图

                holder = (ViewHolderMunu) convertView.getTag();

            }
            //-----endView
            //赋值
            holder.menu_title.setText(menudata.title);
            //高亮处理
            if (position == positionHighLight) {
                holder.menu_title.setSelected(true);//显示红色字体,图片
            } else {
                holder.menu_title.setSelected(false);//显示白色字体,图片
            }
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
}
