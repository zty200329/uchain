package com.uchain.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @Description ("分组（1.后端 2.前端 3.区块链 4.算法 5.UI）")
 * @ClassName GroupEnum
 * @Author: baobao
 * @Date: Created in 19:29 2020/2/19
 */
@Getter
public enum GroupEnum {
    BACK_END(1, "后端"),
    FRONT_END(2, "前端"),
    BLOCK_CHAIN(3,"区块链"),
    ALGORITHM(4,"算法"),
    UI(5,"UI");


    private Integer value;
    private String group;

    GroupEnum(Integer value, String group) {
        this.value = value;
        this.group = group;
    }

    public static String getGroup(Integer integer) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(BACK_END.getValue(), BACK_END.getGroup());
        hashMap.put(FRONT_END.getValue(), FRONT_END.getGroup());
        hashMap.put(BLOCK_CHAIN.getValue(), BLOCK_CHAIN.getGroup());
        hashMap.put(ALGORITHM.getValue(), ALGORITHM.getGroup());
        hashMap.put(UI.getValue(), UI.getGroup());
        return hashMap.get(integer);
    }


    public static Integer getValue(String group) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(BACK_END.getGroup(),BACK_END.getValue());
        hashMap.put(FRONT_END.getGroup(),FRONT_END.getValue());
        hashMap.put(BLOCK_CHAIN.getGroup(),BLOCK_CHAIN.getValue());
        hashMap.put(ALGORITHM.getGroup(),ALGORITHM.getValue());
        hashMap.put(UI.getGroup(),UI.getValue());
        return hashMap.get(group);
    }
}
