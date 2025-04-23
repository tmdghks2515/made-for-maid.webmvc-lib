package io.madeformaid.webmvc.jpa.idGenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.Base64;
import java.util.EnumSet;
import java.util.UUID;

public class ShortIdGenerator implements BeforeExecutionGenerator {

    private static byte[] toByteArray(UUID uuid) {
        byte[] bytes = new byte[16];
        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte)(most >>> (8 * (7 - i)));
            bytes[8 + i] = (byte)(least >>> (8 * (7 - i)));
        }
        return bytes;
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        UUID uuid = UUID.randomUUID();
        byte[] bytes = toByteArray(uuid);
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes)
                .substring(0, 11);
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
