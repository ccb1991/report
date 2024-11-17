package com.mastering.spring.springboot.configuration;

import com.mastering.spring.springboot.bean.vo.exam.ExamVo;
import com.mastering.spring.springboot.controller.ExamController;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodLookUp {
    public static void main(String[] args) throws Throwable {
//        Object o=6;
//        MethodType mt=MethodType.methodType(ExamVo.class,Integer.class);
//        MethodHandles.Lookup l=MethodHandles.lookup();
//        MethodHandle mh=l.findVirtual(ExamController.class,"queryQuestion",mt);
//        ExamVo examVo=(ExamVo) mh.invoke(ExamController.class.newInstance(),o);
//        System.out.println("MethodType.methodType");
        Object rcvr = "a";
        try {
            MethodType mt = MethodType.methodType(int.class);
            MethodHandles.Lookup l = MethodHandles.lookup();
            MethodHandle mh = l.findVirtual(rcvr.getClass(), "hashCode", mt);
            int ret;
            try {
                ret = (int)mh.invoke(rcvr);
                System.out.println(ret);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } catch (IllegalArgumentException |
                 NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }

}
