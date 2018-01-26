package com.bonson.qqtapk.model.baidu;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.NoSuchElementException;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GeoCoderHelper {
    private GeoCoder geoCoder;

    @Inject
    public GeoCoderHelper() {
        geoCoder = GeoCoder.newInstance();
    }

    // 初始化搜索模块，注册事件监听
    public Observable<String> convert(LatLng latLng) {
        return Observable.create(it -> {
            ReverseGeoCodeOption option = new ReverseGeoCodeOption();
            option.location(latLng);
            geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                    if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        //没有检索到结果
                        it.onError(new NoSuchElementException("没有检索到结果"));
                        return;
                    }
                    it.onNext(result.getAddress());
                    it.onComplete();
                }
            });
            geoCoder.reverseGeoCode(option);
        });
    }

    // 初始化搜索模块，注册事件监听
    public Observable<LatLng> convert(String city, String address) {
        return Observable.create(it -> {
            GeoCodeOption option = new GeoCodeOption();
            option.city(city);
            option.address(address);
            geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult result) {
                    if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                        //没有检索到结果
                        it.onError(new NoSuchElementException("没有检索到结果"));
                        return;
                    }
                    it.onNext(result.getLocation());
                    it.onComplete();
                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                }
            });
            geoCoder.geocode(option);
        });
    }

    public void destroy() {
        geoCoder.destroy();
    }
}
