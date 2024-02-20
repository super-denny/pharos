package com.pharos.app.service.link;

import com.pharos.app.service.link.req.LinkSubmitReq;
import com.pharos.app.service.link.vo.LinkSubmitListVO;
import com.pharos.common.email.MailUtil;
import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.utils.OrikaMapperUtils;
import com.pharos.domain.link.LinkInfoGateway;
import com.pharos.domain.link.dto.LinkInfoDTO;
import com.pharos.domain.link.enums.LinkPrivacyEnum;
import com.pharos.domain.user.UserInfoGateway;
import com.pharos.domain.user.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/7/18 11:40 AM
 */
@Slf4j
@Service
public class LinkInfoService {

    @Resource
    private LinkInfoGateway linkInfoGateway;

    @Resource
    private MailUtil mailUtil;

    @Resource
    private UserInfoGateway userInfoGateway;

    @Resource
    @Qualifier("sendEmailThreadPool")
    private Executor sendEmailThreadPool;

    public void submit(LinkSubmitReq req, Integer userId) {
        this.check(req);
        LinkInfoDTO linkInfoDTO = OrikaMapperUtils.map(req, LinkInfoDTO.class);
        linkInfoDTO.setUserId(userId);
        linkInfoGateway.insert(linkInfoDTO);
        UserInfoDTO userInfoDTO = userInfoGateway.getByUserId(userId);
        if (Objects.nonNull(userInfoDTO)) {
            String content = "<html>\n" +
                    "<body>\n" +
                    "  <h3>链接提报审核</h3>\n" +
                    "  <span>提报人：" + userInfoDTO.getDispName() + "</span>\n" +
                    "  </br>\n" +
                    "  <span>标题：" + linkInfoDTO.getTitle() + "</span>\n" +
                    "  </br>\n" +
                    "  <span>ICON：</span></br><img src=" + linkInfoDTO.getIcon() + " style=\"width: 100px; height: 100px;\" />\n" +
                    "  </br>\n" +
                    "  <span>是否私有：" + linkInfoDTO.getType() + "</span>\n" +
                    "  </br>\n" +
                    "  <span>链接：" + linkInfoDTO.getUrl() + "</span>\n" +
                    "  </br>\n" +
                    "  <span>提报时间：" + new Date() + "</span>\n" +
                    "<body>\n" +
                    "</html>";
            CompletableFuture.runAsync(() -> {
                try {
                    mailUtil.sendHtmlMail("wcj5299@qq.com", userInfoDTO.getDispName() + "提报了一个链接，请查看", content);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }, sendEmailThreadPool);
        }
    }

    private void check(LinkSubmitReq req) {
        if (Objects.equals(req.getPrivacy(), LinkPrivacyEnum.ORDINARY.getStatus())) {
            Integer type = req.getType();
            if (Objects.isNull(type)) {
                throw new BizException(BaseCode.PARAM_ERROR);
            }
        } else {
            req.setType(-1);
        }
    }

    public LinkSubmitListVO getSubmitList(Integer userId) {
        List<LinkInfoDTO> list = linkInfoGateway.getSubmitList(userId);
        List<LinkSubmitListVO.LinkSubmitList> linkSubmitLists = OrikaMapperUtils.mapList(list, LinkInfoDTO.class, LinkSubmitListVO.LinkSubmitList.class);
        LinkSubmitListVO vo = new LinkSubmitListVO();
        vo.setList(linkSubmitLists);
        vo.setStatisticsList(this.getStatisticsLists(list));
        return vo;
    }

    private List<LinkSubmitListVO.LinkSubmitStatisticsList> getStatisticsLists(List<LinkInfoDTO> list) {
        int waitNum = list.stream().mapToInt(x -> {
            if (Objects.equals(x.getStatus(), 0)) {
                return 1;
            }
            return 0;
        }).sum();
        int successNum = list.stream().mapToInt(x -> {
            if (Objects.equals(x.getStatus(), 1)) {
                return 1;
            }
            return 0;
        }).sum();
        int failNum = list.stream().mapToInt(x -> {
            if (Objects.equals(x.getStatus(), 2)) {
                return 1;
            }
            return 0;
        }).sum();
        List<LinkSubmitListVO.LinkSubmitStatisticsList> statisticsList = new ArrayList<>();
        statisticsList.add(new LinkSubmitListVO.LinkSubmitStatisticsList("等待审核", waitNum));
        statisticsList.add(new LinkSubmitListVO.LinkSubmitStatisticsList("审核成功", successNum));
        statisticsList.add(new LinkSubmitListVO.LinkSubmitStatisticsList("审核失败", failNum));
        return statisticsList;
    }
}
