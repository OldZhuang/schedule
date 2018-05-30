package schedule.zzk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yalantis.phoenix.PullToRefreshView;

public class MainActivity extends AppCompatActivity {

    private WebView webViewThis;
    private PullToRefreshView mPullToRefreshView;//下滑刷新动画
    private long exitTime = 0;//双击返回键退出延迟
    private long REFRESH_DELAY = 500;//刷新动画最少等待时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewThis = findViewById(R.id.webViewThis);
        webViewThis.loadUrl("http://st.gzvtc.cn/st2/st/login_x.aspx");//载入登陆界面
        starWebView();

        //第三方下滑刷新库
        mPullToRefreshView = findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {

            @Override
            public void onRefresh() {

                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webViewThis.reload();
                        webViewThis.setWebChromeClient(new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                if (newProgress == 100) {
                                    mPullToRefreshView.setRefreshing(false);
                                    Toast.makeText(getApplicationContext(), "刷新完毕", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });

    }

    private void starWebView() {
        setWebView();//浏览器参数

        //在当前Activity打开网页,不调用第三方浏览器
        webViewThis.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                //跳过加载菜单进入课程表页面
                if (url.contains("http://st.gzvtc.cn/st2/st/student/main_c_a.aspx")) {
                    //学校网站有bug,直接打开课程表页面显示为下一周的课程表
                    String postDate = "__EVENTTARGET=LinkButton_%E4%B8%8A%E4%B8%80%E5%91%A8&__EVENTARGUMENT=&__LASTFOCUS=&__VIEWSTATE=%2FwEPDwUJNDI2ODk0NDAxD2QWAgIDD2QWCAIBDw8WAh4EVGV4dAUCMTRkZAIDDxAPFgYeDURhdGFUZXh0RmllbGQFDOWtpuW5tOWtpuacnx4ORGF0YVZhbHVlRmllbGQFDOWtpuW5tOWtpuacnx4LXyFEYXRhQm91bmRnZBAVFRsyMDE3LTIwMTjlrablubTnrKzkuozlrabmnJ8bMjAxNy0yMDE45a2m5bm056ys5LiA5a2m5pyfGzIwMTYtMjAxN%2BWtpuW5tOesrOS6jOWtpuacnxsyMDE2LTIwMTflrablubTnrKzkuIDlrabmnJ8bMjAxNS0yMDE25a2m5bm056ys5LqM5a2m5pyfGzIwMTUtMjAxNuWtpuW5tOesrOS4gOWtpuacnxsyMDE0LTIwMTXlrablubTnrKzkuozlrabmnJ8bMjAxNC0yMDE15a2m5bm056ys5LiA5a2m5pyfGzIwMTMtMjAxNOWtpuW5tOesrOS6jOWtpuacnxsyMDEzLTIwMTTlrablubTnrKzkuIDlrabmnJ8bMjAxMi0yMDEz5a2m5bm056ys5LqM5a2m5pyfGzIwMTItMjAxM%2BWtpuW5tOesrOS4gOWtpuacnxsyMDExLTIwMTLlrablubTnrKzkuozlrabmnJ8bMjAxMS0yMDEy5a2m5bm056ys5LiA5a2m5pyfGzIwMTAtMjAxMeWtpuW5tOesrOS6jOWtpuacnxsyMDEwLTIwMTHlrablubTnrKzkuIDlrabmnJ8bMjAwOS0yMDEw5a2m5bm056ys5LqM5a2m5pyfGzIwMDktMjAxMOWtpuW5tOesrOS4gOWtpuacnxsyMDA4LTIwMDnlrablubTnrKzkuozlrabmnJ8bMjAwOC0yMDA55a2m5bm056ys5LiA5a2m5pyfGzIwMDctMjAwOOWtpuW5tOesrOS6jOWtpuacnxUVGzIwMTctMjAxOOWtpuW5tOesrOS6jOWtpuacnxsyMDE3LTIwMTjlrablubTnrKzkuIDlrabmnJ8bMjAxNi0yMDE35a2m5bm056ys5LqM5a2m5pyfGzIwMTYtMjAxN%2BWtpuW5tOesrOS4gOWtpuacnxsyMDE1LTIwMTblrablubTnrKzkuozlrabmnJ8bMjAxNS0yMDE25a2m5bm056ys5LiA5a2m5pyfGzIwMTQtMjAxNeWtpuW5tOesrOS6jOWtpuacnxsyMDE0LTIwMTXlrablubTnrKzkuIDlrabmnJ8bMjAxMy0yMDE05a2m5bm056ys5LqM5a2m5pyfGzIwMTMtMjAxNOWtpuW5tOesrOS4gOWtpuacnxsyMDEyLTIwMTPlrablubTnrKzkuozlrabmnJ8bMjAxMi0yMDEz5a2m5bm056ys5LiA5a2m5pyfGzIwMTEtMjAxMuWtpuW5tOesrOS6jOWtpuacnxsyMDExLTIwMTLlrablubTnrKzkuIDlrabmnJ8bMjAxMC0yMDEx5a2m5bm056ys5LqM5a2m5pyfGzIwMTAtMjAxMeWtpuW5tOesrOS4gOWtpuacnxsyMDA5LTIwMTDlrablubTnrKzkuozlrabmnJ8bMjAwOS0yMDEw5a2m5bm056ys5LiA5a2m5pyfGzIwMDgtMjAwOeWtpuW5tOesrOS6jOWtpuacnxsyMDA4LTIwMDnlrablubTnrKzkuIDlrabmnJ8bMjAwNy0yMDA45a2m5bm056ys5LqM5a2m5pyfFCsDFWdnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZxYBZmQCCw9kFgJmD2QWVAIBDw8WBB4ISW1hZ2VVcmwFD2dpZi5hc3B4P2lkPTFfMR4HVmlzaWJsZWdkZAIDDw8WBB8EBQ9naWYuYXNweD9pZD0yXzEfBWdkZAIFDw8WAh8FaGRkAgcPDxYCHwVoZGQCCQ8PFgIfBWhkZAILDw8WAh8FaGRkAg0PDxYCHwVoZGQCDw8PFgIfBWhkZAIRDw8WAh8FaGRkAhMPDxYCHwVoZGQCFQ8PFgIfBWhkZAIXDw8WAh8FaGRkAhkPDxYCHwVoZGQCGw8PFgIfBWhkZAIdDw8WBB8EBQ9naWYuYXNweD9pZD0xXzMfBWdkZAIfDw8WBB8EBQ9naWYuYXNweD9pZD0yXzMfBWdkZAIhDw8WAh8FaGRkAiMPDxYCHwVoZGQCJQ8PFgIfBWhkZAInDw8WBB8EBQ9naWYuYXNweD9pZD02XzMfBWdkZAIpDw8WAh8FaGRkAisPDxYCHwVoZGQCLQ8PFgIfBWhkZAIvDw8WBB8EBQ9naWYuYXNweD9pZD0zXzQfBWdkZAIxDw8WBB8EBQ9naWYuYXNweD9pZD00XzQfBWdkZAIzDw8WAh8FaGRkAjUPDxYCHwVoZGQCNw8PFgIfBWhkZAI5Dw8WAh8FaGRkAjsPDxYCHwVoZGQCPQ8PFgIfBWhkZAI%2FDw8WAh8FaGRkAkEPDxYCHwVoZGQCQw8PFgIfBWhkZAJFDw8WAh8FaGRkAkcPDxYCHwVoZGQCSQ8PFgIfBWhkZAJLDw8WAh8FaGRkAk0PDxYCHwVoZGQCTw8PFgIfBWhkZAJRDw8WAh8FaGRkAlMPDxYCHwVoZGQCDQ8PFgIfAAUMMTYxNDE4MTAxMDI2ZGQYAQUKTXVsdGlWaWV3MQ8PZGZk312rShAaRmWjZBrMs0iH0Dz99XY%3D&__EVENTVALIDATION=%2FwEWGQLQ8PCWCgKU96a%2FAQKRyb3lDwKVyr3lDwKny8%2B2CgKrzM%2B2CgL4zfPQCwL0zPPQCwK59%2FqTCgK19vqTCgLa9878BwLW9s78BwK12%2B75AQKx2u75AQLB4vS%2BDAL14fS%2BDALUspS7BgLYs5S7BgLy2q3qCwLm263qCwLWybPgDAKqyrPgDALUj%2FnIDAKP7I%2FZBgKP7Pv9DcqYP3lfi5rtDN%2BjUdGiycNE55Dt&cbo_%E5%AD%A6%E5%B9%B4%E5%AD%A6%E6%9C%9F=2017-2018%E5%AD%A6%E5%B9%B4%E7%AC%AC%E4%BA%8C%E5%AD%A6%E6%9C%9F";
                    view.postUrl("http://st.gzvtc.cn/st2/st/student/st_p.aspx", postDate.getBytes());
                    webViewThis.setWebChromeClient(new WebChromeClient() {
                        @Override
                        public void onProgressChanged(WebView view, int newProgress) {
                            if (newProgress == 100)
                                Toast.makeText(getApplicationContext(), "课程表载入完毕", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "课程表载入中...", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });

    }

    //设置浏览器参数
    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView() {
        WebSettings settings = webViewThis.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        settings.setUseWideViewPort(true);//支持viewport
        settings.setLoadWithOverviewMode(true);//自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);//支持缩放

        //支持alert弹窗
        webViewThis.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

    }

    /**
     * 重写返回按键
     * 在登录界面时按两次返回键退出程序
     */
    public void onBackPressed() {
        if (webViewThis.canGoBack()) {
            webViewThis.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

}
