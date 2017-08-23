package cn.nukkit.network.protocol;

import cn.nukkit.utils.RuleData;


/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class GameRulesChangedPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.GAME_RULES_CHANGED_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public RuleData[] gameRules = new RuleData[0];

    @Override
    public void decode() {
        this.gameRules[0] = this.getRuleData();
    }

    @Override
    public void encode() {
        this.reset();
        this.putInt(this.gameRules.length);
        for (RuleData rule : this.gameRules) {
            this.putRuleData(rule);
        }
    }
}
