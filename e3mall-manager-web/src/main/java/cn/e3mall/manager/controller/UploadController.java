package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.utils.JsonUtils;
import cn.e3mall.utils.PicResult;
import cn.e3mall.utils.fastdfs.FastDFSClient;

@Controller
public class UploadController {

	@Value("${IMG_SERVER_URL}")
	private String IMG_SERVER_URL;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile) {
		try {
			// 获取文件名称
			String filename = uploadFile.getOriginalFilename();
			// 获取文件扩展名
			String extName = filename.substring(filename.lastIndexOf(".") + 1);
			// 创建图片服务器上传工具类
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			// 组合url图片服务器完整路径
			url = IMG_SERVER_URL + url;
			// 创建一个PicResult对象，设置返回值结果
			PicResult picResult = new PicResult();
			picResult.setError(0);
			picResult.setUrl(url);
			String objectToJson = JsonUtils.objectToJson(picResult);
			return objectToJson;
		} catch (Exception e) {
			e.printStackTrace();
			// 上传图片失败
			// 创建一个PicResult对象，设置返回值结果
			PicResult picResult = new PicResult();
			picResult.setError(1);
			String objectToJson = JsonUtils.objectToJson(picResult);
			return objectToJson;
		}
	}
}
