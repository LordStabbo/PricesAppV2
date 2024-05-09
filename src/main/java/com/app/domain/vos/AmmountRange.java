package com.app.domain.vos;

public class AmmountRange {

    private Double value;

    public AmmountRange(Double ammount) {
        if (ammount > 0) {
            this.value = ammount;
        } else {
            throw new IllegalAccessError("Ammount out of range");
        }
    }

    public Double getValue() {
        return value;
    }
}
