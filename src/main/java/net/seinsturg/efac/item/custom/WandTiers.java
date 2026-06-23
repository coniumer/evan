package net.seinsturg.efac.item.custom;

public enum WandTiers {
    CLUMBY(30, 1.4F, 1F, 2),
    ALBY(20, 1.2F, 2F, 4),
    CITRY(10, 0.9F, 3F, 7),
    RUBIED(5, 0.7F, 5F, 10),
    PHILOSOPHERS(0, 0.5F, 7F, 14);

    private int cooldown;
    private int damage;
    private float accuracy;
    private float velMult;

    WandTiers(int cooldown, float accuracy, float velMult, int damage) {
        this.cooldown = cooldown;
        this.accuracy = accuracy;
        this.velMult = velMult;
        this.damage = damage;
    }

    public int getCooldown() {
        return this.cooldown;
    }
    public int getDamage() { return this.damage; }
    public float getAccuracy() {
        return this.accuracy;
    }
    public float getVelMult() {
        return this.velMult;
    }
}
