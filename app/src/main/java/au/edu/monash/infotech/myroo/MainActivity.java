package au.edu.monash.infotech.myroo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import au.edu.monash.infotech.myroo.presenter.RROPresenter;
import au.edu.monash.infotech.myroo.view.IRROView;

public class MainActivity extends AppCompatActivity implements IRROView, View.OnClickListener {

    private Context mContext;
    private RROPresenter rroPresenter;
    private Button btnStart;
    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        tvMsg = findViewById(R.id.tv_msg);
        rroPresenter = new RROPresenter(this);
    }

    @Override
    public void onSuccess(String msg) {
        tvMsg.setText(msg);
        Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail() {
        Toast.makeText(mContext, "Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                rroPresenter.request();
                break;
            default:
        }
    }
}
