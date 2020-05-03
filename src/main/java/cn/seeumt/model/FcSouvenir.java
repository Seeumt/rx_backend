package cn.seeumt.model;

import cn.seeumt.vo.SouvenirSimpleVO;
import lombok.Data;

import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/1/24 16:50
 */
@Data
public class FcSouvenir {
    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     *类目名称
     */
    private String name;

    /**
     * 该类目下所有纪念品
     */
    private List<SouvenirSimpleVO> goods;
}
