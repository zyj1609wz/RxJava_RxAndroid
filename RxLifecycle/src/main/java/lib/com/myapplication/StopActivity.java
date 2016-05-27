package lib.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class StopActivity extends RxAppCompatActivity {

    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        textView = (TextView) findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( StopActivity.this , OtherActivity.class));
            }
        });

        //循环发送数字
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn( Schedulers.io())
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP ))   //当Activity执行Onstop()方法是解除订阅关系
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("lifecycle-stop-" + aLong);
                        textView.setText( "" + aLong );
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("lifecycle-stop-"  + "onPause()方法调用了");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("lifecycle-stop-"  + "onStop()方法调用了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("lifecycle-stop-"  + "onDestroy()方法调用了");
    }
}
