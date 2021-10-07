package org.osca.model;

public class StorePin {

    private static String pin;

    public StorePin(String pin) {
        this.pin = pin;
    }

    public StorePin() {

    }



    public void setPin(String pin) {

        this.pin = pin;
    }

    public String getPin() {
//        System.out.println(pin);
        return pin;
    }

    @Override
    public String toString() {
        return "StorePin{" +
                "pin='" + pin + '\'' +
                '}';
    }
}
