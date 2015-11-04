package com.hybrid.hybridWeb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class HybridWebActivity extends Activity {
	
	
	Button button1 ; 
	Button button2 ; 
	Button button3 ; 
	
	WebView myweb ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hybrid_web);
		
		myweb = (WebView) findViewById(R.id.myweb); 
		
		WebSettings settings = myweb.getSettings(); 
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true); // webview 에서 zoom in & out 기능  
		
		
		myweb.addJavascriptInterface(new MyJavascriptInterface(), "android"); //자바 스크립트에서 자바를 호출할 수 있도록 ... 여러개 만들수 있다. 자바 불러온다.
		myweb.setWebViewClient(new MyWebViewClient()); // a(앵커) tag의  href에 있는 주소에서 브러우저를 열어주기 위해서  
		myweb.setWebChromeClient(new WebChromeClient()); // alert 를 써주기 위해, webkit에서 사용 위해
		
	
		button1 = (Button) findViewById(R.id.button1) ;
		button2 = (Button) findViewById(R.id.button2) ;
		button3 = (Button) findViewById(R.id.button3) ;
		
		myweb.loadUrl("http://192.168.10.23:8080/web/index.jsp");
//		myweb.loadUrl("http://192.168.10.23");
//		myweb.loadUrl("http://www.naver.com");
//		myweb.loadUrl("http://www.soen.kr");
		
		
	}

	//inner class 로 하나 만든다.  
	class MyJavascriptInterface{
		
		// 아래와 같이 annotation 을 하나 붙여줘야 한다. 
		@JavascriptInterface
		public void showToast(String msg) {
			
			Log.i("###", msg); 
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
			
		}
		
	}
	
	//myweb.setWebViewClient(client)의 client를 처리하기 위해 ....
	class MyWebViewClient extends WebViewClient{
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			Log.i("###", "URL = " + url); 
			view.loadUrl(url);
			
			return true ;
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hybrid_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
