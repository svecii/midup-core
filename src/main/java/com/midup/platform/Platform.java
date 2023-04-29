package com.midup.platform;

import com.midup.maintenance.Maintenance;
import lombok.Getter;
import lombok.Setter;

public class Platform {

    @Getter @Setter
    private static String motd;

    @Getter
    private static Maintenance maintenance = new Maintenance();

}
