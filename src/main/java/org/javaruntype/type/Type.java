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

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.javaruntype.typedef.TypeDef;
import org.javaruntype.typedef.TypeDefVariable;
import org.javaruntype.typedef.TypeDefs;
import org.javaruntype.util.Utils;

/**
 * <p>
 * Basic class of the type system. A <code>Type</code> object representes a Java type including its type parameters (if it has any),
 * as specified by the {@link TypeDef} corresponding to the type's component class.
 * </p>
 * <p>
 * Every possible Java type can be represented by a <code>Type</code> object. Some examples:
 * </p>
 *   <ul>
 *     <li><code>java.lang.String</code></li>
 *     <li><code>java.util.List&lt;java.lang.Integer&gt;</code></li>
 *     <li><code>java.util.Comparator&lt;? super java.util.Map&lt;java.lang.String, ? extends java.lang.Number&gt;&gt;</code></li>
 *   </ul>
 *   
 * <p>
 * Objects of this class are never created directly. To obtain a <code>Type</code> object,
 * the diverse methods in the {@link Types} class should be used.
 * </p>
 * <p>
 * Objects of this class are <b>immutable</b>, and thus <b>thread-safe</b>. Also, in order
 * to avoid excessive memory usage, an internal synchronized cache exists which 
 * prevents the same <code>Type</code> from being instantiated more than once (so, if two 
 * Type objects are equal, this will mean that they are the same object).
 * </p>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class Type<T> implements Serializable {

    private static final long serialVersionUID = 2376256493847227243L;
    
    private final Class<?> componentClass;
    private final int arrayDimensions;
    private final TypeParameter<?>[] typeParameters;
    
    private final Class<? super T> rawClass;

    private final TypeDef typeDef;
    private final String name;
    private final String simpleName;
    
    // Class is immutable, so hashCode can be precomputed
    private final int hashCode;

    
    
    
    static Type<?> createType(final Class<?> componentClass, 
            final TypeParameter<?>[] typeParameters, final int arrayDimensions) {

        final Type<?> type = new Type<Object>(componentClass, typeParameters, arrayDimensions);
        TypeUtil.validateTypeParameters(type);

        return type;
        
    }

    
    static Type<?> createTypeWithoutValidation(final Class<?> componentClass, 
            final TypeParameter<?>[] typeParameters, final int arrayDimensions) {

        final Type<?> type = new Type<Object>(componentClass, typeParameters, arrayDimensions);

        return type;
        
    }

    
    
    
    
    
    @SuppressWarnings("unchecked")
    private Type(final Class<?> componentClass, final TypeParameter<?>[] typeParameters, 
            final int arrayDimensions) {

        this.componentClass = componentClass;
        this.typeParameters = typeParameters.clone();
        this.arrayDimensions = arrayDimensions;
        
        this.rawClass = (Class<? super T>) TypeUtil.computeRawClass(componentClass, arrayDimensions);
        
        this.name = 
            TypeUtil.createName(componentClass, typeParameters, arrayDimensions);
        this.simpleName = 
            TypeUtil.createSimpleName(componentClass, typeParameters, arrayDimensions);
        this.typeDef = TypeDefs.forClass(componentClass);
        this.hashCode = this.name.hashCode();

    }
    
    
    /**
     * <p>
     * Returns the type's component class. For instance, it will return List.class
     * for "List&lt;String&gt;[]" (and not List[].class, which would be returned by 
     * {@link #getRawClass()}).
     * </p>
     * 
     * @return the component class.
     */
    public Class<?> getComponentClass() {
        return this.componentClass;
    }
    
    
    /**
     * <p>
     * Returns the type's array dimensions. For instance, it will return 1 for
     * "List&lt;String&gt;[]"
     * </p>
     * 
     * @return the array dimensions
     */
    public int getArrayDimensions() {
        return this.arrayDimensions;
    }
    
    
    /**
     * <p>
     * Returns whether the type is an array or not. For instance, it will return
     * "true" for "List&lt;String&gt;[]".
     * </p>
     * 
     * @return whether the type is an array or not.
     */
    public boolean isArray() {
        return (this.arrayDimensions > 0);
    }
    
    
    /**
     * <p>
     * Returns whether the type represents an interface or not.
     * For instance, it will return "false" for "List&lt;String&gt;[]" as, although List.class is 
     * an interface, List[].class is a instantiable class. On the contrary,
     * "List&lt;String&gt;" would return "true".
     * </p>
     * 
     * @return whether the type represents an interface.
     */
    public boolean isInterface() {
        return !isArray() && this.componentClass.isInterface();
    }
    
    
    /**
     * <p>
     * Returns whether the type is abstract or not. For instance, it will
     * return "false" for "AbstractList&lt;String&gt;[]" as, although AbstractList.class is
     * abstract, AbstractList[] is an instantiable class. On the contrary,
     * "AbstractList&lt;String&gt;" would return "true".
     * </p>
     * 
     * @return whether the type is abstract or not.
     */
    public boolean isAbstract() {
        return !isArray() && ((this.componentClass.getModifiers() & Modifier.ABSTRACT) != 0);
    }
    
    
    TypeParameter<?>[] getTypeParametersArray() {
        return this.typeParameters;
    }

    
    /**
     * <p>
     * Returns the type parameters of the type.
     * </p>
     * 
     * @return the type parameters.
     */
    public List<TypeParameter<?>> getTypeParameters() {
        return Collections.unmodifiableList(Arrays.asList(this.typeParameters));
    }
    
    
    TypeDef getTypeDef() {
        return this.typeDef;
    }
    
    
    /**
     * <p>
     * Returns the type's name.
     * </p>
     * @return the type's name.
     */
    public String getName() {
        return this.name;
    }
    
    
    /**
     * <p>
     * Returns the type's name excluding package.
     * </p>
     * @return the type's simepl name.
     */
    public String getSimpleName() {
        return this.simpleName;
    }
    
    
    /**
     * <p>
     * Returns the type's raw equivalent class representation. For instance,
     * if the type is "List&lt;String&gt;[]", this method will return
     * List[].class (the class representing the raw version
     * of the type), in contrast with {@link #getComponentClass()} which
     * would return List.class (the component class).
     * </p>
     * 
     * @return the type's raw equivalent class representation
     */
    public Class<? super T> getRawClass() {
        return this.rawClass;
    }
    
    
    /**
     * <p>
     * Returns whether type type is assignable from the type that is passed
     * as a parameter.
     * </p>
     * <p>
     * Assignability is computed in the following terms: a type A is considered
     * to be <i>assignable</i> from another type B if a method declared as receiving
     * a parameter of type A can be called with an object of type B 
     * as a parameter.
     * </p>
     * 
     * @param type the type on which assignability is going to be tested
     * @return whether the type is assignable from the parameter type
     */
    public boolean isAssignableFrom(final Type<?> type) {
        Utils.validateNotNull(type, "Type cannot be null");
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.isAssignableFrom(this, type);
    }
    
    
    TypeParameter<?> getTypeParameterForVariable(final String variableName) {
        
        final TypeDefVariable[] typeDefVariables = this.typeDef.getVariables();
        for (int i = 0; i < typeDefVariables.length; i++) {
            if (typeDefVariables[i].getVariableName().equals(variableName)) {
                return this.typeParameters[i];
            }
        }
        return null;
        
    }

    
    /**
     * <p>
     * Creates a new instance of the type.
     * </p>
     * <p>
     * The returned instances are created using the default (no-arg) constructor
     * if this Type does not represent an array. On the contrary, if this Type 
     * represents an array, an array object of zero-size dimensions is returned 
     * (like "String[0][0][0]").
     * </p> 
     * 
     * @return an object created with the default constructor or a zero-dimensions array
     * @throws InstantiationException if the type cannot be instantiated
     * @throws IllegalAccessException if the type cannot be instantiated
     */
    public Object newInstance() 
            throws InstantiationException, IllegalAccessException {
        
        if (this.arrayDimensions == 0) {
            return this.componentClass.newInstance();
        }
        
        final int[] zeroDims = new int[this.arrayDimensions];
        Arrays.fill(zeroDims, 0);
        return Array.newInstance(this.componentClass, zeroDims);
        
    }
    

    
    
    /**
     * <p>
     * Returns a set of the types corresponding to all the interfaces and 
     * superclasses that this type implements or extends.
     * </p>
     * <p>
     * For instance, for "List&lt;String&gt;", this method will return
     * "Collection&lt;String&gt;", "Iterable&lt;String&gt;" and "Object".
     * </p>
     * 
     * @return the set of implemented interfaces and extended superclass types.
     */
    public Set<Type<?>> getAllTypesAssignableFromThis() {
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.getExtendedTypes(this);
    }


    /**
     * <p>
     * Returns a type corresponding with the one on which this method is called, but substituting all its
     * type parameters by "unknown". For instance: "List&lt;String&gt;" -&gt; "List&lt;?&gt;".
     * </p>
     * 
     * @return the corresponding raw type
     */
    public Type<?> getRawEquivalent() {
        return TypeUtil.getRawTypeForType(this);
    }



    /**
     * <p>
     * Returns whether this type would be considered "raw". A type is raw if the result
     * of calling {@link #getRawEquivalent()} on it is the type itself.
     * </p>
     * 
     * @return whether the type is raw or not
     */
    public boolean isRaw() {
        if (this.typeParameters.length == 0) {
            return true;
        }
        for (int i = 0, n = this.typeParameters.length; i < n; i++) {
            if (!this.typeParameters[i].equals(WildcardTypeParameter.UNKNOWN)) {
                return false;
            }
        }
        return true;
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
        final Type<?> other = (Type<?>) obj;
        // Comparing name will be enough and quicker than other comparisons
        return (this.name.equals(other.name));
    }



    @Override
    public String toString() {
        return this.name;
    }
    
    
    @SuppressWarnings("unused")
    private Object readResolve() throws ObjectStreamException {
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.getType(
                this.componentClass, this.typeParameters, this.arrayDimensions);
    }

    
    
}
