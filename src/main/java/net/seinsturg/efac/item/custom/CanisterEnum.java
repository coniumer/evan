package net.seinsturg.efac.item.custom;

public enum CanisterEnum {
    BLOOD(0, 39),
    AMBROSIA(40, 79),
    ELIXIR(80, 119),
    MERCURY(120, 159);

    private final int minToUse;
    private final int maxToUse;

    CanisterEnum(int minToUse, int maxToUse) {
        this.minToUse = minToUse;
        this.maxToUse = maxToUse;
    }

    public int getMinToUse() { return this.minToUse; }
    public int getMaxToUse() { return this.maxToUse; }
}
