package com.bill99.ta.infs.fpd.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

import shelper.datadrive.DataDriven;
import shelper.environment.Environment;

/**
 * 多行数据驱动
 * 
 * @author hongzhi.zheng
 * 
 */
public class ExcelDateProvider implements Iterator<Object[]> {

	// excel绝对路径位置
	private String excel;
	// sheet名称
	private String sheetname;
	private FileInputStream fis;
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	// sheet的行数
	private int linenumber;
	// sheet列数
	private int rownumber = 0;
	// sheet行标
	private int numberindex = 0;
	// 列名数组
	private String rowname[];
	// 单行调试的行号
	private int singleline = 0;

	// 全表执行
	public ExcelDateProvider(Object aimob, String aimmathod) {
		// 设置环境变量
		// Environment.set();
		// 获取测试类得DataDriven注解。从中获得excelfile名称，sheet名称。
		getInfo(aimob, aimmathod);
		// 打开目标sheet获得sheet行数。
		openSheet();
		// 获得列名数组
		getRowname();
		// System.out.println(this.excel);
	}

	private void getRowname() {
		HSSFRow row = sheet.getRow(numberindex);
		while (row.getCell(this.rownumber) != null) {
			this.rownumber++;
		}
		rowname = new String[this.rownumber];
		for (int i = 0; i < this.rownumber; i++) {
			rowname[i] = StringUtils.remove(row.getCell(i).toString(), "\n");
		}
		numberindex++;
	}

	public boolean hasNext() {
		if (singleline == 0) {
			// 全表
			if (this.numberindex < this.linenumber) {
				return true;
			} else {
				try {
					fis.close();
				} catch (IOException ex) {
					Logger.getLogger(ExcelDateProvider.class.getName()).log(
							Level.SEVERE, null, ex);
				}
				return false;
			}
		} else {
			try {
				fis.close();
			} catch (IOException ex) {
				Logger.getLogger(ExcelDateProvider.class.getName()).log(
						Level.SEVERE, null, ex);
			}
			return false;
		}
	}

	public Object[] next() {
		Map<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		// HSSFRow row = sheet.getRow(this.numberindex);
		int MergedRegionSize = 1;
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			if (ca.getFirstColumn() == 0
					&& ca.getFirstRow() == this.numberindex) {
				MergedRegionSize = ca.getLastRow() - ca.getFirstRow() + 1;
				break;
			}
		}

		for (int i = 0; i < this.rownumber; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			if (MergedRegionSize > 1) {
				if (i == 0) {
					temp.add(getStrFromCell(this.numberindex, i));
				} else {
					for (int j = 0; j < MergedRegionSize; j++) {
						temp.add(getStrFromCell(this.numberindex + j, i));
					}
				}
			} else {
				temp.add(getStrFromCell(this.numberindex, i));
			}
			result.put(this.rowname[i], temp);
		}
		Object objresult[] = new Object[1];
		objresult[0] = result;
		this.numberindex = this.numberindex + MergedRegionSize;
		return objresult;

	}

	public void remove() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private String getStrFromCell(int i, int j) {
		String str = "";
		HSSFRow row = sheet.getRow(i);
		if (null != row.getCell(j)) {
			str = row.getCell(j).toString();
		}
		return str;
	}

	/**
	 * 获取测试类得DataDriven注解。从中获得excelfile名称，sheet名称。
	 * 
	 * @param aimob
	 *            测试用例对象
	 * @param aimmathod
	 *            测试方法名
	 * @throws IOException
	 */
	private void getInfo(Object aimob, String aimmathod) {
		DataDriven dd = (DataDriven) locateTestMethod(aimob, aimmathod)
				.getAnnotation(DataDriven.class);
		if (dd == null) {
			this.excel = modifydata(aimob, "auto");
			this.sheetname = modifysheet(aimmathod, "auto");
			modifyType("excel");
		} else {
			this.excel = modifydata(aimob, dd.excel());
			this.sheetname = modifysheet(aimmathod, dd.sheet());
			modifyType(dd.type());
		}
	}

	/**
	 * 打开目标sheet获得sheet行数。
	 */
	private void openSheet() {
		try {
			fis = new FileInputStream(this.excel);
			fs = new POIFSFileSystem(fis);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheet(this.sheetname);
			this.linenumber = sheet.getPhysicalNumberOfRows();
		} catch (java.io.IOException ex) {
			Logger.getLogger(ExcelDateProvider.class.getName()).log(
					Level.SEVERE, null, ex);
			try {
				fis.close();
			} catch (IOException ex1) {
				Logger.getLogger(ExcelDateProvider.class.getName()).log(
						Level.SEVERE, null, ex1);
			}
		}
	}

	private String modifysheet(String method, String sheetname) {
		if (sheetname.equals("auto")) {
			return method;
		} else {
			return sheetname;
		}
	}

	private String modifydata(Object o, String data) {
		try {
			if (data.equals("auto")) {
				String datadrivenRoot = Environment
						.get("Selenium.DatadrivenRoot");
				if (Environment.get("Datadriven.InLocalPorject") != null) {
					if (Environment.get("Datadriven.InLocalPorject")
							.equalsIgnoreCase("true")) {
						datadrivenRoot = new File(".").getCanonicalPath()
								+ "\\build\\data\\excel\\"
								+ System.getProperty("99bill.qa.ta.stage_id")
								+ "\\";
					}
				}
				String filename = datadrivenRoot
						+ o.getClass().getName().replaceAll("\\.", "/")
						+ ".xls";
				// System.out.println(filename);
				return filename;
			}
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Method locateTestMethod(Object objectname, String methodname) {
		try {
			Method[] arrayOfMethod1 = objectname.getClass()
					.getDeclaredMethods();
			Method[] arrayOfMethod2 = arrayOfMethod1;
			int j = arrayOfMethod2.length;
			int k = 0;
			while (k <= j) {
				Method localMethod = arrayOfMethod2[k];
				System.out.println(localMethod);
				if (localMethod.getName().equals(methodname)) {
					return localMethod;
				}
				++k;
			}
		} catch (Throwable ex) {
			Logger.getLogger(ExcelDateProvider.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return null;
	}

	// 预留
	private String modifyType(String type) {
		return type;
	}
}
