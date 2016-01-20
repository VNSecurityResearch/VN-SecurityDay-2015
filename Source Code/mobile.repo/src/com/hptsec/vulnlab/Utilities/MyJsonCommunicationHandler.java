package com.hptsec.vulnlab.Utilities;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyJsonCommunicationHandler {

	public static HttpEntity sendJsonToRemoteServer(StringEntity sE,
			String remoteURL) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPostReq = new HttpPost(remoteURL);

		httpPostReq.setEntity(sE);
		httpPostReq.setHeader("Accept", "application/json");
		httpPostReq.setHeader("Content-type", "application/json");

		HttpResponse httpresponse = httpClient.execute(httpPostReq);
		HttpEntity resultEntity = httpresponse.getEntity();

		return resultEntity;

	}
}
