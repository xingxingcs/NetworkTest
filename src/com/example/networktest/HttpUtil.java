package com.example.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.widget.Toast;

/*
 * 发起一条HTTP请求的公共类HttpUtil
 * 当需要发起一条HTTP请求的时候就可以这样写：
 * String address="http://www.baidu.com";
 * String response=HttpUtil.sendhttpRequest(address);
 */

public class HttpUtil {
	
	public interface HttpCallbackListener {
		void onFinish(String response);

		void onError(Exception e);
	}
	private static boolean isNetworkAvailable(){
		return true;
	}
	public static void sendHttpRequest(final Context context,final String address,
			final HttpCallbackListener listener) {
		if(!isNetworkAvailable()){
			Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						// 回调onFinish()方法
						listener.onFinish(response.toString());
					}
				} catch (Exception ex) {
					if (listener != null) {
						// 回调onError()方法
						listener.onError(ex);
					}

				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}

		}).start();

	}
}
