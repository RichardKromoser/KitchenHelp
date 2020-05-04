package com.rr.kitchenHelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.storage.FirebaseStorage;
import com.rr.kitchenHelp.cameraUtils.OcrCaptureActivity;
public class MainActivity extends BaseActivity {

    public static final String EXTRA_MESSAGE = "com.rr.kitchenHelp.MESSAGE";
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDefaultToolbar();
        initializeDefaultDrawer();
        initializeDatabase();
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://kitchenhelp-58395.appspot.com");
    }


    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, OcrCaptureActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String returnedResult = data.getDataString();
                EditText editText = findViewById(R.id.editText);
                editText.setText(returnedResult);
            }
        }
    }

    public void addRecipe() {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_recipe) {
            addRecipe();
        }
        return true;
    }
}
