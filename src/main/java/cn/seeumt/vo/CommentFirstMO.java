package cn.seeumt.vo;

import cn.seeumt.model.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/17 17:03
 */
@Data
@AllArgsConstructor
public class CommentFirstMO implements Serializable {

    private static final long serialVersionUID = -1237870131418770805L;

    @JsonProperty("reviewMsg")
    private List<CommentMO> commentMOS;

    @JsonProperty("reviewNum")
    private Integer commentCount;


}
