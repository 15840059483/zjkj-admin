package com.jeebase.system.common.domain;

import java.util.List;

public class GetRegTimeRange {


    private String Code;
    private String Message;
    private String ExtendMsg;
    private List<Schemaqueues> Schemaqueues;

    @Override
    public String toString() {
        return "GetRegTimeRange{" +
                "Code='" + Code + '\'' +
                ", Message='" + Message + '\'' +
                ", ExtendMsg='" + ExtendMsg + '\'' +
                ", Schemaqueues=" + Schemaqueues +
                '}';
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getExtendMsg() {
        return ExtendMsg;
    }

    public void setExtendMsg(String extendMsg) {
        ExtendMsg = extendMsg;
    }

    public List<GetRegTimeRange.Schemaqueues> getSchemaqueues() {
        return Schemaqueues;
    }

    public void setSchemaqueues(List<GetRegTimeRange.Schemaqueues> schemaqueues) {
        Schemaqueues = schemaqueues;
    }

    public GetRegTimeRange() {
    }

    public GetRegTimeRange(String code, String message, String extendMsg, List<GetRegTimeRange.Schemaqueues> schemaqueues) {
        Code = code;
        Message = message;
        ExtendMsg = extendMsg;
        Schemaqueues = schemaqueues;
    }

    public class Schemaqueues{
        private String ID;
        private String SeeTime;
        private String State;
        private String Queue;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSeeTime() {
            return SeeTime;
        }

        public void setSeeTime(String seeTime) {
            SeeTime = seeTime;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getQueue() {
            return Queue;
        }

        public void setQueue(String queue) {
            Queue = queue;
        }

        @Override
        public String toString() {
            return "Schemaqueues{" +
                    "ID='" + ID + '\'' +
                    ", SeeTime='" + SeeTime + '\'' +
                    ", State='" + State + '\'' +
                    ", Queue='" + Queue + '\'' +
                    '}';
        }

        public Schemaqueues() {
        }

        public Schemaqueues(String ID, String seeTime, String state, String queue) {
            this.ID = ID;
            SeeTime = seeTime;
            State = state;
            Queue = queue;
        }
    }

}
