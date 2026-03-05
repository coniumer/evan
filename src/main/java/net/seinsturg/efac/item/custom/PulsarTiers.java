package net.seinsturg.efac.item.custom;

public enum PulsarTiers {
    CLUMBY(3, 1),
    ALBY(5, 2),
    CITRY(7, 4),
    RUBIED(8, 6),
    PHILOSOPHERS(10, 8);

    private int chargeAbsorbChance;
    private int pulseDamage;

    PulsarTiers(int chargeAbsorbChance, int pulseDamage) {
        this.chargeAbsorbChance = chargeAbsorbChance;
        this.pulseDamage = pulseDamage;
    }

    public int getChargeAbsorbChance() {
        return chargeAbsorbChance;
    }
    public int getPulseDamage() {
        return pulseDamage;
    }
}
