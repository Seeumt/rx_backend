package cn.seeumt.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seeumt
 * @version 1.0
 */
@Data
public class MyPageHelper<T>  implements Serializable {
    private static final long serialVersionUID = 4262448534440986452L;
    private int id;
    private int pageNum;
    private int pageSize;
    private int size;
    private String orderBy;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list;
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean isFaPage;
    private boolean isLaPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int[] navigatepageNums;


}
