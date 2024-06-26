package com.genius.ifbretailer.activity;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.genius.ifbretailer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import im.delight.android.webview.AdvancedWebView;

public class QuizWebActivity extends AppCompatActivity implements AdvancedWebView.Listener{
    String imageurl,mdid,doc_type,entity_id,accessToken,cookies;
    AdvancedWebView wbUrl;
    ImageView imgCancel;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_web);
        initView();
    }
    private void initView(){



        imgCancel=(ImageView) findViewById(R.id.imgCancel);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        imageurl=getIntent().getStringExtra("imageurl");
        mdid=getIntent().getStringExtra("mdid");
        doc_type=getIntent().getStringExtra("doc_type");
        entity_id=getIntent().getStringExtra("entity_id");
        accessToken=getIntent().getStringExtra("accessToken");
        cookies=getIntent().getStringExtra("cookies");
        wbUrl=(AdvancedWebView)findViewById(R.id.wbUrl);

        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.removeSessionCookie();
        cookieManager.acceptCookie();
        cookieManager.setCookie("https://apps.bsharpcorp.com", cookies);
        CookieSyncManager.getInstance().sync();
        cookieManager.setAcceptThirdPartyCookies(wbUrl,true);

        wbUrl.setListener(this, this);
        wbUrl.requestFocus(View.FOCUS_DOWN);
        wbUrl.getSettings().setJavaScriptEnabled(true);
        wbUrl.getSettings().setAllowContentAccess(true);
        wbUrl.getSettings().setAllowFileAccess(true);
        wbUrl.getSettings().setDatabaseEnabled(true);
        wbUrl.getSettings().setDomStorageEnabled(true);
        wbUrl.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        wbUrl.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wbUrl.getSettings().setUseWideViewPort(false);
        wbUrl.getSettings().setDisplayZoomControls(false);
        wbUrl.loadUrl(imageurl);
        Log.d("imageurl",imageurl);





        wbUrl.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();

            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                //Your code to do
                view.loadUrl(imageurl);
                progressDialog.dismiss();
            }
        });
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(cookies,accessToken);

            }
        });



    }
    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        wbUrl.onResume();

    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        wbUrl.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        wbUrl.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        wbUrl.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!wbUrl.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) { }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }


    private void save(final String cookie, final String accesstoken) {
        final ProgressDialog progressDialog=new ProgressDialog(QuizWebActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AndroidNetworking.get("https://apps.bsharpcorp.com/infocapture/get_doc_status/"+mdid+"/"+doc_type+"/"+entity_id)
                .addHeaders("Cookie", cookie)
                .addHeaders("X-CSRF-Token", accesstoken)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("Information_Response: ",response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.getInt("status") == 1){

                                Intent intent=new Intent(QuizWebActivity.this,DashboardActivity.class);
                                startActivity(intent);
                                finish();



                            }else {
                                Toast.makeText(QuizWebActivity.this,"Please Submit your answer",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressDialog.dismiss();
                        // handle error
                    }
                });
    }




}
