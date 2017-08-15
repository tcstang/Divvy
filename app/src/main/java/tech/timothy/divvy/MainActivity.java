package tech.timothy.divvy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import tech.timothy.divvy.managers.OcrManager;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private static String APP_NAME = "divvy";
    private static String STORAGE_DIR = Environment.getExternalStorageDirectory().toString();
    private static final String DATA_PATH = STORAGE_DIR + "/" + APP_NAME;
    private static final String TESS_DATA_PATH = DATA_PATH + "/tessdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ocr_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testOcr();
            }
        });

        findViewById(R.id.camera_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCamera();
            }
        });
    }

    public void testOcr() {
        OcrManager ocr = OcrManager.getInstance(null);
        String filename = "tessdata/receipt_example_2.jpeg";
        File exampleFile = new File(this.getExternalFilesDir(null), filename);
        if (BuildConfig.DEBUG && !exampleFile.exists()) {
            throw new AssertionError("Precondition fail: Are you sure " + exampleFile.getPath() + " exists?");
        }

        Uri imageUri = Uri.fromFile(exampleFile);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath(), options);
        String text = ocr.getText(bitmap);
        TextView ocrResult = (TextView) findViewById(R.id.ocr_result_text);
        ocrResult.setText(text);
        Log.v(TAG, text);
    }

    private void testCamera() {

    }
}
