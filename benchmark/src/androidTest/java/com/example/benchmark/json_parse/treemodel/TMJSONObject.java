/*
 *  Copyright (c) 2020 Arthur Milchior <arthur@milchior.fr>
 *
 *  This file is free software: you may copy, redistribute and/or modify it
 *  under the terms of the GNU General Public License as published by the
 *  Free Software Foundation, either version 3 of the License, or (at your
 *  option) any later version.
 *
 *  This file is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  This file incorporates work covered by the following copyright and
 *  permission notice:
 *
 *    Copyright (c) 2002 JSON.org
 *
 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *
 *    The above copyright notice and this permission notice shall be included in all
 *    copies or substantial portions of the Software.
 *
 *    The Software shall be used for Good, not Evil.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *    SOFTWARE.
 */

package com.example.benchmark.json_parse.treemodel;

/*
  Each method similar to the methods in JSONObjects. Name changed to add a ,
  and it throws JSONException instead of JSONException.
  Furthermore, it returns JSONObject and JSONArray

 */

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.System.identityHashCode;

public class TMJSONObject implements Iterable<String> {

    public static final JsonNode NULL = NullNode.getInstance();

    private final ObjectNode mNode;

    @JsonCreator
    public TMJSONObject(ObjectNode node) {
        mNode = node;
    }


    public TMJSONObject() {
        this(AnkiSerialization.getObjectMapper().createObjectNode());
    }

    public TMJSONObject(String source) {
        try {
            mNode = (ObjectNode) AnkiSerialization.getObjectMapper().readTree(source);
        } catch (Exception e) {
            throw new TMJSONException(e);
        }
    }

    public TMJSONObject(TMJSONObject copyFrom) {
        mNode = copyFrom.mNode.deepCopy();
    }


    /**
        Iters on the keys. (Similar to iteration in Python's
        dictionnary.
    */
    @NonNull
    public Iterator<String> iterator() {
        return keys();
    }

    public static TMJSONObject objectToObject(TMJSONObject obj) {
        return obj.deepClone();
    }

    public TMJSONObject put(String name, boolean value) {
        mNode.put(name, value);
        return this;
    }

    public TMJSONObject put(String name, double value) {
        mNode.put(name, value);
        return this;
    }

    public TMJSONObject put(String name, int value) {
        mNode.put(name, value);
        return this;
    }

    public TMJSONObject put(String name, long value) {
        mNode.put(name, value);
        return this;
    }


    public TMJSONObject put(String name, Object value) {
        if (value == null) {
            mNode.set(name, NULL);
        } else if (value instanceof String) {
            mNode.put(name, (String) value);
        } else if (value instanceof Double) {
            mNode.put(name, (Double) value);
        } else if (value instanceof Integer) {
            mNode.put(name, (Integer) value);
        } else if (value instanceof Boolean) {
            mNode.put(name, (Boolean) value);
        } else if (value instanceof Long) {
            mNode.put(name, (Long) value);
        } else if (value instanceof JsonNode) {
            mNode.set(name, (JsonNode) value);
        } else if (value instanceof TMJSONObject) {
            mNode.set(name, ((TMJSONObject) value).getJsonNode());
        } else if (value instanceof TMJSONArray) {
            mNode.set(name, ((TMJSONArray) value).getJsonNode());
        } else {
            throw new TMJSONException("Unsupported type " + value.getClass().getName());
        }
        return this;
    }

    public TMJSONObject putOpt(@Nullable String name, Object value) {
        if (name != null && value != null) {
            put(name, value);
        }
        return this;
    }

    public TMJSONObject put(String name, CharSequence value) {
        mNode.put(name, value.toString());
        return this;
    }


    public TMJSONObject put(String name, TMJSONArray value) {
        mNode.set(name, value.getJsonNode());
        return this;
    }


    public TMJSONObject put(String name, TMJSONObject value) {
        mNode.set(name, value.getJsonNode());
        return this;
    }


    public TMJSONObject put(String name, JsonNode node) {
        mNode.set(name, node);
        return this;
    }


    @CheckResult
    public Object get(String name) {
        if (!has(name)) {
            throw new TMJSONException("Key:" + name + " does not exist");
        }
        JsonNode node = mNode.get(name);
        if (node.isArray()) {
            return new TMJSONArray((ArrayNode) node);
        } else if (node.isObject()) {
            return new TMJSONObject((ObjectNode) node);
        } else if (node.isNull()) {
            return TMJSONObject.NULL;
        } else if (node.isTextual()) {
            return node.textValue();
        } else if (node.isBoolean()) {
            return node.booleanValue();
        } else if (node.isInt()) {
            return node.intValue();
        } else if (node.isLong()) {
            return node.longValue();
        } else if (node.isDouble()) {
            return node.isDouble();
        }
        return node;
    }

    @CheckResult
    public boolean getBoolean(String name) {
        return mNode.get(name).asBoolean();
    }


    @CheckResult
    public double getDouble(String name) {
        return mNode.get(name).asDouble();
    }


    @CheckResult
    public int getInt(String name) {
        return mNode.get(name).asInt();
    }


    @CheckResult
    public long getLong(String name) {
        return mNode.get(name).asLong();
    }


    @CheckResult
    public String getString(String name) {
        return mNode.get(name).asText();
    }


    @CheckResult
    public TMJSONArray getJSONArray(String name) {
        return new TMJSONArray((ArrayNode) mNode.get(name));
    }


    @CheckResult
    public TMJSONObject getJSONObject(String name) {
        return new TMJSONObject((ObjectNode) mNode.get(name));
    }


    @CheckResult
    public int length() {
        return mNode.size();
    }


    @CheckResult
    public TMJSONArray names() {
        Iterator<String> stringIterator = mNode.fieldNames();
        List<String> names = new ArrayList<>(length());
        while (stringIterator.hasNext()) {
            names.add(stringIterator.next());
        }
        return new TMJSONArray(names);
    }


    @CheckResult
    public Iterator<String> keys() {
        return mNode.fieldNames();
    }


    @CheckResult
    public TMJSONArray optJSONArray(String name) {
        JsonNode jsonNode = mNode.get(name);
        if (jsonNode != null && jsonNode.isArray()) {
            return new TMJSONArray((ArrayNode) jsonNode);
        }
        return null;
    }


    @CheckResult
    public TMJSONObject optJSONObject(String name) {
        JsonNode jsonNode = mNode.get(name);
        if (jsonNode != null && jsonNode.isObject()) {
            return new TMJSONObject((ObjectNode) jsonNode);
        }
        return null;
    }


    @CheckResult
    public TMJSONObject deepClone() {
        ObjectNode clone = mNode.deepCopy();
        return new TMJSONObject(clone);
    }

    /** deep clone this into clone.

        Given a subtype `T` of JSONObject, and a JSONObject `j`, we could do
        ```
        T t = new T();
        j.deepClonedInto(t);
        ```
        in order to obtain a deep clone of `j` of type ```T```. */
    protected <T extends TMJSONObject> T deepClonedInto(T clone) {
        for (String key: this) {
            if (get(key) instanceof TMJSONObject) {
                clone.put(key, getJSONObject(key).deepClone());
            }
            else if (get(key) instanceof TMJSONArray) {
                clone.put(key, getJSONArray(key).deepClone());
            } else if (get(key) instanceof JsonNode) {
                clone.put(key, new TMJSONObject(((JsonNode) get(key)).deepCopy()));
            } else {
                clone.put(key, get(key));
            }
        }
        return clone;
    }

    @JsonValue
    public ObjectNode getJsonNode() {
        return mNode;
    }


    public static TMJSONObject fromMap(Map<String, Boolean> map) {
        TMJSONObject ret = new TMJSONObject();
        for (Map.Entry<String, Boolean> i : map.entrySet()) {
            ret.put(i.getKey(), i.getValue());
        }
        return ret;
    }


    public boolean optBoolean(String name, boolean defaultValue) {
        return has(name) ? mNode.get(name).asBoolean(defaultValue) : defaultValue;
    }


    public int optInt(String name, int defaultValue) {
        return has(name) ? mNode.get(name).asInt(defaultValue) : defaultValue;
    }


    public long optLong(String name, long defaultValue) {
        return has(name) ? mNode.get(name).asLong(defaultValue) : defaultValue;
    }

    public long optLong(String name) {
        return has(name) ? mNode.get(name).asLong(0) : 0;
    }

    public double optDouble(String name) {
        return has(name) ? getDouble(name) : 0;
    }


    public boolean has(String name) {
        return mNode.has(name);
    }


    public void remove(String name) {
        mNode.remove(name);
    }


    public String optString(String name, String defaultValue) {
        return has(name) ? mNode.get(name).asText(defaultValue) : defaultValue;
    }


    public String optString(String name) {
        return has(name) ? getString(name) : "";
    }


    public boolean isNull(String name) {
        return mNode.get(name).isNull();
    }


    public boolean optBoolean(String name) {
        return has(name) ? getBoolean(name) : false;
    }


    public double optDouble(String name, double defaultValue) {
        return has(name) ? mNode.get(name).asDouble(defaultValue) : defaultValue;
    }


    @Override
    public String toString() {
        return mNode.toString();
    }


    public String toString(int indentation/*not used*/) {
        return mNode.toPrettyString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TMJSONObject other = (TMJSONObject) o;
        // intentional reference comparison
        return mNode == other.getJsonNode();
    }


    @Override
    public int hashCode() {
        return identityHashCode(mNode);
    }
}
