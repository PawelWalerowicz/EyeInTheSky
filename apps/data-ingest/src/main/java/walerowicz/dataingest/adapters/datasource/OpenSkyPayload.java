package walerowicz.dataingest.adapters.datasource;

import java.util.List;

record OpenSkyPayload(
        long time,
        List<Object[]> states
) {
}
