package com.podzirey.datastructures;

import java.util.ArrayList;

public class TestVo {
    Boolean isCreatedWithDefaultConstructor;
    Boolean isCreatedWithIntConstructor;
    ArrayList testList;
    public TestVo(){
        isCreatedWithDefaultConstructor = true;
    }

    public TestVo(int value){
        isCreatedWithIntConstructor = true;
    }

}


