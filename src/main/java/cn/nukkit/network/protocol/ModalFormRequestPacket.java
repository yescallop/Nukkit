package cn.nukkit.network.protocol;

public class ModalFormRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.MODAL_FORM_REQUEST_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int formId;
    public String formData; // JSON

    @Override
    public void decode() {
        this.formId = (int) this.getUnsignedVarInt();
        this.formData = this.getString();
    }

    @Override
    public void encode() {
        this.reset();
        this.putUnsignedVarInt(this.formId);
        this.putString(this.formData);
    }
}
