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




/*
 * (non-javadoc)
 * 
 * This class acts as a container for TypeRegistry, so that it allows
 * assignability between types to be cached.
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
final class TypeAssignation {
    
    private final Type<?> type;
    private final Type<?> fromType;
    
    private int hashCode;
    

    TypeAssignation(final Type<?> type, final Type<?> fromType) {
        super();
        this.type = type;
        this.fromType = fromType;
        this.hashCode = 31 * (31 + type.hashCode()) + fromType.hashCode();
    }

    
    Type<?> getType() {
        return this.type;
    }

    
    Type<?> getFromType() {
        return this.fromType;
    }
    

    @Override
    public int hashCode() {
        return this.hashCode;
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
        final TypeAssignation other = (TypeAssignation) obj;
        return (this.type.equals(other.type) && this.fromType.equals(other.fromType));
    }

    
    
    
}
