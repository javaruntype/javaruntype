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

import java.io.Serializable;

/**
 * <p>
 * Specifies an inner type definition variable which can be both a bound for a <code>BoundTypeDefVariable</code> or
 * a type variable for another <code>InnerTypeDefVariable</code>.
 * </p>
 * <p>
 * Inner type definition variables can take several shapes:
 * </p>
 * <ul>
 *   <li><code>X extends <b>String</b></code>: {@link InnerClassTypeDefVariable}</li>
 *   <li><code>X extends <b>T</b></code>: {@link InnerNamedTypeDefVariable}</li>
 *   <li><code>X extends <b>List&lt;Integer&gt;</b></code>: {@link InnerParameterizedTypeTypeDefVariable}</li>
 *   <li><code>X extends List&lt;<b>? extends Integer</b>&gt;</code>: {@link InnerWildcardTypeDefVariable}</li>
 * </ul>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public interface InnerTypeDefVariable extends Serializable {
    
    // Empty. Just a marker interface
    
}
