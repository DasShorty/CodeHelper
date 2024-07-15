package org.example.core.commands;

import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.client.entity.player.Inventory;
import net.labymod.api.client.world.item.ItemStack;
import net.labymod.api.component.data.DataComponentContainer;
import net.labymod.api.component.data.DataComponentKey;

public class Base64Command extends Command {

  public Base64Command() {
    super("base64", "");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {

    ClientPlayer clientPlayer = this.labyAPI.minecraft().getClientPlayer();

    if (clientPlayer == null) {
      this.displayMessage(Component.text("Player not found!").color(NamedTextColor.RED));
      return true;
    }

    Inventory inventory = clientPlayer.inventory();
    ItemStack itemStack = inventory.itemStackAt(inventory.getSelectedIndex());

    DataComponentContainer dataComponentContainer = itemStack.getOrCreateDataComponentContainer();
    System.out.println(dataComponentContainer.keySet());

    this.displayMessage(Component.text("--------[").color(NamedTextColor.GRAY).append(
        Component.text("Data Component Container").color(NamedTextColor.DARK_PURPLE)
            .append(Component.text("]--------").color(NamedTextColor.GRAY))));

    for (DataComponentKey dataComponentKey : dataComponentContainer.keySet()) {

      Object object = dataComponentContainer.get(dataComponentKey);

      this.displayMessage(Component.text("DataKey: ").color(NamedTextColor.GRAY)
          .append(Component.text(dataComponentKey.name()).color(NamedTextColor.GOLD))
          .append(Component.text(" | DCC > ").color(NamedTextColor.GRAY))
          .append(Component.text("<click to copy>")
              .clickEvent(ClickEvent.copyToClipboard(object.toString()))
              .color(NamedTextColor.GREEN)));

    }

    this.displayMessage(Component.text("--------[").color(NamedTextColor.GRAY).append(
        Component.text("Data Component Container").color(NamedTextColor.DARK_PURPLE)
            .append(Component.text("]--------").color(NamedTextColor.GRAY))));

    return true;
  }
}
