package info.u_team.music_player.init;

import org.lwjgl.glfw.GLFW;

import info.u_team.music_player.event.EventHandlerMusicPlayer;
import info.u_team.u_team_core.registry.util.*;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class MusicPlayerKeys {
	
	public static final KeyBinding open = new KeyBinding("Open", GLFW.GLFW_KEY_F8, "Music Player");
	
	public static void construct() {
		ClientRegistry.registerKeybinding(open);
		CommonRegistry.registerEventHandler(EventHandlerMusicPlayer.class);
	}
}
