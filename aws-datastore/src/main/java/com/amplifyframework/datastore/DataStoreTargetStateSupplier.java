package com.amplifyframework.datastore;

import com.amplifyframework.core.Consumer;
import com.amplifyframework.datastore.syncengine.Orchestrator;

import java.util.function.Supplier;

/**
 * Just a ~type-alias for a consumer of DataStoreException.
 */
public interface DataStoreTargetStateSupplier extends Supplier<Orchestrator.State> {
}
