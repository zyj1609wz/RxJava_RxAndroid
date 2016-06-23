package lib.com.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxBusActivity extends AppCompatActivity {

    String tag = "tag" ;
    Observable<String> ob ;
    private TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        tv = (TextView) findViewById( R.id.tv );

        //创建被观察者
        ob = RxBus.get().register( tag , String.class ) ;
        //订阅观察事件
        ob.subscribeOn( Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread() )
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv.setText( s );
                    }
                }) ;

        //发送内容
        RxBus.get().post(  tag , "我是内容,万能的Rxjava" );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        RxBus.get().unregister( tag , ob );
    }
}
