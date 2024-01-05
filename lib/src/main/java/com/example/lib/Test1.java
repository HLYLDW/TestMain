package com.example.lib;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Test1 {

    /*
     *filePath:xml文件路径
     */
    public static List<BeanArray> readXml(String filePath) throws IOException {
        List<BeanArray> list = new ArrayList<>();
        InputStream in = null;
        // 解析xml文档内容
        try {
            SAXReader reader = new SAXReader();
            in = Files.newInputStream(new File(filePath).toPath());
            Document doc = reader.read(in);
            //获取根节点
            Element root = doc.getRootElement();
            List<Element> usersElem = root.elements();
            for (Element userElem : usersElem) {
                //获取user的index属性值
                String name = userElem.attribute("name").getValue();
                List<Element> textElem = userElem.elements();
                BeanArray itemKey = new BeanArray();
                itemKey.key = name;
                for (Element item : textElem) {
                    String s = item.getText();
                    itemKey.items.add(s == null ? "" : s);
                }
                list.add(itemKey);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
            return null;
        } finally {
            in.close();
        }
        return list;
    }


    static String path = "D:\\project\\gittest\\TestMain\\app\\src\\main\\res";

    public static void main(String[] args) throws IOException {

        List<BeanArray> zhList = readXml(path + "\\values\\arrays.xml");




        File[] files = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() && file.getName().startsWith("values-");
            }
        });
        assert files != null;
        assert zhList != null;

        TreeMap<String, List<BeanArray>> map = new TreeMap<>();
        for (File file : files) {
            if (!file.isDirectory()) continue;
            List<BeanArray> cur = readXml(file.getAbsolutePath() + "\\arrays.xml");
            compareWithZh(zhList, cur);
            map.put(file.getAbsolutePath(), cur);
            createUserDotXML(file.getAbsolutePath() + "\\arrays_temp.xml", cur);
        }


    }

    private static boolean compareWithZh(List<BeanArray> zhList, List<BeanArray> cur) {
        boolean same = true;
        for (int i = 0; i < zhList.size(); i++) {
            BeanArray allSource = zhList.get(i);
            BeanArray source = null;
            for (int j = 0; j < cur.size(); j++) {
                if (cur.get(j).key.equals(allSource.key)) {
                    source = cur.get(j);
                    break;
                }
            }
            if (source == null) {
                source = allSource;
                cur.add(i, allSource);
                same = false;
            }

            if (source.items.size() != allSource.items.size()) {
                same = false;
                for (int j = 0; j < allSource.items.size(); j++) {
                    if (source.items.size() <= j) {
                        source.items.add(allSource.items.get(j));
                    } else if (allSource.items.get(j).equals("")) {
                        if (!source.items.get(j).equals("")) {
                            source.items.add(j, "");
                        }
                    }
                }
            }
        }

        return same;
    }


    /*
     *path:要写入数据的xml文件：D:\\123.xml
     *list:Users集合
     */
    public static boolean createUserDotXML(String path, List<BeanArray> list) throws IOException {
        boolean flag = true;
        OutputStream outputStream = null;
        XMLWriter xmlWriter = null;
        Document document = null;
        try {
            //创建document文档
            document = DocumentHelper.createDocument();
            //创建根节点
            Element rootElem = DocumentHelper.createElement("resources");
            //将list里的值循环写入Element中
            for (BeanArray item : list) {
                Element userElem = DocumentHelper.createElement("array");
                userElem.addAttribute("name", item.key);
                for (String s : item.items) {
                    Element nameElem = DocumentHelper.createElement("item");
                    nameElem.addText(s);
                    userElem.add(nameElem);
                }
                rootElem.add(userElem);
            }
            document.add(rootElem);

            OutputFormat outputFormat = new OutputFormat();
            outputFormat.setEncoding("UTF-8");
            outputStream = Files.newOutputStream(Paths.get(path));
            xmlWriter = new XMLWriter(outputStream, outputFormat);
            xmlWriter.write(document);
        } catch (IOException e) {
            System.out.println("io Exception:" + e);
            return false;
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            return false;
        } finally {
            xmlWriter.close();
            outputStream.close();
        }
        return flag;
    }
}
