package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 专题四级页面
 * Created by Administrator on 2016/6/30.
 */
public class TopicMenuDetailPager extends BaseMenuDetailPager{


    /*
    构造方法
     */
    public TopicMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initview() {


        TextView contentView = new TextView(mActivity);
        contentView.setText("四级页面:专题");
        contentView.setBackgroundColor(Color.BLUE);
        contentView.setTextColor(Color.RED);
        contentView.setGravity(Gravity.CENTER);
        return contentView;
    }
}
