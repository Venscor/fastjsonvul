package com.venscor.test;

public class PersonParent {
    private String family;

    public PersonParent(String family) {
        System.out.println("in PersonParent with param constructor");
        this.family = family;
    }

    public PersonParent() {
        System.out.println("in PersonParent no param constructor");
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        System.out.println("in parent setFamily");
        this.family = family;
    }
}
