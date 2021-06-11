package tech.yummy.silence;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_silence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getCacheDir() + File.separator + "test.apk");
                if (!file.exists()) {
                    copyFiles("test.apk", file);
                }
                UpdateUtils.slientInstall(file.getPath());
            }
        });
    }

    public void copyFiles(String fileName, File desFile) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = getApplicationContext().getAssets().open(fileName);
            out = new FileOutputStream(desFile.getAbsolutePath());
            byte[] bytes = new byte[1024];
            int i;
            while ((i = in.read(bytes)) != -1)
                out.write(bytes, 0, i);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}