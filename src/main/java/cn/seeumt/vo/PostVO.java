package cn.seeumt.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/19 15:04
 */
@Data
public class PostVO {

    @TableId
    private String postId;

    private String content;

    private String cover;

    private String userId;

    private String username;

    private String faceIcon;

    private Integer thumbCount;


}
