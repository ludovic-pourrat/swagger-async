package io.swagger.v2.async.converter;

import java.util.Iterator;

import io.swagger.v2.aas.models.media.Schema;

public interface ModelConverter {

    /**
     * @param type
     * @param context
     * @param chain   the chain of model converters to try if this implementation cannot process
     * @return null if this ModelConverter cannot convert the given Type
     */
    Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain);
}
