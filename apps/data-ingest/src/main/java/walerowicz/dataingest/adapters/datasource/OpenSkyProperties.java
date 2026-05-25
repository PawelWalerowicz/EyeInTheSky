package walerowicz.dataingest.adapters.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opensky")
record OpenSkyProperties(
        String endpoint,
        int connectionTimeoutMs,
        int readTimeoutMs
) {
}
