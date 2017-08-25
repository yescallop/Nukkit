package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;

public class PlaySoundPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PLAY_SOUND_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String soundName;
    public int x;
    public int y;
    public int z;
    public float volume;
    public float pitch;

    @Override
    public void decode() {
        this.soundName = this.getString();
        BlockVector3 v = this.getBlockVector3();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.volume = this.getLFloat();
        this.pitch = this.getLFloat();
    }

    @Override
    public void encode() {
        this.putString(this.soundName);
        this.putBlockVector3(this.x, this.y, this.z);
        this.putLFloat(this.volume);
        this.putLFloat(this.pitch);
    }
}
