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

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang.Validate;

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
public final class ConcurrentCache<K,V> {

    private final static int DEFAULT_MAX_ELEMENTS = 100;
    
    private final ConcurrentHashMap<K,V> cache; 
    private final int maxElements;
    private final Queue<K> keysQueue;
    
    
    /**
     * <p>
     * Create a new synchronized cache.
     * </p>
     * 
     */
    public ConcurrentCache() {
        this(DEFAULT_MAX_ELEMENTS);
    }
    
    /**
     * <p>
     * Create a new synchronized cache specifying a maximum size for the cache
     * </p>
     */
    public ConcurrentCache(final int maxElements) {
        super();
        Validate.isTrue(maxElements > 1, "Max elements must be > 1");
        this.cache = new ConcurrentHashMap<K,V>();
        this.maxElements = maxElements;
        this.keysQueue = new ConcurrentLinkedQueue<K>();
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
     * Puts a value into the cache and returns it. If a value already
     * existed for the same key, the existing value is not modified and is
     * returned instead of the one passed as parameter. This ensures only one
     * object for each key exists at a time. 
     * </p>
     * 
     * @param key the key to which the value will be assigned
     * @param value the value which will be added to the map
     * @return the value added to the map (or the one already existing at the map)
     */
    public V computeAndGet(final K key, final V value) {
        V result = this.cache.get(key);
        if (result == null) {
            result = this.cache.putIfAbsent(key, value);
            if (result == null) {
                result = value;
                final int excessElements = this.cache.size() - this.maxElements;
                for (int i = 0; i < excessElements; i++) {
                    final K keyToRemove = this.keysQueue.poll();
                    this.cache.remove(keyToRemove);
                }
                this.keysQueue.add(key);
            }
        }
        return result;
    }
    
}
