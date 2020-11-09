package io.swagger.v2.async.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion implements Versioned {
    public static final Version VERSION = VersionUtil.parseVersion(
            "0.5.1-SNAPSHOT", "io.swagger", "swagger-async");

    @Override
    public Version version() {
        return VERSION;
    }
}
