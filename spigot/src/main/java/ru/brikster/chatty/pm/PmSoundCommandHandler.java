package ru.brikster.chatty.pm;

import cloud.commandframework.context.CommandContext;
import cloud.commandframework.execution.CommandExecutionHandler;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public final class PmSoundCommandHandler implements CommandExecutionHandler<CommandSender> {

    private final PmSoundStorage pmSoundStorage;
    private final BukkitAudiences audiences;

    @Override
    public void execute(@NonNull CommandContext<CommandSender> commandContext) {
        CommandSender sender = commandContext.getSender();
        if (!(sender instanceof Player)) {
            audiences.sender(sender).sendMessage(
                    Component.text("Only players can use this command.", NamedTextColor.RED));
            return;
        }

        pmSoundStorage.toggle(sender.getName());
    }

}