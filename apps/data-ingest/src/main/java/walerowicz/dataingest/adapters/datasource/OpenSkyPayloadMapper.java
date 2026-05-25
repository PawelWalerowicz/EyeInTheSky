package walerowicz.dataingest.adapters.datasource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.util.ArrayUtils;
import walerowicz.dataingest.domain.StateVector;

import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OpenSkyPayloadMapper {
    static Collection<StateVector> map(OpenSkyPayload payload) {
        if(Objects.isNull(payload)) {
            throw new IllegalArgumentException("Fetched payload is null");
        }
        return payload.states().stream()
                .map(OpenSkyPayloadMapper::map)
                .toList();
    }

    // Source: https://openskynetwork.github.io/opensky-api/rest.html#all-state-vectors
    private static StateVector map(Object[] state) {
        try {
            return StateVector.builder()
                    .icaoAddress(cast(state[0], String.class))
                    .callsign(cast(state[1], String.class))
                    .originCountry(cast(state[2], String.class))
                    .timePosition(castToNumber(state[3], Long.class))
                    .lastContact(castToNumber(state[4], Long.class))
                    .longitude(castToNumber(state[5], Double.class))
                    .latitude(castToNumber(state[6], Double.class))
                    .barometricAltitude(castToNumber(state[7], Double.class))
                    .onGround(cast(state[8], Boolean.class))
                    .velocity(castToNumber(state[9], Double.class))
                    .trueTrack(castToNumber(state[10], Double.class))
                    .verticalRate(castToNumber(state[11], Double.class))
                    // state[12] - sensors array omitted on purpose
                    .geometricAltitude(castToNumber(state[13], Double.class))
                    .squawk(cast(state[14], String.class))
                    .spi(cast(state[15], Boolean.class))
                    .positionSource(StateVector.PositionSource.fromCode(castToNumber(state[16], Integer.class)))
//                .category(StateVector.Category.fromCode(cast(state[17], Integer.class))) // no category found?
                    .build();
        } catch (RuntimeException e) {
            System.out.println(ArrayUtils.toUnmodifiableList(state));
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T cast(Object value, Class<T> type) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (type.isInstance(value)) {
            return (T) value;
        }
        throw new IllegalArgumentException(
                "Expected " + type.getSimpleName() +
                        " but got " + value.getClass().getSimpleName()
        );
    }

    @SuppressWarnings("unchecked")
    private static <T extends Number> T castToNumber(Object value, Class<T> type) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (!(value instanceof Number n)) {
            throw new IllegalArgumentException(
                    "Expected " + type.getSimpleName() +
                            " but got " + value.getClass().getSimpleName()
            );
        }
        if (type == Integer.class) {
            return type.cast(n.intValue());
        }
        if (type == Long.class) {
            return type.cast(n.longValue());
        }
        if (type == Double.class) {
            return type.cast(n.doubleValue());
        }
        if (type == Float.class) {
            return type.cast(n.floatValue());
        }
        throw new IllegalArgumentException("Unsupported numeric type: " + type);
    }

}
