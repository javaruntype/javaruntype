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
package org.javaruntype.typedef;

import org.javaruntype.util.Utils;


/**
 * <p>
 * Represents a type definition variable which, besides a name, specifies a series of
 * <i>bounds</i> with an <tt>extends</tt> expression.
 * </p>
 * <p>
 * A variable can specify several bounds with "<tt>&</tt>": <tt>X extends [bound]&[bound]&[bound]...</tt>.
 * </p>
 * <p>
 * Bounds for a bounded type definition variable are represented by objects of type
 * {@link InnerTypeDefVariable} returned by {@link #getBounds()}. 
 * </p>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class BoundedTypeDefVariable implements TypeDefVariable {

    
    private static final long serialVersionUID = -7857569027190529682L;
    
    private final String variableName;
    private final InnerTypeDefVariable[] bounds;
    
    private final String stringRepresentation;
    
    private final int hashCode;


    
    private static String createStringRepresentation(final String variableName, 
            final InnerTypeDefVariable[] bounds) {
        
        final StringBuilder str = new StringBuilder();
        str.append(variableName);
        str.append(" extends ");
        str.append(Utils.join(bounds, "&"));
        return str.toString();
        
    }
    
    
    BoundedTypeDefVariable(final String variableName, 
            final InnerTypeDefVariable[] bounds) {
        this.variableName = variableName;
        this.bounds = bounds;
        this.stringRepresentation = 
            createStringRepresentation(variableName, bounds);
        this.hashCode = this.stringRepresentation.hashCode();
    }
    
    
    public String getVariableName() {
        return this.variableName;
    }


    /**
     * <p>
     * Returns the bounds specified for the type definition variable.
     * </p>
     * 
     * @return the bounds.
     */
    public InnerTypeDefVariable[] getBounds() {
        return this.bounds.clone();
    }


    @Override
    public int hashCode() {
        return this.hashCode;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BoundedTypeDefVariable other = (BoundedTypeDefVariable) obj;
        return this.stringRepresentation.equals(other.stringRepresentation);
    }


    @Override
    public String toString() {
        return this.stringRepresentation;
    }
    
    
}
