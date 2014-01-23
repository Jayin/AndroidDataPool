package com.example.android_datapool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.utils.DataPool;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * to test the efficency of saving data between  to File & SharedPreference
 * File size =78,871  bytes
 * ------data--------
 *  执行次数  : 平均耗时
 * 10000  : file 141 ms
 *         sp   65ms
 *         
 *  1000  : file 19ms
 *  
 *         sp 12ms
 *         
 *    100: file 2ms;
 *         sp   0ms
 * @author Jayin Ton
 * 
 */
public class SP1File extends Activity {
	private TextView tv;
	private View test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_sp1file);
		test = findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.textView1);
		test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				long t_f = 0, t_s = 0;
				String info = "";
				String content = "";
				long start = System.currentTimeMillis();
				long end = System.currentTimeMillis();
				AssetManager am = SP1File.this.getAssets();
				InputStream in = null;
				FileOutputStream fos = null;
				for (int i = 0; i < 100; i++) {

					try {
						start = System.currentTimeMillis();
						in = am.open("googlehomepage");
						fos = SP1File.this.openFileOutput("test",
								Context.MODE_PRIVATE);
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = in.read(buffer)) != -1) {
							fos.write(buffer, 0, len);
						}

					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (fos != null)
							try {
								fos.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						if (in != null) {
							try {
								in.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						end = System.currentTimeMillis();
					}

					// SharedPreference

					t_f += (end - start);
					DataPool dp = new DataPool(SP1File.this);

					try {
						in = am.open("googlehomepage");
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = in.read(buffer)) != -1) {
							content += new String(buffer, 0, len);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					start = System.currentTimeMillis();
					dp.put("test", content);
					end = System.currentTimeMillis();

					t_s += (end - start);
				}
				info = "file write cost average:" + (t_f / 100) + "ms";
				info += "\nSharedPreference write cost average:" + (t_s / 100)
						+ "ms";
				tv.setText(info);
			}
		});
	}
}
