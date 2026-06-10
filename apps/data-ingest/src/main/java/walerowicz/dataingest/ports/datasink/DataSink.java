package walerowicz.dataingest.ports.datasink;

import walerowicz.eits.ingest.v1.StateVector;

public interface DataSink {

    void sendState(final StateVector stateVector);
}
