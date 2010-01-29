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
package org.javaruntype.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang.Validate;

/**
 * <p>
 * This class centralizes the methods for obtaining {@link Type} instances.
 * </p>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class Types {

    public static final Type<Boolean> BOOLEAN = Types.forClass(Boolean.class);
    public static final Type<Byte> BYTE = Types.forClass(Byte.class);
    public static final Type<Character> CHARACTER = Types.forClass(Character.class);
    public static final Type<Double> DOUBLE = Types.forClass(Double.class);
    public static final Type<Float> FLOAT = Types.forClass(Float.class);
    public static final Type<Integer> INTEGER = Types.forClass(Integer.class);
    public static final Type<Long> LONG = Types.forClass(Long.class);
    public static final Type<Number> NUMBER = Types.forClass(Number.class);
    public static final Type<Object> OBJECT = Types.forClass(Object.class);
    public static final Type<Short> SHORT = Types.forClass(Short.class);
    public static final Type<String> STRING = Types.forClass(String.class);
    public static final Type<Date> DATE = Types.forClass(Date.class);
    public static final Type<Calendar> CALENDAR = Types.forClass(Calendar.class);
    public static final Type<BigDecimal> BIG_DECIMAL = Types.forClass(BigDecimal.class);
    public static final Type<BigInteger> BIG_INTEGER = Types.forClass(BigInteger.class);
    public static final Type<Serializable> SERIALIZABLE = Types.forClass(Serializable.class);

    public static final Type<Boolean[]> ARRAY_OF_BOOLEAN = Types.forClass(Boolean[].class);
    public static final Type<Byte[]> ARRAY_OF_BYTE = Types.forClass(Byte[].class);
    public static final Type<Character[]> ARRAY_OF_CHARACTER = Types.forClass(Character[].class);
    public static final Type<Double[]> ARRAY_OF_DOUBLE = Types.forClass(Double[].class);
    public static final Type<Float[]> ARRAY_OF_FLOAT = Types.forClass(Float[].class);
    public static final Type<Integer[]> ARRAY_OF_INTEGER = Types.forClass(Integer[].class);
    public static final Type<Long[]> ARRAY_OF_LONG = Types.forClass(Long[].class);
    public static final Type<Number[]> ARRAY_OF_NUMBER = Types.forClass(Number[].class);
    public static final Type<Object[]> ARRAY_OF_OBJECT = Types.forClass(Object[].class);
    public static final Type<Short[]> ARRAY_OF_SHORT = Types.forClass(Short[].class);
    public static final Type<String[]> ARRAY_OF_STRING = Types.forClass(String[].class);
    public static final Type<Date[]> ARRAY_OF_DATE = Types.forClass(Date[].class);
    public static final Type<Calendar[]> ARRAY_OF_CALENDAR = Types.forClass(Calendar[].class);
    public static final Type<BigDecimal[]> ARRAY_OF_BIG_DECIMAL = Types.forClass(BigDecimal[].class);
    public static final Type<BigInteger[]> ARRAY_OF_BIG_INTEGER = Types.forClass(BigInteger[].class);
    public static final Type<Serializable[]> ARRAY_OF_SERIALIZABLE = Types.forClass(Serializable[].class);

    public static final Type<Class<?>> CLASS_OF_UNKNOWN = Types.classOf(TypeParameters.forUnknown());
    public static final Type<Class<Boolean>> CLASS_OF_BOOLEAN = Types.classOf(Types.BOOLEAN);
    public static final Type<Class<Byte>> CLASS_OF_BYTE = Types.classOf(Types.BYTE);
    public static final Type<Class<Character>> CLASS_OF_CHARACTER = Types.classOf(Types.CHARACTER);
    public static final Type<Class<Double>> CLASS_OF_DOUBLE = Types.classOf(Types.DOUBLE);
    public static final Type<Class<Float>> CLASS_OF_FLOAT = Types.classOf(Types.FLOAT);
    public static final Type<Class<Integer>> CLASS_OF_INTEGER = Types.classOf(Types.INTEGER);
    public static final Type<Class<Long>> CLASS_OF_LONG = Types.classOf(Types.LONG);
    public static final Type<Class<Number>> CLASS_OF_NUMBER = Types.classOf(Types.NUMBER);
    public static final Type<Class<Object>> CLASS_OF_OBJECT = Types.classOf(Types.OBJECT);
    public static final Type<Class<Short>> CLASS_OF_SHORT = Types.classOf(Types.SHORT);
    public static final Type<Class<String>> CLASS_OF_STRING = Types.classOf(Types.STRING);
    public static final Type<Class<Date>> CLASS_OF_DATE = Types.classOf(Types.DATE);
    public static final Type<Class<Calendar>> CLASS_OF_CALENDAR = Types.classOf(Types.CALENDAR);
    public static final Type<Class<BigDecimal>> CLASS_OF_BIG_DECIMAL = Types.classOf(Types.BIG_DECIMAL);
    public static final Type<Class<BigInteger>> CLASS_OF_BIG_INTEGER = Types.classOf(Types.BIG_INTEGER);
    public static final Type<Class<Serializable>> CLASS_OF_SERIALIZABLE = Types.classOf(Types.SERIALIZABLE);

    public static final Type<Iterable<?>> ITERABLE_OF_UNKNOWN = Types.iterableOf(TypeParameters.forUnknown());
    public static final Type<Iterable<Boolean>> ITERABLE_OF_BOOLEAN = Types.iterableOf(Types.BOOLEAN);
    public static final Type<Iterable<Byte>> ITERABLE_OF_BYTE = Types.iterableOf(Types.BYTE);
    public static final Type<Iterable<Character>> ITERABLE_OF_CHARACTER = Types.iterableOf(Types.CHARACTER);
    public static final Type<Iterable<Double>> ITERABLE_OF_DOUBLE = Types.iterableOf(Types.DOUBLE);
    public static final Type<Iterable<Float>> ITERABLE_OF_FLOAT = Types.iterableOf(Types.FLOAT);
    public static final Type<Iterable<Integer>> ITERABLE_OF_INTEGER = Types.iterableOf(Types.INTEGER);
    public static final Type<Iterable<Long>> ITERABLE_OF_LONG = Types.iterableOf(Types.LONG);
    public static final Type<Iterable<Number>> ITERABLE_OF_NUMBER = Types.iterableOf(Types.NUMBER);
    public static final Type<Iterable<Object>> ITERABLE_OF_OBJECT = Types.iterableOf(Types.OBJECT);
    public static final Type<Iterable<Short>> ITERABLE_OF_SHORT = Types.iterableOf(Types.SHORT);
    public static final Type<Iterable<String>> ITERABLE_OF_STRING = Types.iterableOf(Types.STRING);
    public static final Type<Iterable<Date>> ITERABLE_OF_DATE = Types.iterableOf(Types.DATE);
    public static final Type<Iterable<Calendar>> ITERABLE_OF_CALENDAR = Types.iterableOf(Types.CALENDAR);
    public static final Type<Iterable<BigDecimal>> ITERABLE_OF_BIG_DECIMAL = Types.iterableOf(Types.BIG_DECIMAL);
    public static final Type<Iterable<BigInteger>> ITERABLE_OF_BIG_INTEGER = Types.iterableOf(Types.BIG_INTEGER);
    public static final Type<Iterable<Serializable>> ITERABLE_OF_SERIALIZABLE = Types.iterableOf(Types.SERIALIZABLE);

    public static final Type<Collection<?>> COLLECTION_OF_UNKNOWN = Types.collectionOf(TypeParameters.forUnknown());
    public static final Type<Collection<Boolean>> COLLECTION_OF_BOOLEAN = Types.collectionOf(Types.BOOLEAN);
    public static final Type<Collection<Byte>> COLLECTION_OF_BYTE = Types.collectionOf(Types.BYTE);
    public static final Type<Collection<Character>> COLLECTION_OF_CHARACTER = Types.collectionOf(Types.CHARACTER);
    public static final Type<Collection<Double>> COLLECTION_OF_DOUBLE = Types.collectionOf(Types.DOUBLE);
    public static final Type<Collection<Float>> COLLECTION_OF_FLOAT = Types.collectionOf(Types.FLOAT);
    public static final Type<Collection<Integer>> COLLECTION_OF_INTEGER = Types.collectionOf(Types.INTEGER);
    public static final Type<Collection<Long>> COLLECTION_OF_LONG = Types.collectionOf(Types.LONG);
    public static final Type<Collection<Number>> COLLECTION_OF_NUMBER = Types.collectionOf(Types.NUMBER);
    public static final Type<Collection<Object>> COLLECTION_OF_OBJECT = Types.collectionOf(Types.OBJECT);
    public static final Type<Collection<Short>> COLLECTION_OF_SHORT = Types.collectionOf(Types.SHORT);
    public static final Type<Collection<String>> COLLECTION_OF_STRING = Types.collectionOf(Types.STRING);
    public static final Type<Collection<Date>> COLLECTION_OF_DATE = Types.collectionOf(Types.DATE);
    public static final Type<Collection<Calendar>> COLLECTION_OF_CALENDAR = Types.collectionOf(Types.CALENDAR);
    public static final Type<Collection<BigDecimal>> COLLECTION_OF_BIG_DECIMAL = Types.collectionOf(Types.BIG_DECIMAL);
    public static final Type<Collection<BigInteger>> COLLECTION_OF_BIG_INTEGER = Types.collectionOf(Types.BIG_INTEGER);
    public static final Type<Collection<Serializable>> COLLECTION_OF_SERIALIZABLE = Types.collectionOf(Types.SERIALIZABLE);

    public static final Type<Comparator<?>> COMPARATOR_OF_UNKNOWN = Types.comparatorOf(TypeParameters.forUnknown());
    public static final Type<Comparator<Boolean>> COMPARATOR_OF_BOOLEAN = Types.comparatorOf(Types.BOOLEAN);
    public static final Type<Comparator<Byte>> COMPARATOR_OF_BYTE = Types.comparatorOf(Types.BYTE);
    public static final Type<Comparator<Character>> COMPARATOR_OF_CHARACTER = Types.comparatorOf(Types.CHARACTER);
    public static final Type<Comparator<Double>> COMPARATOR_OF_DOUBLE = Types.comparatorOf(Types.DOUBLE);
    public static final Type<Comparator<Float>> COMPARATOR_OF_FLOAT = Types.comparatorOf(Types.FLOAT);
    public static final Type<Comparator<Integer>> COMPARATOR_OF_INTEGER = Types.comparatorOf(Types.INTEGER);
    public static final Type<Comparator<Long>> COMPARATOR_OF_LONG = Types.comparatorOf(Types.LONG);
    public static final Type<Comparator<Number>> COMPARATOR_OF_NUMBER = Types.comparatorOf(Types.NUMBER);
    public static final Type<Comparator<Object>> COMPARATOR_OF_OBJECT = Types.comparatorOf(Types.OBJECT);
    public static final Type<Comparator<Short>> COMPARATOR_OF_SHORT = Types.comparatorOf(Types.SHORT);
    public static final Type<Comparator<String>> COMPARATOR_OF_STRING = Types.comparatorOf(Types.STRING);
    public static final Type<Comparator<Date>> COMPARATOR_OF_DATE = Types.comparatorOf(Types.DATE);
    public static final Type<Comparator<Calendar>> COMPARATOR_OF_CALENDAR = Types.comparatorOf(Types.CALENDAR);
    public static final Type<Comparator<BigDecimal>> COMPARATOR_OF_BIG_DECIMAL = Types.comparatorOf(Types.BIG_DECIMAL);
    public static final Type<Comparator<BigInteger>> COMPARATOR_OF_BIG_INTEGER = Types.comparatorOf(Types.BIG_INTEGER);
    public static final Type<Comparator<Serializable>> COMPARATOR_OF_SERIALIZABLE = Types.comparatorOf(Types.SERIALIZABLE);

    public static final Type<Enumeration<?>> ENUMERATION_OF_UNKNOWN = Types.enumerationOf(TypeParameters.forUnknown());
    public static final Type<Enumeration<Boolean>> ENUMERATION_OF_BOOLEAN = Types.enumerationOf(Types.BOOLEAN);
    public static final Type<Enumeration<Byte>> ENUMERATION_OF_BYTE = Types.enumerationOf(Types.BYTE);
    public static final Type<Enumeration<Character>> ENUMERATION_OF_CHARACTER = Types.enumerationOf(Types.CHARACTER);
    public static final Type<Enumeration<Double>> ENUMERATION_OF_DOUBLE = Types.enumerationOf(Types.DOUBLE);
    public static final Type<Enumeration<Float>> ENUMERATION_OF_FLOAT = Types.enumerationOf(Types.FLOAT);
    public static final Type<Enumeration<Integer>> ENUMERATION_OF_INTEGER = Types.enumerationOf(Types.INTEGER);
    public static final Type<Enumeration<Long>> ENUMERATION_OF_LONG = Types.enumerationOf(Types.LONG);
    public static final Type<Enumeration<Number>> ENUMERATION_OF_NUMBER = Types.enumerationOf(Types.NUMBER);
    public static final Type<Enumeration<Object>> ENUMERATION_OF_OBJECT = Types.enumerationOf(Types.OBJECT);
    public static final Type<Enumeration<Short>> ENUMERATION_OF_SHORT = Types.enumerationOf(Types.SHORT);
    public static final Type<Enumeration<String>> ENUMERATION_OF_STRING = Types.enumerationOf(Types.STRING);
    public static final Type<Enumeration<Date>> ENUMERATION_OF_DATE = Types.enumerationOf(Types.DATE);
    public static final Type<Enumeration<Calendar>> ENUMERATION_OF_CALENDAR = Types.enumerationOf(Types.CALENDAR);
    public static final Type<Enumeration<BigDecimal>> ENUMERATION_OF_BIG_DECIMAL = Types.enumerationOf(Types.BIG_DECIMAL);
    public static final Type<Enumeration<BigInteger>> ENUMERATION_OF_BIG_INTEGER = Types.enumerationOf(Types.BIG_INTEGER);
    public static final Type<Enumeration<Serializable>> ENUMERATION_OF_SERIALIZABLE = Types.enumerationOf(Types.SERIALIZABLE);

    public static final Type<Iterator<?>> ITERATOR_OF_UNKNOWN = Types.iteratorOf(TypeParameters.forUnknown());
    public static final Type<Iterator<Boolean>> ITERATOR_OF_BOOLEAN = Types.iteratorOf(Types.BOOLEAN);
    public static final Type<Iterator<Byte>> ITERATOR_OF_BYTE = Types.iteratorOf(Types.BYTE);
    public static final Type<Iterator<Character>> ITERATOR_OF_CHARACTER = Types.iteratorOf(Types.CHARACTER);
    public static final Type<Iterator<Double>> ITERATOR_OF_DOUBLE = Types.iteratorOf(Types.DOUBLE);
    public static final Type<Iterator<Float>> ITERATOR_OF_FLOAT = Types.iteratorOf(Types.FLOAT);
    public static final Type<Iterator<Integer>> ITERATOR_OF_INTEGER = Types.iteratorOf(Types.INTEGER);
    public static final Type<Iterator<Long>> ITERATOR_OF_LONG = Types.iteratorOf(Types.LONG);
    public static final Type<Iterator<Number>> ITERATOR_OF_NUMBER = Types.iteratorOf(Types.NUMBER);
    public static final Type<Iterator<Object>> ITERATOR_OF_OBJECT = Types.iteratorOf(Types.OBJECT);
    public static final Type<Iterator<Short>> ITERATOR_OF_SHORT = Types.iteratorOf(Types.SHORT);
    public static final Type<Iterator<String>> ITERATOR_OF_STRING = Types.iteratorOf(Types.STRING);
    public static final Type<Iterator<Date>> ITERATOR_OF_DATE = Types.iteratorOf(Types.DATE);
    public static final Type<Iterator<Calendar>> ITERATOR_OF_CALENDAR = Types.iteratorOf(Types.CALENDAR);
    public static final Type<Iterator<BigDecimal>> ITERATOR_OF_BIG_DECIMAL = Types.iteratorOf(Types.BIG_DECIMAL);
    public static final Type<Iterator<BigInteger>> ITERATOR_OF_BIG_INTEGER = Types.iteratorOf(Types.BIG_INTEGER);
    public static final Type<Iterator<Serializable>> ITERATOR_OF_SERIALIZABLE = Types.iteratorOf(Types.SERIALIZABLE);

    public static final Type<List<?>> LIST_OF_UNKNOWN = Types.listOf(TypeParameters.forUnknown());
    public static final Type<List<Boolean>> LIST_OF_BOOLEAN = Types.listOf(Types.BOOLEAN);
    public static final Type<List<Byte>> LIST_OF_BYTE = Types.listOf(Types.BYTE);
    public static final Type<List<Character>> LIST_OF_CHARACTER = Types.listOf(Types.CHARACTER);
    public static final Type<List<Double>> LIST_OF_DOUBLE = Types.listOf(Types.DOUBLE);
    public static final Type<List<Float>> LIST_OF_FLOAT = Types.listOf(Types.FLOAT);
    public static final Type<List<Integer>> LIST_OF_INTEGER = Types.listOf(Types.INTEGER);
    public static final Type<List<Long>> LIST_OF_LONG = Types.listOf(Types.LONG);
    public static final Type<List<Number>> LIST_OF_NUMBER = Types.listOf(Types.NUMBER);
    public static final Type<List<Object>> LIST_OF_OBJECT = Types.listOf(Types.OBJECT);
    public static final Type<List<Short>> LIST_OF_SHORT = Types.listOf(Types.SHORT);
    public static final Type<List<String>> LIST_OF_STRING = Types.listOf(Types.STRING);
    public static final Type<List<Date>> LIST_OF_DATE = Types.listOf(Types.DATE);
    public static final Type<List<Calendar>> LIST_OF_CALENDAR = Types.listOf(Types.CALENDAR);
    public static final Type<List<BigDecimal>> LIST_OF_BIG_DECIMAL = Types.listOf(Types.BIG_DECIMAL);
    public static final Type<List<BigInteger>> LIST_OF_BIG_INTEGER = Types.listOf(Types.BIG_INTEGER);
    public static final Type<List<Serializable>> LIST_OF_SERIALIZABLE = Types.listOf(Types.SERIALIZABLE);

    public static final Type<ListIterator<?>> LIST_ITERATOR_OF_UNKNOWN = Types.listIteratorOf(TypeParameters.forUnknown());
    public static final Type<ListIterator<Boolean>> LIST_ITERATOR_OF_BOOLEAN = Types.listIteratorOf(Types.BOOLEAN);
    public static final Type<ListIterator<Byte>> LIST_ITERATOR_OF_BYTE = Types.listIteratorOf(Types.BYTE);
    public static final Type<ListIterator<Character>> LIST_ITERATOR_OF_CHARACTER = Types.listIteratorOf(Types.CHARACTER);
    public static final Type<ListIterator<Double>> LIST_ITERATOR_OF_DOUBLE = Types.listIteratorOf(Types.DOUBLE);
    public static final Type<ListIterator<Float>> LIST_ITERATOR_OF_FLOAT = Types.listIteratorOf(Types.FLOAT);
    public static final Type<ListIterator<Integer>> LIST_ITERATOR_OF_INTEGER = Types.listIteratorOf(Types.INTEGER);
    public static final Type<ListIterator<Long>> LIST_ITERATOR_OF_LONG = Types.listIteratorOf(Types.LONG);
    public static final Type<ListIterator<Number>> LIST_ITERATOR_OF_NUMBER = Types.listIteratorOf(Types.NUMBER);
    public static final Type<ListIterator<Object>> LIST_ITERATOR_OF_OBJECT = Types.listIteratorOf(Types.OBJECT);
    public static final Type<ListIterator<Short>> LIST_ITERATOR_OF_SHORT = Types.listIteratorOf(Types.SHORT);
    public static final Type<ListIterator<String>> LIST_ITERATOR_OF_STRING = Types.listIteratorOf(Types.STRING);
    public static final Type<ListIterator<Date>> LIST_ITERATOR_OF_DATE = Types.listIteratorOf(Types.DATE);
    public static final Type<ListIterator<Calendar>> LIST_ITERATOR_OF_CALENDAR = Types.listIteratorOf(Types.CALENDAR);
    public static final Type<ListIterator<BigDecimal>> LIST_ITERATOR_OF_BIG_DECIMAL = Types.listIteratorOf(Types.BIG_DECIMAL);
    public static final Type<ListIterator<BigInteger>> LIST_ITERATOR_OF_BIG_INTEGER = Types.listIteratorOf(Types.BIG_INTEGER);
    public static final Type<ListIterator<Serializable>> LIST_ITERATOR_OF_SERIALIZABLE = Types.listIteratorOf(Types.SERIALIZABLE);

    public static final Type<Map<?,?>> MAP_OF_UNKNOWN_UNKNOWN = Types.mapOf(TypeParameters.forUnknown(), TypeParameters.forUnknown());
    public static final Type<Map<String,?>> MAP_OF_STRING_UNKNOWN = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forUnknown());
    public static final Type<Map<String,Boolean>> MAP_OF_STRING_BOOLEAN = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BOOLEAN));
    public static final Type<Map<String,Byte>> MAP_OF_STRING_BYTE = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BYTE));
    public static final Type<Map<String,Character>> MAP_OF_STRING_CHARACTER = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.CHARACTER));
    public static final Type<Map<String,Double>> MAP_OF_STRING_DOUBLE = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.DOUBLE));
    public static final Type<Map<String,Float>> MAP_OF_STRING_FLOAT = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.FLOAT));
    public static final Type<Map<String,Integer>> MAP_OF_STRING_INTEGER = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.INTEGER));
    public static final Type<Map<String,Long>> MAP_OF_STRING_LONG = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.LONG));
    public static final Type<Map<String,Number>> MAP_OF_STRING_NUMBER = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.NUMBER));
    public static final Type<Map<String,Object>> MAP_OF_STRING_OBJECT = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.OBJECT));
    public static final Type<Map<String,Short>> MAP_OF_STRING_SHORT = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.SHORT));
    public static final Type<Map<String,String>> MAP_OF_STRING_STRING = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.STRING));
    public static final Type<Map<String,Date>> MAP_OF_STRING_DATE = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.DATE));
    public static final Type<Map<String,Calendar>> MAP_OF_STRING_CALENDAR = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.CALENDAR));
    public static final Type<Map<String,BigDecimal>> MAP_OF_STRING_BIG_DECIMAL = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BIG_DECIMAL));
    public static final Type<Map<String,BigInteger>> MAP_OF_STRING_BIG_INTEGER = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BIG_INTEGER));
    public static final Type<Map<String,Serializable>> MAP_OF_STRING_SERIALIZABLE = Types.mapOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.SERIALIZABLE));

    public static final Type<Map.Entry<?,?>> MAP_ENTRY_OF_UNKNOWN_UNKNOWN = Types.mapEntryOf(TypeParameters.forUnknown(), TypeParameters.forUnknown());
    public static final Type<Map.Entry<String,?>> MAP_ENTRY_OF_STRING_UNKNOWN = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forUnknown());
    public static final Type<Map.Entry<String,Boolean>> MAP_ENTRY_OF_STRING_BOOLEAN = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BOOLEAN));
    public static final Type<Map.Entry<String,Byte>> MAP_ENTRY_OF_STRING_BYTE = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BYTE));
    public static final Type<Map.Entry<String,Character>> MAP_ENTRY_OF_STRING_CHARACTER = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.CHARACTER));
    public static final Type<Map.Entry<String,Double>> MAP_ENTRY_OF_STRING_DOUBLE = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.DOUBLE));
    public static final Type<Map.Entry<String,Float>> MAP_ENTRY_OF_STRING_FLOAT = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.FLOAT));
    public static final Type<Map.Entry<String,Integer>> MAP_ENTRY_OF_STRING_INTEGER = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.INTEGER));
    public static final Type<Map.Entry<String,Long>> MAP_ENTRY_OF_STRING_LONG = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.LONG));
    public static final Type<Map.Entry<String,Number>> MAP_ENTRY_OF_STRING_NUMBER = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.NUMBER));
    public static final Type<Map.Entry<String,Object>> MAP_ENTRY_OF_STRING_OBJECT = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.OBJECT));
    public static final Type<Map.Entry<String,Short>> MAP_ENTRY_OF_STRING_SHORT = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.SHORT));
    public static final Type<Map.Entry<String,String>> MAP_ENTRY_OF_STRING_STRING = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.STRING));
    public static final Type<Map.Entry<String,Date>> MAP_ENTRY_OF_STRING_DATE = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.DATE));
    public static final Type<Map.Entry<String,Calendar>> MAP_ENTRY_OF_STRING_CALENDAR = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.CALENDAR));
    public static final Type<Map.Entry<String,BigDecimal>> MAP_ENTRY_OF_STRING_BIG_DECIMAL = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BIG_DECIMAL));
    public static final Type<Map.Entry<String,BigInteger>> MAP_ENTRY_OF_STRING_BIG_INTEGER = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.BIG_INTEGER));
    public static final Type<Map.Entry<String,Serializable>> MAP_ENTRY_OF_STRING_SERIALIZABLE = Types.mapEntryOf(TypeParameters.forType(Types.STRING), TypeParameters.forType(Types.SERIALIZABLE));

    public static final Type<Queue<?>> QUEUE_OF_UNKNOWN = Types.queueOf(TypeParameters.forUnknown());
    public static final Type<Queue<Boolean>> QUEUE_OF_BOOLEAN = Types.queueOf(Types.BOOLEAN);
    public static final Type<Queue<Byte>> QUEUE_OF_BYTE = Types.queueOf(Types.BYTE);
    public static final Type<Queue<Character>> QUEUE_OF_CHARACTER = Types.queueOf(Types.CHARACTER);
    public static final Type<Queue<Double>> QUEUE_OF_DOUBLE = Types.queueOf(Types.DOUBLE);
    public static final Type<Queue<Float>> QUEUE_OF_FLOAT = Types.queueOf(Types.FLOAT);
    public static final Type<Queue<Integer>> QUEUE_OF_INTEGER = Types.queueOf(Types.INTEGER);
    public static final Type<Queue<Long>> QUEUE_OF_LONG = Types.queueOf(Types.LONG);
    public static final Type<Queue<Number>> QUEUE_OF_NUMBER = Types.queueOf(Types.NUMBER);
    public static final Type<Queue<Object>> QUEUE_OF_OBJECT = Types.queueOf(Types.OBJECT);
    public static final Type<Queue<Short>> QUEUE_OF_SHORT = Types.queueOf(Types.SHORT);
    public static final Type<Queue<String>> QUEUE_OF_STRING = Types.queueOf(Types.STRING);
    public static final Type<Queue<Date>> QUEUE_OF_DATE = Types.queueOf(Types.DATE);
    public static final Type<Queue<Calendar>> QUEUE_OF_CALENDAR = Types.queueOf(Types.CALENDAR);
    public static final Type<Queue<BigDecimal>> QUEUE_OF_BIG_DECIMAL = Types.queueOf(Types.BIG_DECIMAL);
    public static final Type<Queue<BigInteger>> QUEUE_OF_BIG_INTEGER = Types.queueOf(Types.BIG_INTEGER);
    public static final Type<Queue<Serializable>> QUEUE_OF_SERIALIZABLE = Types.queueOf(Types.SERIALIZABLE);

    public static final Type<Set<?>> SET_OF_UNKNOWN = Types.setOf(TypeParameters.forUnknown());
    public static final Type<Set<Boolean>> SET_OF_BOOLEAN = Types.setOf(Types.BOOLEAN);
    public static final Type<Set<Byte>> SET_OF_BYTE = Types.setOf(Types.BYTE);
    public static final Type<Set<Character>> SET_OF_CHARACTER = Types.setOf(Types.CHARACTER);
    public static final Type<Set<Double>> SET_OF_DOUBLE = Types.setOf(Types.DOUBLE);
    public static final Type<Set<Float>> SET_OF_FLOAT = Types.setOf(Types.FLOAT);
    public static final Type<Set<Integer>> SET_OF_INTEGER = Types.setOf(Types.INTEGER);
    public static final Type<Set<Long>> SET_OF_LONG = Types.setOf(Types.LONG);
    public static final Type<Set<Number>> SET_OF_NUMBER = Types.setOf(Types.NUMBER);
    public static final Type<Set<Object>> SET_OF_OBJECT = Types.setOf(Types.OBJECT);
    public static final Type<Set<Short>> SET_OF_SHORT = Types.setOf(Types.SHORT);
    public static final Type<Set<String>> SET_OF_STRING = Types.setOf(Types.STRING);
    public static final Type<Set<Date>> SET_OF_DATE = Types.setOf(Types.DATE);
    public static final Type<Set<Calendar>> SET_OF_CALENDAR = Types.setOf(Types.CALENDAR);
    public static final Type<Set<BigDecimal>> SET_OF_BIG_DECIMAL = Types.setOf(Types.BIG_DECIMAL);
    public static final Type<Set<BigInteger>> SET_OF_BIG_INTEGER = Types.setOf(Types.BIG_INTEGER);
    public static final Type<Set<Serializable>> SET_OF_SERIALIZABLE = Types.setOf(Types.SERIALIZABLE);
    

    
    
    

    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> forClass(final Class<T> typeClass) {
        Validate.notNull(typeClass, "Type class cannot be null");
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return (Type<T>) typeRegistry.getRawTypeForClass(typeClass);
    }

    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> forClass(final Class<? super T> componentClass, final TypeParameter<?>... typeParameters) {
        Validate.notNull(componentClass, "Component class cannot be null");
        return (Type<T>) TypeUtil.getTypeWithParameters(componentClass, typeParameters);
    }


    
    public static Type<?> forName(final String typeName) {
        Validate.notNull(typeName, "Type name cannot be null");
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.forName(typeName);
    }
    
    
    
    
    
    public static <T> Type<T[]> arrayOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return TypeUtil.increaseArrayDimensions(type);
    }


    public static <T> Type<T> arrayComponentOf(final Type<T[]> type) {
        Validate.notNull(type, "Type cannot be null");
        return TypeUtil.decreaseArrayDimensions(type);
    }

    
    
    
    public static <T> Type<Iterable<T>> iterableOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return iterableOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> iterableComponentOf(final Type<Iterable<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterable<T>> iterableOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterable<T>>) TypeUtil.getTypeWithParameters(Iterable.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterable<? extends T>> iterableOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterable<? extends T>>) TypeUtil.getTypeWithParameters(Iterable.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterable<? super T>> iterableOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterable<? super T>>) TypeUtil.getTypeWithParameters(Iterable.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterable<?>> iterableOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterable<?>>) TypeUtil.getTypeWithParameters(Iterable.class, typeParameter);
    }

    
    
    
    
    public static <T> Type<Class<T>> classOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return classOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> classComponentOf(final Type<Class<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Class<T>> classOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Class<T>>) TypeUtil.getTypeWithParameters(Class.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Class<? extends T>> classOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Class<? extends T>>) TypeUtil.getTypeWithParameters(Class.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Class<? super T>> classOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Class<? super T>>) TypeUtil.getTypeWithParameters(Class.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Class<?>> classOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Class<?>>) TypeUtil.getTypeWithParameters(Class.class, typeParameter);
    }

    
    
    public static <T> Type<Collection<T>> collectionOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return collectionOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> collectionComponentOf(final Type<Collection<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Collection<T>> collectionOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Collection<T>>) TypeUtil.getTypeWithParameters(Collection.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Collection<? extends T>> collectionOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Collection<? extends T>>) TypeUtil.getTypeWithParameters(Collection.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Collection<? super T>> collectionOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Collection<? super T>>) TypeUtil.getTypeWithParameters(Collection.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Collection<?>> collectionOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Collection<?>>) TypeUtil.getTypeWithParameters(Collection.class, typeParameter);
    }

    
    
    public static <T> Type<Comparator<T>> comparatorOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return comparatorOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> comparatorComponentOf(final Type<Comparator<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Comparator<T>> comparatorOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Comparator<T>>) TypeUtil.getTypeWithParameters(Comparator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Comparator<? extends T>> comparatorOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Comparator<? extends T>>) TypeUtil.getTypeWithParameters(Comparator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Comparator<? super T>> comparatorOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Comparator<? super T>>) TypeUtil.getTypeWithParameters(Comparator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Comparator<?>> comparatorOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Comparator<?>>) TypeUtil.getTypeWithParameters(Comparator.class, typeParameter);
    }

    
    
    public static <T> Type<Enumeration<T>> enumerationOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return enumerationOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> enumerationComponentOf(final Type<Enumeration<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Enumeration<T>> enumerationOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Enumeration<T>>) TypeUtil.getTypeWithParameters(Enumeration.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Enumeration<? extends T>> enumerationOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Enumeration<? extends T>>) TypeUtil.getTypeWithParameters(Enumeration.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Enumeration<? super T>> enumerationOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Enumeration<? super T>>) TypeUtil.getTypeWithParameters(Enumeration.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Enumeration<?>> enumerationOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Enumeration<?>>) TypeUtil.getTypeWithParameters(Enumeration.class, typeParameter);
    }

    
    
    public static <T> Type<Iterator<T>> iteratorOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return iteratorOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> iteratorComponentOf(final Type<Iterator<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterator<T>> iteratorOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterator<T>>) TypeUtil.getTypeWithParameters(Iterator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterator<? extends T>> iteratorOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterator<? extends T>>) TypeUtil.getTypeWithParameters(Iterator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterator<? super T>> iteratorOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterator<? super T>>) TypeUtil.getTypeWithParameters(Iterator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Iterator<?>> iteratorOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Iterator<?>>) TypeUtil.getTypeWithParameters(Iterator.class, typeParameter);
    }
    

    
    
    public static <T> Type<List<T>> listOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return listOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> listComponentOf(final Type<List<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<List<T>> listOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<List<T>>) TypeUtil.getTypeWithParameters(List.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<List<? extends T>> listOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<List<? extends T>>) TypeUtil.getTypeWithParameters(List.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<List<? super T>> listOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<List<? super T>>) TypeUtil.getTypeWithParameters(List.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<List<?>> listOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<List<?>>) TypeUtil.getTypeWithParameters(List.class, typeParameter);
    }
    

    
    
    public static <T> Type<ListIterator<T>> listIteratorOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return listIteratorOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> listIteratorComponentOf(final Type<ListIterator<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<ListIterator<T>> listIteratorOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<ListIterator<T>>) TypeUtil.getTypeWithParameters(ListIterator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<ListIterator<? extends T>> listIteratorOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<ListIterator<? extends T>>) TypeUtil.getTypeWithParameters(ListIterator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<ListIterator<? super T>> listIteratorOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<ListIterator<? super T>>) TypeUtil.getTypeWithParameters(ListIterator.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<ListIterator<?>> listIteratorOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<ListIterator<?>>) TypeUtil.getTypeWithParameters(ListIterator.class, typeParameter);
    }
    

    
    
    public static <K,V> Type<Map<K,V>> mapOf(final Type<K> keyType, final Type<V> valueType) {
        Validate.notNull(keyType, "Key type cannot be null");
        Validate.notNull(valueType, "Value type cannot be null");
        return mapOf(TypeParameters.forType(keyType), TypeParameters.forType(valueType));
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<K> mapKeyComponentOf(final Type<Map<K,V>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<K>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<V> mapValueComponentOf(final Type<Map<K,V>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<V>) type.getTypeParameters().get(1).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<K,V>> mapOf(final StandardTypeParameter<K> keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<K,V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? extends K,V>> mapOf(final ExtendsTypeParameter<K> keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? extends K,V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? super K,V>> mapOf(final SuperTypeParameter<K> keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? super K,V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<?,V>> mapOf(final WildcardTypeParameter keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<?,V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<K,? extends V>> mapOf(final StandardTypeParameter<K> keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<K,? extends V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? extends K,? extends V>> mapOf(final ExtendsTypeParameter<K> keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? extends K,? extends V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? super K,? extends V>> mapOf(final SuperTypeParameter<K> keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? super K,? extends V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<?,? extends V>> mapOf(final WildcardTypeParameter keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<?,? extends V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<K,? super V>> mapOf(final StandardTypeParameter<K> keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<K,? super V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? extends K,? super V>> mapOf(final ExtendsTypeParameter<K> keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? extends K,? super V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? super K,? super V>> mapOf(final SuperTypeParameter<K> keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? super K,? super V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<?,? super V>> mapOf(final WildcardTypeParameter keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<?,? super V>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<K,?>> mapOf(final StandardTypeParameter<K> keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<K,?>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? extends K,?>> mapOf(final ExtendsTypeParameter<K> keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? extends K,?>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<? super K,?>> mapOf(final SuperTypeParameter<K> keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<? super K,?>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map<?,?>> mapOf(final WildcardTypeParameter keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map<?,?>>) TypeUtil.getTypeWithParameters(Map.class, keyTypeParameter, valueTypeParameter);
    }
    

    
    
    public static <K,V> Type<Map.Entry<K,V>> mapEntryOf(final Type<K> keyType, final Type<V> valueType) {
        Validate.notNull(keyType, "Key type cannot be null");
        Validate.notNull(valueType, "Value type cannot be null");
        return mapEntryOf(TypeParameters.forType(keyType), TypeParameters.forType(valueType));
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<K> mapEntryKeyComponentOf(final Type<Map.Entry<K,V>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<K>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<V> mapEntryValueComponentOf(final Type<Map.Entry<K,V>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<V>) type.getTypeParameters().get(1).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<K,V>> mapEntryOf(final StandardTypeParameter<K> keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<K,V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? extends K,V>> mapEntryOf(final ExtendsTypeParameter<K> keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? extends K,V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? super K,V>> mapEntryOf(final SuperTypeParameter<K> keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? super K,V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<?,V>> mapEntryOf(final WildcardTypeParameter keyTypeParameter, final StandardTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<?,V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<K,? extends V>> mapEntryOf(final StandardTypeParameter<K> keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<K,? extends V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? extends K,? extends V>> mapEntryOf(final ExtendsTypeParameter<K> keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? extends K,? extends V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? super K,? extends V>> mapEntryOf(final SuperTypeParameter<K> keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? super K,? extends V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<?,? extends V>> mapEntryOf(final WildcardTypeParameter keyTypeParameter, final ExtendsTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<?,? extends V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<K,? super V>> mapEntryOf(final StandardTypeParameter<K> keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<K,? super V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? extends K,? super V>> mapEntryOf(final ExtendsTypeParameter<K> keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? extends K,? super V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? super K,? super V>> mapEntryOf(final SuperTypeParameter<K> keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? super K,? super V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<?,? super V>> mapEntryOf(final WildcardTypeParameter keyTypeParameter, final SuperTypeParameter<V> valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<?,? super V>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<K,?>> mapEntryOf(final StandardTypeParameter<K> keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<K,?>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? extends K,?>> mapEntryOf(final ExtendsTypeParameter<K> keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? extends K,?>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<? super K,?>> mapEntryOf(final SuperTypeParameter<K> keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<? super K,?>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <K,V> Type<Map.Entry<?,?>> mapEntryOf(final WildcardTypeParameter keyTypeParameter, final WildcardTypeParameter valueTypeParameter) {
        Validate.notNull(keyTypeParameter, "Key type parameter cannot be null");
        Validate.notNull(valueTypeParameter, "Value type parameter cannot be null");
        return (Type<Map.Entry<?,?>>) TypeUtil.getTypeWithParameters(Map.Entry.class, keyTypeParameter, valueTypeParameter);
    }
    

    
    
    public static <T> Type<Queue<T>> queueOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return queueOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> queueComponentOf(final Type<Queue<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Queue<T>> queueOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Queue<T>>) TypeUtil.getTypeWithParameters(Queue.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Queue<? extends T>> queueOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Queue<? extends T>>) TypeUtil.getTypeWithParameters(Queue.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Queue<? super T>> queueOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Queue<? super T>>) TypeUtil.getTypeWithParameters(Queue.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Queue<?>> queueOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Queue<?>>) TypeUtil.getTypeWithParameters(Queue.class, typeParameter);
    }
    

    
    
    public static <T> Type<Set<T>> setOf(final Type<T> type) {
        Validate.notNull(type, "Type cannot be null");
        return setOf(TypeParameters.forType(type));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<T> setComponentOf(final Type<Set<T>> type) {
        Validate.notNull(type, "Type cannot be null");
        return (Type<T>) type.getTypeParameters().get(0).getType();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Set<T>> setOf(final StandardTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Set<T>>) TypeUtil.getTypeWithParameters(Set.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Set<? extends T>> setOf(final ExtendsTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Set<? extends T>>) TypeUtil.getTypeWithParameters(Set.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Set<? super T>> setOf(final SuperTypeParameter<T> typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Set<? super T>>) TypeUtil.getTypeWithParameters(Set.class, typeParameter);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Type<Set<?>> setOf(final WildcardTypeParameter typeParameter) {
        Validate.notNull(typeParameter, "Type parameter cannot be null");
        return (Type<Set<?>>) TypeUtil.getTypeWithParameters(Set.class, typeParameter);
    }


    
    
    
    public static Type<?> getRawEquivalent(final Type<?> type) {
        Validate.notNull(type, "Type cannot be null");
        return TypeUtil.getRawTypeForType(type);
    }


    
    public static Set<Type<?>> getAllTypesAssignableFrom(final Type<?> type) {
        Validate.notNull(type, "Type cannot be null");
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.getExtendedTypes(type);
    }
    
    
    public static boolean isAssignableFrom(final Type<?> type, final Type<?> fromType) {
        Validate.notNull(type, "Type cannot be null");
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.isAssignableFrom(type, fromType);
    }

    
    
    private Types() {
        super();
    }


    
}

