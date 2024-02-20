package com.pharos.common.response;//package com.friday.common.response;
//
//import lombok.Data;
//
//import java.util.List;
//
///**
// * @author wcj
// * @ClassName PageInfo
// * @Description 分页
// * @createTime 2020-08-30
// */
//@Data
//public class PageInfo<T> {
//
//    // 当前页
//    private Integer pageNo = 1;
//    // 每页显示的总条数
//    private Integer pageSize = 10;
//    // 总条数
//    private Integer totalNum;
//    // 总页数
//    private Integer totalPage;
//    // 分页结果
//    private List<T> result;
//
//    public PageInfo() {
//        super();
//    }
//
//    public PageInfo(Integer pageNo, Integer pageSize, Integer totalNum) {
//        this.pageNo = pageNo;
//        this.pageSize = pageSize;
//        this.totalNum = totalNum;
//        int index = totalNum / pageSize;
//        int end = totalNum % pageSize;
//        int totalPage = 0;
//        if (end > 0) {
//            totalPage = index + 1;
//        } else {
//            totalPage = index;
//        }
//        this.totalPage = totalPage;
//    }
//
//}
