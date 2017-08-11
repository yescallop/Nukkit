package cn.nukkit.network.protocol;

public class SetLastHurtByPacket extends DataPacket {
    
    public int entityTypeId;

    @Override
    public byte pid() {
        return ProtocolInfo.SET_LAST_HURT_BY_PACKET;
    }

    @Override
    public void decode() {
        this.entityTypeId = this.getVarInt();

    }

    @Override
    public void encode() {
        this.reset();
        this.putVarInt(this.entityTypeId);
    }
}
