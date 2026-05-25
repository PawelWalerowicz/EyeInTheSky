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
        PositionSource positionSource,
        Category category
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

    @AllArgsConstructor
    public enum Category {
        NO_INFO(0, "No information at all"),
        NO_EMITTER_INFO(1, "No ADS-B Emitter Category Information"),
        LIGHT(2, "Light (< 15500 lbs)"),
        SMALL(3, "Small (15500 to 75000 lbs)"),
        LARGE(4, "Large (75000 to 300000 lbs)"),
        VORTEX_LARGE(5, "High Vortex Large (aircraft such as B-757)"),
        HEAVY(6, "Heavy (> 300000 lbs)"),
        HIGH_PERFORMANCE(7, "High Performance (> 5g acceleration and 400 kts)"),
        ROTORCRAFT(8, "Rotorcraft"),
        GLIDER(9, "Glider / sailplane"),
        LTA(10, "Lighter-than-air"),
        PARACHUTIST(11, "Parachutist / Skydiver"),
        ULTRALIGHT(12, "Ultralight / hang-glider / paraglider"),
        RESERVED(13, "Reserved"),
        UAV(14, "Unmanned Aerial Vehicle"),
        SPACE(15, "Space / Trans-atmospheric vehicle"),
        SURFACE_EMERGENCY(16, "Surface Vehicle – Emergency Vehicle"),
        SURFACE_SERVICE(17, "Surface Vehicle – Service Vehicle"),
        OBSTACLE_POINT(18, "Point Obstacle (includes tethered balloons)"),
        OBSTACLE_CLUSTER(19, "Cluster Obstacle"),
        OBSTACLE_LINE(20, "Line Obstacle");

        final int code;
        final String description;

        public static Category fromCode(int code) {
            return values()[code];
        }
    }

}
