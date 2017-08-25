package cn.nukkit.network.protocol;

public class ShowStoreOfferPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SHOW_STORE_OFFER_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String offerId;
    public boolean unknownBoolean;

    @Override
    public void decode() {
        this.offerId = this.getString();
        this.unknownBoolean = this.getBoolean();
    }

    @Override
    public void encode() {
        this.putString(this.offerId);
        this.putBoolean(this.unknownBoolean);
    }
}
