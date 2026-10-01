package com.rambabu.pollutioncomplaint;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Home screen of the Pollution Complaint App.
 *
 * Lists the complaints the citizen has filed (stored in SQLite) and
 * offers a button to file a new complaint.
 */
public class MainActivity extends AppCompatActivity {

    private ComplaintDbHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private final List<String> rows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ComplaintDbHelper(this);

        ListView complaintsList = findViewById(R.id.complaintsList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rows);
        complaintsList.setAdapter(adapter);

        Button btnFileComplaint = findViewById(R.id.btnFileComplaint);
        btnFileComplaint.setOnClickListener(v ->
                startActivity(new Intent(this,
                        FileComplaintActivity.class)));

        refreshList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    /** Reloads complaints from SQLite and shows them newest-first. */
    private void refreshList() {
        rows.clear();
        List<Complaint> complaints = dbHelper.getAllComplaints();
        if (complaints.isEmpty()) {
            rows.add("No complaints filed yet. Tap 'File Complaint' "
                    + "to report pollution near you.");
        } else {
            for (Complaint c : complaints) {
                rows.add(c.getType() + " — " + c.getStatus()
                        + "\n" + c.getDescription());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
