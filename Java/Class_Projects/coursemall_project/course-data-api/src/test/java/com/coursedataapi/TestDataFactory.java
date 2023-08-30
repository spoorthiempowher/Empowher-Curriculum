package com.coursedataapi;

import java.util.List;

public abstract class TestDataFactory<R, M, D> {

    public abstract M getModelObject();

    public abstract List<M> getModelObjects(Integer count);

    public abstract R getResourceObject();

    public abstract List<R> getResourceObjects(Integer count);

    public abstract D getRepositoryObject();

    public abstract List<D> getRepositoryObjects(Integer count);

}
