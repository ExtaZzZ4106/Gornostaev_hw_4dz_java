package org.example;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {



    public static void sort_Shell (int[] mass_B){
        int step = mass_B.length / 2;

        for (; step > 0;){
            for (int i = step; i < mass_B.length; i++){
             for (int j = i; j >= step && mass_B[j] < mass_B[j - step]; j -= step) {
                 int temp = mass_B[j];
                 mass_B[j] = mass_B[j - step];
                 mass_B[j - step] = temp;
             }
            }
            step = step / 2;

        }
        String result = Arrays.stream(mass_B)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("после: "+result);
    }
    public static void main(String[] args) {

        int[] mass_A = {4,2,3,1,5,8,6,7,9};

        String result = Arrays.stream(mass_A)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("до: "+result);
        sort_Shell(mass_A);


    }
}