package com.leelear.utils;

import static org.junit.Assert.assertTrue;

import com.leelear.business.bo.Human;
import com.leelear.business.bo.Person;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Human human = new Human();
        human.setName("leelear");
        Person person =new Person();
        //BeanUtil.copyProperties(human,person,"first",false);
        System.out.println(human);
        System.out.println(person);
    }
    @Test
    public void test(){
        String str=null;
        String str2="es11";
        String str3=str2.replace("es","");
        System.out.println("".length());
        System.out.println(str3.length());
        System.out.println(str2.length());
    }
}
