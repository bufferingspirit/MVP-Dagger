package com.example.admin.mvp_dagger.view.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.mvp_dagger.R;
import com.example.admin.mvp_dagger.injection.mainactivity.DaggerMainActivityComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    public static final String TAG = "MainActivityTag";

    @Inject
    MainActivityPresenter presenter;

    TextView tvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.create().inject(this);

        tvPerson = (TextView) findViewById(R.id.tvPerson);

        presenter = new MainActivityPresenter();
        presenter.setContext(this);
        presenter.attachView(this);

    }

    public void doMagic(View view){

        switch (view.getId()){
            case R.id.btnSavePerson:
                presenter.savePerson("John Doe");
                break;
            case R.id.btnGetPerson:
                tvPerson.setText(presenter.getPerson("John Doe"));
                break;

        }

    }



    @Override
    public void showError(String s) {

    }

    @Override
    public void isPersonSaved(boolean isSaved) {

        Log.d(TAG, "isPersonSaved: " + isSaved);
    }

    @Override
    public void sendPerson(String person) {
        //presenter.getPerson();
        Log.d(TAG, "sendPerson: " + person);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.removeView();
    }
}
