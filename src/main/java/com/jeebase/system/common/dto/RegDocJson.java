package com.jeebase.system.common.dto;

import java.util.List;

public class RegDocJson {

	private List<RegDoc> RegDoc;
	
	
	@Override
	public String toString() {
		return "RegDocJson [RegDoc=" + RegDoc + "]";
	}


	public RegDocJson(List<com.jeebase.system.common.dto.RegDocJson.RegDoc> regDoc) {
		super();
		RegDoc = regDoc;
	}


	public RegDocJson() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<RegDoc> getRegDoc() {
		return RegDoc;
	}


	public void setRegDoc(List<RegDoc> regDoc) {
		RegDoc = regDoc;
	}


	public class RegDoc{
		private String ID;
		private String DocID;
		private String DocName;
		private String SeeNO;
		private String Begin;
		private String End;
		private String DeptID;
		private String DeptName;
		private String NoonID;
		private String RegLevelID;
		private String RegLevelName;
		public RegDoc(String iD, String docID, String docName, String seeNO, String begin, String end, String deptID,
				String deptName, String noonID, String regLevelID, String regLevelName) {
			super();
			ID = iD;
			DocID = docID;
			DocName = docName;
			SeeNO = seeNO;
			Begin = begin;
			End = end;
			DeptID = deptID;
			DeptName = deptName;
			NoonID = noonID;
			RegLevelID = regLevelID;
			RegLevelName = regLevelName;
		}
		public RegDoc() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getDocID() {
			return DocID;
		}
		public void setDocID(String docID) {
			DocID = docID;
		}
		public String getDocName() {
			return DocName;
		}
		public void setDocName(String docName) {
			DocName = docName;
		}
		public String getSeeNO() {
			return SeeNO;
		}
		public void setSeeNO(String seeNO) {
			SeeNO = seeNO;
		}
		public String getBegin() {
			return Begin;
		}
		public void setBegin(String begin) {
			Begin = begin;
		}
		public String getEnd() {
			return End;
		}
		public void setEnd(String end) {
			End = end;
		}
		public String getDeptID() {
			return DeptID;
		}
		public void setDeptID(String deptID) {
			DeptID = deptID;
		}
		public String getDeptName() {
			return DeptName;
		}
		public void setDeptName(String deptName) {
			DeptName = deptName;
		}
		public String getNoonID() {
			return NoonID;
		}
		public void setNoonID(String noonID) {
			NoonID = noonID;
		}
		public String getRegLevelID() {
			return RegLevelID;
		}
		public void setRegLevelID(String regLevelID) {
			RegLevelID = regLevelID;
		}
		public String getRegLevelName() {
			return RegLevelName;
		}
		public void setRegLevelName(String regLevelName) {
			RegLevelName = regLevelName;
		}
		@Override
		public String toString() {
			return "RegDoc [ID=" + ID + ", DocID=" + DocID + ", DocName=" + DocName + ", SeeNO=" + SeeNO + ", Begin="
					+ Begin + ", End=" + End + ", DeptID=" + DeptID + ", DeptName=" + DeptName + ", NoonID=" + NoonID
					+ ", RegLevelID=" + RegLevelID + ", RegLevelName=" + RegLevelName + "]";
		}
		
	}
}
