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
public class GovAffarisPager extends BasePager{


    public GovAffarisPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentView) {

        mTvTitle.setText("政务");
        mIVMenu.setVisibility(View.VISIBLE);
        TextView view = new TextView(mActivity);
        view.setText("三级页面-政务");
        view.setTextSize(22);
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        contentView.addView(view);
    }


}

