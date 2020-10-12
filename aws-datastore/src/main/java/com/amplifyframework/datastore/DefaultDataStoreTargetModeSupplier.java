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

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.syncengine.Orchestrator;
import com.amplifyframework.logging.Logger;

/**
 * A default implementation of the {@link DataStoreErrorHandler} which just logs the error
 * and moves on.
 */
public final class DefaultDataStoreTargetModeSupplier implements DataStoreTargetModeSupplier {

    private DefaultDataStoreTargetModeSupplier() {}

    @NonNull
    public static DefaultDataStoreTargetModeSupplier instance() {
        return new DefaultDataStoreTargetModeSupplier();
    }

    @Override
    public Orchestrator.Mode get() {
        return Orchestrator.Mode.SYNC_VIA_API;
    }
}
