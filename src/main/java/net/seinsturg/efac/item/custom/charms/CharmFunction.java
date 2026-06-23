package net.seinsturg.efac.item.custom.charms;

public enum CharmFunction {
    LIGHTNING_FUNCTION("tooltip.efac.charm.lightning.desc", PayloadDirection.SERVER),
    DASH_FUNCTION("tooltip.efac.charm.dash.desc", PayloadDirection.BIDIRECTIONAL),
    BLINK_FUNCTION("tooltip.efac.charm.blink.desc", PayloadDirection.BIDIRECTIONAL),
    FIREBALL_FUNCTION("tooltip.efac.charm.fireball.desc", PayloadDirection.SERVER),
    PRANK_FUNCTION("tooltip.efac.charm.prank.desc", PayloadDirection.SERVER),
    HEALTH_FUNCTION("tooltip.efac.charm.health.desc", PayloadDirection.SERVER),
    PHILOSOPHER_FUNCTION("tooltip.efac.charm.philosopher.desc", PayloadDirection.NULL);

    //variables
    private final String tooltipDesc;
    private final PayloadDirection direction;

    CharmFunction(String tooltipDesc, PayloadDirection direction) {
        this.tooltipDesc = tooltipDesc;
        this.direction = direction;
    }

    public String getTooltipDesc() {
        return tooltipDesc;
    }
    public PayloadDirection getDirection() {
        return direction;
    }

    public enum PayloadDirection {
        NULL,
        CLIENT,
        SERVER,
        BIDIRECTIONAL;

        PayloadDirection() {
        }
    }
}
