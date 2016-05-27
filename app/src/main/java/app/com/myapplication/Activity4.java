package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ${zyj} on 2016/2/15.
 * Observable的from 方法接收一个集合数据
 * Observable.from()这个方法可以获得一组数据，并且每次发出他们中的一个。
 */
public class Activity4 extends Activity {

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

    }

    //创建一个观察者对象  （简化版）  简化Subscriber
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println(s);
        }
    };
}
