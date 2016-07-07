package zd.az.zhbj.ben;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/7/1.
 */
public class NewsListData {

    /**
     * 新闻列表页面的数据
     *  1.碰到{创建类
     *  2.碰到[创建集合
     *  3.类的变量要与json变量高度一致
     */

    public NewsData  data;
    public int  retcode;

    @Override
    public String toString() {
        return "NewsListData{" +
                "data=" + data +
                ", retcode=" + retcode +
                '}';
    }

    /**
     * 新闻数据
     */
    public class NewsData{
        public  String countcommenturl;
        public  String more;
        public String title;
        public List<NewItem> news =new ArrayList<>();
        public List<TopicItem> topic =new ArrayList<TopicItem>();
        public List<TopNewsItem> topnews =new ArrayList<TopNewsItem>();

        @Override
        public String toString() {
            return "NewsData{" +
                    "countcommenturl='" + countcommenturl + '\'' +
                    ", more='" + more + '\'' +
                    ", title='" + title + '\'' +
                    ", news=" + news +
                    ", topic=" + topic +
                    ", topnews=" + topnews +
                    '}';
        }
    }


    /**
     * 一条头条纪录
     */
    public  static  class NewItem{
        public boolean comment;
        public  String commentlist;
        public String commenturl;
        public  int id;
        public  String listimage;
        public  String pubdate;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "NewItem{" +
                    "comment=" + comment +
                    ", commentlist='" + commentlist + '\'' +
                    ", commenturl='" + commenturl + '\'' +
                    ", id=" + id +
                    ", listimage='" + listimage + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    /**
     * 一条主题纪录
     */
    public  static  class TopicItem {

        public String description;
        public int id;
        public String listimage;
        public int sort;
        public String title;
        public String url;

        @Override
        public String toString() {

            return "TopicItem{" +
                    "description='" + description + '\'' +
                    ", id=" + id +
                    ", listimage='" + listimage + '\'' +
                    ", sort=" + sort +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }



    }


    /**
     * 一条头条纪录
     */
    public  static  class TopNewsItem {


        public boolean comment;
        public  String commentlist;
        public String commenturl;
        public  int id;
        public  String pubdate;
        public String title;
        public String topimage;
        public String type;
        public String url;



        @Override
        public String toString() {
            return "TopNewsItem{" +
                    "comment=" + comment +
                    ", commentlist='" + commentlist + '\'' +
                    ", commenturl='" + commenturl + '\'' +
                    ", id=" + id +
                    ", pubdate='" + pubdate + '\'' +
                    ", title='" + title + '\'' +
                    ", topImage='" + topimage + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}

