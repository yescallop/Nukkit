package cn.nukkit.inventory.transaction;

import cn.nukkit.Player;

import java.util.Set;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public interface TransactionGroup {

    int TYPE_NORMAL = 0;
    int TYPE_ITEM_USE = 1; // Checked
    int TYPE_ITEM_RELEASE = 2; // Checked
    int TYPE_INVENTORY_MISMATCH = 3; //I do not know this
    int TYPE_ITEM_USE_ON_ENTITY = 4; //I do not know this

    int ITEM_RELEASE_ACTION_RELEASE = 0; //Do not know, probably fireworks
    int ITEM_RELEASE_ACTION_USE = 1; //Shoot arrows, etc.

    //Checked
    int ITEM_USE_ACTION_PLACE = -1;
    int ITEM_USE_ACTION_USE = 0;
    int ITEM_USE_ACTION_DESTROY = 1;

    int ITEM_USE_ON_ENTITY_ACTION_INTERACT = 0; //TODO: Check the use of these actions
    int ITEM_USE_ON_ENTITY_ACTION_ATTACK = 1;
    int ITEM_USE_ON_ENTITY_ACTION_ITEM_INTERACT = 2;

    long getCreationTime();

    Set<Transaction> getTransactions();

    void addTransaction(Transaction transaction);

    boolean canExecute(Player player);

    boolean executeOn(Player player);

    boolean executeOn(Player player, boolean force);

    boolean hasExecuted();

    int getType();
}
