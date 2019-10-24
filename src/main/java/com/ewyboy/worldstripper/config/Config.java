package com.ewyboy.worldstripper.config;

import com.ewyboy.worldstripper.other.Reference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@me.sargunvohra.mcmods.autoconfig1.annotation.Config(name = Reference.ModInfo.MOD_NAME)
public class Config implements ConfigData {

    private int blockStateFlag = 3;
    private int chunkRadiusX = 3;
    private int chunkRadiusZ = 3;
    private String replacementBlock = "minecraft:air";
    private int selectedProfile = 1;

    private Set<String> stripProfile1 = Sets.newHashSet(defaultProfile);
    private Set<String> stripProfile2 = Sets.newHashSet(defaultProfile);
    private Set<String> stripProfile3 = Sets.newHashSet(defaultProfile);
    private Set<String> stripProfile4 = Sets.newHashSet(defaultProfile);
    private Set<String> stripProfile5 = Sets.newHashSet(defaultProfile);

    private static List<String> defaultProfile = new ArrayList<>();

    static  {
        defaultProfile.add("minecraft:dirt");
        defaultProfile.add("minecraft:grass");
        defaultProfile.add("minecraft:tall_grass");
        defaultProfile.add("minecraft:grass_block");
        defaultProfile.add("minecraft:stone");
        defaultProfile.add("minecraft:diorite");
        defaultProfile.add("minecraft:granite");
        defaultProfile.add("minecraft:andesite");
        defaultProfile.add("minecraft:gravel");
        defaultProfile.add("minecraft:sand");
        defaultProfile.add("minecraft:sandstone");
        defaultProfile.add("minecraft:oak_log");
        defaultProfile.add("minecraft:dark_oak_log");
        defaultProfile.add("minecraft:spruce_log");
        defaultProfile.add("minecraft:birch_log");
        defaultProfile.add("minecraft:jungle_log");
        defaultProfile.add("minecraft:acacia_log");
        defaultProfile.add("minecraft:oak_leaves");
        defaultProfile.add("minecraft:dark_oak_leaves");
        defaultProfile.add("minecraft:spruce_leaves");
        defaultProfile.add("minecraft:birch_leaves");
        defaultProfile.add("minecraft:jungle_leaves");
        defaultProfile.add("minecraft:acacia_leaves");
        defaultProfile.add("minecraft:water");
        defaultProfile.add("minecraft:flowing_water");
        defaultProfile.add("minecraft:lava");
        defaultProfile.add("minecraft:flowing_lava");
        defaultProfile.add("minecraft:netherrack");
        defaultProfile.add("minecraft:end_stone");
        defaultProfile.add("minecraft:podzol");
        defaultProfile.add("minecraft:bamboo");
        defaultProfile.add("minecraft:seagrass");
        defaultProfile.add("minecraft:tall_seagrass");
    }


    static Screen createConfigGui(Screen parent) {
        Config config = ConfigHandler.getConfig();
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(String.format("config.%s.title", Reference.ModInfo.MOD_ID));
        builder.setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/dirt.png"));
        ConfigCategory general = builder.getOrCreateCategory("general");
        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();
                general.addEntry(entryBuilder.startTextDescription("Placeholder Text").build());
                general.addEntry(entryBuilder.startIntField("block_state_flag", config.getBlockStateFlag()).setDefaultValue(3).setMin(0).setMax(16).setSaveConsumer(i -> config.blockStateFlag = i).build());
                general.addEntry(entryBuilder.startIntField("chunk_radius_x", config.getChunkRadiusX()).setDefaultValue(3).setMin(0).setMax(256).setSaveConsumer(i -> config.chunkRadiusX = i).build());
                general.addEntry(entryBuilder.startIntField("chunk_radius_z", config.getChunkRadiusZ()).setDefaultValue(3).setMin(0).setMax(256).setSaveConsumer(i -> config.chunkRadiusZ = i).build());
                general.addEntry(entryBuilder.startStrField("replacement_block", config.replacementBlock).setDefaultValue("minecraft:air").setSaveConsumer(str -> config.replacementBlock = str).build());
                general.addEntry(entryBuilder.startIntField("selected_profile", config.getSelectedProfile()).setDefaultValue(1).setMin(1).setMax(5).setSaveConsumer(i -> config.selectedProfile = i).build());
                general.addEntry(entryBuilder.startStrList("profile_1", Lists.newArrayList(config.stripProfile1)).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? I18n.translate("config.worldstripper.error.invalid_identifier", value) : null)).setDefaultValue(Lists.newArrayList(defaultProfile)).setExpended(true).setSaveConsumer(strings -> config.stripProfile1 = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build());
                general.addEntry(entryBuilder.startStrList("profile_2", Lists.newArrayList(config.stripProfile2)).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? I18n.translate("config.worldstripper.error.invalid_identifier", value) : null)).setDefaultValue(Lists.newArrayList(defaultProfile)).setExpended(true).setSaveConsumer(strings -> config.stripProfile1 = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build());
                general.addEntry(entryBuilder.startStrList("profile_3", Lists.newArrayList(config.stripProfile3)).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? I18n.translate("config.worldstripper.error.invalid_identifier", value) : null)).setDefaultValue(Lists.newArrayList(defaultProfile)).setExpended(true).setSaveConsumer(strings -> config.stripProfile1 = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build());
                general.addEntry(entryBuilder.startStrList("profile_4", Lists.newArrayList(config.stripProfile4)).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? I18n.translate("config.worldstripper.error.invalid_identifier", value) : null)).setDefaultValue(Lists.newArrayList(defaultProfile)).setExpended(true).setSaveConsumer(strings -> config.stripProfile1 = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build());
                general.addEntry(entryBuilder.startStrList("profile_5", Lists.newArrayList(config.stripProfile5)).setCellErrorSupplier(value -> Optional.ofNullable(!Identifier.isValid(value) ? I18n.translate("config.worldstripper.error.invalid_identifier", value) : null)).setDefaultValue(Lists.newArrayList(defaultProfile)).setExpended(true).setSaveConsumer(strings -> config.stripProfile1 = strings.stream().filter(Identifier::isValid).map(Identifier::new).map(Identifier::toString).collect(Collectors.toSet())).build());
        builder.setSavingRunnable(ConfigHandler :: save);
        return builder.build();
    }


    public int getBlockStateFlag() {
        return blockStateFlag;
    }

    public int getChunkRadiusX() {
        return chunkRadiusX;
    }

    public int getChunkRadiusZ() {
        return chunkRadiusZ;
    }

    public int getSelectedProfile() {
        return selectedProfile;
    }

    public Set<String> getStripProfile1() {
        return stripProfile1;
    }

    public Set<String> getStripProfile2() {
        return stripProfile2;
    }

    public Set<String> getStripProfile3() {
        return stripProfile3;
    }

    public Set<String> getStripProfile4() {
        return stripProfile4;
    }

    public Set<String> getStripProfile5() {
        return stripProfile5;
    }

    public String getReplacementBlock() {
        return replacementBlock;
    }

    public static List<String> getDefaultProfile() {
        return defaultProfile;
    }
}
