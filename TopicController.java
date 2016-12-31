package cn.edu.bjtu.weibo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bjtu.weibo.model.Topic;
import cn.edu.bjtu.weibo.service.HotTopicService;
import cn.edu.bjtu.weibo.service.TopicService;
import cn.edu.bjtu.weibo.model.BaseContent;
import cn.edu.bjtu.weibo.model.Result;

@RestController
@RequestMapping("/t")
public class TopicController {

	@Autowired
	TopicService topicService;	
	
	@Autowired
	HotTopicService hotTopicService;
	
	Result result;
	
	

	/**
	 * return the top ten hotTopic
	 * @return
	 */
	@RequestMapping(value = "/hotTopic", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result getHotTopic() {
		
		List<Topic> topicList=hotTopicService.HotTopic(1, 10);	
		Result result = new Result();
		
		if(topicList==null||topicList.isEmpty()){
			result.setStatus(Result.FAILED);
			result.setTipCode("401");
			result.setTipMsg("Get hotTopic falied");
			return result;
		}
		
		result.setStatus(Result.SUCCESS);			
		result.setTipCode("200");			
		result.setTipMsg("Get hotTopic success");		
		result.setData(topicList);       
		return result;
	}
	
	
	/**
	 *  When a user want to see all the weibo or comment contain the topic, this function will help 
	 * @return
	 */
	@RequestMapping(value = "/topic", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Result getAllTopic(String topicId,int pageIndex,int pagePerPage) {
		
		List<BaseContent> BaseContentList=topicService.getWeiboOrCommentListUnderTopic(topicId, pageIndex,pagePerPage);	
		Result result = new Result();
		
		if(BaseContentList==null||BaseContentList.isEmpty()){
			result.setStatus(Result.FAILED);
			result.setTipCode("401");
			result.setTipMsg("Get BaseContent falied");
			return result;
		}
		
		result.setStatus(Result.SUCCESS);			
		result.setTipCode("200");			
		result.setTipMsg("Get BaseContent success");		
		result.setData(BaseContentList);   
		return result;
	}
}
