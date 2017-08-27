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
    public int playerGamemode;
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float pitch;
    public int seed;
    public byte dimension;
    public int generator = 1;
    public int gamemode;
    public int difficulty;
    public int spawnX;
    public int spawnY;
    public int spawnZ;
    public boolean hasAchievementsDisabled = true;
    public int dayCycleStopTime = -1; //-1 = not stopped, any positive value = stopped at that time
    public boolean eduMode = false;
    public float rainLevel;
    public float lightningLevel;
    public boolean multiplayerGame = true;
    public boolean broadcastToLAN = true;
    public boolean broadcastToXboxLive = true;
    public boolean commandsEnabled;
    public boolean isTexturePacksRequired = false;
    public RuleData[] ruleDatas = new RuleData[0];
    public boolean bonusChest = false;
    public boolean hasStartWithMapEnabled = false;
    public boolean trustPlayers = false;
    public int permissionLevel = AdventureSettingsPacket.PERMISSION_LEVEL_MEMBER; // 1
    public int gamePublish = 4;
    public String levelId = ""; //base64 string, usually the same as world folder name in vanilla
    public String worldName;
    public String premiumWorldTemplateId = "";
    public boolean unknown = false;
    public long currentTick;
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
        this.dimension = (byte) this.getVarInt();
        this.generator = this.getVarInt();
        this.gamemode = this.getVarInt();
        this.difficulty = this.getVarInt();
        BlockVector3 blockVector3 = this.getBlockVector3();
        blockVector3.add(this.spawnX, this.spawnY, this.spawnZ);
        this.hasAchievementsDisabled = this.getBoolean();
        this.dayCycleStopTime = this.getVarInt();
        this.eduMode = this.getBoolean();
        this.rainLevel = this.getLFloat();
        this.lightningLevel = this.getFloat();
        this.multiplayerGame = this.getBoolean();
        this.broadcastToLAN = this.getBoolean();
        this.broadcastToXboxLive = this.getBoolean();
        this.commandsEnabled = this.getBoolean();
        this.isTexturePacksRequired = this.getBoolean();
        this.ruleDatas[0] = this.getRuleData();
        this.bonusChest = this.getBoolean();
        this.hasStartWithMapEnabled = this.getBoolean();
        this.trustPlayers = this.getBoolean();
        this.permissionLevel = this.getVarInt();
        this.gamePublish = this.getVarInt();
        this.levelId = this.getString();
        this.worldName = this.getString();
        this.premiumWorldTemplateId = this.getString();
        this.unknown = this.getBoolean();
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
        this.putVarInt(this.gamemode);
        this.putVarInt(this.difficulty);
        this.putBlockVector3(this.spawnX, this.spawnY, this.spawnZ);
        this.putBoolean(this.hasAchievementsDisabled);
        this.putVarInt(this.dayCycleStopTime);
        this.putBoolean(this.eduMode);
        this.putLFloat(this.rainLevel);
        this.putLFloat(this.lightningLevel);
        this.putBoolean(this.multiplayerGame);
        this.putBoolean(this.broadcastToLAN);
        this.putBoolean(this.broadcastToXboxLive);
        this.putBoolean(this.commandsEnabled);
        this.putBoolean(this.isTexturePacksRequired);
        this.putUnsignedVarInt(this.ruleDatas.length);
        for (RuleData rule : this.ruleDatas) {
            this.putRuleData(rule);
        }
        this.putBoolean(this.bonusChest);
        this.putBoolean(this.hasStartWithMapEnabled);
        this.putBoolean(this.trustPlayers);
        this.putVarInt(this.permissionLevel);
        this.putVarInt(this.gamePublish);
        this.putString(this.levelId);
        this.putString(this.worldName);
        this.putString(this.premiumWorldTemplateId);
        this.putBoolean(this.unknown);
        this.putLLong(this.currentTick);
        this.putVarInt(this.enchantmentSeed);
    }

}
