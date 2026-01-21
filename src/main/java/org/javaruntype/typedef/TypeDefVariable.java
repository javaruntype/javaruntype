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
 * This interface represents type definition variables. These can be of one of two types (each
 * of them represented by one of this interface's implementing classes):
 * </p>
 * <ul>
 *   <li>Named (<code>NamedTypeDefVariable</code>): <code>E</code></li>
 *   <li>Bounded (<code>BoundedTypeDefVariable</code>): <code>E extends T</code></li>
 * </ul>
 * <p>
 * Some examples:
 * </p>
 * <ul>
 *   <li><code>app.pack.ClassOne</code> contains no <code>TypeDefVariable</code>s.</li>
 *   <li><code>app.pack.ClassThree&lt;E, T&gt;</code> contains two <code>TypeDefVariable</code>s:
 *       <ul>
 *         <li><code>E</code> (named)</li>
 *         <li><code>T</code> (named)</li>
 *       </ul> 
 *   </li>
 *   <li><code>app.pack.ClassEight&lt;E, T, X extends E &amp; java.util.Collection&lt;? extends T&gt;&gt;</code> contains three <code>TypeDefVariable</code>s:
 *       <ul>
 *         <li><code>E</code> (named)</li>
 *         <li><code>T</code> (named)</li>
 *         <li><code>X extends E &amp; java.util.Collection&lt;? extends T&gt;</code> (bounded)</li>
 *       </ul> 
 *   </li>
 * </ul>
 * 
 * <p>
 * As can be seen above, <i>every</i> type definition variable has, at least, a <b>name</b>: <code>E</code> ,<code>T</code> and <code>X</code>
 * in the above examples. Also, it can be noted that bounded variables can create relations among variables, like in 
 * <code>X extends E</code>.
 * </p>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public interface TypeDefVariable extends Serializable {
    
    /**
     * Returns the variable name.
     * 
     * @return the variable name.
     */
    public String getVariableName();
    
}
