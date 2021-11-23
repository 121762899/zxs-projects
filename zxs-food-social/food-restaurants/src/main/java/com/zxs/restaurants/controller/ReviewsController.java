package com.zxs.restaurants.controller;

import com.zxs.restaurants.service.ReviewsService;
import org.springframework.web.bind.annotation.*;
import com.zxs.commons.model.domain.ResultInfo;
import com.zxs.commons.model.vo.CommentVO;
import com.zxs.commons.model.vo.ReviewsVO;
import com.zxs.commons.utils.ResultInfoUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewsController {

    @Resource
    private ReviewsService reviewsService;
    @Resource
    private HttpServletRequest request;

    /**
     * 添加餐厅评论
     *
     * @param restaurantId
     * @param access_token
     * @param content
     * @param likeIt
     * @return
     */
    @PostMapping("{restaurantId}")
    public ResultInfo<String> addReview(@PathVariable Integer restaurantId,
                                        String access_token,
                                        @RequestParam("content") String content,
                                        @RequestParam("likeIt") int likeIt) {
        reviewsService.addReview(restaurantId,content, access_token,likeIt);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), "添加成功");
    }

    /**
     * 获取餐厅最新评论
     *
     * @param restaurantId
     * @param access_token
     * @return
     */
    @GetMapping("{restaurantId}/news")
    public ResultInfo<List<ReviewsVO>> findNewReviews(@PathVariable Integer restaurantId,
                                                      String access_token) {
        List<ReviewsVO> reviewsList = reviewsService.getNewReviews(restaurantId, access_token);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), reviewsList);
    }

    @GetMapping("{restaurantId}/multiple")
    public ResultInfo<List<CommentVO>> getMultipleReviews(@PathVariable Integer restaurantId,
                                                      String access_token) {
        List<CommentVO> multipleReviews = reviewsService.getMultipleReviews(restaurantId, access_token);
        return ResultInfoUtil.buildSuccess(request.getServletPath(), multipleReviews);
    }


}
