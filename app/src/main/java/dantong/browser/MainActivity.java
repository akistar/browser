package dantong.browser;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<String> bookmarks = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //BrowserApplication browserApplication = (BrowserApplication)getApplicationContext();

        Maincontroller maincontroller = new Maincontroller(this);
        maincontroller.setUp();
    }

    @Override

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

    }

}
