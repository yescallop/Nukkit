package cn.nukkit.network.protocol;

public class CameraPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.CAMERA_PACKET;

    public long cameraUniqueId;
    public long playerUniqueId;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.cameraUniqueId = this.getVarLong();
        this.playerUniqueId = this.getVarLong();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.cameraUniqueId);
        this.putVarLong(this.playerUniqueId);
    }
}
