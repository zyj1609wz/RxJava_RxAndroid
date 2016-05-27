package app.com.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class IntervalActivity extends AppCompatActivity {

    Subscription subscription ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        //参数一：延迟时间  参数二：间隔时间  参数三：时间颗粒度
        Observable observable =  Observable.interval(3000, 3000, TimeUnit.MILLISECONDS) ;
        subscription = observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println( "interval-  " + o );
            }
        })  ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( subscription != null ){
            subscription.unsubscribe();
        }
    }
}
