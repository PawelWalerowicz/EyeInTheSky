package walerowicz.dataingest.ports.datasink;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import walerowicz.dataingest.domain.StateVector;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProtoStateVectorMapper {
    private static final NoArgGenerator uuidGenerator = Generators.timeBasedEpochGenerator();

    public static walerowicz.eits.ingest.v1.StateVector map(StateVector stateVector) {
        final walerowicz.eits.ingest.v1.StateVector.Builder builder = walerowicz.eits.ingest.v1.StateVector.newBuilder()
                .setCorrelationId(uuidGenerator.generate().toString())
                .setIcaoAddress(stateVector.icaoAddress())
                .setOriginCountry(stateVector.originCountry())
                .setLastContact(stateVector.lastContact())
                .setOnGround(stateVector.onGround())
                .setSpi(stateVector.spi());

        Optional.ofNullable(stateVector.timePosition()).ifPresent(builder::setTimePosition);
        Optional.ofNullable(stateVector.longitude()).ifPresent(builder::setLongitude);
        Optional.ofNullable(stateVector.latitude()).ifPresent(builder::setLatitude);
        Optional.ofNullable(stateVector.barometricAltitude()).ifPresent(builder::setBarometricAltitude);
        Optional.ofNullable(stateVector.geometricAltitude()).ifPresent(builder::setGeometricAltitude);
        Optional.ofNullable(stateVector.velocity()).ifPresent(builder::setVelocity);
        Optional.ofNullable(stateVector.trueTrack()).ifPresent(builder::setTrueTrack);
        Optional.ofNullable(stateVector.verticalRate()).ifPresent(builder::setVerticalRate);
        Optional.ofNullable(stateVector.squawk()).ifPresent(builder::setSquawk);
        Optional.ofNullable(stateVector.positionSource()).map(StateVector.PositionSource::name).ifPresent(builder::setPositionSource);

        return builder.build();
    }
}
