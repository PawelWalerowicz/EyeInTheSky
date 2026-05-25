package walerowicz.dataingest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import walerowicz.dataingest.adapters.datasource.DataSource;
import walerowicz.dataingest.ports.datasink.DataSink;

@Service
@RequiredArgsConstructor
public class IngestService {
    private final DataSource dataSource;
    private final DataSink dataSink;

    public void processData() {
        dataSource.getCurrentStateVectors()
                .forEach(dataSink::sendState);


    }
}
