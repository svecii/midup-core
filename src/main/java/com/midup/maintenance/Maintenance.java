package com.midup.maintenance;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Maintenance {

    private boolean enabled;
    private String motd;

    private String kickMessage;

    private List<String> members = new ArrayList<>();

    public boolean contains(String name) {
        return members.contains(name);
    }
}
