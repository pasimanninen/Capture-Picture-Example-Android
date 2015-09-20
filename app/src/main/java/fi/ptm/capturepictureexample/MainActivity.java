package fi.ptm.capturepictureexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author PTM
 */
public class MainActivity extends Activity {

    private ImageView imageView;
    private final int TAKE_PICTURE_FULL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void capturePicture(View view) {
        // capture image
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // save file to sdcard
        File file = new File(Environment.getExternalStorageDirectory(),"test.jpg");
        Uri imageUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // start image capture intent
        startActivityForResult(intent, TAKE_PICTURE_FULL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == TAKE_PICTURE_FULL) {
            try {
                File file = new File(Environment.getExternalStorageDirectory(),"test.jpg");
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                imageView.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                Toast.makeText(this,"Captured image not found! ",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(this,"Captured image not found! ",Toast.LENGTH_LONG).show();
            }
        }
    }
}
