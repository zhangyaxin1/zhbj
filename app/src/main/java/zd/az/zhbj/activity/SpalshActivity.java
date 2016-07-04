package zd.az.zhbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import zd.az.zhbj.MainActivity;
import zd.az.zhbj.R;
import zd.az.zhbj.utils.Cache_utils;

/**闪屏界面 control
 * Created by Administrator on 2016/6/21.
 * 开发一个activity时必须要有一个activity标签对应的配置
 */
public class SpalshActivity extends Activity {

private AnimationSet  animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initview();
        initListeners();
    }


    /**
     * 初始化界面控件
     */

    private void initview() {


//
     RelativeLayout rl_root = (RelativeLayout) findViewById(R.id.rl_root);

//        动画 Animation
//        AnimationSet:动画集合 同时播放动画
//        new  AnimationSet(shareInterpolator:加速);参数设置为true就设置加速
  animationSet  = new  AnimationSet(true);

//        旋转   RotateAnimation
//new RotateAnimation(fromDegrees,toDegrees(开始角度,结束角度),pivot(中心)XType(参数设置为以自己为中心),pivotXValue(倍数),pivotYType(参数设置为以自己为中心),pivotYValue(倍数));
  RotateAnimation  rotateAnimation =    new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);//设置时间为5秒
        rotateAnimation.setFillAfter(true);//保留动画结束状态


//        透明动画   AlphaAnimation
//        new   AlphaAnimation(fromAlpha:开始透明度,toAlpha:结束透明度);
        AlphaAnimation alphaAnimation =  new   AlphaAnimation(0.5f,1.0f);
        alphaAnimation.setDuration(1000);//设置时长
        alphaAnimation.setFillAfter(true);//保留动画结束状态


//        缩放   ScaleAnimation
//new ScaleAnimation(fromX,toX(x坐标),  fromY,toY(y坐标),pivot(中心)XType,pivotXValue(倍数),pivotYType,pivotYValue(倍数))
//RELATIVE_TO_SELF:1倍大小
    ScaleAnimation  scaleAnimation  = new ScaleAnimation(0,1f,0,1f,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
             scaleAnimation.setDuration(1000);//设置时长
        scaleAnimation.setFillAfter(true);//保留动画结束状态

        //把特效添加到动画里
        animationSet.addAnimation(alphaAnimation);//把透明度加到动画里
        animationSet.addAnimation(rotateAnimation);//把旋转加到动画里
        animationSet.addAnimation(scaleAnimation);//把缩放加到动画里

//        把动画添加到集合中
        rl_root.startAnimation(animationSet);

    }


     //初始页面的控件或者对象的监听器  获取事件 动画借宿的事件
    private void initListeners() {
 //动画监听器
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {


                Log.d("nkdsfu","结束动画_______________");
//                Intent:意图,1,打开界面,2,传值 Map,put get,3,广播
                //isFirstShow

                 String isFirstShow = Cache_utils.getString(SpalshActivity.this,"isFirstShow");

                Class<?> page="false".equals(isFirstShow)?Main2Activity.class:GuidActivity1.class;
            //打开页面
                Log.e( "onAnimationEnd: ",isFirstShow);
                startActivity(new Intent(SpalshActivity.this, page));
            finish();//关闭动画页面
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
