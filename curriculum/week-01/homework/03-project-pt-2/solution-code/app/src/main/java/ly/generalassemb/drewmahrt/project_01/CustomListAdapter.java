package ly.generalassemb.drewmahrt.project_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Drew on 11/23/15.
 */
public class CustomListAdapter extends ArrayAdapter{
    private Context mContext;
    private ArrayList<ToDoList> mList;

    public CustomListAdapter(Context context, ArrayList<ToDoList> list) {
        super(context, android.R.layout.simple_list_item_1, list);

        mContext = context;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        else {
            view = convertView;
        }

        TextView itemTitleView = (TextView) view.findViewById(android.R.id.text1);

        itemTitleView.setText( mList.get(position).getTitle());

        return view;
    }
}
