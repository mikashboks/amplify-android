/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amplifyframework.datastore;

import androidx.annotation.NonNull;

import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.datastore.model.SubscriptionModel;

import java.util.HashSet;
import java.util.Set;


public final class DefaultDataStoreSubscriptionsSupplier implements DataStoreSubscriptionsSupplier {

    @NonNull
    public static DefaultDataStoreSubscriptionsSupplier instance() {
        return new DefaultDataStoreSubscriptionsSupplier();
    }

    @Override
    public Set<SubscriptionModel>  getSubscriptions(@NonNull ModelProvider modelProvider) {
        Set<SubscriptionModel> subscriptions = new HashSet<>();
        for (Class<? extends Model> clazz : modelProvider.models()) {
            for (SubscriptionType subscriptionType : SubscriptionType.values()) {
                subscriptions.add(new SubscriptionModel(clazz, subscriptionType));
            }
        }
        return subscriptions;
    }
}
