package net.seinsturg.efac.item.custom.charms;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public abstract class CharmItem extends Item {
    CharmFunction function;
    public CharmItem(CharmFunction function, Properties properties) {
        super(properties);
        this.function = function;
        properties.stacksTo(1);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.efac.charm.title"));
        tooltipComponents.add(Component.translatable(function.getTooltipDesc()));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public CharmFunction.PayloadDirection getDirection() {
        return this.function.getDirection();
    }

    //Override to perform an action on the client only
    public void clientAction(Player player) {}
    //Override and send a payload to perform an action on the server only
    public void serverAction(Player player) {}
}
