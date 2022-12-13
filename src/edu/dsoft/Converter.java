package edu.dsoft;

public class Converter {

    public static void main(String[] args) {
        Converter instance = new Converter();
        Rate r1 = new Rate("USD");
        Rate r2 = new Rate("EUR");
        System.out.println(r1.toString());
        System.out.println(r2.toString());

        Pair p = new Pair(r2, r1);
        System.out.println(p.toString());
    }

}
