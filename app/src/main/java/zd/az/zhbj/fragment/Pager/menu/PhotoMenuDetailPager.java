package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import zd.az.zhbj.R;
import zd.az.zhbj.ben.PhotoData;
import zd.az.zhbj.constant.GloableConstant;
import zd.az.zhbj.utils.Cache_utils;

import static zd.az.zhbj.R.layout.menu_detail_photo_pager;

/**
 * 组图四级页面
 * Created by Administrator on 2016/6/30.
 */
public class PhotoMenuDetailPager extends BaseMenuDetailPager{


    private GridView gridView;
    private ListView mlistview;
    private ImageView mIvRight;



    /*
                构造方法
                 */
    public PhotoMenuDetailPager(Activity activity ,ImageView right) {
        super(activity);
        mIvRight =right;
        mIvRight.setImageResource(R.drawable.icon_pic_grid_type);
        initListeners();
    }


    private  boolean isListView  =true;
    private ListPhotoAdapter mListAdapter;
    private ListPhotoAdapter mGridAdapter;
    private  List<PhotoData.PhotoItem> list = null;

    @Override
    public View initview() {
//        TextView contentView = new TextView(mActivity);
//        contentView.setText("四级页面:组图");
//        contentView.setBackgroundColor(Color.BLUE);
//        contentView.setTextColor(Color.RED);
//        contentView.setGravity(Gravity.CENTER);


        View view = View.inflate(mActivity, menu_detail_photo_pager,null);
        mlistview = (ListView) view.findViewById(R.id.listview_photo);
        gridView = (GridView) view.findViewById(R.id.gridview_photo);
        mlistview.setVisibility(View.VISIBLE);


//        list = new ArrayList<PhotoData.PhotoItem>();
      //设置适配器
        mListAdapter = new ListPhotoAdapter();
        mGridAdapter = new ListPhotoAdapter();
        mlistview.setAdapter(mListAdapter);
        gridView.setAdapter(mGridAdapter);
        return view;
    }


    /**
     * 列表图片适配器
     */
    private  class ListPhotoAdapter extends BaseAdapter{
        private BitmapUtils bitmapUtils;

        public  ListPhotoAdapter() {
          list = new ArrayList<PhotoData.PhotoItem>();
            bitmapUtils = new BitmapUtils(mActivity);
            bitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
        }




        @Override

        public int getCount() {
            return list.size();
        }


        class ViewHolderPic{

            ImageView pic;
            TextView desc;

        }
        /**
         * 返回条目视图 需要优化
         */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           // 1.数据
           PhotoData.PhotoItem photoItem = list.get(position);
            //视图 优化inflate findViewById
            ViewHolderPic holder = null;

            if(convertView==null){
                holder = new ViewHolderPic();
                convertView = View.inflate(mActivity,R.layout.item_photo,null);
                holder.pic = (ImageView) convertView.findViewById(R.id.img_list);
                holder.desc = (TextView) convertView.findViewById(R.id.tv_desc);
                //绑定
                convertView.setTag(holder);
            }else{

                holder = (ViewHolderPic) convertView.getTag();
            }
            //赋值
       holder.desc.setText(photoItem.title);
            bitmapUtils.display(holder.pic, photoItem.listimage);

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

    /**
     * 当前组图页面的监听器添加方法
     */
    private void initListeners() {

    mIvRight.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isListView=!isListView;
            if (isListView){//显示列表
                mlistview.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                mIvRight.setImageResource(R.drawable.icon_pic_grid_type);
            }
            else{//显示网格视图
                mlistview.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                mIvRight.setImageResource(R.drawable.icon_pic_list_type);
            }
        }
    });
    }

    @Override
    public void initData(Object params) {
        super.initData(params);

        //解析地址:/photos/photos_1.json

        //先从本地取到缓存数据
        String cache = Cache_utils.getString(mActivity, GloableConstant.URL_PHOTO);
        if(!TextUtils.isEmpty(cache)){
            
            processData(cache);
            
        }
        String url =GloableConstant.SERVER_HOST+GloableConstant.URL_PHOTO;

        //创建请求工具

        HttpUtils httpUtils = new HttpUtils();
        //httpUtils.send(method,请求url正确的地址,callBack 回调对象   提供处理方法的对象)

        //创建回调对象
     RequestCallBack<String> requestCallBack =    new RequestCallBack<String>(){

         @Override //成功
         public void onSuccess(ResponseInfo<String> info) {
             processData(info.result);

             //缓存
             Cache_utils.saveString(mActivity,GloableConstant.URL_PHOTO,info.result);
         }

         @Override//失败
         public void onFailure(HttpException e, String s) {

         }
     };
        httpUtils.send(HttpRequest.HttpMethod.GET, url,requestCallBack);

    }





    /**
     * 解析数据 创建界面
     * @param json  json数据
     */
    private void processData(String json) {

//
        json = json.replaceAll("http://10.0.2.2:8080/zhbj", GloableConstant.SERVER_HOST);
        //创建解析的核心类
        Gson  gson  = new Gson();
   PhotoData data =   gson.fromJson(json, PhotoData.class);

        if (list==null){

            list=new ArrayList<PhotoData.PhotoItem>();
            list.clear();
        }
//
//        list.clear();
        list.addAll(data.data.news);
        mListAdapter.notifyDataSetChanged();
        mGridAdapter.notifyDataSetChanged();
        Log.i("wzx","解析后的内容"+data.toString());
    }


}
