package cn.seeumt.form;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/29 19:23
 */
@Data
public class Category {

    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    private Integer parentId;

    /**
     * 类别名称
     */
    private String name;


    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    private Integer sortOrder;


}
