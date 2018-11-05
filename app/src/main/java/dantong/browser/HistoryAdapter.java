package dantong.browser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private List<String> urls;

    private LayoutInflater inflater;

    public HistoryAdapter(Activity activity, List<String> urls) {
        super();
        this.urls = urls;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return urls.size();
    }

    public Object getItem(int position) {
        return urls.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView itemText;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.history_item, null);
            itemText = (TextView) convertView.findViewById(R.id.history_item);
            convertView.setTag(itemText);
        } else {
            itemText = (TextView) convertView.getTag();
        }

        String url = (String) urls.get(position);
        itemText.setText(url);
        return convertView;
    }
}