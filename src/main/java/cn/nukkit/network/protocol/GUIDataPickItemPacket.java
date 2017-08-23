package cn.nukkit.network.protocol;

public class GUIDataPickItemPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.GUI_DATA_PICK_ITEM_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int hotbarSlot;

    @Override
    public void encode() {
        this.hotbarSlot = this.getLInt();
    }

    @Override
    public void decode() {
        this.putLInt(this.hotbarSlot);
    }
}
