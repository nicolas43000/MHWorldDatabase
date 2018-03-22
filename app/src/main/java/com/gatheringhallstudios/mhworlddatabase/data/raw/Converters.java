package com.gatheringhallstudios.mhworlddatabase.data.raw;

import android.arch.persistence.room.TypeConverter;

import com.gatheringhallstudios.mhworlddatabase.data.ArmorType;
import com.gatheringhallstudios.mhworlddatabase.data.MonsterSize;
import com.gatheringhallstudios.mhworlddatabase.data.WeaponType;
import com.google.common.collect.EnumHashBiMap;

/**
 * Type conversions for things like enumerations.
 * These are registered to the database class via an annotation.
 * Created by Carlos on 3/6/2018.
 */

public class Converters {
    private static EnumHashBiMap<MonsterSize, String> monsterSizeMap;
    private static EnumHashBiMap<ArmorType, String> armorMap;
    private static EnumHashBiMap<WeaponType, String> weaponMap;

    static {
        monsterSizeMap = EnumHashBiMap.create(MonsterSize.class);
        armorMap = EnumHashBiMap.create(ArmorType.class);
        weaponMap = EnumHashBiMap.create(WeaponType.class);

        monsterSizeMap.put(MonsterSize.SMALL, "small");
        monsterSizeMap.put(MonsterSize.LARGE, "large");

        armorMap.put(ArmorType.HEAD, "head");
        armorMap.put(ArmorType.CHEST, "chest");
        armorMap.put(ArmorType.ARMS, "arms");
        armorMap.put(ArmorType.WAIST, "waist");
        armorMap.put(ArmorType.LEGS, "legs");

        weaponMap.put(WeaponType.GREAT_SWORD, "great-sword");
        weaponMap.put(WeaponType.LONG_SWORD, "long-sword");
        weaponMap.put(WeaponType.SWORD_AND_SHIELD, "sword-and-shield");
        weaponMap.put(WeaponType.DUAL_BLADES, "dual-blades");
        weaponMap.put(WeaponType.HAMMER, "hammer");
        weaponMap.put(WeaponType.HUNTING_HORN, "hunting-horn");
        weaponMap.put(WeaponType.LANCE, "lance");
        weaponMap.put(WeaponType.GUNLANCE, "gunlance");
        weaponMap.put(WeaponType.SWITCH_AXE, "switch-axe");
        weaponMap.put(WeaponType.CHARGE_BLADE, "charge-blade");
        weaponMap.put(WeaponType.INSECT_GLAIVE, "insect-glaive");
        weaponMap.put(WeaponType.BOW, "bow");
        weaponMap.put(WeaponType.LIGHT_BOWGUN, "light-bowgun");
        weaponMap.put(WeaponType.HEAVY_BOWGUN, "heavy-bowgun");
    }

    @TypeConverter
    public MonsterSize monsterSizefromString(String value) {
        try {
            return monsterSizeMap.inverse().get(value);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown monster size " + value);
        }
    }

    @TypeConverter
    public String fromMonsterSize(MonsterSize type) {
        return monsterSizeMap.get(type);
    }

    @TypeConverter
    public ArmorType armorTypefromString(String value) {
        try {
            return armorMap.inverse().get(value);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown armor type " + value);
        }
    }

    @TypeConverter
    public String fromArmorType(ArmorType type) {
        return armorMap.get(type);
    }

    @TypeConverter
    public WeaponType weaponTypeFromString(String value) {
        try {
            return weaponMap.inverse().get(value);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Unknown weapon type " + value);
        }
    }

    @TypeConverter
    public String fromWeaponType(WeaponType type) {
        return weaponMap.get(type);
    }
}
