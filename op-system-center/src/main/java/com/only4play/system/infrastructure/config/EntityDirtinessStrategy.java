package com.only4play.system.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CustomEntityDirtinessStrategy;
import org.hibernate.Session;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class EntityDirtinessStrategy implements CustomEntityDirtinessStrategy {

    @Override
    public boolean canDirtyCheck(Object entity, EntityPersister persister, Session session) {
        return entity instanceof DirtyAware;
    }

    @Override
    public boolean isDirty(Object entity, EntityPersister persister, Session session) {
        return !cast(entity).getDirtyProperties().isEmpty();
    }

    @Override
    public void resetDirty(Object entity, EntityPersister persister, Session session) {
        cast(entity).clearDirtyProperties();
    }

    @Override
    public void findDirty(Object entity, EntityPersister persister, Session session, DirtyCheckContext dirtyCheckContext) {
        final DirtyAware dirtyAware = cast(entity);
        dirtyCheckContext.doDirtyChecking(
            new AttributeChecker() {
                @Override
                public boolean isDirty(AttributeInformation attributeInformation) {
                    String propertyName = attributeInformation.getName();
                    boolean dirty = dirtyAware.getDirtyProperties().contains( propertyName );
                    if (dirty) {
                        log.info("The {} property is dirty", propertyName);
                    }
                    return dirty;
                }
            }
        );
    }

    private DirtyAware cast(Object entity) {
        return DirtyAware.class.cast(entity);
    }
}
