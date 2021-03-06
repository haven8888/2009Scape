package core.game.content.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.game.node.item.Item;

/**
 * Dialogue for the boss pet, Commander Zilyana JR.
 * @author Splinter
 * @version 1.0
 */
@Initializable
public final class ZilyanaJRDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ZilyanaJRDialogue} {@code Object}.
	 */
	public ZilyanaJRDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ZilyanaJRDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ZilyanaJRDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ZilyanaJRDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if(player.getInventory().containsItem(new Item(11698, 1)) || (player.getEquipment().get(3) != null && player.getEquipment().get(3).getId() == 11698)){
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I FOUND THE GODSWORD!");
			stage = 0;
		} else {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "FIND THE GODSWORD!");
			stage = 1;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.OLD_NORMAL, "GOOD!");
			stage = 2;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.OLD_NORMAL, "FIND THE GODSWORD!");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8593 };
	}
}
