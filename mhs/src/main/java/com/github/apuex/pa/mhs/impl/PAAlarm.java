package com.github.apuex.pa.mhs.impl;
import java.util.ArrayList;
import java.util.List;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAAlarm extends PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private int   dwID;//4
	private String strIDList;//
	private int	    nDataType;
	private long  tRiseTime;
	private int	    nState;
	private int	bDIValue;
	private float  fValue;
	private String szValue;
	private String szDataObjectName;
	private String szDeviceName;
	private int		nDeviceType;
	private String szStation;
	private String strStationNo;
	private String szNote;
	private int	bRise;
	private int   tLasting;
	private int   dwAlarmLevel;
	private String szMeasureMonad;
	private long tEndTime;
	private int		nACK;
	private int		bRepair;
	private int	tconf_time;
	private String strUsername;
	private int  dwUserID;
	private int  dwMessageStatus;
	private List<AIHistoryValue> aAIHistoryValue=new ArrayList<AIHistoryValue>();
	public PAAlarm(){
		priority=PAMessage.Priority[PAMessage.ALARM];
		type=PAMessage.ALARM;
		version=PAMessage.VERSION;
		tRiseTime=0;
		dwID		= 0;
		nDataType		= -1;
		nState	= -1;
		bDIValue = 0;
		fValue	= -1;
		szValue	= "";

		szNote	= "";
		bRise		= 1;
		tLasting	= 0;
		dwAlarmLevel = -1;
		szStation = "";
		strStationNo = "";
		szDataObjectName = "";
		szDeviceName = "";
		nDeviceType = -1;

		nACK = 0;	
		bRepair = 0;
		strUsername = "";
		dwUserID = 0;
		tEndTime = 0;
		tconf_time = 0;
		dwMessageStatus = 0;
	}
	public short getType(){
		return this.type;
	}
	public short getVersion(){
		return this.version;
	}
	public void setType(short type){
		this.type=type;
	}
	public void setVersion(short version){
		this.version=version;
	}
	public int getDwId(){
		return this.dwID;
	}
	public String getIdList(){
		return this.strIDList;
	}
	public int getDataType(){
		return this.nDataType;
	}
	public String getRiseTime(){
		String time=Utility.getLocalFormatTime();
		if(tRiseTime>1400000){
			time=Utility.getFormatTime((int)tRiseTime);
		}
        return time;
	}
	public int getState(){
		return this.nState;
	}
	public int getDIValue(){
		return this.bDIValue;
	}
	public float getFValue(){
		return this.fValue;
	}
	public String getSValue(){
		return this.szValue;
	}
	public String getDataObjectName(){
		return this.szDataObjectName;
	}
	public String getDeviceName(){
		return this.szDeviceName;
	}
	public int getDeviceType(){
		return this.nDeviceType;
	}
	public String getStation(){
		return this.szStation;
	}
	public String getStationNo(){
		return this.strStationNo;
	}
	public String getNote(){
		return this.szNote;
	}
	public int getRise(){
		return this.bRise;
	}
	
	public int getLasting(){
		return this.tLasting;
	}
	
	public int getAlarmLevel(){
		return this.dwAlarmLevel;
	}
	public String getMeasureMonad(){
		return this.szMeasureMonad;
	}
	public String getEndTim(){
		String time=Utility.getLocalFormatTime();
		if(tEndTime>1400000){
			time=Utility.getFormatTime((int)tEndTime);
		}
        return time;
	}
	public int getACK(){
		return this.nACK;
	}
	public int getRepair(){
		return this.bRepair;
	}
	public String getConfTime(){
		String time=Utility.getLocalFormatTime();
		if(tconf_time>1400000){
	        time=Utility.getFormatTime((int)tconf_time);
		}
        return time;
	}
	
	public String getUsername(){
		return strUsername;
	}
	public int getUserID(){
		return this.dwUserID;
	}
	public long getMessageStatus(){
		return this.dwMessageStatus;
	}
	
	public void byteToClass(byte b[]){
		int index=2;
		int len=0;
		this.version=Utility.byteToshort(b, index);
		index+=2;
		this.dwID=Utility.byteToint(b, index);
		index+=4;
		len=Utility.byteToint(b, index);
		index+=4;
		this.strIDList=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		this.nDataType=Utility.byteToint(b, index);
		index+=4;
		this.tRiseTime=Utility.byteToint(b, index);
		index+=4;
		this.nState=Utility.byteToint(b, index);
		index+=4;
		this.bDIValue=Utility.byteToint(b, index);
		index+=4;
		this.fValue=Utility.byteTofloat(b, index);
		index+=4;
		len=Utility.byteToint(b, index);
		index+=4;
		this.szValue=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		int count=Utility.byteToint(b, index);
		index+=4;
		for(int i=0;i<count;i++){
			AIHistoryValue aihis=new AIHistoryValue();
			aihis.setTime(Utility.byteToint(b, index));
			index+=4;
			aihis.setFValue(Utility.byteTofloat(b, index));
			index+=4;
			aAIHistoryValue.add(aihis);
		}
		len=Utility.byteToint(b, index);
		index+=4;
		this.szDataObjectName=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		len=Utility.byteToint(b, index);
		index+=4;
		this.szDeviceName=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		this.nDeviceType=Utility.byteToint(b, index);
		index+=4;
		
		len=Utility.byteToint(b, index);
		index+=4;
		this.szStation=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		len=Utility.byteToint(b, index);
		index+=4;
		this.strStationNo=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		len=Utility.byteToint(b, index);
		index+=4;
		this.szNote=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		this.bRise=Utility.byteToint(b, index);
		index+=4;
		
		this.tLasting=Utility.byteToint(b, index);
		index+=4;
		
		this.dwAlarmLevel=Utility.byteToint(b, index);
		index+=4;
		
		len=Utility.byteToint(b, index);
		index+=4;
		this.szMeasureMonad=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		this.tEndTime=Utility.byteToint(b, index);
		index+=4;
		
		this.nACK=Utility.byteToint(b, index);
		index+=4;
		
		this.bRepair=Utility.byteToint(b, index);
		index+=4;
		
		this.tconf_time=Utility.byteToint(b, index);
		index+=4;
		
		len=Utility.byteToint(b, index);
		index+=4;
		this.strUsername =Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		
		this.dwUserID=Utility.byteToint(b, index);
		index+=4;
		
		this.dwMessageStatus=Utility.byteToint(b, index);
		index+=4;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=4;//id
		len+=4;//id
		//System.out.println(Utility.getStringGBKLen(this.strIDList));
		len+=Utility.getStringGBKLen(this.strIDList);
		if(Utility.getStringGBKLen(this.strIDList)>0){
			len+=1;
		}
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=Utility.getStringGBKLen(this.szValue);
		if(Utility.getStringGBKLen(this.szValue)>0){
			len+=1;
		}
		len+=4;
		len+=aAIHistoryValue.size()*8;
		len+=4;
		len+=Utility.getStringGBKLen(this.szDataObjectName);
		if(Utility.getStringGBKLen(this.szDataObjectName)>0){
			len+=1;
		}
		len+=4;
		len+=Utility.getStringGBKLen(this.szDeviceName);
		if(Utility.getStringGBKLen(this.szDeviceName)>0){
			len+=1;
		}
		len+=4;
		len+=4;
		len+=Utility.getStringGBKLen(this.szStation);
		if(Utility.getStringGBKLen(this.szStation)>0){
			len+=1;
		}
		len+=4;
		len+=Utility.getStringGBKLen(this.strStationNo);
		if(Utility.getStringGBKLen(this.strStationNo)>0){
			len+=1;
		}
		len+=4;
		len+=Utility.getStringGBKLen(this.szNote);
		if(Utility.getStringGBKLen(this.szNote)>0){
			len+=1;
		}
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=Utility.getStringGBKLen(this.szMeasureMonad);
		if(Utility.getStringGBKLen(this.szMeasureMonad)>0){
			len+=1;
		}
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=4;
		len+=Utility.getStringGBKLen(this.strUsername);
		if(Utility.getStringGBKLen(this.strUsername)>0){
			len+=1;
		}
		len+=4;
		len+=4;
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.intTobyte(b, index, this.dwID);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.strIDList));
		index+=Utility.strTobytePA(b, index, this.strIDList);
		index+=Utility.intTobyte(b, index, this.nDataType);
		index+=Utility.longTobyte(b, index, this.tRiseTime);
		index+=Utility.intTobyte(b, index, this.nState);
		index+=Utility.intTobyte(b, index, this.bDIValue);
		index+=Utility.floatTobyte(b, index, this.fValue);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.szValue));
		index+=Utility.strTobytePA(b, index, this.szValue);
		index+=Utility.intTobyte(b, index, aAIHistoryValue.size());
		for(int i=0;i<aAIHistoryValue.size();i++){
			AIHistoryValue his=aAIHistoryValue.get(i);
			index+=Utility.intTobyte(b, index,his.getTime());
			index+=Utility.floatTobyte(b, index,his.getFValue());
		}
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.szDataObjectName));
		index+=Utility.strTobytePA(b, index, this.szDataObjectName);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.szDeviceName));
		index+=Utility.strTobytePA(b, index, this.szDeviceName);
		index+=Utility.intTobyte(b, index,this.nDeviceType);
		
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.szStation));
		index+=Utility.strTobytePA(b, index, this.szStation);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.strStationNo));
		index+=Utility.strTobytePA(b, index, this.strStationNo);
		
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.szNote));
		index+=Utility.strTobytePA(b, index, this.szNote);
		index+=Utility.intTobyte(b, index,this.bRise);
		index+=Utility.intTobyte(b, index,this.tLasting);
		index+=Utility.intTobyte(b, index,this.dwAlarmLevel);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.szMeasureMonad));
		index+=Utility.strTobytePA(b, index, this.szMeasureMonad);
		index+=Utility.longTobyte(b, index,this.tEndTime);
		index+=Utility.intTobyte(b, index,this.nACK);
		index+=Utility.intTobyte(b, index,this.bRepair);
		index+=Utility.intTobyte(b, index,this.tconf_time);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.strUsername));
		index+=Utility.strTobytePA(b, index, this.strUsername);
		index+=Utility.intTobyte(b, index,this.dwUserID);
		index+=Utility.intTobyte(b, index,this.dwMessageStatus);
		return b;
	}

	public void setDwId(int dwID){
		this.dwID=dwID;
	}
	public void setIdList(String strIDList){
		this.strIDList=strIDList;
	}
	public void setDataType(int nDataType){
		this.nDataType=nDataType;
	}
	public void setRiseTime(long tRiseTime){
		this.tRiseTime=tRiseTime;
	}
	public void setState(int nState){
		this.nState=nState;
	}
	public void setDIValue(int bDIValue){
		this.bDIValue=bDIValue;
	}
	public void setFValue(float fValue){
		this.fValue=fValue;
	}
	public void setSValue(String szValue){
		this.szValue=szValue;
	}
	public void setDataObjectName(String szDataObjectName){
		this.szDataObjectName=szDataObjectName;
	}
	public void setDeviceName(String szDeviceName){
		this.szDeviceName=szDeviceName;
	}
	public void setDeviceType(int nDeviceType){
		this.nDeviceType=nDeviceType;
	}
	public void setStation(String szStation){
		this.szStation=szStation;
	}
	public void setStationNo(String strStationNo){
		this.strStationNo=strStationNo;
	}
	public void setNote(String szNote){
		this.szNote=szNote;
	}
	public void setRise(int bRise){
		this.bRise=bRise;
	}
	
	public void setLasting(int tLasting){
		this.tLasting=tLasting;
	}
	
	public void setAlarmLevel(int dwAlarmLevel){
		this.dwAlarmLevel=dwAlarmLevel;
	}
	public void setMeasureMonad(String szMeasureMonad){
		this.szMeasureMonad=szMeasureMonad;
	}
	public void setEndTim(long tEndTime){
		this.tEndTime=tEndTime;
	}
	public void setACK(int nACK){
		this.nACK=nACK;
	}
	public void setRepair(int bRepair){
		this.bRepair=bRepair;
	}
	public void setConfTime(int tconf_time){
		this.tconf_time=tconf_time;
	}
	
	public void setUsername(String strUsername){
		this.strUsername=strUsername;
	}
	public void setUserID(int dwUserID){
		this.dwUserID=dwUserID;
	}
	public void setMessageStatus(int dwMessageStatus){
		this.dwMessageStatus=dwMessageStatus;
	}
	public void addAIHistoryValue(float fValue,int tTime){
		AIHistoryValue his=new AIHistoryValue();
		his.setFValue(fValue);
		his.setTime(tTime);
		aAIHistoryValue.add(his);
	}
	public int getPriority() {
		return this.priority;
	}
	public String getSql(int agentId){
		String sql="",sqlValue="",AlarmBegin="",AlarmEnd="";//
		AlarmBegin=Utility.getFormatTime((int)tRiseTime);
		if(bRise==1){
			sqlValue=String.format("%d,'',%d,'%s','','%s',%d,'%s','%s',%d,%d,%.2f,'','',0,0,%d,'%s','',0,%d,0",dwID,nDataType,szStation,szDeviceName,nDeviceType,szDataObjectName,AlarmBegin,nState,bDIValue,fValue,dwAlarmLevel,szNote,agentId);
			sql=String.format("if not exists (select * from AAlarmData where DataObjectID=%d and AlarmBegin='%s') insert into AAlarmData (DataObjectID,IDList,DataObjType,Station,StationNo,DeviceName,DeviceType,ObjectName,AlarmBegin,State,DIValue,fValue,szValue,MeasureMonad ,AlarmLasting,AckStatus,AlarmLevel,AlarmValue,AlarmHistory,RepairLogId,AgentID,MessageStatus) values (%s)",dwID,AlarmBegin,sqlValue);
		}else{
			AlarmEnd=Utility.getFormatTime((int)tEndTime);
			sql=String.format("update  AAlarmData set AlarmEnd='%s',AlarmValue='%s' where DataObjectID='%d' and AlarmBegin='%s'",AlarmEnd,szNote,dwID,AlarmBegin);
		}
		return sql;
	}
}
