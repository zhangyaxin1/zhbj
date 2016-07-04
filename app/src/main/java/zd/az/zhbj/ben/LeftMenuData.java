package zd.az.zhbj.ben;

import java.io.Serializable;
import java.util.List;

/**
 * 将json解析的数据赋值给当前左侧菜单
 * 类的变量与json的变量高度一致
 * Created by Administrator on 2016/6/29.
 */
public class LeftMenuData  implements Serializable {

    public  List<MenuData> data;
    public List<Integer> extend;
    public  int retcode;

    @Override
    public String toString() {
        return "LeftMenuData{" +
                "data=" + data +
                ", extend=" + extend +
                ", retcode=" + retcode +
                '}';
    }

    /**
     * 菜单数据
     */
    public  static class MenuData implements Serializable{
        public List<CategoryData> children;
        public int  id;
        public String  title;
        public  int  type;

        @Override
        public String toString() {
            return "MenuData{" +
                    "children=" + children +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    '}';
        }

        /**
         * 新闻频道
         */
        public static class CategoryData implements Serializable{
            public  int  id;
            public  String title;
            public int  type;
            public String url;

            @Override
            public String toString() {
                return "CategoryData{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }

}
