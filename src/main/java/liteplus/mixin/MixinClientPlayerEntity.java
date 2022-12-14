package liteplus.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity {

    @Shadow @Final protected MinecraftClient client;
    //private String playerName;

/*
    @Inject(at = @At("HEAD"), method = "sendChatMessagePacket", cancellable = true)
    public void sendChatMessage(ChatMessageSigner signer, String message, Text preview, CallbackInfo ci) {

        assert client.player != null;
        String playerName = client.player.getName().getString();
        List<String> allowedPlayers = GithubReader.readPlayerNames("allowedplayers.txt");
        if (allowedPlayers.contains(playerName)) {
            CrashCommand.onCommand(message, ci);
            CreativeCommand.onCommand(message, ci);
            FlightCommand.onCommand(message, ci);
            GhostCommand.onCommand(message, ci);
            PlayerCopyCommand.onCommand(message, ci);
            TimerCommand.onCommand(message, ci);
            VanishCommand.onCommand(message, ci);
            VisionCommand.onCommand(message, ci);
        }
        LimiterCommand.onCommand(message, ci);
        SkyColorCommand.onCommand(message, ci);
        StatusCommand.onCommand(message, ci);
        CommandList.onCommand(message, ci);
        HelpCommand.onCommand(message, ci);




    }

 */
}