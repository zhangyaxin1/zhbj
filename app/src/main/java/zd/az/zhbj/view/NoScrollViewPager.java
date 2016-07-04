package zd.az.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 修改掉原有的viewPager的滑动切换效果
 * Created by Administrator on 2016/6/28.
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *
     * @param ev
     * @return 作为dispatcheTouchEvent()的条件
     * true表示拦截掉事件 不予下发 子控件就不能调用onTouchEvent
     * false表示保持下发
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//完成事件的下发
    }

    //触摸事件,禁止掉
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;//消费事件不回传
    }
}

