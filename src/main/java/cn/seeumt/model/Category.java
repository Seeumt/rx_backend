package cn.seeumt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/30 16:47
 */
@Data
public class Category {
    @JsonProperty("id")
    private Integer categoryId;
    private Integer parentId;
    private String name;
    private Integer rootId;
    private List<Category> children;
}
