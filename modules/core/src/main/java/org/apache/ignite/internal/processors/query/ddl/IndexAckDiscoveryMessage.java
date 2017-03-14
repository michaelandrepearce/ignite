/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.query.ddl;

import org.apache.ignite.internal.managers.discovery.DiscoveryCustomMessage;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * {@code ACK} message which triggers local index create/drop.
 */
public class IndexAckDiscoveryMessage extends IndexAbstractDiscoveryMessage {
    /** */
    private static final long serialVersionUID = 0L;

    /** Node reported an error. */
    private UUID errNodeId;

    /** Error message. */
    private String errMsg;

    /**
     * Constructor.
     *
     * @param op Original operation.
     */
    public IndexAckDiscoveryMessage(AbstractIndexOperation op) {
        super(op);
    }

    /** {@inheritDoc} */
    @Nullable @Override public DiscoveryCustomMessage ackMessage() {
        return null;
    }

    /** {@inheritDoc} */
    @Override public boolean isMutable() {
        return false;
    }

    /**
     * @return {@code True} if error was reported during init.
     */
    public boolean hasError() {
        return errNodeId != null;
    }

    /**
     * @return ID of the node reported an error (if any).
     */
    @Nullable public UUID errorNodeId() {
        return errNodeId;
    }

    /**
     * @return Error message (if any).
     */
    @Nullable public String errorMessage() {
        return errMsg;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(IndexAckDiscoveryMessage.class, this);
    }
}
