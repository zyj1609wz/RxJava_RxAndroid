package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 *  Created by ${zyj} on 2016/2/15.
 *  filter() 过滤
 *  返回true ,代表保留  。  返回false 代表过滤掉
 *  就是返回我们想要的数据
 */
public class Activity7 extends Activity {

    private List<String> list = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.add("hello 1") ;
        list.add( "hello 2" ) ;
        list.add( "hello 3" ) ;
        list.add( null ) ;
        list.add("hello 4") ;

        //创建一个事件源对象
        //注意 flatMap() 方法其实就是把  Observable<List<String>> 转化成一个个 Observable<String>
        query().flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from( strings );
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return  s != null  ;  //返回true ,代表保留  。  返回false 代表过滤掉
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s + "哈哈哈");
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

    /**
     * 创建一个事件源
     * @return
     */
    public Observable<List<String>> query( ) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext( list );
                subscriber.onCompleted();
            }
        }) ;

    }
}
