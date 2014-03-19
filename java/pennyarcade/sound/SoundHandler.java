package pennyarcade.sound;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SoundHandler {

	public static void onEntityPlay(String name , World world, Entity entityName, float volume, float pitch)	{
        world.playSoundAtEntity(entityName, ("pennyarcade:" + name), (float)volume, (float)pitch);
	}
}
