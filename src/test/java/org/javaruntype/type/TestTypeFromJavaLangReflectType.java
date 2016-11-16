package org.javaruntype.type;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.javaruntype.exceptions.TypeValidationException;

public class TestTypeFromJavaLangReflectType extends TestCase {

    
    public String m1() {
        return null;
    }
    
    public List<String> m2() {
        return null;
    }
    
    public <E> Map<String,List<E>> m3() {
        return null;
    }
    
    public <R extends Serializable, E> Map<R,List<? extends E>> m4() {
        return null;
    }
    
    public <R> R m5() {
        return null;
    }
    
    public <R extends Serializable> R m6() {
        return null;
    }
    
    public List<? extends String> m7() {
        return null;
    }
    
    public List<? extends Map<? extends Serializable, List<String>>> m8() {
        return null;
    }
    
    public <E> List<? extends Map<? extends Serializable, List<E>>> m9() {
        return null;
    }

    
    
    @SuppressWarnings("unused")
    public void testForJavaLangReflectType() throws Exception {
        
        final Method m1 = TestTypeFromJavaLangReflectType.class.getMethod("m1");
        final Method m2 = TestTypeFromJavaLangReflectType.class.getMethod("m2");
        final Method m3 = TestTypeFromJavaLangReflectType.class.getMethod("m3");
        final Method m4 = TestTypeFromJavaLangReflectType.class.getMethod("m4");
        final Method m5 = TestTypeFromJavaLangReflectType.class.getMethod("m5");
        final Method m6 = TestTypeFromJavaLangReflectType.class.getMethod("m6");
        final Method m7 = TestTypeFromJavaLangReflectType.class.getMethod("m7");
        final Method m8 = TestTypeFromJavaLangReflectType.class.getMethod("m8");
        final Method m9 = TestTypeFromJavaLangReflectType.class.getMethod("m9");

        final java.lang.reflect.Type tm1 = m1.getGenericReturnType();
        final java.lang.reflect.Type tm2 = m2.getGenericReturnType();
        final java.lang.reflect.Type tm3 = m3.getGenericReturnType();
        final java.lang.reflect.Type tm4 = m4.getGenericReturnType();
        final java.lang.reflect.Type tm5 = m5.getGenericReturnType();
        final java.lang.reflect.Type tm6 = m6.getGenericReturnType();
        final java.lang.reflect.Type tm7 = m7.getGenericReturnType();
        final java.lang.reflect.Type tm8 = m8.getGenericReturnType();
        final java.lang.reflect.Type tm9 = m9.getGenericReturnType();
        

        final Type<?> et1 = Types.forName("java.lang.String");
        final Type<?> et2 = Types.forName("java.util.List<java.lang.String>");
        final Type<?> et3a = Types.forName("java.util.Map<String,List<String>>");
        final Type<?> et3b = Types.forName("java.util.Map<String,List<Integer>>");
        final Type<?> et4a = Types.forName("java.util.Map<Integer,java.util.List<? extends Serializable>>");
        final Type<?> et4b = Types.forName("java.util.Map<Number,java.util.List<? extends Integer>>");
        final Type<?> et4c = Types.forName("java.util.Map<Integer,java.util.List<? extends java.util.List<? extends Number>>>");
        final Type<?> et4d = Types.forName("java.util.Map<java.util.List<?>,java.util.List<? extends java.util.List<? extends Number>>>");
        final Type<?> et5a = Types.forName("java.lang.String");
        final Type<?> et5b = Types.forName("java.lang.Integer");
        final Type<?> et5c = Types.forName("java.io.Serializable");
        final Type<?> et5d = Types.forName("java.util.List<?>");
        final Type<?> et6a = Types.forName("java.lang.String");
        final Type<?> et6b = Types.forName("java.io.Serializable");
        final Type<?> et6c = Types.forName("java.util.List<?>");
        final Type<?> et7 = Types.forName("java.util.List<? extends java.lang.String>");
        final Type<?> et8 = Types.forName("java.util.List<? extends java.util.Map<? extends java.io.Serializable, java.util.List<java.lang.String>>>");
        final Type<?> et9a = Types.forName("java.util.List<? extends java.util.Map<? extends java.io.Serializable, java.util.List<java.lang.String>>>");
        final Type<?> et9b = Types.forName("java.util.List<? extends java.util.Map<? extends java.io.Serializable, java.util.List<java.lang.Integer>>>");
        final Type<?> et9c = Types.forName("java.util.List<? extends java.util.Map<? extends java.io.Serializable, java.util.List<java.util.List<Integer>>>>");


        final Type<?> jt1 = Types.forJavaLangReflectType(tm1);
        assertEquals(et1, jt1);
        
        
        final Type<?> jt2 = Types.forJavaLangReflectType(tm2);
        assertEquals(et2, jt2);

        
        final Map<String,Type<?>> jt3av = new HashMap<String, Type<?>>();
        jt3av.put("E", Types.STRING);
        final Type<?> jt3a = Types.forJavaLangReflectType(tm3, jt3av);
        assertEquals(et3a, jt3a);

        
        final Map<String,Type<?>> jt3bv = new HashMap<String, Type<?>>();
        jt3bv.put("E", Types.INTEGER);
        final Type<?> jt3b = Types.forJavaLangReflectType(tm3, jt3bv);
        assertEquals(et3b, jt3b);

        
        final Map<String,Type<?>> jt4av = new HashMap<String, Type<?>>();
        jt4av.put("R", Types.INTEGER);
        jt4av.put("E", Types.SERIALIZABLE);
        final Type<?> jt4a = Types.forJavaLangReflectType(tm4, jt4av);
        assertEquals(et4a, jt4a);

        
        final Map<String,Type<?>> jt4bv = new HashMap<String, Type<?>>();
        jt4bv.put("R", Types.NUMBER);
        jt4bv.put("E", Types.INTEGER);
        final Type<?> jt4b = Types.forJavaLangReflectType(tm4, jt4bv);
        assertEquals(et4b, jt4b);

        
        final Map<String,Type<?>> jt4cv = new HashMap<String, Type<?>>();
        jt4cv.put("R", Types.INTEGER);
        jt4cv.put("E", Types.listOf(TypeParameters.forExtendsType(Types.NUMBER)));
        final Type<?> jt4c = Types.forJavaLangReflectType(tm4, jt4cv);
        assertEquals(et4c, jt4c);

        
        final Map<String,Type<?>> jt4dv = new HashMap<String, Type<?>>();
        jt4dv.put("R", Types.LIST_OF_UNKNOWN);
        jt4dv.put("E", Types.listOf(TypeParameters.forExtendsType(Types.NUMBER)));
        try {
            Types.forJavaLangReflectType(tm4, jt4dv);
            assertTrue(false);
        } catch (TypeValidationException e) {
            assertTrue(true);
        }

        
        final Map<String,Type<?>> jt5av = new HashMap<String, Type<?>>();
        jt5av.put("R", Types.STRING);
        final Type<?> jt5a = Types.forJavaLangReflectType(tm5, jt5av);
        assertEquals(et5a, jt5a);

        
        final Map<String,Type<?>> jt5bv = new HashMap<String, Type<?>>();
        jt5bv.put("R", Types.INTEGER);
        final Type<?> jt5b = Types.forJavaLangReflectType(tm5, jt5bv);
        assertEquals(et5b, jt5b);

        
        final Map<String,Type<?>> jt5cv = new HashMap<String, Type<?>>();
        jt5cv.put("R", Types.SERIALIZABLE);
        final Type<?> jt5c = Types.forJavaLangReflectType(tm5, jt5cv);
        assertEquals(et5c, jt5c);

        
        final Map<String,Type<?>> jt5dv = new HashMap<String, Type<?>>();
        jt5dv.put("R", Types.LIST_OF_UNKNOWN);
        final Type<?> jt5d = Types.forJavaLangReflectType(tm5, jt5dv);
        assertEquals(et5d, jt5d);

        
        final Map<String,Type<?>> jt6av = new HashMap<String, Type<?>>();
        jt6av.put("R", Types.STRING);
        final Type<?> jt6a = Types.forJavaLangReflectType(tm6, jt6av);
        assertEquals(et6a, jt6a);

        
        final Map<String,Type<?>> jt6bv = new HashMap<String, Type<?>>();
        jt6bv.put("R", Types.SERIALIZABLE);
        final Type<?> jt6b = Types.forJavaLangReflectType(tm6, jt6bv);
        assertEquals(et6b, jt6b);

        
        final Map<String,Type<?>> jt6cv = new HashMap<String, Type<?>>();
        jt6cv.put("R", Types.LIST_OF_UNKNOWN);
        try {
            Types.forJavaLangReflectType(tm6, jt6cv);
            assertTrue(false);
        } catch (TypeValidationException e) {
            assertTrue(true);
        }

        
        final Type<?> jt7 = Types.forJavaLangReflectType(tm7);
        assertEquals(et7, jt7);

        
        final Type<?> jt8 = Types.forJavaLangReflectType(tm8);
        assertEquals(et8, jt8);

        
        final Map<String,Type<?>> jt9av = new HashMap<String, Type<?>>();
        jt9av.put("E", Types.STRING);
        final Type<?> jt9a = Types.forJavaLangReflectType(tm9, jt9av);
        assertEquals(et9a, jt9a);

        
        final Map<String,Type<?>> jt9bv = new HashMap<String, Type<?>>();
        jt9bv.put("E", Types.INTEGER);
        final Type<?> jt9b = Types.forJavaLangReflectType(tm9, jt9bv);
        assertEquals(et9b, jt9b);

        
        final Map<String,Type<?>> jt9cv = new HashMap<String, Type<?>>();
        jt9cv.put("E", Types.listOf(Types.INTEGER));
        final Type<?> jt9c = Types.forJavaLangReflectType(tm9, jt9cv);
        assertEquals(et9c, jt9c);
        
    }

    
}
