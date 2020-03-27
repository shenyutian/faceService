package com.syt.face.faceservice;

import com.alibaba.fastjson.JSONObject;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

@Controller
public class FaceEngineController {

    @Value("${syt.upload}")
    String path;

    @Value("${appId}")
    private String appId;

    @Value("${sdkKey}")
    private String sdkKey;

    @Value("${lib}")
    private String lib;

    @Value("${img}")
    private String img;

    @Value("${ip}")
    private String ip;

    private FaceEngine faceEngine;
    JSONObject jsonObject = new JSONObject();

    /**
     * 实现传入人脸检测生成 人脸数据
     */
    @PostMapping("api/createFace")
    @ResponseBody
    public Object createFace(MultipartFile file) {

        jsonObject.clear();

        String filePath = path + "/" + file.getOriginalFilename();

        try {
            String filepath = path + "/" + file.getOriginalFilename();
            jsonObject.put("filepath", filepath);
            File destFile = new File(filepath);
            if(!destFile.getParentFile().exists()){
                destFile.mkdirs();
            }
            file.transferTo(destFile);
            jsonObject.put("src", "http://" + ip + ":8001/" + file.getOriginalFilename());

            file.getOriginalFilename();
            createFace(destFile);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg", "上传失败," + e.getMessage());
        }

        return jsonObject;
    }

    /**
     * 创建人脸特征值
     * @param faceFile
     */
    public void createFace(File faceFile) {
        if (faceEngine == null) {
            initFaceEngine();
        }
        //人脸检测
        ImageInfo imageInfo = getRGBData(faceFile);
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        int errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        System.out.println(faceInfoList);
        jsonObject.put("人脸检测结果", errorCode);
        jsonObject.put("faceInfoList", faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);
        jsonObject.put("特征提取结果", errorCode);
        jsonObject.put("特征值大小", faceFeature.getFeatureData().length);
        jsonObject.put("faceFeature", faceFeature);

    }

    /**
     * 初始化人脸引擎
     */
    private void initFaceEngine() {
        faceEngine = new FaceEngine(lib);
        //激活引擎
        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
            jsonObject.put("errorCode", "引擎激活失败");
        }

        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
            jsonObject.put("errorCode", "获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(1);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);

        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
            jsonObject.put("errorCode", "初始化引擎失败");
        }
    }

}
