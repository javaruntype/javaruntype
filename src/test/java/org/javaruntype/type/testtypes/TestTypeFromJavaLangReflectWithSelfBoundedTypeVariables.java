package org.javaruntype.type.testtypes;

import java.util.HashMap;
import java.util.Map;

import org.javaruntype.type.Type;
import org.javaruntype.type.Types;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTypeFromJavaLangReflectWithSelfBoundedTypeVariables {
    public interface X<T extends Comparable<T>> {
    }

    @Test public void test() throws Exception {
        Map<String, Type<?>> vars = new HashMap<String, Type<?>>();
        vars.put("T", Types.forJavaLangReflectType(Integer.class));

        Type<?> t = Types.forJavaLangReflectType(X.class.getTypeParameters()[0], vars);

        assertEquals(Integer.class, t.getRawClass());
    }
}
