package org.javaruntype.typedef;


import junit.framework.Assert;
import junit.framework.TestCase;

public class TestTypeDefs extends TestCase {
    
    
    public void testForClass() throws Exception {
        Assert.assertEquals("java.lang.String",TypeDefs.forClass(String.class).getName());
    }
    
}
