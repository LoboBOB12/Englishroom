package com.edroom.englishroom.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.edroom.englishroom.R;

public class AdController {
    public static int adCounter = 1;
    public static int adDisplayCounter = 7;

    public static NativeAd nativeAd;

    public static void initAd(Context context) {
        MobileAds.initialize(context, initializationStatus -> {

        });
    }

    static AdView gadView;

    public static void loadBannerAd(Context context, LinearLayout adContainer) {
        gadView = new AdView(context);
        gadView.setAdUnitId(context.getString(R.string.admob_banner_ad_id));
        adContainer.addView(gadView);
        loadBanner(context);
    }

    static void loadBanner(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();

        AdSize adSize = getAdSize((Activity) context);
        gadView.setAdSize(adSize);
        gadView.loadAd(adRequest);
    }

    static AdSize getAdSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    static AdView adView;

    public static void largeBannerAd(Context context, LinearLayout adContainer) {
        adView = new AdView(context);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdSize(AdSize.LARGE_BANNER);
        adView.setAdUnitId(context.getString(R.string.admob_banner_ad_id));
        adView.loadAd(adRequest);
        adContainer.addView(adView);
    }

    static InterstitialAd mInterstitialAd;

    public static void loadInterAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                context,
                context.getString(R.string.admob_interstitial_ad_id),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    public static void showInterstitialAd(Activity activity) {
        if (adCounter == adDisplayCounter && mInterstitialAd != null) {
            adCounter = 1;
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                // ...
            });
            mInterstitialAd.show(activity);
        } else {
            if (adCounter == adDisplayCounter) {
                adCounter = 1;
            }
            // Handle the case when interstitial ad is not ready
        }
    }

    public static void loadNativeAd(Activity activity, FrameLayout adContainer) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, activity.getResources().getString(R.string.admob_native_ad_id));
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = unifiedNativeAd;

                @SuppressLint("InflateParams") NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.ad_unified_new, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                adContainer.removeAllViews();
                adContainer.addView(adView);
            }
        });

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(new VideoOptions.Builder()
                        .setStartMuted(true)
                        .build())
                .build();

        builder.withNativeAdOptions(adOptions).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("SetTextI18n")
    private static void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        VideoController vc = nativeAd.getMediaContent().getVideoController();
        vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            public void onVideoEnd() {
                super.onVideoEnd();
            }
        });

        adView.setMediaView(adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());

        if (nativeAd.getStarRating() != null && nativeAd.getStarRating().doubleValue() > 0) {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        } else {
            adView.getStarRatingView().setVisibility(View.GONE);
        }

        if (nativeAd.getStore() != null) {
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
            adView.getStoreView().setVisibility(View.VISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.GONE);
        }

        if (nativeAd.getPrice() != null) {
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
            adView.getPriceView().setVisibility(View.VISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.GONE);
        }

        if (nativeAd.getAdvertiser() != null) {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        } else {
            adView.getAdvertiserView().setVisibility(View.GONE);
        }

        adView.setNativeAd(nativeAd);
    }

    public static void showFullScreenAd(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    loadInterAd(activity);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    loadInterAd(activity);
                }
            });
            mInterstitialAd.show(activity);
        }
    }
}