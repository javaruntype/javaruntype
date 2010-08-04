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

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.javaruntype.exceptions.TypeRecognitionException;
import org.javaruntype.exceptions.TypeValidationException;
import org.javaruntype.type.parser.TypeLexer;
import org.javaruntype.type.parser.TypeParser;
import org.javaruntype.typedef.BoundedTypeDefVariable;
import org.javaruntype.typedef.InnerClassTypeDefVariable;
import org.javaruntype.typedef.InnerNamedTypeDefVariable;
import org.javaruntype.typedef.InnerParameterizedTypeTypeDefVariable;
import org.javaruntype.typedef.InnerTypeDefVariable;
import org.javaruntype.typedef.InnerWildcardTypeDefVariable;
import org.javaruntype.typedef.NamedTypeDefVariable;
import org.javaruntype.typedef.TypeDef;
import org.javaruntype.typedef.TypeDefVariable;
import org.javaruntype.typedef.TypeDefs;
import org.javaruntype.util.Utils;

/*
 * (non-javadoc)
 * 
 * This class contains internal algorithms for Type processing and
 * handling.
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
final class TypeUtil {
    
    
    
    static Type<?> forName(final String typeName) {

        try {
            
            final String parsedTypeName = 
                (typeName.startsWith("class "))?
                        typeName.substring("class ".length()) :
                        (typeName.startsWith("interface "))?
                                typeName.substring("interface ".length()) :
                                typeName; 
            
            final TypeLexer lex = new TypeLexer(new ANTLRStringStream(parsedTypeName));
            final CommonTokenStream tokens = new CommonTokenStream(lex);

            final TypeParser parser = new TypeParser(tokens);

            final CommonTree tree = (CommonTree) parser.type().getTree();
            return createTypeFromTree(tree);
            
        } catch (Exception e) {
            throw new TypeRecognitionException(typeName, e);
        }
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    private static Type<?> createTypeFromTree(final Tree tree) 
            throws ClassNotFoundException {

        if (tree.getType() != TypeLexer.CLASSNAME) {
            throw new TypeRecognitionException(
                    "A class name was expected (was: " + tree.getType() + ")");
        }

        final String className = tree.getText();
        Class<?> typeClass = null;
        try {
            typeClass = Utils.getClass(className);
        } catch (ClassNotFoundException e1) {
            try {
                typeClass = Utils.getClass(TypeNaming.TYPE_PACKAGE_LANG + className);
            } catch (ClassNotFoundException e2) {
                try {
                    typeClass = Utils.getClass(TypeNaming.TYPE_PACKAGE_UTIL + className);
                } catch (ClassNotFoundException e3) {
                    try {
                        typeClass = Utils.getClass(TypeNaming.TYPE_PACKAGE_IO + className);
                    } catch (ClassNotFoundException e4) {
                        try {
                            typeClass = Utils.getClass(TypeNaming.TYPE_PACKAGE_MATH + className);
                        } catch (ClassNotFoundException e5) {
                            throw new ClassNotFoundException(className);
                        }
                    }
                }
            }
        }
        
        final List<TypeParameter<?>> typeParameters = new LinkedList<TypeParameter<?>>();
        int arrayDimensions = 0;
        
        for (int i = 0; i < tree.getChildCount(); i++) {
                
            final Tree child = tree.getChild(i);
            
            if (child.getType() == TypeLexer.ARRAY) {
                
                arrayDimensions++;
                
            } else {
                
                switch (child.getType()) {
                
                    case TypeLexer.UNKNOWN:
                        typeParameters.add(WildcardTypeParameter.UNKNOWN); 
                        break;
                        
                    case TypeLexer.EXT:
                        Type<?> extendedType = createTypeFromTree(child.getChild(0));
                        typeParameters.add(new ExtendsTypeParameter<Object>((Type<Object>) extendedType));
                        break;
                        
                    case TypeLexer.SUP:
                        Type<?> superType = createTypeFromTree(child.getChild(0));
                        typeParameters.add(new SuperTypeParameter<Object>((Type<Object>) superType));
                        break;
                        
                    default:
                        Type<?> type = createTypeFromTree(child);
                        typeParameters.add(new StandardTypeParameter<Object>((Type<Object>) type));
                
                }
                
            }
        
        }
        
        // Maybe the type has been specified as raw without the wildcards
        if (typeParameters.size() == 0) {
            for (int i = 0; i < typeClass.getTypeParameters().length; i++) {
                typeParameters.add(WildcardTypeParameter.UNKNOWN);
            }
        }
        
        final TypeParameter[] typeParametersArray = 
            typeParameters.toArray(new TypeParameter[typeParameters.size()]);
        
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();

        return typeRegistry.getType(typeClass, typeParametersArray, arrayDimensions);
        
    }
    
    
    
    
    static Class<?> computeRawClass(
            final Class<?> componentClass, final int arrayDimensions) {
        if (arrayDimensions == 0) {
            return componentClass;
        }
        final int[] zeroDims = new int[arrayDimensions];
        Arrays.fill(zeroDims, 0);
        return Array.newInstance(componentClass, zeroDims).getClass();
    }
    
    
    
    static String createName(final Class<?> componentClass, 
            final TypeParameter<?>[] typeParameters, final int arrayDimensions) {
        
        final StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(componentClass.getName());
        if (typeParameters.length > 0) {
            strBuilder.append(TypeNaming.TYPE_NAME_PARAMETERS_START);
            strBuilder.append(
                    Utils.join(typeParameters, TypeNaming.TYPE_NAME_PARAMETERS_SEPARATOR));
            strBuilder.append(TypeNaming.TYPE_NAME_PARAMETERS_END);
        }
        for (int i = 0; i < arrayDimensions; i++) {
            strBuilder.append(TypeNaming.TYPE_NAME_ARRAY);
        }
        return strBuilder.toString();
        
    }
    
    
    
    static String createSimpleName(final Class<?> componentClass, 
            final TypeParameter<?>[] typeParameters, final int arrayDimensions) {
        
        final StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(componentClass.getSimpleName());
        if (typeParameters.length > 0) {
            strBuilder.append(TypeNaming.TYPE_NAME_PARAMETERS_START);
            strBuilder.append(
                    Utils.join(typeParameters, TypeNaming.TYPE_NAME_PARAMETERS_SEPARATOR));
            strBuilder.append(TypeNaming.TYPE_NAME_PARAMETERS_END);
        }
        for (int i = 0; i < arrayDimensions; i++) {
            strBuilder.append(TypeNaming.TYPE_NAME_ARRAY);
        }
        return strBuilder.toString();
        
    }
    

    
    @SuppressWarnings("unchecked")
    static <T> Type<T> decreaseArrayDimensions(final Type<T[]> type) {
        if (!type.isArray()) {
            throw new IllegalStateException(
                    "Cannot get an array component type from a non-array type: " + type.getName());
        }
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return (Type<T>) typeRegistry.getType(type.getComponentClass(), type.getTypeParametersArray(),(type.getArrayDimensions() - 1));
    }
    
    
    @SuppressWarnings("unchecked")
    static <T> Type<T[]> increaseArrayDimensions(final Type<T> type) {
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return (Type<T[]>) typeRegistry.getType(type.getComponentClass(), type.getTypeParametersArray(),(type.getArrayDimensions() + 1));
    }
    

    
    
    
    
    static void validateTypeParameters(final Type<?> type) {
        if (!isTypeParametersValid(type)) {
            throw new TypeValidationException(
                    type.getName() + " is not a valid type " +
                    "according to definition " + type.getTypeDef());
        }
    }

    
    
    private static boolean isTypeParametersValid(final Type<?> type) {

        final TypeDefVariable[] typeDefVariables = type.getTypeDef().getVariables();
        final TypeParameter<?>[] typeParameters = type.getTypeParametersArray();
        
        if (typeDefVariables.length != typeParameters.length) {
            return false;
        }

        final Map<String,TypeParameter<?>> checkedTypeParametersByName =
            new HashMap<String, TypeParameter<?>>();
        
        for (int i = 0; i < typeDefVariables.length; i++) {
            
            if (typeParameters[i] == null) {
                return false;
            }
            
            if (typeDefVariables[i] instanceof NamedTypeDefVariable) {

                checkedTypeParametersByName.put(
                        typeDefVariables[i].getVariableName(), 
                        typeParameters[i]);
                
            } else { // typeDefVariables[i] instanceof BoundedTypeDefVariable
                
                final BoundedTypeDefVariable boundedVar = 
                    (BoundedTypeDefVariable) typeDefVariables[i];
                
                final String variableName = boundedVar.getVariableName();
                final InnerTypeDefVariable[] innerVariables = boundedVar.getBounds();

                // A wildcard will always be checked. For the rest of types,
                // we will need to resolve
                if (!(typeParameters[i] instanceof WildcardTypeParameter)) {

                    final List<Type<?>> extendedTypes = resolveFormalExtendedTypes(
                            checkedTypeParametersByName, variableName, 
                            typeParameters[i], innerVariables);

                    // If there was an error resolving inner types, return false
                    if (extendedTypes == null) {
                        return false;
                    }

                    for (Type<?> extendedType : extendedTypes) {
                        if (!extendedType.isAssignableFrom(typeParameters[i].getType())) {
                            return false;
                        }
                    }
                    
                }
                
                // If we have not returned false, the type parameter can be
                // considered checked
                checkedTypeParametersByName.put(
                        typeDefVariables[i].getVariableName(), 
                        typeParameters[i]);
                    
            }
            
        }
        
        return true;
        
    }
    
    
    
    
    private static List<Type<?>> resolveFormalExtendedTypes(
            final Map<String, TypeParameter<?>> checkedTypeParametersByName,
            final String currentVariableName, final TypeParameter<?> currentTypeParameter,
            final InnerTypeDefVariable[] innerVariables) {

        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        final List<Type<?>> types = new LinkedList<Type<?>>();
        for (int i = 0; i < innerVariables.length; i++) {

            
            if (innerVariables[i] instanceof InnerClassTypeDefVariable) {
                
                /*
                 * We simply get the raw type (as it is not
                 * an InnerParameterizedTypeDefVariable) and add it to the
                 * types array.
                 */
                
                final InnerClassTypeDefVariable classVariable = 
                    (InnerClassTypeDefVariable) innerVariables[i];
                final Type<?> innerType = 
                    getRawTypeForClass(
                            classVariable.getComponentClass(), 
                            classVariable.getArrayDimensions());
                types.add(innerType);
                
            } else if (innerVariables[i] instanceof InnerNamedTypeDefVariable) {

                /*
                 * We will check the variables which already have an assigned
                 * type parameter (checkedTypeParameters).
                 */
                
                final InnerNamedTypeDefVariable innerVariable = 
                    (InnerNamedTypeDefVariable) innerVariables[i];
                
                // We check if the variable has already been defined and checked
                final TypeParameter<?> linkedTypeParameter = 
                    checkedTypeParametersByName.get(
                            innerVariable.getVariableName());

                // If variable has not been set before, return null
                if (linkedTypeParameter == null) {
                    return null;
                }
                
                /*
                 * Basing on the linked type parameter, add the
                 * types which must conform
                 */
                if (linkedTypeParameter instanceof WildcardTypeParameter) {
                    
                    // No types to be added, any type would be valid
                    
                } else if (linkedTypeParameter instanceof StandardTypeParameter<?>) {
                    
                    types.add(linkedTypeParameter.getType());
                    
                } else if (linkedTypeParameter instanceof ExtendsTypeParameter<?>) {
                    
                    types.add(linkedTypeParameter.getType());
                    
                } else { // linkedTypeParameter instanceof SuperTypeParameter
                    
                    // No types to be added, any type would be valid
                    
                }
                
            } else if (innerVariables[i] instanceof InnerWildcardTypeDefVariable) {
                
                // This can never happen, as a Wildcard is not allowed here
                throw new IllegalStateException("Wildcard should not appear " +
                        "at first level of 'extends' clause in type definition");
                
            } else { // innerVariables[i] instanceof InnerParameterizedTypeTypeDefVariable 
            
                final InnerParameterizedTypeTypeDefVariable parameterizedVariable =
                    (InnerParameterizedTypeTypeDefVariable) innerVariables[i];
                
                final TypeParameter<?>[] typeParameters =
                    resolveInnerParameterizedType(checkedTypeParametersByName,
                            currentVariableName, currentTypeParameter,
                            parameterizedVariable.getVariables());

                if (typeParameters == null) {
                    // We have tried to solve a non-existing variable
                    return null;
                }
                
                final Class<?> componentClass = 
                    parameterizedVariable.getComponentClass();
                final int arrayDimensions = parameterizedVariable.getArrayDimensions();

                final Type<?> parameterizedType =
                    typeRegistry.getTypeWithoutValidation(
                            componentClass, typeParameters, arrayDimensions);
                
                types.add(parameterizedType);
                
            }

            
        }
        
        return types;
        
    }

    
    
    @SuppressWarnings("unchecked")
    private static TypeParameter<?>[] resolveInnerParameterizedType(
            final Map<String, TypeParameter<?>> checkedTypeParametersByName,
            final String currentVariableName, final TypeParameter<?> currentTypeParameter,
            final InnerTypeDefVariable[] innerVariables) {

        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        final TypeParameter<?>[] typeParameters = 
            new TypeParameter<?>[innerVariables.length];
        
        for (int i = 0; i < innerVariables.length; i++) {

            if (innerVariables[i] instanceof InnerClassTypeDefVariable) {
                
                final InnerClassTypeDefVariable classVariable = 
                    (InnerClassTypeDefVariable) innerVariables[i];
                final Type<?> innerType = 
                    getRawTypeForClass(
                            classVariable.getComponentClass(), 
                            classVariable.getArrayDimensions());
                typeParameters[i] = 
                    new StandardTypeParameter(innerType);

            } else if (innerVariables[i] instanceof InnerNamedTypeDefVariable) {
                
                final InnerNamedTypeDefVariable innerVariable = 
                    (InnerNamedTypeDefVariable) innerVariables[i];
                
                // We check if the variable has already been defined and checked
                TypeParameter<?> linkedTypeParameter = 
                    checkedTypeParametersByName.get(
                            innerVariable.getVariableName());

                // If variable has not been set before, check if we are
                // linking to the current variable itself. If not, return null.
                if (linkedTypeParameter == null) {
                    if (innerVariable.getVariableName().equals(currentVariableName)) {
                        linkedTypeParameter = currentTypeParameter;
                    } else {
                        return null;
                    }
                }
                
                // We need to take care of the array dimensions, and if it is
                // more than 0, no wildcard-based type parameters will be 
                // allowed

                if ((linkedTypeParameter instanceof WildcardTypeParameter) ||
                    (linkedTypeParameter instanceof ExtendsTypeParameter<?>) ||
                    (linkedTypeParameter instanceof SuperTypeParameter<?>)) {
                    
                    return null;
                    
                }

                // If it is allowed, compute the arrayDimensions
                final Type<?> containedType = linkedTypeParameter.getType();
                int newArrayDimensions = 
                    containedType.getArrayDimensions() + 
                    innerVariable.getArrayDimensions();
                final Type<?> newType = 
                    typeRegistry.getTypeWithoutValidation(
                            containedType.getComponentClass(), 
                            containedType.getTypeParametersArray(), 
                            newArrayDimensions);
                
                typeParameters[i] = new StandardTypeParameter(newType);
                
            } else if (innerVariables[i] instanceof InnerWildcardTypeDefVariable) {
                
                final InnerWildcardTypeDefVariable wildcardVariable = 
                    (InnerWildcardTypeDefVariable) innerVariables[i];

                if (wildcardVariable.isUnbound()) {
                    
                    typeParameters[i] = WildcardTypeParameter.UNKNOWN;
                    
                } else {

                    /*
                     * We need to recursively obtain the inner type
                     */
                    
                    InnerTypeDefVariable bound = null;
                    if (wildcardVariable.hasUpperBound()) {
                        bound = wildcardVariable.getUpperBound();
                    } else { // wildcardVariable.hasLowerBound() == true
                        bound = wildcardVariable.getLowerBound();
                    }
                    
                    final TypeParameter<?>[] resolvedTypeParameters = 
                        resolveInnerParameterizedType(checkedTypeParametersByName,
                                currentVariableName, currentTypeParameter,
                                new InnerTypeDefVariable[] { bound });

                    if (resolvedTypeParameters == null) {
                        return null;
                    }
                    
                    if (resolvedTypeParameters.length != 1) {
                        throw new IllegalStateException("Wildcard variable " +
                                "is supposed to have a resolvable upper bound");
                    }
                    if (!(resolvedTypeParameters[0] instanceof StandardTypeParameter<?>)) {
                        throw new IllegalStateException("Wildcard variable " +
                                "is supposed to have a resolvable upper bound " +
                                "in the form of a Standard Type parameter");
                    }
                    
                    final Type<?> type = 
                        ((StandardTypeParameter<?>) resolvedTypeParameters[0]).getType();
                    
                    if (wildcardVariable.hasUpperBound()) {
                        typeParameters[i] = new ExtendsTypeParameter(type);
                    } else { // unknownVariable.hasLowerBound() == true
                        typeParameters[i] = new SuperTypeParameter(type);
                    }
                    
                }
                
            } else { // innerVariables[i] instanceof InnerParameterizedTypeTypeDefVariable
                
                final InnerParameterizedTypeTypeDefVariable parameterizedVariable =
                    (InnerParameterizedTypeTypeDefVariable) innerVariables[i];

                final TypeParameter<?>[] innerTypeParameters = 
                    resolveInnerParameterizedType(checkedTypeParametersByName,
                            currentVariableName, currentTypeParameter,
                            parameterizedVariable.getVariables());

                if (innerTypeParameters == null) {
                    // We have tried to solve a non-existing variable
                    return null;
                }
                
                final Class<?> componentClass = 
                    parameterizedVariable.getComponentClass();
                final int arrayDimensions = parameterizedVariable.getArrayDimensions();
                
                final Type<?> parameterizedType =
                    typeRegistry.getTypeWithoutValidation(
                            componentClass, innerTypeParameters, arrayDimensions);
                
                typeParameters[i] = new StandardTypeParameter(parameterizedType);
                
            }
            
        }
        return typeParameters;
    }


    
    
    
    
    
    static Type<?> getRawTypeForClass(final Class<?> typeClass) {
        return getRawTypeForClass(typeClass, 0);
    }
  
    
    
    private static Type<?> getRawTypeForClass(
            final Class<?> typeClass, final int arrayDimensions) {
        
        Class<?> componentClass = typeClass;
        int newArrayDimensions = arrayDimensions;
        
        while (componentClass.isArray()) {
            componentClass = componentClass.getComponentType();
            newArrayDimensions++;
        }
        
        final TypeDef typeDef = TypeDefs.forClass(componentClass);
        final TypeDefVariable[] variables = typeDef.getVariables();
        
        final TypeParameter<?>[] typeParameters = new TypeParameter<?>[variables.length];
        for (int i = 0; i < variables.length; i++) {
            typeParameters[i] = WildcardTypeParameter.UNKNOWN;
        }
            
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();

        return typeRegistry.getType(componentClass, typeParameters, newArrayDimensions);

    }
    

    
    
    
    
    
    static Set<Type<?>> getExtendedTypes(final Type<?> type) {
        
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        
        final Set<Type<?>> equivalenceSet = new HashSet<Type<?>>();
        
        if (Object.class.equals(type.getComponentClass())) {
            // If we reached the Object class, then we only need to add
            // all the Object-based classes down to array dimension zero.
            int currentArrayDim = type.getArrayDimensions();
            while (currentArrayDim > 0) {
                currentArrayDim--;
                equivalenceSet.add(
                        typeRegistry.getTypeWithoutValidation(Object.class, new TypeParameter<?>[0], currentArrayDim));
            }
            return equivalenceSet;
        }
        
        if (type.isInterface()) {
            equivalenceSet.add(typeRegistry.getRawTypeForClass(Object.class));
        }

        final Class<?> componentClass = type.getComponentClass();
        
        final java.lang.reflect.Type superclassTypeDeclaration = 
            componentClass.getGenericSuperclass();
        if (superclassTypeDeclaration != null) {
            final Type<?> superclassType = resolveExtendedTypeByDeclaration(type, superclassTypeDeclaration);
            equivalenceSet.add(superclassType);
            equivalenceSet.addAll(typeRegistry.getExtendedTypes(superclassType));
        }
        
        for (java.lang.reflect.Type interfaceTypeDeclaration : componentClass.getGenericInterfaces()) {
            final Type<?> interfaceType = resolveExtendedTypeByDeclaration(type, interfaceTypeDeclaration);
            equivalenceSet.add(interfaceType);
            equivalenceSet.addAll(typeRegistry.getExtendedTypes(interfaceType));
        }

        return Collections.unmodifiableSet(equivalenceSet);
        
    }
    
    
    private static Type<?> resolveExtendedTypeByDeclaration(
            final Type<?> originalType, final java.lang.reflect.Type typeDeclaration) {

        final Map<String,TypeParameter<?>> typeParametersMap = 
            new HashMap<String, TypeParameter<?>>();

        Class<?> componentClass = null;
        
        if (typeDeclaration instanceof ParameterizedType) {
            
            /*
             * If the declaration is for a parameterized type and its 
             * declaration specifies values for its type parameters 
             * (like "<T,F>"), we will add the corresponding TypeParameter<?> 
             * objects to the type, using the own TypeParameter<?> objects of 
             * the containing (original) class.
             */

            final ParameterizedType parameterizedTypeDeclaration = 
                (ParameterizedType) typeDeclaration;

            // Get the type argument declarations as they appear in the 
            // original type (eg: "class Original implements Map<A,B>" -> 
            // "[A,B]")
            final java.lang.reflect.Type[] parameterizedTypeDeclarationArguments = 
                parameterizedTypeDeclaration.getActualTypeArguments();
            
            componentClass = 
                (Class<?>) parameterizedTypeDeclaration.getRawType();
            
            // Get the type arguments as they are declared in the type declared
            // itself as argument of the original type (eg: "class Map<K,V>" ->
            // "[K,V]") 
            final TypeVariable<?>[] componentClassTypeParameters =
                componentClass.getTypeParameters();
            
            for (int i = 0; i < parameterizedTypeDeclarationArguments.length; i++) {
                
                final TypeParameter<?> typeParameter = 
                    resolveEquivalentTypeParameterByDeclaration(
                            originalType, 
                            parameterizedTypeDeclarationArguments[i], 0);

                typeParametersMap.put(
                        componentClassTypeParameters[i].getName(),
                        typeParameter);
                
            }
            
        } else {

            /*
             * If the declaration is not parameterized, it can mean either that
             * the type has no type parameters or that it has type parameters
             * but these have not been specified (raw type).
             * In the first case, the type class is simply set as component 
             * class. In the second case, the type parameters are filled with
             * wildcards (eg: "implements List" -> "implements List<?>").
             */
            
            componentClass = (Class<?>) typeDeclaration;
            
            for (int i = 0; i < componentClass.getTypeParameters().length; i++) {
                typeParametersMap.put(
                        componentClass.getTypeParameters()[i].getName(), 
                        WildcardTypeParameter.UNKNOWN);
            }
            
        }

        final TypeDef typeDef = TypeDefs.forClass(componentClass);
        
        final TypeParameter<?>[] typeParameters = 
            new TypeParameter<?>[typeDef.getVariables().length];
        for (int i = 0; i < typeDef.getVariables().length; i++) {
            typeParameters[i] = 
                typeParametersMap.get(typeDef.getVariables()[i].getVariableName());
        }

        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.getTypeWithoutValidation(
                componentClass, typeParameters, originalType.getArrayDimensions());
        
    }
    

    
    
    
    
    @SuppressWarnings("unchecked")
    private static TypeParameter<?> resolveEquivalentTypeParameterByDeclaration(
            final Type<?> originalType, final java.lang.reflect.Type typeDeclaration,
            final int arrayDimensions) {
        
        if (typeDeclaration instanceof TypeVariable<?>) {

            /*
             * The type argument is a variable, as in "List<E>"
             */
            
            final String argumentName = ((TypeVariable<?>) typeDeclaration).getName();

            // Return a type with the suitable array dimensions
            final TypeParameter<?> typeParameter = 
                originalType.getTypeParameterForVariable(argumentName);
            
            // For returning a TypeParameter<?>, we will have to make sure that
            // the array dimensions of the type referred by the TypeParameter<?>
            // correspond with the dimensions we are being requested plus
            // the ones at the original type parameter. 
            
            if (typeParameter instanceof WildcardTypeParameter) {
                
                // If it is a wildcard, simply return it
                return typeParameter;
                
            } 
                
            // If it is not a wildcard, we will extract the referred
            // type and return the same type of TypeParameter<?>, but
            // with the original arrayDimensions in the type plus
            // the dimensions we are currently using.

            if (arrayDimensions == 0) {
                return typeParameter;
            }
            
            final Type<?> containedType = typeParameter.getType();
            final int newArrayDimensions = 
                containedType.getArrayDimensions() + arrayDimensions;

            final TypeRegistry typeRegistry = TypeRegistry.getInstance();
            
            final Type<?> newType = 
                typeRegistry.getTypeWithoutValidation(
                        containedType.getComponentClass(), 
                        containedType.getTypeParametersArray(), 
                        newArrayDimensions);
            
            if (typeParameter instanceof StandardTypeParameter<?>) {
                return new StandardTypeParameter(newType);
            } else if (typeParameter instanceof ExtendsTypeParameter<?>){
                return new ExtendsTypeParameter(newType);
            } else { // typeParameter instanceof SuperTypeParameter
                return new SuperTypeParameter(newType);
            }

        } else if (typeDeclaration instanceof GenericArrayType) {

            final GenericArrayType genericArrayType = 
                (GenericArrayType) typeDeclaration;
            
            return resolveEquivalentTypeParameterByDeclaration(
                    originalType, genericArrayType.getGenericComponentType(), 
                    (arrayDimensions + 1));
            
        } else {

            /*
             * The type argument is a specific type, as in "List<String>"
             * It could be parameterized ("List<Comparable<E>>") 
             * or not ("List<String>")
             */
            
            // We should propagate the new array dimensions
            Type<?> baseType = originalType;
            if (baseType.isArray()) {
                TypeRegistry typeRegistry = TypeRegistry.getInstance();
                baseType = 
                    typeRegistry.getType(
                        baseType.getComponentClass(),
                        baseType.getTypeParametersArray(),
                        arrayDimensions);
            }
            
            // Create the appropiate type recursively
            final Type<?> parameterizedTypeDeclarationArgumentType = 
                resolveExtendedTypeByDeclaration(baseType, typeDeclaration);

            return new StandardTypeParameter(parameterizedTypeDeclarationArgumentType);
            
        }
        
    }
    
    
    
    static Type<?> getTypeWithParameters(final Class<?> componentClass, final TypeParameter<?>... typeParameters) {
        int arrayDimensions = 0;
        Class<?> newTypeComponentClass = componentClass;
        while (newTypeComponentClass.isArray()) {
            arrayDimensions++;
            newTypeComponentClass = newTypeComponentClass.getComponentType();
        }
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        return typeRegistry.getType(newTypeComponentClass, typeParameters, arrayDimensions);
    }

    
    
    
    
    
    static boolean isAssignableFrom(final Type<?> type, final Type<?> fromType) {

        if (type.equals(fromType)) {
            return true;
        }
        if (type.getComponentClass().equals(Object.class) &&
            type.getArrayDimensions() <= fromType.getArrayDimensions()) {
            return true;
        }
        if (isTypeAssignableFrom(type,fromType)) {
            return true;
        }
        final TypeRegistry typeRegistry = TypeRegistry.getInstance();
        final Set<Type<?>> extendedTypes = typeRegistry.getExtendedTypes(fromType);
        for (Type<?> extendedType : extendedTypes) {
            if (isTypeAssignableFrom(type,extendedType)) {
                return true;
            }
        }

        return false;
        
    }
    

    
    

    
    private static boolean isTypeAssignableFrom(final Type<?> type, final Type<?> fromType) {
        if (type.getArrayDimensions() != fromType.getArrayDimensions()) {
            return false;
        }
        if (!type.getComponentClass().isAssignableFrom(fromType.getComponentClass())) {
            return false;
        }
        if (type.getTypeParametersArray().length != fromType.getTypeParametersArray().length) {
            return false;
        }
        for (int i = 0; i < type.getTypeParametersArray().length; i++) {
            if (!type.getTypeParametersArray()[i].isAssignableFrom(fromType.getTypeParametersArray()[i])) {
                return false;
            }
        }
        return true;
    }
    
    
    
    
    
    static Type<?> getRawTypeForType(final Type<?> type) {
        return getRawTypeForClass(type.getComponentClass(), type.getArrayDimensions());
    }

    

    
    
    
    public static TypeParameter<?> createFromJavaLangReflectTypeParameter(
            final java.lang.reflect.Type originalType, final java.lang.reflect.Type type, 
            final Map<String,Type<?>> variableSubstitutions) {

        if (type instanceof Class<?>) {
            return TypeParameters.forType(createFromJavaLangReflectType(originalType, type, variableSubstitutions));
        }
        
        if (type instanceof GenericArrayType) {
            return TypeParameters.forType(createFromJavaLangReflectType(originalType, type, variableSubstitutions));
        }
        
        if (type instanceof ParameterizedType) {
            return TypeParameters.forType(createFromJavaLangReflectType(originalType, type, variableSubstitutions));
        }
        
        if (type instanceof TypeVariable<?>) {
            
            final TypeVariable<?> typeVariable = (TypeVariable<?>) type;
            final java.lang.reflect.Type[] bounds = typeVariable.getBounds();
            
            final Type<?> correspondingType = 
                variableSubstitutions.get(typeVariable.getName());
            if (correspondingType == null) {
                throw new TypeValidationException("No variable substitution established for variable " +
                     "\"" + typeVariable.getName() + "\" in type \"" + originalType + "\"");
            }
            
            /*
             * Bounds here refer to declarations like:
             *     public <E extends Serializable> List<E> method() { ... }
             * ...and thus variable substitutions will have to be validated 
             * against these bounds. 
             */ 
            for (java.lang.reflect.Type bound : bounds) {
                final Type<?> boundType = createFromJavaLangReflectType(originalType, bound, variableSubstitutions);
                if (!boundType.isAssignableFrom(correspondingType)) {
                    throw new TypeValidationException("Variable substitution established for variable " +
                            "\"" + typeVariable.getName() + "\" in type \"" + originalType + "\" is " +
                    		"\"" + correspondingType + "\", which does not conform to upper bound \"extends " + boundType + "\"");
                }
            }
            
            return TypeParameters.forType(correspondingType);
            
        }
        
        if (type instanceof WildcardType) {
            final WildcardType wildcardType = (WildcardType) type;
            
            if (wildcardType.getLowerBounds() != null && wildcardType.getLowerBounds().length > 0) {
                
                final java.lang.reflect.Type[] lowerBounds = wildcardType.getLowerBounds();
                if (lowerBounds.length > 1) {
                    throw new TypeValidationException("Type parameter \"" + type + "\" cannot " +
                            "have more than one bound at this point in type \"" + originalType + "\"");
                }
                return TypeParameters.forSuperType(createFromJavaLangReflectType(originalType, lowerBounds[0], variableSubstitutions));
                
            } else if (wildcardType.getUpperBounds() != null && wildcardType.getUpperBounds().length > 0) {
                
                final java.lang.reflect.Type[] upperBounds = wildcardType.getUpperBounds();
                if (upperBounds.length > 1) {
                    throw new TypeValidationException("Type parameter \"" + type + "\" cannot " +
                    		"have more than one bound at this point in type \"" + originalType + "\"");
                }
                return TypeParameters.forExtendsType(createFromJavaLangReflectType(originalType, upperBounds[0], variableSubstitutions));
                
            } else {
                return TypeParameters.forUnknown();
            }
        }
        
        throw new TypeValidationException("Specified \"" + type + "\" in type \"" + originalType + "\" is of class \"" + type.getClass() + "\", which is " +
                "not a recognized java.lang.reflect.Type implementation.");
        
    }

    
    
    
    public static Type<?> createFromJavaLangReflectType(
            final java.lang.reflect.Type originalType, final java.lang.reflect.Type type, 
            final Map<String,Type<?>> variableSubstitutions) {

        if (type instanceof Class<?>) {
            
            final Class<?> classType = (Class<?>) type;
            return Types.forClass(classType);
            
        }
        
        if (type instanceof GenericArrayType) {
            
            final GenericArrayType genericArrayType = (GenericArrayType) type;
            final Type<?> componentType = createFromJavaLangReflectType(originalType, genericArrayType.getGenericComponentType(), variableSubstitutions);
            
            final TypeRegistry typeRegistry = TypeRegistry.getInstance();
            return typeRegistry.getType(componentType.getComponentClass(), componentType.getTypeParametersArray(), componentType.getArrayDimensions() + 1);
            
        }
        
        if (type instanceof ParameterizedType) {
            
            final ParameterizedType parameterizedType = (ParameterizedType) type;
            final java.lang.reflect.Type[] actualTypeParameters = parameterizedType.getActualTypeArguments();
            
            final TypeParameter<?>[] typeParameters = new TypeParameter<?>[actualTypeParameters.length];
            for (int i = 0; i < actualTypeParameters.length; i++) {
                typeParameters[i] = createFromJavaLangReflectTypeParameter(originalType, actualTypeParameters[i], variableSubstitutions);
            }
            
            final Type<?> rawType = createFromJavaLangReflectType(originalType, parameterizedType.getRawType(), variableSubstitutions);
            
            final TypeRegistry typeRegistry = TypeRegistry.getInstance();
            return typeRegistry.getType(rawType.getComponentClass(), typeParameters, rawType.getArrayDimensions());
            
        }
        
        if (type instanceof TypeVariable<?>) {
            
            final TypeVariable<?> typeVariable = (TypeVariable<?>) type;
            final java.lang.reflect.Type[] bounds = typeVariable.getBounds();
            
            final Type<?> correspondingType = 
                variableSubstitutions.get(typeVariable.getName());
            if (correspondingType == null) {
                throw new TypeValidationException("No variable substitution established for variable " +
                     "\"" + typeVariable.getName() + "\" in type \"" + originalType + "\"");
            }
            
            /*
             * Bounds here refer to declarations like:
             *     public <E extends Serializable> E method() { ... }
             * ...and thus variable substitutions will have to be validated 
             * against these bounds. 
             */ 
            for (java.lang.reflect.Type bound : bounds) {
                final Type<?> boundType = createFromJavaLangReflectType(originalType, bound, variableSubstitutions);
                if (!boundType.isAssignableFrom(correspondingType)) {
                    throw new TypeValidationException("Variable substitution established for variable " +
                            "\"" + typeVariable.getName() + "\" in type \"" + originalType + "\" is " +
                            "\"" + correspondingType + "\", which does not conform to upper bound \"extends " + boundType + "\"");
                }
            }
            
            return correspondingType;
            
        }
        
        if (type instanceof WildcardType) {
            throw new TypeValidationException("Cannot convert wildcard \"" + type + "\" in type \"" + originalType + "\" into a javaRuntype type.");
        }
        
        throw new TypeValidationException("Specified \"" + type + "\" in type \"" + originalType + "\" is of class \"" + type.getClass() + "\", which is " +
        		"not a recognized java.lang.reflect.Type implementation.");
    }
    
    
    
    
    
    
    private TypeUtil() {
        super();
    }
    
}
