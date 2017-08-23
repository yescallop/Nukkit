package cn.nukkit.network.protocol;

/**
 * Created by CreeperFace on 5.3.2017.
 */
public class MapInfoRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.MAP_INFO_REQUEST_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long mapId;

    @Override
    public void decode() {
        mapId = this.getVarLong();
    }

    @Override
    public void encode() {
        this.putVarLong(this.mapId);
    }
}
