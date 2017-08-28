package com.atguigu.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {
    Employee employee1 =new Employee();
    Employee employee2 =new Employee();
    String json;
    List<Employee> list = new ArrayList<>();

    {
        employee1.setName("David");
        employee1.setGender('M');
        employee1.setDeptName("R & D");
        employee2.setName("David");
        employee2.setGender('M');
        employee2.setDeptName("R & D");
        list.add(employee1);
        list.add(employee2);
    }

    @Test
    public void testObject2JsonString(){
        //对象转Json
        String json=JSON.toJSONString(employee1);
        System.out.println(json);
    }

    @Test
    public void testJsonString2Object(){
        //json转对象
        String json=JSON.toJSONString(employee1);
        Employee employee = JSON.parseObject(json, Employee.class);
        System.out.println(employee);
    }

    @Test
    public void testList2JsonString(){
        //集合转json
        String json=JSON.toJSONString(list);
        System.out.println(json);
    }

    @Test
    public void testJsonString2List(){
        //json转集合
        String json=JSON.toJSONString(list);
        List<Employee> employees = JSON.parseArray(json, Employee.class);
        System.out.println(employees);
    }

    @Test
    public void RegTest(){
        String s1="_E_Q";
        String s2 = s1.replaceAll("_", "%");
        System.out.println(s2);
    }
}
