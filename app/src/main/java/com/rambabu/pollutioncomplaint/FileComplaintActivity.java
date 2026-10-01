package com.rambabu.pollutioncomplaint;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * File Complaint screen.
 *
 * The citizen picks a pollution type, describes the problem, and
 * submits. The complaint is tagged with the device's GPS location and
 * saved to SQLite (offline-first).
 */
public class FileComplaintActivity extends AppCompatActivity {

    private Spinner typeSpinner;
    private EditText descriptionInput;
    private ComplaintDbHelper dbHelper;

    // TODO: wire to Android Location Services to capture real GPS.
    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_complaint);

        dbHelper = new ComplaintDbHelper(this);

        typeSpinner = findViewById(R.id.typeSpinner);
        descriptionInput = findViewById(R.id.descriptionInput);
        Button btnAttachPhoto = findViewById(R.id.btnAttachPhoto);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        ArrayAdapter<Complaint.Type> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                Complaint.Type.values());
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        btnAttachPhoto.setOnClickListener(v ->
                // TODO: launch camera intent and store the photo path.
                Toast.makeText(this, "Photo capture coming soon",
                        Toast.LENGTH_SHORT).show());

        btnSubmit.setOnClickListener(v -> submitComplaint());
    }

    /** Validates input, saves the complaint to SQLite, and finishes. */
    private void submitComplaint() {
        String description = descriptionInput.getText().toString().trim();
        if (description.isEmpty()) {
            Toast.makeText(this, "Please describe the problem",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Complaint complaint = new Complaint();
        complaint.setType(
                (Complaint.Type) typeSpinner.getSelectedItem());
        complaint.setDescription(description);
        complaint.setLatitude(currentLatitude);
        complaint.setLongitude(currentLongitude);

        long id = dbHelper.insertComplaint(complaint);
        if (id > 0) {
            Toast.makeText(this, "Complaint filed! ID: " + id,
                    Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Could not save the complaint",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
