package com.jeebase.system.common.dto;

import java.util.List;

public class MedicalRecordResult {
    private List<Resultset> Resultset;

    @Override
    public String toString() {
        return "MedicalRecordResult{" +
                "Resultset=" + Resultset +
                '}';
    }

    public List<MedicalRecordResult.Resultset> getResultset() {
        return Resultset;
    }

    public void setResultset(List<MedicalRecordResult.Resultset> resultset) {
        Resultset = resultset;
    }

    public class Resultset{

        private String bllsh0; //病例流水号
        private String blmc00; //病例名称
        private String blzlx0; //病例子类型描述
        private String blpdf0; //病例PDF文件

        public String getBllsh0() {
            return bllsh0;
        }

        public void setBllsh0(String bllsh0) {
            this.bllsh0 = bllsh0;
        }

        public String getBlmc00() {
            return blmc00;
        }

        public void setBlmc00(String blmc00) {
            this.blmc00 = blmc00;
        }

        public String getBlzlx0() {
            return blzlx0;
        }

        public void setBlzlx0(String blzlx0) {
            this.blzlx0 = blzlx0;
        }

        public String getBlpdf0() {
            return blpdf0;
        }

        public void setBlpdf0(String blpdf0) {
            this.blpdf0 = blpdf0;
        }

        @Override
        public String toString() {
            return "Resultset{" +
                    "bllsh0='" + bllsh0 + '\'' +
                    ", blmc00='" + blmc00 + '\'' +
                    ", blzlx0='" + blzlx0 + '\'' +
                    ", blpdf0='" + blpdf0 + '\'' +
                    '}';
        }
    }
}
