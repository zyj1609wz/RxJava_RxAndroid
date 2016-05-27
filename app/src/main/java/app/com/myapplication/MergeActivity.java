package app.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class MergeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);

        List<String> list1 = new ArrayList<>() ;
        List<String> list2 = new ArrayList<>() ;

        list1.add( "1" ) ;
        list1.add( "2" ) ;
        list1.add( "3" ) ;

        list2.add( "a" ) ;
        list2.add( "b" ) ;
        list2.add( "c" ) ;
        list2.add( "d" ) ;

        Observable observable1 = Observable.from( list1 ) ;
        Observable observable2 = Observable.from( list2 ) ;

        //合并数据  先发送observable2的全部数据，然后发送 observable1的全部数据
        Observable observable = Observable.merge( observable2 , observable1 ) ;

        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
             System.out.println( "rx-- " + o );
            }
        }) ;


        /////////////////////////////////////////////////////////////////////////////////
       //zip操作符的使用

       Observable observable3 =  Observable.zip(observable1, observable2, new Func2<String , String , String >() {
            @Override
            public String call(String s1 , String s2 ) {
                return s1 + s2  ;
            }
        }) ;

        observable3.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println( "zip-- " + o );
            }
        }) ;

    }
}
