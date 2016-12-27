package cn.edu.bjtu.weibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import cn.edu.bjtu.weibo.service.HotTopicService;
//import org.springframework.stereotype.Controller;
import cn.edu.bjtu.weibo.service.TopicService;

@RestController
@RequestMapping("/t")
public class RegisterController {

	@Autowired
	TopicService topicService;	
	@Autowired
	HotTopicService hotTopicService;
	@Autowired
	Result result;

	/**
	 * 返回前十热门话题
	 * @return
	 */
	@RequestMapping(value = "/hotTopic", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result getHotTopic() {
		result.setData(hotTopicService.HotTopic(1, 10));
       return result;
	}
	
	
	/**
	 *  When a user want to see all the weibo or comment contain the topic, this service will help
	 * @return
	 */
	@RequestMapping(value = "/topic", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result getAllTopic(@RequestBody  Info info) {
		
		result.setData(topicService.getWeiboOrCommentListUnderTopic(Info.topicId, Info.pageIndex, Info.pagePerPage));   
		return result;
	}
}
