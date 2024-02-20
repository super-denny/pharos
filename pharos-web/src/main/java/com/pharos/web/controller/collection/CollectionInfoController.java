package com.pharos.web.controller.collection;

import com.pharos.app.service.collection.CollectionService;
import com.pharos.app.service.collection.req.CollectionActionReq;
import com.pharos.app.service.collection.vo.MyCollectionVO;
import com.pharos.common.response.Response;
import com.pharos.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author wcj
 * @version 1.0
 * @description: TODO
 * @date 2023/8/3 10:40 AM
 */
@RestController
@RequestMapping("/api/collection")
public class CollectionInfoController extends BaseController {

    @Resource
    private CollectionService collectionService;

    @PostMapping("/action")
    public Response<Void> action(@Valid @RequestBody CollectionActionReq req) {
        collectionService.action(req, getLoginUserId());
        return new Response<Void>().success();
    }

    @GetMapping("/queryMyCollection")
    public Response<List<MyCollectionVO>> queryMyCollection() {
        List<MyCollectionVO> list = collectionService.queryMyCollection(getLoginUserId());
        return new Response<List<MyCollectionVO>>().success(list);
    }

}
