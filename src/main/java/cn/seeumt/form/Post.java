package cn.seeumt.form;

import cn.seeumt.vo.TagVO;
import cn.seeumt.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/14 14:36
 */
@Data
@ApiModel(value="动态类",description="用户发布动态" )
public class Post {
    @ApiModelProperty(value = "用户Id",example="1002")
    private String userId;
    @ApiModelProperty(value = "动态内容",example="hello Tips ！")
    private String content;
    @ApiModelProperty(value = "动态类型",example="原创1/转发0")
    private Boolean type;
    @ApiModelProperty(value = "标签数组",example="[1,2,3,4]",allowEmptyValue = true)
    private String tagIds;
    @ApiModelProperty(value = "地理位置",example="sm.cumt",allowEmptyValue = true)
    private String location;
}
