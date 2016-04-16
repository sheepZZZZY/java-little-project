package project2;

import java.util.Vector;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class cTask{
	String cID,cName;
	String major,Class;
	int hour,praticedhour;
	String tID,tName;
	Vector<cPos> tuplec; //MAX_SIZE=2
	public cTask(){tuplec=new Vector<cPos>();}
	int start,end;
	public String rname;
}
class cTable{  //3D: (Week,Day,Course)
	int weekNum=25,dayNum=5,courseNum=4;
	Vector<Vector<Vector<String> > > table;
	public cTable(){
		table=new Vector<Vector<Vector<String> > >();
		table.setSize(weekNum+1);
		for(int i=0;i<=weekNum;i++){
			table.set(i, new Vector<Vector<String>>());
			table.get(i).setSize(dayNum+1);
			for(int j=0;j<=dayNum;j++){
				table.get(i).set(j, new Vector<String>());
				table.get(i).get(j).setSize(courseNum+1);}
		}
	}
}
class tSchedule extends cTable{
	public tSchedule(){
		table=new Vector<Vector<Vector<String> > >();
		table.setSize(weekNum+1);
		for(int i=0;i<=weekNum;i++){
			table.set(i, new Vector<Vector<String>>());
			table.get(i).setSize(dayNum+1);
			for(int j=0;j<=dayNum;j++){
				table.get(i).set(j, new Vector<String>());
				table.get(i).get(j).setSize(courseNum+1);
				for(int k=0;k<=courseNum;k++) table.get(i).get(j).set(k,"空闲");
			}
		}
	}
}
class cPos{
	int day,pos;
	public cPos(int day,int pos){this.day=day;this.pos=pos;
	}
	}

public class ArrangeCourse {
	   Map<String,Vector<cTask> > task=new HashMap<String,Vector<cTask> >();
	   Map<String,cTable> cSchedule=new HashMap<String,cTable>();
	   Map<String,tSchedule> tSchedule=new HashMap<String,tSchedule>();
	   Map<String,LinkedList> Q1=new HashMap<String,LinkedList>();
	   Map<String,LinkedList> Q2=new HashMap<String,LinkedList>();
	   static Map<String,LinkedList> display=new HashMap<String,LinkedList>();
	   public Excel fileEditor;
	   DisplayS mainFrame;
	   public NewDisplay mainFrame2;
	protected static Object doc;
	   ArrangeCourse() throws IOException
	   {
		   fileEditor=new Excel();
		   Serialize();//将Java 对象序列化为二进制文件的序列化技术
		   loadTask();
	   }
	   public cTask getCourseFromMainQ(int week,int day,int pos,String cName){  //从主队链中取出第一个符合条件的课，条件：教师空闲
		   LinkedList mQ=Q1.get(cName);
		   cTask nowTask;
		   tSchedule nowTeacher;
		   for(int i=0;i<mQ.size();i++){
			   nowTask=(cTask) mQ.get(i);
			   nowTeacher=tSchedule.get(nowTask.tName);
			   if(nowTeacher.table.get(week).get(day).get(pos).equals("空闲")){
				   nowTeacher.table.get(week).get(day).set(pos,cName);
				   mQ.remove(i);
				   nowTask.start=week; //首次出列，添加起始周标记，修改主队链和教师计划
				   Q1.put(cName, mQ);
				   tSchedule.put(nowTask.tName, nowTeacher);
				   return nowTask;
			   }
		   }
		   nowTask=new cTask();
		   nowTask.hour=-1;
		   return nowTask;
	   }
	   public cTask getCourseFromSubQ(int week,int day,int pos,String cName,Map<String,Integer> status){  //从次队链中取出第一个符合条件的课，条件：教师空闲，当周次数少于2
		   LinkedList sQ=Q2.get(cName);
		   cTask nowTask;
		   tSchedule nowTeacher;
		   for(int i=0;i<sQ.size();i++){
			   nowTask=(cTask) sQ.get(i);
			   //检查该课当周情况
			   if(status.containsKey(nowTask.cName)&&status.get(nowTask.cName)>=2){ //读取到下周队列，拒绝排课
				   continue;
			   }
			   boolean offsetFlag=true;
			   if(nowTask.tuplec.size()==2){ //初始位置设置完，开始执行位置检查
				   for(int x=0;x<2;x++){
					   if(nowTask.tuplec.get(x).day==day&&nowTask.tuplec.get(x).pos==pos)
						   offsetFlag=false;
				   }
				   if(offsetFlag) continue;
			   }
			   nowTeacher=tSchedule.get(nowTask.tName);
			   if(nowTeacher.table.get(week).get(day).get(pos).equals("空闲")){
				   nowTeacher.table.get(week).get(day).set(pos,cName);
				   sQ.remove(i);
				   if(nowTask.tuplec.size()<2){ //初始位置未设置完
					   nowTask.tuplec.add(new cPos(day,pos));
				   }
				   //修改次队链和教师计划
				   Q2.put(cName, sQ);
				   tSchedule.put(nowTask.tName, nowTeacher);
				   return nowTask;
			   }
		   }
		   nowTask=new cTask();
		   nowTask.hour=-1;
		   return nowTask;
	   }
	   public void changeCourseStatus(Map<String,Integer> status,String cName){
		   if(!status.containsKey(cName)) status.put(cName, 1);
		   else{
			   Integer x=status.get(cName);
			   status.put(cName, x+1);
		   }
	   }
	   public void weekNext(String cName,int week){ 
		   Map<String,Integer> courseStatus=new HashMap<String,Integer>();
		   cTable table3D=cSchedule.get(cName);
		   LinkedList  mQ=new LinkedList(),sQ=new LinkedList();
		   //整体策略：先处理次对列，如果次对列发现某门课课时用完，则不再添加，并且从主队列中取出一门新课
		   
		   for(int i=1;i<=table3D.dayNum;i++){
				   for(int j=1;j<=table3D.courseNum;j++){
					   cTask c=getCourseFromSubQ(week,i,j,cName,courseStatus);
					   sQ=Q2.get(cName);  //读取最新次链队
					   if(c.hour!=-1){  //排次课
						   c.hour--;
						   if(c.hour==0){  //课时为0,从主队链中取出新课，顶替当前位置，保持第一周的结构不乱
							   c.end=week;//添加结束周标记
							   if(!display.containsKey(cName)){ //对排完的课，移入显示队链
								   display.put(cName,new LinkedList());
								   display.get(cName).add(c);//
							   }else display.get(cName).add(c);
							   cTask x=getCourseFromMainQ(week+1,i,j,cName);//周定位在下一周
							   if(x.hour==-1) continue;
							   changeCourseStatus(courseStatus,x.cName);
							   changeCourseStatus(courseStatus,x.cName);
							   x.tuplec=c.tuplec; //课程位置不变
							   sQ.add(x);Q2.put(cName, sQ); //更新次链队
						   }
						   else{ //课时不为0，继续工作
							   sQ.add(c);Q2.put(cName, sQ);
							   changeCourseStatus(courseStatus,c.cName);
						   }
					   }
				   }
			   }
	   }
	   public void weekInit(String cName){    //第一周，初始化队列
		   cTable table3D=cSchedule.get(cName);
		   Vector<Vector<String>> table2D=table3D.table.get(1);
		   LinkedList  mQ=new LinkedList(),sQ=new LinkedList();
		   //初始化主链队
		   for(int i=0;i<task.get(cName).size();i++){ 
			   mQ.addLast(task.get(cName).get(i));
		   }
		   Q1.put(cName, mQ);
		   Map<String,Integer> courseStatus=new HashMap<String,Integer>();
		   //初始化第一周
		   for(int i=1;i<=table3D.dayNum;i++){
			   switch(i){
			   case 1:  //周一，三节新课
				   int cnt=0;
				   for(int j=1;j<=table3D.courseNum&&cnt<3;j++){
					   cTask c=getCourseFromMainQ(1,i,j,cName);
					   if(c.hour==-1) continue;
					   //mainFrame.mainTable.getModel().setValueAt(c.courseName, j-1, i-1);
					   c.hour--;cnt++;
					   if(c.tuplec.size()<2){c.tuplec.add(new cPos(i,j));System.out.printf("%d %d\n",i,j);}  
					   sQ.add(c);Q2.put(cName, sQ); //更新次链队
					   changeCourseStatus(courseStatus,c.cName);
				   }
				   break; 
			   case 2:   //周二，最多满新课
				   for(int j=1;j<=table3D.courseNum;j++){
					   Q2.get(cName);  //读取最新次链队
					   cTask c=getCourseFromMainQ(1,i,j,cName);
					   if(c.hour==-1) continue;
					   //mainFrame.mainTable.getModel().setValueAt(c.courseName, j-1, i-1);
					   c.hour--;
					   if(c.tuplec.size()<2){c.tuplec.add(new cPos(i,j));}  
					   sQ.add(c);Q2.put(cName, sQ);
					   changeCourseStatus(courseStatus,c.cName);
				   }
				   break;
			   case 3:   //周三，最多两节新课，两节次课
				   //先处理次课,后处理新课
				   int nCnt=0,oCnt=0;
				   for(int j=1;j<=table3D.courseNum;j++){
					   if(oCnt<2){  //拍次课
						   cTask c=getCourseFromSubQ(1,i,j,cName,courseStatus);
						   sQ=Q2.get(cName);  //读取最新次链队
						   if(c.hour!=-1){  //排次课
							   //mainFrame.mainTable.getModel().setValueAt(c.courseName, j-1, i-1);
							   c.hour--;oCnt++;
							   sQ.add(c);Q2.put(cName, sQ);
							   changeCourseStatus(courseStatus,c.cName);
							   continue;
						   }
					   }
					   else{  //排主课
						   if(nCnt<2){
							   cTask c=getCourseFromMainQ(1,i,j,cName);
							   if(c.hour==-1) continue;
							   //mainFrame.mainTable.getModel().setValueAt(c.courseName, j-1, i-1);
							   c.hour--;
							   if(c.tuplec.size()<2){c.tuplec.add(new cPos(i,j));System.out.printf("%d %d\n",i,j);} 
							   sQ.add(c);Q2.put(cName, sQ);
							   changeCourseStatus(courseStatus,c.cName);
						   }
					   }
				   }
				   break;
			   case 4://全是次课
				   for(int j=1;j<=table3D.courseNum;j++){
						   cTask c=getCourseFromSubQ(1,i,j,cName,courseStatus);
						   sQ=Q2.get(cName);  //读取最新次链队
						   if(c.hour!=-1){  //排次课
							   //mainFrame.mainTable.getModel().setValueAt(c.courseName, j-1, i-1);
							   c.hour--;
							   sQ.add(c);Q2.put(cName, sQ);
							   changeCourseStatus(courseStatus,c.cName);
						   }
					   }
				   break;
			   case 5: //全是次课
				   for(int j=1;j<=table3D.courseNum;j++){
					   cTask c=getCourseFromSubQ(1,i,j,cName,courseStatus);
					   sQ=Q2.get(cName);  //读取最新次链队
					   if(c.hour!=-1){  //排次课
						   //mainFrame.mainTable.getModel().setValueAt(c.courseName, j-1, i-1);
						   c.hour--;
						   sQ.add(c);Q2.put(cName, sQ);
						   changeCourseStatus(courseStatus,c.cName);
					   }
				   }
			   break;
		   }
		   }
	   }
	   public void loadTask(){
		   Sheet st=fileEditor.book.getSheetAt(0);
			for(int i=1;i<=st.getLastRowNum();i++)
			{
				Row row=st.getRow(i);
				if(row==null) continue;
				cTask task=new cTask();
				for(int j=0;j<row.getLastCellNum();j++){
					Cell cell=row.getCell(j);
					switch (j){
					case 0:  //课程ID
						task.cID=cell.getStringCellValue();
						break;
					case 1:  //课程Name
						task.cName=cell.getStringCellValue();
						break;
					case 2:  //专业
						task.major=cell.getStringCellValue();
						break;
					case 3:  //班级
						task.Class=cell.getStringCellValue();
						initClass(task.Class);
						break;
					case 4:  //理论课时
						task.hour=(int) cell.getNumericCellValue();
						task.hour/=2;
						break;
					case 5:  //实验课时
						task.praticedhour=(int) cell.getNumericCellValue();
						task.praticedhour/=2;
						break;
					case 6:  //教师ID
						task.tID=cell.getStringCellValue();
						break;
					case 7:  //教师姓名
						task.tName=cell.getStringCellValue();
						initTeacher(task.tName);
						break;
		            }
		        }
				if(!this.task.containsKey(task.Class)){
					this.task.put(task.Class, new Vector<cTask>());
					this.task.get(task.Class).add(task);
				}
				else this.task.get(task.Class).add(task);
			}
	   }
	   public void mergeDisplay(String className){
		   String str;
		   String str2=null;
		   LinkedList Q=display.get(className);
		   for(int i=0;i<Q.size();i++)
		   {
			   cTask c= (cTask)Q.get(i);
			   if(c.Class.equals("13-计科-1"))
			   {
				   for(int x=0;x<2;x++){
				   int day=c.tuplec.get(x).day-1,pos=c.tuplec.get(x).pos-1;
				   String pstr=mainFrame.mainTable.getModel().getValueAt(pos, day).toString();
				   if(i==0)
					   str2="新安学堂401";
				   else if(i>1 && i<10)
					   str2="新安学堂40"+i;
				   else if(i>9)
					   str2="新安学堂4"+i;
			   str=c.cName+"["+str2+"("+String.valueOf(c.start)+"-"
					   		+String.valueOf(c.end)+"周)"+"]";
				   mainFrame.mainTable.getModel().setValueAt
			   					(pstr.isEmpty()?"<html>"+str:pstr+"<br>"+str, pos,day);
			   }
			   }
			   if(c.Class.equals("13-计科-2"))
			   {
				   for(int x=0;x<2;x++){
				   int day=c.tuplec.get(x).day-1,pos=c.tuplec.get(x).pos-1;
				   String pstr=mainFrame.mainTable.getModel().getValueAt(pos, day).toString();
				   if(i==0)
					   str2="新安学堂401";
				   else if(i>1 && i<10)
					   str2="新安学堂40"+i;
				   else if(i>9)
					   str2="新安学堂4"+i;
			   str=c.cName+"["+str2+"("+String.valueOf(c.start)+"-"
					   		+String.valueOf(c.end)+"周)"+"]";
				   mainFrame.mainTable.getModel().setValueAt
			   					(pstr.isEmpty()?"<html>"+str:pstr+"<br>"+str, pos,day);
			   }		   
			   }
			   if(c.Class.equals("13-计科-3"))
			   {
				   for(int x=0;x<2;x++){
				   int day=c.tuplec.get(x).day-1,pos=c.tuplec.get(x).pos-1;
				   String pstr=mainFrame.mainTable.getModel().getValueAt(pos, day).toString();
				   if(i==0)
					   str2="新安学堂401";
				   else if(i>1 && i<10)
					   str2="新安学堂40"+i;
				   else if(i>9)
					   str2="新安学堂4"+i;
			   str=c.cName+"["+str2+"("+String.valueOf(c.start)+"-"
					   		+String.valueOf(c.end)+"周)"+"]";
				   mainFrame.mainTable.getModel().setValueAt
			   					(pstr.isEmpty()?"<html>"+str:pstr+"<br>"+str, pos,day);
				   }
			   }
		   }
	   }
	   
	   public void mergeNewDisplay(String className){
		   String str;
		   String str2=null;
		   LinkedList Q=display.get(className);
		   for(int i=0;i<Q.size();i++)
		   {
			   cTask c= (cTask)Q.get(i);
			   if(c.Class.equals("13-计科-1"))
			   {
				   for(int x=0;x<2;x++){
				   int day=c.tuplec.get(x).day-1,pos=c.tuplec.get(x).pos-1;
				   String pstr=mainFrame2.mainTable2.getModel().getValueAt(pos, day).toString();
				   if(i==0)
					   str2="新安学堂301";
				   else if(i>1 && i<10)
					   str2="新安学堂30"+i;
				   else if(i>9)
					   str2="新安学堂3"+i;
			   str=c.cName+"["+str2+"("+String.valueOf(c.start)+"-"
					   		+String.valueOf(c.end)+"周)"+"]";
				   mainFrame2.mainTable2.getModel().setValueAt
			   					(pstr.isEmpty()?"<html>"+str:pstr+"<br>"+str, pos,day);
			   }
			   }
			   if(c.Class.equals("13-计科-2"))
			   {
				   for(int x=0;x<2;x++){
				   int day=c.tuplec.get(x).day-1,pos=c.tuplec.get(x).pos-1;
				   String pstr=mainFrame2.mainTable2.getModel().getValueAt(pos, day).toString();
				   if(i==0)
					   str2="新安学堂301";
				   else if(i>1 && i<10)
					   str2="新安学堂30"+i;
				   else if(i>9)
					   str2="新安学堂3"+i;
			   str=c.cName+"["+str2+"("+String.valueOf(c.start)+"-"
					   		+String.valueOf(c.end)+"周)"+"]";
				   mainFrame2.mainTable2.getModel().setValueAt
			   					(pstr.isEmpty()?"<html>"+str:pstr+"<br>"+str, pos,day);
			   }		   
			   }
			   if(c.Class.equals("13-计科-3"))
			   {
				   for(int x=0;x<2;x++){
				   int day=c.tuplec.get(x).day-1,pos=c.tuplec.get(x).pos-1;
				   String pstr=mainFrame2.mainTable2.getModel().getValueAt(pos, day).toString();
				   if(i==0)
					   str2="新安学堂301";
				   else if(i>1 && i<10)
					   str2="新安学堂30"+i;
				   else if(i>9)
					   str2="新安学堂3"+i;
			   str=c.cName+"["+str2+"("+String.valueOf(c.start)+"-"
					   		+String.valueOf(c.end)+"周)"+"]";
				   mainFrame2.mainTable2.getModel().setValueAt
			   					(pstr.isEmpty()?"<html>"+str:pstr+"<br>"+str, pos,day);
				   }
			   }		 
		   }
	   }
	   
	   public void Serialize() throws IOException
	   {
		   if(!fileEditor.load("course.xlsx"))
		   {
			   fileEditor.create("course.xlsx");
			   fileEditor.load("course.xlsx");
		   }
	   }
	   public void initTeacher(String tName){
		   if(!tSchedule.containsKey(tName)){
			   tSchedule.put(tName, new tSchedule());
		   }
	   }
	   public void initClass(String cName){
		   if(!cSchedule.containsKey(cName)){
			   cSchedule.put(cName, new cTable());
		   }
	   }
	
	   public static void main(String args[]) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException{
			ArrangeCourse doc=new ArrangeCourse();
			DisplayS mainFrame=new DisplayS(doc);
			mainFrame.setResizable(false);
			doc.mainFrame=mainFrame;
			mainFrame.setTitle("智能排课系统-"+"13-计科-1"+"班-课表展示");
			//mainFrame.setTitle("智能排课系统-"+"13-计科-2"+"班-课表展示");
			//mainFrame.setTitle("智能排课系统-"+"13-计科-3"+"班-课表展示");
			
			JButton button = new JButton("\u8C03\u6574\u8BFE\u8868");
			button.setFont(new Font("宋体", Font.PLAIN, 12));
			button.setBounds(555, 27, 82, 25);
			mainFrame.getContentPane().add(button);
			button.addMouseListener(new MouseAdapter() {    // 对button按钮添加监听事件
			    public void mouseClicked(MouseEvent e) {    // 当鼠标点击时
			       ChangeWindow.main(args);
			       mainFrame.dispose();
			    }
			});
			doc.weekInit("13-计科-1");
			for(int i=2;i<22;i++) 
				doc.weekNext("13-计科-1", i);
		        doc.mergeDisplay("13-计科-1");
		        
			doc.weekInit("13-计科-2");
			for(int i=2;i<22;i++) 
				doc.weekNext("13-计科-2", i);
			  //doc.mergeDisplay("13-计科-2");
			
			doc.weekInit("13-计科-3");
			for(int i=2;i<22;i++) 
				doc.weekNext("13-计科-3", i);
			  //doc.mergeDisplay("13-计科-3");
			mainFrame.setVisible(true);
		}   
}




