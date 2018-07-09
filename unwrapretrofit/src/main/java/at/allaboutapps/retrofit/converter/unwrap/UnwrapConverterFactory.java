package at.allaboutapps.retrofit.converter.unwrap;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Add support for {@link UnwrapResponse}.
 */
public class UnwrapConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        final Annotation unwrapResponse = findUnwrapResponse(annotations);
        if (unwrapResponse == null) return null;

        final ParameterizedType parameterizedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{String.class, type};
            }

            @Override
            public Type getRawType() {
                return Map.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

        final Converter<ResponseBody, ?> bodyConverter = retrofit.nextResponseBodyConverter(this, parameterizedType, annotations);

        return new UnwrapConverter(bodyConverter, (UnwrapResponse) unwrapResponse);
    }

    private Annotation findUnwrapResponse(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof UnwrapResponse) return annotation;
        }
        return null;
    }
}
