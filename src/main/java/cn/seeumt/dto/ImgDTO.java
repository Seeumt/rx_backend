package cn.seeumt.dto;

import cn.seeumt.model.Img;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 */
@Data
public class ImgDTO {
    private String parentId;
    private List<Img> imgs;
    private String[] urls;
}
