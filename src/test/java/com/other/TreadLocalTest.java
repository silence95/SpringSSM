package com.other;

import java.util.HashMap;

import java.util.Map;



public class TreadLocalTest {

    static ThreadLocal<HashMap> threadLocal = new ThreadLocal<HashMap>() {

        @Override
        protected HashMap initialValue() {

            System.out.println(Thread.currentThread().getName() + "initialValue");

            return new HashMap();

        }

    };
    
    static ThreadLocal<HashMap> threadLocal2 = new ThreadLocal<HashMap>() {

        @Override
        protected HashMap initialValue() {

            System.out.println(Thread.currentThread().getName() + "initialValue");

            return new HashMap();

        }

    };



    public static class T1 implements Runnable {

//        private final static Map map = new HashMap();

        int id;



        public T1(int id) {

            this.id = id;

        }

        public void run() {

            Map map = threadLocal.get();
            Map map2 = threadLocal2.get();

            for (int i = 0; i < 20; i++) {

                map.put(i, id + " " + i);
                map2.put(i, id + " 2 " + i);

                try {

                    Thread.sleep(100);

                } catch (Exception ex) {

                }

            }

            System.out.println(Thread.currentThread().getName() + "# map.size()=" + map.size() + "#" + map);
            System.out.println(Thread.currentThread().getName() + "# map2.size()=" + map2.size() + "#" + map2);

        }
    }

    public static void main(String[] args) {

        Thread[] runs = new Thread[15];

        T1 t = new T1(1);

        for (int i = 0; i < runs.length; i++) {

            runs[i] = new Thread(t);

        }

        for (int i = 0; i < runs.length; i++) {

            runs[i].start();

        }

    }

}
