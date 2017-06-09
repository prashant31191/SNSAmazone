package com.api.response;


import com.api.model.LanguageModel;
import com.api.model.TestExamModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dhaval.mehta on 13 - Feb, 2017.
 */

public class TestExamResponce extends CommonResponse
{
    @SerializedName("data")
    public ArrayList<TestExamModel> arrayListTestExamModel;
}
