package com.practice.knowledge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.dto.DataDto;

public class WriteXMLFile {

	public static void main(String[] args) {

		try {

			int counter = 0;
			int fileCounter = 0;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// Reading data using readLine
			System.out.println("Please enter file (with extension eg. abc.xlsx)");
			String fileName = reader.readLine();
			String path = "C:\\Users\\monicsingh\\Desktop\\POC\\";

			// SIT
			// String path="/home/legalusr/LegalAsynchApplication/ReadExcel/file/";
			System.out.println("FileName>>" + fileName);
			System.out.println("Path>>" + path);

			// Read from properties
			// FileReader readerfile=new
			// FileReader("/lglapp/ReadExcel/application.properties");
			// FileReader readerfile=new
			// FileReader("/lglapp/ReadExcel/"+"application.properties");
			FileReader readerfile = new FileReader("C:\\Users\\monicsingh\\Desktop\\POC\\" + "application.properties");
			//C:\Users\monicsingh\Desktop\POC

			Properties p = new Properties();
			p.load(readerfile);

			counter = Integer.parseInt(p.getProperty("recId_Req_id_counter"));
			fileCounter = Integer.parseInt(p.getProperty("File_counter"));
			System.out.println(p.getProperty("recId_Req_id_counter"));

			// Read from properties end

			ArrayList<DataDto> datalist = new ArrayList<>();
			// CsvToXml date=new CsvToXml();
			try {
				File file = new File(path + fileName); // creating a new file instance

				// POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
				XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));

				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;

				int rows; // No of rows
				rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Excel no. of rows" + rows);

				int cols = 0; // No of columns
				int tmp = 0;

				// This trick ensures that we get the data properly even if it doesn't start
				// from first few rows
				for (int i = 0; i < 10 || i < rows; i++) {
					row = sheet.getRow(i);
					if (row != null) {
						tmp = sheet.getRow(i).getPhysicalNumberOfCells();
						if (tmp > cols)
							cols = tmp;
					}
				}

				for (int r = 1; r < rows; r++) {
					row = sheet.getRow(r);
					if (row != null) {
						// AsynchDao dao=new AsynchDao();
						DataDto datadato = new DataDto();

						cell = row.getCell((short) 0);
						// datadato.setRequestType(cell.getRawValue().toString().trim());
						datadato.setRequestType(cell.toString().trim());

						cell = row.getCell((short) 1);
						datadato.setSuspectNumber(cell.getRawValue().toString().trim());

						cell = row.getCell((short) 2);
						datadato.setSuspectNumType(cell.toString().trim());

						cell = row.getCell((short) 3);
						datadato.setSuspectNumPattern(cell.toString().trim());

						cell = row.getCell((short) 4);
						datadato.setCircle(cell.toString().trim());
						
						cell = row.getCell((short) 5);
						datadato.setRequestTime(cell.toString().trim());
						
						cell = row.getCell((short) 6);
						datadato.setStartDate(cell.toString().trim());
						
						cell = row.getCell((short) 7);
						datadato.setEndDate(cell.toString().trim());
						
						cell = row.getCell((short) 8);
						datadato.setServiceType(cell.toString().trim());
						cell = row.getCell((short) 9);
						datadato.setDataType(cell.toString().trim());
						cell = row.getCell((short) 10);
						datadato.setPriority(cell.toString().trim());

						cell = row.getCell((short) 11);
						datadato.setRemarks(cell.toString().trim());
						cell = row.getCell((short) 12);
						datadato.setRespFileType(cell.toString().trim());
						cell = row.getCell((short) 13);
						datadato.setRespFileName(cell.toString().trim());
						cell = row.getCell((short) 14);
						datadato.setRespFileHeader(cell.toString().trim());
						
						cell = row.getCell((short) 15);
						datadato.setRespFileMode(cell.toString().trim());
						cell = row.getCell((short) 16);
						datadato.setRespFilePath(cell.toString().trim());
						
						cell = row.getCell((short) 17);
						datadato.setRespSecretKey(cell.toString().trim());
						
						cell = row.getCell((short) 18);
						datadato.setRespInitializationVector(cell.toString().trim());

						/*
						 * cell = row.getCell((short)3);
						 * datadato.setTillDate(date.getTillStringDate(cell.toString().trim()));
						 */

						datalist.add(datadato);

						datadato = null;
					}
				}
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}

			for (int i = 0; i < datalist.size(); i++) {
				String requestType = datalist.get(i).getRequestType();
				String SuspectNumber = datalist.get(i).getSuspectNumber();
				String SuspectNumType = datalist.get(i).getSuspectNumType();
				String SuspectNumPattern = datalist.get(i).getSuspectNumPattern();
				String Circle = datalist.get(i).getCircle();
				String RequestTime = datalist.get(i).getRequestTime();
				String StartDate = datalist.get(i).getStartDate();
				String EndDate = datalist.get(i).getEndDate();
				String ServiceType = datalist.get(i).getServiceType();
				String DataType = datalist.get(i).getDataType();
				String Priority = datalist.get(i).getPriority();
				String Remarks = datalist.get(i).getRemarks();
				String RespFileType = datalist.get(i).getRespFileType();
				String RespFileName = datalist.get(i).getRespFileName();
				String RespFileHeader = datalist.get(i).getRespFileHeader();
				String RespFileMode = datalist.get(i).getRespFileMode();
				String RespFilePath = datalist.get(i).getRespFilePath();
				String RespSecretKey = datalist.get(i).getRespSecretKey();
				String RespInitializationVector = datalist.get(i).getRespInitializationVector();

				// System.out.println(":requestType:"+requestType);
				// System.out.println(":SuspectNumber:"+SuspectNumber);
				/*
				 * System.out.println("RespFilePath :"+RespFilePath);
				 * System.out.println("RespSecretKey : "+RespSecretKey);
				 */

				// }

//==========================================================================

				Namespace tns = Namespace.getNamespace("tns", "http://www.example.org/ReqFileFormat2");
				Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
				
				  Namespace schemaLocation = Namespace.getNamespace("schemaLocation",
				  "http://www.example.org/ReqFileFormat2 ../ReqFileFormat2.xsd");
				 

				
				Element Request = new Element("Request",tns);
				Document doc = new Document(Request);
				doc.setRootElement(Request);
				
				Request.addNamespaceDeclaration(xsi);
			//	System.out.println(Request.getAdditionalNamespaces());
			//	Request.addNamespaceDeclaration(schemaLocation);
				Request.setAttribute("schemaLocation","http://www.example.org/ReqFileFormat2 ../ReqFileFormat2.xsd ");
			//	Request.setAttribute("xsi","schemaLocation",schemaLocation);
				
				
				Element Suspect = new Element("Suspect");
				Suspect.addContent(new Element("SuspectNumber").setText(SuspectNumber));// 9711777218
				Suspect.addContent(new Element("SuspectNumType").setText(SuspectNumType));// MSISDN
				Suspect.addContent(new Element("SuspectNumPattern").setText(SuspectNumPattern));
				Suspect.addContent(new Element("Circle").setText(Circle));
				
				Element RequestEntity = new Element("RequestEntity");
				RequestEntity.addContent(Suspect);
				
				
				Element RequestDetails = new Element("RequestDetails");
				// RequestDetails.setAttribute(new Attribute("id", "1"));
				RequestDetails.addContent(new Element("RequestType").setText(requestType)); // CDR_SUSPECT
				RequestDetails.addContent(new Element("RequestTime").setText(RequestTime));
				RequestDetails.addContent(new Element("StartDate").setText(StartDate));
				RequestDetails.addContent(new Element("EndDate").setText(EndDate));
				RequestDetails.addContent(new Element("ServiceType").setText(ServiceType));
				RequestDetails.addContent(new Element("DataType").setText(DataType));
				RequestDetails.addContent(new Element("Priority").setText(Priority));
				RequestDetails.addContent(new Element("Remarks").setText(Remarks));
				
				RequestDetails.addContent(RequestEntity);

				doc.getRootElement().addContent(RequestDetails);

				
				/*
				 * Element Suspect = new Element("Suspect"); Suspect.addContent(new
				 * Element("SuspectNumber").setText(SuspectNumber));// 9711777218
				 * Suspect.addContent(new Element("SuspectNumType").setText(SuspectNumType));//
				 * MSISDN Suspect.addContent(new
				 * Element("SuspectNumPattern").setText(SuspectNumPattern));
				 * Suspect.addContent(new Element("Circle").setText(Circle));
				 */
				
				/* Element RequestEntity = new Element("RequestEntity"); */
				
			//	RequestEntity.addContent(Suspect);
			//	doc.getRootElement().addContent(RequestEntity);

				//-------------------------------------------Below This is Fine------------------
				
				Element RespFileProp = new Element("RespFileProp");
				// RespFileProp.setAttribute(new Attribute("id", "2"));
				RespFileProp.addContent(new Element("RespFileType").setText(RespFileType));
				RespFileProp.addContent(new Element("RespFileName").setText(RespFileName));
				RespFileProp.addContent(new Element("RespFileHeader").setText(RespFileHeader));
				RespFileProp.addContent(new Element("RespFileMode").setText(RespFileMode));
				RespFileProp.addContent(new Element("RespFilePath").setText(RespFilePath));
				RespFileProp.addContent(new Element("RespSecretKey").setText(RespSecretKey));
				RespFileProp.addContent(new Element("RespInitializationVector").setText(RespInitializationVector));

				doc.getRootElement().addContent(RespFileProp);

				// new XMLOutputter().output(doc, System.out);
				XMLOutputter xmlOutput = new XMLOutputter();

				// display nice nice
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc,
						new FileWriter("C:\\Users\\monicsingh\\Desktop\\POC\\Generated_Files\\RQ110000PT0" + i + ".xml"));
				System.out.println("File Name : RQ110000PT0" + i + ".xml");
			} // end of for loop
			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
