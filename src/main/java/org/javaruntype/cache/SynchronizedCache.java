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
package org.javaruntype.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.math.RandomUtils;
import org.javaruntype.exceptions.CacheException;

/**
 * <p>
 * Base synchronized cache for <tt>*Registry</tt> objects.
 * </p>
 * <p>
 * <b>Do not</b> use this class directly.
 * </p>
 * <p>
 * This class is <b>thread-safe</b>.
 * </p>
 * 
 * @since 1.0
 * 
 * @param <K> key type
 * @param <V> value type
 *  
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class SynchronizedCache<K,V> {

    private final String name;
    private final ConcurrentHashMap<K,V> cache; 
    
    
    /**
     * <p>
     * Create a new synchronized cache.
     * </p>
     * 
     * @param name the cache name (will be used in logging)
     */
    public SynchronizedCache(final String name) {
        super();
        Validate.notNull(name, "Name for cache cannot be null");
        this.name = name;
        this.cache = new ConcurrentHashMap<K,V>();
    }

    
    /**
     * <p>
     * Clear the cache.
     * </p>
     */
    public void clear() {
        this.cache.clear();
    }
    
    
    /**
     * <p>
     * Get the value for a specific key
     * </p>
     * 
     * @param key the key
     * @return the value, or null if not found
     */
    public V get(final K key) {
        return this.cache.get(key);
    }
    

    /**
     * <p>
     * This method executes a <tt>Callable</tt> object, puts its result
     * into the cache and finally returns this result. If an evaluation for
     * the same key is currently ongoing, its result is waited for (instead
     * of computing it again).
     * </p>
     * <p>
     * Once a value is assigned to a key, no substitutions can be done. This
     * means that the same key will not be able to have different values over time.
     * </p>
     * 
     * @param key the key to which the computed value will be assigned
     * @param eval the Callable which will be evaluated to obtain the result
     * @return the result of evaluation
     */
    public V computeAndGet(final K key, final Callable<V> eval) {
boolean log = this.name.equals("ExtendedTypesByType");
int nExec = RandomUtils.nextInt(1000);
if (log) {
    try {Thread.currentThread().sleep(10); } catch (Exception e) {}
    System.out.println("+-" + nExec + "-> {" + Thread.currentThread().getName() + "} " + System.currentTimeMillis());
}
        try {
            V value = this.cache.get(key);
            if (value == null) {
                if (log) {
                    try {Thread.currentThread().sleep(10); } catch (Exception e) {}
                    System.out.println("  +-" + nExec + "-> before call {" + Thread.currentThread().getName() + "} " + System.currentTimeMillis());
                }
                final V newValue = eval.call(); 
                if (log) {
                    try {Thread.currentThread().sleep(10); } catch (Exception e) {}
                    System.out.println("  +-" + nExec + "-> after call, before putIfAbsent {" + Thread.currentThread().getName() + "} " + System.currentTimeMillis());
                }
                value = this.cache.putIfAbsent(key, newValue);
                if (log) {
                    try {Thread.currentThread().sleep(10); } catch (Exception e) {}
                    System.out.println("  +-" + nExec + "-> after putIfAbsent {" + Thread.currentThread().getName() + "} " + System.currentTimeMillis());
                }
                if (value == null) {
                    value = newValue;
                }
            }
if (log) {
    try {Thread.currentThread().sleep(10); } catch (Exception e) {}
    System.out.println("  +-" + nExec + "-> obtained value {" + Thread.currentThread().getName() + "} " + System.currentTimeMillis());
}
            return value;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new CacheException(this.name, e);
        }
    }
    
}
