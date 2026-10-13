package com.only4play.system.infrastructure.config;

import java.util.Set;

public interface DirtyAware {

    Set<String> getDirtyProperties();

    void clearDirtyProperties();
}
