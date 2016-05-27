package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ${zyj} on 2016/2/15.
 * Activity5 Observable的from 方法接收一个集合数据 和 map 方法混用
 */
public class Activity5 extends Activity {

    private List<String> list = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.add( "hello 1" ) ;
        list.add( "hello 2" ) ;
        list.add( "hello 3" ) ;
        list.add( "hello 4" ) ;

        //创建一个事件源对象 （简化版）
        Observable observable = Observable.from( list ) ;
        observable.subscribe( onNextAction ) ;


        //map 的用法
        Observable.from( list ).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "from " + s ;
            }
        }).subscribe( onNextAction ) ;

    }

    //创建一个观察者对象  （简化版）  简化Subscriber
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println(s);
        }
    };
}
