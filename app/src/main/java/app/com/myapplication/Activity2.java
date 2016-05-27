package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ${zyj} on 2016/2/15.
 * 简化版操作
 */
public class Activity2 extends Activity {

    String TAG = "Activity2-->" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册事件
       // myObservable.subscribe(onNextAction);

        //或者

        myObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        }) ;

    }

    //创建一个事件源对象 （简化版）
    // Observable.just就是用来创建只发出一个事件就结束的Observable对象
    Observable<String> myObservable = Observable.just("Hello, world!");

    //创建一个观察者对象  （简化版）  简化Subscriber
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println( TAG + s);
        }
    };
}
