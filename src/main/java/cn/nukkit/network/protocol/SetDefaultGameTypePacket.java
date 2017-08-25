package cn.nukkit.network.protocol;

public class SetDefaultGameTypePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SET_DEFAULT_GAME_TYPE_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int gamemode;

    @Override
    public void decode() {
        this.gamemode = this.getVarInt(); // Idk var, it's encoded unsigned
    }

    @Override
    public void encode() {
        this.putUnsignedVarInt(this.gamemode);
    }
}
