package com.framework.test;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class PoiTest {
    static Map<String, Object> fm = new HashMap<>();
    static List<Map<String, Object>> jybg = new ArrayList<>(1);

    static {
        fm.put("XM", "zxc");
        fm.put("XB", "男");
        fm.put("NL", "11");
        fm.put("TMH", "030930");
        fm.put("SYH", "1");
        fm.put("KB", "微创骨科病区");
        fm.put("CH", "604-11");
        fm.put("ZY", "27973");
        fm.put("TJH", "");
        fm.put("ZJHM", "");
        fm.put("DH", "");
        fm.put("TJLX", "");
        fm.put("TJRQ", "");
        fm.put("DWALL", "");

    }

    public static void main(String[] args) throws Exception {
        XWPFDocument doc = new XWPFDocument();// 创建Word文件
        jybg("",doc);

        FileOutputStream out = new FileOutputStream("d:\\sample.docx");
        doc.write(out);
        out.close();

    }

    // 检验报告
    private static void jybg(String id, XWPFDocument document) throws Exception {
//        Map<String, Object> fm = grTjbgService.getFm(id);
//        List<Map<String, Object>> jybg = grTjbgService.getJybg(id);
        // 标题
        XWPFParagraph p = document.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        p.setSpacingAfter(100);
        XWPFRun r = p.createRun();
        r.setText("吉林省吉林中西医结合医院检验报告单");
        r.setFontSize(18);
        r.setBold(true);
        //
        XWPFParagraph p1 = document.createParagraph();// 新建一个段落
        p1.setAlignment(ParagraphAlignment.CENTER);// 设置段落的对齐方式
        XWPFRun r1 = p1.createRun();// 创建段落文本
        r1.setText("BVCA-I");

        // 项目信息
        XWPFParagraph p2 = document.createParagraph();
        p2.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun r2 = p2.createRun();
        //jybg.get(0);
        r2.setText("【检验项目】：" + "输血科");
        r2.setBold(true);

        // 个人信息
        XWPFTable table = document.createTable(4, 8);
        // 表格宽度
        setTableWidth(table, 10460);
        // 跨列合并单元格
        mergeCellsHorizontal(table, 2, 3, 5);
        mergeCellsHorizontal(table, 3, 2, 7);
        // mergeCellsHorizontal(table, 4, 0, 7);
        // 单元格宽度
        setCellWidth(table.getRow(0).getCell(0), "1500");
        setCellWidth(table.getRow(0).getCell(1), "1500");
        setCellWidth(table.getRow(0).getCell(2), "800");
        setCellWidth(table.getRow(0).getCell(3), "1500");
        setCellWidth(table.getRow(0).getCell(4), "1100");
        setCellWidth(table.getRow(0).getCell(5), "1550");
        setCellWidth(table.getRow(0).getCell(6), "1200");
        setCellWidth(table.getRow(0).getCell(7), "1850");

        for (XWPFTableRow row : table.getRows()) {
            row.setHeight(500);
            List<XWPFTableCell> cells = row.getTableCells();
            // 设置文字居中
            for (XWPFTableCell cell : cells) {
                setCellVAlign(cell, null, STVerticalJc.CENTER);
            }
        }

        paragraphFontBold(table.getRow(0).getCell(0), "姓名：");
        table.getRow(0).getCell(1).setText((String) fm.get("XM"));
        paragraphFontBold(table.getRow(0).getCell(2), "性别：");
        table.getRow(0).getCell(3).setText((String) fm.get("XB"));
        paragraphFontBold(table.getRow(0).getCell(4), "年龄：");
        table.getRow(0).getCell(5).setText((String) fm.get("NL") + "岁");
        paragraphFontBold(table.getRow(0).getCell(6), "实验号：");
        table.getRow(0).getCell(7).setText((String) fm.get("SYH"));
        paragraphFontBold(table.getRow(1).getCell(0), "科别：");
        table.getRow(1).getCell(1).setText((String) fm.get("KB"));
        paragraphFontBold(table.getRow(1).getCell(2), "床号：");
        table.getRow(1).getCell(3).setText((String) fm.get("CH"));
        paragraphFontBold(table.getRow(1).getCell(4), "住院/门诊");
        table.getRow(1).getCell(5).setText((String) fm.get("ZYMZ"));
        paragraphFontBold(table.getRow(1).getCell(6), "条码号：");
        table.getRow(1).getCell(7).setText((String) fm.get("TMH"));
        paragraphFontBold(table.getRow(2).getCell(0), "病人类型：");
        table.getRow(2).getCell(1).setText((String) fm.get("BRLX"));
        paragraphFontBold(table.getRow(2).getCell(2), "标本：");
        table.getRow(2).getCell(3).setText((String) fm.get("BB"));
        paragraphFontBold(table.getRow(2).getCell(6), "采样时间：");
        table.getRow(2).getCell(7).setText((String) fm.get("CYSJ"));

        paragraphFontBold(table.getRow(3).getCell(0), "医生：");
        table.getRow(3).getCell(1).setText((String) fm.get("YS"));
        paragraphFontBold(table.getRow(3).getCell(2), "临床判断：");
        table.getRow(3).getCell(3).setText((String) fm.get("CYSJ"));
        // 去边框
        removeTableBorders(table);
        document.createParagraph();

        // 项目详细
        XWPFParagraph p3 = null;
        //p3 = setTjbgStyleThree(jybg, document);

        // 体检登记账号
        XWPFParagraph p4 = document.createParagraph();
        p4.setSpacingAfterLines(50);
        p4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r4 = p4.createRun();
        r4.setText((String) fm.get("TJBJDH"));

        XWPFParagraph p41 = document.createParagraph();
        p41.setSpacingAfterLines(50);
        p41.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun r41 = p41.createRun();
        r41.setText("2020年9月29日15:02:35");



        // 插入图片
//        List<Map<String, Object>> picList = new ArrayList<>();// grTjbgService.getPictureUrl(id);
//
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("FILEURL", "test/00007310.jpg");
//        picList.add(m);
//
//        //FTPUtils ftp = null;
//        ByteArrayInputStream file = null;
//        for (Map<String, Object> map : picList) {
//            String url = (String) map.get("FILEURL");
//            url = url.replaceAll("\\\\", "/");
//            String filename = url.substring(url.lastIndexOf("/") + 1);
//            String pathname = url.substring(0, url.lastIndexOf("/") + 1);
//
//            //ftp = ServletUtils.getBean("ggftp", FTPUtils.class);
//            //file = ftp.downloadFile(pathname, filename);
//            try {
//                XWPFParagraph image = document.createParagraph();
//                image.setPageBreak(true);
//                XWPFRun imageRun = image.createRun();
//                imageRun.addPicture(file, XWPFDocument.PICTURE_TYPE_JPEG, "jpeg", Units.toEMU(520), Units.toEMU(240));
//
//            } catch (Exception e) {
//                throw new WebException(map.get("FILEURL") + "图片丢失！");
//            } finally {
//                if (file != null) {
//                    file.close();
//                }
//            }
//        }

        // 影像描述
//		XWPFParagraph p3 = table.getRow(4).getCell(0).getParagraphs().get(0);
//		XWPFParagraph p11 = table.getRow(5).getCell(0).getParagraphs().get(0);
//		setzdbgData(p1, "报告结果：", p11, "ok");
        // 本次体检汇总
        // if (tjhz.size() > 0) {
        document.createParagraph();
        XWPFTable table2 = document.createTable(4, 8);
        setTableWidth(table2, 10460);
        table2.getRow(0).setHeight(500);
        table2.setCellMargins(0, 0, 0, 0);
        // 跨列合并单元格
        mergeCellsHorizontal(table2, 0, 1, 7);
        mergeCellsHorizontal(table2, 1, 1, 7);
        mergeCellsHorizontal(table2, 2, 1, 7);
        //mergeCellsHorizontal(table2, 3, 1, 3);
        removeTableBorders(table2);
        // 单元格宽度
        setCellWidth(table2.getRow(3).getCell(0), "1500");
        setCellWidth(table2.getRow(3).getCell(1), "1300");
        setCellWidth(table2.getRow(3).getCell(2), "900");
        setCellWidth(table2.getRow(3).getCell(3), "1300");
        setCellWidth(table2.getRow(3).getCell(4), "850");
        setCellWidth(table2.getRow(3).getCell(5), "1000");
        setCellWidth(table2.getRow(3).getCell(6), "850");
        setCellWidth(table2.getRow(3).getCell(7), "1000");

        XWPFParagraph p5 = table2.getRow(0).getCell(0).getParagraphs().get(0);
        XWPFParagraph p51 = table2.getRow(0).getCell(1).getParagraphs().get(0);
        // 标题
        XWPFRun run5 = p5.createRun();
        p5.setBorderTop(Borders.THICK);
        run5.setText("  报告结果：    ");
        // 内容
        //p51.setIndentationLeft(500);
        XWPFRun r51 = p51.createRun();
        p51.setBorderTop(Borders.THICK);
        autoBreak(r51, "报告结果内容");


        XWPFParagraph p6 = table2.getRow(1).getCell(0).getParagraphs().get(0);
        XWPFParagraph p61 = table2.getRow(1).getCell(1).getParagraphs().get(0);
        // 标题
        XWPFRun run6 = p6.createRun();
        p6.setBorderTop(Borders.THICK);
        run6.setText("      备注：");
        // 内容
        //p6.setIndentationLeft(200);
        XWPFRun r61 = p61.createRun();
        p61.setBorderTop(Borders.THICK);
        autoBreak(r61, "备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容备注内容");


        XWPFParagraph p7 = table2.getRow(2).getCell(0).getParagraphs().get(0);
        XWPFParagraph p71 = table2.getRow(2).getCell(1).getParagraphs().get(0);
        // 标题
        XWPFRun run7 = p7.createRun();
        p7.setBorderTop(Borders.THICK);
        run7.setText("      实验室提示：");
        // 内容

        //p7.setIndentationLeft(200);
        XWPFRun r71 = p71.createRun();
        p71.setBorderTop(Borders.THICK);
        autoBreak(r71, "实验室提示内容");


        XWPFParagraph p8 = table2.getRow(3).getCell(0).getParagraphs().get(0);
        XWPFParagraph p81 = table2.getRow(3).getCell(1).getParagraphs().get(0);
        XWPFParagraph p82 = table2.getRow(3).getCell(2).getParagraphs().get(0);
        XWPFParagraph p83 = table2.getRow(3).getCell(3).getParagraphs().get(0);
        XWPFParagraph p84 = table2.getRow(3).getCell(4).getParagraphs().get(0);
        XWPFParagraph p85 = table2.getRow(3).getCell(5).getParagraphs().get(0);
        XWPFParagraph p86 = table2.getRow(3).getCell(6).getParagraphs().get(0);
        XWPFParagraph p87 = table2.getRow(3).getCell(7).getParagraphs().get(0);

        p8.setBorderTop(Borders.THICK);
        XWPFRun r8 = p8.createRun();
        r8.setText("接收时间：");
        r8.setBold(true);

        XWPFRun r81 = p81.createRun();
        p81.setBorderTop(Borders.THICK);
        autoBreak(r81, "2020-9-29 21:21");

        XWPFRun r82 = p82.createRun();
        p82.setBorderTop(Borders.THICK);
        r82.setText("报告时间：");
        r82.setBold(true);

        XWPFRun r83 = p83.createRun();
        p83.setBorderTop(Borders.THICK);
        autoBreak(r83, "2020-9-24 21:21");

        XWPFRun r84 = p84.createRun();
        p84.setBorderTop(Borders.THICK);
        r84.setText("检验者：");
        r84.setBold(true);

        XWPFRun r85 = p85.createRun();
        p85.setBorderTop(Borders.THICK);
        autoBreak(r85, "赤心忠胆");

        XWPFRun r86 = p86.createRun();
        p86.setBorderTop(Borders.THICK);
        r86.setText("审核者：");
        r86.setBold(true);

        XWPFRun r87 = p87.createRun();
        p87.setBorderTop(Borders.THICK);
        autoBreak(r87, "学了启动");
    }

    // 体检报告样式三（五列版 ）（序号、项目名称、测定值、单位、参考范围）
    private static XWPFParagraph setTjbgStyleThree(List<Map<String, Object>> list, XWPFDocument document) throws Exception {
        int siez = 1;
        XWPFTable table = document.createTable(list.size() + siez, 5);
        table.getRow(0).setRepeatHeader(true);
        table.setCellMargins(0, 0, 0, 0);
        removeTableBorders(table);
        setTableWidth(table, 10460);
        if (list.size() > 0) {
            setCellWidth(table.getRow(2).getCell(0), "1500");
            setCellWidth(table.getRow(2).getCell(1), "3500");
            setCellWidth(table.getRow(2).getCell(2), "2000");
            setCellWidth(table.getRow(2).getCell(3), "1800");
            setCellWidth(table.getRow(2).getCell(4), "2000");
        }

        //mergeCellsHorizontal(table, 0, 0, 4);
        for (int j = 0; j < 1; j++) {
            for (XWPFTableCell cell : table.getRow(j).getTableCells()) {
                setCellBorders(cell, null, null, STBorder.THICK, null);
            }
        }

        List<XWPFTableCell> cells = table.getRow(1).getTableCells();
        for (XWPFTableCell cell : cells) {
            setCellVAlign(cell, STJc.CENTER, STVerticalJc.CENTER);
        }

        XWPFParagraph p = table.getRow(0).getCell(0).getParagraphs().get(0);
        p.setVerticalAlignment(TextAlignment.CENTER);
        p.setSpacingAfterLines(10);
        p.setSpacingBeforeLines(10);

        paragraphFontBold(table.getRow(0).getCell(0), "序号");
        paragraphFontBold(table.getRow(0).getCell(1), "项目名称");
        paragraphFontBold(table.getRow(0).getCell(2), "测定值");
        paragraphFontBold(table.getRow(0).getCell(3), "单位");
        paragraphFontBold(table.getRow(0).getCell(4), "参考范围");

        for (int k = 0; k < list.size(); k++) {
            Map<String, Object> lcxm = list.get(k);
            table.getRow(siez + k).setCantSplitRow(true);
            XWPFRun run = setCellVAlign(table.getRow(siez + k).getCell(1), STJc.LEFT, STVerticalJc.TOP).getParagraphs()
                    .get(0).createRun();
            run.setText((String) lcxm.get("TJMXXMMC"));
            setCellVAlign(table.getRow(siez + k).getCell(0), STJc.CENTER, STVerticalJc.CENTER);
            autoBreak(table.getRow(siez + k).getCell(0).getParagraphs().get(0).createRun(), lcxm.get("ROWNUM") + "");

            // setCellVAlign(table.getRow(2+k).getCell(0), STJc.LEFT,
            // STVerticalJc.TOP).setText((String)lcxm.get("XMMC"));

            setCellVAlign(table.getRow(siez + k).getCell(2), STJc.CENTER, STVerticalJc.CENTER)
                    .setText((String) lcxm.get("MXXMJG"));
            setCellVAlign(table.getRow(siez + k).getCell(3), STJc.CENTER, STVerticalJc.CENTER)
                    .setText((String) lcxm.get("JGDW"));
            setCellVAlign(table.getRow(siez + k).getCell(4), STJc.CENTER, STVerticalJc.CENTER)
                    .setText((String) lcxm.get("CASE"));
            if (k == list.size() - 1) {
                for (XWPFTableCell cell : table.getRow(1 + k).getTableCells()) {
                    setCellBorders(cell, null, null, STBorder.THICK, null);
                }
            }
        }
        return p;
    }
    // 表格总宽度
    private static void setTableWidth(XWPFTable table, Integer width) {
        CTTblPr tablePr = table.getCTTbl().getTblPr();
        tablePr.getTblW().setType(STTblWidth.DXA);
        tablePr.getTblW().setW(new BigInteger(String.valueOf(width)));
//		 固定列宽
        CTTblLayoutType t = tablePr.isSetTblLayout() ? tablePr.getTblLayout() : tablePr.addNewTblLayout();
        t.setType(STTblLayoutType.FIXED);
    }

    // 单元格宽度
    private static void setCellWidth(XWPFTableCell cell, String width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        CTTblWidth tcw = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
        if (width != null) {
            tcw.setType(STTblWidth.DXA);
            tcw.setW(new BigInteger(width));
        }
    }

    // 单元格数据位置
    private static XWPFTableCell setCellVAlign(XWPFTableCell cell, STJc.Enum hAlign, STVerticalJc.Enum vAlign) {
        CTTc cttc = cell.getCTTc();
        if (vAlign != null) {
            CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
            CTVerticalJc vJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr.addNewVAlign();
            vJc.setVal(vAlign);
        }
        if (hAlign != null) {
            CTP ctp = cttc.getPList().get(0);
            CTPPr ctppr = ctp.getPPr();
            if (ctppr == null) {
                ctppr = ctp.addNewPPr();
            }
            CTJc ctjc = ctppr.getJc();
            if (ctjc == null) {
                ctjc = ctppr.addNewJc();
            }
            ctjc.setVal(hAlign);
        }
        return cell;
    }

    // 设置段落背景色
    private void setParagraphBgColor(XWPFRun run, XWPFParagraph p, String color) {
        CTPPr pPpr = null;
        if (run.getCTR() != null) {
            pPpr = p.getCTP().getPPr();
            if (pPpr == null) {
                pPpr = p.getCTP().addNewPPr();
            }
        }
        CTShd cTShd = pPpr.isSetShd() ? pPpr.getShd() : pPpr.addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill(color);
    }

    // 移除表格边框
    private static void removeTableBorders(XWPFTable table) throws Exception {
        CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
        CTBorder hBorder = borders.addNewInsideH();
        hBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder vBorder = borders.addNewInsideV();
        vBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder lBorder = borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder rBorder = borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder tBorder = borders.addNewTop();
        tBorder.setVal(STBorder.Enum.forString("none"));
        CTBorder bBorder = borders.addNewBottom();
        bBorder.setVal(STBorder.Enum.forString("none"));
    }

    // 设置单元格边框线
    private static void setCellBorders(XWPFTableCell cell, STBorder.Enum top, STBorder.Enum left, STBorder.Enum bottom,
                                       STBorder.Enum right) {
        CTTcBorders tblBordersImage = cell.getCTTc().addNewTcPr().addNewTcBorders();
        if (top != null)
            tblBordersImage.addNewTop().setVal(top);
        if (left != null)
            tblBordersImage.addNewLeft().setVal(left);
        if (bottom != null)
            tblBordersImage.addNewBottom().setVal(bottom);
        if (right != null)
            tblBordersImage.addNewRight().setVal(right);
    }

    // 体检报告样式一（五列版）
    private XWPFParagraph setTjbgStyleOne(List<Map<String, Object>> list, XWPFDocument document) throws Exception {
        XWPFTable table = document.createTable(list.size() + 2, 5);
        table.getRow(0).setRepeatHeader(true);
        table.getRow(1).setRepeatHeader(true);
        table.setCellMargins(0, 0, 0, 0);
        removeTableBorders(table);
        setTableWidth(table, 10460);
        if (list.size() > 0) {
            setCellWidth(table.getRow(2).getCell(0), "2800");
            setCellWidth(table.getRow(2).getCell(1), "1800");
            setCellWidth(table.getRow(2).getCell(2), "1800");
            setCellWidth(table.getRow(2).getCell(3), "1800");
            setCellWidth(table.getRow(2).getCell(4), "1800");
        }

        mergeCellsHorizontal(table, 0, 0, 4);
        for (int j = 0; j < 2; j++) {
            for (XWPFTableCell cell : table.getRow(j).getTableCells()) {
                setCellBorders(cell, null, null, STBorder.THICK, null);
            }
        }

        List<XWPFTableCell> cells = table.getRow(1).getTableCells();
        for (XWPFTableCell cell : cells) {
            setCellVAlign(cell, STJc.CENTER, STVerticalJc.CENTER);
        }

        XWPFParagraph p = table.getRow(0).getCell(0).getParagraphs().get(0);
        p.setVerticalAlignment(TextAlignment.CENTER);
        p.setSpacingAfterLines(10);
        p.setSpacingBeforeLines(10);

        paragraphFontBold(table.getRow(1).getCell(0), "项目名称");
        paragraphFontBold(table.getRow(1).getCell(1), "检查结果");
        paragraphFontBold(table.getRow(1).getCell(2), "单位");
        paragraphFontBold(table.getRow(1).getCell(3), "参考范围");
        paragraphFontBold(table.getRow(1).getCell(4), "结果提示");

        for (int k = 0; k < list.size(); k++) {
            Map<String, Object> lcxm = list.get(k);
            table.getRow(2 + k).setCantSplitRow(true);
            XWPFRun run = setCellVAlign(table.getRow(2 + k).getCell(0), STJc.LEFT, STVerticalJc.TOP).getParagraphs().get(0).createRun();
            String xmmc = (String) lcxm.get("XMMC");
            if (xmmc != null && xmmc.indexOf("★") > -1) {
                run.getCTR().getSymList().add(getCTSym("Wingdings 2", "F0" + Integer.toHexString(234)));
                run.setText(xmmc.substring(xmmc.indexOf("★") + 1));
            } else {
                run.setText(xmmc);
            }
            //setCellVAlign(table.getRow(2+k).getCell(0), STJc.LEFT, STVerticalJc.TOP).setText((String)lcxm.get("XMMC"));
            setCellVAlign(table.getRow(2 + k).getCell(1), STJc.LEFT, STVerticalJc.CENTER);
            autoBreak(table.getRow(2 + k).getCell(1).getParagraphs().get(0).createRun(), (String) lcxm.get("JCJG"));
            setCellVAlign(table.getRow(2 + k).getCell(2), STJc.CENTER, STVerticalJc.CENTER).setText((String) lcxm.get("DW"));
            setCellVAlign(table.getRow(2 + k).getCell(3), STJc.CENTER, STVerticalJc.CENTER).setText((String) lcxm.get("CKFW"));
            setCellVAlign(table.getRow(2 + k).getCell(4), STJc.CENTER, STVerticalJc.CENTER).setText((String) lcxm.get("JGTS"));
            if (k == list.size() - 1) {
                for (XWPFTableCell cell : table.getRow(2 + k).getTableCells()) {
                    setCellBorders(cell, null, null, STBorder.THICK, null);
                }
            }
        }
        return p;
    }

    // 体检报告样式二（三列版）
    private XWPFParagraph setTjbgStyleTwo(List<Map<String, Object>> list, XWPFDocument document) throws Exception {
        XWPFTable table1 = document.createTable(list.size() + 2, 3);
        table1.getRow(0).setRepeatHeader(true);
        table1.getRow(1).setRepeatHeader(true);
        table1.setCellMargins(0, 0, 0, 0);
        removeTableBorders(table1);
        setTableWidth(table1, 10460);
        if (list.size() > 0) {
            setCellWidth(table1.getRow(2).getCell(0), "2300");
            setCellWidth(table1.getRow(2).getCell(1), "5400");
            setCellWidth(table1.getRow(2).getCell(2), "2300");
        }

        mergeCellsHorizontal(table1, 0, 0, 2);
        for (int i = 0; i < 2; i++) {
            for (XWPFTableCell cell : table1.getRow(i).getTableCells()) {
                setCellBorders(cell, null, null, STBorder.THICK, null);
            }
        }

        List<XWPFTableCell> cells = table1.getRow(1).getTableCells();
        for (XWPFTableCell cell : cells) {
            setCellVAlign(cell, STJc.CENTER, STVerticalJc.CENTER);
        }

        XWPFParagraph p2 = table1.getRow(0).getCell(0).getParagraphs().get(0);
        p2.setVerticalAlignment(TextAlignment.CENTER);
        p2.setSpacingAfterLines(10);
        p2.setSpacingBeforeLines(10);

        paragraphFontBold(table1.getRow(1).getCell(0), "项目名称");
        paragraphFontBold(table1.getRow(1).getCell(1), "检查结果");
        paragraphFontBold(table1.getRow(1).getCell(2), "结果提示");

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> lcxm = list.get(i);
            table1.getRow(2 + i).setCantSplitRow(true);
            XWPFRun run = setCellVAlign(table1.getRow(2 + i).getCell(0), STJc.LEFT, STVerticalJc.TOP).getParagraphs().get(0).createRun();
            String xmmc = (String) lcxm.get("XMMC");
            if (xmmc != null && xmmc.indexOf("★") > -1) {
                run.getCTR().getSymList().add(getCTSym("Wingdings 2", "F0" + Integer.toHexString(234)));
                run.setText(xmmc.substring(xmmc.indexOf("★") + 1));
            } else {
                run.setText(xmmc);
            }
            setCellVAlign(table1.getRow(2 + i).getCell(1), STJc.LEFT, STVerticalJc.CENTER);
            autoBreak(table1.getRow(2 + i).getCell(1).getParagraphs().get(0).createRun(), (String) lcxm.get("JCJG"));
            setCellVAlign(table1.getRow(2 + i).getCell(2), STJc.CENTER, STVerticalJc.TOP).setText((String) lcxm.get("JGTS"));
            if (i == list.size() - 1) {
                for (XWPFTableCell cell : table1.getRow(2 + i).getTableCells()) {
                    setCellBorders(cell, null, null, STBorder.THICK, null);
                }
            }
        }
        return p2;
    }

    // 辅诊与实验室的检查 超长保留14个字符
    private void setTjbgSub(String jc, XWPFRun run) throws Exception {
        if (jc != null) {
            if (jc.length() > 14) {
                jc = jc.substring(0, 14);
            }
            run.setText(jc);
            if (jc.length() < 14) {
                for (int i = 0; i < 14 - jc.length(); i++) {
                    run.setText(" ");
                }
            }
        }
    }

    // 诊断报告描述或提示（固定格式）
    private static void setzdbgData(XWPFParagraph title, String titleData, XWPFParagraph nr, String nrData) throws Exception {
        // 标题
        XWPFRun run = title.createRun();
        run.setText(titleData);
        run.setBold(true);
        // 内容
        nr.setIndentationLeft(500);
        XWPFRun run1 = nr.createRun();
        autoBreak(run1, nrData);
    }

    // 段落字体加粗样式
    private static XWPFParagraph paragraphFontBold(XWPFTableCell cell, String txt) throws Exception {
        XWPFParagraph p = cell.getParagraphs().get(0);
        XWPFRun run = p.createRun();
        run.setText(txt);
        run.setBold(true);
        return p;
    }

    // 段落内容换行
    private static void autoBreak(XWPFRun run, String txt) throws Exception {
        if (run != null && txt != null) {
            txt = txt.replaceAll("\n\n", "\n");
            String[] str = txt.split("\n");
            for (int i = 0; i < str.length; i++) {
                run.setFontFamily("宋体");
                run.setText(str[i]);
                if (i != str.length - 1) {
                    run.addBreak();
                }
            }
        }
    }

    // 跨列合并单元格
    private static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    // 跨行并单元格
    private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    // 三年对比表格字体样式
    private void sndbTableFontStyle(XWPFTableCell cell, Object obj) throws Exception {
        if (cell != null && obj != null) {
            XWPFParagraph p = cell.getParagraphs().get(0);
            XWPFRun run = p.createRun();
            run.setFontFamily("宋体");
            run.setFontSize(10);
            run.setText(obj.toString());
        }
    }


    // 删除本地图片
    private void deleteFile(FileInputStream in, File file) throws Exception {
        if (in != null)
            in.close();
        if (file.exists())
            file.delete();
    }

    // 特殊字符
    private CTSym getCTSym(String wingType, String charStr) throws Exception {
        CTSym sym = CTSym.Factory
                .parse("<xml-fragment w:font=\""
                        + wingType
                        + "\" w:char=\""
                        + charStr
                        + "\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\"> </xml-fragment>");
        return sym;
    }
}
