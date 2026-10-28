package defense.common.compat;

import net.minecraft.world.World;
import net.sxmaa.u25core.api.radiation.RadiationAPI;
import defense.common.Settings;

/** Nuclear fallout through U25Core's radiation. Only touched when U25Core is loaded. */
public final class U25CoreRadiation
{
    private U25CoreRadiation() {}

    /** Contaminates every chunk within the blast radius, strongest at ground zero. */
    public static void fallout(World world, double x, double y, double z, float radius)
    {
        int chunks = (int) Math.ceil(radius / 16);
        for (int dx = -chunks; dx <= chunks; dx++)
        {
            for (int dz = -chunks; dz <= chunks; dz++)
            {
                double distance = Math.sqrt(dx * dx + dz * dz) * 16;
                if (distance > radius) continue;
                RadiationAPI.contaminate(world, x + dx * 16, y, z + dz * 16, Settings.NUCLEAR_FALLOUT * (1 - distance / radius));
            }
        }
    }
}
