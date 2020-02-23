package cn.seeumt.vo;

import lombok.Data;

/**
 * 点赞拓展类
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/10 18:12
 */
@Data
public class LoveVO {

    private String type;

    private Integer likeCount;

    private Integer hateCount;

}
