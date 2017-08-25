package cn.nukkit.network.protocol;

public class ModalFormResponsePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.MODAL_FORM_RESPONSE_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int formId;
    public String formData; // JSON

    @Override
    public void decode() {
        this.formId = (int) this.getUnsignedVarInt();
        this.formData = this.getString(); //Data will be null if player close form without submit (by cross button or ESC)
    }

    @Override
    public void encode() {
        this.putUnsignedVarInt(this.formId);
        this.putString(this.formData);
    }
}
