package com.jeebase.system.common.dto;

import java.util.List;

public class GIExamList_Patient {

    private String PatientSex;
    private String PatientCode;
    private String MedicalCode;
    private String BarCode;
    private String IDCode;
    private String PatientName;
    private String PatientBirthday;

    private ExamInfoList ExamInfoList;

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getIDCode() {
        return IDCode;
    }

    public void setIDCode(String IDCode) {
        this.IDCode = IDCode;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPatientBirthday() {
        return PatientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        PatientBirthday = patientBirthday;
    }

    @Override
    public String toString() {
        return "GIExamList_Patient{" +
                "PatientSex='" + PatientSex + '\'' +
                ", PatientCode='" + PatientCode + '\'' +
                ", MedicalCode='" + MedicalCode + '\'' +
                ", ExamInfoList=" + ExamInfoList +
                '}';
    }

    public String getPatientSex() {
        return PatientSex;
    }

    public void setPatientSex(String patientSex) {
        PatientSex = patientSex;
    }

    public String getPatientCode() {
        return PatientCode;
    }

    public void setPatientCode(String patientCode) {
        PatientCode = patientCode;
    }

    public String getMedicalCode() {
        return MedicalCode;
    }

    public void setMedicalCode(String medicalCode) {
        MedicalCode = medicalCode;
    }

    public GIExamList_Patient.ExamInfoList getExamInfoList() {
        return ExamInfoList;
    }

    public void setExamInfoList(GIExamList_Patient.ExamInfoList examInfoList) {
        ExamInfoList = examInfoList;
    }

    public class ExamInfoList{
        private List<GIExamList_Patient_ExamInfo> GIExamList_Patient_ExamInfo;

        public List<GIExamList_Patient.GIExamList_Patient_ExamInfo> getGIExamList_Patient_ExamInfo() {
            return GIExamList_Patient_ExamInfo;
        }

        public void setGIExamList_Patient_ExamInfo(List<GIExamList_Patient.GIExamList_Patient_ExamInfo> GIExamList_Patient_ExamInfo) {
            this.GIExamList_Patient_ExamInfo = GIExamList_Patient_ExamInfo;
        }

        @Override
        public String toString() {
            return "ExamInfoList{" +
                    "GIExamList_Patient_ExamInfo=" + GIExamList_Patient_ExamInfo +
                    '}';
        }
    }
    public class GIExamList_Patient_ExamInfo{
        private String ExamInfo_ID;
        private String ExamDoctorName;
        private String VisitCode;
        private String VisitDate;
        private String ApplyDate;
        private String ExamItemName;
        private String ExamDate;
        private String ExamItemCode;
        private String GatherType;
        private String ApplyDeptName;
        private String VisitType;
        private String DeviceCode;
        private String ExamStatus;
        private String ApplyDoctorName;
        private String DeviceName;

        public void setExamInfo_ID(String examInfo_ID) {
            ExamInfo_ID = examInfo_ID;
        }

        public void setExamDoctorName(String examDoctorName) {
            ExamDoctorName = examDoctorName;
        }

        public void setVisitCode(String visitCode) {
            VisitCode = visitCode;
        }

        public void setVisitDate(String visitDate) {
            VisitDate = visitDate;
        }

        public void setApplyDate(String applyDate) {
            ApplyDate = applyDate;
        }

        public void setExamItemName(String examItemName) {
            ExamItemName = examItemName;
        }

        public void setExamDate(String examDate) {
            ExamDate = examDate;
        }

        public void setExamItemCode(String examItemCode) {
            ExamItemCode = examItemCode;
        }

        public void setGatherType(String gatherType) {
            GatherType = gatherType;
        }

        public void setApplyDeptName(String applyDeptName) {
            ApplyDeptName = applyDeptName;
        }

        public void setVisitType(String visitType) {
            VisitType = visitType;
        }

        public void setDeviceCode(String deviceCode) {
            DeviceCode = deviceCode;
        }

        public void setExamStatus(String examStatus) {
            ExamStatus = examStatus;
        }

        public void setApplyDoctorName(String applyDoctorName) {
            ApplyDoctorName = applyDoctorName;
        }

        public void setDeviceName(String deviceName) {
            DeviceName = deviceName;
        }

        public String getExamInfo_ID() {
            return ExamInfo_ID;
        }

        public String getExamDoctorName() {
            return ExamDoctorName;
        }

        public String getVisitCode() {
            return VisitCode;
        }

        public String getVisitDate() {
            return VisitDate;
        }

        public String getApplyDate() {
            return ApplyDate;
        }

        public String getExamItemName() {
            return ExamItemName;
        }

        public String getExamDate() {
            return ExamDate;
        }

        public String getExamItemCode() {
            return ExamItemCode;
        }

        public String getGatherType() {
            return GatherType;
        }

        public String getApplyDeptName() {
            return ApplyDeptName;
        }

        public String getVisitType() {
            return VisitType;
        }

        public String getDeviceCode() {
            return DeviceCode;
        }

        public String getExamStatus() {
            return ExamStatus;
        }

        public String getApplyDoctorName() {
            return ApplyDoctorName;
        }

        public String getDeviceName() {
            return DeviceName;
        }

        @Override
        public String toString() {
            return "GIExamList_Patient_ExamInfo{" +
                    "ExamInfo_ID='" + ExamInfo_ID + '\'' +
                    ", ExamDoctorName='" + ExamDoctorName + '\'' +
                    ", VisitCode='" + VisitCode + '\'' +
                    ", VisitDate='" + VisitDate + '\'' +
                    ", ApplyDate='" + ApplyDate + '\'' +
                    ", ExamItemName='" + ExamItemName + '\'' +
                    ", ExamDate='" + ExamDate + '\'' +
                    ", ExamItemCode='" + ExamItemCode + '\'' +
                    ", GatherType='" + GatherType + '\'' +
                    ", ApplyDeptName='" + ApplyDeptName + '\'' +
                    ", VisitType='" + VisitType + '\'' +
                    ", DeviceCode='" + DeviceCode + '\'' +
                    ", ExamStatus='" + ExamStatus + '\'' +
                    ", ApplyDoctorName='" + ApplyDoctorName + '\'' +
                    ", DeviceName='" + DeviceName + '\'' +
                    '}';
        }
    }
}
