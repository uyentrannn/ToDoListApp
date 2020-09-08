package comp5216.sydney.edu.au.todolist;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.time.LocalTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Define variables
    ListView listView;
    ListView timeView;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> itemsTime;
    ArrayAdapter<String> itemsTimeAdapter;
    EditText addItemEditText;

    private void setupListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long rowId) {
                Log.i("MainActivity", "Long Clicked item " + position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_delete_title)
                        .setMessage(R.string.dialog_delete_msg)
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                items.remove(position); // Remove item from the Arraylist
                                itemsTime.remove(position);
                                itemsAdapter.notifyDataSetChanged(); // Notify listView adapter to update the list
                                itemsTimeAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User cancelled the dialog
                                // Nothing happens
                            }
                        });

                builder.create().show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String updateItem = (String) itemsAdapter.getItem(position);
                Log.i("MainActivity", "Clicked item " + position + ": " + updateItem);
            }
        });
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use "activity_main.xml" as the layout
        setContentView(R.layout.activity_main);

        // Reference the "listView" variable to the id "lstView" in the layout
        listView = (ListView) findViewById(R.id.listView);
        addItemEditText = (EditText) findViewById(R.id.txtNewItem);
        timeView = (ListView) findViewById(R.id.timeView);

        // Create an ArrayList of String
        items = new ArrayList<String>();
        itemsTime = new ArrayList<String>();

        // Create an adapter for the list view using Android's built-in item layout
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsTimeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsTime);

        // Connect the listView and the adapter
        listView.setAdapter(itemsAdapter);
        timeView.setAdapter(itemsTimeAdapter);

        // Setup listView listeners
        setupListViewListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onAddItemClick(View view) {
        String toAddString = addItemEditText.getText().toString();
        if (toAddString.length() > 0) {
            LocalTime localTime = java.time.LocalTime.now();
            String stringDate = localTime.toString();
            itemsAdapter.add(toAddString); // Add text to list view adapter
            itemsTimeAdapter.add(stringDate); // Add text to list view adapter
            addItemEditText.setText("");
        }
    }

}