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

package org.apache.ignite.platform;

import org.apache.ignite.Ignite;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.CacheEntryEventSerializableFilter;
import org.apache.ignite.resources.IgniteInstanceResource;

import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Test filter.
 */
@SuppressWarnings({"FieldCanBeLocal", "FloatingPointEquality", "MismatchedReadAndWriteOfArray", "unused",
    "SpellCheckingInspection"})
public class PlatformCacheEntryEventFilter implements CacheEntryEventSerializableFilter {
    /** Property to be set from platform. */
    private String startsWith = "-";

    /** Property to be set from platform. */
    private char charField;

    /** Property to be set from platform. */
    private byte byteField;

    /** Property to be set from platform. */
    private byte sbyteField;

    /** Property to be set from platform. */
    private short shortField;

    /** Property to be set from platform. */
    private short ushortField;

    /** Property to be set from platform. */
    private int intField;

    /** Property to be set from platform. */
    private int uintField;

    /** Property to be set from platform. */
    private long longField;

    /** Property to be set from platform. */
    private long ulongField;

    /** Property to be set from platform. */
    private float floatField;

    /** Property to be set from platform. */
    private double doubleField;

    /** Property to be set from platform. */
    private BigDecimal decimalField;

    /** Property to be set from platform. */
    private boolean boolField;

    /** Property to be set from platform. */
    private UUID guidField;

    /** Property to be set from platform. */
    private BinaryObject objField;

    /** Property to be set from platform. */
    private char[] charArr;

    /** Property to be set from platform. */
    private byte[] byteArr;

    /** Property to be set from platform. */
    private byte[] sbyteArr;

    /** Property to be set from platform. */
    private short[] shortArr;

    /** Property to be set from platform. */
    private short[] ushortArr;

    /** Property to be set from platform. */
    private int[] intArr;

    /** Property to be set from platform. */
    private int[] uintArr;

    /** Property to be set from platform. */
    private long[] longArr;

    /** Property to be set from platform. */
    private long[] ulongArr;

    /** Property to be set from platform. */
    private float[] floatArr;

    /** Property to be set from platform. */
    private double[] doubleArr;

    /** Property to be set from platform. */
    private boolean[] boolArr;

    /** Property to be set from platform. */
    private Object[] objArr;

    /** Injected instance. */
    @IgniteInstanceResource
    private Ignite ignite;

    /** {@inheritDoc} */
    @Override public boolean evaluate(CacheEntryEvent event) throws CacheEntryListenerException {
        // check injection
        assert ignite != null;

        // check fields
        assert charField == 'a';
        assert byteField == 1;
        assert sbyteField == 2;
        assert shortField == 3;
        assert ushortField == 4;
        assert intField == 5;
        assert uintField == 6;
        assert longField == 7;
        assert ulongField == 8;
        assert floatField == (float)9.99;
        assert doubleField == 10.123;
        assert "11.245".equals(decimalField.toString());
        assert boolField;
        assert guidField.equals(UUID.fromString("1c579241-509d-47c6-a1a0-87462ae31e59"));

        // check arrays
        assert charArr[0] == 'a';
        assert byteArr[0] == 1;
        assert sbyteArr[0] == 2;
        assert shortArr[0] == 3;
        assert ushortArr[0] == 4;
        assert intArr[0] == 5;
        assert uintArr[0] == 6;
        assert longArr[0] == 7;
        assert ulongArr[0] == 8;
        assert floatArr[0] == (float)9.99;
        assert doubleArr[0] == 10.123;
        assert boolArr[0];

        // check collections
        // TODO

        // check binary object
        assert objField != null && objField.field("Int") == 1 && "2".equals(objField.field("String"));
        assert objArr != null && objArr.length == 1 &&
            ((BinaryObject)objArr[0]).field("Int") == 1 && "2".equals(((BinaryObject)objArr[0]).field("String"));

        return ((String)event.getValue()).startsWith(startsWith);
    }
}