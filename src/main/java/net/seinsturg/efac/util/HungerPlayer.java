package net.seinsturg.efac.util;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.neoforged.fml.LogicalSide;
import net.seinsturg.efac.data.EvansData;

public class HungerPlayer {
    private final Player player;

    public HungerPlayer(Player player) {
        this.player = player;
    }

    public static HungerPlayer get (Player player) {
        return new HungerPlayer(player);
    }

    public void tickStart() {
        setFoodData(player.getFoodData(), calcHunger());
        player.setData(EvansData.LAST_HUNGER, player.getFoodData().getFoodLevel());
    }

    public void tickEnd(LogicalSide side) {
        if (side == LogicalSide.SERVER) {
            int foodDiff = player.getFoodData().getFoodLevel() - player.getData(EvansData.LAST_HUNGER);
            if (foodDiff > 0)
                player.heal((foodDiff * 0.5f));
        }
        setFoodData(player.getFoodData(), calcHunger());
    }

    private void setFoodData (FoodData foodStats, int foodLevel) {
        foodStats.eat(1, ((float) 1 - foodStats.getSaturationLevel()) / 2);
        foodStats.eat(foodLevel - foodStats.getFoodLevel(), 0);
    }

    private int calcHunger() {
        if (player.hasEffect(MobEffects.HUNGER)) {
            return 5;
        } else if (player.hasEffect(MobEffects.REGENERATION)) {
            return 19;
        } else {
            return 10;
        }
    }
}
