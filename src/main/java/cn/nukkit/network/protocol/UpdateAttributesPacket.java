package cn.nukkit.network.protocol;

import cn.nukkit.entity.Attribute;

/**
 * @author Nukkit Project Team
 */
public class UpdateAttributesPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.UPDATE_ATTRIBUTES_PACKET;

    public long entityRuntimeId;
    public Attribute[] entries;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public void decode() {
        this.entityRuntimeId = this.getUnsignedVarLong();

        if (this.entries == null) {
            this.getUnsignedVarInt();
        } else {
            for (Attribute entry : this.entries) {
                entry.getMinValue();
                entry.getMaxValue();
                entry.getValue();
                entry.getDefaultValue();
                entry.getName();
            }
        }
    }

    public void encode() {
        this.reset();

        this.putUnsignedVarLong(this.entityRuntimeId);

        if (this.entries == null) {
            this.putUnsignedVarInt(0);
        } else {
            this.putUnsignedVarInt(this.entries.length);
            for (Attribute entry : this.entries) {
                this.putLFloat(entry.getMinValue());
                this.putLFloat(entry.getMaxValue());
                this.putLFloat(entry.getValue());
                this.putLFloat(entry.getDefaultValue());
                this.putString(entry.getName());
            }
        }
    }

}
