package net.seinsturg.efac.item.custom;

public enum WandTiers {
    CLUMBY(30, 1.4F, 1F),
    ALBY(20, 1.2F, 2F),
    CITRY(10, 0.9F, 3F),
    RUBIED(5, 0.7F, 5F),
    PHILOSOPHERS(0, 0.5F, 7F);

    private int cooldown;
    private float accuracy;
    private float velMult;

    WandTiers(int cooldown, float accuracy, float velMult) {
        this.cooldown = cooldown;
        this.accuracy = accuracy;
        this.velMult = velMult;
    }

    public int getCooldown() {
        return this.cooldown;
    }
    public float getAccuracy() {
        return this.accuracy;
    }
    public float getVelMult() {
        return this.velMult;
    }
}
