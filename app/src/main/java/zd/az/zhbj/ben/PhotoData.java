package zd.az.zhbj.ben;

import java.util.ArrayList;
import java.util.List;

/**
 * 组图页面数据
 */
public class PhotoData {

    public PhotoListData data;
    public int retcode;

    @Override
    public String toString() {
        return "PhotoData{" +
                "data=" + data +
                ", retcode=" + retcode +
                '}';
    }


    /**
     * 组图列表数据
     */
    public static class PhotoListData {

        public String countcommenturl;
        public String more;
        public List<PhotoItem> news = new ArrayList<PhotoItem>();
        public String title;
        public List<String> topic = new ArrayList<String>();

        @Override
        public String toString() {
            return "PhotoListData{" +
                    "countcommenturl='" + countcommenturl + '\'' +
                    ", more='" + more + '\'' +
                    ", news=" + news +
                    ", title='" + title + '\'' +
                    ", topic=" + topic +
                    '}';
        }
    }

    /**
     * 一条组图记录
     */
    public static class PhotoItem {
        public boolean comment;
        public String commentlist;
        public String commenturl;
        public int id;
        public String largeimage;
        public String listimage;
        public String pubdate;
        public String smallimage;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "PhotoItem{" +
                    "comment=" + comment +
                    ", commentlist='" + commentlist + '\'' +
                    ", commenturl='" + commenturl + '\'' +
                    ", id=" + id +
                    ", largeimage='" + largeimage + '\'' +
                    ", listimage='" + listimage + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", smallimage='" + smallimage + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
