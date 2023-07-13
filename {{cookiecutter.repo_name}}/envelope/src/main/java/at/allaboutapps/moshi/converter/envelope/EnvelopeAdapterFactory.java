package at.allaboutapps.moshi.converter.envelope;

import static java.util.Collections.unmodifiableSet;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class EnvelopeAdapterFactory implements JsonAdapter.Factory {

    @Override
    public JsonAdapter<?> create(@NotNull Type type, @NotNull Set<? extends Annotation> annotations, @NotNull Moshi moshi) {
        final EnvelopeJson annotation = findAnnotation(annotations);
        if (annotation == null) return null;

        JsonAdapter<Object> adapter = moshi.adapter(type, filterAnnotations(annotations));

        return new Adapter(annotation.value(), adapter);
    }

    private Set<? extends Annotation> filterAnnotations(Set<? extends Annotation> annotations) {
        Set<Annotation> result = null;
        for (Annotation annotation : annotations) {
            if (!(annotation instanceof EnvelopeJson)) {
                if (result == null) result = new LinkedHashSet<>();
                result.add(annotation);
            }
        }
        return result != null ? unmodifiableSet(result) : Collections.emptySet();
    }

    private class Adapter extends JsonAdapter<Object> {
        private final String name;
        private final JsonAdapter<Object> adapter;

        private final JsonReader.Options options;

        private Adapter(String name, JsonAdapter<Object> adapter) {
            this.name = name;
            this.adapter = adapter;
            this.options = JsonReader.Options.of(name);
        }

        @Override
        public Object fromJson(JsonReader reader) throws IOException {
            reader.beginObject();
            Object result = null;
            while (reader.hasNext()) {
                switch (reader.selectName(options)) {
                    case 0:
                        result = adapter.fromJson(reader);
                        break;
                    case -1: {
                        // Unknown name, skip it.
                        reader.skipName();
                        reader.skipValue();
                        break;
                    }
                }
            }
            reader.endObject();
            return result;
        }

        @Override
        public void toJson(JsonWriter writer, Object value) throws IOException {
            writer.beginObject();
            writer.name(name);
            adapter.toJson(writer, value);
            writer.endObject();
        }
    }


    private <T> T findAnnotation(Set<? extends Annotation> annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == EnvelopeJson.class) {
                //noinspection unchecked
                return (T) annotation;
            }
        }
        return null;
    }
}
