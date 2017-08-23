package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;
import cn.nukkit.math.Vector3;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class ExplodePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.EXPLODE_PACKET;

    public float x;
    public float y;
    public float z;
    public float radius;
    public Vector3[] records = new Vector3[0];

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public DataPacket clean() {
        this.records = new Vector3[0];
        return super.clean();
    }

    @Override
    public void decode() {
        BlockVector3 v = this.getBlockVector3();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.radius = (float) (this.getVarInt() / 32);

        int count = (int) this.getUnsignedVarInt();
        for (int i = 0; i < count; ++i) {
            x = y = z = 0.0f; // NULL
            // $this->getSignedBlockPosition($x, $y, $z);
            // Idk it's correct. Need check. @Anyone
            BlockVector3 v1 = this.getBlockVector3();
            this.x = v1.x;
            this.y = v1.y;
            this.z = v1.z;
            //
            this.records[i] = new Vector3(x, y, z);
        }
    }

    @Override
    public void encode() {
        this.reset();
        this.putVector3f(this.x, this.y, this.z);
        this.putVarInt((int) (this.radius * 32));
        this.putUnsignedVarInt(this.records.length);
        if (this.records.length > 0) {
            for (Vector3 record : records) {
                this.putBlockVector3((int) record.x, (int) record.y, (int) record.z);
            }
        }
    }

}
