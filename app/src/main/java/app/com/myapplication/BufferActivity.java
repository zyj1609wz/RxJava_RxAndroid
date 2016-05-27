package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;

public class BufferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);

        List<String> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add("" + i);
        }

        Observable<String> observable = Observable.from(list);
        observable
                .buffer(2)   //把每两个数据为一组打成一个包，然后发送
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        System.out.println( "buffer---------------" );
                        Observable.from( strings ).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println( "buffer data --" + s  );
                            }
                        }) ;
                    }
                });

        //第1、2 个数据打成一个数据包，跳过第三个数据 ； 第4、5个数据打成一个包，跳过第6个数据
        observable.buffer( 2 , 3 )  //把每两个数据为一组打成一个包，然后发送。第三个数据跳过去
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {

                        System.out.println( "buffer22---------------" );
                        Observable.from( strings ).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println( "buffer22 data --" + s  );
                            }
                        }) ;
                    }
                }) ;
    }
}

