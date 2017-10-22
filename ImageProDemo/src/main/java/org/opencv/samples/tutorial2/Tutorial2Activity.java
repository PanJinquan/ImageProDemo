package org.opencv.samples.tutorial2;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.ndk.detectFaceFeatureClass;

public class Tutorial2Activity extends Activity  {
    static{
        Log.i("OCVSample", "opencv_java loading...");
        System.loadLibrary("opencv_java3");
        Log.i("OCVSample", "opencv_java loaded successfully");
    }
    private static final String    TAG = "OCVSample::Activity";
    private ImageView imageview;
    private Bitmap bmp;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.main_activity);
        imageview = (ImageView) findViewById(R.id.image_view);
        //将girl图像加载程序中并进行显示
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        imageview.setImageBitmap(bmp);

        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.i(TAG, "onClick...");
                detectFaceFeatureClass detect=new detectFaceFeatureClass();
                Bitmap bmp_out =detect.autoCutOut(bmp,null,null);
                imageview.setImageBitmap(bmp_out);
                Log.i(TAG, "setImageBitmap");
            }
        });

    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    // Load native library after(!) OpenCV initialization
                    System.loadLibrary("mixed_sample");

                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    public Tutorial2Activity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }
}
