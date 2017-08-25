package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;
import cn.nukkit.math.Vector3f;
import cn.nukkit.utils.RuleData;

/**
 * Created on 15-10-13.
 */
public class StartGamePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.START_GAME_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityUniqueId;
    public long entityRuntimeId;
    public int playerGamemode; // 0, 1, 2
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float pitch;
    public int seed;
    public int dimension; // 0, 1, 2
    public int generator = 1; // 0, 1, 2
    public int worldGamemode; // 0, 1, 2, 3
    public int difficulty;
    public int spawnX;
    public int spawnY;
    public int spawnZ;
    public boolean hasAchievementsDisabled = true; //loadInCreative
    public int dayCycleStopTime = -1; //-1 = not stopped, any positive value = stopped at that time
    public boolean eduMode; // 0 - vanilla, 1 - edu
    public float rainLevel;
    public float lightningLevel;
    public boolean isMultiplayerGame = true;
    public boolean hasBroadcastToLAN;
    public boolean hasXboxLiveBroadcast;
    public boolean commandsEnabled;
    public boolean isTexturePacksRequired;
    public RuleData[] ruleDatas = new RuleData[0];
    public boolean hasBonusChestEnabled;
    public boolean hasStartWithMapEnabled;
    public boolean hasTrustPlayersEnabled;
    public int defaultPlayerPermission = AdventureSettingsPacket.PERMISSION_LEVEL_MEMBER;
    public int unknown;
    public String levelId = ""; //base64 string, usually the same as world folder name in vanilla
    public String worldName;
    public String premiumWorldTemplateId = "";
    public boolean unknownBoolean = false;
    public long currentTick = 0;
    public int enchantmentSeed = 0;

    @Override
    public void decode() {
        this.entityUniqueId = this.getVarLong();
        this.entityRuntimeId = this.getUnsignedVarLong();
        this.playerGamemode = this.getVarInt();
        Vector3f v = this.getVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.yaw = this.getLFloat();
        this.pitch = this.getLFloat();
        this.seed = this.getVarInt();
        this.dimension = this.getVarInt();
        this.generator = this.getVarInt();
        this.worldGamemode = this.getVarInt();
        this.difficulty = this.getVarInt();
        BlockVector3 v1 = this.getBlockVector3();
        this.spawnX = v1.x;
        this.spawnY = v1.y;
        this.spawnZ = v1.z;
        this.hasAchievementsDisabled = this.getBoolean();
        this.dayCycleStopTime = this.getVarInt();
        this.eduMode = this.getBoolean();
        this.rainLevel = this.getLFloat();
        this.lightningLevel = this.getLFloat();
        this.isMultiplayerGame = this.getBoolean();
        this.hasBroadcastToLAN = this.getBoolean();
        this.hasXboxLiveBroadcast = this.getBoolean();
        this.commandsEnabled = this.getBoolean();
        this.isTexturePacksRequired = this.getBoolean();
        this.ruleDatas[0] = this.getRuleData();
        this.hasBonusChestEnabled = this.getBoolean();
        this.hasStartWithMapEnabled = this.getBoolean();
        this.hasTrustPlayersEnabled = this.getBoolean();
        this.defaultPlayerPermission = this.getVarInt();
        this.levelId = this.getString();
        this.worldName = this.getString();
        this.premiumWorldTemplateId = this.getString();
        this.unknownBoolean = this.getBoolean();
        this.currentTick = this.getLLong();

        this.enchantmentSeed = this.getVarInt();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityUniqueId);
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putVarInt(this.playerGamemode);
        this.putVector3f(this.x, this.y, this.z);
        this.putLFloat(this.yaw);
        this.putLFloat(this.pitch);
        this.putVarInt(this.seed);
        this.putVarInt(this.dimension);
        this.putVarInt(this.generator);
        this.putVarInt(this.worldGamemode);
        this.putVarInt(this.difficulty);
        this.putBlockVector3(this.spawnX, this.spawnY, this.spawnZ);
        this.putBoolean(this.hasAchievementsDisabled);
        this.putVarInt(this.dayCycleStopTime);
        this.putBoolean(this.eduMode);
        this.putLFloat(this.rainLevel);
        this.putLFloat(this.lightningLevel);
        this.putBoolean(this.isMultiplayerGame);
        this.putBoolean(this.hasBroadcastToLAN);
        this.putBoolean(this.hasXboxLiveBroadcast);
        this.putBoolean(this.commandsEnabled);
        this.putBoolean(this.isTexturePacksRequired);
        this.putUnsignedVarInt(this.ruleDatas.length);
        for (RuleData rule : this.ruleDatas) {
            this.putRuleData(rule);
        }
        this.putBoolean(this.hasBonusChestEnabled);
        this.putBoolean(this.hasStartWithMapEnabled);
        this.putBoolean(this.hasTrustPlayersEnabled);
        this.putVarInt(this.defaultPlayerPermission);
        this.putVarInt(this.unknown);
        this.putString(this.levelId);
        this.putString(this.worldName);
        this.putString(this.premiumWorldTemplateId);
        this.putBoolean(this.unknownBoolean);
        this.putLLong(this.currentTick);
        this.putVarInt(this.enchantmentSeed);
    }

}
