package walerowicz.dataingest.ports.datasink;

import walerowicz.dataingest.domain.StateVector;

public interface DataSink {

    void sendState(final StateVector stateVector);
}
