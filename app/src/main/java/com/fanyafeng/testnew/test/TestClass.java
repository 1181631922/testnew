package com.fanyafeng.testnew.test;

import android.database.CursorJoiner;
import android.text.Html;
import android.text.Spanned;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by fanyafeng on 2015/12/6,0006.
 */
public class TestClass {
    public static void main(String a[]) {
        String s = "祈福开运,祈福求愿,今天周记";
        dealTag(s);

//        TestSet01();
//        TestSet02();
//        TestSet03();
//        join();
    }

    public static String dealTag(String str) {
        String[] array = str.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(" [ " + s + " ]");
        }
        return sb.toString();
    }

    public static void join() {
//        CursorJoiner
//        do {
//            int i = 0;
//            i++;
//        } while (true);
        String name="test";
        int pos=0;
        String sex="famel";
        int position=1;
        System.out.print(MessageFormat.format("测试占位符{0},{1}",name,sex,pos,position));
        System.out.print(String.format("测试占位符%s",name));
    }

    //set都可以去重
//    如果对bean进行排序的话需要
    public static void TestSet01() {
        System.out.println("--------");
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            stringSet.add(i + "");
            stringSet.add("6");
        }
        for (String string : stringSet) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    public static void TestSet02() {
//        不能排序
        System.out.println("--------");
        Set<String> stringSet = new LinkedHashSet<>();
        for (int i = 0; i < 5; i++) {
            stringSet.add(i + "");
            stringSet.add("6");
        }
        for (String string : stringSet) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    public static void TestSet03() {
        System.out.println("--------");
        Set<String> stringSet = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            stringSet.add(i + "");
            stringSet.add("6");
        }
        for (String string : stringSet) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    /**
     * class ComparatorUser implements Comparator {

     public int compare(Object arg0, Object arg1) {
     User user0 = (User) arg0;
     User user1 = (User) arg1;
     // 首先比较年龄，如果年龄相同，则比较名字
     int flag = user0.getAge().compareTo(user1.getAge());
     if (flag == 0) {
     return user0.getName().compareTo(user1.getName());
     } else {
     return flag;
     }
     }

     }

     public class SortTest {

     public static void main(String[] args) {
     List userlist = new ArrayList();
     userlist.add(new User("dd", "4"));
     userlist.add(new User("aa", "1"));
     userlist.add(new User("ee", "5"));
     userlist.add(new User("bb", "2"));
     userlist.add(new User("ff", "5"));
     userlist.add(new User("cc", "3"));
     userlist.add(new User("gg", "6"));

     ComparatorUser comparator = new ComparatorUser();
     Collections.sort(userlist, comparator);

     for (int i = 0; i < userlist.size(); i++) {
     User user_temp = (User) userlist.get(i);
     System.out.println(user_temp.getAge() + "," + user_temp.getName());
     }

     }
     }
     复制代码
     */

}
