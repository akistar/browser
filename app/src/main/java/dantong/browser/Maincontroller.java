package dantong.browser;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Maincontroller {
    private Activity myActivity;
    private List<String> bookmarks = new ArrayList<String>();
    private List<String> histories = new ArrayList<String>();



    public Maincontroller(Activity activity){

        BrowserApplication browserApplication = (BrowserApplication)activity.getApplicationContext();
        this.myActivity = activity;
        this.bookmarks = browserApplication.getBookmarks();
        this.histories = browserApplication.getHistories();

    }

    public void setUp(){
        myActivity.setContentView(R.layout.activity_main);

        ImageButton goBtn = (ImageButton)myActivity.findViewById(R.id.main_button_go);
        ImageView googleBtn = (ImageView)myActivity.findViewById(R.id.main_google);
        ImageView facebookBtn = (ImageView)myActivity.findViewById(R.id.main_facebook);
        ImageView githubBtn = (ImageView)myActivity.findViewById(R.id.main_github);
        ImageView trademeBtn = (ImageView)myActivity.findViewById(R.id.main_trademe);
        ImageView bookmarkBtn = (ImageView)myActivity.findViewById(R.id.main_bookmark);
        ImageView historyBtn = (ImageView)myActivity.findViewById(R.id.main_history);

        final EditText urlInput = (EditText)myActivity.findViewById(R.id.main_input_url);


        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString();
                Webcontroller webcontroller = new Webcontroller(myActivity,url);
                Log.d("goBtn","clicked");
                webcontroller.goToUrl();
            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Webcontroller webcontroller = new Webcontroller(myActivity,"https://www.google.com");
                webcontroller.goToUrl();

            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Webcontroller webcontroller = new Webcontroller(myActivity,"https://www.facebook.com");
                webcontroller.goToUrl();
            }
        });

        githubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Webcontroller webcontroller = new Webcontroller(myActivity,"https://www.github.com");
                webcontroller.goToUrl();
            }
        });

        trademeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Webcontroller webcontroller = new Webcontroller(myActivity,"https://trademe.nz");
                webcontroller.goToUrl();
            }
        });

        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookmarkcontroller bookmarkcontroller = new Bookmarkcontroller(myActivity);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Historycontroller historycontroller = new Historycontroller(myActivity);
            }
        });

    }
}
