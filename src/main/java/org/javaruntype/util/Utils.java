/*
 * =============================================================================
 * 
 *   Copyright (c) 2009, The JAVARUNTYPE team (http://www.javaruntype.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.javaruntype.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * This is an internal utility class. No method in this class should be used directly.
 * </p>
 * 
 * @since 1.1
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public class Utils {
    

    private static Map<String,String> primitiveClassAbbrevs = new HashMap<String,String>();

    
    static {
        primitiveClassAbbrevs.put("int", "I");
        primitiveClassAbbrevs.put("boolean", "Z");
        primitiveClassAbbrevs.put("float", "F");
        primitiveClassAbbrevs.put("long", "J");
        primitiveClassAbbrevs.put("short", "S");
        primitiveClassAbbrevs.put("byte", "B");
        primitiveClassAbbrevs.put("double", "D");
        primitiveClassAbbrevs.put("char", "C");
    }

    
    

    private Utils() {
      super();
    }

    
    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param object
     * @param message
     */
    public static void validateNotNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
    

    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param object
     */
    public static void validateNotNull(final Object object) {
        if (object == null) {
            throw new IllegalArgumentException("The validated object is null");
        }
    }

    
    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param expression
     */
    public static void validateIsTrue(final boolean expression) {
        if (expression == false) {
            throw new IllegalArgumentException("The validated expression is false");
        }
    }


    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param expression
     * @param message
     */
    public static void validateIsTrue(final boolean expression, final String message) {
        if (expression == false) {
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param left
     * @param right
     * @return
     */
    public static boolean isArrayEqual(final Object[] left, final Object[] right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.length != right.length) {
            return false;
        }
        for (int i = 0; i < left.length; ++i) {
            if (!isArrayElementEqual(left[i], right[i])) {
                return false;
            }
        }
        return true;
    }
    
    
    
    private static boolean isArrayElementEqual(final Object left, final Object right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        final Class<?> leftClass = left.getClass();
        if (!leftClass.isArray()) {
            if (left instanceof java.math.BigDecimal) {
                if (right instanceof BigDecimal) {
                    return (((BigDecimal)left).compareTo((BigDecimal)right) == 0);
                }
                return false;
            }
            return left.equals(right);
        } else if (left.getClass() != right.getClass()) {
            return false;
        }
        return isArrayEqual((Object[]) left, (Object[]) right);
    }

    
    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param array
     * @param separator
     * @return
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0, z = array.length; i < z; i++) {
            if (i > 0) {
                strBuilder.append(separator);
            }
            strBuilder.append(array[i]);
        }
        return strBuilder.toString();
    }


    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param string
     * @return
     */
    public static String removeAllWhitespaces(final String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        final int originalSize = string.length();
        final char[] charArray = new char[originalSize];
        int charCount = 0;
        for (int i = 0; i < originalSize; i++) {
            final char currentChar = string.charAt(i);
            if (!Character.isWhitespace(currentChar)) {
                charArray[charCount++] = currentChar;
            }
        }
        if (charCount == originalSize) {
            return string;
        }
        return new String(charArray, 0, charCount);
    }
    
    
    /**
     * <p>
     * Internal utility method. DO NOT use this method directly.
     * </p>
     * 
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getClass(final String className) throws ClassNotFoundException {
        
        final ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        final ClassLoader loader = 
            (contextCL == null ? Utils.class.getClassLoader() : contextCL);
        
        if (primitiveClassAbbrevs.containsKey(className)) {
            final String clsName = "[" + primitiveClassAbbrevs.get(className);
            return Class.forName(clsName, true, loader).getComponentType();
        }
        return Class.forName(getClassCanonicalName(className), true, loader);

    }

    
    private static String getClassCanonicalName(final String className) {
        String trimmedClassName = Utils.removeAllWhitespaces(className); 
        if (trimmedClassName == null) {
            throw new IllegalArgumentException("className cannot be null");
        }
        if (trimmedClassName.endsWith("[]")) {
            final StringBuilder strBuilder = new StringBuilder();
            while (trimmedClassName.endsWith("[]")) {
                trimmedClassName = trimmedClassName.substring(0, trimmedClassName.length() - 2);
                strBuilder.append("[");
            }
            final String abbreviation = primitiveClassAbbrevs.get(trimmedClassName);
            if (abbreviation != null) {
                strBuilder.append(abbreviation);
            } else {
                strBuilder.append("L").append(trimmedClassName).append(";");
            }
            trimmedClassName = strBuilder.toString();
        }
        return trimmedClassName;
    }
    
    
}
