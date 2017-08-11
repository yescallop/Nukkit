package cn.nukkit.network.protocol;

public class NPCRequestPacket extends DataPacket {
    
    public int entityRuntimeId;
    public int requestType;
    public String commandString;
    public int actionType;

    @Override
    public byte pid() {
        return ProtocolInfo.NPC_REQUEST_PACKET;
    }

    @Override
    public void decode() {
        this.entityRuntimeId = (int) this.getVarLong();
        this.requestType = this.getByte();
        this.commandString = this.getString();
        this.actionType = this.getByte();

    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityRuntimeId);
        this.putByte((byte) this.requestType);
        this.putString(this.commandString);
        this.putByte((byte) this.actionType);
    }
}
