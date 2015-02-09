package co.gdera.javacvtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameRecorder;

import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //You need to have an image, of course!
        opencv_core.IplImage img = cvLoadImage("/sdcard/image.jpg");

        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("/sdcard/test.mpeg",200,150);

        try {
            recorder.setFrameRate(30);
            recorder.start();

            for (int i=0;i<100;i++)
            {
                recorder.record(img);
            }
            recorder.stop();
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
