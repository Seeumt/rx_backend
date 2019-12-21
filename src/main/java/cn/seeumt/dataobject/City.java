package cn.seeumt.dataobject;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Seeumt
 * @since 2019-12-21
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String cityId;

    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
