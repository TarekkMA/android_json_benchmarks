package com.example.benchmark.json_parse.models.primitives_model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(shape= JsonFormat.Shape.ARRAY)
public class PRequirement {
    private long mTemplateOrdinal;
    private String mType;
    private long[] mFieldsOrdinal;


    @JsonCreator // same order as in the original array
    public PRequirement(@JsonProperty("templateOrdinal") long templateOrdinal,
                        @JsonProperty("type") String type,
                        @JsonProperty("fieldsOrdinal") long[] fieldsOrdinal) {
        mTemplateOrdinal = templateOrdinal;
        mType = type;
        mFieldsOrdinal = fieldsOrdinal;
    }


    public long getTemplateOrdinal() {
        return mTemplateOrdinal;
    }


    public PRequirement setTemplateOrdinal(long templateOrdinal) {
        mTemplateOrdinal = templateOrdinal;
        return this;
    }


    public String getType() {
        return mType;
    }


    public PRequirement setType(String type) {
        mType = type;
        return this;
    }


    public long[] getFieldsOrdinal() {
        return mFieldsOrdinal;
    }


    public PRequirement setFieldsOrdinal(long[] fieldsOrdinal) {
        mFieldsOrdinal = fieldsOrdinal;
        return this;
    }
}
