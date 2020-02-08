package info.u_team.music_player.musicplayer;

import org.apache.logging.log4j.*;

import com.google.gson.*;

import info.u_team.music_player.dependency.DependencyManager;
import info.u_team.music_player.lavaplayer.api.IMusicPlayer;

public class MusicPlayerManager {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static IMusicPlayer PLAYER;
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	private static final PlaylistManager PLAYLIST_MANAGER = new PlaylistManager(GSON);
	private static final SettingsManager SETTINGS_MANAGER = new SettingsManager(GSON);
	
	public static void setup() {
		generatePlayer();
		PLAYER.startAudioOutput();
		PLAYLIST_MANAGER.loadFromFile();
		SETTINGS_MANAGER.loadFromFile();
		
		PLAYER.setVolume(SETTINGS_MANAGER.getSettings().getVolume());
	}
	
	private static void generatePlayer() {
		try {
			Class<?> clazz = Class.forName("info.u_team.music_player.lavaplayer.MusicPlayer", true, DependencyManager.getClassLoader());
			if (!IMusicPlayer.class.isAssignableFrom(clazz)) {
				throw new IllegalAccessError("The class " + clazz + " does not implement IMusicPlayer! This should not happen?!");
			}
			PLAYER = (IMusicPlayer) clazz.getDeclaredConstructor().newInstance();
			LOGGER.info("Successfully created music player instance");
		} catch (Exception ex) {
			LOGGER.fatal("Cannot create music player instance. This is a serious bug and the mod will not work. Report to the mod authors", ex);
			System.exit(0);
		}
	}
	
	public static IMusicPlayer getPlayer() {
		return PLAYER;
	}
	
	public static PlaylistManager getPlaylistManager() {
		return PLAYLIST_MANAGER;
	}
	
	public static SettingsManager getSettingsManager() {
		return SETTINGS_MANAGER;
	}
}