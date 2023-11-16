package com.jeebase.system.common.dto;

import java.util.List;

public class GIExamImageList {
    private String ResultMsg;
    private String ResultCode;
    private DataList DataList;

    @Override
    public String toString() {
        return "GIExamImageList{" +
                "ResultMsg='" + ResultMsg + '\'' +
                ", ResultCode='" + ResultCode + '\'' +
                ", DataList=" + DataList +
                '}';
    }

    public String getResultMsg() {
        return ResultMsg;
    }

    public void setResultMsg(String resultMsg) {
        ResultMsg = resultMsg;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public GIExamImageList.DataList getDataList() {
        return DataList;
    }

    public void setDataList(GIExamImageList.DataList dataList) {
        DataList = dataList;
    }

    public class DataList{
        private List<GIExamImage> GIExamImage;

        @Override
        public String toString() {
            return "DataList{" +
                    "GIExamImage=" + GIExamImage +
                    '}';
        }

        public List<GIExamImageList.GIExamImage> getGIExamImage() {
            return GIExamImage;
        }

        public void setGIExamImage(List<GIExamImageList.GIExamImage> GIExamImage) {
            this.GIExamImage = GIExamImage;
        }
    }

    public class GIExamImage{
        private String EyeType;
        private String FileType;
        private String ImageID;

        @Override
        public String toString() {
            return "GIExamImage{" +
                    "EyeType='" + EyeType + '\'' +
                    ", FileType='" + FileType + '\'' +
                    ", ImageID='" + ImageID + '\'' +
                    '}';
        }

        public String getEyeType() {
            return EyeType;
        }

        public void setEyeType(String eyeType) {
            EyeType = eyeType;
        }

        public String getFileType() {
            return FileType;
        }

        public void setFileType(String fileType) {
            FileType = fileType;
        }

        public String getImageID() {
            return ImageID;
        }

        public void setImageID(String imageID) {
            ImageID = imageID;
        }
    }
}
