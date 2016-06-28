package zd.az.zhbj.fragment.Pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/27.
 */
public class NewsCenterPager extends BasePager{


    public NewsCenterPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentView) {

        mTvTitle.setText("新闻中心");
        mIVMenu.setVisibility(View.VISIBLE);
        TextView view = new TextView(mActivity);
        view.setText("三级页面-新闻中心");
        view.setTextSize(22);
        view.setTextColor(Color.RED);
        contentView.addView(view);
        view.setGravity(Gravity.CENTER);
    }


}
