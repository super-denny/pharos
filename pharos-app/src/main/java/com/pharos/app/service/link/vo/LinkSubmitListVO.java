package com.pharos.app.service.link.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/19 10:18 PM
 */
@Data
public class LinkSubmitListVO {

    private List<LinkSubmitList> list;

    private List<LinkSubmitStatisticsList> statisticsList;

    @Data
    public static class LinkSubmitList {
        private Integer id;
        private String title;
        private String url;
        private Integer privacy;
        private Integer status;
        private String failReason;
        private Date gmtCreate;
        private Date gmtModify;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkSubmitStatisticsList {
        private String title;
        private Integer num;
    }
}
