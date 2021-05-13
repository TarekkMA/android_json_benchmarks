package com.example.benchmark.json_parse.models.object_model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFormat(shape= JsonFormat.Shape.ARRAY)
public class ORequirement {
    private Long mTemplateOrdinal;
    private String mType;
    private Long[] mFieldsOrdinal;


    @JsonCreator // same order as in the original array
    public ORequirement(@JsonProperty("templateOrdinal") Long templateOrdinal,
                        @JsonProperty("type") String type,
                        @JsonProperty("fieldsOrdinal") Long[] fieldsOrdinal) {
        mTemplateOrdinal = templateOrdinal;
        mType = type;
        mFieldsOrdinal = fieldsOrdinal;
    }


    public Long getTemplateOrdinal() {
        return mTemplateOrdinal;
    }


    public ORequirement setTemplateOrdinal(Long templateOrdinal) {
        mTemplateOrdinal = templateOrdinal;
        return this;
    }


    public String getType() {
        return mType;
    }


    public ORequirement setType(String type) {
        mType = type;
        return this;
    }


    public Long[] getFieldsOrdinal() {
        return mFieldsOrdinal;
    }


    public ORequirement setFieldsOrdinal(Long[] fieldsOrdinal) {
        mFieldsOrdinal = fieldsOrdinal;
        return this;
    }
}
