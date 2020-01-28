package com.example.dayfourthactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webbrowseractivity.R;

public class PuneActivity extends AppCompatActivity implements View.OnClickListener {
    Button go,forward,back, reload,history;
    WebView webview;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pune);
        go=(Button)findViewById(R.id.go_button3);
        forward=(Button)findViewById(R.id.forward_button6);
        back=(Button)findViewById(R.id.back_button5);
        reload=(Button)findViewById(R.id.reload_button7);
        history=(Button)findViewById(R.id.history_button);
        webview=(WebView)findViewById(R.id.webview_browser);

        go.setOnClickListener(this);
        forward.setOnClickListener(this);
        back.setOnClickListener(this);
        reload.setOnClickListener(this);
        history.setOnClickListener(this);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebViewClient(new WebviewHandler());
        webview.loadUrl("htts://www.google.com");


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.go_button3:

                String str = editText.getText().toString().trim();
                if (str.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "wright some thing in URL", Toast.LENGTH_LONG).show();
                }else{
                    webview.loadUrl("https://www."+str);
                    InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),0);

                }
                break;
            case R.id.forward_button6:
                if (webview.canGoForward()){
                    webview.goForward();
                }
                break;
            case R.id.back_button5:
                if(webview.canGoBack()){
                    webview.goBack();
                }
                break;
            case R.id.reload_button7:
                webview.reload();
                Toast.makeText(getApplicationContext(),"Refresh u page",Toast.LENGTH_LONG).show();
                webview.loadUrl("https://www.google.com");


                break;
            case R.id.history_button:
                webview.clearHistory();
                Toast.makeText(getApplicationContext(),"clear ur history",Toast.LENGTH_LONG).show();
                break;

        }

    }

    public class WebviewHandler extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            view.loadUrl(request);
            return true;
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder= new AlertDialog.Builder(PuneActivity.this);
        builder.setTitle("alert");
        builder.setMessage("do u want close Browser");
        LayoutInflater layoutInflater=getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.alertactivity,null);
        builder.setView(view);
        builder.show();
    }
}


