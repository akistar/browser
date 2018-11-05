package dantong.browser;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settingcontroller {

    private Activity myActivity;

    public Settingcontroller(Activity activity){
        myActivity = activity;
        myActivity.setContentView(R.layout.setting_page);

        Button deleteBtn = (Button)myActivity.findViewById(R.id.setting_delete_history);
        final BrowserApplication browserApplication = (BrowserApplication)myActivity.getApplicationContext();


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserApplication.deleteAllHistory();

                Toast.makeText(myActivity, "histories delete successfully", Toast.LENGTH_LONG).show();


            }
        });

        final TextView backBtn = (TextView)myActivity.findViewById(R.id.setting_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(browserApplication.getCurrentUrl().equals("")) {
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
