package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 交互四级页面
 * Created by Administrator on 2016/6/30.
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager{


    /*
    构造方法
     */
    public InteractMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initview() {


        TextView contentView = new TextView(mActivity);
        contentView.setText("四级页面:交互");
        contentView.setBackgroundColor(Color.BLUE);
        contentView.setTextColor(Color.RED);
        contentView.setGravity(Gravity.CENTER);
        return contentView;
    }
}
