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
 * Specifies an inner type definition variable which can be both a bound for a <tt>BoundTypeDefVariable</tt> or
 * a type variable for another <tt>InnerTypeDefVariable</tt>.
 * </p>
 * <p>
 * Inner type definition variables can take several shapes:
 * </p>
 * <ul>
 *   <li><tt>X extends <b>String</b></tt>: {@link InnerClassTypeDefVariable}</li>
 *   <li><tt>X extends <b>T</b></tt>: {@link InnerNamedTypeDefVariable}</li>
 *   <li><tt>X extends <b>List&lt;Integer&gt;</b></tt>: {@link InnerParameterizedTypeTypeDefVariable}</li>
 *   <li><tt>X extends List&lt;<b>? extends Integer</b>&gt;</tt>: {@link InnerWildcardTypeDefVariable}</li>
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
