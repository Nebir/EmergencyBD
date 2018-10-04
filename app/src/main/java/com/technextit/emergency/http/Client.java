package com.technextit.emergency.http;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.technextit.emergency.app.AppController;
import com.technextit.emergency.listener.VolleyResponseHandler;
import com.technextit.emergency.utils.URLUtils;

public class Client {

	public static <T> void post(String url,
			Map<String, String> params, Class<T> clazz,
			Map<String, String> headers,
			final VolleyResponseHandler<T> responseHandler) {
		makeRequest(url, params, clazz, headers, responseHandler);
	}

	private static <T> void makeRequest(String url, Map<String, String> params,
			Class<T> clazz, Map<String, String> headers,
			final VolleyResponseHandler<T> responseHandler) {
		
		GsonRequest<T> gsonReq = new GsonRequest<T>(URLUtils.getAbsoluteUrl(url),
				params, clazz, headers, Method.POST,
				new Response.Listener<T>() {
					@Override
					public void onResponse(T response) {
						responseHandler.onSuccess(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						responseHandler.onError(error);
					}
				});
		AppController.getInstance().addToRequestQueue(gsonReq);
	}

}
