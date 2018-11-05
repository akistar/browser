package dantong.browser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Webcontroller {
    private Activity myActivity;
    private String myUrl;
    private EditText urlInput;
    private WebView myWebView;
    private ImageButton renewBtn;
    private ImageButton tagBtn;
    private TextView backBtn;
    private TextView forwardBtn;
    private ImageView mainBtn;
    private ImageView settingBtn;
    private List<String> bookmarks = new ArrayList<String>();

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url){
            super.onPageFinished(view, url);
            Log.d("onPagesFinshed","finish");

            if(view.canGoBack()){
                Log.d("can go back","can go back");
                backBtn.setTextColor(myActivity.getResources().getColor(R.color.button_active));
            }else {
                backBtn.setTextColor(myActivity.getResources().getColor(R.color.button_initial));
            }
            if(view.canGoForward()){
                Log.d("can go forward","can go forward");
                forwardBtn.setTextColor(myActivity.getResources().getColor(R.color.button_active));
            }else {
                forwardBtn.setTextColor(myActivity.getResources().getColor(R.color.button_initial));
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url){

            myUrl = url;
            urlInput.setText(url);


            if(bookmarks.contains(myUrl)){
                tagBtn.setImageResource(android.R.drawable.btn_star_big_on);
            }else {
                tagBtn.setImageResource(android.R.drawable.btn_star_big_off);
            }

            BrowserApplication browserApplication = (BrowserApplication)myActivity.getApplicationContext();
            browserApplication.addHistory(myUrl);
            browserApplication.setCurrentUrl(myUrl);

            return false;
        }
    }


    public Webcontroller(Activity activity,String url){
        this.myActivity = activity;
        this.myUrl = url;
        myActivity.setContentView(R.layout.web_page);
        this.urlInput = (EditText) myActivity.findViewById(R.id.web_input_url);


        this.renewBtn = (ImageButton) myActivity.findViewById(R.id.web_button_renew);
        this.tagBtn = (ImageButton) myActivity.findViewById(R.id.web_button_tag);
        this.backBtn = (TextView) myActivity.findViewById(R.id.back);
        this.forwardBtn = (TextView) myActivity.findViewById(R.id.forward);
        this.mainBtn = (ImageView) myActivity.findViewById(R.id.main);
        this.settingBtn = (ImageView) myActivity.findViewById(R.id.setting);

        this.myWebView = (WebView) myActivity.findViewById(R.id.web_view);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAppCacheEnabled(true);

        BrowserApplication browserApplication = (BrowserApplication)activity.getApplicationContext();
        browserApplication.setCurrentUrl(myUrl);
        this.bookmarks = browserApplication.getBookmarks();

        if(bookmarks.contains(myUrl)){
            tagBtn.setImageResource(android.R.drawable.btn_star_big_on);
        }else {
            tagBtn.setImageResource(android.R.drawable.btn_star_big_off);
        }

        this.setUpButtons();


    }

    public void goToUrl(){
        urlInput.setText(myUrl,TextView.BufferType.EDITABLE);
        Log.d("webPage","current web");
        myWebView.loadUrl(myUrl);
    }

    public void setPopupMenu(View v){
        PopupMenu popup = new PopupMenu(myActivity, v);
        popup.inflate(R.menu.menu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_bookmark:
                        Log.d("bookmark","clicked");
                        Bookmarkcontroller bookmarkcontroller = new Bookmarkcontroller(myActivity);
                        return true;
                    case R.id.menu_history:
                        Log.d("history","clicked");
                        Historycontroller historycontroller = new Historycontroller(myActivity);
                        return true;
                    case R.id.menu_setting:
                        Log.d("setting","clicked");
                        Settingcontroller settingcontroller = new Settingcontroller(myActivity);
                        return true;
                }
                return false;
            }
        });

        popup.show();

    }


    public void setUpButtons(){
        renewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUrl = urlInput.getText().toString();
                goToUrl();
            }
        });

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Maincontroller maincontroller = new Maincontroller(myActivity);
                maincontroller.setUp();
            }
        });

        backBtn.setText("<");
        forwardBtn.setText(">");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.goBack();

            }
        });

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.goForward();
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopupMenu(v);
            }
        });


        tagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowserApplication browserApplication = (BrowserApplication)myActivity.getApplicationContext();

                if(bookmarks.contains(myUrl)){
                    tagBtn.setImageResource(android.R.drawable.btn_star_big_off);
                    browserApplication.deleteBookmark(myUrl);
                }else {
                    tagBtn.setImageResource(android.R.drawable.btn_star_big_on);
                    browserApplication.addBookmark(myUrl);
                }
            }
        });
    }
}

