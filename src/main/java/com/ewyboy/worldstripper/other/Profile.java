package com.ewyboy.worldstripper.other;

import com.ewyboy.worldstripper.config.Config;
import com.ewyboy.worldstripper.config.ConfigHandler;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    public static Map<Integer, Object[]> profileMapper = new HashMap<>();

    static {
        Config config = ConfigHandler.getConfig();
        profileMapper.put(1, config.getStripProfile1().toArray());
        profileMapper.put(2, config.getStripProfile2().toArray());
        profileMapper.put(3, config.getStripProfile3().toArray());
        profileMapper.put(4, config.getStripProfile4().toArray());
        profileMapper.put(5, config.getStripProfile5().toArray());
    }

}
