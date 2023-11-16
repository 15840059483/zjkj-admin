package com.jeebase.system.common.dto;

public class ExamImage {

    private String EyeType;
    private String ImageID;
    private String FileType;

    @Override
    public String toString() {
        return "ExamImage{" +
                "EyeType='" + EyeType + '\'' +
                ", ImageID='" + ImageID + '\'' +
                ", FileType='" + FileType + '\'' +
                '}';
    }

    public String getEyeType() {
        return EyeType;
    }

    public void setEyeType(String eyeType) {
        EyeType = eyeType;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }
}
