package boogie.utilmod.screens;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

import static java.lang.Math.floor;

public class WorldInfoScreen extends LightweightGuiDescription {

    WorldInfoScreen(){
        WGridPanel root = new WGridPanel();
        root.setSize(356,204);
        setRootPanel(root);

        WLabel label = new WLabel(I18n.translate("gui.utilities.world"));
        root.add(label,5,1,10,1);
        PlayerEntity player = MinecraftClient.getInstance().player;
        World world;
        assert player != null;
        world = player.world;
        Biome playerbiome = world.getBiome(player.getBlockPos());
        Chunk chunk = world.getChunk(player.getBlockPos());
        Difficulty difficulty = world.getDifficulty();
        long time = world.getTimeOfDay();
        boolean raining = world.isRaining();
        boolean thundering = world.isThundering();
        String weather = I18n.translate("gui.utilities.world.clear");
        if(raining) weather = I18n.translate("gui.utilities.world.raining");
        if(thundering) weather = I18n.translate("gui.utilities.world.thundering");
        double x = player.x;
        x = floor(x*10000) / 10000.0;
        double y = player.y;
        y = floor(y*10000) / 10000.0;
        double z = player.z;
        z = floor(z*10000) / 10000.0;

        String biome = playerbiome.toString();
        String pchunk = chunk.toString();
        String diff = difficulty.toString();
        String wtime = Long.toString(time);
        String wthr = weather;
        String uuid = player.getUuidAsString();
        String pos = "X:" + x + " Y:" + y + " Z:" + z;

        biome = biome.replaceAll("net.minecraft.world.biome.","");
        pchunk = pchunk.replaceAll("net.minecraft.world.chunk.","");

        WLabel biomeLabel = new WLabel(I18n.translate("gui.utilities.world.biome") + biome);
        WLabel pchunkLabel = new WLabel(I18n.translate("gui.utilities.world.chunk") + pchunk);
        WLabel diffLabel = new WLabel(I18n.translate("gui.utilities.world.difficulty") + diff);
        WLabel wtimeLabel = new WLabel(I18n.translate("gui.utilities.world.time")+ wtime);
        WLabel wthrLabel = new WLabel(I18n.translate("gui.utilities.world.weather") + wthr);
        WLabel uuidLabel = new WLabel(I18n.translate("gui.utilities.world.uuid") + uuid);
        WLabel posLabel = new WLabel(I18n.translate("gui.utilities.world.pos") + pos);

        root.add(posLabel,1,3,8,1);
        root.add(uuidLabel,1,4,8,1);
        root.add(wthrLabel,1,5,8,1);
        root.add(diffLabel,1,6,8,1);
        root.add(pchunkLabel,1,7,8,1);
        root.add(biomeLabel,1,8,8,1);
        root.add(wtimeLabel,1,9,8,1);

        root.validate(this);
    }
    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA);
    }

}
