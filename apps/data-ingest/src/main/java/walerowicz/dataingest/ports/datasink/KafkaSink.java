package walerowicz.dataingest.ports.datasink;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import walerowicz.eits.ingest.v1.StateVector;

@Service
@RequiredArgsConstructor
public class KafkaSink implements DataSink {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void sendState(final StateVector stateVector) {
        kafkaTemplate.send("current-flights-data",
                stateVector.getIcaoAddress(),
                stateVector.toByteArray()
                );
    }
}
