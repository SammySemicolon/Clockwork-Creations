package com.sammy.clockwork_creations.setup;

import com.sammy.clockwork_creations.ClockworkCreationsMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ClockworkCreationsMod.MODID);

    public static final RegistryObject<SoundEvent> CUCKOO_CHIME = register(new SoundEvent(ClockworkCreationsMod.path("cuckoo_chime")));
    public static final RegistryObject<SoundEvent> CUCKOO_TICK = register(new SoundEvent(ClockworkCreationsMod.path("cuckoo_tick")));
    public static final RegistryObject<SoundEvent> REGULATOR_CHIME = register(new SoundEvent(ClockworkCreationsMod.path("regulator_chime")));
    public static final RegistryObject<SoundEvent> REGULATOR_TICK = register(new SoundEvent(ClockworkCreationsMod.path("regulator_tick")));
    public static final RegistryObject<SoundEvent> GRANDFATHER_CHIME = register(new SoundEvent(ClockworkCreationsMod.path("grandfather_chime")));
    public static final RegistryObject<SoundEvent> GRANDFATHER_TICK = register(new SoundEvent(ClockworkCreationsMod.path("grandfather_tick")));

    private static RegistryObject<SoundEvent> register(SoundEvent soundEvent) {
        return SOUNDS.register(soundEvent.getLocation().getPath(), () -> soundEvent);
    }
}