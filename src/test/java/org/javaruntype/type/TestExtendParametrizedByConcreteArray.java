package org.javaruntype.type;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.javaruntype.type.testtypes.TType3;

import junit.framework.TestCase;

public class TestExtendParametrizedByConcreteArray extends TestCase {

    private abstract static class A implements Collection<List<Integer[]>[][]> {

    }

    public void test() {
        Type<TType3<Object, Integer, Integer>> type3 = Types.forClass(TType3.class,
                TypeParameters.forType(Types.OBJECT),
                TypeParameters.forType(Types.INTEGER),
                TypeParameters.forType(Types.INTEGER));

        assertTrue(type3.getAllTypesAssignableFromThis()
                .contains(Types.forClass(Map.class,
                        TypeParameters.forType(Types.ARRAY_OF_INTEGER),
                        TypeParameters.forType(Types.INTEGER))));
    }

    public void testArrayParametrized() {
        Type<A> t = Types.forClass(A.class);

        assertTrue(t.getAllTypesAssignableFromThis().contains(Types.forClass(Collection.class,
                TypeParameters.forType(Types.arrayOf(
                        Types.arrayOf(Types.forClass(List.class, TypeParameters.forType(Types.ARRAY_OF_INTEGER))))))));
    }

}

