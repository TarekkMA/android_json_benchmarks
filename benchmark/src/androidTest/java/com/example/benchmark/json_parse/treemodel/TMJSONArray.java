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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.identityHashCode;

public class TMJSONArray {

    private final ArrayNode mNode;

    public TMJSONArray(ArrayNode node) {
        mNode = node;
    }

    public TMJSONArray() {
        this(AnkiSerialization.getObjectMapper().createArrayNode());
    }

    public TMJSONArray(TMJSONArray copyFrom) {
        mNode = copyFrom.mNode.deepCopy();
    }

    public TMJSONArray(String source) {
        try {
            mNode = (ArrayNode) AnkiSerialization.getObjectMapper().readTree(source);
        } catch (Exception e) {
            throw new TMJSONException(e);
        }
    }


    public TMJSONArray(Object array) {
        this();
        if (array.getClass().isArray()) {
            int length = Array.getLength(array);
            for (int i = 0; i < length; i += 1) {
                this.put(Array.get(array, i));
            }
        } else {
            throw new TMJSONException(
                    "JSONArray initial value should be a string or collection or array.");
        }
    }

    public TMJSONArray(Collection<?> copyFrom) {
        this();
        if (copyFrom != null) {
            for (Object o : copyFrom) {
                put(o);
            }
        }
    }

    public TMJSONArray put(double value) {
        mNode.add(value);
        return this;
    }


    public TMJSONArray put(String value) {
        mNode.add(value);
        return this;
    }

    public TMJSONArray put(TMJSONArray value) {
        mNode.add(value.getJsonNode());
        return this;
    }

    public TMJSONArray put(TMJSONObject value) {
        mNode.add(value.getJsonNode());
        return this;
    }

    private JsonNode objectToJsonNode(Object value) {
        if (value == null) {
            return TMJSONObject.NULL;
        } else if (value instanceof String) {
            return TextNode.valueOf((String) value);
        } else if (value instanceof Double) {
            return DoubleNode.valueOf((Double) value);
        } else if (value instanceof Integer) {
            return IntNode.valueOf((Integer) value);
        } else if (value instanceof Boolean) {
            return BooleanNode.valueOf((Boolean) value);
        } else if (value instanceof Long) {
            return LongNode.valueOf((Long) value);
        } else if (value instanceof JsonNode) {
            return (JsonNode) value;
        } else if (value instanceof TMJSONObject) {
            return  ((TMJSONObject) value).getJsonNode();
        } else if (value instanceof TMJSONArray) {
            return  ((TMJSONArray) value).getJsonNode();
        } else {
            throw new TMJSONException("Unsupported type " + value.getClass().getName());
        }
    }

    public TMJSONArray put(int index, boolean value) {
        put(index, objectToJsonNode(value));
        return this;
    }

    public TMJSONArray put(int index, double value) {
        put(index, objectToJsonNode(value));
        return this;
    }

    public TMJSONArray put(int index, int value) {
        put(index, objectToJsonNode(value));
        return this;
    }

    public TMJSONArray put(int index, long value) {
        put(index, objectToJsonNode(value));
        return this;
    }

    public TMJSONArray put(Object value) {
        mNode.add(objectToJsonNode(value));
        return this;
    }

    public TMJSONArray put(int index, Object value) {
        JsonNode node = objectToJsonNode(value);
        while (length() <= index) {
            put(TMJSONObject.NULL);
        }
        mNode.set(index, node);
        return this;
    }


    protected void throwIfInvalidIndex(int index) {
        if ((index < 0) || (index >= length())) {
            throw new TMJSONException("Index:" + index + " is out of bounds");
        }
    }

    public boolean getBoolean(int index) {
        throwIfInvalidIndex(index);
        return mNode.get(index).booleanValue();
    }

    public double getDouble(int index) {
        throwIfInvalidIndex(index);
        return mNode.get(index).doubleValue();
    }

    public int getInt(int index) {
        throwIfInvalidIndex(index);
        return mNode.get(index).intValue();
    }

    public long getLong(int index) {
        throwIfInvalidIndex(index);
        return mNode.get(index).longValue();
    }

    public String getString(int index) {
        throwIfInvalidIndex(index);
        return mNode.get(index).asText();
    }

    public TMJSONArray getJSONArray(int pos) {
        throwIfInvalidIndex(pos);
        ArrayNode node = (ArrayNode) mNode.get(pos);
        return new TMJSONArray(node);
    }

    public TMJSONObject getJSONObject(int pos) {
        throwIfInvalidIndex(pos);
        ObjectNode node = (ObjectNode) mNode.get(pos);
        return new TMJSONObject(node);
    }

    public ArrayNode getJsonNode() {
        return mNode;
    }

    public TMJSONArray deepClone() {
        ArrayNode node = mNode.deepCopy();
        return new TMJSONArray(node);
    }

    public Iterable<TMJSONArray> jsonArrayIterable() {
        return this::jsonArrayIterator;
    }
    public Iterator<TMJSONArray> jsonArrayIterator() {
        return new Iterator<TMJSONArray>() {
            private int mIndex = 0;
            @Override
            public boolean hasNext() {
                return mIndex < length();
            }


            @Override
            public TMJSONArray next() {
                TMJSONArray array = getJSONArray(mIndex);
                mIndex++;
                return array;
            }
        };
    }

    public int length() {
        return mNode.size();
    }

    public Iterable<TMJSONObject> jsonObjectIterable() {
        return this::jsonObjectIterator;
    }
    public Iterator<TMJSONObject> jsonObjectIterator() {
        return new Iterator<TMJSONObject>() {
            private int mIndex = 0;
            @Override
            public boolean hasNext() {
                return mIndex < length();
            }


            @Override
            public TMJSONObject next() {
                TMJSONObject object = getJSONObject(mIndex);
                mIndex++;
                return object;
            }
        };
    }

    public Iterable<String> stringIterable() {
        return this::stringIterator;
    }
    public Iterator<String> stringIterator() {
        return new Iterator<String>() {
            private int mIndex = 0;
            @Override
            public boolean hasNext() {
                return mIndex < length();
            }


            @Override
            public String next() {
                String string = getString(mIndex);
                mIndex++;
                return string;
            }
        };
    }

    public Iterable<Long> longIterable() {
        return this::longIterator;
    }
    public Iterator<Long> longIterator() {
        return new Iterator<Long>() {
            private int mIndex = 0;
            @Override
            public boolean hasNext() {
                return mIndex < length();
            }


            @Override
            public Long next() {
                Long long_ = getLong(mIndex);
                mIndex++;
                return long_;
            }
        };
    }

    public List<TMJSONObject> toJSONObjectList() {
        List<TMJSONObject> l = new ArrayList<>(length());
        for (TMJSONObject object : jsonObjectIterable()) {
            l.add(object);
        }
        return l;
    }

    public List<Long> toLongList() {
        List<Long> l = new ArrayList<>(length());
        for (Long object : longIterable()) {
            l.add(object);
        }
        return l;
    }

    public List<String> toStringList() {
        List<String> l = new ArrayList<>(length());
        for (String object : stringIterable()) {
            l.add(object);
        }
        return l;
    }


    /**
     * @return Given an array of objects, return the array of the value with `key`, assuming that they are String.
     * E.g. templates, fields are a JSONArray whose objects have name
     */
    public List<String> toStringList(String key) {
        List<String> l = new ArrayList<>(length());
        for (TMJSONObject object : jsonObjectIterable()) {
            l.add(object.getString(key));
        }
        return l;
    }


    public boolean isNull(int i) {
        return mNode.get(i).isNull();
    }


    public String optString(int index) {
        if (index < 0 || index >= length()) {
            return "";
        }
        return getString(index);
    }


    public Object get(int index) {
        JsonNode node = mNode.get(index);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TMJSONArray other = (TMJSONArray) o;
        // intentional reference comparison
        return mNode == other.getJsonNode();
    }


    @Override
    public int hashCode() {
        return identityHashCode(mNode);
    }
}
