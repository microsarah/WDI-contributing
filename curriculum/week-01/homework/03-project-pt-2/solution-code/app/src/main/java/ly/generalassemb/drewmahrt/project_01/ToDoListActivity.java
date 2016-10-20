package ly.generalassemb.drewmahrt.project_01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {
    private ToDoList toDoList;
    private ArrayList<String> mItemList;
    private ArrayAdapter<String> mItemListAdapter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_new_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        toDoList = getIntent().getParcelableExtra("toDoList");

        //Initialize to do lists array if it is empty
        if(toDoList != null){
            toolbar.setTitle(toDoList.getTitle());
            mItemList = toDoList.getToDoItems();
        }else{
            mItemList = new ArrayList<>();
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mItemListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mItemList);
        ListView listView = (ListView)findViewById(R.id.items_list_view);
        TextView emptyItemTextView = (TextView)findViewById(R.id.empty_item_text_view);
        listView.setAdapter(mItemListAdapter);
        listView.setEmptyView(emptyItemTextView);
    }

    public void showInputDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.input_text_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editText = (EditText) dialogView.findViewById(R.id.title_text);

        dialogBuilder.setTitle("To-Do Title");
        dialogBuilder.setMessage("Enter to-do title below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(editText.getText().toString().length() == 0) {
                    Toast.makeText(mContext, "Please enter an item title", Toast.LENGTH_LONG).show();
                } else {
                    mItemList.add(editText.getText().toString());
                    mItemListAdapter.notifyDataSetChanged();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent intent = getIntent();
            toDoList.setToDoItems(mItemList);intent.putExtra("modifiedToDoList", toDoList);
            this.setResult(RESULT_OK, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        toDoList.setToDoItems(mItemList);intent.putExtra("modifiedToDoList", toDoList);
        this.setResult(RESULT_OK, intent);
        finish();
    }
}
