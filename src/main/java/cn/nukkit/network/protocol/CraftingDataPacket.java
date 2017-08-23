package cn.nukkit.network.protocol;

import cn.nukkit.inventory.FurnaceRecipe;
import cn.nukkit.inventory.ShapedRecipe;
import cn.nukkit.inventory.ShapelessRecipe;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.item.enchantment.EnchantmentEntry;
import cn.nukkit.item.enchantment.EnchantmentList;
import cn.nukkit.utils.BinaryStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Nukkit Project Team
 */
public class CraftingDataPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.CRAFTING_DATA_PACKET;

    public static final int ENTRY_SHAPELESS = 0;
    public static final int ENTRY_SHAPED = 1;
    public static final int ENTRY_FURNACE = 2;
    public static final int ENTRY_FURNACE_DATA = 3;
    public static final int ENTRY_ENCHANT_LIST = 4;
    public static final int ENTRY_SHULKER_BOX = 5;

    public List<Object> entries = new ArrayList<>();
    public boolean cleanRecipes = false;

    private static int writeEntry(Object entry, BinaryStream stream) {
        if (entry instanceof ShapelessRecipe) {
            return writeShapelessRecipe(((ShapelessRecipe) entry), stream);
        } else if (entry instanceof ShapedRecipe) {
            return writeShapedRecipe(((ShapedRecipe) entry), stream);
        } else if (entry instanceof FurnaceRecipe) {
            return writeFurnaceRecipe(((FurnaceRecipe) entry), stream);
        } else if (entry instanceof EnchantmentList) {
            return writeEnchantList(((EnchantmentList) entry), stream);
        }
        return -1;
    }

    private static int writeShapelessRecipe(ShapelessRecipe recipe, BinaryStream stream) {
        stream.putUnsignedVarInt(recipe.getIngredientCount());

        for (Item item : recipe.getIngredientList()) {
            stream.putSlot(item);
        }

        stream.putUnsignedVarInt(1);
        stream.putSlot(recipe.getResult());
        stream.putUUID(recipe.getId());

        return CraftingDataPacket.ENTRY_SHAPELESS;
    }

    private static int writeShapedRecipe(ShapedRecipe recipe, BinaryStream stream) {
        stream.putVarInt(recipe.getWidth());
        stream.putVarInt(recipe.getHeight());

        for (int z = 0; z < recipe.getHeight(); ++z) {
            for (int x = 0; x < recipe.getWidth(); ++x) {
                stream.putSlot(recipe.getIngredient(x, z));
            }
        }

        stream.putUnsignedVarInt(1);
        stream.putSlot(recipe.getResult());

        stream.putUUID(recipe.getId());

        return CraftingDataPacket.ENTRY_SHAPED;
    }

    private static int writeFurnaceRecipe(FurnaceRecipe recipe, BinaryStream stream) {
        if (recipe.getInput().hasMeta()) { //Data recipe
            stream.putVarInt(recipe.getInput().getId());
            stream.putVarInt(recipe.getInput().getDamage());
            stream.putSlot(recipe.getResult());

            return CraftingDataPacket.ENTRY_FURNACE_DATA;
        } else {
            stream.putVarInt(recipe.getInput().getId());
            stream.putSlot(recipe.getResult());

            return CraftingDataPacket.ENTRY_FURNACE;
        }
    }

    private static int writeEnchantList(EnchantmentList list, BinaryStream stream) {
        stream.putByte((byte) list.getSize());
        for (int i = 0; i < list.getSize(); ++i) {
            EnchantmentEntry entry = list.getSlot(i);
            stream.putUnsignedVarInt(entry.getCost());
            stream.putUnsignedVarInt(entry.getEnchantments().length);
            for (Enchantment enchantment : entry.getEnchantments()) {
                stream.putUnsignedVarInt(enchantment.getId());
                stream.putUnsignedVarInt(enchantment.getLevel());
            }
            stream.putString(entry.getRandomName());
        }
        return CraftingDataPacket.ENTRY_ENCHANT_LIST;
    }

    public void addShapelessRecipe(ShapelessRecipe... recipe) {
        Collections.addAll(entries, (ShapelessRecipe[]) recipe);
    }

    public void addShapedRecipe(ShapedRecipe... recipe) {
        Collections.addAll(entries, (ShapedRecipe[]) recipe);
    }

    public void addFurnaceRecipe(FurnaceRecipe... recipe) {
        Collections.addAll(entries, (FurnaceRecipe[]) recipe);
    }

    public void addEnchantList(EnchantmentList... list) {
        Collections.addAll(entries, (EnchantmentList[]) list);
    }

    @Override
    public DataPacket clean() {
        entries = new ArrayList<>();
        return super.clean();
    }

    @Override
    public void decode() {
        /*$entries = [];
        $recipeCount = $this->getUnsignedVarInt();
        for($i = 0; $i < $recipeCount; ++$i){
            $entry = [];
            $entry["type"] = $recipeType = $this->getVarInt();
            switch($recipeType){
                case self::ENTRY_SHAPELESS:
                case self::ENTRY_SHULKER_BOX:
                    $ingredientCount = $this->getUnsignedVarInt();
                    /** @var Item */
                    /*$entry["input"] = [];
                    for($j = 0; $j < $ingredientCount; ++$j){
                        $entry["input"][] = $this->getSlot();
                    }
                    $resultCount = $this->getUnsignedVarInt();
                    $entry["output"] = [];
                    for($k = 0; $k < $resultCount; ++$k){
                        $entry["output"][] = $this->getSlot();
                    }
                    $entry["uuid"] = $this->getUUID()->toString();
                    break;
                case self::ENTRY_SHAPED:
                    $entry["width"] = $this->getVarInt();
                    $entry["height"] = $this->getVarInt();
                    $count = $entry["width"] * $entry["height"];
                    $entry["input"] = [];
                    for($j = 0; $j < $count; ++$j){
                        $entry["input"][] = $this->getSlot();
                    }
                    $resultCount = $this->getUnsignedVarInt();
                    $entry["output"] = [];
                    for($k = 0; $k < $resultCount; ++$k){
                        $entry["output"][] = $this->getSlot();
                    }
                    $entry["uuid"] = $this->getUUID()->toString();
                    break;
                case self::ENTRY_FURNACE:
                case self::ENTRY_FURNACE_DATA:
                    $entry["inputId"] = $this->getVarInt();
                    if($recipeType === self::ENTRY_FURNACE_DATA){
                        $entry["inputDamage"] = $this->getVarInt();
                    }
                    $entry["output"] = $this->getSlot();
                    break;
                case self::ENTRY_MULTI:
                    $entry["uuid"] = $this->getUUID()->toString();
                    break;
                default:
                    throw new \UnexpectedValueException("Unhandled recipe type $recipeType!"); //do not continue attempting to decode
            }
            $entries[] = $entry;
        }
        $this->getBool(); //cleanRecipes*/
    }

    @Override
    public void encode() {
        this.reset();
        this.putUnsignedVarInt(entries.size());

        BinaryStream writer = new BinaryStream();

        for (Object entry : entries) {
            int entryType = writeEntry(entry, writer);
            if (entryType >= 0) {
                this.putVarInt(entryType);
                this.put(writer.getBuffer());
            } else {
                this.putVarInt(-1);
            }

            writer.reset();
        }

        this.putBoolean(cleanRecipes);
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

}
