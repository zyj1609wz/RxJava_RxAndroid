package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class StartWithActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_with);

        //插入普通数据
        //startWith 数据序列的开头插入一条指定的项 , 最多插入9条数据
        Observable observable = Observable.just( "aa" , "bb" , "cc" ) ;
        observable
                .startWith( "11" , "22" )
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println( "startWith-- " + o );
                    }
                }) ;

        //插入Observable对象
        List<String> list = new ArrayList<>() ;
        list.add( "ww" ) ;
        list.add( "tt" ) ;
        observable.startWith( Observable.from( list ))
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println( "startWith2 -- " + o );
                    }
                }) ;
    }
}
