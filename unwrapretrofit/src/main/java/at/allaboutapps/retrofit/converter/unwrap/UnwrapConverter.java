package at.allaboutapps.retrofit.converter.unwrap;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class UnwrapConverter<T> implements Converter<ResponseBody, T> {
    private final Converter<ResponseBody, Map<String, T>> converter;
    private final UnwrapResponse unwrapResponse;

    public UnwrapConverter(Converter<ResponseBody, Map<String, T>> converter, UnwrapResponse unwrapResponse) {
        this.converter = converter;
        this.unwrapResponse = unwrapResponse;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return converter.convert(value).get(unwrapResponse.value());
    }
}
