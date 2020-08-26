package comp5216.sydney.edu.au.todolist;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Define variables
    ListView listView;
    ListView timeView;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> itemsTime;
    ArrayAdapter<String> itemsTimeAdapter;
    EditText addItemEditText;

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
//        items.add("item one");
//        items.add("item two");

        // Create an adapter for the list view using Android's built-in item layout
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsTimeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsTime);

        // Connect the listView and the adapter
        listView.setAdapter(itemsAdapter);
        timeView.setAdapter(itemsTimeAdapter);
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