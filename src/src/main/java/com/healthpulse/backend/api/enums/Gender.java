package com.healthpulse.backend.api.enums;

public enum Gender {

    M("male"),
    F("female"),
    O("other");

    public final String label;

    Gender(String label) {
        this.label = label;
    }

    public static Gender getGender(String label) {
        if (label.equalsIgnoreCase("male") || label.equalsIgnoreCase("M")) return M;
        else if (label.equalsIgnoreCase("female") || label.equalsIgnoreCase("F")) return F;
        else return O;
    }
}
