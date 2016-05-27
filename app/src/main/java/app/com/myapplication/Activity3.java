package app.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ${zyj} on 2016/2/15.
 *  map操作符
 */
public class Activity3 extends Activity {

    String TAG = "Activity3-->" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册事件  一map操作符，就是用来把个事件转换为另一个事件的。
        //map()操作符就是用于变换Observable对象的，map操作符返回一个Observable对象，
        // 这样就可以实现链式调用，在一个Observable对象上多次使用map操作符，最终将最简洁的数据传递给Subscriber对象。
        myObservable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "哈哈";
            }
        }).subscribe( onNextAction ) ;


        //或者
        myObservable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "哈哈";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println( TAG + s );
            }
        }) ;


        //或者
        Observable.just( "测试" ).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "哈哈";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println( TAG + s );
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
            System.out.println( TAG + s );
        }
    };
}
