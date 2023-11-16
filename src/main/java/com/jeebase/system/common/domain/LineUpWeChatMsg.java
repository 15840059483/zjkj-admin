package com.jeebase.system.common.domain;

public class LineUpWeChatMsg {
    private MsgMap first;
    private MsgMap keyword1;
    private MsgMap keyword2;
    private MsgMap keyword3;
    private MsgMap keyword4;
    private MsgMap keyword5;
    private MsgMap remark;

    @Override
    public String toString() {
        return "LineUpWeChatMsg{" +
                "first=" + first +
                ", keyword1=" + keyword1 +
                ", keyword2=" + keyword2 +
                ", keyword3=" + keyword3 +
                ", keyword4=" + keyword4 +
                ", keyword5=" + keyword5 +
                ", remark=" + remark +
                '}';
    }

    public class MsgMap{
        private String value;
        private String color;

        @Override
        public String toString() {
            return "MsgMap{" +
                    "value='" + value + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }

        public MsgMap set(String value, String color){
            MsgMap msgMap = new MsgMap(value, color);
            return msgMap;
        }
        public MsgMap() {
        }

        public MsgMap(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public LineUpWeChatMsg() {
    }

    public LineUpWeChatMsg(MsgMap first, MsgMap keyword1, MsgMap keyword2, MsgMap keyword3, MsgMap keyword4, MsgMap keyword5, MsgMap remark) {
        this.first = first;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.keyword4 = keyword4;
        this.keyword5 = keyword5;
        this.remark = remark;
    }
}
