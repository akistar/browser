package dantong.browser;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class BrowserApplication extends Application {


    private List<String> histories = new ArrayList<String>();
    private List<String> bookmarks = new ArrayList<String>();

    private String currentUrl="";

    public BrowserApplication(){
        initialBookmark();
        initialHistories();
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public List<String> getHistories() {
        return histories;
    }

    public void setHistories(List<String> histories) {
        this.histories = histories;
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public void addBookmark(String bookmark){
        bookmarks.add(bookmark);
    }

    public void deleteBookmark(String bookmark){
        bookmarks.remove(bookmark);
    }
    public void addHistory(String history){
        histories.add(history);
    }

    public void deleteAllHistory(){
        this.histories = new ArrayList<String>();
    }
    public void initialBookmark(){
        bookmarks.add("https://www.google.com");
        bookmarks.add("https://www.ecs.vuw.ac.nz");
        bookmarks.add("https://trademe.nz");
        bookmarks.add("https://www.facebook.com");
    }


    public void initialHistories(){
        histories.add("https://www.google.com");
        histories.add("https://www.ecs.vuw.ac.nz");
        histories.add("https://trademe.nz");
    }

}
