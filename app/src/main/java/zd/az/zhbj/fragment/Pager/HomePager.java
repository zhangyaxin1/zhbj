package zd.az.zhbj.fragment.Pager;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/27.
 */
public class HomePager extends BasePager{
    public HomePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentView) {
        mTvTitle.setText("首页");
        TextView view = new TextView(mActivity);
        view.setText("三级页面-首页");
        view.setTextSize(22);
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        contentView.addView(view);
    }
}
