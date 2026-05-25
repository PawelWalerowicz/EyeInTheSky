package walerowicz.dataingest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import walerowicz.dataingest.IngestService;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class InitController {
    private final IngestService ingestService;

    @GetMapping("/initialize")
    public void initializeDataIngest() {
        ingestService.processData();
    }
}
