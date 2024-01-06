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
import java.util.Objects;
import java.util.TreeMap;

public class Test1 {

    public static TreeMap<String, String> readStringXml(String filePath) throws IOException {
        TreeMap<String, String> map = new TreeMap<>();
        InputStream in = null;
        try {
            SAXReader reader = new SAXReader();
            in = Files.newInputStream(new File(filePath).toPath());
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> usersElem = root.elements();
            for (Element userElem : usersElem) {
                String key = userElem.attribute("name").getValue();
                String value = userElem.getText();
                map.put(key, value);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
            return null;
        } finally {
            in.close();
        }
        return map;
    }

    public static List<BeanArray> readArrayXml(String filePath) throws IOException {
        List<BeanArray> list = new ArrayList<>();
        InputStream in = null;
        try {
            SAXReader reader = new SAXReader();
            in = Files.newInputStream(new File(filePath).toPath());
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> usersElem = root.elements();
            for (Element userElem : usersElem) {
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

        File[] files = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() && file.getName().startsWith("values") && !file.getName().equals("values-en");
            }
        });
        Bean zhBean = new Bean();
        assert files != null;

        List<Bean> list = new ArrayList<>();
        for (File dir : files) {
            if (!dir.isDirectory()) continue;
            if (dir.getName().equals("values")) {
                zhBean = praseBeanFromFile(dir);
            } else {
                list.add(praseBeanFromFile(dir));
            }
        }

        for (Bean bean : list) {
            compareWithZh(zhBean, bean);
            createUserDotXML(bean);
        }

    }

    private static Bean praseBeanFromFile(File dir) throws IOException {
        Bean bean = new Bean();
        File[] childFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && (file.getName().equals("arrays.xml") || file.getName().equals("strings.xml"));
            }
        });
        assert childFiles != null;

        bean.path = dir.getAbsolutePath();

        for (File file : childFiles) {
            if (file.getName().equals("arrays.xml")) {
                if (!dir.getName().equals("values")) {
                    preWriteFile(file.getAbsolutePath());
                }
                bean.arrays = readArrayXml(file.getAbsolutePath());
            } else if (file.getName().equals("strings.xml")) {
                if (!dir.getName().equals("values")) {
                    preWriteFile(file.getAbsolutePath());
                }
                bean.strings = readStringXml(file.getAbsolutePath());
            }
        }

        return bean;
    }

    private static void preWriteFile(String filePath) {
        StringBuilder content = new StringBuilder(Objects.requireNonNull(FileUtil.readFile(filePath)));
        String newContent = content.toString().replaceAll("\\\\'", "'").
                replaceAll("'", "\\\\'").
                replaceAll("\\[&] ", "").
                replaceAll("\\[&]", "");
        FileUtil.writerFile(filePath, newContent);
    }

    private static void compareWithZh(Bean zhBean, Bean bean) {

        List<BeanArray> zhList = zhBean.arrays;
        List<BeanArray> curList = bean.arrays;

        for (int i = 0; i < zhList.size(); i++) {
            BeanArray zhSource = zhList.get(i);
            BeanArray source = null;
            for (int j = 0; j < curList.size(); j++) {
                if (curList.get(j).key.equals(zhSource.key)) {
                    source = curList.get(j);
                    break;
                }
            }
            if (source == null) {
                source = zhSource;
                curList.add(i, zhSource);
            }

            if (source.items.size() != zhSource.items.size()) {
                for (int j = 0; j < zhSource.items.size(); j++) {
                    if (source.items.size() <= j) {
                        source.items.add(zhSource.items.get(j));
                    } else if (zhSource.items.get(j).equals("")) {
                        if (!source.items.get(j).equals("")) {
                            source.items.add(j, "");
                        }
                    }
                }
            }
        }


        for (String key : zhBean.strings.keySet()) {
            if (!bean.strings.containsKey(key)) {
                bean.strings.put(key,zhBean.strings.get(key));
            }
        }

    }



    public static boolean createUserDotXML(Bean bean) throws IOException {
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
            for (BeanArray item : bean.arrays) {
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
            outputStream = Files.newOutputStream(Paths.get(bean.path + "\\arrays.xml"));
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

        try {
            //创建document文档
            document = DocumentHelper.createDocument();
            //创建根节点
            Element rootElem = DocumentHelper.createElement("resources");
            //将list里的值循环写入Element中
            for (String key : bean.strings.keySet()) {
                Element userElem = DocumentHelper.createElement("string");
                userElem.addAttribute("name", key);
                userElem.setText(bean.strings.get(key));
                rootElem.add(userElem);
            }
            document.add(rootElem);

            OutputFormat outputFormat = new OutputFormat();
            outputFormat.setEncoding("UTF-8");
            outputStream = Files.newOutputStream(Paths.get(bean.path + "\\strings.xml"));
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
