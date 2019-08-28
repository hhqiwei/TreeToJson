package cn.db.jdbc;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToJson {

    public void treeToJson(List<Tree> list) {
        List<Tree> treeList = new ArrayList<Tree>();
        treeList = listToTree(list);// 调用函数，传入List<Tree>参数
//        System.out.println("SUCCESS TO JSON.\n" + JSON.toJSONString(treeList));

        //将转换完的数据保存到本地文件中
        BufferedWriter writer = null;

        File file = new File("src/treetojson.json");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(JSON.toJSONString(treeList));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("The json writer success!");
        }
    }

    // 用递归的方法
    public List<Tree> listToTree(List<Tree> list) {
        // 用递归找子节点
        List<Tree> treeList = new ArrayList<Tree>();
        for (Tree tree : list) {
            if (tree.getPid() == 0) {//先找到根节点
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    private Tree findChildren(Tree tree, List<Tree> list) {
        for (Tree node : list) {
            if (node.getPid() == tree.getId()) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<Tree>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }
}
