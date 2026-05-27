package walerowicz.dataingest.ports.datasink;

import org.springframework.stereotype.Service;
import walerowicz.dataingest.domain.StateVector;

@Service
public class KafkaSink implements DataSink {
    @Override
    public void sendState(StateVector stateVector) {
        // TODO: Implement kafka push
    }
}
