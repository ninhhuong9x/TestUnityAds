package com.example.testunityads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;
import com.unity3d.services.banners.api.Banner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String unityGameID = "3918523";
    private Boolean testMode = true;
    private String placementId = "Interstitial";

    Boolean enableLoad = true;
    String bannerPlacement = "TopBanner";
    BannerView topBanner;
    LinearLayout topBannerView;
    UnityBannerListener bannerListener = new UnityBannerListener();

    private String TAG = "linh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showBanner = findViewById(R.id.btShowBanner);
        Button showInter = findViewById(R.id.btShowInter);

        showBanner.setOnClickListener(this);
        showInter.setOnClickListener(this);

        UnityAdsListener adsListener = new UnityAdsListener();
        UnityAds.addListener(adsListener);
        UnityAds.initialize(this, unityGameID, testMode, enableLoad);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btShowBanner:

                topBanner = new BannerView(this, bannerPlacement, new UnityBannerSize(320, 50));
                topBanner.setListener(bannerListener);
                topBanner.load();

                topBannerView = findViewById(R.id.top_banner_view);
                topBannerView.addView(topBanner);

                Log.d(TAG, "onClick: showbanner");
                break;
            case R.id.btShowInter:
                Log.d(TAG, "onClick: showInter");
                showInterstitial();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

    }

    private void showInterstitial(){
        if (UnityAds.isReady(placementId)) {
            UnityAds.show(this, placementId);
        }
    }

    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady(String s) {
            Log.d(TAG, "onUnityAdsReady: ");
        }

        @Override
        public void onUnityAdsStart(String s) {
            Log.d(TAG, "onUnityAdsStart: ");
        }

        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
            Log.d(TAG, "onUnityAdsFinish: ");
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
            Log.d(TAG, "onUnityAdsError: ");
        }
    }

    private class UnityBannerListener implements BannerView.IListener {

        @Override
        public void onBannerLoaded(BannerView bannerView) {
            Log.d(TAG, "onBannerLoaded: ");
        }

        @Override
        public void onBannerClick(BannerView bannerView) {
            Log.d(TAG, "onBannerClick: ");
        }

        @Override
        public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
            Log.d(TAG, "onBannerFailedToLoad: ");
        }

        @Override
        public void onBannerLeftApplication(BannerView bannerView) {
            Log.d(TAG, "onBannerLeftApplication: ");
        }
    }
    
}