package com.amplifyframework.datastore.model;

import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.model.Model;

import java.util.Objects;

public class SubscriptionModel {
    private Class<? extends Model> modelCls;
    private SubscriptionType subscriptionType;

    public SubscriptionModel(Class<? extends Model> modelCls, SubscriptionType subscriptionType) {
        this.modelCls = modelCls;
        this.subscriptionType = subscriptionType;
    }

    public Class<? extends Model> getModelCls() {
        return modelCls;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    @Override
    public String toString() {
        return "SubscriptionModel{" +
            "modelCls=" + modelCls +
            ", subscriptionType=" + subscriptionType +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionModel that = (SubscriptionModel) o;
        return Objects.equals(modelCls, that.modelCls) &&
            subscriptionType == that.subscriptionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelCls, subscriptionType);
    }
}
