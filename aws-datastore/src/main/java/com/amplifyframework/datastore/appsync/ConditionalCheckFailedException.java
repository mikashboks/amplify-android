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

package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.syncengine.PendingMutation;
import com.amplifyframework.util.Empty;

import java.util.List;
import java.util.Objects;

/**
 * It is possible for the client and server to have two copies of one logical entity,
 * each containing different contents, but each believing that it is the latest
 * copy of the data. When the client tries to mutate the server's data, AppSync's response
 * may contain a GraphQLError with errorType as ConditionalCheckFailedException.
 *
 * This ConditionalCheckFailedExceptionError class models that response data.
 *
 * @see <a href="https://docs.aws.amazon.com/appsync/latest/devguide/conflict-detection-and-sync.html#errors">
 *     AppSync Conflict Detection & Sync Errors
 *     </a>
 */
public final class ConditionalCheckFailedException extends AmplifyException {

    private static final long serialVersionUID = 1L;

    public ConditionalCheckFailedException(@NonNull String message, @NonNull String recoverySuggestion) {
        super(message, recoverySuggestion);
    }

    /**
     * Iterates over a list of GraphQL.Error, checking to see if any of them
     * contain 'ConditionalCheckFailedException' as the errorType.
     * @param errors A list of GraphQL errors, as received in response to a mutation
     * @return A model of the first ConditionalCheckFailedException error found in the GraphQL error list.
     *         If there is no ConditionalCheckFailedExceptionError in the list, returns null.
     */
    @Nullable
    public static void check(@Nullable List<GraphQLResponse.Error> errors) throws ConditionalCheckFailedException {
        if (Empty.check(errors)) {
            return;
        }

        for (GraphQLResponse.Error error : errors) {
            if (Empty.check(error.getExtensions())) {
                continue;
            }

            AppSyncExtensions appSyncExtensions = new AppSyncExtensions(error.getExtensions());
            if (AppSyncExtensions.AppSyncErrorType.CONDITIONAL_CHECK_FAILED_EXCEPTION.equals(appSyncExtensions.getErrorType())) {
                throw new ConditionalCheckFailedException("CONDITIONAL_CHECK_FAILED_EXCEPTION", "CONDITIONAL_CHECK_FAILED_EXCEPTION");
            }
        }

        return;
    }
}
