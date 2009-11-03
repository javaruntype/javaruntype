package org.javaruntype.type.testtypes;


@SuppressWarnings("unchecked")
public class TType <Z,Y extends Number&Comparable<Float>,X extends Z> extends TParentType<Y>
        implements Comparable {


    private static final long serialVersionUID = 5957929133996499746L;


    public TType() {
        super();
    }


    public int compareTo(Object o) {
        return 0;
    }
    
}
