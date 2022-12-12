package juc_basic;

import lombok.Data;

@Data
public class Pair {
    private Integer a;
    private Integer b;
    public Pair(Integer a,Integer b){
        this.a = a;
        this.b = b;
    }
}
