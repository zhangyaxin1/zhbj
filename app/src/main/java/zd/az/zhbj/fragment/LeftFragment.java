package zd.az.zhbj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.PublicKey;

import zd.az.zhbj.R;

/**
 * Created by Administrator on 2016/6/24.
 */
public class LeftFragment extends BaseFragment {

    /**
     * 初始化片段的控件
     * @return
/*
     *//*

    public View initView() {
        View view  =View.inflate(mActivity, R.layout.fragment_left_menu,null);
        return view;
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView= inflater.inflate(R.layout.fragment_left_menu,container,false);//初始化控件
        return mRootView;
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
