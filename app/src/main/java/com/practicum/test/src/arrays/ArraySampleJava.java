package com.practicum.test.src.arrays;

import java.util.Arrays;

public class ArraySampleJava {
    void sample(String... strings) {
        String[] sampleArray = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            sampleArray[i] = strings[i];
        }
        System.out.println(Arrays.toString(sampleArray));
    }

    public static void main(String[] args) {
        ArraySampleJava sampleJava = new ArraySampleJava();
        sampleJava.sample("We", " will", " rock", " you");
    }
}
