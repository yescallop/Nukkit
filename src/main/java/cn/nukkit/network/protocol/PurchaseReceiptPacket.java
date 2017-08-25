package cn.nukkit.network.protocol;

public class PurchaseReceiptPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PURCHASE_RECEIPT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String[] entries = new String[0];

    @Override
    public void decode() {
        int count = (int) this.getUnsignedVarInt();
        for (int i = 0; i < count; ++i) {
            this.entries[i] = this.getString();
        }
    }

    @Override
    public void encode() {
        this.putUnsignedVarInt(this.entries.length);
        for (String entry : this.entries) {
            this.putString(entry);
        }
    }
}
