package meow.kikir.freesia.backend;

import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTLimiter;
import com.github.retrooper.packetevents.protocol.nbt.NBTString;
import com.github.retrooper.packetevents.protocol.nbt.serializer.DefaultNBTSerializer;
import meow.kikir.freesia.backend.misc.VirtualPlayerManager;
import meow.kikir.freesia.backend.tracker.TrackerProcessor;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public final class FreesiaBackend extends JavaPlugin implements Listener {
    public static FreesiaBackend INSTANCE;

    private final TrackerProcessor trackerProcessor = new TrackerProcessor();
    private final VirtualPlayerManager virtualPlayerManager = new VirtualPlayerManager();

    public boolean initialized = false;

    public List<UUID> uuids = new ArrayList<>();

    @Override
    public void onEnable() {
        INSTANCE = this;

        // TODO- De-hard-coding?
        Bukkit.getMessenger().registerIncomingPluginChannel(this, TrackerProcessor.CHANNEL_NAME, this.trackerProcessor);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, TrackerProcessor.CHANNEL_NAME);

        // TODO- De-hard-coding?
        Bukkit.getMessenger().registerIncomingPluginChannel(this, VirtualPlayerManager.CHANNEL_NAME, this.virtualPlayerManager);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, VirtualPlayerManager.CHANNEL_NAME);


        Bukkit.getPluginManager().registerEvents(this.trackerProcessor, this);
        Bukkit.getPluginManager().registerEvents(this, this);

//        virtualPlayerManager.setVirtualPlayerData()

        saveDefaultConfig();
        reloadConfig();



    }

    public VirtualPlayerManager getVirtualPlayerManager() {
        return this.virtualPlayerManager;
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        if (initialized) {
            return; // Already initialized, no need to reinitialize
        }

        init();
        initialized = true;
    }


    public void init(){
        reloadConfig();

        for (String key : getConfig().getKeys(false)) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(Integer.parseInt(key));
            if (npc == null) {
                getLogger().warning("No NPC found with ID " + key + ". Skipping.");
                continue;
            }
            uuids.add(npc.getUniqueId());
            if (npc.getEntity() == null) {
                getLogger().warning("NPC entity is null for ID " + key + ". Skipping.");
                continue;
            }
            try {
                byte[] data = Base64.getDecoder().decode("CgkAC3N0YXJfbW9kZWxzAAAAAAAJAApvd25fbW9kZWxzCAAAAAQAEXdpbmVfZm94X25ld195ZWFyAAh3aW5lX2ZveAALd2luZV9mb3hfamsABmJhaV9sdQoACG1vZGVsX2lkCgALbW9sYW5nX3ZhcnMAAwALaW5zdGFuY2VfaWQAAAAEAQAOcGxheV9hbmltYXRpb24ACAAIbW9kZWxfaWQACHdpbmVfZm94CAAOc2VsZWN0X3RleHR1cmUABHNraW4BAAltYW5kYXRvcnkACAAJYW5pbWF0aW9uAARpZGxlAAA=");// Decode to check if it works
                final DefaultNBTSerializer serializer = new DefaultNBTSerializer();
                final NBTCompound read = (NBTCompound) serializer.deserializeTag(NBTLimiter.forBuffer(data, Integer.MAX_VALUE), new DataInputStream(new ByteArrayInputStream(data)), false);
                NBTCompound modelId = read.getCompoundTagOrNull("model_id");
                modelId.setTag("model_id",new NBTString(getConfig().getString(key + ".model_id")));
                modelId.setTag("select_texture",new NBTString(getConfig().getString(key + ".select_texture")));
                modelId.setTag("animation",new NBTString("idle"));
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final DataOutputStream dos = new DataOutputStream(bos);

                serializer.serializeTag(dos, modelId, true); //1.20.2
                dos.flush();



                virtualPlayerManager.removeVirtualPlayer(npc.getUniqueId());
                Bukkit.getScheduler().runTaskLater(FreesiaBackend.INSTANCE, () -> {
                    virtualPlayerManager.addVirtualPlayer(npc.getUniqueId(), npc.getEntity().getEntityId());
                    Bukkit.getScheduler().runTaskLater(FreesiaBackend.INSTANCE, () -> {
                        virtualPlayerManager.setVirtualPlayerData(npc.getUniqueId(), bos.toByteArray());
                        getLogger().info("读取文件成功，虚拟玩家已添加: " + npc.getUniqueId());
                    }, 10L);
                }, 10L);

            } catch (IOException e) {
                getLogger().severe("Failed to load virtual player data for NPC ID: " + key);
                e.printStackTrace();
            }
        }


    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (args.length == 1 && args[0].equalsIgnoreCase("reload")){


            init();
            sender.sendMessage("虚拟玩家数据已重新加载。");
            return true;
        }


        return true;
    }


//    @EventHandler
//    public void onEvent(CyanidinTrackerScanEvent event){
//        NPC npc = CitizensAPI.getNPCRegistry().getById(0);
//        if (npc == null) {
//            System.out.println("No NPC found with ID 0.");
//            return;
//        }
//        if (npc.getEntity() == null) {
//            System.out.println("NPC entity is null.");
//            return;
//        }
//        event.getResultsModifiable().add(npc.getUniqueId());
//        System.out.println("Tracker scan event processed for NPC: " + npc.getUniqueId());
//    }
//



}
