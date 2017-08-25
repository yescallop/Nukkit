package cn.nukkit.network.protocol;

public class StopSoundPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.STOP_SOUND_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String soundName;
    public boolean stopAll;

    @Override
    public void decode() {
        this.soundName = this.getString();
        this.stopAll = this.getBoolean();
    }

    @Override
    public void encode() {
        this.putString(this.soundName);
        this.putBoolean(this.stopAll);
    }
}
