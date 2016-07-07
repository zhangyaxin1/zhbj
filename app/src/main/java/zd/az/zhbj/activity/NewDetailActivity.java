package zd.az.zhbj.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Switch;

import zd.az.zhbj.R;

/**
 * Created by Administrator on 2016/7/6.
 */
public class NewDetailActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_detail);
        //获取打开自己
        initView();
        initData();
        initListeners();

    }

    private void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
//        String url = "http://192.168.1.131:8080/";

        Log.e("wzx", url);
        //根据地址加载网页数据
        mWebView.loadUrl(url);
    }


    private void initListeners() {
//返回按钮
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//字体修改
        findViewById(R.id.im_font).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //抽取代码成为方法:ctrl+alt+m
                showTextSizeDialog();

            }
        });
    }


    /**
     * 打开选中字体的对话框
     */
    private void showTextSizeDialog() {
        //弹出对话框
        //超大号字体
        //大号字体
        //正常字体
        //小号字体
        //超小号字体
        //对话框只能传Acvitity上下文其它上下文一律报错
        AlertDialog.Builder builder = new AlertDialog.Builder(NewDetailActivity.this);
        builder.setTitle("设置字号");
        String[] items = new String[]{
                "超大号字体",
                "大号字体",
                "正常字体",
                "小号字体",
                "超小号字体"
        };
//                builder.setSingleChoiceItems(items:选项文字,checkItem:默认打开位置,listener:点击监听器)
        builder.setSingleChoiceItems(items, 2, new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                    case 1:
                        mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 2:
                        mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 3:
                        mWebView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
                        break;
                    case 4:
                        mWebView.getSettings().setTextSize(WebSettings.TextSize.SMALLEST);
                        break;

                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //显示
        builder.show();
    }


    /**
     * 初始化新闻页面的控件
     */
    private void initView() {

        mWebView = (WebView) findViewById(R.id.webview);

        //WebSetting :webViewde配置参数
        WebSettings settings = mWebView.getSettings();
        //设置支持js
        settings.setJavaScriptEnabled(true);

        //设置缩放按钮
        settings.setBuiltInZoomControls(true);

        //设置双击缩放
        settings.setUseWideViewPort(true);

        //回调对象:提供处理方法的对象
        //WebViewClient:WebView的回调对象可以防止打开系统自带的浏览器
        //同时提供三个处理方法

        //1.网页开始加载
        //2.网页结束加载
        //3.重写加载地址

        mWebView.setWebViewClient(new WebViewClient() {
            /**
             * 加载前显示缓冲视图
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            }

            /**
             * 关闭缓冲视图
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            }

            //重写地址加载路径
            //针对跳转的地址而不是针对loadurl的路径
           /* @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

//                view.loadUrl("http://192.168.1.131:8080/");
                return true;//重写了地址
            }*/
        });


        //回调对象 用来获取加载中的进度变化
        mWebView.setWebChromeClient(new WebChromeClient() {
            /**
             * 进度加载方法
             * @param view
             * @param newProgress 加载中的进度百分比
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                ProgressBar percent = (ProgressBar) findViewById(R.id.progressBar_percent);
                if (newProgress == 100) {
                    percent.setVisibility(View.GONE);
                } else {
                    percent.setVisibility(View.VISIBLE);
                    percent.setProgress(newProgress);
                }

            }
        });

    }


}

