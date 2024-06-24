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

/**
 * <p>
 * This class represents a type parameter which only contains a <code>Type</code>.
 * </p>
 * <p>
 * For example, the type <code>List&lt;Set&lt;String[]&gt;&gt;</code>;
 * would have a <code>StandardTypeParameter</code>
 * containing the <code>Set&lt;String[]&gt;</code> parameter.
 * </p>
 * 
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class StandardTypeParameter<T> extends TypeParameter<T> {

    private static final long serialVersionUID = -1799703488533881728L;
    
    private final Type<T> type;

    
    
    StandardTypeParameter(final Type<T> type) {
        super();
        this.type = type;
    }


    @Override
    public Type<T> getType() {
        return this.type;
    }


    @Override
    boolean isAssignableFrom(final TypeParameter<?> typeParameter) {

        if (this.equals(typeParameter)) {
            return true;
        }
        // Only a StandardTypeParameter for exactly the same type will be valid
        return false;
        
    }


    @Override
    public int hashCode() {
        return this.type.hashCode();
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StandardTypeParameter<?> other = (StandardTypeParameter<?>) obj;
        return this.type.equals(other.type);
    }


    @Override
    public String toString() {
        return this.type.getName();
    }
    
    
}
