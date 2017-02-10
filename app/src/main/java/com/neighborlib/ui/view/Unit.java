package com.neighborlib.ui.view;

/**
 * Created by ts.ha on 2017-02-10.
 */

public enum Unit {
    KM("km"),
    MI("mi"),
    KPH("km/h"),
    MPH("mph"),
    L("L"),
    GAL_US("gal(US)"),
    GAL_UK("gal(UK)"),
    KPL("km/L"),
    LP100KM("L/100km"),
    MPG_US("mpg(US)"),
    MPG_UK("mpg(UK)"),
    C("°C"),
    F("°F"),
    KG("kg"),
    LB("lb"),
    CC("cc"),
    VOLT("v"),
    PERCENT("%"),
    RPM("rpm"),
    KPA("kPa"),
    EA("ea"),
    UNKNOWN("?");

    private String a;

    private Unit(String var3) {
        this.a = var3;
    }

    public String toString() {
        return this.a;
    }

    public static Unit fromString(String var0) {
        return KM.toString().equals(var0)?KM:(MI.toString().equals(var0)?MI:(KPH.toString().equals(var0)?KPH:(MPH.toString().equals(var0)?MPH:(L.toString().equals(var0)?L:(GAL_US.toString().equals(var0)?GAL_US:(GAL_UK.toString().equals(var0)?GAL_UK:(KPL.toString().equals(var0)?KPL:(LP100KM.toString().equals(var0)?LP100KM:(MPG_US.toString().equals(var0)?MPG_US:(MPG_UK.toString().equals(var0)?MPG_UK:(CC.toString().equals(var0)?CC:(VOLT.toString().equals(var0)?VOLT:(PERCENT.toString().equals(var0)?PERCENT:(RPM.toString().equals(var0)?RPM:(KPA.toString().equals(var0)?KPA:(EA.toString().equals(var0)?EA:UNKNOWN))))))))))))))));
    }
}
