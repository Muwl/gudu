package vip.gudugudu.gudu.base;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者：Administrator on 2016/10/27 12:02
 * 描述：
 */

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return adapter.fromJson(value.charStream());

        } finally {
            value.close();
        }
    }
}
