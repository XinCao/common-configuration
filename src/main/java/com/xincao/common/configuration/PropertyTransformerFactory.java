package com.xincao.common.configuration;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.regex.Pattern;
import com.xincao.common.configuration.transformers.BooleanTransformer;
import com.xincao.common.configuration.transformers.ByteTransformer;
import com.xincao.common.configuration.transformers.CharTransformer;
import com.xincao.common.configuration.transformers.ClassTransformer;
import com.xincao.common.configuration.transformers.DoubleTransformer;
import com.xincao.common.configuration.transformers.EnumTransformer;
import com.xincao.common.configuration.transformers.FileTransformer;
import com.xincao.common.configuration.transformers.FloatTransformer;
import com.xincao.common.configuration.transformers.InetSocketAddressTransformer;
import com.xincao.common.configuration.transformers.IntegerTransformer;
import com.xincao.common.configuration.transformers.LongTransformer;
import com.xincao.common.configuration.transformers.PatternTransformer;
import com.xincao.common.configuration.transformers.ShortTransformer;
import com.xincao.common.configuration.transformers.StringTransformer;
import com.xincao.common.util.tool.ClassRelationshipAnalysis;

/**
 * This class is responsible for creating property transformers. Each time it
 * creates new instance of custom property transformer, but for build-in it uses
 * shared instances to avoid overhead
 *
 * @author SoulKeeper
 */
public class PropertyTransformerFactory {

    /**
     * Returns property transformer or throws
     * {@link com.aionemu.commons.configuration.TransformationException} if
     * can't create new one.
     *
     * @param clazzToTransform Class that will is going to be transformed
     * @param tc {@link com.aionemu.commons.configuration.PropertyTransformer}
     * class that will be instantiated
     * @return instance of PropertyTransformer
     * @throws TransformationException if can't instantiate
     * {@link com.aionemu.commons.configuration.PropertyTransformer}
     */
    @SuppressWarnings("unchecked")
    public static PropertyTransformer newTransformer(Class clazzToTransform, Class<? extends PropertyTransformer> tc)
            throws TransformationException {

        // Just a hack, we can't set null to annotation value
        if (tc == PropertyTransformer.class) {
            tc = null;
        }

        if (tc != null) {
            try {
                return tc.newInstance();
            } catch (Exception e) {
                throw new TransformationException("Can't instantiate property transfromer", e);
            }
        } else {
            if (clazzToTransform == Boolean.class || clazzToTransform == Boolean.TYPE) {
                return BooleanTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Byte.class || clazzToTransform == Byte.TYPE) {
                return ByteTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Character.class || clazzToTransform == Character.TYPE) {
                return CharTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Double.class || clazzToTransform == Double.TYPE) {
                return DoubleTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Float.class || clazzToTransform == Float.TYPE) {
                return FloatTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Integer.class || clazzToTransform == Integer.TYPE) {
                return IntegerTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Long.class || clazzToTransform == Long.TYPE) {
                return LongTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Short.class || clazzToTransform == Short.TYPE) {
                return ShortTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == String.class) {
                return StringTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform.isEnum()) {
                return EnumTransformer.SHARED_INSTANCE;
                // TODO: Implement
                // } else if (ClassUtils.isSubclass(clazzToTransform,
                // Collection.class)) {
                // return new CollectionTransformer();
                // } else if (clazzToTransform.isArray()) {
                // return new ArrayTransformer();
            } else if (clazzToTransform == File.class) {
                return FileTransformer.SHARED_INSTANCE;
            } else if (ClassRelationshipAnalysis.isSubclass(clazzToTransform, InetSocketAddress.class)) {
                return InetSocketAddressTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Pattern.class) {
                return PatternTransformer.SHARED_INSTANCE;
            } else if (clazzToTransform == Class.class) {
                return ClassTransformer.SHARED_INSTANCE;
            } else {
                throw new TransformationException("Transformer not found for class " + clazzToTransform.getName());
            }
        }
    }
}