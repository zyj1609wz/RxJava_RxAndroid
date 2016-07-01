package app.com.myapplication;
import android.app.Activity;
import android.os.Bundle;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ${zyj} on 2016/2/15.
 */
public class Activity1 extends Activity {

    String TAG = "Activity1-->" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册事件
        myObservable.subscribe( mySubscriber ) ;

        //一旦mySubscriber订阅了myObservable，
        // myObservable就是调用mySubscriber对象的onNext和onComplete方法，mySubscriber就会打印出Hello World！
    }

    //创建一个事件源对象
    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, world! 1");
                    sub.onNext("Hello, world! 2");
                    sub.onCompleted();
                }
            }


    );

    //创建一个观察者对象
    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            System.out.println( TAG + s);
        }

        @Override
        public void onCompleted() { }
        @Override
        public void onError(Throwable e) { }
    };
}
