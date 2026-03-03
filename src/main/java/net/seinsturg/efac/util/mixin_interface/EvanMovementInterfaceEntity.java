package net.seinsturg.efac.util.mixin_interface;

//Based on https://github.com/PaleMannie/Squake-Ported/blob/1.21-1.21.1/src/main/java/mett/palemannie/squakeport_1_21/ISquakeEntity.java
public interface EvanMovementInterfaceEntity {
    boolean getJumping();

    int getDisabledMovementTicks_Squake();

    void setDisabledMovementTicks_Squake(int amt);

    default boolean shouldReturnMovement_Squake() {
        return getDisabledMovementTicks_Squake() > 0;
    }
}