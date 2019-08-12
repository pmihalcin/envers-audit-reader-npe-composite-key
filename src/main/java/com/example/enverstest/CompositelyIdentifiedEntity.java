package com.example.enverstest;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CompositelyIdentifiedEntity<PK extends Serializable> extends AuditedEntity {

    protected PK pk;

    @EmbeddedId
    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositelyIdentifiedEntity)) return false;
        CompositelyIdentifiedEntity<?> that = (CompositelyIdentifiedEntity<?>) o;
        return Objects.equals(getPk(), that.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPk());
    }

    @Override
    public String toString() {
        return "CompositelyIdentifiedEntity{" +
                "pk=" + pk +
                '}';
    }
}

