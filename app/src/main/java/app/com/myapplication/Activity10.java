package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 *  Created by ${zyj} on 2016/2/15.
 *
 */
public class Activity10 extends Activity {

    private String TAG = "Activity10-->" ;
    private List<String> list = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.add( "hello 1" ) ;
        list.add( "hello 2" ) ;
        list.add( "hello 3" ) ;
        list.add( "hello 4" ) ;

        //创建一个事件源对象
       Subscription subscription =  Observable.from(list).subscribe(onNextAction) ;

        //取消两者的关系
        //RxJava处理取消订阅漂亮的地方在于它停止了链。如果你有一堆复合的操作链，不论当前执行的是什么代码，
        // 利用unsubscribe都将会终止链，其他工作都不需要做。
        subscription.unsubscribe();

    }

    //创建一个观察者对象  （简化版）  简化Subscriber
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
           out( s );
        }
    };

    void out( String s ){
        System.out.println( TAG +   s );
    }

}
