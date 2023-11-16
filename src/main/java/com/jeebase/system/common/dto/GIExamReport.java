package com.jeebase.system.common.dto;

public class GIExamReport {
    private String ResultCode;
    private String ResultMsg;
    private GIExamReport_Data DataList;

    public GIExamReport(String resultCode, String resultMsg, GIExamReport_Data dataList) {
        ResultCode = resultCode;
        ResultMsg = resultMsg;
        DataList = dataList;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public String getResultMsg() {
        return ResultMsg;
    }

    public void setResultMsg(String resultMsg) {
        ResultMsg = resultMsg;
    }

    public GIExamReport_Data getDataList() {
        return DataList;
    }

    @Override
    public String toString() {
        return "GIExamReport{" +
                "ResultCode='" + ResultCode + '\'' +
                ", ResultMsg='" + ResultMsg + '\'' +
                ", DataList=" + DataList +
                '}';
    }

    public void setDataList(GIExamReport_Data dataList) {
        DataList = dataList;
    }

    public class  GIExamReport_Data{
        private Report GIExamReport_Data;

        public Report getGIExamReport_Data() {
            return GIExamReport_Data;
        }

        public void setGIExamReport_Data(Report GIExamReport_Data) {
            this.GIExamReport_Data = GIExamReport_Data;
        }

        @Override
        public String toString() {
            return "GIExamReport_Data{" +
                    "GIExamReport_Data=" + GIExamReport_Data +
                    '}';
        }
    }


    public class Report {

        private String ReportDate;
        private String ReportDoctorName;
        private String ConfirmDate;
        private String ConfirmDoctorName;
        private String Finding;
        private String Conclusion;
        private String PdfContent;
        private String XmlContent;

        @Override
        public String toString() {
            return "Report{" +
                    "ReportDate='" + ReportDate + '\'' +
                    ", ReportDoctorName='" + ReportDoctorName + '\'' +
                    ", ConfirmDate='" + ConfirmDate + '\'' +
                    ", ConfirmDoctorName='" + ConfirmDoctorName + '\'' +
                    ", Finding='" + Finding + '\'' +
                    ", Conclusion='" + Conclusion + '\'' +
                    ", PdfContent='" + PdfContent + '\'' +
                    ", XmlContent='" + XmlContent + '\'' +
                    '}';
        }

        public String getReportDate() {
            return ReportDate;
        }

        public void setReportDate(String reportDate) {
            ReportDate = reportDate;
        }

        public String getReportDoctorName() {
            return ReportDoctorName;
        }

        public void setReportDoctorName(String reportDoctorName) {
            ReportDoctorName = reportDoctorName;
        }

        public String getConfirmDate() {
            return ConfirmDate;
        }

        public void setConfirmDate(String confirmDate) {
            ConfirmDate = confirmDate;
        }

        public String getConfirmDoctorName() {
            return ConfirmDoctorName;
        }

        public void setConfirmDoctorName(String confirmDoctorName) {
            ConfirmDoctorName = confirmDoctorName;
        }

        public String getFinding() {
            return Finding;
        }

        public void setFinding(String finding) {
            Finding = finding;
        }

        public String getConclusion() {
            return Conclusion;
        }

        public void setConclusion(String conclusion) {
            Conclusion = conclusion;
        }

        public String getPdfContent() {
            return PdfContent;
        }

        public void setPdfContent(String pdfContent) {
            PdfContent = pdfContent;
        }

        public String getXmlContent() {
            return XmlContent;
        }

        public void setXmlContent(String xmlContent) {
            XmlContent = xmlContent;
        }
    }


}
