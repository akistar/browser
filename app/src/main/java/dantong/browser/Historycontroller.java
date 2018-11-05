package dantong.browser;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Historycontroller {

    private Activity myActivity;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Activity getMyActivity() {
        return myActivity;
    }

    public void setMyActivity(Activity myActivity) {
        this.myActivity = myActivity;
    }

    private List<String> urls;

    public Historycontroller(final Activity activity){
        this.myActivity=activity;
        myActivity.setContentView(R.layout.history_list_view);
        final BrowserApplication browserApplication = (BrowserApplication)activity.getApplicationContext();
        this.urls = browserApplication.getHistories();


        HistoryAdapter historyAdapter = new HistoryAdapter(myActivity,urls);

        final ListView listView = (ListView) myActivity.findViewById(R.id.history_list);

        listView.setAdapter(historyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String)listView.getItemAtPosition(position);
                Log.d("url in history",url);
                Webcontroller webcontroller = new Webcontroller(activity, url);
                webcontroller.goToUrl();
            }
        });

        final TextView backBtn = (TextView)myActivity.findViewById(R.id.history_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(browserApplication.getCurrentUrl().equals("")){
                    Maincontroller maincontroller = new Maincontroller(myActivity);
                    maincontroller.setUp();
                }else {
                    Webcontroller webcontroller = new Webcontroller(myActivity, browserApplication.getCurrentUrl());
                    webcontroller.goToUrl();
                }
            }
        });


    }
}
