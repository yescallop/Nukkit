package cn.nukkit.network.protocol;

import cn.nukkit.network.protocol.types.ContainerIds;
import cn.nukkit.network.protocol.types.WindowTypes;

public class UpdateTradePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.UPDATE_TRADE_PACKET;

    public int windowId;
    public int windowType = WindowTypes.TRADING; // Mojang hardcoded this
    public int unknownVarInt1;
    public int unknownVarInt2;
    public boolean isWilling;
    public long tradeEid;
    public long playeEid;
    public String displayName;
    public byte[] offers;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.windowId = this.getByte();
        this.windowType = this.getByte();
        this.unknownVarInt1 = this.getVarInt();
        this.unknownVarInt2 = this.getVarInt();
        this.isWilling = this.getBoolean();
        this.tradeEid = this.getVarLong();
        this.playeEid = this.getVarLong();
        this.displayName = this.getString();
        this.offers = this.getByteArray();
    }

    @Override
    public void encode() {
        this.putByte((byte) windowId);
        this.putByte((byte) windowType);
        this.putVarInt(unknownVarInt1);
        this.putVarInt(unknownVarInt2);
        this.putBoolean(isWilling);
        this.putVarLong(tradeEid);
        this.putVarLong(playeEid);
        this.putString(displayName);
        this.putByteArray(this.offers);
    }

}
