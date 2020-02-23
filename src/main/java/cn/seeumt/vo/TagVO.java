package cn.seeumt.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Seeumt
 * @since
 */
@Data
public class TagVO{

    /**
     * //标签id
     */
    private String tagId;

    /**
     * //标签名称
     */
    private String name;


    private Boolean isSelected;

}
