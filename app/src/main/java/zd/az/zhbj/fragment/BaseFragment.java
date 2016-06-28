package zd.az.zhbj.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/24.
 */
public abstract class BaseFragment  extends Fragment {

    public Activity mActivity;

    public View mRootView;//当前页面的视图

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//保存上下文
//        Log.d("jhji", "+++++++++" + mActivity);
    }
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView();//初始化控件
        return mRootView;
    }
    protected abstract View initView();


}