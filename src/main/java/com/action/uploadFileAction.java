package com.action;


import com.service.IwantReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@Api(value = "uploadFileAction", description = "账号交易页面接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("uploadFile")
public class uploadFileAction {
    @Autowired
    IwantReleaseService iwantReleaseService;

    public uploadFileAction() {
    }

//    http://localhost:8881/testDemoRest/uploadFile/getInputStream
    @ApiOperation(value = "图片访问", notes = "返回checkbox数据", produces = "application/json")
    @RequestMapping(value = "getInputStream", method = RequestMethod.GET)
    @Produces("application/json")
    public InputStream getInputStreamAction(
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "parNum", required = false, defaultValue = "") String parNum,
            @RequestParam(value = "cate", required = false, defaultValue = "") String cate
    ) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL("http://localhost:8882/testDemo/dist/css/images/jx3/logo_2.png");
            if (url != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;

    }

//    调用：http://localhost:8881/testDemoRest/uploadFile/getImage?favorId=2315161&seqNum=1
//    @ApiOperation(value = "图片访问", notes = "返回图片流", produces = "application/json")
//    @RequestMapping(value = "getImage", method = RequestMethod.GET)
//    @Produces("application/zip")
//    public InputStream getImageAction(HttpServletResponse response,
//             @RequestParam(value = "favorId", required = false, defaultValue = "") String favorId,
//             @RequestParam(value = "seqNum", required = false, defaultValue = "") String seqNum) {
//
//        String folder = "d://";
//        String fileName = "a.png";
//        String id = favorId;
//        String picum = seqNum;
//
//      try{
//            response.setContentType("image/jpeg"); // 设置返回内容格式
//
//            fileName = iwantReleaseService.getPicturePath(favorId,seqNum);
//            fileName = folder+fileName;
//
//            File file = new File(fileName);       //括号里参数为文件图片路径
//            if(file.exists()){   //如果文件存在
//                InputStream in = new FileInputStream(fileName);   //用该文件创建一个输入流
//                OutputStream os = response.getOutputStream();  //创建输出流
//                byte[] b = new byte[1024];
//                while( in.read(b)!= -1){
//                    os.write(b);
//                }
//                in.close();
//                os.flush();
//                os.close();
//
//                return in;
//            }
//
//        }catch (Exception e) {
//                 e.printStackTrace();
//        }
//        return null;
//    }

    @ApiOperation(value = "图片访问", notes = "返回图片流", produces = "application/json")
    @RequestMapping(value = "getImage", method = RequestMethod.GET)
    @Produces("application/zip")
    public InputStream getImageAction(HttpServletResponse response,
          @RequestParam(value = "WENJIAN_PATH", required = false, defaultValue = "") String WENJIAN_PATH)
    {

        String folder = "d://";
        String fileName = WENJIAN_PATH;

        try{
            response.setContentType("image/jpeg"); // 设置返回内容格式
            fileName = folder+fileName;
            File file = new File(fileName);       //括号里参数为文件图片路径
            if(file.exists()){   //如果文件存在
                InputStream in = new FileInputStream(fileName);   //用该文件创建一个输入流
                OutputStream os = response.getOutputStream();  //创建输出流
                byte[] b = new byte[1024];
                while( in.read(b)!= -1){
                    os.write(b);
                }
                in.close();
                os.flush();
                os.close();
                return in;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
