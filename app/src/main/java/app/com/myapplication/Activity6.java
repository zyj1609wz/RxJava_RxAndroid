package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 *  Created by ${zyj} on 2016/2/15.
 *  Observable.from()这个方法可以获得一组数据，并且每次发出他们中的一个。
 *  注意 flatMap() 方法其实就是把  Observable<List<String>> 转化成一个个 Observable<String>
 */
public class Activity6 extends Activity {

    private List<String> list = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.add( "hello 1" ) ;
        list.add( "hello 2" ) ;
        list.add( "hello 3" ) ;
        list.add("hello 4") ;

        //创建一个事件源对象
        //注意 flatMap() 方法其实就是把  Observable<List<String>> 转化成一个个 Observable<String>
         query().flatMap(new Func1<List<String>, Observable<String>>() {
             @Override
             public Observable<String> call(List<String> strings) {
                 return Observable.from(strings);
             }
         }).subscribe( onNextAction );


        //或者这样的
        query().flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from( strings );
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return  Observable.just( s + "哈哈哈" ) ;
            }
        }).subscribe( onNextAction ) ;

        //这样也是可以的
        query().subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                Toast.makeText(Activity6.this, "" + strings.size(), Toast.LENGTH_SHORT).show();
            }
        }) ;

        //或者这样也是可以的
        Observable.from( list ).subscribe( onNextAction ) ;

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
