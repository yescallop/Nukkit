package cn.nukkit.network.protocol;

import cn.nukkit.network.protocol.types.ContainerIds;

public class PlayerHotbarPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PLAYER_HOTBAR_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int selectedSlot;
    public int windowId = ContainerIds.INVENTORY;
    public int[] data = new int[0];

    @Override
    public void decode() {
        this.selectedSlot = (int) this.getUnsignedVarInt();
        this.windowId = this.getByte();
        int count = (int) this.getUnsignedVarInt();
        this.data = new int[count];
        for (int s = 0; s < count && !this.feof(); ++s) {
            this.data[s] = (int) this.getUnsignedVarInt();
        }
    }

    @Override
    public void encode() {
        this.putUnsignedVarInt(selectedSlot);
        this.putByte((byte) this.windowId);
        this.putUnsignedVarInt(this.data.length);
        for (int slot : this.data) {
            this.putUnsignedVarInt(slot);
        }
    }
}
