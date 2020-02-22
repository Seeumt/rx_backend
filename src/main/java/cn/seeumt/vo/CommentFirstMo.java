package cn.seeumt.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/17 17:03
 */
@Data
@AllArgsConstructor
public class CommentFirstMo implements Serializable {

    private static final long serialVersionUID = -1237870131418770805L;

    @JsonProperty("reviewMsg")
    private List<CommentMo> commentMos;

    @JsonProperty("reviewNum")
    private Integer commentCount;


}
