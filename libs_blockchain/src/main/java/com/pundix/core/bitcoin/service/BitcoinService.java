package com.pundix.core.bitcoin.service;


import com.pundix.core.FunctionxNodeConfig;
import com.pundix.core.coin.Coin;
import com.pundix.core.http.ChainOkHttpClientConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: BitcoinService
 * @Description:
 * @Author: YT
 * @CreateDate: 2020/6/12 5:47 PM
 */
public class BitcoinService {

    private static BitcoinClient bitcoinClient;

    public static BitcoinClient getFunctionxClient() {
        if (bitcoinClient == null) {
            OkHttpClient okHttpClient = ChainOkHttpClientConfig.getOkHttpClient();
            Retrofit.Builder builder = getRetrofitBuilder();
            builder.baseUrl(FunctionxNodeConfig.getInstance().getNodeConfig(Coin.BITCOIN).getUrl());
            builder.client(okHttpClient);
            bitcoinClient = builder.build().create(BitcoinClient.class);
        }
        return bitcoinClient;
    }

    private static Retrofit.Builder retrofitBuilder;

    private static Retrofit.Builder getRetrofitBuilder() {
        if (retrofitBuilder == null) {
            retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        }
        return retrofitBuilder;
    }


}
