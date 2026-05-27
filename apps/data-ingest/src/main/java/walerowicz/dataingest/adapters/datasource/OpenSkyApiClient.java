package walerowicz.dataingest.adapters.datasource;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import walerowicz.dataingest.domain.StateVector;

import java.util.Collection;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class OpenSkyApiClient implements DataSource {
    private final RestClient client;

    @Retry(name="opensky")
    public Collection<StateVector> getCurrentStateVectors() {
        final OpenSkyPayload payload = client.get()
                .retrieve()
                .toEntity(OpenSkyPayload.class)
                .getBody();
        return OpenSkyPayloadMapper.map(payload);

    }

}
