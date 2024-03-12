package com.myblog;

import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<Integer> Numbers = Arrays.asList(2,5,6,8,9);

        int even = Numbers.stream().filter(i->i%2==0).mapToInt(Integer::intValue).sum();
        int odd = Numbers.stream().filter(i->i%2!=0).mapToInt(Integer::intValue).sum();

        System.out.println(even);
        System.out.println(odd);
    }
}
