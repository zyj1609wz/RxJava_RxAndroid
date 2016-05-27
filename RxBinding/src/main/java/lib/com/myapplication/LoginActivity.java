package lib.com.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import rx.functions.Action1;

public class LoginActivity extends AppCompatActivity {

    private Button button ;
    private CheckBox checkBox ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById( R.id.login_bt );
        checkBox = (CheckBox) findViewById( R.id.checkbox );

        RxCompoundButton.checkedChanges( checkBox )
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        button.setEnabled( aBoolean );
                        button.setBackgroundResource( aBoolean ? R.color.button_yes : R.color.button_no );
                    }
                }) ;

    }
}
