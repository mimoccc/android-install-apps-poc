package me.tombailey.installappspoc;

import android.app.Application;

import com.karumi.dexter.Dexter;

/**
 * Created by Tom on 18/09/2016.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Dexter.initialize(this);
    }
}
