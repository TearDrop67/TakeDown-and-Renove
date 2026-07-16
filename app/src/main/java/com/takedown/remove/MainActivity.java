package com.takedown.remove;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * MainActivity - Entry point for the TakeDown and Remove application
 * Handles UI initialization and permission requests
 */
public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private TextView statusText;
    private Button scanButton;
    private Button removeButton;
    private Button preventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        initializeUI();

        // Request necessary permissions
        requestRequiredPermissions();
    }

    /**
     * Initialize UI components
     */
    private void initializeUI() {
        statusText = findViewById(R.id.status_text);
        scanButton = findViewById(R.id.scan_button);
        removeButton = findViewById(R.id.remove_button);
        preventButton = findViewById(R.id.prevent_button);

        // Set button click listeners
        scanButton.setOnClickListener(v -> performMalwareScan());
        removeButton.setOnClickListener(v -> performMalwareRemoval());
        preventButton.setOnClickListener(v -> activatePrevention());

        // Initial status
        statusText.setText("Ready to scan for malware...");
    }

    /**
     * Request necessary permissions for malware detection and removal
     */
    private void requestRequiredPermissions() {
        String[] permissions = {
            Manifest.permission.QUERY_ALL_PACKAGES,
            Manifest.permission.PACKAGE_USAGE_STATS,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE
        };

        // Check Android version for additional permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions = new String[]{
                Manifest.permission.QUERY_ALL_PACKAGES,
                Manifest.permission.PACKAGE_USAGE_STATS,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_MEDIA_IMAGES
            };
        }

        // Request permissions if not already granted
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
                return;
            }
        }

        statusText.setText("All permissions granted. Ready to proceed.");
    }

    /**
     * Handle permission request results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                statusText.setText("All permissions granted. Ready to proceed.");
            } else {
                statusText.setText("Some permissions were denied. Functionality may be limited.");
                Toast.makeText(this, "Permissions required for full functionality", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Perform malware scan
     */
    private void performMalwareScan() {
        statusText.setText("Scanning for malware...");
        Toast.makeText(this, "Scan started", Toast.LENGTH_SHORT).show();
        // TODO: Implement malware detection logic
    }

    /**
     * Perform malware removal
     */
    private void performMalwareRemoval() {
        statusText.setText("Removing malware...");
        Toast.makeText(this, "Removal started", Toast.LENGTH_SHORT).show();
        // TODO: Implement malware removal logic
    }

    /**
     * Activate prevention measures
     */
    private void activatePrevention() {
        statusText.setText("Activating prevention...");
        Toast.makeText(this, "Prevention activated", Toast.LENGTH_SHORT).show();
        // TODO: Implement prevention logic
    }
}
