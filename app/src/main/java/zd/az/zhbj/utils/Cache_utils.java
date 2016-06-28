package zd.az.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * 保存变量
 * Created by Administrator on 2016/6/23.
 */
public class Cache_utils {
    /**
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {

        //获取编辑器
        SharedPreferences sharedPreferences =  context.getSharedPreferences("config.xml", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        //提交
        editor.commit();
        Log.e("saveString: ",sharedPreferences.getString(key,"null"));
    }

    /**
     * 获取变量值
     * @param context
     *
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {

        //获取编辑器
        SharedPreferences sharedPreferences =  context.getSharedPreferences("config.xml", context.MODE_PRIVATE);
     return sharedPreferences.getString(key,"");
    }

}
