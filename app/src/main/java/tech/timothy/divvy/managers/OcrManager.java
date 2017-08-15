package tech.timothy.divvy.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import tech.timothy.divvy.BuildConfig;
import tech.timothy.divvy.DivvyApplication;

/**
 * Created by tcstang on 8/13/17.
 */

public class OcrManager {
    private TessBaseAPI api;
    private static  OcrManager instance;
    private static Context applicationContext;
    private static String TAG = OcrManager.class.getSimpleName();
    private static String STORAGE_DIR = Environment.getExternalStorageDirectory().toString();
    private static final String DATA_PATH = STORAGE_DIR + "/" + "divvy";
    private static final String TESS_DATA_PATH = DATA_PATH + "/tessdata";
    private static final String TESS_DATA = "/tessdata";

    private OcrManager(Context context) {
        // set the environment's context as DivvyApplication if not otherwise provided
        if (context == null)
        {
            applicationContext = DivvyApplication.getContext();
        }
        else {
            applicationContext = context;
        }
        applicationContext = DivvyApplication.getContext();

        prepareTessData();
        api = new TessBaseAPI();
        api.init(applicationContext.getExternalFilesDir(null).toString(), "eng");
    }

    /**
     *
     * @param context the environment context; if null, will use DivvyApplication's context
     */
    public static OcrManager getInstance(Context context) {
        if (instance == null) {
            instance = new OcrManager(context);
        }

        return instance;
    }

    /**
     * Use Tesseract API to convert bitmap to String
     *
     * @return the converted text
     *
     * @param bitmap the bitmap in which to perform Optical Character Recognition
     */
    public String getText(Bitmap bitmap) {
        // attempt to perform OCR on bitmap
        api.setImage(bitmap);
        String result = "";
        try {
            result = api.getUTF8Text();
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return result;
    }

    /**
     * This method is necessary for ensuring the trained data is present on the device
     *
     * @throws AssertionError when the necessary tessdata directory can't be created
     */
    private void prepareTessData() {
        try {
            // create app dir if it does not exist
            File dir = new File(applicationContext.getExternalFilesDir(null), TESS_DATA);
            if (!dir.exists()) {
                Boolean createdProperly = dir.mkdirs();
                if (BuildConfig.DEBUG && !createdProperly) {
                    throw new AssertionError("Was not able to create data directory");
                }
            }

            // write all assets not present on exernal file dir
            String fileList[] = {"eng.traineddata", "receipt_example_2.jpeg"};
            File file;
            for (String fileName : fileList) {
                if (!(file = new File(applicationContext.getExternalFilesDir(null), TESS_DATA + "/" + fileName)).exists()) {
                    InputStream in = applicationContext.getAssets().open(fileName);
                    OutputStream out = new FileOutputStream(file.getAbsoluteFile());
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) > 0) {
                        out.write(buff, 0, len);
                    }

                    in.close();
                    out.close();
                }
            }
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
