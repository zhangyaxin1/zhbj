package zd.az.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/7/6.
 */
/**
 * @author itheima
 *
 */
public class InnerViewPager extends ViewPager {

    /***
     * 构造方法 .xml
     *
     * @param context
     * @param attrs
     */
    public InnerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // getParent()就外层的ViewPager
        // getParent().requestDisallowInterceptTouchEvent(disallowIntercept);//
        // disallowIntercept true禁拦截成功 将父类的onInterceptTouchEvent强改成false
    }

    int startX;
    int startY;

    /***
     * 方法外局事件下发
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);// 外层的onInterceptTouchEvent
        // return false
        // 也是事件正常下发
        // 左右滑动
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 第一个点的坐标
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 收集新坐标
                // 第二个点的坐标
                int newX = (int) ev.getX();
                int newY = (int) ev.getY();

                int offX = newX - startX;
                int offY = newY - startY;

                // -4 ==> 4
                // 4==>4
                if (Math.abs(offX) > Math.abs(offY)) {
                    // 左右滑
                    // 保持
                    // 最后一张大图
                    int currentItem = getCurrentItem();// 获取当前页下标
                    //第一页往左拖动
                    if (currentItem == 0&&offX>0) {
                        // 第一页面
                        getParent().requestDisallowInterceptTouchEvent(false);// 外层的onInterceptTouchEvent
                    }
                    if (currentItem == (getAdapter().getCount() - 1)) {
                        getParent().requestDisallowInterceptTouchEvent(false);// 外层的onInterceptTouchEvent
                        // return
                        // true拦截
                    }
                } else {
                    // 上下滑动
                    getParent().requestDisallowInterceptTouchEvent(false); // 外层的onInterceptTouchEvent
                    // return
                    // true拦截
                }

                break;
        }

        return super.dispatchTouchEvent(ev);
    }
}
