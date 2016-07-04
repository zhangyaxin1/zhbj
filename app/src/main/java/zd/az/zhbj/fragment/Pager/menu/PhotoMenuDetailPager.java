package zd.az.zhbj.fragment.Pager.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 组图四级页面
 * Created by Administrator on 2016/6/30.
 */
public class PhotoMenuDetailPager extends BaseMenuDetailPager{


    /*
    构造方法
     */
    public PhotoMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initview() {


        TextView contentView = new TextView(mActivity);
        contentView.setText("四级页面:组图");
        contentView.setBackgroundColor(Color.BLUE);
        contentView.setTextColor(Color.RED);
        contentView.setGravity(Gravity.CENTER);
        return contentView;
    }
}
