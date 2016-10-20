package ly.generalassemb.drewmahrt.project_01;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Drew on 11/23/15.
 */

public class ToDoList implements Parcelable {
    private String mTitle;
    private ArrayList<String> mToDoItems = new ArrayList<>();

    public ToDoList(String title){
        mTitle = title;
        //mToDoItems = new ArrayList<String>();
    }

    public ToDoList(Parcel in){
        mTitle = in.readString();
        in.readStringList(mToDoItems);
        //mToDoItems = new ArrayList<String>();
    }

    public static final Creator<ToDoList> CREATOR = new Creator<ToDoList>() {
        @Override
        public ToDoList createFromParcel(Parcel in) {
            return new ToDoList(in);
        }

        @Override
        public ToDoList[] newArray(int size) {
            return new ToDoList[size];
        }
    };

    public void addItem(String itemTitle){
        mToDoItems.add(itemTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeStringList(mToDoItems);
    }

    @Override
    public String toString(){
        return mTitle;
    }

    public String getTitle(){
        return mTitle;
    }

    public ArrayList<String> getToDoItems(){
        return mToDoItems;
    }

    public void setToDoItems(ArrayList<String> items){
        mToDoItems = items;
    }
}
