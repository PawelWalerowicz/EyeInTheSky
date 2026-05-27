package walerowicz.dataingest.adapters.datasource;

import walerowicz.dataingest.domain.StateVector;

import java.util.Collection;

public interface DataSource {
    Collection<StateVector> getCurrentStateVectors();

}
