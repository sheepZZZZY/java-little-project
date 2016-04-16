package projetc1;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 //测试poi读取excel文件内容
 
 */
public class ExcelFile {

        /**
         * @param args
         */
//成员变量

	//写入Excel内容，即修改 暂时不考虑插入的情况
	class StudentInfo{
		public int ID;				//学号
		public String Name;			//姓名
		public String Spec;			//专业
		public String Class;		//班级
		public int SC=0;				//Score_Course
		public int SH=0;				//Score_Homework
		public int SL=0;				//Score_Lab
		public int SP=0;				//Score_Project
		public int SE=0;				//Score_Exam	
		public int STotal=0;			//Score_TOTAL(SC+SH+SL+SP+SE)
		public boolean init=false;
		public void calcTotal()
		{
			this.STotal=this.SC+this.SH+this.SL+this.SP+this.SE;
		}
}
		Map<Integer,StudentInfo> stuTable=new HashMap<Integer,StudentInfo>();			//OUT : ScoreList
		//读取存放数组
		public String[] TablestuStr=new String[20];
		//输出存放数组
		public static  String[] Memory=new String[20];
		public int NumOfStu=0;
//写入文件
       public void writeExcel(String filename) throws FileNotFoundException
       {
    	   File file=new File(filename);
    	   FileOutputStream fOut=null;
    	   FileInputStream in=new FileInputStream(file);
    		  try{  
    			  HSSFWorkbook workbook=new HSSFWorkbook(in);
       		   HSSFSheet sheet=workbook.getSheetAt(0);
       		   //从记录的行号更新那一行
       		   int RowNum=Integer.parseInt(Memory[0]);
       		   int ColNum;
       		   //到当前行
       		   HSSFRow  row=sheet.getRow(RowNum);
         		   int lastCellNum=row.getLastCellNum();
       		   HSSFCell cell;
       		   for(ColNum=4;ColNum<lastCellNum;ColNum++){
       			   //获取当前位置单元格
       			   cell=row.getCell(ColNum);
       			switch (cell.getCellType()) {
       		   case HSSFCell.CELL_TYPE_NUMERIC: // 数字
       			   			double s=Double.parseDouble(Memory[ColNum]);
       			   			cell.setCellValue(s);
       			   		System.out.println("here1 "+s);
       			   			break;
       		   case HSSFCell.CELL_TYPE_STRING: // 字符串
       			             cell.setCellValue(Memory[ColNum]);
       			          System.out.println("here2 "+Memory[ColNum]);
       			          System.out.println("col:"+cell.getColumnIndex() + "row: "+cell.getRowIndex()+"value"+cell.getStringCellValue());
       			          break;
       		  default:
       			  System.out.println("here3");
       			            	 break;
             	}
       			  
       			  //cell.setCellValue(Memory[0]);
       			   //cell=cell.setCellValue();
       		   }
    		   //新建一个文件输出流
    		   fOut=new FileOutputStream(file);
    		   //把相应的excel工作簿存盘
    		   workbook.write(fOut);
    		   fOut.flush();
    		   fOut.close();//操作结束，关闭文件
    	   }catch(Exception e){
    		   System.out.println("生成失败"+e);
    	   }finally{
    		   if(fOut!=null){
    			   try{
    				   fOut.close();
    			   }catch(IOException e1){
    		 
    			   }
    		   }
    	   }
    		  }
       //读Excel文件   注意字符串以及文字编码的问题
       public void readExcel(String filename,String text)
       {
    	   File file=new File(filename);//参考
    	   FileInputStream in=null;
    	 //  HSSFWorkbook workbook=new HSSFWorkbook(FileUtils.)
    	   try{
    		   //创建Excel工作簿的引用
    		   in=new FileInputStream(file);
    		   HSSFWorkbook workbook=new HSSFWorkbook(in); //创建对工作表的引用
    		   HSSFSheet sheet=workbook.getSheetAt(0); //在Excel工作表中，第一个工作表的默认索引是0
    		  
    		   StudentInfo CurrentStu=new StudentInfo(); //新建一个学生表
    		   
    		 
    		   System.out.println("读取结果是"+file.getAbsolutePath()+"的内容");  //读取Excel
    		   HSSFRow row=null;
    		   HSSFCell cell=null;
    		   int rowNum=0;   //行 列
    		   int colNum=0;
    		   int CurrentStuID=0;
    		   //获取sheet中最后一行行号
    		   int lastRowNum=sheet.getLastRowNum();
    		   for(rowNum=1;rowNum<lastRowNum;rowNum++){
    			   //获取行
    			    row=sheet.getRow(rowNum);
    			   //获取当前行的第一列
    			   HSSFCell cell1=row.getCell(0);
    			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
					String str1=text;
					String s=cell1.getStringCellValue();
					
	    			   int lastCellNum=row.getLastCellNum();
	    			    if(str1.equals(s)){
	    			    	//记录下此时的行号
	    			    	TablestuStr[15]=String.valueOf(rowNum);
    				   System.out.println(rowNum);
    			    	 for(colNum=0;colNum<lastCellNum;colNum++){
     				   cell=row.getCell(colNum);
     					   switch (cell.getCellType())   {
        		                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
        		                	//第一列为学号内容，应该用字符串的方式输出
        		                	switch(colNum){
        		                	
        		                	   case 4:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	            CurrentStu.SC=(int)(cell.getNumericCellValue());
                                                   break;
        		                	   case 5:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	           CurrentStu.SH=(int)(cell.getNumericCellValue());
        		                	    	    	break;
        		                	   case 6:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	            CurrentStu.SL=(int)(cell.getNumericCellValue());
        		                	   			   break;
        		                	   case 7:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	             CurrentStu.SP=(int)(cell.getNumericCellValue());
        		                	              break;
        		                	   case 8:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	             CurrentStu.SE=(int)(cell.getNumericCellValue());
        		                	                break;
        		                	   case 9:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	              CurrentStu.calcTotal();
               		                            break;
        		                	  // case 10:System.out.printf("%.2f     ",cell.getNumericCellValue());
        		                	          //    break;
        		                	}
        		                	break;
        		             
        		                	
        		                case HSSFCell.CELL_TYPE_STRING: // 字符串
        		                	switch(colNum){
        		                	case 0:
        		                		     System.out.print(cell.getStringCellValue()  + "\t");
        		                	         CurrentStuID=Integer.valueOf(cell.getStringCellValue()).intValue();
        		                	        CurrentStu.ID=CurrentStuID;
        		                	        break;
        		                	case 1:System.out.print(cell.getStringCellValue()  + "\t");
        		                	           CurrentStu.Name=cell.getStringCellValue();
        		                	           break;
        		                	case 2:System.out.print(cell.getStringCellValue()  + "\t");
        		                	           CurrentStu.Spec=cell.getStringCellValue();
        		                	           break;
        		                	case 3:
        		                	        System.out.print(cell.getStringCellValue()  + "\t");
        		                	        CurrentStu.Class=cell.getStringCellValue();
        		                	        break;
        		                	}
        		                    break;
        		                default:
        		                    System.out.print("未知类型   ");
        		                    break;
        		            }
 					   }
     				  
     		        }
	    			    CurrentStu.init=true;
	    				stuTable.put(CurrentStuID,CurrentStu);
	    				NumOfStu++;
	    				TablestuStr[0]=String.valueOf(CurrentStu.ID);
	    				TablestuStr[1]=CurrentStu.Name;
	    				TablestuStr[2]=CurrentStu.Spec;
	    				TablestuStr[3]=CurrentStu.Class;
	    				TablestuStr[4]=String.valueOf(CurrentStu.SC);
	    				TablestuStr[5]=String.valueOf(CurrentStu.SH);
	    				TablestuStr[6]=String.valueOf(CurrentStu.SL);
	    				TablestuStr[7]=String.valueOf(CurrentStu.SP);
	    				TablestuStr[8]=String.valueOf(CurrentStu.SE);
	    				TablestuStr[9]=String.valueOf(CurrentStu.STotal);
    		}   
    		   in.close();
    	   }catch (Exception e){
    		   e.printStackTrace();
    	   }finally{
    		   if(in!=null){
    			   try{
    				   in.close();
    			   }catch(IOException e1){
    				   
    			   }
    		   }
    	   }
       }
}
