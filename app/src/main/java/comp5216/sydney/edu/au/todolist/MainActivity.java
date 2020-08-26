package comp5216.sydney.edu.au.todolist;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Define variables
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    EditText addItemEditText;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use "activity_main.xml" as the layout
        setContentView(R.layout.activity_main);

        // Reference the "listView" variable to the id "lstView" in the layout
        listView = (ListView) findViewById(R.id.lstView);
        addItemEditText = (EditText) findViewById(R.id.txtNewItem);

        // Create an ArrayList of String
        items = new ArrayList<String>();
        items.add("item one");
        items.add("item two");

        // Create an adapter for the list view using Android's built-in item layout
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        // Connect the listView and the adapter
        listView.setAdapter(itemsAdapter);
    }

    public void onAddItemClick(View view) {
        String toAddString = addItemEditText.getText().toString();
        if (toAddString.length() > 0) {
            itemsAdapter.add(toAddString); // Add text to list view adapter
            addItemEditText.setText("");
        }
    }

}