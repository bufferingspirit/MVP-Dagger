package com.example.admin.mvp_dagger.view.mainactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Admin on 8/21/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{

    public static final String TAG = "MainActivityContract";
    MainActivityContract.View view;
    Context context;

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void savePerson(String person) {

        Log.d(TAG, "savePerson: " + person);
        SharedPreferences sharedPref = context.getSharedPreferences("myPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(person, person);
        editor.commit();
        view.isPersonSaved(true);
    }

    @Override
    public String getPerson(String person) {
        SharedPreferences sharedPref = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String out = sharedPref.getString(person, null);
        //you can do this if you want the view updated after this function executes
        view.sendPerson(out);
        return out;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
