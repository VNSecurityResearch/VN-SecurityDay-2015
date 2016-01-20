package com.hptsec.vulnlab.Utilities;

import android.content.res.Resources;

import org.apache.http.client.CookieStore;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by baolq on 9/14/2015.
 * M3 - Certificate Pinning
 */
public class MyHttpClientBuilderForPinning extends DefaultHttpClient {
    static final String BOUNCY_CASTLE = "BKS";
    static final int HTTPS_PORT = 443;
    static final String HTTPS_SCHEME = "https";
    static final int HTTP_PORT = 80;
    static final String HTTP_SCHEME = "http";
    static final String TLS = "TLS";
    protected CookieStore cookieStore;
    protected HttpParams httpParams;
    protected int httpPort;
    protected int httpsPort;
    protected KeyStore keyStore;
    protected SchemeRegistry schemeRegistry;

    public MyHttpClientBuilderForPinning() {
        super();
        this.keyStore = null;
        this.httpParams = new BasicHttpParams();
        this.schemeRegistry = new SchemeRegistry();
        this.httpPort = 80;
        this.httpsPort = 443;
        this.cookieStore = new BasicCookieStore();
    }


    public DefaultHttpClient build() {
        this.schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), this.
                httpPort));

        if(this.keyStore != null) {
            try {
                this.schemeRegistry.register(new Scheme("https", new SSLSocketFactory(this.keyStore),
                        this.httpsPort));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            }
        }
        else {
            this.schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), this
                    .httpsPort));
        }

        DefaultHttpClient defaultClient = new DefaultHttpClient(new ThreadSafeClientConnManager(this.httpParams,
                this.schemeRegistry), this.httpParams);

        if(this.cookieStore != null) {
            defaultClient.setCookieStore(this.cookieStore);
        }

        return defaultClient;
    }

    public MyHttpClientBuilderForPinning pinCertificates(InputStream resourceStream, char[] password) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        this.keyStore = KeyStore.getInstance("BKS");
        this.keyStore.load(resourceStream, password);
        return this;
    }

    public MyHttpClientBuilderForPinning pinCertificates(Resources resources, int certificateRawResource, char[]
            password) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        this.pinCertificates(resources.openRawResource(certificateRawResource), password);
        return this;
    }

    public MyHttpClientBuilderForPinning registerScheme(String name, SocketFactory factory, int port) {
        this.schemeRegistry.register(new Scheme(name, factory, port));
        return this;
    }

    public MyHttpClientBuilderForPinning setConnectionTimeout(int connectionTimeout) {
        HttpConnectionParams.setConnectionTimeout(this.httpParams, connectionTimeout);
        return this;
    }


    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public MyHttpClientBuilderForPinning setHttpPort(int port) {
        this.httpPort = port;
        return this;
    }

    public MyHttpClientBuilderForPinning setHttpsPort(int port) {
        this.httpsPort = port;
        return this;
    }

    public MyHttpClientBuilderForPinning setSocketTimeout(int socketTimeout) {
        HttpConnectionParams.setSoTimeout(this.httpParams, socketTimeout);
        return this;
    }
}
