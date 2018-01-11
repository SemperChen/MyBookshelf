package com.monke.basemvplib;

import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class EncodeConverter extends Converter.Factory {

    private String encode = "utf-8";

    private EncodeConverter(){

    }

    private EncodeConverter(String encode){
        this.encode = encode;
    }

    public static EncodeConverter create(){
        return new EncodeConverter();
    }

    public static EncodeConverter create(String en){
        return new EncodeConverter(en);
    }

    @Override
    public Converter<ResponseBody, String> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new Converter<ResponseBody, String>() {
            @Override
            public String convert(@NonNull ResponseBody value) throws IOException {
                BufferedSource bufferedSource = Okio.buffer(value.source());
                return bufferedSource.readString(Charset.forName(encode));
            }
        };
    }
}
