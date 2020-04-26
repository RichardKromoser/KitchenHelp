package com.rr.kitchenHelp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AddRecipeActivity extends BaseActivity {
    private ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        initializeDefaultToolbar();
        initializeDatabase();
        setSearchVisible(false);
        setAddRecipeVisible(false);
        picture = findViewById(R.id.new_recipe_picture);

        Button pictureButton = findViewById(R.id.add_foto);

        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture(AddRecipeActivity.this);
            }
        });
    }

    public void selectPicture(Context context) {
        final String[] options = {"Neues Foto", "Wähle aus Galerie", "Abbrechen"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Wähle ein Foto für dein Rezept");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Neues Foto")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePicture.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePicture, 0);
                    }
                } else if (options[which].equals("Wähle aus Galerie")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                } else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        picture.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        if (selectedImage != null) {
//                                InputStream inputStream = null;
//                                try {
//                                    inputStream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                }
                            picture.setImageURI(selectedImage);
                        }
                    }
                    break;
            }
        }
    }
}
