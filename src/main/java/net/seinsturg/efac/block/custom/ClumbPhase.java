package net.seinsturg.efac.block.custom;

import net.minecraft.util.StringRepresentable;

public enum ClumbPhase implements StringRepresentable {
    SOLID("solid", true),
    PASSABLE("passable", false);

    private final String name;
    private final boolean stable;

    private ClumbPhase(String name, boolean stable) {
        this.name = name;
        this.stable = stable;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
    public Boolean getIsStable() { return this.stable; }
}
