package org.javaruntype;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.javaruntype.type.Type;
import org.javaruntype.type.TypeParameter;
import org.javaruntype.type.TypeParameters;
import org.javaruntype.type.Types;

public class Testing {

    
    
    
    public static void main(String[] args) {
        
        System.out.println(Types.forClass(List.class));
        
        TypeParameter<?> tp1 = TypeParameters.forExtendsType(Types.STRING);
        System.out.println(Types.forClass(List.class, tp1));
        System.out.println(Types.forClass(Map.class, tp1, tp1));
        
        Type<List<String>> listOfStrType = 
            Types.forClass(
                List.class,
                TypeParameters.forType(Types.STRING));

        
        Type<Map<String,? extends Number>> mapOfStrExtNumberType = 
            (Type<Map<String,? extends Number>>) Types.forName("Map<String,? extends Number>");

        
        System.out.println(mapOfStrExtNumberType);
        Type<URL> urlType = (Type<URL>) Types.forName("java.net.URL");
        System.out.println(urlType);
        
        Type<String[]> t = Types.arrayOf(Types.STRING);
        
        
        System.out.println(Types.LIST_OF_STRING.getAllTypesAssignableFromThis());
    }
}
