package walerowicz.dataingest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record StateVector(
        String icaoAddress,
        String callsign,
        String originCountry,
        Long timePosition,
        Long lastContact,
        Double longitude,
        Double latitude,
        Double barometricAltitude,
        Double geometricAltitude,
        Boolean onGround,
        Double velocity,
        Double trueTrack,
        Double verticalRate,
        String squawk,
        Boolean spi,
        PositionSource positionSource
) {

    @AllArgsConstructor
    public enum PositionSource {
        ADS_B(0),
        ASTERIX(1),
        MLAT(2),
        FLARM(3);

        final int code;

        public static PositionSource fromCode(int code) {
            return values()[code];
        }
    }
}
