package com.github.typingtanuki.locomotive.widgets.tools;

public class CalibratorDevice {
    private final String name;
    private final int id;

    public CalibratorDevice(String name, String id) {
        this(name, Integer.parseInt(id));
    }

    public CalibratorDevice(String name, int id) {
        super();
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
