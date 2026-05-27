package walerowicz.dataingest.adapters.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Optional;

@Configuration
@EnableConfigurationProperties(OpenSkyProperties.class)
@RequiredArgsConstructor
public class DataSourceConfiguration {
    private final OpenSkyProperties openSkyProperties;

    @Bean
    public DataSource dataSource(OAuth2AuthorizedClientManager manager) {
        final var restClient = RestClient.builder()
                .baseUrl(openSkyProperties.endpoint())
                .requestInterceptor(buildOAuthTokenInterceptor(manager))
                .requestFactory(setupRequestFactory())
                .build();
        return new OpenSkyApiClient(restClient);
    }

    private ClientHttpRequestInterceptor buildOAuthTokenInterceptor(OAuth2AuthorizedClientManager manager) {
        return (request, body, execution) -> {
            final var authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("opensky")
                    .principal("data-ingest")
                    .build();

            final String token = Optional.ofNullable(manager.authorize(authorizeRequest))
                    .map(OAuth2AuthorizedClient::getAccessToken)
                    .map(OAuth2AccessToken::getTokenValue)
                    .orElseThrow(() -> new IllegalStateException("OpenSky OAuth2 Authorization failed"));

            request.getHeaders().setBearerAuth(token);

            return execution.execute(request, body);
        };
    }

    ClientHttpRequestFactory setupRequestFactory() {
        var httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(openSkyProperties.connectionTimeoutMs()))
                .build();

        var requestFactory = new JdkClientHttpRequestFactory(httpClient);

        requestFactory.setReadTimeout(Duration.ofMillis(openSkyProperties.readTimeoutMs()));

        return requestFactory;
    }
}
