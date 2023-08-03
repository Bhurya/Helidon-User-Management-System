package com.helidon.ums;

import io.helidon.config.Config;
import io.helidon.microprofile.server.Server;

import static io.helidon.config.ConfigSources.classpath;

public class UserManagementSystemApplication {


    public static void main(String[] args) {
        Server server = startSever();
    }

    private static Server startSever() {
        return Server.builder()
               // .config(buildConfig())
              //  .basePath("application.yaml")
                .
                build().start();
    }


    private static Config buildConfig() {
        return Config.builder()
                .disableEnvironmentVariablesSource()
                .sources(
                        //classpath("mp-config.yaml").optional(),
                        // classpath("application.yaml"))
                       classpath("META-INF/microprofile-config.properties"))
                .build();
    }


}
